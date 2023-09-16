package com.tcs.library.librarymanagement.controller;

import com.tcs.library.librarymanagement.dto.MessageDTO;
import com.tcs.library.librarymanagement.enums.StatusEnum;
import com.tcs.library.librarymanagement.model.Book;
import com.tcs.library.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController
{
    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<Object> addBook(@RequestBody Book book)
    {
        Book responseData=this.bookService.addBook(book);
        if(responseData!=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
        }
        else
        {
            MessageDTO message=new MessageDTO(StatusEnum.ERROR,"Unable to add Book");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBook(@PathVariable Long id)
    {
        Book responseData=this.bookService.getBook(id);
        if(responseData!=null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
        else
        {
            MessageDTO message=new MessageDTO(StatusEnum.ERROR,"Unable to find Book");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks()
    {
        List<Book> responseData=this.bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDTO> deleteBook(@PathVariable Long id)
    {
        Boolean isDeleted=this.bookService.deleteBook(id);
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
    public ResponseEntity<Object> updateBook(@PathVariable Long id ,@RequestBody Book book)
    {
        Book responseData=this.bookService.updateBook(id,book);
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


    @PatchMapping("/updatestatus/{id}")
    public ResponseEntity<Object> updateBookStatus(@PathVariable Long id)
    {
        Book responseData=this.bookService.updateStatus(id);
        if(responseData!=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
        }
        else
        {
            MessageDTO message=new MessageDTO(StatusEnum.ERROR,"Unable to Update the status of the Book");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }
}
