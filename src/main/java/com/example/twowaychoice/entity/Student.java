package com.example.twowaychoice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 学生类
 */
@Data
@NoArgsConstructor
@Entity
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    private Tutor tutor;
    @OneToMany(mappedBy = "student")
    private List<SA> sas;
    @OneToMany(mappedBy = "student")
    private List<SC> scs;
}
