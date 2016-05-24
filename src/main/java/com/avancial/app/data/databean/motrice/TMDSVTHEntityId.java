package com.avancial.app.data.databean.motrice;

import java.io.Serializable;

@SuppressWarnings("unused")
public class TMDSVTHEntityId implements Serializable{
   
   private static final long serialVersionUID = 1L;
   
   private String SVTH_TRCH_COD_CIE;
   private String SVTH_TRCH_NUM_TRA1;
   private String SVTH_TRCH_IND_FER;
   private Long SVTH_TRCH_NUM;
   private String SVTH_LIBS_SERV_COD;
   private Long SVTH_NUM;
   private String SVTH_COD_ORIGINE;
   private String SVTH_INPT_RR_D;
   private String SVTH_INPT_RR_F;
   private Boolean SVTH_REGI;
   private String SVTH_IND_SPTH;
   private String SVTH_TYP_CLAS;
   
   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
