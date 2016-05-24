package com.avancial.app.data.databean.motrice;

import java.io.Serializable;

@SuppressWarnings("unused")
public class TMDETVOEntityId implements Serializable{
   
   private static final long serialVersionUID = 1L;
   
   private String  ETVO_VOIT_COD_CIE;
   private String  ETVO_VOIT_NUM_TRA1;
   private String  ETVO_VOIT_IND_FER;
   private Long    ETVO_VOIT_TRCH_NUM;
   private Long    ETVO_VOIT_NUM;
   private Long    ETVO_NUM;
   private String  ETVO_ETAT_VOIT;
   private Boolean ETVO_REGI;
   
   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
