package com.learningNavigator.Learning_Navigator_Project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningNavigator.Learning_Navigator_Project.Exchange.PutStudentRequest;
import com.learningNavigator.Learning_Navigator_Project.Exchange.PostStudentRequest;
import com.learningNavigator.Learning_Navigator_Project.Exchange.PostStudentResponse;
import com.learningNavigator.Learning_Navigator_Project.Model.Student;
import com.learningNavigator.Learning_Navigator_Project.Service.StudentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learningNavigator.Learning_Navigator_Project.Exchange.StudentResponse;

@RestController
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<PostStudentResponse> saveStudent(@Valid @RequestBody PostStudentRequest postStudentRequest) {
        PostStudentResponse psr =  studentService.createStudent(postStudentRequest.getRegistrationId(), postStudentRequest.getName());
        return ResponseEntity.ok().body(psr);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents(){
        List<StudentResponse> studentList = studentService.getAllStudents();
        return ResponseEntity.ok().body(studentList);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponse> getSubjectById(@PathVariable String studentId){
        StudentResponse student = studentService.getByStudentId(studentId);
        return ResponseEntity.ok().body(student);
    }
    
    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable String studentId){
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().body("Student deleted!");
    }
    //NOT WORKING

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable String studentId, @Valid @RequestBody PutStudentRequest putStudentRequest){
        StudentResponse student = studentService.updateStudent(studentId, putStudentRequest.getName());
        return ResponseEntity.ok().body(student);
    }
}
