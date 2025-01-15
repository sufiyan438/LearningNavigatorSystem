package com.learningNavigator.Learning_Navigator_Project.Exchange;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostExamRequest {
    @NotNull
    @Length(min=5, max=5)
    private String examId;

    @NotNull
    @Length(min=5, max=5)
    private String subjectId;
}
