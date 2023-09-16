package com.tcs.library.librarymanagement.repository;

import com.tcs.library.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long>
{
}
