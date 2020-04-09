package com.example.twowaychoice.interceptor;

import com.example.twowaychoice.component.RequestComponent;
import com.example.twowaychoice.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TutorInterceptor implements HandlerInterceptor {
    @Autowired
    private RequestComponent requestComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (requestComponent.getRole() != Role.TUTOR){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "douniwan ");
        }

        return true;
    }
}
