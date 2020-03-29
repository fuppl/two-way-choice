package com.example.twowaychoice.service;

import com.example.twowaychoice.entity.*;
import com.example.twowaychoice.repository.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.security.PublicKey;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class TutorService  {

//    @Autowired
//    protected EntityManager manager;
    @Autowired
    protected AreaRepository areaRepository;
    @Autowired
    protected AreaStudentRepository areaStudentRepository;
    @Autowired
    protected AreaTutorRepository areaTutorRepository;
    @Autowired
    protected CourseRepository courseRepository;
    @Autowired
    protected CourseStudentRepository courseStudentRepository;
    @Autowired
    protected StudentRepository studentRepository;
    @Autowired
    protected TutorRepository tutorRepository;

//导师相关
    /**
     * 新建教师
     * @param name
     * @param password
     * @param RECEIVABLE 导师可接收的人数
     * @param OPTIONAL 学生可参与双选的范围
     * @return
     */
    public Tutor newTutor(String name, String password, Integer RECEIVABLE, Integer OPTIONAL){
        Tutor tutor = new Tutor();
        tutor.setName(name);
        tutor.setPassword(password);
        tutor.setRECEIVABLE(RECEIVABLE);
        tutor.setChosen(0);
        tutor.setOPTIONAL(OPTIONAL);
        tutorRepository.save(tutor);
//        manager.persist(tutor);

        return tutor;
    }


    /**
     * 内定学生，当学生已经持久化
     * @param studentId
     * @param tutorId
     * @return
     */
    public Student unofficiallyStudent(Integer studentId, Integer tutorId){
//        Student student = manager.find(Student.class, studentId);
//        Tutor tutor = manager.find(Tutor.class, tutorId);
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null){
            log.debug("您查找的学生不存在，请重试");
            return null;
        }
        Tutor tutor = tutorRepository.findById(tutorId).orElse(null);

        student.setTutor(tutor);
        tutor.setChosen(tutor.getChosen() + 1);

        return student;
    }

    /**
     * 内定学生，用于当该学生还未持久化时新建学生
     * @param studentId
     * @param studentName
     * @param tutorId
     * @return
     */
    public Student unofficiallyStudent(Integer studentId, String studentName, Integer tutorId){
//        Tutor tutor = manager.find(Tutor.class, tutorId);
        Tutor tutor = tutorRepository.findById(tutorId).orElse(null);

//        Student student = new Student();
//        student.setId(studentId);
//        student.setName(studentName);
//        student.setTutor(tutor);
        Student student = this.addStudent(studentId, studentName);
        student.setTutor(tutor);
        studentRepository.save(student);
        tutor.setChosen(tutor.getChosen() + 1);

        return student;
    }


    /**
     * 修改密码
     * @param newPassword
     * @param tutorId
     * @return
     */
    public String updatePassword(String newPassword, Integer tutorId){
//        Tutor oldTutor = manager.find(Tutor.class, tutorId);
        Tutor oldTutor = tutorRepository.findById(tutorId).orElse(null);
        oldTutor.setPassword(newPassword);

        return newPassword;
    }


    /**
     * 修改教师信息（将教师信息全部查询到前端，教师修改后全部返回，直接覆盖原有信息）
     * @param tutor
     * @param tutorId
     * @return
     */
    public Tutor updateTutor(Tutor tutor, Integer tutorId){
//        Tutor oldTutor = manager.find(Tutor.class, tutorId);
        Tutor oldTutor = tutorRepository.findById(tutorId).orElse(null);
        System.out.println(oldTutor.getId()+"/"+oldTutor.getName());
//        oldTutor = tutor;//todo 为什么不行捏
        oldTutor.setName(tutor.getName());
//        oldTutor.setPassword(tutor.getPassword());
        oldTutor.setRECEIVABLE(tutor.getRECEIVABLE());
        oldTutor.setOPTIONAL(tutor.getOPTIONAL());

        return oldTutor;
    }


