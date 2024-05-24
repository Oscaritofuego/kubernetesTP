package com.isis.backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.context.annotation.Primary;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    public String isbn = "";
    public String title = "";
    public String author = "";

    public Book(String title, String author, String isdb) {
        this.title = title;
        this.author = author;
        this.isbn = isdb;
        this.id = 0;
    }

    public Book() {

    }
}
