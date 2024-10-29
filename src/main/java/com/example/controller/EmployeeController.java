package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.SwaggerCodeGenExample.api.EmployeeApi;
import com.example.SwaggerCodeGenExample.model.EmployeeDto;
import com.example.exception.EmployeeNotFoundException;
import com.example.service.EmployeeService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController implements EmployeeApi {

   @Autowired
   private EmployeeService employeeService;

   @Override
   public ResponseEntity<Void> createEmployee(@Valid List<EmployeeDto> employeeDto) {
      employeeService.createEmployee(employeeDto);
      return new ResponseEntity<>(HttpStatus.CREATED);
   }

   @Override
   public ResponseEntity<EmployeeDto> findEmployeeById(String firstname, String lastname) {
      try {
         EmployeeDto employeeDto = employeeService.getEmployeeByName(firstname, lastname);
         return new ResponseEntity<>(employeeDto, HttpStatus.OK);
      } catch(EmployeeNotFoundException e) {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
   }

}
