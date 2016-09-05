package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeRepas;
import com.avancial.app.data.objetsMetier.PlanTransport.FareProfile;
import com.avancial.app.data.objetsMetier.PlanTransport.Horaire;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.MapTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Repas;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.IComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.ComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.CompareTranche;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.socle.utils.ListUtils;
import junit.framework.Assert;

public class TestComparePlanTransport {

    // @Test
    public void testPlanTransport() {
        Train train1 = new Train();
        train1.setNumeroTrain("1");
        List<Train> trains1 = new ArrayList<>();
        trains1.add(train1);
        List<Train> trains2 = new ArrayList<>();

        Tranche tranche1 = new Tranche();
        tranche1.setNumeroTranche("10");
        train1.getTranches().add(tranche1);

        PlanTransport p1 = new PlanTransport(EnumCompagnies.ES, trains1);
        PlanTransport p2 = new PlanTransport(EnumCompagnies.ES, trains2);
        List<ComparaisonPlanTransport<IPlanTransport>> expected = new ArrayList<>();
        ComparaisonPlanTransport<IPlanTransport> cpt = new ComparaisonPlanTransport<>();
        cpt.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.DELETE);
        cpt.setNumeroTrain("1");
        cpt.setNumeroTranche("10");
        expected.add(cpt);

