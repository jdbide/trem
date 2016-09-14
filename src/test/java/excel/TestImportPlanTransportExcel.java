package excel;

import org.junit.Test;
import com.avancial.app.export.FileTypeNotExpectedException;
import com.avancial.app.service.controlePlanTransport.ImportPlanTransportExcel;

public class TestImportPlanTransportExcel {

    @Test
    public void main() {
        ImportPlanTransportExcel importPlanTransportExcel = new ImportPlanTransportExcel();
        try {
            importPlanTransportExcel.importePlanTransport("D:/was_tmp/Eurostar Timetable Period D2 2016 V1.0A 2016_01_28.xlsx");
        }
        catch (FileTypeNotExpectedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
