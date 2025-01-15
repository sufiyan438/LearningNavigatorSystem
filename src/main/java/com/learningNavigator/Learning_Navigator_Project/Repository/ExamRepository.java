package com.learningNavigator.Learning_Navigator_Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learningNavigator.Learning_Navigator_Project.Model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long>{
    boolean existsByExamUniqueIdentifier(String examId);
    Exam getByExamUniqueIdentifier(String examId);
    void deleteByExamUniqueIdentifier(String examId);

    @Query(value="select exam_unique_identifier from exam where subject_id =:subjectPK", nativeQuery=true)
    String existsBySubjectId(@Param("subjectPK") Long subjectPK);
}
