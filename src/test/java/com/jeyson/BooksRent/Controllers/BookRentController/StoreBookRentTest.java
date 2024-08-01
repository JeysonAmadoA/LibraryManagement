package com.jeyson.BooksRent.Controllers.BookRentController;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeyson.BooksRent.Domain.Dto.RegisterBookRentDto;
import com.jeyson.Core.BaseControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
public class StoreBookRentTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @Rollback
    void storeBookSuccessTest() throws Exception {
        long userId = userRepository.findAll().getFirst().getId();
        long bookId = bookRepository.findAll().getFirst().getId();

        RegisterBookRentDto dto = RegisterBookRentDto.builder()
                .bookId(bookId)
                .userWhoRentedId(userId)
                .userWhoDeliveryId(userId)
                .rentedAt(LocalDateTime.now())
                .deliveryAt(LocalDateTime.now())
                .build();


        mockMvc.perform(post("/rent/store")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

}
