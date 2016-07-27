package com.avancial.app.utilitaire;

public enum SchemaMotrice {
   SN("F$MDRP1"),
   ES("F$MDRP2"),
   TH("F$MDRP3"),
   SN_REC("F$MDRO1"),
   ES_REC("F$MDRO2"),
   TH_REC("F$MDRO3");

   private String schema = "";

   private SchemaMotrice(String schema) {
      this.schema = schema;
   }

   public String getSchema() {
      return this.schema;
   }
}
