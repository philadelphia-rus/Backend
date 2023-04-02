package com.philadelphia.api.Services;

import com.philadelphia.api.DAO.UnitDao;
import com.philadelphia.api.DTO.AddUnitDTO;
import com.philadelphia.api.DTO.GetUnitDTO;
import com.philadelphia.api.DTO.StepDTO;
import com.philadelphia.api.Database.Steps;
import com.philadelphia.api.Database.Units;
import com.philadelphia.api.Errors.Failed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UnitService {
    @Autowired
    private UnitDao unitDao;
    public void addUnit(AddUnitDTO addUnitDTO){
        unitDao.addUnit(addUnitDTO.getName(), addUnitDTO.getNumber());
    }
    @Transactional(readOnly = true)
    public List<GetUnitDTO> getAllUnits(){
        List<Units> allUnits = unitDao.getAll();
        List<GetUnitDTO> result = new ArrayList<>();
        for(Units in : allUnits){
            List<StepDTO> steps = new ArrayList<>();
            for(Steps step: in.getSteps()){
                steps.add(StepDTO.builder().name(step.getName()).video(step.getVideo())
                        .type(step.getType()).mdFile(step.getMdFile()).build());
            }
            result.add(GetUnitDTO.builder().name(in.getName()).number(in.getNumber()).steps(steps).build());
        }

        return result;
    }
    @Transactional(readOnly = true)
    public GetUnitDTO getUnitByNumber(Long number){
        List<Units> units = unitDao.getUnitByNumber(number);
        if(units.size() == 0){
            throw new Failed("Doesn't have such unit");
        }
        Units unit = unitDao.getUnitByNumber(number).get(0);
        List<StepDTO> steps = new ArrayList<>();
        for(Steps in : unit.getSteps()){
            steps.add(StepDTO.builder().name(in.getName()).video(in.getVideo())
                    .type(in.getType()).mdFile(in.getMdFile()).build());
        }
        return GetUnitDTO.builder().name(unit.getName()).number(unit.getNumber()).steps(steps).build();
    }

}
