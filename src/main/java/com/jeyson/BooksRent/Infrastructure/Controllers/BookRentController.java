package com.jeyson.BooksRent.Infrastructure.Controllers;

import com.jeyson.BooksRent.Application.Services.BookRentService;
import com.jeyson.BooksRent.Domain.Dto.BookRentDto;
import com.jeyson.BooksRent.Domain.Dto.RegisterBookRentDto;
import com.jeyson.Core.Infrastructure.Controller.BaseController;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rent")
public class BookRentController extends BaseController {

    private final BookRentService bookRentService;

    public BookRentController(BookRentService bookRentService) {
        this.bookRentService = bookRentService;
    }

    @GetMapping
    public ResponseEntity<?> getAllBooksRent() {
        List<BookRentDto> booksRented = bookRentService.findAll();
        Map<String, Object> response = getJsonResponse(booksRented);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookRentById(@PathVariable Long id) {
        BookRentDto bookRented = bookRentService.filterById(id);
        Map<String, Object> response = getJsonResponse(bookRented);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/store")
    public ResponseEntity<?> storeBookRent(@Valid @RequestBody RegisterBookRentDto registerBookRentDto) {
        BookRentDto rentCreated = bookRentService.store(registerBookRentDto);
        Map<String, Object> response = getJsonResponse(rentCreated, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateBookRent(@PathVariable Long id, @RequestBody RegisterBookRentDto rentDto) {
        BookRentDto rentUpdated = bookRentService.update(id, rentDto);
        Map<String, Object> response = getJsonResponse(rentUpdated);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBookRent(@PathVariable Long id) {
        bookRentService.deleteById(id);
        Map<String, Object> response = getJsonResponse("Alquiler de libro eliminado exitosamente");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
        bookRentService.storeReturnBookRent(id);
        Map<String, Object> response = getJsonResponse("Registro existoso");
        return ResponseEntity.ok().body(response);
    }

}
