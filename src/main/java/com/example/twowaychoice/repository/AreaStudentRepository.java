package com.example.twowaychoice.repository;

import com.example.twowaychoice.entity.AreaStudent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaStudentRepository extends BaseRepository<AreaStudent,Integer> {
    @Modifying
    @Query("delete from AreaStudent a where a.student.id=:studentId")
    void deleteByStudentId(@Param("studentId") Integer studentId);
}
