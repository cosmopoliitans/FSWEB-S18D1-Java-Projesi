package com.workintech.jpa.dao;

import com.workintech.jpa.entity.Burger;

import java.util.List;

public interface BurgerDao {
    Burger save(Burger burger);
    Burger findById(Integer id);
    List<Burger> findAll();
    List<Burger> findByPrice(double price);
    List<Burger> findByBreadType(String breadType);
    List<Burger> findByContent(String content);
    Burger update(Burger burger);
    Burger remove(int id);

}
