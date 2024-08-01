package com.jeyson.BooksRent.Services.BookRentService;

import com.jeyson.BooksRent.Application.Repositories.BookRentRepository;
import com.jeyson.BooksRent.Application.Services.CustomerService;
import com.jeyson.BooksRent.Domain.Dto.BookRentDto;
import com.jeyson.BooksRent.Domain.Dto.RegisterBookRentDto;
import com.jeyson.BooksRent.Domain.Entities.BookRent;
import com.jeyson.BooksRent.Domain.Exceptions.BookAlreadyRentedException;
import com.jeyson.BooksRent.Domain.Exceptions.BooksRentedExceededException;
import com.jeyson.BooksRent.Domain.Exceptions.CustomerWithPenaltiesException;
import com.jeyson.BooksRent.Infrastructure.Services.BookRentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StoreTest {

    @Mock
    private BookRentRepository bookRentRepository;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private BookRentServiceImpl bookRentService;

    @Test
    public void storeSuccessTest() {
        RegisterBookRentDto registerDto = RegisterBookRentDto.builder()
                .bookId(1).userWhoRentedId(1).rentedAt(LocalDateTime.now())
                .userWhoDeliveryId(2).deliveryAt(LocalDateTime.now()).build();

        BookRent newBookRent = BookRent.builder()
                .bookId(1).userWhoRentedId(1).rentedAt(LocalDateTime.now())
                .userWhoDeliveryId(2).deliveryAt(LocalDateTime.now()).build();

        newBookRent.setId(1L);

        when(customerService.customerHasPenalty(anyLong())).thenReturn(false);
        when(customerService.getBooksRentedByCustomerId(anyLong())).thenReturn(Collections.emptyList());
        when(bookRentRepository.existsByBookIdAndReturnedAtIsNull(anyLong())).thenReturn(false);
        when(bookRentRepository.save(any(BookRent.class))).thenReturn(newBookRent);

        BookRentDto rentDto = bookRentService.store(registerDto);

        assertNotNull(rentDto);
        verify(customerService).customerHasPenalty(1L);
        verify(customerService).getBooksRentedByCustomerId(1L);
        verify(bookRentRepository).existsByBookIdAndReturnedAtIsNull(1L);
        verify(bookRentRepository).save(argThat(bookRent ->
                bookRent.getBookId() == 1 &&
                        bookRent.getUserWhoRentedId() == 1 &&
                        bookRent.getUserWhoDeliveryId() == 2 &&
                        bookRent.getReturningDate() != null
        ));
    }

    @Test
    public void storeThrowCustomerWithPenaltiesException() {
        RegisterBookRentDto registerDto = RegisterBookRentDto.builder()
                .bookId(1).userWhoRentedId(1).rentedAt(LocalDateTime.now())
                .userWhoDeliveryId(2).deliveryAt(LocalDateTime.now()).build();

        when(customerService.customerHasPenalty(anyLong())).thenReturn(true);
        assertThrows(CustomerWithPenaltiesException.class, () -> bookRentService.store(registerDto));

        verify(customerService).customerHasPenalty(1L);
        verify(customerService, never()).getBooksRentedByCustomerId(anyLong());
        verify(bookRentRepository, never()).existsByBookIdAndReturnedAtIsNull(anyLong());
        verify(bookRentRepository, never()).save(any(BookRent.class));
    }


    @Test
    public void storeThrowBooksRentedExceededException() {
        RegisterBookRentDto registerDto = RegisterBookRentDto.builder()
                .bookId(1).userWhoRentedId(1).rentedAt(LocalDateTime.now())
                .userWhoDeliveryId(2).deliveryAt(LocalDateTime.now()).build();

        BookRentDto rentDto = BookRentDto.builder().build();
        List<BookRentDto> rents = List.of(rentDto, rentDto, rentDto);

        when(customerService.customerHasPenalty(anyLong())).thenReturn(false);
        when(customerService.getBooksRentedByCustomerId(anyLong())).thenReturn(rents);
        assertThrows(BooksRentedExceededException.class, () -> bookRentService.store(registerDto));

        verify(customerService).customerHasPenalty(1L);
        verify(customerService).getBooksRentedByCustomerId(1L);
        verify(bookRentRepository, never()).existsByBookIdAndReturnedAtIsNull(anyLong());
        verify(bookRentRepository, never()).save(any(BookRent.class));
    }

    @Test
    public void storeThrowBookAlreadyRentedException() {
        RegisterBookRentDto registerDto = RegisterBookRentDto.builder()
                .bookId(1).userWhoRentedId(1).rentedAt(LocalDateTime.now())
                .userWhoDeliveryId(2).deliveryAt(LocalDateTime.now()).build();

        when(customerService.customerHasPenalty(anyLong())).thenReturn(false);
        when(customerService.getBooksRentedByCustomerId(anyLong())).thenReturn(Collections.emptyList());
        when(bookRentRepository.existsByBookIdAndReturnedAtIsNull(anyLong())).thenReturn(true);
        assertThrows(BookAlreadyRentedException.class, () -> bookRentService.store(registerDto));

        verify(customerService).customerHasPenalty(1L);
        verify(customerService).getBooksRentedByCustomerId(1L);
        verify(bookRentRepository).existsByBookIdAndReturnedAtIsNull(1L);
        verify(bookRentRepository, never()).save(any(BookRent.class));
    }

}
