package com.learningNavigator.Learning_Navigator_Project.Service;

import com.learningNavigator.Learning_Navigator_Project.Exchange.PostExamResponse;
import com.learningNavigator.Learning_Navigator_Project.Model.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> getAllExams();
    Exam getByExamId(String examId);
    PostExamResponse createExam(String examId, String subjectId);
    void deleteExam(String examId);
    Exam updateExam(String examId, String subjectId);
}
