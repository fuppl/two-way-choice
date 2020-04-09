package com.example.twowaychoice.service;

import com.example.twowaychoice.entity.Area;
import com.example.twowaychoice.entity.AreaStudent;
import com.example.twowaychoice.entity.Student;
import com.example.twowaychoice.entity.Tutor;
import com.example.twowaychoice.repository.AreaRepository;
import com.example.twowaychoice.repository.AreaStudentRepository;
import com.example.twowaychoice.repository.StudentRepository;
import com.example.twowaychoice.repository.TutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentService {
    @Autowired
    private EntityManager manager;
    @Autowired
    private AreaStudentRepository areaStudentRepository;
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private AreaRepository areaRepository;

    public List<Area> listAllAreas(){
        return areaRepository.findAll();
    }

    /**
     * 学生选择课设方向
     * @param studentId
     * @param areaIds
     * @return
     */
    public List<Area> chooseAreas(Integer studentId, List<Integer> areaIds){
        List<Area> areas = new ArrayList<>();
        for (Integer a : areaIds){
            AreaStudent areaStudent = new AreaStudent();
            areaStudent.setStudent(tutorService.getStudentById(studentId));
            areaStudent.setArea(areaRepository.findById(a)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"您输入的课设方向号不存在")));
            areaStudentRepository.save(areaStudent);
            areas.add(areaStudent.getArea());
        }

        return areas;
    }


//    /**
//     * 学生更新课设方向
//     * @param studentId
//     * @param areaIds
//     * @return
//     */
//    public void updateAreas(Integer studentId, List<Integer> areaIds){
//        // 删除原有的在CourseStudent表的数据
//        areaStudentRepository.deleteByStudentId(studentId);
//        this.chooseAreas(studentId, areaIds);
//    }


    /**
     * 学生选择导师，没有退选
     * @param studentId
     * @param tutorId
     * @return
     */
    public Tutor chooseTutor(Integer studentId, Integer tutorId){
        Student student = tutorService.getStudentById(studentId);
        if (student.getTutor() != null){
            Tutor tutor = tutorService.getTutorById(student.getTutor().getId());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "您已经是 "+tutor.getName()+" 的学生了");
        }
        if (tutorService.couldAccept(tutorId)){
//        Student student = manager.find(Student.class, studentId);

        Tutor tutor = tutorService.getTutorById(tutorId);
        student.setTutor(tutor);
        tutor.setChosen(tutor.getChosen() + 1);

        return tutor;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "当前教师人数已满");
        }
    }

    public Student getStudentById(Integer studentId) {
        return tutorService.getStudentById(studentId);
    }

    public Student getStudentByNumber(Integer studentNumber){
        return tutorService.getStudentByNumber(studentNumber);
    }
}
