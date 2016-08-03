package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.comparePlanTransport.ComparaisonTrainTranche;
import com.avancial.app.service.comparePlanTransport.ComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.TrainTranche;
import com.avancial.socle.utils.ListUtils;
import junit.framework.Assert;

public class TestComparePlanTransport {

    private ComparePlanTransport comparePlanTransport = new ComparePlanTransport();

    @Test
    public void testNewDelete() {

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
        Map<TrainTranche, TrainTranche> expectedMap = new HashMap<>();
        expectedMap.put(new TrainTranche("20", tranche2), new TrainTranche("20", tranche2));
        expectedMap.put(new TrainTranche("10", tranche3), new TrainTranche("10", tranche3));
        List<TrainTranche> res = new ArrayList<>();
        Map<TrainTranche, TrainTranche> resMap = new HashMap<>();

        /* NEW */
        this.comparePlanTransport.compare(p1, p2);
        res.clear();
        res.addAll(this.comparePlanTransport.getNews());
        Assert.assertTrue("Test NEW, liste news", ListUtils.compareLists(expected, res));
        res.clear();
        res.addAll(this.comparePlanTransport.getDeletes());
        Assert.assertTrue("Test NEW, liste deletes", ListUtils.compareLists(new ArrayList<TrainTranche>(), res));
        resMap.clear();
        resMap.putAll(this.comparePlanTransport.getOthers());
        Assert.assertTrue("Test NEW, liste others", compareMaps(new HashMap<TrainTranche, TrainTranche>(), resMap));

        /* DELETE */
        this.comparePlanTransport.compare(p2, p1);
        res.clear();
        res.addAll(this.comparePlanTransport.getNews());
        Assert.assertTrue("Test DELETE, liste news", ListUtils.compareLists(new ArrayList<TrainTranche>(), res));
        res.clear();
        res.addAll(this.comparePlanTransport.getDeletes());
        Assert.assertTrue("Test DELETE, liste deletes", ListUtils.compareLists(expected, res));
        resMap.clear();
        resMap.putAll(this.comparePlanTransport.getOthers());
        Assert.assertTrue("Test DELETE, liste others", compareMaps(new HashMap<TrainTranche, TrainTranche>(), resMap));

        /* Other */
        this.comparePlanTransport.compare(p2, p2);
        res.clear();
        res.addAll(this.comparePlanTransport.getNews());
        Assert.assertTrue("Test Other, liste news", ListUtils.compareLists(new ArrayList<TrainTranche>(), res));
        res.clear();
        res.addAll(this.comparePlanTransport.getDeletes());
        Assert.assertTrue("Test Other, liste deletes", ListUtils.compareLists(new ArrayList<TrainTranche>(), res));
        resMap.clear();
        resMap.putAll(this.comparePlanTransport.getOthers());
        Assert.assertTrue("Test Other, liste others", compareMaps(expectedMap, resMap));

    }

    public void testOther() {
        Tranche tranche1 = new Tranche();
        tranche1.setNumeroTranche("1");
        Tranche tranche2 = new Tranche();
        tranche2.setNumeroTranche("1");

        List<Tranche> tranches1 = new ArrayList<>();
        tranches1.add(tranche1);
        List<Tranche> tranches2 = new ArrayList<>();
        tranches2.add(tranche2);

        List<Train> trains1 = new ArrayList<>();
        Train train1 = new Train(tranches1, "1", true);
        trains1.add(train1);

        List<Train> trains2 = new ArrayList<>();
        Train train2 = new Train(tranches2, "1", true);
        trains2.add(train2);

        PlanTransport p1 = new PlanTransport(EnumCompagnies.ES, trains1);
        PlanTransport p2 = new PlanTransport(EnumCompagnies.ES, trains2);
        /*
         * Deux plans de transport avec le même train-tranche : on va comparer
         * ces train-tranche
         */

        Regime regime = new Regime();
        regime.setCodeRegime("r");

        /* CodeSat */
        CodeSat codeSat1 = new CodeSat("1", regime);
        CodeSat codeSat2 = new CodeSat("2", regime);
        List<CodeSat> cs1 = new ArrayList<>();
        cs1.add(codeSat1);
        List<CodeSat> cs2 = new ArrayList<>();
        cs2.add(codeSat2);

        tranche1.addAttributsField(cs1);
        tranche2.addAttributsField(cs2);

        List<ComparaisonTrainTranche<CodeSat>> expected = new ArrayList<>();
        expected.add(new ComparaisonTrainTranche<CodeSat>("1", "1", cs1, cs2));

        /* Composition */
        /* Desserte */
        /* Distribution */
        /* FareProfile */
        /* OrigineDestination */
        /* Repas */
        /* Restriction */
        /* ServiceABord */
        /* Specification */
        /* TypeEquipement */
        /* Tranche */
    }

    public boolean compareMaps(Map<TrainTranche, TrainTranche> m1, Map<TrainTranche, TrainTranche> m2) {
        TrainTranche tt = null;
        for (TrainTranche tt1 : m1.keySet()) {
            for (TrainTranche tt2 : m2.keySet()) {
                if (tt2.equals(tt1)) {
                    tt = tt2;
                    break;
                }
            }
            if (tt == null || !m1.get(tt1).equals(m2.get(tt))) {
                return false;
            }
        }
        for (TrainTranche tt2 : m2.keySet()) {
            for (TrainTranche tt1 : m1.keySet()) {
                if (tt1.equals(tt2)) {
                    tt = tt1;
                    break;
                }
            }
            if (tt == null || !m2.get(tt2).equals(m1.get(tt))) {
                return false;
            }
        }
        return true;
    }

}
