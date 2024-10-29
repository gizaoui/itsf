package com.example.reposirory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.ParentEntity;

public interface ParentRepository extends JpaRepository<ParentEntity, String> {

}
