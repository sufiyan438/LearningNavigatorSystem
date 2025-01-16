package com.learningNavigator.Learning_Navigator_Project.Service;

import java.util.List;

import com.learningNavigator.Learning_Navigator_Project.Exchange.StudentResponse;

public interface ModService {
    StudentResponse enrollSubject(String studentId, List<String> subjectIds);
    StudentResponse updateSubject(String studentId, List<String> subjectIds);
    StudentResponse registerExam(String studentId, List<String> examIds);
}
