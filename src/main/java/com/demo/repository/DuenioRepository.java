package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Duenio;
@Repository
public interface DuenioRepository extends JpaRepository<Duenio, Long> {

}
