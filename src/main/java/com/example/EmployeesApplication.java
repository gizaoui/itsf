package com.example;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.SwaggerCodeGenExample.model.ContractDto;
import com.example.SwaggerCodeGenExample.model.EmployeeDto;
import com.example.SwaggerCodeGenExample.model.ParentDto;
import com.example.SwaggerCodeGenExample.model.ServiceDto;
import com.example.service.EmployeeService;
import com.example.service.ServiceService;

@SpringBootApplication
public class EmployeesApplication {

   public static void main(String[] args) {
      SpringApplication.run(EmployeesApplication.class, args);
   }

   @Bean
   public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
      return args -> {
         Start bean = ctx.getBean(Start.class);
         bean.doSomething();
      };
   }

   @Component
   private static class Start {

      @Autowired
      ServiceService serviceService;

      @Autowired
      private EmployeeService employeeService;

      public void doSomething() {
         /**
          * Services
          */

         ServiceDto serviceDto1 = new ServiceDto();
         serviceDto1.setName("service1");
         serviceDto1.setParent(null);

         ServiceDto serviceDto2 = new ServiceDto();
         serviceDto2.setName("service2");
         serviceDto2.setParent("service1");

         ServiceDto serviceDto3 = new ServiceDto();
         serviceDto3.setName("service3");
         serviceDto3.setParent(null);

         serviceService.createService(Arrays.asList(serviceDto1, serviceDto2, serviceDto3));

         /**
          * Employees
          */

         EmployeeDto employeeDto1 = new EmployeeDto();
         employeeDto1.setFirstname("John");
         employeeDto1.setLastname("Daniel");
         employeeDto1.setManager(Boolean.TRUE);
         employeeDto1.setBirthdate("1999-07-01");

         ContractDto contractDto1 = new ContractDto();
         contractDto1.setDuration(900);
         contractDto1.setSalary(new BigDecimal("12021.51"));
         contractDto1.setSignature("2022-05-01");
         employeeDto1.addContractItem(contractDto1);

         ServiceDto empServiceDto1 = new ServiceDto();
         empServiceDto1.setName("service1");
         employeeDto1.addServiceItem(empServiceDto1);

         EmployeeDto employeeDto2 = new EmployeeDto();
         employeeDto2.setFirstname("Jack");
         employeeDto2.setLastname("Doom");
         employeeDto2.setManager(Boolean.FALSE);
         employeeDto2.setBirthdate("1980-05-20");

         ParentDto parentDto1 = new ParentDto();
         parentDto1.setFirstname("John");
         parentDto1.setLastname("Daniel");
         parentDto1.setFamilyTies("bother");
         employeeDto1.addParentItem(parentDto1);

         ContractDto contractDto2 = new ContractDto();
         contractDto2.setDuration(300);
         contractDto2.setSalary(new BigDecimal("10223.21"));
         contractDto2.setSignature("2002-07-01");
         employeeDto2.addContractItem(contractDto2);

         ServiceDto empServiceDto2 = new ServiceDto();
         empServiceDto2.setName("service2");
         employeeDto2.addServiceItem(empServiceDto2);

         employeeService.createEmployee(Arrays.asList(employeeDto1, employeeDto2));
      }
   }

}