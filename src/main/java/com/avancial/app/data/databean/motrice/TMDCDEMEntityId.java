package com.avancial.app.data.databean.motrice;

import java.io.Serializable;

@SuppressWarnings("unused")
public class TMDCDEMEntityId implements Serializable{
   
   private static final long serialVersionUID = 1L;
   
   private String  CDEM_TRA1_COD_CIE;
   private String  CDEM_TRA1_NUM_TRA1;
   private String  CDEM_TRA1_IND_FER;
   private Double    CDEM_NUM_CONDITION;
   private String  CDEM_LIBS_TYCO_COD;
   private String CDEM_REGI;
   
   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
