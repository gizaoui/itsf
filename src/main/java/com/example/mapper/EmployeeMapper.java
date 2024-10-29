package com.example.mapper;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.SwaggerCodeGenExample.model.ContractDto;
import com.example.SwaggerCodeGenExample.model.EmployeeDto;
import com.example.SwaggerCodeGenExample.model.ParentDto;
import com.example.SwaggerCodeGenExample.model.ServiceDto;
import com.example.entity.ContractEntity;
import com.example.entity.EmployeeEntity;
import com.example.entity.ParentEntity;
import com.example.entity.ServiceEntity;
import com.example.exception.ServiceNotFoundException;
import com.example.service.ServiceService;

public class EmployeeMapper {

   private static Logger logger = LoggerFactory.getLogger(EmployeeMapper.class);

   private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

   public static EmployeeDto mapToEmployeeDto(EmployeeEntity employee) {
      EmployeeDto employeeDto = new EmployeeDto();
      employeeDto.setFirstname(employee.getFirstname());
      employeeDto.setLastname(employee.getLastname());
      employeeDto.setBirthdate(dateFormat.format(employee.getBirthdate()));
      employeeDto.setManager(employee.isManager());

      employee.getParent().stream().collect(Collectors.toCollection(ArrayList::new)).forEach(e -> {
         employeeDto.addParentItem(EmployeeMapper.mapToParentDto(e));
      });

      employee.getContract().stream().collect(Collectors.toCollection(ArrayList::new)).forEach(c -> {
         employeeDto.addContractItem(EmployeeMapper.mapToContractDto(c));
      });

      employee.getService().stream().collect(Collectors.toCollection(ArrayList::new)).forEach(c -> {
         employeeDto.addServiceItem(EmployeeMapper.mapToServiceDto(c));
      });

      return employeeDto;
   }

   public static ParentDto mapToParentDto(ParentEntity parent) {
      ParentDto parentDto = new ParentDto();
      parentDto.setFirstname(parent.getParent_firstname());
      parentDto.setLastname(parent.getParent_lastname());
      parentDto.setFamilyTies(parent.getFamily_ties());
      return parentDto;
   }

   public static ContractDto mapToContractDto(ContractEntity contract) {
      ContractDto contractDto = new ContractDto();
      contractDto.setSignature(dateFormat.format(contract.getSignature()));
      contractDto.setDuration(contract.getDuration());
      contractDto.setSalary(contract.getSalary());
      return contractDto;
   }

   public static ServiceDto mapToServiceDto(ServiceEntity service) {
      ServiceDto serviceDto = new ServiceDto();
      serviceDto.setName(service.getName());
      serviceDto.setParent(service.getParent());
      return serviceDto;
   }

   public static EmployeeEntity mapToEmployee(EmployeeDto employeeDto, ServiceService serviceService) {

      EmployeeEntity employeeEntity = new EmployeeEntity(employeeDto.getFirstname(), employeeDto.getLastname(),
            Date.valueOf(employeeDto.getBirthdate().substring(0, 10)), employeeDto.getManager());

      if (employeeDto.getParent() != null && !employeeDto.getParent().isEmpty()) {
         Set<ParentEntity> parentEntities = new HashSet<ParentEntity>();
         employeeDto.getParent().forEach(p -> {
            parentEntities.add(new ParentEntity(p.getFirstname(), p.getLastname(), p.getFamilyTies(), employeeEntity));
         });
         employeeEntity.setParent(parentEntities);
      }

      if (employeeDto.getContract() != null && !employeeDto.getContract().isEmpty()) {
         Set<ContractEntity> contractEntities = new HashSet<ContractEntity>();
         employeeDto.getContract().forEach(c -> {
            contractEntities.add(new ContractEntity(Date.valueOf(c.getSignature().substring(0, 10)), c.getDuration(),
                  c.getSalary(), employeeEntity));
         });
         employeeEntity.setContract(contractEntities);
      }

      if (employeeDto.getService() != null && !employeeDto.getService().isEmpty()) {
         Set<ServiceEntity> serviceEntities = new HashSet<ServiceEntity>();
         employeeDto.getService().forEach(s -> {
            try {
               ServiceDto serviceDto = serviceService.getServiceByName(s.getName());
               serviceEntities.add(ServiceMapper.mapToService(serviceDto));

            } catch(ServiceNotFoundException ex) {
               logger.warn("Service '" + employeeDto.getService() + "' not found");
            }
         });
         employeeEntity.setService(serviceEntities);
      }

      return employeeEntity;
   }

}
