/**
 * 
 */
package com.avancial.app.export;

import java.io.OutputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 * @author hamza.laterem
 *
 */
public abstract class ASocleExportService {
   protected String          fileName        = "export";
   protected String          fileExtension   = "";
   protected String          filePath        = "D:/was_tmp/tremas/";
   protected String          contentType     = "";
   protected OutputStream    out             = null;
   protected ResponseBuilder responseBuilder = null;

   /**
    * 
    */
   public ASocleExportService() {
   }

   /**
    * 
    * @param fileName
    * @param filePath
    */
   public ASocleExportService(String fileName, String filePath) {
      this.fileName = fileName;
   }

   /**
    * Constructeur pour une instanciation avec une reponse http
    * 
    * @param exportFileName
    * @param exportFileExtension
    * @param contentType
    * @param httpResponse
    */
   public ASocleExportService(String fileName, String filePath, String fileExtension) {
      this.fileName = fileName;
      this.fileExtension = fileExtension;
      this.filePath = filePath;
   }

   /**
    * 
    * @throws Exception
    */
   public abstract void generate() throws Exception;

   /**
    * 
    * @return
    * @throws Exception
    */
   protected abstract Object generateFileForExport() throws Exception;

   /**
    * 
    * @throws Exception
    */
   protected abstract void initFileExport() throws Exception;

   /**
    * 
    * @return
    * @throws Exception
    */
   public ResponseBuilder export() throws Exception {
      try {
         this.initFileExport();
         this.prepareResponse(this.generateFileForExport());
      } catch (Exception e) {
         e.printStackTrace();
         this.responseBuilder = Response.status(500);
      }

      return this.responseBuilder;
   }

   /**
    * 
    * @param fileDownload
    * @throws Exception
    */
   private void prepareResponse(Object fileDownload) throws Exception {
      responseBuilder = Response.ok(fileDownload);
      responseBuilder.header("Content-Disposition", "attachment; filename=\"" + this.fileName + "." + this.fileExtension + "\"");
      /*
       * TODO A voir
       */
      // responseBuilder.header("contentType", this.contentType);
   }

   /**
    * @return the exportFileName
    */
   public String getFileName() {
      return this.fileName;
   }

   /**
    * @param exportFileName
    *           the exportFileName to set
    */
   public void setFileName(String fileName) {
      this.fileName = fileName;
   }

   /**
    * @return the exportFileExtension
    */
   protected String getExportFileExtension() {
      return this.fileExtension;
   }

   /**
    * @param exportFileExtension
    *           the exportFileExtension to set
    */
   protected void setFileExtension(String fileExtension) {
      this.fileExtension = fileExtension;
   }

   /**
    * @return the contentType
    */
   protected String getContentType() {
      return this.contentType;
   }

   /**
    * @param contentType
    *           the contentType to set
    */
   protected void setContentType(String contentType) {
      this.contentType = contentType;
   }

   /**
    * @return the exportFilePath
    */
   public String getFilePath() {
      return this.filePath;
   }

   /**
    * @param exportFilePath
    *           the exportFilePath to set
    */
   public void setFilePath(String filePath) {
      this.filePath = filePath;
   }

}
