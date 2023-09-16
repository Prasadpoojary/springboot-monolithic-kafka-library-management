package com.tcs.library.librarymanagement.controller;

import com.tcs.library.librarymanagement.dto.MessageDTO;
import com.tcs.library.librarymanagement.enums.StatusEnum;
import com.tcs.library.librarymanagement.model.Author;
import com.tcs.library.librarymanagement.model.Book;
import com.tcs.library.librarymanagement.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController
{

    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<Object> addAuthor(@RequestBody Author author)
    {
        Author responseData=this.authorService.addAuthor(author);
        if(responseData!=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
        }
        else
        {
            MessageDTO message=new MessageDTO(StatusEnum.ERROR,"Unable to add Author");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAuthor(@PathVariable Long id)
    {
        Author responseData=this.authorService.getAuthor(id);
        if(responseData!=null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
        else
        {
            MessageDTO message=new MessageDTO(StatusEnum.ERROR,"Unable to find Author");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAllBooks()
    {
        List<Author> responseData=this.authorService.getAllAuthors();
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDTO> deleteAuthor(@PathVariable Long id)
    {
        Boolean isDeleted=this.authorService.deleteAuthor(id);
        if(isDeleted)
        {
            MessageDTO message=new MessageDTO(StatusEnum.SUCCESS,"Deleted the Book");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        else
        {
            MessageDTO message=new MessageDTO(StatusEnum.ERROR,"Unable to Delete Book");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable Long id ,@RequestBody Author author)
    {
        Author responseData=this.authorService.updateAuthor(id,author);
        if(responseData!=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
        }
        else
        {
            MessageDTO message=new MessageDTO(StatusEnum.ERROR,"Unable to Update Book");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }


    @GetMapping("/books/{id}")
    public ResponseEntity<Object> getBooks(@PathVariable Long id)
    {
        List<Book> responseData=this.authorService.getBooks(id);
        if(responseData!=null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
        else
        {
            MessageDTO message=new MessageDTO(StatusEnum.ERROR,"No Books Found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

}
