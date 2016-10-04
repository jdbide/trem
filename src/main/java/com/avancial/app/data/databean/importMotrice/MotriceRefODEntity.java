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
@Table(name = "tremas_motrice_ref_od")
@NamedQueries({ @NamedQuery(name = "MotriceRefOD.getAll", query = "SELECT t FROM MotriceRefODEntity t"),
      @NamedQuery(name = "MotriceRefOD.getUnique", query = "SELECT t FROM MotriceRefODEntity t where t.codeGareOrigineMotriceRefOd = :codeGareOrigine "
            + "and t.codeGareDestinationMotriceRefOd = :codeGareDestination and t.compagnie = :compagnie"), })
public class MotriceRefODEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long            idMotriceRefOd;

   @Column(length = 50, nullable = false)
   private String          codeGareOrigineMotriceRefOd;

   @Column(length = 50, nullable = false)
   private String          codeGareDestinationMotriceRefOd;

   @ManyToOne
   @JoinColumn(name = "idCompagnie")
   private CompagnieEntity compagnie;

   public Long getIdMotriceRefOd() {
      return this.idMotriceRefOd;
   }

   public void setIdMotriceRefOd(Long idMotriceRefOd) {
      this.idMotriceRefOd = idMotriceRefOd;
   }

   public String getCodeGareOrigineMotriceRefOd() {
      return this.codeGareOrigineMotriceRefOd;
   }

   public void setCodeGareOrigineMotriceRefOd(String codeGareOrigineMotriceRefOd) {
      this.codeGareOrigineMotriceRefOd = codeGareOrigineMotriceRefOd;
   }

   public String getCodeGareDestinationMotriceRefOd() {
      return this.codeGareDestinationMotriceRefOd;
   }

   public void setCodeGareDestinationMotriceRefOd(String codeGareDestinationMotriceRefOd) {
      this.codeGareDestinationMotriceRefOd = codeGareDestinationMotriceRefOd;
   }

   public CompagnieEntity getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(CompagnieEntity compagnie) {
      this.compagnie = compagnie;
   }

}
