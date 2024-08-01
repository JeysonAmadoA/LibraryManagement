package com.jeyson.Core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeyson.Users.Application.Repositories.UserRepository;
import com.jeyson.Users.Application.Services.JwtService;
import com.jeyson.Users.Domain.Constants.Security.Role;
import com.jeyson.Users.Domain.Entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    protected String token;

    @BeforeEach
    public void setUpLogin() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPassword = encoder.encode("1234");

        User user = User.builder()
                .name("Testing")
                .password(encryptedPassword)
                .documentNumber(String.valueOf(UUID.randomUUID()))
                .email("test@example.com")
                .role(Role.ADMIN)
                .build();

        userRepository.save(user);
        token = jwtService.generateToken(user);
    }

    protected void assertPostRequest(String url, Object body, ResultMatcher expectedStatus) throws Exception {
        String jsonBody = objectMapper.writeValueAsString(body);

        mockMvc.perform(post(url)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)).andExpect(expectedStatus);
    }
}
