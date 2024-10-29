package com.example.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "service")
public class ServiceEntity {

   @Id
   @Column(name = "name")
   private String name;

   @Column(name = "parent")
   private String parent;

   @ManyToMany(mappedBy = "service", fetch = FetchType.LAZY)
   @JsonIgnore
   private Set<EmployeeEntity> employee;

   public ServiceEntity(String name, String parent) {
      super();
      this.name = name;
      this.parent = parent;
   }

   public ServiceEntity() {
      super();
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getParent() {
      return parent;
   }

   public void setParent(String parent) {
      this.parent = parent;
   }

   public Set<EmployeeEntity> getEmployee() {
      return employee;
   }

   public void setEmployee(Set<EmployeeEntity> employee) {
      this.employee = employee;
   }

}
