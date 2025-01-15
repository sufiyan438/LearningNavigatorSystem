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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="exam")
@Entity
@NoArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name="exam_unique_identifier", unique=true, nullable=false)
    private String examUniqueIdentifier;    

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn (name="subject_id")
    private Subject subject;

    @ManyToMany(mappedBy="exams", cascade=CascadeType.ALL,
        fetch=FetchType.LAZY)
    private List<Student> registeredStudents;
}
