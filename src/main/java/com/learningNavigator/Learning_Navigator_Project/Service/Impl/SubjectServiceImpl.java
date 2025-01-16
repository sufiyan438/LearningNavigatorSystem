package com.learningNavigator.Learning_Navigator_Project.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningNavigator.Learning_Navigator_Project.Exception.ResouceAlreadyExistsException;
import com.learningNavigator.Learning_Navigator_Project.Exception.ResourceNotFoundException;
import com.learningNavigator.Learning_Navigator_Project.Exchange.SubjectResponse;
import com.learningNavigator.Learning_Navigator_Project.Exchange.PostSubjectResponse;
import com.learningNavigator.Learning_Navigator_Project.Model.Student;
import com.learningNavigator.Learning_Navigator_Project.Model.Subject;
import com.learningNavigator.Learning_Navigator_Project.Repository.SubjectRepository;
import com.learningNavigator.Learning_Navigator_Project.Service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<SubjectResponse> getAllSubjects() {
        List<Subject> subjectList = subjectRepository.findAll();
        List<SubjectResponse> srList = new ArrayList<>();
        for(Subject subject : subjectList){
            srList.add(maptoSubjectResponse(subject));
        }
        return srList;
    }

    @Override
    public SubjectResponse getBySubjectId(String subjectId) {
        if(!subjectRepository.existsBySubjectId(subjectId)){
            throw new ResourceNotFoundException("Subject doesn't exist!");
        }
        Subject subject = subjectRepository.findBySubjectId(subjectId).get();
        return maptoSubjectResponse(subject);
    }

    SubjectResponse maptoSubjectResponse(Subject subject){
        SubjectResponse sr = new SubjectResponse();
        sr.setSubjectId(subject.getSubjectId());
        sr.setSubjectName(subject.getSubjectName());
        List<Student> studentList = subject.getRegisteredStudents();
        List<String> studentNames = new ArrayList<>();
        for(Student student: studentList){
            studentNames.add(student.getName());
        }
        sr.setStudentNames(studentNames);
        return sr;
    }

    @Override
    public PostSubjectResponse createSubject(String subjectId, String subjectName) {
        if(subjectRepository.existsBySubjectId(subjectId)){
            throw new ResouceAlreadyExistsException("Subject already saved!");
        }
        Subject subject = new Subject();
        subject.setSubjectId(subjectId);
        subject.setSubjectName(subjectName);
        Subject subject2 = subjectRepository.save(subject);

        PostSubjectResponse psr = new PostSubjectResponse(subject2.getSubjectId(), subject2.getSubjectName());
        return psr;
    }

    @Override
    public void deleteSubject(String subjectId) {
        if(!subjectRepository.existsBySubjectId(subjectId)){
            throw new ResourceNotFoundException("Subject doesn't exist!");
        }
        subjectRepository.deleteBySubjectId(subjectId);
    }

    @Override
    public SubjectResponse updateSubject(String subjectId, String subjectName) {
        if(!subjectRepository.existsBySubjectId(subjectId)){
            throw new ResourceNotFoundException("Subject doesn't exist!");
        }
        Subject subject = subjectRepository.findBySubjectId(subjectId).get();
        subject.setSubjectName(subjectName);
        Subject subject2 = subjectRepository.save(subject);
        return maptoSubjectResponse(subject2);
    }
    
}
