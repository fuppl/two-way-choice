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
    private AreaTutorRepository areaTutorRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AreaStudentRepository areaStudentRepository;
    @Autowired
    private CourseStudentRepository courseStudentRepository;
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
        tutor.setChosen(0);
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

        AreaTutor areaTutor = new AreaTutor();
        areaTutor.setTutor(tutor);
        areaTutor.setArea(area);

        areaTutorRepository.save(areaTutor);

        AreaStudent areaStudent = new AreaStudent();
        areaStudent.setStudent(student);
        areaStudent.setArea(area);
        areaStudentRepository.save(areaStudent);

        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setStudent(student);
        courseStudent.setCourse(course);
        courseStudent.setScore(100F);
        courseStudentRepository.save(courseStudent);
    }
}
