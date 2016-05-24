package com.avancial.app.data.databean.motrice;

import java.io.Serializable;

@SuppressWarnings("unused")
public class TMDSPPLEntityId implements Serializable{
   
   private static final long serialVersionUID = 1L;
   
   private String SPPL_VOIT_COD_CIE;
   private String SPPL_VOIT_NUM_TRA1;
   private String SPPL_VOIT_IND_FER;
   private Double SPPL_VOIT_TRCH_NUM;
   private Double SPPL_VOIT_NUM;
   private Double SPPL_PCDD_NUM_COMP;
   private Double SPPL_PCDD_NUM_PLAC;
   private String SPPL_SPEC_COD;
   private Boolean SPPL_REGI;
   
   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
