package com.learningNavigator.Learning_Navigator_Project.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.learningNavigator.Learning_Navigator_Project.Exchange.PostExamRequest;
import com.learningNavigator.Learning_Navigator_Project.Exchange.PostExamResponse;
import com.learningNavigator.Learning_Navigator_Project.Exchange.PutExamRequest;
import com.learningNavigator.Learning_Navigator_Project.Model.Exam;
import com.learningNavigator.Learning_Navigator_Project.Service.ExamService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/exam")    
public class ExamController {
    
    @Autowired
    private ExamService examService;

    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams(){
        List<Exam> examList = examService.getAllExams();
        return ResponseEntity.ok().body(examList);
    }

    @GetMapping("/{examId}")
    public ResponseEntity<Exam> getExamById(@PathVariable String examId){
        Exam exam = examService.getByExamId(examId);
        return ResponseEntity.ok().body(exam);
    }

    @PostMapping
    public ResponseEntity<PostExamResponse> createExam(@Valid @RequestBody PostExamRequest postExamRequest){
        PostExamResponse exam = examService.createExam(postExamRequest.getExamId(), postExamRequest.getSubjectId());
        return ResponseEntity.ok().body(exam);
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<String> deleteExam(@PathVariable String examId){
        examService.deleteExam(examId);
        return ResponseEntity.ok().body("Exam deleted!");
    }
    //NOT WORKING
    
    @PutMapping("/{examId}")
    public ResponseEntity<Exam> updateExam(@PathVariable String examId, @Valid @RequestBody PutExamRequest putExamRequest){
        Exam exam = examService.updateExam(examId, putExamRequest.getSubjectId());
        return ResponseEntity.ok().body(exam);
    }

}