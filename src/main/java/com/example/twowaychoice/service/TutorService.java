package com.example.twowaychoice.service;

import com.example.twowaychoice.entity.*;
import com.example.twowaychoice.repository.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * todo get*ByNumber均可以返回 null ，方便使用时判断查重，否则查重还要单写
 */
@Slf4j
@Service
@Transactional
public class TutorService  {

//    @Autowired
//    protected EntityManager manager;
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
    @Autowired
    private PasswordEncoder encoder;

//导师相关

//    /**
//     * 新建教师
//     * @param name
//     * @param password
//     * @param RECEIVABLE 导师可接收的人数
//     * @param OPTIONAL 学生可参与双选的范围
//     * @return
//     */
//    public Tutor newTutor(Integer tutorNumber, String name, String password, Integer RECEIVABLE, Integer OPTIONAL){
//        //todo 判断数据库是否已有
//        Tutor t = getTutorByNumber(tutorNumber);
//        if (t == null){
//            Tutor tutor = new Tutor();
//            tutor.setTutorNumber(tutorNumber);
//            tutor.setName(name);
//            tutor.setPassword(password);
//            tutor.setRECEIVABLE(RECEIVABLE);
//            tutor.setChosen(0);
//            tutor.setOPTIONAL(OPTIONAL);
//            tutor.setRole(Role.TUTOR);
//            newTutor(tutor);
//
//            return tutor;
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "您输入的学号已存在");
//        }
//    }

    public Tutor addTutor(Tutor tutor) {
        Tutor t = new Tutor();
        t.setTutorNumber(tutor.getTutorNumber());
        t.setName(tutor.getName());
        t.setPassword(encoder.encode(tutor.getPassword()));
        t.setRECEIVABLE(tutor.getRECEIVABLE());
        t.setChosen(0);
        t.setOPTIONAL(tutor.getOPTIONAL());
        t.setRole(Role.TUTOR);

        tutorRepository.save(t);
        return t;
    }

    /**
     * 内定学生，导师输入学生id和学生姓名
     * @param studentNumber
     * @param studentName
     * @param tutorId
     * @return
     */
    public Student unofficiallyStudent(Integer studentNumber, String studentName, Integer tutorId) {
        if (couldAccept(tutorId)){
        Tutor tutor = getTutorById(tutorId);

        Student student = Optional.ofNullable(getStudentByNumber(studentNumber))
                .orElseGet(() -> {
                    Student s = new Student();
                    s.setStudentNumber(studentNumber);
                    s.setName(studentName);
                    return s =addStudent(s);
                });
        student.setTutor(tutor);
        tutor.setChosen(tutor.getChosen()+1);

        return student;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "当前您的人数已满");
        }

    }


    /**
     * 修改密码
     * @param newPassword
     * @param tutorId
     * @return
     */
    public String updatePassword(String newPassword, Integer tutorId){
//        Tutor oldTutor = manager.find(Tutor.class, tutorId);
//        Tutor oldTutor = tutorRepository.findById(tutorId).orElse(new Tutor());
        Tutor oldTutor = getTutorById(tutorId);
        oldTutor.setPassword(encoder.encode(newPassword));

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
        Tutor oldTutor = getTutorById(tutorId);
//        oldTutor = tutor;//todo 为什么不行捏
        oldTutor.setTutorNumber(tutor.getTutorNumber());
        oldTutor.setName(tutor.getName());
//        oldTutor.setPassword(tutor.getPassword());
        oldTutor.setRECEIVABLE(tutor.getRECEIVABLE());
//        oldTutor.setRole(tutor.getRole());
        oldTutor.setOPTIONAL(tutor.getOPTIONAL());

        return oldTutor;
    }

    public Tutor getTutorByNumber(Integer tutorNumber) {
        return tutorRepository.findByTutorNumber(tutorNumber);
//        return Optional.ofNullable(tutorRepository.findByTutorNumber(tutorNumber))
//                .orElse(new Tutor());
//        return Optional.ofNullable(tutorRepository.findByTutorNumber(tutorNumber))
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "您输入的教师号不存在"));
    }

    public Tutor getTutorById(Integer tutorId){
        return tutorRepository.findById(tutorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "您输入的教师号不存在"));
    }

    /**
     * 判断导师是否有可选位置
     * @param tutorId
     * @return
     */
    public Boolean couldAccept(Integer tutorId){
//        Tutor tutor = manager.find(Tutor.class, tutorId);
        Tutor tutor = getTutorById(tutorId);
        return tutor.getRECEIVABLE() - tutor.getChosen() > 0;
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
        areaTutor.setTutor(getTutorById(tutorId));
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

//    /**
//     * 更新课设方向
//     * @param areaId
//     * @param detail
//     * @return
//     */
//    public Area updateArea(Integer areaId, String detail){
//        Area oldArea = areaRepository.findById(areaId).orElse(new Area());
//        oldArea.setDetail(detail);
//
//        return oldArea;
//    }

    public Area getArea(Integer areaId) {
        return areaRepository.findById(areaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "您输入的课设方向号不存在"));
    }

    /**
     * 查询导师下的方向
     * 有问题，无结果
     * 问题已解决，不知道咋解决的
     * @param tutorId
     * @return
     */
    public List<Area> listAreas(Integer tutorId) {
//        return areaTutorRepository.getAllByTutorId(tutorId);
        return areaRepository.getAreasByTutorId(tutorId);
    }


