package com.tcs.library.librarymanagement.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Transactional
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    @OneToMany(mappedBy = "author")
    private List<Book> books;
}

