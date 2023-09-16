package com.tcs.library.librarymanagement.service;

import com.tcs.library.librarymanagement.model.Author;
import com.tcs.library.librarymanagement.model.Book;
import com.tcs.library.librarymanagement.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService
{
    @Autowired
    private AuthorRepository authorRepository;


    public Author addAuthor(Author author)
    {
        try
        {
            return this.authorRepository.save(author);
        }
        catch (Exception e)
        {
            System.out.println("Unable to add Author : "+e.getMessage());
            return null;
        }
    }


    public Author getAuthor(Long id)
    {
        try
        {
            return this.authorRepository.findById(id).get();
        }
        catch (Exception e)
        {
            System.out.println("Unable to fetch Author : "+e.getMessage());
            return null;
        }
    }

    public List<Author> getAllAuthors()
    {
        return this.authorRepository.findAll();
    }

    public Boolean deleteAuthor(Long id)
    {
        try
        {
            if(!this.authorRepository.existsById(id))
            {
                return false;
            }

            this.authorRepository.deleteById(id);
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Unable to Delete Author : "+e.getMessage());
            return false;
        }
    }

    public Author updateAuthor(Long id,Author author)
    {
        try
        {
            if(!this.authorRepository.existsById(id))
            {
                return null;
            }
            return this.authorRepository.save(author);
        }
        catch (Exception e)
        {
            System.out.println("Unable to update Author : "+e.getMessage());
            return null;
        }
    }

    public List<Book> getBooks(Long id)
    {
        try
        {
            if(!this.authorRepository.existsById(id))
            {
                return null;
            }
            return this.authorRepository.findById(id).get().getBooks();
        }
        catch (Exception e)
        {
            System.out.println("Unable to fetch Author books: "+e.getMessage());
            return null;
        }
    }


}
