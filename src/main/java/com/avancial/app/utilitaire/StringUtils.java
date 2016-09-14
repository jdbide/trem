package com.avancial.app.utilitaire;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
   public static String nonConforme = "nc";
   public static String nonRenseigne = "nr";
   public static String sautDeLigne = System.getProperty("line.separator");

   /**
    * Concat�nation de chaines de caract�re
    * 
    * @param args
    * @return
    */
   public static String concatStringAvecBuffer(final String... args) {
      return join(args);
   }

   /**
    * M�thode changeant le style d'un bol�en en OUI/NON color�s.
    * 
    * @param etat
    * @param ok
    * @param ko
    * @return String color�
    */
   public static String getEtatToString(final Boolean etat, final String ok, final String ko) {
      return etat != null && etat ? "<font color='green'><b>" + ok + "</b></font>" : "<font color='red'><b>" + ko + "</b></font>";
   }

   /**
    * M�thode qui convertit la chaine pass�e en argument au format MD5.
    * 
    * @param chaine
    *           une chaine de caract�re
    * @return la chaine au format MD5
    * @throws Exception
    */
   public static String getMd5(final String chaine) throws Exception {
      final MessageDigest digest = MessageDigest.getInstance("MD5");

      final InputStream is = new ByteArrayInputStream(chaine.getBytes());
      final byte[] buffer = new byte[1024];
      int read = 0;

      while ((read = is.read(buffer)) > 0) {
         digest.update(buffer, 0, read);
      }
      final byte[] md5sum = digest.digest();
      final BigInteger bigInt = new BigInteger(1, md5sum);
      final String output = bigInt.toString(16);

      return leftPad(output, 32, "0");
   }

   /**
    * M�thode qui convertit la chaine pass�e en argument au format MD5 et en majuscule.
    * 
    * @param chaine
    *           une chaine de caract�re
    * @return la chaine au format MD5 en majuscule
    * @throws Exception
    */
   public static String getMd5ToUpperCase(final String chaine) throws Exception {
      return StringUtils.getMd5(chaine).toUpperCase();
   }

   /**
    * 
    * @param stringValue
    * @return
    */
   public static int getNbMaxDeCaratere(String stringValue) {
      StringTokenizer chaine = new StringTokenizer(stringValue, sautDeLigne);
      int maxSize = 0;
      while (chaine.hasMoreTokens()) {
         String tmp = chaine.nextToken();
         if (tmp.length() > maxSize)
            maxSize = tmp.length();
      }
      return maxSize;
   }

   public static int getNbSautDeligne(final String stringValue) {
      final StringTokenizer chaine = new StringTokenizer(stringValue, sautDeLigne);
      return chaine.countTokens();
   }

   public static String toLowerCaseFirst(final String chaine) {
      return chaine.substring(0, 1).toLowerCase() + chaine.substring(1, chaine.length());
   }

   public static String toString(final String stringValue, final String defautValue) {
      if (stringValue != null)
         return stringValue;
      else
         return defautValue;
   }

   public static String toStringDefautNr(final String stringValue) {
      return toString(stringValue, StringUtils.nonRenseigne);
   }

   public static String toStringHtmlAvecBr(final String stringValue) {
      return stringValue.replace(sautDeLigne, "<br/>");
   }

   public static String toStringHtmlAvecBrPourRenderCtrl(final String stringValue) {
      return toStringHtmlAvecBr(stringValue).replace("\"", "&#39;").replace("'", "&#39;");
   }

   public static String toUpperCaseFirst(final String chaine) {
      return chaine.substring(0, 1).toUpperCase() + chaine.substring(1, chaine.length());
   }

   public static String toUpperCaseFirstOnly(final String chaine) {
      return chaine.substring(0, 1).toUpperCase() + chaine.substring(1, chaine.length()).toLowerCase();
   }

   public static boolean validLogin(final String login) {
      if (login != null) {
         final Pattern p = Pattern.compile("[a-zA-Z0-9_\\.||-]*");
         final Matcher m = p.matcher(login);
         return m.matches();
      } else
         return false;
   }

   public static Boolean validMail(final String email) {
      if (email != null) {
         Pattern p;
         try {
            p = Pattern.compile("");
         } catch (final Exception e) {
            e.printStackTrace();
            p = Pattern.compile("[\\.[a-z0-9_-]]*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
         }

         final Matcher m = p.matcher(email);
         return m.matches();
      } else
         return false;
   }

   /**
    * Renvoie vrai si la cha�ne contient des caract�res accentu�s
    * 
    * @param chaine
    * @return Vrai si la chaine contient des accents, faux sinon
    */
   public static Boolean contientDesAccents(String chaine) {
      String regex = ".*[����������].*";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(chaine.trim());
      if (matcher.find())
         return true;
      return false;
   }
}
