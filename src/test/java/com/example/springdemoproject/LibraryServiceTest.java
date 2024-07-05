package com.example.springdemoproject;
import com.example.springdemoproject.model.Book;
import com.example.springdemoproject.model.BookDto;
import com.example.springdemoproject.repository.BookRepository;
import com.example.springdemoproject.service.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class LibraryServiceTest {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BookRepository bookRepository;

    private BookDto bookDto;
    private Book book;

    @BeforeEach
    void setUp() {
        bookDto = new BookDto(null, "Test Book", "Author", 123L, LocalDate.now());
        book = new Book(null, "Test Book", "Author", 123L, LocalDate.now());
        bookRepository.deleteAll();
    }

    @Test
    public void testCreateBook() {
        BookDto createdBook = libraryService.createBook(bookDto);
        assertThat(createdBook.name()).isEqualTo(bookDto.name());
    }

    @Test
    public void testFindAllBooks() {
        bookRepository.save(book);
        List<BookDto> books = libraryService.findAllBooks();
        assertThat(books).hasSize(1).extracting("name").contains("Test Book");
    }
}