package org.example.hw.dto;

import org.example.hw.model.Book;

public class BookResponse {
    private String title;
    private String author;
    private int price;

    public BookResponse() {}

    public BookResponse(Book b) {
        this.title = b.getTitle();
        this.author = b.getAuthor();
        this.price = b.getPrice();
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPrice() { return price; }
}
