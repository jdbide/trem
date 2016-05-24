package com.avancial.app.data.databean.motrice;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

@SuppressWarnings("unused")
public class TMDTRA1EntityId implements Serializable {

   private static final long serialVersionUID = 1L;

   private String            TRA1_CIES_COD_CIE;
   private String            TRA1_NUM_TRAIN;
   private String            TRA1_IND_FER_ROUTE;
   private String            TRA1_LIBS_GERE_COD;
   private String            TRA1_NOM_TRAIN;
   private String            TRA1_AUT_MOD_THOR;
   private String            TRA1_NAT_MOD_THOR;
   private Date              TRA1_DAT_MOD_THOR;
   private Date              TRA1_DAT_DER_MOD;
   private String            TRA1_IND_COMPO;
   private String            TRA1_IND_CDEM;
   private String            TRA1_IND_ECOLE;
   private Boolean           TRA1_REGI_VAL;
   private Boolean           TRA1_REGI_VAL_TRTH;
   private Boolean           TRA1_REGI_NON_EXTR;

   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
