package com.spring.repo;

import com.spring.entities.Car;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
}
