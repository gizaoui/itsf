package com.example.mapper;

import com.example.SwaggerCodeGenExample.model.ServiceDto;
import com.example.entity.ServiceEntity;

public class ServiceMapper {

   public static ServiceDto mapToServiceDto(ServiceEntity service) {
      ServiceDto serviceDto = new ServiceDto();
      serviceDto.setName(service.getName());
      serviceDto.setParent(service.getParent());
      return serviceDto;
   }

   public static ServiceEntity mapToService(ServiceDto employeeDto) {
      return new ServiceEntity(employeeDto.getName(), employeeDto.getParent());
   }
}
