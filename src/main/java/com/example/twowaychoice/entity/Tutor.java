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
 * 导师类
 */
@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"courses", "areaTutors", "students"})
public class Tutor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private Integer tutorNumber;// 导师员工号
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//序列化时忽略，反序列化正常
    private String password;
    //老师可以接收的人数
    private Integer RECEIVABLE;
    //已经选择的人数
    private Integer chosen;
    //可选择的范围
    private Integer OPTIONAL;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//序列化时忽略，反序列化正常
    private Role role;

    @OneToMany(mappedBy = "tutor",  fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) //todo 强制为饿汉
//    @OneToMany(mappedBy = "tutor", cascade = CascadeType.REMOVE) //todo springboot 将访问提升到view级，不加这句话也能直接访问
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
