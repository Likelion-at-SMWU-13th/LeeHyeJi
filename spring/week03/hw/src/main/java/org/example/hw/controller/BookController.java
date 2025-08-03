package org.example.hw.controller;

import org.example.hw.model.Book;
import org.example.hw.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/books")
    public String viewBooks(Model model) {
        var books = bookService.findAll();
        model.addAttribute("books", books);

        return "books.html";
    }

    @RequestMapping(path="/books", method = RequestMethod.POST)
    public String addBook(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam int price,
            Model model
    ){
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);
        bookService.addBook(book);

        var books = bookService.findAll();
        model.addAttribute("books", books);

        return "books.html";
    }
}
