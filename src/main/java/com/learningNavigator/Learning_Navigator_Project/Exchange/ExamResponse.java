package com.learningNavigator.Learning_Navigator_Project.Exchange;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExamResponse {
    private String examUniqueIdentifier;    

    private String subjectId;

    private List<String> studentNames;
}
