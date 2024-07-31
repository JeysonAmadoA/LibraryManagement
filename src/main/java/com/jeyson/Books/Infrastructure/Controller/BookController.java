package com.jeyson.Books.Infrastructure.Controller;

import com.jeyson.Books.Application.Services.BookService;
import com.jeyson.Books.Domain.Dto.BookDto;
import com.jeyson.Books.Domain.Dto.RegisterBookDto;
import com.jeyson.Core.Infrastructure.Controller.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController extends BaseController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<?> getAllBookcases() {
        List<BookDto> books = bookService.findAll();
        Map<String, Object> response = getJsonResponse(books);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        BookDto books = bookService.filterById(id);
        Map<String, Object> response = getJsonResponse(books);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/store")
    public ResponseEntity<?> storeBook(@RequestBody RegisterBookDto bookDto) {
        BookDto bookCreated = bookService.store(bookDto);
        Map<String, Object> response = getJsonResponse(bookCreated, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody RegisterBookDto bookDto) {
        BookDto bookUpdated = bookService.update(id, bookDto);
        Map<String, Object> response = getJsonResponse(bookUpdated);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        Map<String, Object> response = getJsonResponse("Libro eliminado correctamente");
        return ResponseEntity.ok().body(response);
    }
}
