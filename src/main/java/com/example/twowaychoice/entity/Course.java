package com.example.twowaychoice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 科目类，老师设置分数线和所占的权重
 */
@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties("courseStudents")
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

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private List<CourseStudent> courseStudents;

    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "timestamp default current_timestamp " +
            "on update current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime updateTime;
}
