package com.learningNavigator.Learning_Navigator_Project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningNavigator.Learning_Navigator_Project.Model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    Optional<String> findNameById(Long id)    ;
}
