package com.learningNavigator.Learning_Navigator_Project.Exchange;

import lombok.Data;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class PutExamRequest {
    @NotNull
    @Length(min=5, max=5)
    String subjectId;
}
