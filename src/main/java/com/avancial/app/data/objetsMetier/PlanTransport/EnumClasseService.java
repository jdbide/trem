package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.HashMap;
import java.util.Map;

import org.jboss.weld.exceptions.IllegalArgumentException;

public enum EnumClasseService {
   Premiere ("1"),
   Toute ("3"),
   Erreur ("T");
   
   private final String symbol;
   private EnumClasseService(String symbol) {
      this.symbol = symbol;
   }
   
   public String getSymbol() {
      return this.symbol;
   }
   
   private static final HashMap<String, EnumClasseService> mapSymbols = new HashMap<String, EnumClasseService>();
   static {
      for (EnumClasseService enumClasseService : values()) {
         mapSymbols.put(enumClasseService.getSymbol(), enumClasseService);
      }
   }
   
   public static EnumClasseService getEnumClasseService(String symbole) {
      final EnumClasseService value = mapSymbols.get(symbole);
      if (value != null) {
         return value;
      }
      throw new IllegalArgumentException("Symbole inconnu : " + symbole);
   }
   
}
