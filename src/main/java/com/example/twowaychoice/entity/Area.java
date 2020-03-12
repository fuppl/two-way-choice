package com.example.twowaychoice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
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
    private List<AT> ats;
    @OneToMany(mappedBy = "area")
    private List<SA> sas;
}
