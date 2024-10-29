package com.example.entity;

import java.io.Serializable;
import java.util.Objects;

public class ParentId implements Serializable {

   private static final long serialVersionUID = 1L;

   private String parent_firstname;

   private String parent_lastname;

   private String family_ties;

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

   public String getFamily_ties() {
      return family_ties;
   }

   public void setFamily_ties(String family_ties) {
      this.family_ties = family_ties;
   }

   @Override
   public int hashCode() {
      return Objects.hash(family_ties, parent_firstname, parent_lastname);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      ParentId other = (ParentId) obj;
      return Objects.equals(family_ties, other.family_ties) && Objects.equals(parent_firstname, other.parent_firstname)
            && Objects.equals(parent_lastname, other.parent_lastname);
   }

}