//课程相关

    /**
     * 添加课程
     * @param c
     * @param tutorId
     * @return
     */
//    public Course addCourse(String name, Float LINE, Float WEIGHT, Integer tutorId){
    public Course addCourse(Course c, Integer tutorId){
        Course course = new Course();
        course.setTutor(getTutorById(tutorId));
        course.setName(c.getName());
        course.setWEIGHT(c.getWEIGHT());
        course.setLINE(c.getLINE());
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
        Course oldCourse = getCourseById(courseId);
        if (oldCourse.getTutor().getId() == tutorId){
            oldCourse.setName(course.getName());
            oldCourse.setLINE(course.getLINE());
            oldCourse.setWEIGHT(course.getWEIGHT());

            return oldCourse;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "这位教师，这不是您的课，无法更改");
        }
    }

    /**
     * 删除课程
     * @param courseId
     */
    public void deleteCourse(Integer courseId){
        courseRepository.deleteById(courseId);
    }

    public Course getCourseById(Integer courseId) {
         return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "您输入的教师号不存在"));
    }

    /**
     * 读取导师课程集合 todo 主要是为了返回分数线集合，再想想，这里必须再Tutor类上强制为饿汉加载，不符合想法
     * @param tutorId
     * @return
     */
    public List<Course> listCourses(Integer tutorId){
//        return manager.find(Tutor.class, tutorId).getCourses();
        return getTutorById(tutorId).getCourses();
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
     * @param s
     * @return
     */
//    public Student addStudent(Integer studentNumber, String studentName){
    public Student addStudent(Student s){
        Student student = new Student();
//        student.setId(studentId);
        student.setStudentNumber(s.getStudentNumber());
        student.setName(s.getName());
        student.setRole(Role.STUDENT);

        studentRepository.save(student);

        return student;
    }

    public Student updateStudent(Student student, Integer studentId) {
        Student oldStudent = getStudentById(studentId);
//        oldStudent.setId(student.getId());
        oldStudent.setStudentNumber(student.getStudentNumber());
        oldStudent.setName(student.getName());
//        oldStudent.setRole(Role.STUDENT);

        return oldStudent;
    }

    public void deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    public Student getStudentByNumber(Integer studentNumber) {
//        return studentRepository.findById(studentNumber).orElse(new Student());//todo 此处不可new Student()，因为controller层用otional接收
        return studentRepository.findByStudentNumber(studentNumber);
//        return Optional.ofNullable(studentRepository.findByStudentNumber(studentNumber))
//                .orElse(new Student());
//        return Optional.ofNullable(studentRepository.findByStudentNumber(studentNumber))
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "您输入的学号不存在"));
    }

    public Student getStudentById(Integer studentId){
//        return studentRepository.findById(studentId).orElse(null);
//        return Optional.ofNullable(studentRepository.findById(studentId))
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "您输入的学号不存在"));
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "您输入的学号不存在"));
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

}
