package com.example.twowaychoice.repository;

import com.example.twowaychoice.entity.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends BaseRepository<Course,Integer> {
//    @Modifying
//    @Query("update Course c set c.name=:course where c.id=:courseId")
//    int updateById(@Param("course") Course course, @Param("courseId") Integer courseId);


}
