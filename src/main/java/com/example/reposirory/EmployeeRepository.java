package com.example.reposirory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.EmployeeEntity;
import com.example.entity.EmployeeId;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, EmployeeId> {
   
}
