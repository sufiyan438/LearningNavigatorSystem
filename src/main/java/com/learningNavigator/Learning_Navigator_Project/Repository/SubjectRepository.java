package com.learningNavigator.Learning_Navigator_Project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningNavigator.Learning_Navigator_Project.Model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{
    Optional<Subject> findBySubjectId(String subjectId);
    void deleteBySubjectId(String subjectId);
    boolean existsBySubjectId(String subjectId);
}
