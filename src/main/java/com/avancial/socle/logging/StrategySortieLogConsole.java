package com.avancial.socle.logging;

import java.lang.reflect.Field;

import javassist.Modifier;

/**
 * Implémentation du pattern Strategy pour logger en console.<br/>
 * Cette stratégie est générique et accepte tous les type de bean.
 * 
 * @author heloise.guillemaud
 *
 */
public class StrategySortieLogConsole implements IStrategySortieLog {

   @Override
   public void log(ALogBean logBean) throws IllegalArgumentException, IllegalAccessException {
      System.out.println(StrategySortieLogConsole.printLogBean(logBean));
   }

   /**
    * Génère la chaîne de caractères décrivant un LogBean
    * 
    * @param logBean
    * @return String à afficher sur la console Java
    * @throws IllegalAccessException
    * @throws IllegalArgumentException
    */
   private static String printLogBean(ALogBean logBean) throws IllegalArgumentException, IllegalAccessException {
      StringBuilder sb = new StringBuilder();
      String tab = "    ";
      String sautLigne = "\n";

      sb.append("-----  " + logBean.getClass().getName() + "  -----" + sautLigne);

      for (Field field : logBean.getClass().getDeclaredFields()) {
         // Si c'est une constante, on la zappe
         int modifier = field.getModifiers();
         if (!(Modifier.isFinal(modifier) && Modifier.isStatic(modifier))) {
            sb.append(String.format("%s : %s", field.getName(), field.get(logBean)));
            sb.append(sautLigne);
         }

      }

      return sb.toString();
   }

}
