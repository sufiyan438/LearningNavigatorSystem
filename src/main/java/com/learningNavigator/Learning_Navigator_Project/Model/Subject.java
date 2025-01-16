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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="subject")
@NoArgsConstructor
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name="subject_id", nullable=false)
    private String subjectId;

    // @Enumerated(EnumType.STRING)
    @Column(name="subject_name")
    private String subjectName;

    @ManyToMany(mappedBy = "subjects", cascade={CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.LAZY)
    private List<Student> registeredStudents;
}
