package com.philadelphia.api.DAO;

import com.philadelphia.api.Database.Steps;
import com.philadelphia.api.Database.TypesSteps;
import com.philadelphia.api.Database.Units;
import com.philadelphia.api.Errors.Failed;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class StepDAO {
    @Autowired
    private EntityManager entityManager;
    public void addVideoStep(String name, String videoHref, Long number, Long numberUnit){
        Session session = entityManager.unwrap(Session.class);
        List<Units> units = session.createQuery("select unit from Units unit where unit.number=:number",
                        Units.class).setParameter("number", numberUnit).getResultList();
        if(units.size() == 0){
            throw new Failed("Bad unit number");
        }
        Units unit = units.get(0);
        Steps step = Steps.builder().video(videoHref).name(name).number(number).type(TypesSteps.VIDEO).build();
        unit.addStep(step);
        session.save(step);
    }
    public void addMDStep(String name, String mdHref, Long number, Long numberUnit){
        Session session = entityManager.unwrap(Session.class);
        List<Units> units = session.createQuery("select unit from Units unit where unit.number=:number",
                Units.class).setParameter("number", numberUnit).getResultList();
        if(units.size() == 0){
            throw new Failed("Bad unit number");
        }
        Units unit = units.get(0);
        Steps step = Steps.builder().mdFile(mdHref).name(name).type(TypesSteps.MDFILE).number(number).build();
        unit.addStep(step);
        session.save(step);
    }
    public void addQuestionStep(String name, Long number, Long numberUnit, String fileQuestion){
        Session session = entityManager.unwrap(Session.class);
        List<Units> units = session.createQuery("select unit from Units unit where unit.number=:number",
                Units.class).setParameter("number", numberUnit).getResultList();
        if(units.size() == 0){
            throw new Failed("Bad unit number");
        }
        Units unit = units.get(0);
        Steps step = Steps.builder().question(fileQuestion).name(name).type(TypesSteps.QUESTIONS).number(number).build();
        unit.addStep(step);
        session.save(step);
    }
    public List<Steps> getStepByNumberAndNumberUnit(Long number, Long numberUnit){
        Session session = entityManager.unwrap(Session.class);
        List<Units> units = session.createQuery("select unit from Units unit where unit.number=:number",
                Units.class).setParameter("number", numberUnit).getResultList();
        if(numberUnit == 0){
            throw new Failed("Bad step");
        }
        return session.createQuery("select step from Steps step where " +
                        "step.number=:number and step.unit =:unit", Steps.class)
                .setParameter("number", number).setParameter("unit", units.get(0)).getResultList();
    }
}
