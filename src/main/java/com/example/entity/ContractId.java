package com.example.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class ContractId implements Serializable {

   private static final long serialVersionUID = 1L;

   private Date signature;

   private EmployeeEntity employee;

   public Date getSignature() {
      return signature;
   }

   public void setSignature(Date signature) {
      this.signature = signature;
   }

   public EmployeeEntity getEmployee() {
      return employee;
   }

   public void setEmployee(EmployeeEntity employee) {
      this.employee = employee;
   }

   @Override
   public int hashCode() {
      return Objects.hash(employee, signature);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      ContractId other = (ContractId) obj;
      return Objects.equals(employee.getFirstname(), other.employee.getFirstname())
            && Objects.equals(employee.getLastname(), other.employee.getLastname())
            && Objects.equals(signature, other.signature);
   }

}