//课设方向相关

    /**
     * 添加课设方向
     * @param detail
     * @return
     */
    public Area addArea(String detail, Integer tutorId){
        Area area = new Area();
        area.setDetail(detail);
        areaRepository.save(area);

        AreaTutor areaTutor = new AreaTutor();
        areaTutor.setArea(area);
        areaTutor.setTutor(tutorRepository.findById(tutorId).orElse(null));
        //todo 加入try catch
        try {
            areaTutorRepository.save(areaTutor);
        }catch (Exception e){
            log.debug("失败请重试");
            return null;
        }
        return area;
    }

    /**
     * 删除课设方向
     * @param areaId
     */
    public void deleteArea(Integer areaId){
        areaRepository.deleteById(areaId);
    }

    /**
     * 更新课设方向
     * @param areaId
     * @param detail
     * @return
     */
    public Area updateArea(Integer areaId, String detail){
        Area oldArea = areaRepository.findById(areaId).orElse(null);
        oldArea.setDetail(detail);

        return oldArea;
    }

    /**
     * 查询导师下的方向
     * 有问题，无结果
     * 问题已解决，不知道咋解决的
     * @param tutorId
     * @return
     */
    public List<Area> getAreas(Integer tutorId) {
//        return areaTutorRepository.getAllByTutorId(tutorId);
        return areaRepository.getAreasByTutorId(tutorId);
    }


//    public void test_(Integer tutorId){
//        areaTutorRepository.listATIdsByTutorId(tutorId).forEach(integer -> System.out.println(integer));
//    }

//课程相关

    /**
     * 添加课程
     * @param name
     * @param LINE 分数线
     * @param WEIGHT 权重
     * @return
     */
    public Course addCourse(String name, Float LINE, Float WEIGHT, Integer tutorId){
        Course course = new Course();
        course.setTutor(tutorRepository.findById(tutorId).orElse(null));
        course.setName(name);
        course.setWEIGHT(WEIGHT);
        course.setLINE(LINE);
        courseRepository.save(course);

        return course;
    }

    /**
     * 修改课程
     * @param course
     * @param courseId
     */
    public Course updateCourse(Course course, Integer courseId, Integer tutorId){
        int flag = 0;
//        Course oldCourse = manager.find(Course.class, courseId);
        Course oldCourse = courseRepository.findById(courseId).orElse(null);
//        List<Course> courses = manager.find(Tutor.class, tutorId).getCourses();
        List<Course> courses = tutorRepository.findById(tutorId).orElse(null).getCourses();
        for (Course c : courses) {
            if (c.getId().equals(oldCourse.getId())) {
                flag = 1;
                break;
            }
        }
        //todo 这个判断应该也是放在控制层，先放在这
        if (flag == 1) {
            oldCourse.setName(course.getName());
            oldCourse.setLINE(course.getLINE());
            oldCourse.setWEIGHT(course.getWEIGHT());

            return oldCourse;
        }else {
            log.debug("这位教师，这不是您的课，请重新选择");
            return null;
        }
    }

    /**
     * 删除课程
     * @param courseId
     */
    public void deleteCourse(Integer courseId){
        courseRepository.deleteById(courseId);
    }

//学生成绩相关

    /**todo 不知道传入类型
     *  思路：读取课程名，判断有没有，新建课程，
     *  读取学生，判断有没有，新建学生
     *  CouseStudent表插入数据
     * 创建学生成绩单，待定，在CourseStudent 表
     */
    public List<CourseStudent> addStudentsScores(){
        return List.of();

    }

    /**todo 同上
     * 修改学生成绩单（重新录入，直接覆盖）
     */
    public List<CourseStudent> updateStudentsScores(){
        return List.of();
    }


//学生相关

    /**
     * 添加学生
     * @param studentId
     * @param name
     * @return
     */
    public Student addStudent(Integer studentId, String name){
        Student student = new Student();
        student.setId(studentId);
        student.setName(name);
        studentRepository.save(student);

        return student;
    }

    public Student updateStudent(Student student, Integer studentId) {
        Student oldStudent = studentRepository.findById(studentId).orElse(null);
        oldStudent.setId(student.getId());
        oldStudent.setName(student.getName());

        return oldStudent;
    }

    public void deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
    }


//其他功能

    /**todo 启动互选，思路：
     *  将学生全体拉入缓存（怎么拉入缓存--redis）
     *  将学生按成绩排名那个，在教师范围外的直接删除
     *  要在这层写吗
     *
     * 启动互选
     * @param tutorId
     */
    public void enableChoosing(Integer tutorId){

    }

    /**todo 关闭双选
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
    public Boolean couldAccept(Integer tutorId){
//        Tutor tutor = manager.find(Tutor.class, tutorId);
        Tutor tutor = tutorRepository.findById(tutorId).orElse(null);
        return tutor.getRECEIVABLE() - tutor.getChosen() > 0;
    }


    /**
     * 读取导师课程集合 todo 主要是为了返回分数线集合，再想想，这里必须再Tutor类上强制为饿汉加载，不符合想法
     * @param tutorId
     * @return
     */
    public List<Course> listCourses(Integer tutorId){
//        return manager.find(Tutor.class, tutorId).getCourses();
        return tutorRepository.findById(tutorId).orElse(null).getCourses();
    }
}
