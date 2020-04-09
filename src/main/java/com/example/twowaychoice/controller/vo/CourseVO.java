package com.example.twowaychoice.controller.vo;

import com.example.twowaychoice.entity.Course;
import com.example.twowaychoice.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class CourseVO {
    private Course course;
    private List<Student> students;
}
