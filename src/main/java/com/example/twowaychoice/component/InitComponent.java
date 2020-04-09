package com.example.twowaychoice.component;

import com.example.twowaychoice.entity.Tutor;
import com.example.twowaychoice.service.TutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 初始化组件。用于添加默认管理员
 */
@Component
@Slf4j
public class InitComponent implements InitializingBean {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private TutorService tutorService;

    @Override
    public void afterPropertiesSet() throws Exception {
        final int num = 1001;
        Tutor tutor = tutorService.getTutorByNumber(num);
        if (tutor == null){
            Tutor t = new Tutor();
            t.setTutorNumber(num);
            t.setName("bo");
            t.setPassword(String.valueOf(num));
            t.setRECEIVABLE(10);
            t.setOPTIONAL(50);
//            tutorService.newTutor(num,"bo", encoder.encode(String.valueOf(num)), 10, 50);
            tutorService.addTutor(t);
        }
    }
}
