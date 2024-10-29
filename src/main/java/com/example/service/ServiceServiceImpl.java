package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SwaggerCodeGenExample.model.ServiceDto;
import com.example.entity.ServiceEntity;
import com.example.exception.ServiceNotFoundException;
import com.example.mapper.ServiceMapper;
import com.example.reposirory.ServiceRepository;

@Service
public class ServiceServiceImpl implements ServiceService {

   @Autowired
   ServiceRepository serviceRepository;

   @Override
   public void createService(List<ServiceDto> serviceDto) {
      serviceRepository
            .saveAll(serviceDto.stream().map((dto) -> ServiceMapper.mapToService(dto)).collect(Collectors.toList()));
   }

   @Override
   public ServiceDto getServiceByName(String serviceName) throws ServiceNotFoundException {
      ServiceEntity service = serviceRepository.findById(serviceName)
            .orElseThrow(() -> new ServiceNotFoundException("Service with name '" + serviceName + "' not found."));
      return ServiceMapper.mapToServiceDto(service);
   }

}
