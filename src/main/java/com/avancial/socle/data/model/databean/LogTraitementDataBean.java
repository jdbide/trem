package com.avancial.socle.data.model.databean;

import java.util.Date;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Named
@Entity
@Table(name = "socle_log_traitement")
public class LogTraitementDataBean extends AbstractDataBean {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long              idLogTraitement;
   @Temporal(TemporalType.TIMESTAMP)
   private Date              dateDebutLogTraitement;
   @Temporal(TemporalType.TIMESTAMP)
   private Date              dateFinLogTraitement;
   private String            libelleLogTraitement;
   private String            libelleUserLogTraitement;
   private String            messageTraitement;
   private String            exceptionTraitement;

   public Long getIdLogTraitement() {
      return this.idLogTraitement;
   }

   public void setIdLogTraitement(Long idLogTraitement) {
      this.idLogTraitement = idLogTraitement;
   }

   public Date getDateDebutLogTraitement() {
      return this.dateDebutLogTraitement;
   }

   public void setDateDebutLogTraitement(Date dateDebutLogTraitement) {
      this.dateDebutLogTraitement = dateDebutLogTraitement;
   }

   public Date getDateFinLogTraitement() {
      return this.dateFinLogTraitement;
   }

   public void setDateFinLogTraitement(Date dateFinLogTraitement) {
      this.dateFinLogTraitement = dateFinLogTraitement;
   }

   public String getLibelleLogTraitement() {
      return this.libelleLogTraitement;
   }

   public void setLibelleLogTraitement(String libelleLogTraitement) {
      this.libelleLogTraitement = libelleLogTraitement;
   }

   public String getLibelleUserLogTraitement() {
      return this.libelleUserLogTraitement;
   }

   public void setLibelleUserLogTraitement(String libelleUserLogTraitement) {
      this.libelleUserLogTraitement = libelleUserLogTraitement;
   }

   public String getMessageTraitement() {
      return this.messageTraitement;
   }

   public void setMessageTraitement(String messageTraitement) {
      this.messageTraitement = messageTraitement;
   }

   public String getExceptionTraitement() {
      return this.exceptionTraitement;
   }

   public void setExceptionTraitement(String exceptionTraitement) {
      this.exceptionTraitement = exceptionTraitement;
   }
}
