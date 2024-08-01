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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
public class ExistsByBookIdAndReturnedAtIsNullTest {

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

        Book bookOne = Book.builder()
                .bookName("Harry Potter y la piedra filosofal")
                .bookcaseId(bookcase.getId()).author("JK Rowling")
                .pages((short) 323).publicationYear((short) 1990)
                .build();

        Book bookTwo = Book.builder()
                .bookName("Harry Potter y el prisionero de Azkaban")
                .bookcaseId(bookcase.getId()).author("JK Rowling")
                .pages((short) 323).publicationYear((short) 1990)
                .build();

        User user = User.builder()
                .name("Testing").password("password")
                .documentNumber(String.valueOf(UUID.randomUUID()))
                .email("test@example.com").role(Role.ADMIN)
                .build();

        userRepository.save(user);
        bookRepository.save(bookOne);
        bookRepository.save(bookTwo);

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDate currentDate = LocalDate.now();

        BookRent bookRent = BookRent.builder()
                .bookId(bookOne.getId())
                .userWhoRentedId(user.getId())
                .userWhoDeliveryId(user.getId())
                .rentedAt(currentTime)
                .deliveryAt(currentTime)
                .returningDate(currentDate)
                .build();

        bookRentRepository.save(bookRent);
    }

    @Test
    void isBookAlreadyRentedTest(){
        setUp();
        Book book = bookRepository.findAll().getFirst();
        assertTrue(bookRentRepository.existsByBookIdAndReturnedAtIsNull(book.getId()));
    }

    @Test
    void isBookAvailableTest(){
        setUp();
        Book book = bookRepository.findAll().get(1);
        assertFalse(bookRentRepository.existsByBookIdAndReturnedAtIsNull(book.getId()));
    }
}
