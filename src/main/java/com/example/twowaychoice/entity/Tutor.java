package com.example.twowaychoice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    private String password;
    //老师可以接收的人数
    private Integer RECEIVABLE;
    //已经选择的人数
    private Integer chosen;
    //可选择的范围
    private Integer OPTIONAL;

    @OneToMany(mappedBy = "tutor",  fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) //todo 强制为饿汉
    private List<Course> courses;
    @OneToMany(mappedBy = "tutor",cascade = CascadeType.REMOVE)
    private List<AreaTutor> areaTutors;
    @OneToMany(mappedBy = "tutor")
    private List<Student> students;
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
