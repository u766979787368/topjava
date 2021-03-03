package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User user = em.getReference(User.class, userId);
        meal.setUser(user);
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            Query query = em.createQuery("SELECT meal FROM Meal meal WHERE meal.id=:id", Meal.class);
            Meal meal1 = (Meal) query.setParameter("id", meal.getId()).getSingleResult();
            int id = meal1.getUser().getId();
            if (id == userId) {
                return em.merge(meal);
            } else return null;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("user_id", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal;
        try {
            meal = em.createNamedQuery(Meal.GET, Meal.class)
                    .setParameter("id", id)
                    .setParameter("user_id", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundException(e.getMessage());
        }
        return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .setParameter("user_id", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return em.createNamedQuery(Meal.ALL_BETWEEN_HALF_OPEN, Meal.class)
                .setParameter("user_id", userId)
                .setParameter("startDateTime", startDateTime)
                .setParameter("endDateTime", endDateTime)
                .getResultList();
    }
}