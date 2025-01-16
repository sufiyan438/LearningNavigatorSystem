package com.learningNavigator.Learning_Navigator_Project.Service.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningNavigator.Learning_Navigator_Project.Exception.IdNotOfLengthException;
import com.learningNavigator.Learning_Navigator_Project.Exception.ResouceAlreadyExistsException;
import com.learningNavigator.Learning_Navigator_Project.Exception.ResourceNotFoundException;
import com.learningNavigator.Learning_Navigator_Project.Exchange.StudentResponse;
import com.learningNavigator.Learning_Navigator_Project.Model.Exam;
import com.learningNavigator.Learning_Navigator_Project.Model.Student;
import com.learningNavigator.Learning_Navigator_Project.Model.Subject;
import com.learningNavigator.Learning_Navigator_Project.Repository.StudentRepository;
import com.learningNavigator.Learning_Navigator_Project.Repository.ExamRepository;
import com.learningNavigator.Learning_Navigator_Project.Repository.SubjectRepository;
import com.learningNavigator.Learning_Navigator_Project.Service.ModService;

@Service
public class ModServiceImpl implements ModService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Override
    public StudentResponse enrollSubject(String studentId, List<String> subjectIds) {
        List<Subject> subjects = validationHelper(studentId, subjectIds);
        Student student = studentRepository.findByStudentId(studentId);
        //checking if already enrolled once. IF yes, then cant use POST
        List<Subject> enrolledSubject = student.getSubjects();
        if(enrolledSubject.size() >= 1){
            throw new ResouceAlreadyExistsException("Already enrolled in some subjects. Try updating them!");
        }
        //end check
        student.setSubjects(subjects);
        Student student2 = studentRepository.save(student);
        return studentServiceImpl.mapToStudentResponse(student2);
    }

    List<Subject> validationHelper(String studentId, List<String> subjectIds){
        if(!studentRepository.existsByStudentId(studentId)){
            throw new ResourceNotFoundException("Student doesn't exist!");
        }
        List<Subject> subjects = new ArrayList<>();
        for(String subjectId : subjectIds){
            if(subjectId.length() != 5){
                throw new IdNotOfLengthException("One of your subject Id's is not 5 letters long. Try again!");
            }
            if(!subjectRepository.existsBySubjectId(subjectId)){
                throw new ResourceNotFoundException("One of these subject's doesn't exist. Try again!");
            }
            Subject s = subjectRepository.findBySubjectId(subjectId).get();
            subjects.add(s);
        }
        return subjects;
    }

    @Override
    public StudentResponse updateSubject(String studentId, List<String> subjectIds) {
        List<Subject> subjects = validationHelper(studentId, subjectIds);
        Student student = studentRepository.findByStudentId(studentId);
        List<Subject> subjectsEnrolled = student.getSubjects();
        for(Subject subject: subjects){
            if (!subjectsEnrolled.contains(subject)) {
                subject = subjectRepository.findById(subject.getId()).orElse(subject);
                subjectsEnrolled.add(subject);
            }
        }
        student.setSubjects(subjectsEnrolled);
        // student.getSubjects().addAll(subjects);
        Student student2 = studentRepository.save(student);
        return studentServiceImpl.mapToStudentResponse(student2);
    }

    @Override
    public StudentResponse registerExam(String studentId, List<String> examIds) {
        if(!studentRepository.existsByStudentId(studentId)){
            throw new ResourceNotFoundException("Student doesn't exist!");
        }
        Student student = studentRepository.findByStudentId(studentId);
        List<Exam> exams = new ArrayList<>();
        List<String> subjectIds = new ArrayList<>();
        for(String examId : examIds){
            if(examId.length() != 5){
                throw new IdNotOfLengthException("One of your exam Id's is not 5 letters long. Try again!");
            }
            if(!examRepository.existsByExamUniqueIdentifier(examId)){
                throw new ResourceNotFoundException("One of these exam's doesn't exist. Try again!");
            }
            Exam e = examRepository.getByExamUniqueIdentifier(examId);
            String subjectId = e.getSubject().getSubjectId();
            subjectIds.add(subjectId);
            exams.add(e);
        }
        List<Subject> enrolledSubjects = student.getSubjects();
        List<String> enrolledStrings = new ArrayList<>();
        for(Subject s : enrolledSubjects){
            enrolledStrings.add(s.getSubjectId());
        }
        Set<String> enrolledStringsSet = new HashSet<>(enrolledStrings);
        Set<String> subjectIdSet = new HashSet<>(subjectIds);
        if(!enrolledStringsSet.equals(subjectIdSet)){
            throw new ResourceNotFoundException("You haven't enrolled for some of these subjects!");
        }
        student.setExams(exams);
        Student student2 = studentRepository.save(student);
        return studentServiceImpl.mapToStudentResponse(student2);
    }
    
}
