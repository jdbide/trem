package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_ref_stop")
@NamedQuery(name = "MotriceRefStop.getAll", query = "SELECT t FROM MotriceRefStopEntity t")
public class MotriceRefStopEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long   idMotriceRefStop;
   @Column(length = 5, nullable = false)
   private String labelStop;

   public Long getIdMotriceRefStop() {
      return this.idMotriceRefStop;
   }

   public void setIdMotriceRefStop(Long idMotriceRefStop) {
      this.idMotriceRefStop = idMotriceRefStop;
   }

   public String getLabelStop() {
      return this.labelStop;
   }

   public void setLabelStop(String labelStop) {
      this.labelStop = labelStop;
   }

}
