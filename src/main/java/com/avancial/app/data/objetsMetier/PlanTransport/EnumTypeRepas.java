package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.HashMap;

import org.jboss.weld.exceptions.IllegalArgumentException;

public enum EnumTypeRepas {
   PetitDejeuner("B"),
   Dejeuner("L"),
   Snack("S"),
   diner("D");

   private final String symbol;

   private EnumTypeRepas(String symbol) {
      this.symbol = symbol;
   }

   public String getSymbol() {
      return this.symbol;
   }

   private static final HashMap<String, EnumTypeRepas> mapSymbols = new HashMap<String, EnumTypeRepas>();
   static {
      for (EnumTypeRepas enumTypeRepas : values()) {
         mapSymbols.put(enumTypeRepas.getSymbol(), enumTypeRepas);
      }
   }

   public static EnumTypeRepas getEnumTypeRepas(String symbole) {
      final EnumTypeRepas value = mapSymbols.get(symbole);
      if (value != null) {
         return value;
      }
      throw new IllegalArgumentException("Symbole inconnu : " + symbole);
   }
}
