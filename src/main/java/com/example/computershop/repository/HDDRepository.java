package com.example.computershop.repository;

import com.example.computershop.model.HDD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HDDRepository extends JpaRepository<HDD, Integer> {

}
