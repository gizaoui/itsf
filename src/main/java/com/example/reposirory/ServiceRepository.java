package com.example.reposirory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, String> {

}
