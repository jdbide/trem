package com.avancial.app.data.databean.motrice;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("unused")
public class TMDPAREEntityId implements Serializable{

   private static final long serialVersionUID = 1L;
   
   private String PARE_SVTH_COD_CIE;
   private String PARE_SVTH_NUM_TRA1;
   private String PARE_SVTH_IND_FER;
   private Double PARE_SVTH_TRCH_NUM;
   private String PARE_SVTH_SERV_COD;
   private Double PARE_SVTH_NUM;
   private Double PARE_NUM_REST;
   private String PARE_TYRE_COD_REP;
   private String PARE_RESP_VOIT_NUM;
   private String PARE_VRES_NUM_SERV;
   private Date PARE_H_DEB_SERV;
   private Date PARE_H_FIN_SERV;
   private Boolean PARE_REGI;
   private Double PARE_NB_REPAS;
   private String PARE_IND_PLACE;
   private String PARE_IND_VOIT_REST;
   
   public boolean equals(Object o) {
      return super.equals(o);
   }

   public int hashCode() {
      return super.hashCode();
   }

}
