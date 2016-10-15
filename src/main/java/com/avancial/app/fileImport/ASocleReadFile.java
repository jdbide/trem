package com.avancial.app.fileImport;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Lecture d'un fichier
 * 
 * @author heloise.guillemaud
 *
 */
public abstract class ASocleReadFile {

   /**
    * Chemin jusqu'au nom du fichier
    */
   private String          filePath;

   /**
    * Flux vers le fichier en lecture
    */
   private FileInputStream fileInput;

   public ASocleReadFile(String filePath) {
      this.filePath = filePath;
   }

   /**
    * Ouverture du flux vers le fichier
    * 
    * @throws Exception
    */
   public void start() throws Exception {
      this.fileInput = new FileInputStream(this.filePath);
   }

   /**
    * Fermeture du flux vers le fichier
    * 
    * @throws IOException
    */
   public void close() throws IOException {
      if (this.fileInput != null) {
         this.fileInput.close();
      }
   }

   public String getFilePath() {
      return this.filePath;
   }

   public void setFilePath(String filePath) {
      this.filePath = filePath;
   }

   public FileInputStream getFileInput() {
      return this.fileInput;
   }

   public void setFileInput(FileInputStream fileInput) {
      this.fileInput = fileInput;
   }
}
