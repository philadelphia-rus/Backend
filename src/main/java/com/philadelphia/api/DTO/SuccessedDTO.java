package com.philadelphia.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuccessedDTO {
    private Long numberUnit;
    private Long numberStep;
    private Boolean isCompleted;
}
