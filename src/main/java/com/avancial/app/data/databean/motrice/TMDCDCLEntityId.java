package com.avancial.app.data.databean.motrice;

import java.io.Serializable;

@SuppressWarnings("unused")
public class TMDCDCLEntityId implements Serializable{
   
   private static final long serialVersionUID = 1L;
   
   private String CDCL_CDEM_COD_CIE;
   private String CDCL_CDEM_NUM_TRA1;
   private String CDCL_CDEM_IND_FER;
   private Long CDCL_CDEM_NUM_COND;
   private String CDCL_CLBA_COD;
   
   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
