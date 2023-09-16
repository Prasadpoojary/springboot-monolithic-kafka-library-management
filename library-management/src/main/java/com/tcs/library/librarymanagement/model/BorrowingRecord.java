package com.tcs.library.librarymanagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Book book;
    private LocalDate borrowingDate;
    private LocalDate returnDate;

    @Column(name="user_name")
    private String user;

    private boolean canDelete;
}

