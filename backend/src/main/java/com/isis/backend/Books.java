package com.isis.backend;

import java.util.List;

public class Books {
    public Books(List<Book> books) {
        this.books = books;
    }

    public List<Book> books = List.of();
}
