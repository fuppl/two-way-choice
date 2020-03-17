package com.example.twowaychoice.entity;

import com.example.twowaychoice.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Rollback(value = false)
@Transactional
public class InitTest  {
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private ATRepository atRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SARepository saRepository;
    @Autowired
    private SCRepository scRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TutorRepository tutorRepository;

    @Test
    public void test_init(){
        Tutor tutor = new Tutor();
        tutor.setName("lalala");
        tutor.setPassword("123456");
        tutor.setOPTIONAL(60);
        tutor.setRECEIVABLE(10);
        tutorRepository.save(tutor);

        Student student = new Student();
        student.setId(2017214239);
        studentRepository.save(student);

        Course course = new Course();
        course.setName("web框架");
        course.setLINE(60F);
        course.setWEIGHT(1F);
        courseRepository.save(course);

        Area area = new Area();
        area.setDetail("后端");
        areaRepository.save(area);

        AT at = new AT();
        at.setTutor(tutor);
        at.setArea(area);
        at.setWEIGHT(1F);
        atRepository.save(at);

        SA sa = new SA();
        sa.setStudent(student);
        sa.setArea(area);
        saRepository.save(sa);

        SC sc = new SC();
//        sc.setId(1);
        sc.setStudent(student);
        sc.setCourse(course);
        sc.setScore(100F);
        scRepository.save(sc);
    }
}
