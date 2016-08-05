package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import com.avancial.app.data.objetsMetier.PlanTransport.ARegimeComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransportComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.MapTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.comparePlanTransport.ComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.TrainTranche;
import com.avancial.socle.utils.ListUtils;
import junit.framework.Assert;

public class TestComparePlanTransport {

//    @Test
    public void testPlanTransport() {
        Train train1 = new Train(null, "1", true);
        List<Train> trains1 = new ArrayList<>();
        trains1.add(train1);
        List<Train> trains2 = new ArrayList<>();

        PlanTransport p1 = new PlanTransport(EnumCompagnies.ES, trains1);
        PlanTransport p2 = new PlanTransport(EnumCompagnies.ES, trains2);
        List<IComparaisonPlanTransport> expected = new ArrayList<>();
        ComparaisonPlanTransport<IPlanTransportComparable> cpt = new ComparaisonPlanTransport<>();
        cpt.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.DELETE);
        cpt.setNumeroTrain("1");
        expected.add(cpt);
        try {
            Assert.assertTrue("Compare DELETE PlanTransport", ListUtils.compareLists(expected, p1.compare(p2)));
            cpt.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.NEW);
            Assert.assertTrue(ListUtils.compareLists(expected, p2.compare(p1)));
            Assert.assertTrue(ListUtils.compareLists(new ArrayList<IComparaisonPlanTransport>(), p1.compare(p1)));
            Assert.assertTrue(ListUtils.compareLists(new ArrayList<IComparaisonPlanTransport>(), p2.compare(p2)));
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void testChaine() {
        Train train1 = new Train();
        train1.setNumeroTrain("1");
        List<Train> trains1 = new ArrayList<>();
        trains1.add(train1);
        List<Train> trains2 = new ArrayList<>();
        trains2.add(train1);
        
        Tranche tranche1 = new Tranche();
        tranche1.setNumeroTranche("10");
        train1.getTranches().add(tranche1);
        
        CodeSat codeSat1 = new CodeSat();
        codeSat1.setCodeSat("100");
        List<ARegimeComparable> l = new ArrayList<>();
        l.add(codeSat1);
        tranche1.addAttributsField(l);

        PlanTransport p1 = new PlanTransport(EnumCompagnies.ES, trains1);
        PlanTransport p2 = new PlanTransport(EnumCompagnies.ES, trains2);
        
        IComparePlanTransport comparePlanTransport = new ComparePlanTransport();
        try {
            comparePlanTransport.compare(p1, p2);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void testClone() {
        MapTranche mapTranche = new MapTranche();
        List<ARegimeComparable> l = new ArrayList<>();
        Regime regime = new Regime("0", new Date(), new Date());
        ARegimeComparable codeSat = new CodeSat("1", regime);
        l.add(codeSat);
        mapTranche.put(CodeSat.class, l);
        MapTranche yo = mapTranche.clone();
        MapTranche you = yo.clone();
        mapTranche.clear();
        System.out.println("cooucou");
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
