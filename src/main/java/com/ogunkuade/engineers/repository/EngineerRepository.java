package com.ogunkuade.engineers.repository;


import com.ogunkuade.engineers.entity.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EngineerRepository extends JpaRepository<Engineer, Long> {

    Boolean existsByName(String name);

}
