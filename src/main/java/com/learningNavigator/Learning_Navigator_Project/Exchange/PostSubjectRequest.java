package com.learningNavigator.Learning_Navigator_Project.Exchange;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostSubjectRequest {
    @NotNull
    @Length(min=5, max=5)
    private String subjectId;

    @NotNull
    private String subjectName;
}
