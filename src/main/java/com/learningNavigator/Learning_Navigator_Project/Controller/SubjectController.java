package com.learningNavigator.Learning_Navigator_Project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningNavigator.Learning_Navigator_Project.Service.SubjectService;

import jakarta.validation.Valid;

import com.learningNavigator.Learning_Navigator_Project.Exchange.PostSubjectRequest;
import com.learningNavigator.Learning_Navigator_Project.Exchange.PostSubjectResponse;
import com.learningNavigator.Learning_Navigator_Project.Exchange.PutSubjectRequest;
import com.learningNavigator.Learning_Navigator_Project.Exchange.SubjectResponse;
import com.learningNavigator.Learning_Navigator_Project.Model.Subject;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SubjectResponse>> getAllSubjects(){
        List<SubjectResponse> subjectList = subjectService.getAllSubjects();
        return ResponseEntity.ok().body(subjectList);
    }

        @GetMapping("/{subjectId}")
    public ResponseEntity<SubjectResponse> getSubjectById(@PathVariable String subjectId){
        SubjectResponse subject = subjectService.getBySubjectId(subjectId);
        return ResponseEntity.ok().body(subject);
    }

    @PostMapping
    public ResponseEntity<PostSubjectResponse> createSubject(@Valid @RequestBody PostSubjectRequest postSubjectRequest){
        PostSubjectResponse subject = subjectService.createSubject(postSubjectRequest.getSubjectId(), postSubjectRequest.getSubjectName());
        return ResponseEntity.ok().body(subject);
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<String> deleteSubject(@PathVariable String subjectId){
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.ok().body("Subject deleted!");
    }

    @PutMapping("/{subjectId}")
    public ResponseEntity<SubjectResponse> updateSubject(@PathVariable String subjectId, @RequestBody PutSubjectRequest putSubjectRequest){
        SubjectResponse subject = subjectService.updateSubject(subjectId, putSubjectRequest.getSubjectName());
        return ResponseEntity.ok().body(subject);
    }
    
}
