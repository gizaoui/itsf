package com.example.entity;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeId implements Serializable {

   private static final long serialVersionUID = 1L;

   private String firstname;

   private String lastname;

   public String getFirstname() {
      return firstname;
   }

   public void setFirstname(String firstname) {
      this.firstname = firstname;
   }

   public String getLastname() {
      return lastname;
   }

   public void setLastname(String lastname) {
      this.lastname = lastname;
   }

   @Override
   public int hashCode() {
      return Objects.hash(firstname, lastname);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      EmployeeId other = (EmployeeId) obj;
      return Objects.equals(firstname, other.firstname) && Objects.equals(lastname, other.lastname);
   }

}
