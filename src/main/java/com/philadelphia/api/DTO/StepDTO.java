package com.philadelphia.api.DTO;

import com.philadelphia.api.Database.TypesSteps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StepDTO {
    private TypesSteps type;
    private String name;
    private String video;
    private String mdFile;
    private String question;
}
