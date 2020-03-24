package com.example.twowaychoice.service;


import com.example.twowaychoice.entity.AreaStudent;
import com.example.twowaychoice.repository.AreaStudentRepository;
import com.example.twowaychoice.repository.StudentRepository;
import com.example.twowaychoice.repository.TutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {
    @Autowired
    private AreaStudentRepository areaStudentRepository;
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private StudentRepository studentRepository;

    /**
     * 学生选择课设方向，可多选
     * @param studentId
     * @param areaIds
     * @return
     */
    public List<Integer> chooseAreas(Integer studentId, List<Integer> areaIds){
        return List.of(0);
    }

    /**
     * 学生更新课设方向，直接覆盖
     * @param studentId
     * @param areaIds
     * @return
     */
    public List<Integer> updateAreas(Integer studentId, List<Integer> areaIds){
        return List.of(0);
    }

    /**
     * 学生选择导师，没有推选
     * @param studentId
     * @param tutorId
     * @return
     */
    public Integer chooseTutor(Integer studentId, Integer tutorId){
        return 0;
    }

}
