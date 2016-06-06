package com.avancial.app.utilitaire;

public enum SchemaMotrice {
   SN("F$MDRP1"),
   ES("F$MDRP2"),
   TH("F$MDRP3");

   private String schema = "";

   private SchemaMotrice(String schema) {
      this.schema = schema;
   }

   public String getSchema() {
      return this.schema;
   }
}
