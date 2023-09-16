package com.tcs.library.librarymanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tcs.library.librarymanagement.enums.BookStatus;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;

@Entity
@Data
@Transactional
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String ISBN;
    @JsonIgnoreProperties("books")
    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;
    @Enumerated(EnumType.STRING)
    private BookStatus status;
}
