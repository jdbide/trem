package com.avancial.socle.model.managedbean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.avancial.socle.resources.constants.SOCLE_navigation;

/**
 * Managed Bean de gestion de la page liste utilisateurs public
 * 
 * @author guillaume.bouziou
 * 
 */
@Named("menu")
@SessionScoped
public class SocleMenuManagedBean implements Serializable {
   private static final long serialVersionUID = 1L;

   public static String goAccueil() {
      return SOCLE_navigation.NAVIGATION_ACCUEIL.toString();
   }

   public static String goMdpOublie() {
      return SOCLE_navigation.NAVIGATION_MDPOUBLIE.toString();
   }

   public static String goRole() {
      return SOCLE_navigation.NAVIGATION_ROLE.toString();
   }

   public static String goUser() {
      return SOCLE_navigation.NAVIGATION_USER.toString();
   }

   public static String goJob() {
      return SOCLE_navigation.NAVIGATION_JOB.toString();
   }

   public static String goJobPlanif() {
      return SOCLE_navigation.NAVIGATION_JOB_PLANIF.toString();
   }

   public static String goRobot() {
      return SOCLE_navigation.NAVIGATION_ROBOT.toString();
   }

   public static String goJobSuper() {
      return SOCLE_navigation.NAVIGATION_JOB_SUPERVISION.toString();
   }
   public static String goItem2Role() {
       return SOCLE_navigation.NAVIGATION_ITEM2_ROLE.toString();
    }
}
