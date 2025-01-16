package com.learningNavigator.Learning_Navigator_Project.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningNavigator.Learning_Navigator_Project.Exception.ResouceAlreadyExistsException;
import com.learningNavigator.Learning_Navigator_Project.Exception.ResourceNotFoundException;
import com.learningNavigator.Learning_Navigator_Project.Exchange.ExamResponse;
import com.learningNavigator.Learning_Navigator_Project.Exchange.PostStudentResponse;
import com.learningNavigator.Learning_Navigator_Project.Exchange.StudentResponse;
import com.learningNavigator.Learning_Navigator_Project.Model.Exam;
import com.learningNavigator.Learning_Navigator_Project.Model.Student;
import com.learningNavigator.Learning_Navigator_Project.Model.Subject;
import com.learningNavigator.Learning_Navigator_Project.Repository.StudentRepository;
import com.learningNavigator.Learning_Navigator_Project.Service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
    
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public PostStudentResponse createStudent(String registrationId, String name) {
        if(studentRepository.existsByStudentId(registrationId)){
            throw new ResouceAlreadyExistsException("Student already saved!");
        }
        Student student = new Student();
        student.setName(name);
        student.setStudentId(registrationId);
        Student student2 = studentRepository.save(student);
        PostStudentResponse psr = new PostStudentResponse(student2.getStudentId(), student2.getName());
        return psr;
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentResponse> srList = new ArrayList<>();
        for(Student student : studentList){
            srList.add(mapToStudentResponse(student));
        }
        return srList;
    }

    @Override
    public StudentResponse getByStudentId(String studentId) {
        if(!studentRepository.existsByStudentId(studentId)){
            throw new ResourceNotFoundException("Student doesn't exist!");
        }
        Student student = studentRepository.findByStudentId(studentId);
        return mapToStudentResponse(student);
    }

    StudentResponse mapToStudentResponse(Student student){
        StudentResponse sr = new StudentResponse();
        sr.setStudentId(student.getStudentId());
        sr.setName(student.getName());
        List<String> subjectIds = new ArrayList<>();
        List<String> examIds = new ArrayList<>();
        List<Exam> exams = student.getExams();
        List<Subject> subjects = student.getSubjects();
        for(Exam exam : exams){
            examIds.add(exam.getExamUniqueIdentifier());
        }
        for(Subject subject : subjects){
            subjectIds.add(subject.getSubjectId());
        }
        sr.setExamIds(examIds);
        sr.setSubjectIds(subjectIds);
        return sr;
    }

    @Override
    public void deleteStudent(String studentId) {
        if(!studentRepository.existsByStudentId(studentId)){
            throw new ResourceNotFoundException("Student doesn't exist!");
        }
        studentRepository.deleteByStudentId(studentId);
    }

    @Override
    public StudentResponse updateStudent(String studentId, String name) {
        if(!studentRepository.existsByStudentId(studentId)){
            throw new ResourceNotFoundException("Student doesn't exist!");
        }
        Student student = studentRepository.findByStudentId(studentId);
        student.setName(name);
        Student student2 = studentRepository.save(student);
        return mapToStudentResponse(student2);
    }

}
