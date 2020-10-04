package com.spring.controller;

import com.spring.entities.Car;
import com.spring.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarRepo repo;

    @GetMapping("/{id}")
    @Cacheable("cars")
    public Optional<Car> getCar(@PathVariable("id") long id){
        System.out.println("*************** FIRST QUERY ********************");
        repo.findById(id);
        System.out.println("*************** SECOND QUERY ********************");
        return repo.findById(id);
    }

    @PostMapping("/add")
    @Caching(evict = {@CacheEvict(value = "cars", allEntries = true)})
    public Car addCar(@RequestBody Car car){
        System.out.println(car);
        repo.save(car);
        return car;
    }

    @GetMapping("/list")
    @Cacheable("cars")
    public List<Car> listCars (){
        System.out.println("*************** FIRST QUERY ********************");
        repo.findAll();
        System.out.println("*************** SECOND QUERY ********************");
        return repo.findAll();
    }

    @PutMapping("/update/{id}")
    @Caching(evict = {@CacheEvict(value = "cars", allEntries = true)})
    public Car updateCar(@PathVariable("id") Long id, @RequestBody Car car) throws Exception {
        Optional<Car> ecarOp = repo.findById(id);
        if(!ecarOp.isPresent()){
            throw new Exception("Car not found");
        }
        Car ecar = ecarOp.get();
        ecar.setEngine(car.getEngine());
        ecar.setName(car.getName());
        ecar.setTyre(car.getTyre());
        repo.save(ecar);
        return ecar;
    }

    @DeleteMapping("/delete/{id}")
    @Caching(evict = {@CacheEvict(value = "cars", allEntries = true)})
    public String deleteCar (@PathVariable("id") long id){
        repo.deleteById(id);
        return "deleted";
    }
}
