package com.learningNavigator.Learning_Navigator_Project.Exchange;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class PutSubjectRequest {
    @NotNull
    String subjectName;
}
