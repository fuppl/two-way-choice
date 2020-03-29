package com.example.twowaychoice.controller;

import com.example.twowaychoice.entity.Area;
import com.example.twowaychoice.service.TutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 导师Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/tutor/")
public class TutorController {
    @Autowired
    private TutorService tutorService;

    @GetMapping("getAreas/{tutorId}")
    public List<Area> getAreas( @PathVariable Integer tutorId){
        return tutorService.getAreas(tutorId);
    }
//todo 500
    @GetMapping("updatePwd/{newPwd}/{tutorId}")
    public String updatePwd(@Param("newPwd") String newPwd, @Param("tutorId") Integer tutorId){
        tutorService.updatePassword(newPwd, tutorId);
        return newPwd;
    }
}
