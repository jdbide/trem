package com.avancial.app.data.databean.motrice;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

@SuppressWarnings("unused")
public class TMDTRCHEntityId implements Serializable {

   private static final long serialVersionUID = 1L;

   private String            TRCH_TRA1_COD_CIE;
   private String            TRCH_TRA1_NUM_TRA1;
   private String            TRCH_TRA1_IND_FER;
   private Long              TRCH_NUM;
   private String            TRCH_COD_SENS_AUTO;
   private String            TRCH_IND_TGV;
   private String            TRCH_IND_VAL_DC;
   private Date              TRCH_DAT_DER_MOD;
   private String            TRCH_INPT_RR_ORIG;
   private String            TRCH_INPT_RR_DEST;
   private Boolean           TRCH_REGI_VAL;
   private Boolean           TRCH_REGI_VAL_DC;
   private String            TRCH_LIBS_SEMA_COD;

   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
