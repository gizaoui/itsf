package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.SwaggerCodeGenExample.api.ServiceApi;
import com.example.SwaggerCodeGenExample.model.ServiceDto;
import com.example.exception.ServiceNotFoundException;
import com.example.service.ServiceService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ServiceController implements ServiceApi {

   private final Logger logger = LoggerFactory.getLogger(this.getClass());

   @Autowired
   ServiceService serviceService;

   @Override
   public ResponseEntity<Void> createService(@Valid List<ServiceDto> serviceDto) {
      serviceService.createService(serviceDto);
      return new ResponseEntity<>(HttpStatus.CREATED);
   }

   @Override
   public ResponseEntity<ServiceDto> findServiceById(String serviceName) {
      logger.info(serviceName);
      try {
         ServiceDto serviceDto = serviceService.getServiceByName(serviceName);
         return new ResponseEntity<>(serviceDto, HttpStatus.OK);
      } catch(ServiceNotFoundException ex) {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
   }

}
