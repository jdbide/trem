package com.avancial.app.data.databean.motrice;

import java.io.Serializable;

@SuppressWarnings("unused")
public class TMDVOITEntityId implements Serializable{
   
   private static final long serialVersionUID = 1L;
   
   private String VOIT_TRCH_COD_CIE;
   private String VOIT_TRCH_NUM_TRA1;
   private String VOIT_TRCH_IND_FER;
   private Long VOIT_TRCH_NUM;
   private Long VOIT_NUM;
   private String VOIT_NUM_RESA;
   private Long VOIT_NUM_VOIT;
   private String VOIT_COD_ORIG;
   private Long VOIT_SENS_ORIG;
   private Long VOIT_NUM_ORIG;
   private Long VOIT_TYVO_NUM_TYP;
   private String VOIT_CIES_COD_GERE;
   private String VOIT_IND_ORIG;
   private Boolean VOIT_REGI_UTIL;
   private Long VOIT_ROUL_NUM;
   private Long VOIT_INDCE_CLASST;
   
   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
