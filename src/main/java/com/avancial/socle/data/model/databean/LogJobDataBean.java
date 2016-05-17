package com.avancial.socle.data.model.databean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "socle_log_job")
public class LogJobDataBean extends AbstractDataBean {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long              idLogJob;
   @Temporal(TemporalType.TIMESTAMP)
   private Date              dateDebutLogJob;
   @Temporal(TemporalType.TIMESTAMP)
   private Date              dateFinLogJob;
   private String            libelleJobLogJob;
   private Boolean           etatOkLogJob;
   @OneToOne(cascade = { CascadeType.MERGE })
   @JoinColumn(name = "idJobPlanif", nullable = false)
   private JobPlanifDataBean jobPlanif;

   private String            libelleUserLogJob;

   public Long getIdLogJob() {
      return this.idLogJob;
   }

   public void setIdLogJob(Long idLogJob) {
      this.idLogJob = idLogJob;
   }

   public Date getDateDebutLogJob() {
      return this.dateDebutLogJob;
   }

   public void setDateDebutLogJob(Date dateDebutLogJob) {
      this.dateDebutLogJob = dateDebutLogJob;
   }

   public Boolean getEtatOkLogJob() {
      return this.etatOkLogJob;
   }

   public void setEtatOkLogJob(Boolean etatOkLogJob) {
      this.etatOkLogJob = etatOkLogJob;
   }

   public Date getDateFinLogJob() {
      return this.dateFinLogJob;
   }

   public void setDateFinLogJob(Date dateFinLogJob) {
      this.dateFinLogJob = dateFinLogJob;
   }

   public JobPlanifDataBean getJobPlanif() {
      return this.jobPlanif;
   }

   public void setJobPlanif(JobPlanifDataBean jobPlanif) {
      this.jobPlanif = jobPlanif;
   }

   /**
    * @return the libelleUserLogJob
    */
   public String getLibelleUserLogJob() {
      return this.libelleUserLogJob;
   }

   /**
    * @param libelleUserLogJob
    *           the libelleUserLogJob to set
    */
   public void setLibelleUserLogJob(String libelleUserLogJob) {
      this.libelleUserLogJob = libelleUserLogJob;
   }

   /**
    * @return the libelleJobLogJob
    */
   public String getLibelleJobLogJob() {
      return libelleJobLogJob;
   }

   /**
    * @param libelleJobLogJob
    *           the libelleJobLogJob to set
    */
   public void setLibelleJobLogJob(String libelleJobLogJob) {
      this.libelleJobLogJob = libelleJobLogJob;
   }

}
