/**
 * 
 */
package com.avancial.app.export;

/**
 * @author hamza.laterem
 *
 */
public class AsocleExcelService extends ASocleExportExcelService {

   /**
    * 
    */
   public AsocleExcelService() {
      // TODO Auto-generated constructor stub
   }

   /**
    * @param xlsx
    */
   public AsocleExcelService(boolean xlsx) {
      super(xlsx);
      // TODO Auto-generated constructor stub
   }

   /**
    * @param xlsx
    * @param fileName
    * @param filePath
    */
   public AsocleExcelService(boolean xlsx, String fileName, String filePath) {
      super(xlsx, fileName, filePath);
      // TODO Auto-generated constructor stub
   }

   /* (non-Javadoc)
    * @see com.avancial.app.export.ASocleExportExcelService#generateAsXls()
    */
   @Override
   protected void generateAsXls() throws Exception {
      // TODO Auto-generated method stub

   }

}
