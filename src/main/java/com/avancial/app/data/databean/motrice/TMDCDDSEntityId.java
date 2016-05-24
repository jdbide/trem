package com.avancial.app.data.databean.motrice;

import java.io.Serializable;

@SuppressWarnings("unused")
public class TMDCDDSEntityId implements Serializable{
   
   private static final long serialVersionUID = 1L;
   
   private String CDDS_CDEM_COD_CIE;
   private String CDDS_CDEM_NUM_TRA1;
   private String CDDS_CDEM_IND_FER;
   private Long CDDS_CDEM_NUM_COND;
   private String CDDS_INPT_RR_MONT;
   private String CDDS_INPT_RR_DESC;
   
   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
