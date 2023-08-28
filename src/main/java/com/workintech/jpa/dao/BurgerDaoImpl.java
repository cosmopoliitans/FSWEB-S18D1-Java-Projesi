package com.workintech.jpa.dao;

import com.workintech.jpa.entity.Burger;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BurgerDaoImpl implements BurgerDao {
    private EntityManager entityManager;

    @Autowired
    public BurgerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Burger burger) {
        entityManager.persist(burger);
    }

    @Override
    public Burger findById(Integer id) {
        return entityManager.find(Burger.class, id);
    }

    @Override
    public List<Burger> findAll() {
        return entityManager.createQuery("SELECT b FROM Burger b", Burger.class).getResultList();
    }

    @Override
    public List<Burger> findByPrice(double price) {
        return entityManager.createQuery("SELECT b FROM Burger b WHERE b.price >= :price", Burger.class)
                .setParameter("price", price)
                .getResultList();
    }

    @Override
    public List<Burger> findByBreadType(String breadType) {
        return entityManager.createQuery("SELECT b FROM Burger b WHERE b.breadType = :breadType ORDER BY b.name", Burger.class)
                .setParameter("breadType", breadType)
                .getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        return entityManager.createQuery("SELECT b FROM Burger b WHERE b.contents LIKE :content", Burger.class)
                .setParameter("content", "%" + content + "%")
                .getResultList();
    }

    @Override
    public void update(Burger burger) {
        entityManager.merge(burger);
    }

    @Override
    public void remove(Integer id) {
        Burger burger = entityManager.find(Burger.class, id);
        if (burger != null) {
            entityManager.remove(burger);
        }
    }
}
