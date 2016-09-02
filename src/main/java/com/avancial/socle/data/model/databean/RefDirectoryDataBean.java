package com.avancial.socle.data.model.databean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the socle_ref_directory database table.
 * 
 */
@Entity
@Table(name = "socle_ref_directory")
@NamedQueries({ 
   @NamedQuery(name = RefDirectoryDataBean.QUERY_GET_ALL, query = "FROM RubriqueDataBean"),
   @NamedQuery(name = RefDirectoryDataBean.QUERY_GET_BY_NOM_TECH, query = "FROM RefDirectoryDataBean refDirectory WHERE refDirectory.technicalNameRefDirectory = :technicalName"),
})
public class RefDirectoryDataBean extends AbstractDataBean {
   private static final long serialVersionUID = 1L;
   
   public final static String     QUERY_GET_ALL               = "refDirectoryGetAll";
   public final static String     QUERY_GET_BY_NOM_TECH       = "refDirectoryGetByNomTech";

   @Id
   @Column(unique = true, nullable = false)
   private int idRefDirectory;

   @Lob
   private String commentsRefDirectory;

   @Column(nullable = false, length = 100)
   private String pathRefDirectory;

   @Column(nullable = false, length = 30)
   private String technicalNameRefDirectory;

   public RefDirectoryDataBean() {
   }

   public int getIdRefDirectory() {
      return this.idRefDirectory;
   }

   public void setIdRefDirectory(int idRefDirectory) {
      this.idRefDirectory = idRefDirectory;
   }

   public String getCommentsRefDirectory() {
      return this.commentsRefDirectory;
   }

   public void setCommentsRefDirectory(String commentsRefDirectory) {
      this.commentsRefDirectory = commentsRefDirectory;
   }

   public String getPathRefDirectory() {
      return this.pathRefDirectory;
   }

   public void setPathRefDirectory(String pathRefDirectory) {
      this.pathRefDirectory = pathRefDirectory;
   }

   public String getTechnicalNameRefDirectory() {
      return this.technicalNameRefDirectory;
   }

   public void setTechnicalNameRefDirectory(String technicalNameRefDirectory) {
      this.technicalNameRefDirectory = technicalNameRefDirectory;
   }

}