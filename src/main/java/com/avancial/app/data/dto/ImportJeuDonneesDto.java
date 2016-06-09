package com.avancial.app.data.dto;

public class ImportJeuDonneesDto {
   private Integer idApplication;
   private String username;
   private String password;
   
   public ImportJeuDonneesDto() {
      // TODO Auto-generated constructor stub
   }

   /**
    * @return the idApplication
    */
   public Integer getIdApplication() {
      return idApplication;
   }

   /**
    * @param idApplication the idApplication to set
    */
   public void setIdApplication(Integer idApplication) {
      this.idApplication = idApplication;
   }

   /**
    * @return the username
    */
   public String getUsername() {
      return username;
   }

   /**
    * @param username the username to set
    */
   public void setUsername(String username) {
      this.username = username;
   }

   /**
    * @return the password
    */
   public String getPassword() {
      return password;
   }

   /**
    * @param password the password to set
    */
   public void setPassword(String password) {
      this.password = password;
   }

}
