package com.learningNavigator.Learning_Navigator_Project.Exchange;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentResponse {
    private String studentId;
    
    private String name;

    private List<String> subjectIds;

    private List<String> examIds;
}
