package com.example.twowaychoice.service;

import com.example.twowaychoice.entity.Area;
import com.example.twowaychoice.entity.AreaStudent;
import com.example.twowaychoice.entity.Student;
import com.example.twowaychoice.entity.Tutor;
import com.example.twowaychoice.repository.AreaStudentRepository;
import com.example.twowaychoice.repository.StudentRepository;
import com.example.twowaychoice.repository.TutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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

    /**
     * 学生选择课设方向，可多选，返回值先改为void，否则查询好多次
     * @param studentId
     * @param areaIds
     * @return
     */
    public void chooseAreas(Integer studentId, List<Integer> areaIds){
        for (Integer a : areaIds){
            AreaStudent areaStudent = new AreaStudent();
            areaStudent.setStudent(manager.find(Student.class, studentId));
            areaStudent.setArea(manager.find(Area.class, a));
            areaStudentRepository.save(areaStudent);
        }
    }


    /**
     * 学生更新课设方向
     * @param studentId
     * @param areaIds
     * @return
     */
    public void updateAreas(Integer studentId, List<Integer> areaIds){
        // 删除原有的在CourseStudent表的数据
        areaStudentRepository.deleteByStudentId(studentId);
        this.chooseAreas(studentId, areaIds);
    }


    /**
     * 学生选择导师，没有退选
     * @param studentId
     * @param tutorId
     * @return
     */
    public Tutor chooseTutor(Integer studentId, Integer tutorId){
        Student student = manager.find(Student.class, studentId);
        Tutor tutor = manager.find(Tutor.class, tutorId);
        student.setTutor(tutor);
        tutor.setChosen(tutor.getChosen() + 1);

        return tutor;
    }

}
