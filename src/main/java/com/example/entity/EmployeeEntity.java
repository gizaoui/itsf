package com.example.entity;

import java.sql.Date;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
@IdClass(EmployeeId.class)
public class EmployeeEntity {

   @Id
   @Column(name = "firstname")
   private String firstname;

   @Id
   @Column(name = "lastname")
   private String lastname;

   @Column(name = "birthdate")
   private Date birthdate;

   @Column(name = "manager")
   private boolean manager;

   @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JsonIgnore
   private Set<ParentEntity> parent;

   @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JsonIgnore
   private Set<ContractEntity> contract;

   @ManyToMany(fetch = FetchType.EAGER, cascade = {
         CascadeType.MERGE, CascadeType.PERSIST
   })
   @JoinTable(name = "employee_service", joinColumns = {
         @JoinColumn(name = "firstname", referencedColumnName = "firstname"),
         @JoinColumn(name = "lastname", referencedColumnName = "lastname")
   }, inverseJoinColumns = @JoinColumn(name = "service", referencedColumnName = "name"))
   @JsonManagedReference
   private Set<ServiceEntity> service;

   public EmployeeEntity(String firstname, String lastname, Date birthdate, boolean manager) {
      super();
      this.firstname = firstname;
      this.lastname = lastname;
      this.birthdate = birthdate;
      this.manager = manager;
   }

   public EmployeeEntity() {
      super();
   }

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

   public Date getBirthdate() {
      return birthdate;
   }

   public void setBirthdate(Date birthdate) {
      this.birthdate = birthdate;
   }

   public boolean isManager() {
      return manager;
   }

   public void setManager(boolean manager) {
      this.manager = manager;
   }

   public Set<ParentEntity> getParent() {
      return parent;
   }

   public void setParent(Set<ParentEntity> parent) {
      this.parent = parent;
   }

   public Set<ContractEntity> getContract() {
      return contract;
   }

   public void setContract(Set<ContractEntity> contract) {
      this.contract = contract;
   }

   public Set<ServiceEntity> getService() {
      return service;
   }

   public void setService(Set<ServiceEntity> service) {
      this.service = service;
   }

   @Override
   public String toString() {
      return "EmployeeEntity [firstname=" + firstname + ", lastname=" + lastname + "]";
   }
}
