package com.learningNavigator.Learning_Navigator_Project.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningNavigator.Learning_Navigator_Project.Exception.ResouceAlreadyExistsException;
import com.learningNavigator.Learning_Navigator_Project.Exception.ResourceNotFoundException;
import com.learningNavigator.Learning_Navigator_Project.Exchange.PostSubjectResponse;
import com.learningNavigator.Learning_Navigator_Project.Model.Subject;
import com.learningNavigator.Learning_Navigator_Project.Repository.SubjectRepository;
import com.learningNavigator.Learning_Navigator_Project.Service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getBySubjectId(String subjectId) {
        if(!subjectRepository.existsBySubjectId(subjectId)){
            throw new ResourceNotFoundException("Subject doesn't exist!");
        }
        return subjectRepository.findBySubjectId(subjectId).get();
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
    public Subject updateSubject(String subjectId, String subjectName) {
        if(!subjectRepository.existsBySubjectId(subjectId)){
            throw new ResourceNotFoundException("Subject doesn't exist!");
        }
        Subject subject = subjectRepository.findBySubjectId(subjectId).get();
        subject.setSubjectName(subjectName);
        return subjectRepository.save(subject);
    }
    
}
