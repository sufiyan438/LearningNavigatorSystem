package com.learningNavigator.Learning_Navigator_Project.Exchange;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostStudentRequest {
    @NotNull
    @Length(min=5, max=5)
    String registrationId;

    @NotNull
    String name;
}
