package com.example.libraryManagement.exception;

public class BookAlreadyIssuedException extends RuntimeException{
    public BookAlreadyIssuedException(Long bookId){
        super("Book with id " + bookId + " is already issued to someone else");
    }
}
