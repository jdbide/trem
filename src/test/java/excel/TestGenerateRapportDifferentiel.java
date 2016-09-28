package excel;

import org.junit.Assert;
import org.junit.Test;
import com.avancial.app.export.ExcelRapportDifferentiel;
import com.avancial.app.service.comparePlanTransport.ComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.utilitaire.FileUtil;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import factory.PlanTransportFactory;

public class TestGenerateRapportDifferentiel {

    @Test
    public void testGenerationXlsTrancheSplit() {
        try {
            ExcelRapportDifferentiel excelRapportDifferentiel = new ExcelRapportDifferentiel();
            MapPlansDeTransport mapPlansDeTransport = PlanTransportFactory.createDataForCompareTrancheSplit();
            IComparePlanTransport comparePlanTransport = new ComparePlanTransport();
            excelRapportDifferentiel.setDatas(comparePlanTransport.compare(mapPlansDeTransport.getPlanTransportActive(),
                    mapPlansDeTransport.getPlanTransportDraft()));
            excelRapportDifferentiel.setMapPlansDeTransport(mapPlansDeTransport);

            mapPlansDeTransport.getPlanTransportActive();
            mapPlansDeTransport.getPlanTransportDraft();

            excelRapportDifferentiel.setFileName("testGenerationXlsTrancheSplit");
            excelRapportDifferentiel.setFilePath("D:/was_tmp/tremas/export/");
            excelRapportDifferentiel.setXlsx(true);
            excelRapportDifferentiel.generate();
            if (!FileUtil.existFile("D:/was_tmp/tremas/testGenerationXlsTrancheSplit.xls")) {
                Assert.assertTrue(false);
            }
            else
                Assert.assertTrue(true);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
