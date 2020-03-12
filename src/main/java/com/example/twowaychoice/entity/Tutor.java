package com.example.twowaychoice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 导师类
 */
@Data
@NoArgsConstructor
@Entity
public class Tutor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    //老师可以接收的人数
    private Integer receivable;
    //已经选择的人数
    private Integer chosen;
    //可选择的人数
    private Integer optional;
    @OneToMany(mappedBy = "tutor")
    private List<AT> ats;
    @OneToMany(mappedBy = "tutor")
    private List<Student> students;
}
