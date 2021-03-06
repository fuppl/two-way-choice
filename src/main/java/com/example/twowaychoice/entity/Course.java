package com.example.twowaychoice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.List;

/**
 * 科目类，老师设置分数线和所占的权重
 */
@Data
@NoArgsConstructor
@Entity
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    //分数线
    private Float LINE;
    //权重
    private Float WEIGHT;
    @ManyToOne
    private Tutor tutor;
    @OneToMany(mappedBy = "course")
    private List<SC> scs;
}
