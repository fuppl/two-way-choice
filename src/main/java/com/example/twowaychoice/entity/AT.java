package com.example.twowaychoice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 导师与课设方向的中间类，由老师来设计每个课设方向所占的权重
 */
@Entity
@NoArgsConstructor
@Data
public class AT implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //权重
    private Float WEIGHT;
    @ManyToOne
    private Area area;
    @ManyToOne
    private Tutor tutor;
}
