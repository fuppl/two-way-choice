package com.example.twowaychoice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 学生类
 */
@Data
@NoArgsConstructor
@Entity
public class Student implements Serializable {
    @Id
    private Integer id;
    private String name;
    @ManyToOne
    private Tutor tutor;
    @OneToMany(mappedBy = "student")
    private List<AreaStudent> areaStudents;
    @OneToMany(mappedBy = "student")
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
