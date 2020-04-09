package com.example.twowaychoice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties({"areaStudents", "courseStudents"})
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private Integer studentNumber; //学号
    private String name;
    @ManyToOne
    private Tutor tutor;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//序列化时忽略，反序列化正常
    private Role role;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<AreaStudent> areaStudents;
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
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
