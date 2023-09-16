package com.tcs.library.librarymanagement.repository;

import com.tcs.library.librarymanagement.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long>
{
}
