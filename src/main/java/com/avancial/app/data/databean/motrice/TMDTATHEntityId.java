package com.avancial.app.data.databean.motrice;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("unused")
public class TMDTATHEntityId implements Serializable {

   private static final long serialVersionUID = 1L;

   private String            TATH_TRCH_COD_CIE;
   private String            TATH_TRCH_NUM_TRA1;
   private String            TATH_TRCH_IND_FER;
   private Double              TATH_TRCH_NUM;
   private String            TATH_TYP_TAX;
   private String            TATH_CD_VAL;
   private String            TATH_INPT_RR_D;
   private String            TATH_INPT_RR_F;
   private String            TATH_PRIX_HORS_SYS;
   private Date              TATH_DHDO;

   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
