package com.learningNavigator.Learning_Navigator_Project.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningNavigator.Learning_Navigator_Project.Exception.ResouceAlreadyExistsException;
import com.learningNavigator.Learning_Navigator_Project.Exception.ResourceNotFoundException;
import com.learningNavigator.Learning_Navigator_Project.Exchange.PostExamRequest;
import com.learningNavigator.Learning_Navigator_Project.Exchange.PostExamResponse;
import com.learningNavigator.Learning_Navigator_Project.Model.Subject;
import com.learningNavigator.Learning_Navigator_Project.Model.Exam;
import com.learningNavigator.Learning_Navigator_Project.Repository.ExamRepository;
import com.learningNavigator.Learning_Navigator_Project.Repository.SubjectRepository;
import com.learningNavigator.Learning_Navigator_Project.Service.ExamService;

@Service
public class ExamServiceImpl implements ExamService{

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Exam getByExamId(String examId) {
        if(!examRepository.existsByExamUniqueIdentifier(examId)){
            throw new ResourceNotFoundException("Exam doesn't exist!");
        }
        return examRepository.getByExamUniqueIdentifier(examId);
    }

    @Override
    public PostExamResponse createExam(String examId, String subjectId) {
        if(examRepository.existsByExamUniqueIdentifier(examId)){
            throw new ResouceAlreadyExistsException("Exam already exists!");
        }
        if(!subjectRepository.existsBySubjectId(subjectId)){
            throw new ResourceNotFoundException("Subject doesn't exist!");
        }
        Subject subject = subjectRepository.findBySubjectId(subjectId).get();
        
        //check if exam is already scheduled for the subject

        Long subjectPK = subject.getId();
        String exam_unique_identifier = examRepository.existsBySubjectId(subjectPK);
        if((!exam_unique_identifier.isBlank()) || exam_unique_identifier != null){
            throw new ResouceAlreadyExistsException("Exam has already been scheduled for this subject!");
        }

        //end check

        Exam exam = new Exam();
        exam.setExamUniqueIdentifier(examId);
        exam.setSubject(subject);
        Exam exam2 = examRepository.save(exam);
        PostExamResponse psr = new PostExamResponse(exam2.getExamUniqueIdentifier(), exam2.getSubject().getSubjectId());
        return psr;
    }

    @Override
    public void deleteExam(String examId) {
        if(!examRepository.existsByExamUniqueIdentifier(examId)){
            throw new ResourceNotFoundException("Exam doesn't exist!");
        }
        examRepository.deleteByExamUniqueIdentifier(examId);
    }

    @Override
    public Exam updateExam(String examId, String subjectId) {
        if(!examRepository.existsByExamUniqueIdentifier(examId)){
            throw new ResourceNotFoundException("Exam doesn't exist!");
        }
        if(!subjectRepository.existsBySubjectId(subjectId)){
            throw new ResourceNotFoundException("Subject doesn't exist!");
        }
        Subject subject = subjectRepository.findBySubjectId(subjectId).get();
        //check if exam is already scheduled for the subject

        Long subjectPK = subject.getId();
        String exam_unique_identifier = examRepository.existsBySubjectId(subjectPK);
        if((!exam_unique_identifier.isBlank()) || exam_unique_identifier != null){
            throw new ResouceAlreadyExistsException("Exam has already been scheduled for this subject!");
        }

        //end check
        Exam exam = examRepository.getByExamUniqueIdentifier(examId);
        exam.setSubject(subject);
        return examRepository.save(exam);
    }
}
