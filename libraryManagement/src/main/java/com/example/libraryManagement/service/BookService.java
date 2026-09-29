package com.example.libraryManagement.service;

import com.example.libraryManagement.dto.BookRequestDTO;
import com.example.libraryManagement.dto.BookResponseDTO;
import com.example.libraryManagement.entity.Book;
import com.example.libraryManagement.exception.BookNotFoundException;
import com.example.libraryManagement.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    private BookResponseDTO toResponseDTO(Book book){
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.isAvailable()
        );
    }

    public Page<BookResponseDTO> getAllBooks(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookRepository.findAll(pageable).map(this::toResponseDTO);
    }
    public BookResponseDTO addBook(@Valid BookRequestDTO dto){
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        return toResponseDTO(bookRepository.save(book));
    }

    public BookResponseDTO updateBook(Long id, @Valid  BookRequestDTO dto){
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        return toResponseDTO(bookRepository.save(book));
    }
    public void deleteBook(Long id){
        if (!bookRepository.existsById(id)){
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }
    public BookResponseDTO getBookById(Long id){
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return toResponseDTO(book);

    }
}
