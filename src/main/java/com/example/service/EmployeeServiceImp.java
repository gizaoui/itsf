package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SwaggerCodeGenExample.model.EmployeeDto;
import com.example.entity.EmployeeEntity;
import com.example.entity.EmployeeId;
import com.example.exception.EmployeeNotFoundException;
import com.example.mapper.EmployeeMapper;
import com.example.reposirory.EmployeeRepository;

@Service
public class EmployeeServiceImp implements EmployeeService {

   @Autowired
   EmployeeRepository employeeRepository;

   @Autowired
   ServiceService serviceService;

   @Override
   public void createEmployee(List<EmployeeDto> employeeDto) {
      employeeRepository.saveAll(employeeDto.stream().map((dto) -> EmployeeMapper.mapToEmployee(dto, serviceService))
            .collect(Collectors.toList()));
   }

   @Override
   public EmployeeDto getEmployeeByName(String firstName, String lastName) throws EmployeeNotFoundException {

      EmployeeId employeeId = new EmployeeId();
      employeeId.setFirstname(firstName);
      employeeId.setLastname(lastName);
      EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EmployeeNotFoundException(
                  "Employee with name '" + firstName + " - " + lastName + "' not found."));
      return EmployeeMapper.mapToEmployeeDto(employeeEntity);
   }

}
