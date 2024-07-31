package com.jeyson.Books.Infrastructure.Controller;

import com.jeyson.Books.Application.Services.BookcaseService;
import com.jeyson.Books.Domain.Dto.BookcaseDto;
import com.jeyson.Books.Domain.Dto.RegisterBookcaseDto;
import com.jeyson.Core.Infrastructure.Controller.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookcases")
public class BookcaseController extends BaseController {

    private final BookcaseService bookcaseService;

    public BookcaseController(BookcaseService bookcaseService) {
        this.bookcaseService = bookcaseService;
    }

    @GetMapping
    public ResponseEntity<?> getAllBookcases() {
        List<BookcaseDto> bookcase = bookcaseService.findAll();
        Map<String, Object> response = getJsonResponse(bookcase);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookcaseById(@PathVariable Long id) {
        BookcaseDto bookcase = bookcaseService.filterById(id);
        Map<String, Object> response = getJsonResponse(bookcase);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/store")
    public ResponseEntity<?> storeBookcase(@RequestBody RegisterBookcaseDto bookcaseDto) {
        BookcaseDto bookcaseCreated = bookcaseService.store(bookcaseDto);
        Map<String, Object> response = getJsonResponse(bookcaseCreated, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateBookcase(@PathVariable Long id, @RequestBody RegisterBookcaseDto bookcaseDto) {
        BookcaseDto bookcaseUpdated = bookcaseService.update(id, bookcaseDto);
        Map<String, Object> response = getJsonResponse(bookcaseUpdated);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBookcase(@PathVariable Long id) {
        bookcaseService.deleteById(id);
        Map<String, Object> response = getJsonResponse("Estanteria eliminada exitosamente");
        return ResponseEntity.ok().body(response);
    }
}
