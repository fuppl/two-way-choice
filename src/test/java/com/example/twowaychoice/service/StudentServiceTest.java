package com.example.twowaychoice.service;

import com.example.twowaychoice.entity.Area;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootTest
@Slf4j
@Rollback(value = false)
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void test_chooseAreas() {
        List<Integer> integers = List.of(1, 2,3,4);
        List<Area> areas = studentService.chooseAreas(1, integers);
        areas.forEach(area -> System.out.println(area.getDetail()));
    }

//    @Test
//    public void test_updateAreas() {
//        List<Integer> integers = List.of(3,4);
//        studentService.updateAreas(2017214239,integers);
//    }


    @Test
    public void test_chooseTutor() {
        studentService.chooseTutor(8, 2);
    }
}
