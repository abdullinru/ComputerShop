package com.example.computershop.repository;

import com.example.computershop.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Integer> {
//    Optional<Computer> findComputerById(Integer id);

}
