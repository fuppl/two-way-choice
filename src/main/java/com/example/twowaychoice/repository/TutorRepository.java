package com.example.twowaychoice.repository;

import com.example.twowaychoice.entity.Tutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends BaseRepository<Tutor,Integer> {

//    @Query("select t.password from Tutor t where t.id=:tutorId")
//    String findPwdById(@Param("tutorId") Integer tutorId);
//
//    @Modifying
//    @Query("update Tutor t set t.password=:newpwd where t.id=:tutorId")
//    int updatePwdById(@Param("newpwd") String newpwd, @Param("tutorId") Integer tutorId);
//
//    @Query("from Tutor t where t.id=:tutorId")
//    Optional<Tutor> findById(@Param("tutorId") Integer tutorId);
//
//    @Modifying
//    @Query("update Tutor t set t=:newTutor where t.id=:tutorId")
//    int updateById(@Param("newTutor") Tutor newTutor, @Param("tutorId") Integer tutorId);
    @Query("from Tutor t where t.tutorNumber = :tnum")
    public Tutor findByTutorNumber(@Param("tnum") Integer tnum);
}
