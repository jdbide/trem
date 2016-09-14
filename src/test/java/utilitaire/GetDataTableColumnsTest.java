package utilitaire;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeDistributionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.utilitaire.GetDataTableColumns;

public class GetDataTableColumnsTest {

    @Test
    public void main() {
        String test = "";
        List<String> res = new ArrayList<>();

        res = GetDataTableColumns.getNameColumns(MotriceTrainTrancheEntity.class);
        test = "";
        for (String name : res) {
            test += name + ",";
        }
        assertEquals(
                "idMotriceTrainTranche,trainNumberMotriceTrainTranche,validForRRMotriceTrainTranche,trancheNumberMotriceTrainTranche,"
                        + "trancheStatusMotriceTrainTranche,idJeuDonnees,",
                test);

        res = GetDataTableColumns.getNameColumns(MotriceRegimeDistributionEntity.class);
        test = "";
        for (String name : res) {
            test += name + ",";
        }
        assertEquals("idMotriceRegimeDistribution,distribIndexMotriceRegimeDistribution,idMotriceRegime,", test);

        res = GetDataTableColumns.getNameColumns(MotriceRegimeEntity.class);
        test = "";
        for (String name : res) {
            test += name + ",";
        }
        assertEquals("idMotriceRegime,periodMotriceRegime,idMotriceRefRegimeType,idMotriceTrainTranche,", test);
    }
}
