package com.avancial.socle.data.model.databean;

import java.util.Date;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Named
@Entity
@Table(name = "socle_log_traitement_detail")
public class LogTraitementDetailDataBean extends AbstractDataBean {

   private static final long     serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long                  idLogTraitementDetail;

   @OneToOne
   @JoinColumn(name = "idLogTraitement", nullable = false)
   private LogTraitementDataBean logTraitementDataBean;

   @Temporal(TemporalType.TIMESTAMP)
   private Date                  dateLogTraitementDetail;
   private String                messageTraitementDetail;
   private String                exceptionTraitementDetail;

   public Long getIdLogTraitementDetail() {
      return this.idLogTraitementDetail;
   }

   public void setIdLogTraitementDetail(Long idLogTraitementDetail) {
      this.idLogTraitementDetail = idLogTraitementDetail;
   }

   public Date getDateLogTraitementDetail() {
      return this.dateLogTraitementDetail;
   }

   public void setDateLogTraitementDetail(Date dateLogTraitementDetail) {
      this.dateLogTraitementDetail = dateLogTraitementDetail;
   }

   public String getMessageTraitementDetail() {
      return this.messageTraitementDetail;
   }

   public void setMessageTraitementDetail(String messageTraitementDetail) {
      this.messageTraitementDetail = messageTraitementDetail;
   }

   public String getExceptionTraitementDetail() {
      return this.exceptionTraitementDetail;
   }

   public void setExceptionTraitementDetail(String exceptionTraitementDetail) {
      this.exceptionTraitementDetail = exceptionTraitementDetail;
   }

   public LogTraitementDataBean getLogTraitementDataBean() {
      return this.logTraitementDataBean;
   }

   public void setLogTraitementDataBean(LogTraitementDataBean logTraitementDataBean) {
      this.logTraitementDataBean = logTraitementDataBean;
   }

}
