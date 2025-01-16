package com.learningNavigator.Learning_Navigator_Project.Service;

import java.util.List;

import com.learningNavigator.Learning_Navigator_Project.Exchange.PostStudentResponse;
import com.learningNavigator.Learning_Navigator_Project.Exchange.StudentResponse;

public interface StudentService {
    PostStudentResponse createStudent(String registrationId, String name);
    List<StudentResponse> getAllStudents();
    StudentResponse getByStudentId(String studentId);
    void deleteStudent(String studentId);
    StudentResponse updateStudent(String studentId, String name);
}
