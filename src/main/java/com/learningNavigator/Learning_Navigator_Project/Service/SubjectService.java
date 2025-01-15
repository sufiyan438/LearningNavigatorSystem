package com.learningNavigator.Learning_Navigator_Project.Service;

import java.util.List;

import com.learningNavigator.Learning_Navigator_Project.Exchange.PostSubjectResponse;
import com.learningNavigator.Learning_Navigator_Project.Model.Subject;

public interface SubjectService {
    List<Subject> getAllSubjects();
    Subject getBySubjectId(String subjectId);
    PostSubjectResponse createSubject(String subjectId, String subjectName);
    void deleteSubject(String subjectId);
    Subject updateSubject(String subjectId, String subjectName);
}
