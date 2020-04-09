package com.example.twowaychoice.controller;

import com.example.twowaychoice.component.RequestComponent;
import com.example.twowaychoice.entity.Area;
import com.example.twowaychoice.entity.Student;
import com.example.twowaychoice.entity.Tutor;
import com.example.twowaychoice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student/")
public class StudentController {
    @Autowired
    private RequestComponent requestComponent;
    @Autowired
    private StudentService studentService;


    @GetMapping("index")
    public Map getIndex(){
        Student student = studentService.getStudentById(requestComponent.getUid());
        return Map.of(
                "student", student,
                "areas",  studentService.listAllAreas()
        );
    }

    @PostMapping("areaStudent")
    public Map chooseAreas(@RequestBody Map<String , List<Integer>> list){
        List<Integer> integers = list.get("areas");
        List<Area> areas = studentService.chooseAreas(requestComponent.getUid(), integers);
        return Map.of("areas",areas);
    }

    @PatchMapping("student")
    public Map chooseStudent(@RequestBody Map<String, Integer> map) {
        Integer tutorId = map.get("tutorId");
        Tutor tutor = studentService.chooseTutor(requestComponent.getUid(), tutorId);
        return Map.of("tutor", tutor);
    }

}
