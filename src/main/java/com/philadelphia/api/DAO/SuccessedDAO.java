package com.philadelphia.api.DAO;

import com.philadelphia.api.Database.Steps;
import com.philadelphia.api.Database.Successed;
import com.philadelphia.api.Database.Users;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class SuccessedDAO {
    @Autowired
    private EntityManager entityManager;
    public void saveSuccessed(Successed successed){
        Session session = entityManager.unwrap(Session.class);
        session.save(successed);
    }
    public List<Successed> getByUserAndStep(Long userId, Long stepId){
        Session session = entityManager.unwrap(Session.class);
        Users user = session.get(Users.class, userId);
        Steps steps = session.get(Steps.class, stepId);
        return session.createQuery("select successed from Successed successed where " +
                "successed.steps = :steps and successed.user = :user", Successed.class)
                .setParameter("steps", steps).setParameter("user", user).getResultList();
    }
    public List<Successed> getByUserIdOrder(Long userId){
        Session session = entityManager.unwrap(Session.class);
        Users users = session.get(Users.class, userId);
        return session.createQuery("select successed from Successed successed where successed.user=:user " +
                "order by successed.units.number, successed.steps.number", Successed.class)
                .setParameter("user", users).getResultList();
    }
}
