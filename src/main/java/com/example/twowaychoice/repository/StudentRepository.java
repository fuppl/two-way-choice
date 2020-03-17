package com.example.twowaychoice.repository;

import com.example.twowaychoice.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends BaseRepository<Student,Integer> {
}
