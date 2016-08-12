/**
 * 
 */
package com.avancial.app.export;

import java.io.File;
import java.io.FileOutputStream;

import com.avancial.app.utilitaire.FileUtils;

/**
 * @author hamza.laterem
 *
 */
public abstract class ASocleGenerateFile extends ASocleExportService {
   /**
    * 
    */
   public ASocleGenerateFile() {
      super();
   }

   public ASocleGenerateFile(String fileName, String filePath) {
      super(fileName, filePath);
   }

   /**
    * @param exportFileName
    * @param exportFileExtension
    * @param contentType
    */
   public ASocleGenerateFile(String fileName, String filePath, String fileExtension) {
      super(fileName, filePath, fileExtension);
   }

   protected abstract void initFileExport() throws Exception;

   protected abstract void initFileType() throws Exception;

   protected abstract void instanceDocument() throws Exception;

   protected abstract void generateFile() throws Exception;

   protected abstract void finalizeFile() throws Exception;

   @Override
   public void generate() throws Exception {
      this.initFileType();
      this.createFolder();
      this.createFile();
      this.instanceDocument();
      try {
         this.generateFile();
      } catch (Exception e) {
         e.printStackTrace();
         this.closeFile();
         throw e;
      }

      this.finalizeFile();
      this.closeFile();
   }

   private void createFile() throws Exception {
      try {
         File destinationFile = new File(this.filePath + this.fileName + "." + this.fileExtension);
         this.out = new FileOutputStream(destinationFile);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println("Erreur de création du fichier \"" + this.filePath + this.fileName + "." + this.fileExtension + "\"");
         throw e;
      }
   }

   private void createFolder() throws Exception {
      try {
         if (!FileUtils.existFile(this.filePath)) {
            FileUtils.mkDirs(this.filePath);
         }
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println("Erreur de création du répertoire \"" + this.filePath + "\"");
         throw e;
      }
   }

   private void closeFile() throws Exception {
      try {
         this.out.flush();
         this.out.close();
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println("Erreur de fermeture du fichier");
         throw e;
      }

   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.app.export.ASocleExportService#generateFile()
    */
   @Override
   protected Object generateFileForExport() throws Exception {
      // TODO Auto-generated method stub
      return null;
   }
}
