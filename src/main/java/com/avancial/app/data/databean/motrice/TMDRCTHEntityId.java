package com.avancial.app.data.databean.motrice;

import java.io.Serializable;

@SuppressWarnings("unused")
public class TMDRCTHEntityId implements Serializable{
   
   private static final long serialVersionUID = 1L;
   
   private String RCTH_TRCH_COD_CIE;
   private String RCTH_TRCH_NUM_TRA1;
   private String RCTH_TRCH_IND_FER;
   private Double RCTH_TRCH_NUM;
   private String RCTH_RESA_TYP;
   private String RCTH_CLBA_COD;
   private String RCTH_INPT_RR_D;
   private Boolean RCTH_REGI;
   
   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
