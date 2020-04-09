package com.example.twowaychoice;

//import com.example.twowaychoice.interceptor.RootInterceptor;
import com.example.twowaychoice.interceptor.LoginInterceptor;
import com.example.twowaychoice.interceptor.StudentInterceptor;
import com.example.twowaychoice.interceptor.TutorInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//todo 注意这个注释
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//    @Autowired
//    private RootInterceptor rootInterceptor;
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private TutorInterceptor tutorInterceptor;
    @Autowired
    private StudentInterceptor studentInterceptor;

    /**
     * 添加拦截器 s ,todo 有拦截规则，也有过滤规则，应将登录请求放行
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(rootInterceptor)
//                .addPathPatterns("/api/example06/admin/**")
//                .excludePathPatterns("/api/example06/login");
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/tutorLogin")
                .excludePathPatterns("/api/studentLogin");

        registry.addInterceptor(tutorInterceptor)
                .addPathPatterns("/api/tutor/**");

        registry.addInterceptor(studentInterceptor)
                .addPathPatterns("/api/student/**");
    }
}
