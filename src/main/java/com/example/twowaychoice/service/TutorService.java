package com.example.twowaychoice.service;

import com.example.twowaychoice.entity.Course;
import com.example.twowaychoice.entity.Tutor;
import com.example.twowaychoice.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IntrospectionException;
import java.util.List;

@Service
@Transactional
public class TutorService  {

    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private AreaStudentRepository areaStudentRepository;
    @Autowired
    private AreaTutorRepository areaTutorRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseStudentRepository courseStudentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TutorRepository tutorRepository;

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param tutorId
     * @return
     */
    public String updatePassword(String oldPassword, String newPassword, Integer tutorId){
        return null;
    }

    /**
     * 修改教师信息（将教师信息全部查询到前端，教师修改后全部返回，直接覆盖原有信息）
     * @param tutor
     * @param tutorId
     * @return
     */
    public Integer updateTutor(Tutor tutor, Integer tutorId){
        return 0;
    }


    /**
     * 添加课设方向，没有权重，在AT表
     * @param detail
     * @return
     */
    public Integer addArea(String detail){
        return 0;
    }

    /**
     * 删除课设方向
     * @param areaId
     */
    public void deleteArea(Integer areaId){

    }

    /**
     * 更新课设方向
     * @param areaId
     * @param detail
     * @return
     */
    public String updateArea(Integer areaId, String detail){
        return null;
    }


    /**
     * 添加课程，注意是否可以获取到教师的id
     * @param name
     * @param LINE
     * @param WEIGHT
     * @return
     */
    public Integer addCourse(String name, Float LINE, Float WEIGHT, Integer tutorId){
        return 0;
    }

    /**
     * 修改课程
     * @param course
     * @param tutorId
     */
    public void updateCourse(Course course, Integer tutorId){

    }

    /**
     * 删除课程
     * @param courseId
     */
    public void deleteCourse(Integer courseId){

    }

    /**
     * 创建学生成绩单，待定，在CourseStudent 表
     */
    public void addStudentsScores(){

    }

    /**
     * 修改学生成绩单（重新录入，直接覆盖）
     */
    public void updateStudentsScores(){

    }


    /**
     * 启动互选
     * @param tutorId
     */
    public void enableChoosing(Integer tutorId){

    }

    /**
     * 关闭双选
     * @param tutorId
     */
    public void disableChoosing(Integer tutorId){

    }


    /**
     * 判断导师是否有可选位置
     * @param tutorId
     * @return
     */
    public Integer couldAccept(Integer tutorId){
        return 0;
    }


    /**
     * 读取导师分数线集合
     * @param tutorId
     * @return
     */
    public List<Float> listLines(Integer tutorId){
        return List.of(0F);
    }
}
