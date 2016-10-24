package com.avancial.app.data.dto;

import java.util.ArrayList;
import java.util.List;

/*
* 
* @author jeandaniel.bide
*
                     */
public class ErrorListDto {

   private List<String> parsing;
   private List<String> validation;
   private List<String> extraction;
   private String erreurFatale;;

   public ErrorListDto() {
      parsing = new ArrayList<>();
      validation = new ArrayList<>();
      extraction = new ArrayList<>();
      erreurFatale = "";
   }

   public List<String> getParsing() {
      return parsing;
   }

   public List<String> getValidation() {
      return validation;
   }


   public List<String> getExtraction() {
      return extraction;
   }

   public String getErreurFatale() {
      return erreurFatale;
   }

   public void setErreurFatale(String erreurFatale) {
      this.erreurFatale = erreurFatale;
   }


}
