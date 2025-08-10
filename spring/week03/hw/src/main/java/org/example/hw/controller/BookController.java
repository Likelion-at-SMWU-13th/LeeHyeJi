package org.example.hw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.hw.dto.BookRequest;
import org.example.hw.dto.BookResponse;
import org.example.hw.model.Book;
import org.example.hw.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book API", description = "도서 등록 및 조회 API")
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "도서 등록", description = "도서 등록하기")
    @Parameters({
            @Parameter(name = "title", description = "도서 제목"),
            @Parameter(name = "author", description = "도서 저자"),
            @Parameter(name = "price", description = "도서 가격")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "등록 성공", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "중복된 도서", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/add")
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest req) {
        Book book = new Book();
        book.setTitle(req.getTitle());
        book.setAuthor(req.getAuthor());
        book.setPrice(req.getPrice());
        bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BookResponse(book));
    }

    @Operation(summary = "도서 전체 조회", description = "등록된 모든 도서 조회하기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "등록된 도서가 없음", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/read")
    public ResponseEntity<List<BookResponse>> viewBooks() {
        var list = bookService.findAll().stream()
                .map(BookResponse::new)
                .toList();
        return ResponseEntity.ok(list);
    }
}
