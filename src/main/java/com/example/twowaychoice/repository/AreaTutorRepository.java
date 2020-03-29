package com.example.twowaychoice.repository;

import com.example.twowaychoice.entity.Area;
import com.example.twowaychoice.entity.AreaTutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaTutorRepository extends BaseRepository<AreaTutor,Integer> {
//    @Query("select at.id from AreaTutor at where at.tutor.id=:tutorId")
//    public List<Integer> listATIdsByTutorId(@Param("tutorId") Integer tutorId);
}
