package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.avancial.app.data.databean.CompagnieEntity;

@Entity
@Table(name = "tremas_motrice_ref_tosp")
@NamedQueries({ @NamedQuery(name = "MotriceRefTosp.getAll", query = "SELECT t FROM MotriceRefTospEntity t"),
      @NamedQuery(name = "MotriceRefTosp.getUnique", query = "SELECT t FROM MotriceRefTospEntity t where t.codeMotriceRefTosp = :codeTosp and t.compagnie = :compagnie"), })
public class MotriceRefTospEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long            idMotriceRefTosp;
   
   @Column(length = 50, nullable = false)
   private String          codeMotriceRefTosp;

   @ManyToOne
   @JoinColumn(name = "idCompagnie")
   private CompagnieEntity compagnie;

   public Long getIdMotriceRefTosp() {
      return this.idMotriceRefTosp;
   }

   public void setIdMotriceRefTosp(Long idMotriceRefTosp) {
      this.idMotriceRefTosp = idMotriceRefTosp;
   }

   public String getCodeMotriceRefTosp() {
      return this.codeMotriceRefTosp;
   }

   public void setCodeMotriceRefTosp(String codeMotriceRefTosp) {
      this.codeMotriceRefTosp = codeMotriceRefTosp;
   }

   public CompagnieEntity getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(CompagnieEntity compagnie) {
      this.compagnie = compagnie;
   }

}
