package com.example.entity;

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
@Table(name = "parent")
@IdClass(ParentId.class)
public class ParentEntity {

   @Id
   @Column(name = "parent_firstname", insertable = false, updatable = false)
   private String parent_firstname;

   @Id
   @Column(name = "parent_lastname", insertable = false, updatable = false)
   private String parent_lastname;

   @Id
   @Column(name = "family_ties")
   private String family_ties;

   @ManyToOne(cascade = CascadeType.PERSIST)
   @JoinColumns({
         @JoinColumn(name = "firstname", referencedColumnName = "firstname"),
         @JoinColumn(name = "lastname", referencedColumnName = "lastname")
   })
   @JsonManagedReference
   private EmployeeEntity employee;

   public ParentEntity(String parent_firstname, String parent_lastname, String family_ties, EmployeeEntity employee) {
      super();
      this.parent_firstname = parent_firstname;
      this.parent_lastname = parent_lastname;
      this.family_ties = family_ties;
      this.employee = employee;
   }

   public ParentEntity() {
      super();
   }

   public String getParent_firstname() {
      return parent_firstname;
   }

   public void setParent_firstname(String parent_firstname) {
      this.parent_firstname = parent_firstname;
   }

   public String getParent_lastname() {
      return parent_lastname;
   }

   public void setParent_lastname(String parent_lastname) {
      this.parent_lastname = parent_lastname;
   }

   public EmployeeEntity getEmployee() {
      return employee;
   }

   public void setEmployee(EmployeeEntity employee) {
      this.employee = employee;
   }

   public String getFamily_ties() {
      return family_ties;
   }

   public void setFamily_ties(String family_ties) {
      this.family_ties = family_ties;
   }

}
