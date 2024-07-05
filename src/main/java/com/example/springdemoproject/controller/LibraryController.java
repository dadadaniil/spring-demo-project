package com.example.springdemoproject.controller;

import com.example.springdemoproject.model.BookDto;
import com.example.springdemoproject.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto) {
        BookDto createdBook = libraryService.createBook(bookDto);
        return ResponseEntity.ok(createdBook);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(libraryService.findAllBooks());
    }

    @PutMapping
    public ResponseEntity<BookDto> updateBook(@Valid @RequestBody BookDto bookDto) {
        BookDto updatedBook = libraryService.updateBook(bookDto);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        libraryService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

}