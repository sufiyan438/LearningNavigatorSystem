package com.learningNavigator.Learning_Navigator_Project.Exchange;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubjectResponse {
    private String subjectId;

    private String subjectName;

    private List<String> studentNames;
}
