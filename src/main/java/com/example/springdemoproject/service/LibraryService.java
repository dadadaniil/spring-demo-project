package com.example.springdemoproject.service;

import com.example.springdemoproject.model.Book;
import com.example.springdemoproject.model.BookDto;
import com.example.springdemoproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private final BookRepository bookRepository;

    @Autowired
    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto createBook(BookDto bookDto) {
        Book book = Book.builder()
            .name(bookDto.name())
            .author(bookDto.author())
            .sizePages(bookDto.sizePages())
            .publishingDate(bookDto.publishingDate())
            .build();

        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);
    }

    public List<BookDto> findAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream().map(this::convertToDto).toList();
    }

    private BookDto convertToDto(Book book) {
        //This snippet should be located in mapper class, admission in educational purposes
        return new BookDto(book.getId(), book.getName(), book.getAuthor(), book.getSizePages(), book.getPublishingDate());
    }

    public BookDto updateBook(BookDto bookDto) {
        if (bookRepository.existsById(bookDto.id())) {

            Book book = Book.builder()
                .name(bookDto.name())
                .author(bookDto.author())
                .sizePages(bookDto.sizePages())
                .publishingDate(bookDto.publishingDate())
                .build();

            Book updatedBook = bookRepository.save(book);
            return convertToDto(updatedBook);
        }
        throw new IllegalStateException("Book with id: " + bookDto.id() + " does not exist.");
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalStateException("Book with id: " + id + " does not exist.");
        }
        bookRepository.deleteById(id);
    }
}