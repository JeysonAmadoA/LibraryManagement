package com.jeyson.BooksRent.Repositories.BookRentRepository;

import com.jeyson.Books.Application.Repositories.BookRepository;
import com.jeyson.Books.Application.Repositories.BookcaseRepository;
import com.jeyson.Books.Domain.Entities.Book;
import com.jeyson.Books.Domain.Entities.Bookcase;
import com.jeyson.BooksRent.Application.Repositories.BookRentRepository;
import com.jeyson.BooksRent.Domain.Entities.BookRent;
import com.jeyson.Users.Application.Repositories.UserRepository;
import com.jeyson.Users.Domain.Constants.Security.Role;
import com.jeyson.Users.Domain.Entities.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class SaveBookRentTest {

    @Autowired
    protected BookcaseRepository bookcaseRepository;

    @Autowired
    protected BookRepository bookRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private BookRentRepository bookRentRepository;

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

        User user = User.builder()
                .name("Testing")
                .password("password")
                .documentNumber(String.valueOf(UUID.randomUUID()))
                .email("test@example.com")
                .role(Role.ADMIN)
                .build();

        userRepository.save(user);
        bookRepository.save(book);
    }

    @Test
    @Transactional
    @Rollback
    void saveBookRent(){
        setUp();
        long userId = userRepository.findAll().getFirst().getId();
        long bookId = bookRepository.findAll().getFirst().getId();
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDate currentDate = LocalDate.now();

        BookRent bookRent = BookRent.builder()
                .bookId(bookId)
                .userWhoRentedId(userId)
                .userWhoDeliveryId(userId)
                .rentedAt(currentTime)
                .deliveryAt(currentTime)
                .returningDate(currentDate)
                .build();

        bookRentRepository.save(bookRent);
        BookRent bookStored = bookRentRepository.findAll().getFirst();
        assertEquals(bookId, bookStored.getBookId());
        assertEquals(userId, bookStored.getUserWhoRentedId());
        assertEquals(currentTime, bookStored.getRentedAt());
        assertEquals(currentTime, bookStored.getDeliveryAt());
        assertEquals(currentDate, bookStored.getReturningDate());
    }
}
