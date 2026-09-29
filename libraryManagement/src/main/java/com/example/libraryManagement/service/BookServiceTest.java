package com.example.libraryManagement.service;

import com.example.libraryManagement.dto.BookResponseDTO;
import com.example.libraryManagement.entity.Book;
import com.example.libraryManagement.exception.BookNotFoundException;
import com.example.libraryManagement.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService;

    @Test
    void getBookById_whenBookExists_returnsDTto(){
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Clean code");
        book.setAuthor("Robert C. Martin");
        book.setIsbn("124357799");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        BookResponseDTO result = bookService.getBookById(1L);
        assertEquals("Clean code",result.getTitle());
        assertEquals(1L,result.getId());
    }

    @Test
    void getBookById_whenBookMissing_throwsException(){
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class,() -> bookService.getBookById(99L));
    }

    @Test
    void deleteBook_whenBookMissing_throwsException(){
        when(bookRepository.existsById(99L)).thenReturn(false);
        assertThrows(BookNotFoundException.class,() ->bookService.deleteBook(99L));
        verify(bookRepository,never()).deleteById(any());
    }

}
