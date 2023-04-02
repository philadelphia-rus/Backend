package com.philadelphia.api.DAO;

import com.philadelphia.api.Database.Units;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UnitDao {
    @Autowired
    private EntityManager entityManager;
    public void addUnit(String name, Long number){
        Session session = entityManager.unwrap(Session.class);
        session.save(Units.builder().name(name).number(number).build());
    }
    public List<Units> getAll(){
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("SELECT unit from Units unit", Units.class).getResultList();
    }
    public List<Units> getUnitByNumber(Long number){
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("SELECT unit from  Units unit where unit.number=:number", Units.class)
                .setParameter("number", number).getResultList();
    }
}
