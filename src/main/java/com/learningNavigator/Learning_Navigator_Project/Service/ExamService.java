package com.learningNavigator.Learning_Navigator_Project.Service;

import com.learningNavigator.Learning_Navigator_Project.Exchange.PostExamResponse;

import java.util.List;

import com.learningNavigator.Learning_Navigator_Project.Exchange.ExamResponse;

public interface ExamService {
    List<ExamResponse> getAllExams();
    ExamResponse getByExamId(String examId);
    PostExamResponse createExam(String examId, String subjectId);
    void deleteExam(String examId);
    ExamResponse updateExam(String examId, String subjectId);
}
