package com.learningNavigator.Learning_Navigator_Project.Exchange;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PutStudentRequest {
    @NotNull
    String name;
}
