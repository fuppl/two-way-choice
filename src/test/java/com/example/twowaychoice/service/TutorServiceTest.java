package com.example.twowaychoice.service;

import com.example.twowaychoice.entity.Course;
import com.example.twowaychoice.entity.Tutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Rollback(value = false)
public class TutorServiceTest {

    @Autowired
    private TutorService tutorService;

    @Test
    public void test_newTutor() {
        tutorService.newTutor("lalala", "asdw", 10, 60);
    }

    @Test
    public void test_unofficiallyStudent() {
//        tutorService.unofficiallyStudent(2017214239, 1);
        tutorService.unofficiallyStudent(2017214240, "lalala", 1);
    }


    @Test
    public void test_updatePassword() {

        tutorService.updatePassword("78910jqka", 1);
    }


    @Test
    public void test_updateTutor(){
        Tutor tutor = new Tutor();
        tutor.setName("lilaosi");
        tutor.setPassword("1234560");
        tutor.setChosen(0);
        tutorService.updateTutor(tutor, 2);
    }


    @Test
    public void test_addArea(){
//        tutorService.addArea("java", 1);
        tutorService.addArea("web", 1);
    }


    @Test
    public void test_deleteArea() {
        tutorService.deleteArea(6);
    }


    @Test
    public void test_updateArea(){
        tutorService.updateArea(4, "爱啥啥");
    }

    @Test
    public void test_getAreas() {
        tutorService.getAreas(1).forEach(area -> System.out.println(area.getDetail()));
    }

//    @Test
//    public void test_test() {
//        tutorService.test_(1);
//    }

    @Test
    public void test_addCourse() {
        tutorService.addCourse("lalala", 80f, 1f, 1);
    }



    @Test
    public void test_updateCourse(){
        Course course = new Course();
        course.setName("你吃了吗");
        course.setWEIGHT(1.5f);
        course.setLINE(2.0f);

        tutorService.updateCourse(course, 2,2);
    }

//    @Test
//    public void test_deleteCourse() {
//        tutorService.deleteCourse();
//    }

    @Test
    public void test_list_Courses() {
        tutorService.listCourses(1).forEach(c -> System.out.println(c.getName()));
    }

}
