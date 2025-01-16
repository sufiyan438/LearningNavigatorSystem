package com.learningNavigator.Learning_Navigator_Project.Service;

import java.util.List;

import com.learningNavigator.Learning_Navigator_Project.Exchange.PostSubjectResponse;
import com.learningNavigator.Learning_Navigator_Project.Exchange.SubjectResponse;
import com.learningNavigator.Learning_Navigator_Project.Model.Subject;

public interface SubjectService {
    List<SubjectResponse> getAllSubjects();
    SubjectResponse getBySubjectId(String subjectId);
    PostSubjectResponse createSubject(String subjectId, String subjectName);
    void deleteSubject(String subjectId);
    SubjectResponse updateSubject(String subjectId, String subjectName);
}
