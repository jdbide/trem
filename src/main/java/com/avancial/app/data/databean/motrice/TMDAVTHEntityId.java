package com.avancial.app.data.databean.motrice;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@SuppressWarnings("unused")
public class TMDAVTHEntityId implements Serializable {

   private static final long serialVersionUID = 1L;

   private String            AVTH_TRCH_COD_CIE;
   private String            AVTH_TRCH_NUM_TRA1;
   private String            AVTH_TRCH_IND_FER;
   private Long              AVTH_TRCH_NUM;
   private String            AVTH_LIBS_AVAL_COD;

   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }
}
