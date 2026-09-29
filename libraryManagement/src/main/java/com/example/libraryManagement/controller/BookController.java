package com.example.libraryManagement.controller;

import com.example.libraryManagement.dto.BookRequestDTO;
import com.example.libraryManagement.dto.BookResponseDTO;
import com.example.libraryManagement.entity.Book;
import com.example.libraryManagement.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public PagedModel<BookResponseDTO> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy){
        return new PagedModel<>(bookService.getAllBooks(page,size,sortBy));
    }

    @PostMapping
    public BookResponseDTO addBook(@Valid @RequestBody BookRequestDTO book)
    {
        return bookService.addBook(book);
    }
    @PutMapping("/{id}")
    public BookResponseDTO updateBook(@PathVariable Long id,@Valid @RequestBody BookRequestDTO book){
        return bookService.updateBook(id,book);

    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return "Book deleted successfully";
    }
    @GetMapping("/{id}")
    public BookResponseDTO getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }
}
