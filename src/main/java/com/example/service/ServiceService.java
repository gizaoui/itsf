package com.example.service;

import java.util.List;

import com.example.SwaggerCodeGenExample.model.ServiceDto;
import com.example.exception.ServiceNotFoundException;

public interface ServiceService {

   void createService(List<ServiceDto> serviceDto);

   ServiceDto getServiceByName(String userName) throws ServiceNotFoundException;
}
