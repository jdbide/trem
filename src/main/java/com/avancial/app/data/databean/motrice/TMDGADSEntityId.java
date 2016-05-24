package com.avancial.app.data.databean.motrice;

import java.io.Serializable;

@SuppressWarnings("unused")
public class TMDGADSEntityId implements Serializable{
   
   private static final long serialVersionUID = 1L;
   
   private String GADS_DSTR_COD_CIE;
   private String GADS_DSTR_NUM_TRA1;
   private String GADS_DSTR_IND_FER;
   private Double GADS_DSTR_NUM;
   private Double GADS_NUM_GAR;
   private String GADS_INPT_RR_GAR;
   private String GADS_DEB_ARRET;
   private String GADS_FIN_ARRET;
   private String GADS_VAL_PARITE;
   private String GADS_TYP_ARRET;
   private String GADS_CAST_COD_STAT;
   private String GADS_IND_PT_FRONT;
   private String GADS_IND_CIRC_THO;
   
   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
