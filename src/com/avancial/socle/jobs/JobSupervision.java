package com.avancial.socle.jobs;

public class JobSupervision {

   
   private String jobNom ;
   private String lastExec ; 
   private String nextExec ;
   private String etat ; 
   private String jobPlanifType ;
   public JobSupervision() { 
      this.etat="innactif" ;
   }

   public String getJobNom() {
      return this.jobNom;
   }

   public void setJobNom(String jobNom) {
      this.jobNom = jobNom;
   }

   public String getLastExec() {
      return this.lastExec;
   }

   public void setLastExec(String lastExec) {
      this.lastExec = lastExec;
   }

   public String getNextExec() {
      return this.nextExec;
   }

   public void setNextExec(String nextExec) {
      this.nextExec = nextExec;
   }

   public String getEtat() {
      return this.etat;
   }

   public void setEtat(String etat) {
      this.etat = etat;
   }

   public String getJobPlanifType() {
      return this.jobPlanifType;
   }

   public void setJobPlanifType(String jobPlanifType) {
      this.jobPlanifType = jobPlanifType;
   }
   
}
