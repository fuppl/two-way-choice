package com.example.twowaychoice.controller;

import com.example.twowaychoice.component.EncryptComponent;
import com.example.twowaychoice.component.MyToken;
import com.example.twowaychoice.entity.Role;
import com.example.twowaychoice.entity.Student;
import com.example.twowaychoice.entity.Tutor;
import com.example.twowaychoice.service.StudentService;
import com.example.twowaychoice.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class LoginController {
    @Value("${my.teacher}")
    private String roleTutor;
    @Value("${my.student}")
    private String roleStudent;
    @Autowired
    private EncryptComponent encrypt;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private StudentService studentService;

    @PostMapping("tutorLogin")
    public Map login(@RequestBody Tutor login, HttpServletResponse response) {
        Tutor tutor = Optional.ofNullable(tutorService.getTutorByNumber(login.getTutorNumber()))
                .filter(t -> encoder.matches(login.getPassword(), t.getPassword()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误"));

        MyToken token = new MyToken(tutor.getId(), tutor.getRole());
        String auth = encrypt.encryptToken(token);
        response.setHeader(MyToken.AUTHORIZATION, auth);
        String roleCode = tutor.getRole() == Role.TUTOR ? roleTutor : roleStudent;
        return Map.of("role", roleCode);
    }

    @PostMapping("studentLogin")
    public Map login(@RequestBody Student login, HttpServletResponse response){
        Student student = Optional.ofNullable(studentService.getStudentByNumber(login.getStudentNumber()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "您输入的学号不存在"));
        MyToken token = new MyToken(student.getId(), student.getRole());
        String auth = encrypt.encryptToken(token);
        response.setHeader(MyToken.AUTHORIZATION, auth);
        String roleCode = student.getRole() == Role.TUTOR ? roleTutor : roleStudent;
        return Map.of("role", roleCode);
    }
}
