package com.avancial.app.data.databean.motrice;

import java.io.Serializable;

@SuppressWarnings("unused")
public class TMDSPCOEntityId implements Serializable{
   
   private static final long serialVersionUID = 1L;
   
   private String SPCO_VOIT_COD_CIE;
   private String SPCO_VOIT_NUM_TRA1;
   private String SPCO_VOIT_IND_FER;
   private Double SPCO_VOIT_TRCH_NUM;
   private Double SPCO_VOIT_NUM;
   private Double SPCO_COMP_NUM;
   private String SPCO_SPEC_COD;
   private String SPCO_REGI;

   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }
}
