package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import com.avancial.app.data.objetsMetier.PlanTransport.ARegimeComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeRepas;
import com.avancial.app.data.objetsMetier.PlanTransport.FareProfile;
import com.avancial.app.data.objetsMetier.PlanTransport.Horaire;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransportComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.MapTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Repas;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.comparePlanTransport.ComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.CompareTranche;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.TrainTranche;
import com.avancial.socle.utils.ListUtils;
import junit.framework.Assert;

public class TestComparePlanTransport {

    // @Test
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

    @Test
    public void testClone() {
        MapTranche mapTranche = new MapTranche();
        List<ARegimeComparable> l = new ArrayList<>();
        Regime regime = new Regime("0", new Date(), new Date());
        ARegimeComparable codeSat = new CodeSat("1", regime);
        l.add(codeSat);
        mapTranche.put(CodeSat.class, l);
        MapTranche yo = mapTranche.clone();
        @SuppressWarnings("unused")
        MapTranche you = yo.clone();
        mapTranche.clear();
        System.out.println("cooucou");
    }

    public void testTranche() {
        MapTranche mapTranche1 = new MapTranche();
        MapTranche mapTranche2 = new MapTranche();

        /* CodeSat MODIFY */
        List<ARegimeComparable> listCodeSat1 = new ArrayList<>();
        List<ARegimeComparable> listCodeSat2 = new ArrayList<>();
        /* FareProfile SPLIT */
        List<ARegimeComparable> listFareProfile1 = new ArrayList<>();
        List<ARegimeComparable> listFareProfile2 = new ArrayList<>();
        /* Repas UNCHENGED */
        List<ARegimeComparable> listRepas1 = new ArrayList<>();
        List<ARegimeComparable> listRepas2 = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -10);
        Date date1 = cal.getTime();
        cal.add(Calendar.DATE, 20);
        Date date2 = cal.getTime();
        cal.add(Calendar.DATE, -15);
        Date date3 = cal.getTime();
        cal.add(Calendar.DATE, 10);
        Date date4 = cal.getTime();

        Regime regimeTranche = new Regime("0", new Date(), new Date());
        Regime regime1 = new Regime("1", date1, date2);
        Regime regime2 = new Regime("2", date3, date4);
        ARegimeComparable codeSat1 = new CodeSat("1", regime1);
        ARegimeComparable codeSat2 = new CodeSat("2", regime1);
        ARegimeComparable fareProfile1 = new FareProfile("1", regime1);
        ARegimeComparable fareProfile2 = new FareProfile("1", regime2);
        ARegimeComparable repas1 = new Repas(EnumTypeRepas.Dejeuner, new Horaire(), regime1);
        ARegimeComparable repas2 = new Repas(EnumTypeRepas.Dejeuner, new Horaire(), regime1);

        listCodeSat1.add(codeSat1);
        listCodeSat2.add(codeSat2);
        listFareProfile1.add(fareProfile1);
        listFareProfile2.add(fareProfile2);
        listRepas1.add(repas1);
        listRepas2.add(repas2);

        mapTranche1.put(codeSat1.getClass(), listCodeSat1);
        mapTranche2.put(codeSat2.getClass(), listCodeSat2);
        mapTranche1.put(fareProfile1.getClass(), listFareProfile1);
        mapTranche2.put(fareProfile2.getClass(), listFareProfile2);
        mapTranche1.put(repas1.getClass(), listRepas1);
        mapTranche2.put(repas2.getClass(), listRepas2);

        Tranche tranche1 = new Tranche();
        Tranche tranche2 = new Tranche();

        tranche1.setAttributs(mapTranche1);
        tranche2.setAttributs(mapTranche2);
        tranche1.setNumeroTranche("1");
        tranche2.setNumeroTranche("1");
        tranche1.setRegime(regimeTranche);
        tranche2.setRegime(regimeTranche);
        tranche1.setTrancheStatut(EnumTrancheStatut.Ouvert);
        tranche2.setTrancheStatut(EnumTrancheStatut.Ouvert);

        IComparePlanTransport compareTranche = new CompareTranche();
        List<IComparaisonPlanTransport> comparaison = null;
        try {
            comparaison = compareTranche.compare(tranche1, tranche2);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        List<IComparaisonPlanTransport> expected = new ArrayList<IComparaisonPlanTransport>();
        ComparaisonPlanTransport<ARegimeComparable> codeSatExpected = new ComparaisonPlanTransport<>();
        codeSatExpected.setNumeroTranche("1");
        codeSatExpected.setAncienFields(listCodeSat1);
        codeSatExpected.setNouveauFields(listCodeSat2);
        codeSatExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.MODIFY);

        ComparaisonPlanTransport<ARegimeComparable> fareProfileExpected = new ComparaisonPlanTransport<>();
        fareProfileExpected.setNumeroTranche("1");
        fareProfileExpected.setAncienFields(listFareProfile1);
        fareProfileExpected.setNouveauFields(listFareProfile2);
        fareProfileExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.REGIMESPLIT);
        
        ComparaisonPlanTransport<ARegimeComparable> repasExpected = new ComparaisonPlanTransport<>();
        repasExpected.setNumeroTranche("1");
        repasExpected.setAncienFields(listRepas1);
        repasExpected.setNouveauFields(listRepas2);
        repasExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.UNCHANGED);
        
        expected.add(codeSatExpected);
        expected.add(fareProfileExpected);
        expected.add(repasExpected);

        Assert.assertTrue(ListUtils.compareLists(comparaison, expected));
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
