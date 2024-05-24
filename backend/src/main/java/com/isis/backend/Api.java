package com.isis.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class Api {

    @Autowired
    private BookRepository bookRepository;

    @Value("${app.title}")
    private String title;


    @GetMapping("/title")
    public String getTitle() {

        return title;
    }

    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/{isbn}")
    public Book getBookByISBN(@PathVariable String isbn) {
        return bookRepository.findBookByIsbn(isbn).orElse(new Book("Aucun livre avec cet isbn", "", ""));
    }

}
