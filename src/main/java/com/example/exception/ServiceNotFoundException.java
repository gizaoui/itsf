package com.example.exception;

public class ServiceNotFoundException extends Exception {

   private static final long serialVersionUID = 1L;

   public ServiceNotFoundException() {
   }

   public ServiceNotFoundException(String message) {
      super(message);
   }

}
