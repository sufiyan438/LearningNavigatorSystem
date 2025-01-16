package com.learningNavigator.Learning_Navigator_Project.Exchange;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostEnrollSubject {
    @Length(min=5, max=5)
    @NotNull
    String studentId;

    @NotNull
    // @Length(min=5, max=5)
    List<String> subjectIds;
}
