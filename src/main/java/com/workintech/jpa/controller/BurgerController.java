package com.workintech.jpa.controller;

import com.workintech.jpa.dao.BurgerDao;
import com.workintech.jpa.entity.BreadType;
import com.workintech.jpa.entity.Burger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/burgers")
public class BurgerController
{
   private BurgerDao burgerDao;
    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }


     //[GET]/workintech/burgers => tüm burger listini dönmeli.
    @GetMapping("/")
    public List<Burger> getAllBurgers(){
        return burgerDao.findAll();
    }
     //[GET]/workintech/burgers/{id} => İlgili id deki burger objesini dönmeli.
    @GetMapping("/{id}")
    public Burger getBurgerById(@PathVariable Integer id){
        return burgerDao.findById(id);
    }
     //[POST]/workintech/burgers => Bir adet burger objesini veritabanına kaydeder.
    @PostMapping("/")
    public void createBurger(@RequestBody Burger burger){
        burgerDao.save(burger);
    }
     //[PUT]/workintech/burgers/{id} => İlgili id deki burger objesinin değerlerini yeni gelen data ile değiştirir.
    @PutMapping("/{id}")
    public void updateBurger(@PathVariable Integer id, @RequestBody Burger newBurger){
        Burger existingBurger = burgerDao.findById(id);
        if (existingBurger != null) {
            existingBurger.setName(newBurger.getName());
            existingBurger.setPrice(newBurger.getPrice());
            existingBurger.setVegan(newBurger.isVegan());
            existingBurger.setBreadType(newBurger.getBreadType());
            existingBurger.setContents(newBurger.getContents());
            burgerDao.update(existingBurger);
        }
    }
     //[DELETE]/workintech/burgers/{id} => İlgili id değerindeki burger objesini veritabanından siler.
    @DeleteMapping("/{id}")
    public void deleteBurger(@PathVariable Integer id){
        burgerDao.remove(id);
    }
     //[GET]/workintech/burgers/findByPrice => RequestBody'de price değerini alır ve BurgerDaoImpl sınıfındaki findByPrice metodunu çağırır.
    @GetMapping("/findByPrice")
    public List<Burger> findByPrice(@RequestParam double price){
        return burgerDao.findByPrice(price);
    }
     //[GET]/workintech/burgers/findByBreadType => RequestBody'de breadType değerini alır ve BurgerDaoImpl sınıfındaki findByBreadType metodunu çağırır.
     @GetMapping("/findByBreadType")
     public List<Burger> findByBreadType(@RequestParam BreadType breadType) {
         return burgerDao.findByBreadType(String.valueOf(breadType));
     }


    //[GET]/workintech/burgers/findByContent => RequestBody'de content değerini alır ve BurgerDaoImpl sınıfındaki findByContent metodunu çağırır.
    @GetMapping("/findByContent")
    public List<Burger> findByContent(@RequestParam String content) {
        return burgerDao.findByContent(content);
    }
}
