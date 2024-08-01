package com.jeyson.BooksRent.Controllers.BookRentController;


import com.jeyson.Books.Application.Repositories.BookRepository;
import com.jeyson.Books.Application.Repositories.BookcaseRepository;
import com.jeyson.Books.Domain.Entities.Book;
import com.jeyson.Books.Domain.Entities.Bookcase;
import com.jeyson.BooksRent.Application.Repositories.BookRentRepository;
import com.jeyson.BooksRent.Domain.Dto.RegisterBookRentDto;
import com.jeyson.BooksRent.Domain.Entities.BookRent;
import com.jeyson.Core.BaseControllerTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class StoreBookRentTest extends BaseControllerTest {

    @Autowired
    private BookRentRepository bookRentRepository;

    @Autowired
    protected BookRepository bookRepository;

    @Autowired
    protected BookcaseRepository bookcaseRepository;

    @BeforeEach
    void setUp(){
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

        bookRepository.save(book);
    }

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

        assertPostRequest("/rent/store", dto, status().isCreated());
        List<BookRent> list = bookRentRepository.findAll();
        assertThat(list).isNotEmpty();

        boolean recordExists = list.stream().anyMatch(rent ->
                rent.getBookId() == bookId && rent.getUserWhoRentedId() == userId
        );
        assertThat(recordExists).isTrue();
    }

}
