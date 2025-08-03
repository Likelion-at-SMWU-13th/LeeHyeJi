package org.example.hw.service;

import org.example.hw.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private List<Book> books = new ArrayList<Book>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> findAll() {
        return books;
    }

}
