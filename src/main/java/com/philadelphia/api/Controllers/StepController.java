package com.philadelphia.api.Controllers;

import com.philadelphia.api.DTO.AddStepDTO;
import com.philadelphia.api.DTO.SuccessedDTO;
import com.philadelphia.api.Services.StepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/steps")
@Tag(name = "Controller for steps in units")
@PreAuthorize("hasAuthority('ADMIN')")
public class StepController {
    @Autowired
    private StepService stepService;
    @PostMapping
    @Operation(summary = "Add step to unit")
    public void addStep(@RequestBody AddStepDTO addStepDTO){
        stepService.addStep(addStepDTO);
    }
    @GetMapping("/{number}/{numberUnit}")
    @Operation(summary = "Get step by number")
    public Object getStepByNumber(@PathVariable("number") Long number, @PathVariable("numberUnit") Long numberUnit){
        return stepService.getStepByNumber(number, numberUnit);
    }
    @GetMapping("/makeComplete/{stepNumber}/{unitNumber}/{userId}")
    @Operation(summary = "Make successfull step to user")
    public void completeStep(@PathVariable("stepNumber") Long number, @PathVariable("unitNumber") Long unitNumber, @PathVariable("userId") Long userId){
        stepService.completeStep(number, unitNumber, userId);
    }
    @GetMapping("/getAllStepsForUser/{userId}")
    @Operation(summary = "Get all info about status steps for user")
    public List<SuccessedDTO> getUserSteps(@PathVariable("userId") Long userId){
        return stepService.getUserSteps(userId);
    }
}
