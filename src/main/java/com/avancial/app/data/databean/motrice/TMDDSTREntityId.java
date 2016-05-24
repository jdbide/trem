package com.avancial.app.data.databean.motrice;

import java.io.Serializable;

@SuppressWarnings("unused")
public class TMDDSTREntityId implements Serializable{
   
   private static final long serialVersionUID = 1L;
   
   private String DSTR_TRA1_COD_CIE;
   private String DSTR_TRA1_NUM_TRA1;
   private String DSTR_TRA1_IND_FER;
   private Double DSTR_NUM;
   private Boolean DSTR_REGI;
   private String DSTR_ETAT_TRA1;
   private String DSTR_COD_MISSION;
   private String DSTR_TYP_DES;
   private String DSTR_REF_IHM;
   private String DSTR_LIBS_IND_JALO;
   
   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
