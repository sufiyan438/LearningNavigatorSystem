package com.learningNavigator.Learning_Navigator_Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningNavigator.Learning_Navigator_Project.Model.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long>{
    
}
