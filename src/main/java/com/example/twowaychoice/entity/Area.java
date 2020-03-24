package com.example.twowaychoice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 课设方向类
 */
@Entity
@Data
@NoArgsConstructor
public class Area implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String detail;
    @OneToMany(mappedBy = "area")
    private List<AreaTutor> areaTutors;
    @OneToMany(mappedBy = "area")
    private List<AreaStudent> areaStudents;
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
