package service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.comparePlanTransport.ComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.TrainTranche;
import junit.framework.Assert;

public class TestComparePlanTransport {

    @Test
    public void main() {
        ComparePlanTransport comparePlanTransport = new ComparePlanTransport();

        Tranche tranche1 = new Tranche();
        Tranche tranche2 = new Tranche();
        Tranche tranche3 = new Tranche();
        tranche1.setNumeroTranche("1");
        tranche2.setNumeroTranche("2");
        tranche3.setNumeroTranche("3");

        List<Tranche> tranches10 = new ArrayList<>();
        tranches10.add(tranche1);
        List<Tranche> tranches20 = new ArrayList<>();
        tranches20.add(tranche2);
        List<Tranche> tranches30 = new ArrayList<>();
        tranches30.add(tranche3);

        List<Train> trains1 = new ArrayList<>();

        List<Train> trains2 = new ArrayList<>();
        Train train20 = new Train(tranches20, "20", true);
        Train train21 = new Train(tranches30, "10", true);
        trains2.add(train20);
        trains2.add(train21);

        PlanTransport p1 = new PlanTransport(EnumCompagnies.ES, trains1);
        PlanTransport p2 = new PlanTransport(EnumCompagnies.ES, trains2);

        List<TrainTranche> expected = new ArrayList<>();
        expected.add(new TrainTranche("20", tranche2));
        expected.add(new TrainTranche("10", tranche3));
        List<TrainTranche> res = new ArrayList<>();

        /* NEW */
        comparePlanTransport.compare(p1, p2);
        res.clear();
        res.addAll(comparePlanTransport.getNews());
        Assert.assertTrue("Test NEW, liste news", compareRes(expected, res));
        res.clear();
        res.addAll(comparePlanTransport.getDeletes());
        Assert.assertTrue("Test NEW, liste deletes", compareRes(new ArrayList<TrainTranche>(), res));
        res.clear();
        res.addAll(comparePlanTransport.getOthers());
        Assert.assertTrue("Test NEW, liste others", compareRes(new ArrayList<TrainTranche>(), res));

        /* DELETE */
        comparePlanTransport.compare(p2, p1);
        res.clear();
        res.addAll(comparePlanTransport.getNews());
        Assert.assertTrue("Test DELETE, liste news", compareRes(new ArrayList<TrainTranche>(), res));
        res.clear();
        res.addAll(comparePlanTransport.getDeletes());
        Assert.assertTrue("Test DELETE, liste deletes", compareRes(expected, res));
        res.clear();
        res.addAll(comparePlanTransport.getOthers());
        Assert.assertTrue("Test DELETE, liste others", compareRes(new ArrayList<TrainTranche>(), res));

        /* Other */
        comparePlanTransport.compare(p2, p2);
        res.clear();
        res.addAll(comparePlanTransport.getNews());
        Assert.assertTrue("Test Other, liste news", compareRes(new ArrayList<TrainTranche>(), res));
        res.clear();
        res.addAll(comparePlanTransport.getDeletes());
        Assert.assertTrue("Test Other, liste deletes", compareRes(new ArrayList<TrainTranche>(), res));
        res.clear();
        res.addAll(comparePlanTransport.getOthers());
        Assert.assertTrue("Test Other, liste others", compareRes(expected, res));

    }

    /**
     * Vérifie si les listes contiennent les mêmes valeurs.
     * @param tt1
     * @param tt2
     * @return
     */
    private boolean compareRes(List<TrainTranche> tt1, List<TrainTranche> tt2) {
        for (TrainTranche trainTranche : tt1) {
            if (!tt2.contains(trainTranche)) {
                return false;
            }
        }
        for (TrainTranche trainTranche : tt2) {
            if (!tt1.contains(trainTranche)) {
                return false;
            }
        }
        return true;
    }
}
