package com.tcs.library.librarymanagement.service;

import com.tcs.library.librarymanagement.Projections.ProjectUser;
import com.tcs.library.librarymanagement.model.Book;
import com.tcs.library.librarymanagement.model.BorrowingRecord;
import com.tcs.library.librarymanagement.repository.AuthorRepository;
import com.tcs.library.librarymanagement.repository.BookRepository;
import com.tcs.library.librarymanagement.repository.BorrowingRecordRepository;
import org.springframework.kafka.support.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class BorrowingRecordService
{

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    KafkaTemplate<String,Object> template;

    public BorrowingRecord addBorrowedRecord(BorrowingRecord borrowedRecord)
    {
        try
        {
            this.authorRepository.save(borrowedRecord.getBook().getAuthor());
            this.bookRepository.save(borrowedRecord.getBook());
            borrowedRecord.setBorrowingDate(LocalDate.now());
            return this.borrowingRecordRepository.save(borrowedRecord);
        }
        catch (Exception e)
        {
            System.out.println("Unable to add borrowed record : "+e.getMessage());
            return null;
        }
    }

    public BorrowingRecord updatedReturnedRecord(Long id)
    {
        try
        {
            if(!this.borrowingRecordRepository.existsById(id))
            {
                return null;
            }
            BorrowingRecord borrowedRecord=this.borrowingRecordRepository.findById(id).get();
            borrowedRecord.setReturnDate(LocalDate.now());
            if(borrowedRecord.isCanDelete())
            {
               CompletableFuture<SendResult<String,Object>> response= template.send("borrowedRecord","Books has been deleted");
                response.whenComplete((result,ex)->{
                    if (ex == null) {
                        System.out.println("Sent message with offset=[" + result.getRecordMetadata().offset() + "]");
                    } else {
                        System.out.println("Unable to send message due to : " + ex.getMessage());
                    }
                });

            }

            return this.borrowingRecordRepository.save(borrowedRecord);
        }
        catch (Exception e)
        {
            System.out.println("Unable to updated returned record : "+e.getMessage());
            return null;
        }
    }

    public List<ProjectUser> getBorrowedUsers(String user)
    {
        try
        {
            return this.borrowingRecordRepository.findBookByUser(user);
        }
        catch (Exception e)
        {
            System.out.println("Unable to updated returned record : "+e.getMessage());
            return null;
        }
    }

    public boolean isBorrowedBook(Long id)
    {
        try
        {
            List<BorrowingRecord> allBorrowed=this.borrowingRecordRepository.findAll();
            BorrowingRecord borrowingRecord= allBorrowed.stream().filter(borrRecord -> Objects.equals(borrRecord.getBook().getId(), id)).toList().get(0);
            if(borrowingRecord!=null)
            {
                borrowingRecord.setCanDelete(true);
                this.borrowingRecordRepository.save(borrowingRecord);
            }
            return borrowingRecord!=null;
        }
        catch (Exception e)
        {
            System.out.println("Unable find book in borrowed users : "+e.getMessage());
            return true;
        }
    }


}
