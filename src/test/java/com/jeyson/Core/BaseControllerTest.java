package com.jeyson.Core;

import com.jeyson.Books.Application.Repositories.BookRepository;
import com.jeyson.Books.Application.Repositories.BookcaseRepository;
import com.jeyson.Books.Domain.Entities.Book;
import com.jeyson.Books.Domain.Entities.Bookcase;
import com.jeyson.Users.Application.Repositories.UserRepository;
import com.jeyson.Users.Application.Services.JwtService;
import com.jeyson.Users.Domain.Constants.Security.Role;
import com.jeyson.Users.Domain.Entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BaseControllerTest {

    @Autowired
    protected BookRepository bookRepository;

    @Autowired
    protected BookcaseRepository bookcaseRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    protected String token;

    @BeforeEach
    public void setUp() {
        Bookcase bookcase = Bookcase.builder()
                .bookcaseName("Bookcase")
                .category("Magic")
                .build();

        bookcaseRepository.save(bookcase);

        Book book = Book.builder()
                .bookName("Harry Potter")
                .bookcaseId(bookcase.getId())
                .author("JK Rowling")
                .pages((short) 323)
                .publicationYear((short) 1990)
                .build();

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
        bookRepository.save(book);
        token = jwtService.generateToken(user);
    }
}
