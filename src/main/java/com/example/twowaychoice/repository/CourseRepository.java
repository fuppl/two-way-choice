package com.example.twowaychoice.repository;

import com.example.twowaychoice.entity.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends BaseRepository<Course,Integer> {
}
