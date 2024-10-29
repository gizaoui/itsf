package com.example.reposirory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.ContractEntity;
import com.example.entity.ContractId;

public interface ContractRepository extends JpaRepository<ContractEntity, ContractId> {

}
