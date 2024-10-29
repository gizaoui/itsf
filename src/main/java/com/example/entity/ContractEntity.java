package com.example.entity;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contract")
@IdClass(ContractId.class)
public class ContractEntity {

   @Id
   @Column(name = "signature")
   private Date signature;

   @Id
   @ManyToOne(cascade = CascadeType.PERSIST)
   @JoinColumns({
         @JoinColumn(name = "firstname", referencedColumnName = "firstname"),
         @JoinColumn(name = "lastname", referencedColumnName = "lastname")
   })
   @JsonManagedReference
   private EmployeeEntity employee;

   @Column(name = "duration")
   private Integer duration;

   @Column(name = "salary")
   private BigDecimal salary;

   public ContractEntity() {
      super();
   }

   public ContractEntity(Date signature, Integer duration, BigDecimal salary, EmployeeEntity employee) {
      super();
      this.signature = signature;
      this.duration = duration;
      this.salary = salary;
      this.employee = employee;
   }

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

   public Integer getDuration() {
      return duration;
   }

   public void setDuration(Integer duration) {
      this.duration = duration;
   }

   public BigDecimal getSalary() {
      return salary;
   }

   public void setSalary(BigDecimal salary) {
      this.salary = salary;
   }

}
