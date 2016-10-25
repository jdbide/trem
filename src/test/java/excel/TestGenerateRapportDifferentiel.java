package excel;

import org.junit.Assert;
import org.junit.Test;

import com.avancial.app.export.ExcelRapportDifferentiel;
import com.avancial.app.export.control.ExcelRapportControle;
import com.avancial.app.service.comparePlanTransport.ComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.controlePlanTransport.ControlePlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.utils.FileUtils;

import factory.PlanTransportFactory;

public class TestGenerateRapportDifferentiel {

   @Test
   public void testGenerationXlsTrancheSplit() {
      try {
         ExcelRapportDifferentiel excelRapportDifferentiel = new ExcelRapportDifferentiel();
         MapPlansDeTransport mapPlansDeTransport = PlanTransportFactory.createDataForCompareTrancheSplit();
         IComparePlanTransport comparePlanTransport = new ComparePlanTransport();
         excelRapportDifferentiel
               .setDatas(comparePlanTransport.compare(mapPlansDeTransport.getPlanTransportActive(), mapPlansDeTransport.getPlanTransportDraft()));
         excelRapportDifferentiel.setMapPlansDeTransport(mapPlansDeTransport);

         excelRapportDifferentiel.setFileName("testGenerationXlsTrancheSplit");
         excelRapportDifferentiel.setFilePath("D:/was_tmp/tremas/export/");
         excelRapportDifferentiel.setXlsx(true);
         excelRapportDifferentiel.generate();
         Assert.assertTrue(FileUtils.existFile("D:/was_tmp/tremas/export/testGenerationXlsTrancheSplit.xlsx"));
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

   @Test
   public void testGenerationRapportControle() {
      try {
         ExcelRapportControle rapportControle = new ExcelRapportControle(true);
         MapPlansDeTransport mapPlansDeTransport = PlanTransportFactory.createDataForControle();
         ControlePlanTransport comparePlanTransport = new ControlePlanTransport();
         rapportControle
               .setDatas(comparePlanTransport.compare(mapPlansDeTransport.getPlanTransportActive(), mapPlansDeTransport.getPlanTransportDraft()));
         rapportControle.setMapPlansDeTransport(mapPlansDeTransport);

         rapportControle.setFileName("testGenerationRapportControle");
         rapportControle.setFilePath("D:/was_tmp/tremas/export/");
         rapportControle.generate();
         Assert.assertTrue(FileUtils.existFile("D:/was_tmp/tremas/export/testGenerationRapportControle.xlsx"));
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
