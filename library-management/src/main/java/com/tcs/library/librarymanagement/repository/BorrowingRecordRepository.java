package com.tcs.library.librarymanagement.repository;

import com.tcs.library.librarymanagement.Projections.ProjectUser;
import com.tcs.library.librarymanagement.model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Long>
{
    @Query(value = "SELECT book FROM BorrowingRecord where user=?",nativeQuery = true)
    List<ProjectUser> findBookByUser(String user);
}
