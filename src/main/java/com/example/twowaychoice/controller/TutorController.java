package com.example.twowaychoice.controller;

import com.example.twowaychoice.component.RequestComponent;
import com.example.twowaychoice.entity.Area;
import com.example.twowaychoice.entity.Course;
import com.example.twowaychoice.entity.Student;
import com.example.twowaychoice.entity.Tutor;
import com.example.twowaychoice.service.TutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 导师Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/tutor/")
public class TutorController {
    @Autowired
    private TutorService tutorService;
//    @Autowired
//    private EncryptComponent encrypt;
    @Autowired
    private RequestComponent requestComponent;
//tutor
    @GetMapping("index")
    public Map getTutor(){
        Tutor tutor = tutorService.getTutorById(requestComponent.getUid());
        return Map.of(
                "tutor", tutor,
                "courses", tutor.getCourses(),
                "students", tutor.getStudents()
        );
    }

    @PostMapping("tutor")
    public Map addTutor(@RequestBody Tutor t){
        Tutor tutor = tutorService.addTutor(t);
        return Map.of("newTutor", tutor);
    }

    /**
     * 内定学生
     * @param s
     * @return
     */
    @PostMapping("unofficiallyStudent")
    public Map unofficiallyStudent(@RequestBody Student s){
        Student student = tutorService.unofficiallyStudent(s.getStudentNumber(), s.getName(), requestComponent.getUid());
        return Map.of("student",student);
    }

    @PatchMapping("pwd")
    public Map updatePwd(@RequestBody Map<String, String> user){
        String newPwd = user.get("newPwd");
        String pwd = tutorService.updatePassword(newPwd, requestComponent.getUid());
        return Map.of("newPwd", pwd);
    }

    @PatchMapping("settings")
    public Map updateTutor(@RequestBody Tutor t) {
        Tutor tutor = tutorService.updateTutor(t, requestComponent.getUid());
        return Map.of("newTutor",tutor);
    }

// area

    @PostMapping("area")
    public Map addArea(@RequestBody Map<String, String> a) {
        String detail = a.get("detail");
        Area area = tutorService.addArea(detail, requestComponent.getUid());
        return Map.of("area", area);
    }

    @GetMapping("areas")
    public Map listAreas(){
        List<Area> areas = tutorService.listAreas(requestComponent.getUid());
        return Map.of("areas", areas);
    }

    @GetMapping("areas/{areaId}")
    public Map getArea(@PathVariable Integer areaId){
        Area area = tutorService.getArea(areaId);
        return Map.of("area",area);
    }

//course

    @PostMapping("course")
    public Map addCourse(@RequestBody Course c){
        Course course = tutorService.addCourse(c, requestComponent.getUid());
        return Map.of("course", course);
    }

    @PatchMapping("courses/{courseId}")
    public Map updateCourse(@RequestBody Course c, @PathVariable Integer courseId){
        Course course = tutorService.updateCourse(c, courseId, requestComponent.getUid());
        return Map.of("course",course);
    }

    @GetMapping("courses")
    public Map listCourses(){
        List<Course> courses = tutorService.listCourses(requestComponent.getUid());
        return Map.of("courses",courses);
    }

    @GetMapping("courses/{courseId}")
    public Map getCourse(@PathVariable Integer courseId){
        Course course = tutorService.getCourseById(courseId);
        return Map.of("course",course);
    }

//student

    @PostMapping("student")
    public Map addStudent(@RequestBody Student s){
        Student student = tutorService.addStudent(s);
        return Map.of("student",student);
    }

    @PatchMapping("students/{studentId}")
    public Map updateStudent(@RequestBody Student student, @PathVariable Integer studentId) {
        Student newStudent = tutorService.updateStudent(student, studentId);
        return Map.of("newStudent", newStudent);
    }

    @GetMapping("students/{studentId}")
    public Map getStudent(@PathVariable Integer studentId){
        Student student = tutorService.getStudentById(studentId);
        return Map.of("student",student);
    }

}
