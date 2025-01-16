package com.learningNavigator.Learning_Navigator_Project.Exchange;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostRegisterExam {
    @Length(min=5, max=5)
    @NotNull
    String studentId;

    @NotNull
    List<String> examIds;
}
