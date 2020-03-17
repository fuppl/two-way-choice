package com.example.twowaychoice.repository;

import com.example.twowaychoice.entity.Tutor;
import com.example.twowaychoice.repository.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends BaseRepository<Tutor,Integer> {
}
