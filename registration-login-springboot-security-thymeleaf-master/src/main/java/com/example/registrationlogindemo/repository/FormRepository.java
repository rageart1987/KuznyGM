package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FormRepository extends JpaRepository<Form, Integer> {

    List<Form> findAllByOrderByDateDesc();

//    @Query("select * from form a where a.date <= :endDate and a.date = :endDate ")
//    List<Form> findAllByFilter();
//
}