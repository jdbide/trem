package com.avancial.app.data.databean.motrice;

import java.io.Serializable;

@SuppressWarnings("unused")
public class TMDCATREntityId implements Serializable{

   private static final long serialVersionUID = 1L;
   
   private String CATR_TRA1_COD_CIE;
   private String CATR_TRA1_NUM_TRA1;
   private String CATR_TRA1_IND_FER;
   private Long CATR_NUM;
   private String CATR_READ_COD;
   private String CATR_TYEQ_COD;
   private String CATR_LIBS_QLCO_COD;
   private String CATR_LIBS_OURE_COD;
   private String CATR_IND_TRA1_RESA;
   private Boolean CATR_REGI;
   private String CATR_PARITE;
   
   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
