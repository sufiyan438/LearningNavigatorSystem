package com.learningNavigator.Learning_Navigator_Project.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningNavigator.Learning_Navigator_Project.Exception.ResouceAlreadyExistsException;
import com.learningNavigator.Learning_Navigator_Project.Exception.ResourceNotFoundException;
import com.learningNavigator.Learning_Navigator_Project.Exchange.ExamResponse;
import com.learningNavigator.Learning_Navigator_Project.Exchange.PostExamRequest;
import com.learningNavigator.Learning_Navigator_Project.Exchange.PostExamResponse;
import com.learningNavigator.Learning_Navigator_Project.Exchange.SubjectResponse;
import com.learningNavigator.Learning_Navigator_Project.Model.Subject;
import com.learningNavigator.Learning_Navigator_Project.Model.Exam;
import com.learningNavigator.Learning_Navigator_Project.Model.Student;
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
    public List<ExamResponse> getAllExams() {
        List<Exam> examList = examRepository.findAll();
        List<ExamResponse> erList = new ArrayList<>();
        for(Exam exam : examList){
            erList.add(mapToExamResponse(exam));
        }
        return erList;
    }

    @Override
    public ExamResponse getByExamId(String examId) {
        if(!examRepository.existsByExamUniqueIdentifier(examId)){
            throw new ResourceNotFoundException("Exam doesn't exist!");
        }
        Exam exam = examRepository.getByExamUniqueIdentifier(examId);
        return mapToExamResponse(exam);
    }

    ExamResponse mapToExamResponse(Exam exam){
        ExamResponse er = new ExamResponse();
        er.setExamUniqueIdentifier(exam.getExamUniqueIdentifier());
        er.setSubjectId(exam.getSubject().getSubjectId());
        List<String> studentIds = new ArrayList<>();
        List<Student> students = exam.getRegisteredStudents();
        for(Student student : students){
            studentIds.add(student.getName());
        }
        er.setStudentNames(studentIds);
        return er;
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
    public ExamResponse updateExam(String examId, String subjectId) {
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
        Exam exam2 = examRepository.save(exam);
        return mapToExamResponse(exam2);
    }
}
