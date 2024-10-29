package com.example.service;

import java.util.List;

import com.example.SwaggerCodeGenExample.model.EmployeeDto;
import com.example.exception.EmployeeNotFoundException;

public interface EmployeeService {
   void createEmployee(List<EmployeeDto> employeeDto);
   EmployeeDto getEmployeeByName(String firstName, String lastName) throws EmployeeNotFoundException;
}
