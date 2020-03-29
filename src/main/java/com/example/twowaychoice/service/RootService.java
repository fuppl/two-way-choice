package com.example.twowaychoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 超级管理员Service
 */
@Service
@Transactional
public class RootService extends TutorService{
    @Autowired
    private TutorService tutorService;

    /**
     * 重置导师密码
     * @param tutorId
     * @return
     */
    public String resetPassword(Integer tutorId) {
        String reset = "123456";
        tutorService.updatePassword(reset, tutorId);
        return reset;
    }

}
