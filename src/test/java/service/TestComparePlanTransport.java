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
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonDifferentielPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.IComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.ComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.CompareTrain;
import com.avancial.app.service.comparePlanTransport.CompareTranche;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.utils.ListUtils;

import factory.PlanTransportFactory;
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
      List<AComparaisonPlanTransport<IPlanTransport>> expected = new ArrayList<>();
      ComparaisonDifferentielPlanTransport<IPlanTransport> cpt = new ComparaisonDifferentielPlanTransport<>();
      cpt.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.DELETE);
      cpt.setNumeroTrain("1");
      cpt.setNumeroTranche("10");
      expected.add(cpt);

      IComparePlanTransport comparePlanTransport = new ComparePlanTransport();
      try {
         Assert.assertTrue("Compare DELETE PlanTransport",
               ListUtils.compareLists(expected, comparePlanTransport.compare(p1, p2).getComparaison(EnumTypeComparaisonPlanTransport.DELETE, null)));
         cpt.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.NEW);
         Assert.assertTrue("Compare NEW PlanTransport",
               ListUtils.compareLists(expected, comparePlanTransport.compare(p2, p1).getComparaison(EnumTypeComparaisonPlanTransport.NEW, null)));
         cpt.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.UNCHANGED);
         Assert.assertTrue("Compare UNCHANGED1 PlanTransport", ListUtils.compareLists(expected,
               comparePlanTransport.compare(p1, p1).getComparaison(EnumTypeComparaisonPlanTransport.UNCHANGED, null)));
         Assert.assertTrue("Compare UNCHANGED2 PlanTransport", ListUtils.compareLists(new ArrayList<AComparaisonPlanTransport<IPlanTransport>>(),
               comparePlanTransport.compare(p2, p2).getComparaison(EnumTypeComparaisonPlanTransport.UNCHANGED, null)));
      } catch (Exception e) {
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
      } catch (Exception e) {
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
   public void testAttributsTranche() {
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
      Date dateTmp = new Date();
      Calendar calTmp = Calendar.getInstance();
      ArrayList<Date> dates1 = new ArrayList<Date>();
      ArrayList<Date> dates2 = new ArrayList<Date>();
      ArrayList<Date> dates3 = new ArrayList<Date>();

      dateTmp = date1;
      while (dateTmp.compareTo(date2) < 0) {
         dates1.add(dateTmp);
         calTmp.setTime(dateTmp);
         calTmp.add(Calendar.DATE, 1);
         dateTmp = calTmp.getTime();
      }

      dateTmp = date1;
      while (dateTmp.compareTo(date3) < 0) {
         dates1.add(dateTmp);
         calTmp.setTime(dateTmp);
         calTmp.add(Calendar.DATE, 1);
         dateTmp = calTmp.getTime();
      }

      dateTmp = date3;
      while (dateTmp.compareTo(date2) < 0) {
         dates1.add(dateTmp);
         calTmp.setTime(dateTmp);
         calTmp.add(Calendar.DATE, 1);
         dateTmp = calTmp.getTime();
      }

      Regime regime1 = new Regime("1", date1, date2, dates1);
      Regime regime2 = new Regime("2", date1, date3, dates2);
      Regime regime3 = new Regime("3", date3, date2, dates3);
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
      } catch (Exception e) {
         e.printStackTrace();
      }

      List<IComparaisonPlanTransport> expected = new ArrayList<IComparaisonPlanTransport>();
      ComparaisonDifferentielPlanTransport<ASousRegimeTranche> codeSatExpected = new ComparaisonDifferentielPlanTransport<>();
      codeSatExpected.setNumeroTranche("1");
      codeSatExpected.setAncienField(codeSat1);
      codeSatExpected.setNouveauField(codeSat2);
      codeSatExpected.setTrancheStatut(tranche1.getTrancheStatut());
      codeSatExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.MODIFY);

      ComparaisonDifferentielPlanTransport<ASousRegimeTranche> fareProfileExpected = new ComparaisonDifferentielPlanTransport<>();
      fareProfileExpected.setNumeroTranche("1");
      fareProfileExpected.setAncienField(fareProfile1);
      fareProfileExpected.setNouveauField(fareProfile2);
      fareProfileExpected.setTrancheStatut(tranche1.getTrancheStatut());
      fareProfileExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.REGIMESPLIT);

      ComparaisonDifferentielPlanTransport<ASousRegimeTranche> fareProfileExpected2 = new ComparaisonDifferentielPlanTransport<>();
      fareProfileExpected2.setNumeroTranche("1");
      fareProfileExpected2.setAncienField(fareProfile1);
      fareProfileExpected2.setNouveauField(fareProfile3);
      fareProfileExpected2.setTrancheStatut(tranche1.getTrancheStatut());
      fareProfileExpected2.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.REGIMESPLIT);

      expected.add(codeSatExpected);
      expected.add(fareProfileExpected);
      expected.add(fareProfileExpected2);

      List<IComparaisonPlanTransport> res = new ArrayList<>();
      for (List<AComparaisonPlanTransport<IPlanTransport>> listComparaison : comparaison.values()) {
         res.addAll(listComparaison);
      }
      Assert.assertTrue(ListUtils.compareLists(res, expected));
   }

   // @Test
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

      Date dateTmp = new Date();
      Calendar calTmp = Calendar.getInstance();
      ArrayList<Date> dates1 = new ArrayList<Date>();
      ArrayList<Date> dates2 = new ArrayList<Date>();
      ArrayList<Date> dates3 = new ArrayList<Date>();

      dateTmp = date1;
      while (dateTmp.compareTo(date2) < 0) {
         dates1.add(dateTmp);
         calTmp.setTime(dateTmp);
         calTmp.add(Calendar.DATE, 1);
         dateTmp = calTmp.getTime();
      }

      dateTmp = date1;
      while (dateTmp.compareTo(date3) < 0) {
         dates1.add(dateTmp);
         calTmp.setTime(dateTmp);
         calTmp.add(Calendar.DATE, 1);
         dateTmp = calTmp.getTime();
      }

      dateTmp = date3;
      while (dateTmp.compareTo(date2) < 0) {
         dates1.add(dateTmp);
         calTmp.setTime(dateTmp);
         calTmp.add(Calendar.DATE, 1);
         dateTmp = calTmp.getTime();
      }

      Regime regimeTranche = new Regime("0", new Date(), new Date(), new ArrayList<Date>());
      Regime regime1 = new Regime("1", date1, date2, dates1);
      Regime regime2 = new Regime("2", date1, date3, dates2);
      Regime regime3 = new Regime("2", date3, date2, dates3);
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
      } catch (Exception e) {
         e.printStackTrace();
      }

      List<IComparaisonPlanTransport> expected = new ArrayList<IComparaisonPlanTransport>();
      ComparaisonDifferentielPlanTransport<Repas> repasExpected = new ComparaisonDifferentielPlanTransport<>();
      repasExpected.setNumeroTranche("1");
      repasExpected.setAncienField(repas1);
      repasExpected.setNouveauField(repas2);
      repasExpected.setTrancheStatut(EnumTrancheStatut.Ouvert);
      repasExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.REGIMESPLIT);
      ComparaisonDifferentielPlanTransport<Repas> repasExpected2 = new ComparaisonDifferentielPlanTransport<>();
      repasExpected2.setNumeroTranche("1");
      repasExpected2.setAncienField(repas1);
      repasExpected2.setNouveauField(repas3);
      repasExpected2.setTrancheStatut(EnumTrancheStatut.Ouvert);
      repasExpected2.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.REGIMESPLIT);

      expected.add(repasExpected);
      expected.add(repasExpected2);

      List<IComparaisonPlanTransport> res = new ArrayList<>();
      for (List<AComparaisonPlanTransport<IPlanTransport>> listComparaison : comparaison.values()) {
         res.addAll(listComparaison);
      }
      Assert.assertTrue(ListUtils.compareLists(res, expected));
   }

   @Test
   public void testTranche() {
      MapPlansDeTransport mapPlansDeTransport = PlanTransportFactory.createDataForCompareTrancheSplit();
      Train trainAncien = mapPlansDeTransport.getPlanTransportActive().getTrains().get(0);
      Train trainNouveau = mapPlansDeTransport.getPlanTransportDraft().getTrains().get(0);

      IComparePlanTransport compareTrain = new CompareTrain();
      MapComparaisonPlanTransport comparaison = null;
      try {
         comparaison = compareTrain.compare(trainAncien, trainNouveau);
      } catch (Exception e) {
         e.printStackTrace();
      }
      List<IComparaisonPlanTransport> res = new ArrayList<>();
      for (List<AComparaisonPlanTransport<IPlanTransport>> listComparaison : comparaison.values()) {
         res.addAll(listComparaison);
      }

      List<IComparaisonPlanTransport> expected = new ArrayList<IComparaisonPlanTransport>();
      List<IComparaisonPlanTransport> test = new ArrayList<IComparaisonPlanTransport>();
      ComparaisonDifferentielPlanTransport<Tranche> trancheExpected = new ComparaisonDifferentielPlanTransport<>();
      trancheExpected.setNumeroTrain(trainAncien.getNumeroTrain());
      trancheExpected.setTrancheStatut(EnumTrancheStatut.Ouvert);
      trancheExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.DELETE);
      trancheExpected.setNumeroTranche("Delete");
      trancheExpected.setRegimeTranche(PlanTransportFactory.getTrancheAncienDelete().getRegime());
      expected.add(trancheExpected);

      trancheExpected = new ComparaisonDifferentielPlanTransport<>();
      trancheExpected.setNumeroTrain(trainNouveau.getNumeroTrain());
      trancheExpected.setTrancheStatut(EnumTrancheStatut.Ouvert);
      trancheExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.NEW);
      trancheExpected.setNumeroTranche("New");
      trancheExpected.setRegimeTranche(PlanTransportFactory.getTrancheNouveauNew().getRegime());
      expected.add(trancheExpected);

      trancheExpected = new ComparaisonDifferentielPlanTransport<>();
      trancheExpected.setNumeroTrain("1");
      trancheExpected.setTrancheStatut(EnumTrancheStatut.Ouvert);
      trancheExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.REGIMESPLIT);
      trancheExpected.setNumeroTranche("Split");
      trancheExpected.setRegimeTranche(PlanTransportFactory.getTrancheNouveauSplit1().getRegime());
      trancheExpected.setAncienField(PlanTransportFactory.getTrancheAncienSplit());
      trancheExpected.setNouveauField(PlanTransportFactory.getTrancheNouveauSplit1());
      expected.add(trancheExpected);

      trancheExpected = new ComparaisonDifferentielPlanTransport<>();
      trancheExpected.setNumeroTrain("1");
      trancheExpected.setTrancheStatut(EnumTrancheStatut.Ouvert);
      trancheExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.REGIMESPLIT);
      trancheExpected.setNumeroTranche("Split");
      trancheExpected.setRegimeTranche(PlanTransportFactory.getTrancheNouveauSplit2().getRegime());
      trancheExpected.setAncienField(PlanTransportFactory.getTrancheAncienSplit());
      trancheExpected.setNouveauField(PlanTransportFactory.getTrancheNouveauSplit2());
      expected.add(trancheExpected);

      trancheExpected = new ComparaisonDifferentielPlanTransport<>();
      trancheExpected.setNumeroTrain("1");
      trancheExpected.setTrancheStatut(EnumTrancheStatut.Ouvert);
      trancheExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.UNCHANGED);
      trancheExpected.setNumeroTranche("Split");
      trancheExpected.setRegimeTranche(PlanTransportFactory.getTrancheNouveauSplit1().getRegime());
      expected.add(trancheExpected);

      trancheExpected = new ComparaisonDifferentielPlanTransport<>();
      trancheExpected.setNumeroTrain("1");
      trancheExpected.setTrancheStatut(EnumTrancheStatut.Ouvert);
      trancheExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.UNCHANGED);
      trancheExpected.setNumeroTranche("Split");
      trancheExpected.setRegimeTranche(PlanTransportFactory.getTrancheNouveauSplit2().getRegime());
      expected.add(trancheExpected);
      
      trancheExpected = new ComparaisonDifferentielPlanTransport<>();
      trancheExpected.setNumeroTrain("1");
      trancheExpected.setTrancheStatut(EnumTrancheStatut.Ouvert);
      trancheExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.MODIFY);
      trancheExpected.setAncienField(PlanTransportFactory.getTrancheAncienModify());
      trancheExpected.setNouveauField(PlanTransportFactory.getTrancheNouveauModify());
      trancheExpected.setNumeroTranche("Modify");
      trancheExpected.setRegimeTranche(PlanTransportFactory.getTrancheNouveauModify().getRegime());
      expected.add(trancheExpected);
      
      trancheExpected = new ComparaisonDifferentielPlanTransport<>();
      trancheExpected.setNumeroTrain("1");
      trancheExpected.setTrancheStatut(EnumTrancheStatut.Ouvert);
      trancheExpected.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.UNCHANGED);
      trancheExpected.setNumeroTranche("Modify");
      trancheExpected.setRegimeTranche(PlanTransportFactory.getTrancheNouveauModify().getRegime());
      expected.add(trancheExpected);


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
