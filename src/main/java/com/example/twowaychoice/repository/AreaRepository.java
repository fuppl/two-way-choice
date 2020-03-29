package com.example.twowaychoice.repository;

import com.example.twowaychoice.entity.Area;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends BaseRepository<Area,Integer> {
    @Query("from Area a where a.id in (select at.id from AreaTutor at where at.tutor.id=:tutorId)")
    List<Area> getAreasByTutorId(@Param("tutorId") Integer tutorId);
}
