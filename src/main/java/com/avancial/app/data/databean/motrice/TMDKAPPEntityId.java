package com.avancial.app.data.databean.motrice;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("unused")
public class TMDKAPPEntityId implements Serializable{
   
   private static final long serialVersionUID = 1L;
   
   private String KAPP_APP;
   private String KAPP_CON;
   private Date KAPP_TRA;
   private Date KAPP_ORI;
   private Date KAPP_DEX;
   private String KAPP_INF;
   private String KAPP_MOD;
   private String KAPP_QAL;
   private String KAPP_SER;
   private String KAPP_TIT;
   private String KAPP_VAL;
   private Long KAPP_VES;
   private Long KAPP_VER;
   private Long KAPP_GLO;
   
   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
