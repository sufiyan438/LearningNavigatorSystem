package com.learningNavigator.Learning_Navigator_Project.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="student")
@Entity
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    
    @Column(name="student_id", unique=true, nullable=false)
    private String studentId;
    
    @Column(nullable=false)
    private String name;

    @ManyToMany(cascade=CascadeType.ALL,
                fetch=FetchType.LAZY)
    @JoinTable(
        name = "subjects_registered",
        joinColumns = @JoinColumn(name="student_id"),
        inverseJoinColumns = @JoinColumn(name="subject_id")
    )
    private List<Subject> subjects;


    @ManyToMany(cascade=CascadeType.ALL,
                fetch=FetchType.LAZY)
    @JoinTable(
        name = "exams_enrolled",
        joinColumns = @JoinColumn(name="student_id"),
        inverseJoinColumns = @JoinColumn(name="exam_id")
    )         
    private List<Exam> exams;
}
