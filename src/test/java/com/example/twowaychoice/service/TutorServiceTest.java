package com.example.twowaychoice.service;

import com.example.twowaychoice.entity.Course;
import com.example.twowaychoice.entity.Tutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Slf4j
@Rollback(value = false)
public class TutorServiceTest {

    @Autowired
    private TutorService tutorService;

    @Autowired
    private PasswordEncoder encoder;

//    @Test
//    public void test_newtutor2() {
////        int num = 1001;
////        Tutor tutor = tutorService.getTutor(num);
////        if (tutor == null) {
////            Tutor t =new Tutor();
////            t.setName("BO");
//////            t.setId(num);
////            t.setOPTIONAL(50);
////            t.setChosen(0);
////            t.setRECEIVABLE(10);
////            t.setPassword(encoder.encode(String.valueOf(num)));
////
////            tutorService.newTutor(tutor);
//        int num = 1001;
//        Tutor t = tutorService.getTutorByNumber(num);
//        if (t == null){
//            tutorService.newTutor(num,"bo", encoder.encode(String.valueOf(num)), 10, 50);
//        }
//    }
//
//
//    @Test
//    public void test_newTutor() {
//        tutorService.newTutor(1003,"lalala", "asdw", 10, 60);
//    }

    @Test
    public void test_unofficiallyStudent() {
//        tutorService.unofficiallyStudent(2017214239, 1);
        tutorService.unofficiallyStudent(2017214239, "李景阳", 1);
//        System.out.println(tutorService.getStudent(2017214239).getName());
    }


    @Test
    public void test_updatePassword() {

        tutorService.updatePassword("1002", 2);
    }


    @Test
    public void test_updateTutor(){
        Tutor tutor = new Tutor();
        tutor.setTutorNumber(123456);
        tutor.setName("123456");
//        tutor.setPassword("1234560");
//        tutor.setChosen(0);
//        tutor.setRole(Role.TUTOR);
        tutor.setOPTIONAL(100);
        tutor.setRECEIVABLE(100);
        tutorService.updateTutor(tutor, 2);
    }

    @Test
    public void test_getTutor() {
        tutorService.getTutorByNumber(1002).getCourses().forEach(course -> System.out.println(course.getName()));
    }

    @Test
    public void test_addArea(){
//        tutorService.addArea("java", 1);
        tutorService.addArea("web", 1);
    }


    @Test
    public void test_deleteArea() {
        tutorService.deleteArea(2);
    }


//    @Test
//    public void test_updateArea(){
//        tutorService.updateArea(4, "爱啥啥");
//    }

    @Test
    public void test_getAreas() {
        tutorService.listAreas(2).forEach(area -> System.out.println(area.getDetail()));
    }

//    @Test
//    public void test_test() {
//        tutorService.test_(1);
//    }

//    @Test
//    public void test_addCourse() {
//        tutorService.addCourse("lalala", 80f, 1f, 1);
//    }



    @Test
    public void test_updateCourse(){
        Course course = new Course();
        course.setName("你吃了吗");
        course.setWEIGHT(1.5f);
        course.setLINE(2.0f);

        tutorService.updateCourse(course, 2,1);
    }

//    @Test
//    public void test_deleteCourse() {
//        tutorService.deleteCourse();
//    }

    @Test
    public void test_list_Courses() {
        tutorService.listCourses(1).forEach(c -> System.out.println(c.getName()));
    }

//    @Test
//    public void test_addStudent(){
//        tutorService.addStudent(2017214242, "wuwuwu");
//    }

//    @Test
//    public void test_test(){
////        tutorService.unofficiallyStudent(2017214242, "wuwuwu", 11);
//        tutorService.addCourse("爱啥啥", 0F, 0F, 11);
//    }

    @Test
    public void test_test2(){
        Tutor tutor = tutorService.getTutorByNumber(11);
        tutor.getCourses().forEach(course -> System.out.println(course.getName()));
    }

}
