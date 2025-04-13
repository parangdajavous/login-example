package com.example.loginexample.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public User findByUsername(String username) {
        Query query = em.createNativeQuery("select * from user_tb where username = ?", User.class);
        query.setParameter(1, username);
        try {
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void save(String username, String password, String email) {
        Query query = em.createNativeQuery("insert into user_tb(username, password, email, created_at) values(?,?,?,now())");
        query.setParameter(1, username);
        query.setParameter(2, password);
        query.setParameter(3, email);
        query.executeUpdate();
    }
}
