package com.philadelphia.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUnitDTO {
    private String name;
    private Long number;
    private List<StepDTO> steps;
}
