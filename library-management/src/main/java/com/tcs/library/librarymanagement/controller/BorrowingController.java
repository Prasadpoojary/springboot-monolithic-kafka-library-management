package com.tcs.library.librarymanagement.controller;

import com.tcs.library.librarymanagement.Projections.ProjectUser;
import com.tcs.library.librarymanagement.dto.MessageDTO;
import com.tcs.library.librarymanagement.enums.StatusEnum;
import com.tcs.library.librarymanagement.model.Author;
import com.tcs.library.librarymanagement.model.BorrowingRecord;
import com.tcs.library.librarymanagement.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowedrecord/")
public class BorrowingController
{
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/add")
    public ResponseEntity<Object> addBorrowedRecord(@RequestBody BorrowingRecord borrowingRecord)
    {
        BorrowingRecord responseData=this.borrowingRecordService.addBorrowedRecord(borrowingRecord);
        if(responseData!=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
        }
        else
        {
            MessageDTO message=new MessageDTO(StatusEnum.ERROR,"Unable to add Borrowed Record");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateBorrowedRecord(@PathVariable Long id)
    {
        BorrowingRecord responseData=this.borrowingRecordService.updatedReturnedRecord(id);
        if(responseData!=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
        }
        else
        {
            MessageDTO message=new MessageDTO(StatusEnum.ERROR,"Unable to updated Borrowed Record return date");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<ProjectUser>> getBorrowedUsers(@PathVariable String username)
    {
        List<ProjectUser> responseData=this.borrowingRecordService.getBorrowedUsers(username);
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }
}
