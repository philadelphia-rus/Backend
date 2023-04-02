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
public class VideoStepDTO{
    private TypesSteps type;
    private Long number;
    private String name;
    private String video;
}
