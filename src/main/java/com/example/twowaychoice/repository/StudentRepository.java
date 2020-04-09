package com.example.twowaychoice.repository;

import com.example.twowaychoice.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends BaseRepository<Student,Integer> {
    @Query("from Student s where s.studentNumber = :studentNumber")
    public Student findByStudentNumber(@Param("studentNumber") Integer studentNumber);
}
