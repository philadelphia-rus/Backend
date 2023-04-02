package com.philadelphia.api.DTO;

import com.philadelphia.api.Database.TypesSteps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionStepDTO {
    private TypesSteps type;
    private Long number;
    private String name;
    private String question;
    private List<String> options;
    private String correctAnswer;
}
