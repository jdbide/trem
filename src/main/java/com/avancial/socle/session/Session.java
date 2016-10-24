/**
 * 
 */
package com.avancial.socle.session;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.socle.authentification.model.User;

/**
 * @author bruno.legloahec
 *
 */
@SessionScoped
public class Session implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   /**
    * User connecté
    */
   @Inject
   private User              user;
   
   @Inject
   private  PlanTransport planDeTransportTemp;

   /**
    * Constructeur
    */
   public Session() {
      // Pour l'injection
   }

   public User getUser() {
      return this.user;
   }

   public void setUser(User user) {
      this.user = user;
   }
   

   public PlanTransport getPlanDeTransportTemp() {
      return planDeTransportTemp;
   }

   public void setPlanDeTransportTemp(PlanTransport planDeTransportTemp) {
      this.planDeTransportTemp = planDeTransportTemp;
   }

}
