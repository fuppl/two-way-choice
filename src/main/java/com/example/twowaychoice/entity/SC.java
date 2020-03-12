package com.example.twowaychoice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 学生选课中间类，用来记录学生每门课程的成绩
 */
@Data
@NoArgsConstructor
@Entity
public class SC implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float score;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
}
