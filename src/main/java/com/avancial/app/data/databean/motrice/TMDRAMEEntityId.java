package com.avancial.app.data.databean.motrice;

import java.io.Serializable;

@SuppressWarnings("unused")
public class TMDRAMEEntityId implements Serializable{

   private static final long serialVersionUID = 1L;
   
   private String RAME_TRCH_COD_CIE;
   private String RAME_TRCH_NUM_TRA1;
   private String RAME_TRCH_IND_FER;
   private Double RAME_TRCH_NUM;
   private String RAME_RAMC_COD;
   private Double RAME_NUM;
   private String RAME_NUM_PREM_VOIT;
   private String RAME_REGI;
   
   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
