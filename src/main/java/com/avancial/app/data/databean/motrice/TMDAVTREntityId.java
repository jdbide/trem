package com.avancial.app.data.databean.motrice;

import java.io.Serializable;



//@Embeddable
public class TMDAVTREntityId implements Serializable{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private String AVTR_TRA1_COD_CIE;
   private String AVTR_TRA1_NUM_TRA1;
   private String AVTR_TRA1_IND_FER;
   private String AVTR_LIBS_AVAL_COD;
  
   public boolean equals(Object o){
      return super.equals(o);
    }
   
   public int hashCode(){
      return super.hashCode();
   }

}
