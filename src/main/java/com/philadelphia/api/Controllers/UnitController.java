package com.philadelphia.api.Controllers;

import com.philadelphia.api.DTO.AddUnitDTO;
import com.philadelphia.api.DTO.GetUnitDTO;
import com.philadelphia.api.Services.UnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/units")
@Tag(name = "Controller for units")
@PreAuthorize("hasAuthority('ADMIN')")
public class UnitController {
    @Autowired
    private UnitService unitService;
    @PostMapping
    @Operation(summary = "Add unit")
    public void addUnit(@RequestBody AddUnitDTO addUnitDTO){
        unitService.addUnit(addUnitDTO);
    }
    @GetMapping
    @Operation(summary = "Get all units")
    public List<GetUnitDTO> getAllUnits(){
        return unitService.getAllUnits();
    }
    @GetMapping("/{number}")
    @Operation(summary = "Get unit by number")
    public GetUnitDTO getUnitByNumber(@PathVariable("number") Long number){
        return unitService.getUnitByNumber(number);
    }
}
