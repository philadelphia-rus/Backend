package com.philadelphia.api.DAO;

import com.philadelphia.api.Database.Users;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UsersDAO {
    @Autowired
    private EntityManager entityManager;

    public void addUser(String login, String passwordHash, String name) {
        Session session = entityManager.unwrap(Session.class);
        session.save(Users.builder().login(login).password(passwordHash).name(name).xp(0L).money(0L).build());
    }

    public List<Users> getBylogin(String login) {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("select user from Users  user where user.login=:login", Users.class)
                .setParameter("login", login).getResultList();
    }

    public Users getById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Users.class, id);
    }

    public List<Users> getAllUsers() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("select user from Users user", Users.class).getResultList();
    }
}
