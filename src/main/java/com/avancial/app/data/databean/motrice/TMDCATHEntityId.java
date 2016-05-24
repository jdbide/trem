package com.avancial.app.data.databean.motrice;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@SuppressWarnings("unused")
public class TMDCATHEntityId implements Serializable {

   private static final long serialVersionUID = 1L;

   private String CATH_TRCH_COD_CIE;
   private String CATH_TRCH_NUM_TRA1;
   private String CATH_TRCH_IND_FER;
   private Double CATH_TRCH_NUM;
   private Double CATH_NUM;
   private String CATH_CIRR_COD_CIE;
   private String CATH_REGI;
   private String CATH_ETAT_TRCH;
   private String CATH_EN_RESA;
   private String CATH_SSIM;

   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }
}
