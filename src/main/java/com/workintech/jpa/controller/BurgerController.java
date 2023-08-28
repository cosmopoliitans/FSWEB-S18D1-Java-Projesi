package com.workintech.jpa.controller;

import com.workintech.jpa.dao.BurgerDao;
import com.workintech.jpa.entity.BreadType;
import com.workintech.jpa.entity.Burger;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "*")
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
    public Burger getBurgerById(@Positive @PathVariable Integer id){
        return burgerDao.findById(id);
    }
     //[POST]/workintech/burgers => Bir adet burger objesini veritabanına kaydeder.
    @PostMapping("/")
    public Burger createBurger(@Validated @RequestBody Burger burger){
        return burgerDao.save(burger);
    }
     //[PUT]/workintech/burgers/{id} => İlgili id deki burger objesinin değerlerini yeni gelen data ile değiştirir.
    @PutMapping("/{id}")
    public Burger updateBurger(@PathVariable Integer id, @RequestBody Burger newBurger){
        Burger existingBurger = burgerDao.findById(id);
        if (existingBurger != null) {
            existingBurger.setName(newBurger.getName());
            existingBurger.setPrice(newBurger.getPrice());
            existingBurger.setVegan(newBurger.isVegan());
            existingBurger.setBreadType(newBurger.getBreadType());
            existingBurger.setContents(newBurger.getContents());

        } return burgerDao.update(existingBurger);
    }
     //[DELETE]/workintech/burgers/{id} => İlgili id değerindeki burger objesini veritabanından siler.
    @DeleteMapping("/{id}")
    public Burger removeBurger(@PathVariable int id){
        return burgerDao.remove(id);
    }
     //[GET]/workintech/burgers/findByPrice => RequestBody'de price değerini alır ve BurgerDaoImpl sınıfındaki findByPrice metodunu çağırır.
    @GetMapping("/findByPrice/{price}")
    public List<Burger> findByPrice(@PathVariable double price){
        return burgerDao.findByPrice(price);
    }
     //[GET]/workintech/burgers/findByBreadType => RequestBody'de breadType değerini alır ve BurgerDaoImpl sınıfındaki findByBreadType metodunu çağırır.
     @GetMapping("/findByBreadType/{breadType}")
     public List<Burger> findByPrice(@PathVariable String breadType){
         return burgerDao.findByBreadType(String.valueOf(BreadType.WHITEBREAD));//??????
     }


    //[GET]/workintech/burgers/findByContent => RequestBody'de content değerini alır ve BurgerDaoImpl sınıfındaki findByContent metodunu çağırır.
    @GetMapping("/findByContent/{content}")
    public List<Burger> findByContent(@PathVariable String content) {
        return burgerDao.findByContent(content);
    }
}
