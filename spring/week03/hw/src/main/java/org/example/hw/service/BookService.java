package org.example.hw.service;

import org.example.hw.exception.BookNotFoundException;
import org.example.hw.exception.DuplicateBookException;
import org.example.hw.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private List<Book> books = new ArrayList<Book>();

    // 도서 등록
    public void addBook(Book book) {
        boolean exists = books.stream()
                .anyMatch(b -> b.getTitle().equals(book.getTitle())
                        && b.getAuthor().equals(book.getAuthor()));
        if (exists) {
            throw new DuplicateBookException("이미 등록된 도서입니다: " + book.getTitle());
        }
        books.add(book);
    }

    // 도서 목록 조회
    public List<Book> findAll() {
        if (books.isEmpty()) {
            throw new BookNotFoundException("등록된 도서가 없습니다.");
        }
        return books;
    }

}
