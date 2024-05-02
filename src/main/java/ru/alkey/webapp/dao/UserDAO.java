package ru.alkey.webapp.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.alkey.webapp.models.User;

import java.util.List;

@Repository
public class UserDAO {
    private EntityManager entityManager;

    @PersistenceContext
    public void setEm(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void removeUser(Long id) {
        entityManager.remove(entityManager.find(User.class,id));
    }

    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return entityManager.find(User.class,id);
    }

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    @Transactional
    public void updateUser(Long id, User user) {
        User userFromDB = entityManager.find(User.class,id);
        userFromDB.setName(user.getName());
        userFromDB.setAge(user.getAge());
        userFromDB.setEmail(user.getEmail());
        entityManager.merge(userFromDB);
    }
}
