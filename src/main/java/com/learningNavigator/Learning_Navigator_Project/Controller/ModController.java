package com.learningNavigator.Learning_Navigator_Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningNavigator.Learning_Navigator_Project.Exchange.PostEnrollSubject;
import com.learningNavigator.Learning_Navigator_Project.Exchange.PostRegisterExam;
import com.learningNavigator.Learning_Navigator_Project.Exchange.StudentResponse;
import com.learningNavigator.Learning_Navigator_Project.Service.ModService;
import com.learningNavigator.Learning_Navigator_Project.Model.Student;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class ModController {
    
    @Autowired
    private ModService modService;

    @PostMapping("/subject")
    public ResponseEntity<StudentResponse> enrollSubjects(@Valid @RequestBody PostEnrollSubject postEnrollSubject){
        StudentResponse student = modService.enrollSubject(postEnrollSubject.getStudentId(), postEnrollSubject.getSubjectIds());
        return ResponseEntity.ok().body(student);
    }

    @PutMapping("/subject")
    public ResponseEntity<StudentResponse> updateSubjects(@Valid @RequestBody PostEnrollSubject postEnrollSubject){
        StudentResponse student = modService.enrollSubject(postEnrollSubject.getStudentId(), postEnrollSubject.getSubjectIds());
        return ResponseEntity.ok().body(student);
    }

    @PostMapping("/exam")
    public ResponseEntity<StudentResponse> registerExams(@Valid @RequestBody PostRegisterExam postRegisterExam){
        StudentResponse student = modService.registerExam(postRegisterExam.getStudentId(), postRegisterExam.getExamIds());
        return ResponseEntity.ok().body(student);
    }

}