        IComparePlanTransport comparePlanTransport = new ComparePlanTransport();
        try {
            Assert.assertTrue("Compare DELETE PlanTransport", ListUtils.compareLists(expected, comparePlanTransport
                    .compare(p1, p2).getComparaison(EnumTypeComparaisonPlanTransport.DELETE, null)));
            cpt.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.NEW);
            Assert.assertTrue("Compare NEW PlanTransport", ListUtils.compareLists(expected,
                    comparePlanTransport.compare(p2, p1).getComparaison(EnumTypeComparaisonPlanTransport.NEW, null)));
            cpt.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.UNCHANGED);
            Assert.assertTrue("Compare UNCHANGED1 PlanTransport", ListUtils.compareLists(expected, comparePlanTransport
                    .compare(p1, p1).getComparaison(EnumTypeComparaisonPlanTransport.UNCHANGED, null)));
            Assert.assertTrue("Compare UNCHANGED2 PlanTransport", ListUtils.compareLists(null, comparePlanTransport
                    .compare(p2, p2).getComparaison(EnumTypeComparaisonPlanTransport.UNCHANGED, null)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    // @Test
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
        List<ASousRegimeTranche> l = new ArrayList<>();
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

    // @Test
    public void testClone() {
        MapTranche mapTranche = new MapTranche();
        List<ASousRegimeTranche> l = new ArrayList<>();
        Regime regime = new Regime("0", new Date(), new Date(), new ArrayList<Date>());
        ASousRegimeTranche codeSat = new CodeSat("1", regime);
        l.add(codeSat);
        mapTranche.put(CodeSat.class, l);
        MapTranche yo = mapTranche.clone();
        MapTranche you = yo.clone();
        mapTranche.clear();
        System.out.println("cooucou");
    }

    // @Test
    public void testTranche() {
        MapTranche mapTranche1 = new MapTranche();
        MapTranche mapTranche2 = new MapTranche();

        /* CodeSat MODIFY */
        List<CodeSat> listCodeSat1 = new ArrayList<>();
        List<CodeSat> listCodeSat2 = new ArrayList<>();
        /* FareProfile SPLIT */
        List<FareProfile> listFareProfile1 = new ArrayList<>();
        List<FareProfile> listFareProfile2 = new ArrayList<>();
        /* Repas UNCHANGED */
        List<Repas> listRepas1 = new ArrayList<>();
        List<Repas> listRepas2 = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -10);
        Date date1 = cal.getTime();
        cal.add(Calendar.DATE, 20);
        Date date2 = cal.getTime();
        cal.add(Calendar.DATE, -15);
        Date date3 = cal.getTime();

        Regime regimeTranche = new Regime("0", new Date(), new Date(), new ArrayList<Date>());
        Regime regime1 = new Regime("1", date1, date2, new ArrayList<Date>());
        Regime regime2 = new Regime("2", date1, date3, new ArrayList<Date>());
        Regime regime3 = new Regime("2", date3, date2, new ArrayList<Date>());
        CodeSat codeSat1 = new CodeSat("1", regime1);
        CodeSat codeSat2 = new CodeSat("2", regime1);
        FareProfile fareProfile1 = new FareProfile("1", regime1);
        FareProfile fareProfile2 = new FareProfile("1", regime2);
        FareProfile fareProfile3 = new FareProfile("1", regime3);
        Repas repas1 = new Repas(EnumTypeRepas.Dejeuner, new Horaire(), regime1);
        Repas repas2 = new Repas(EnumTypeRepas.Dejeuner, new Horaire(), regime1);

        listCodeSat1.add(codeSat1);
        listCodeSat2.add(codeSat2);

        listFareProfile1.add(fareProfile1);
        listFareProfile2.add(fareProfile2);
        listFareProfile2.add(fareProfile3);

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
        MapComparaisonPlanTransport comparaison = null;
        try {
            comparaison = compareTranche.compare(tranche1, tranche2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        List<IComparaisonPlanTransport> expected = new ArrayList<IComparaisonPlanTransport>();
        ComparaisonPlanTransport<ASousRegimeTranche> codeSatExpected = new ComparaisonPlanTransport<>();
        codeSatExpected.setNumeroTranche("1");
        codeSatExpected.setAncienField(codeSat1);
        codeSatExpected.setNouveauField(codeSat2);
        codeSatExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.MODIFY);

        ComparaisonPlanTransport<ASousRegimeTranche> fareProfileExpected = new ComparaisonPlanTransport<>();
        fareProfileExpected.setNumeroTranche("1");
        fareProfileExpected.setAncienField(fareProfile1);
        fareProfileExpected.setNouveauField(fareProfile2);
        fareProfileExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.REGIMESPLIT);

        expected.add(codeSatExpected);
        expected.add(fareProfileExpected);

        List<IComparaisonPlanTransport> res = new ArrayList<>();
        for (List<ComparaisonPlanTransport<IPlanTransport>> listComparaison : comparaison.values()) {
            res.addAll(listComparaison);
        }
        Assert.assertTrue(ListUtils.compareLists(res, expected));
    }

     @Test
    public void testRegimeSplit() {
        MapTranche mapTranche1 = new MapTranche();
        MapTranche mapTranche2 = new MapTranche();

        /* Reaps REGIMESPLIT */
        List<Repas> listRepas1 = new ArrayList<>();
        List<Repas> listRepas2 = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -10);
        Date date1 = cal.getTime();
        cal.add(Calendar.DATE, 20);
        Date date2 = cal.getTime();
        cal.add(Calendar.DATE, -15);
        Date date3 = cal.getTime();

        Regime regimeTranche = new Regime("0", new Date(), new Date(), new ArrayList<Date>());
        Regime regime1 = new Regime("1", date1, date2, new ArrayList<Date>());
        Regime regime2 = new Regime("2", date1, date3, new ArrayList<Date>());
        Regime regime3 = new Regime("2", date3, date2, new ArrayList<Date>());
        Horaire horaire = new Horaire();
        Repas repas1 = new Repas(EnumTypeRepas.Dejeuner, horaire, regime1);
        Repas repas2 = new Repas(EnumTypeRepas.Dejeuner, horaire, regime2);
        Repas repas3 = new Repas(EnumTypeRepas.diner, horaire, regime3);

        listRepas1.add(repas1);
        listRepas2.add(repas2);
        listRepas2.add(repas3);

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
        MapComparaisonPlanTransport comparaison = null;
        try {
            comparaison = compareTranche.compare(tranche1, tranche2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        List<IComparaisonPlanTransport> expected = new ArrayList<IComparaisonPlanTransport>();
        ComparaisonPlanTransport<Repas> repasExpected = new ComparaisonPlanTransport<>();
        repasExpected.setNumeroTranche("1");
        repasExpected.setAncienField(repas1);
        repasExpected.setNouveauField(repas2);
        repasExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.REGIMESPLIT);
        ComparaisonPlanTransport<Repas> repasExpected2 = new ComparaisonPlanTransport<>();
        repasExpected2.setNumeroTranche("1");
        repasExpected2.setAncienField(repas1);
        repasExpected2.setNouveauField(repas3);
        repasExpected2.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.REGIMESPLIT);

        expected.add(repasExpected);
        expected.add(repasExpected2);

        List<IComparaisonPlanTransport> res = new ArrayList<>();
        for (List<ComparaisonPlanTransport<IPlanTransport>> listComparaison : comparaison.values()) {
            res.addAll(listComparaison);
        }
        Assert.assertTrue(ListUtils.compareLists(res, expected));
    }

    public boolean compareMaps(Map<Object, Object> m1, Map<Object, Object> m2) {
        Object tt = null;
        for (Object tt1 : m1.keySet()) {
            for (Object tt2 : m2.keySet()) {
                if (tt2.equals(tt1)) {
                    tt = tt2;
                    break;
                }
            }
            if (tt == null || !m1.get(tt1).equals(m2.get(tt))) {
                return false;
            }
        }
        for (Object tt2 : m2.keySet()) {
            for (Object tt1 : m1.keySet()) {
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
