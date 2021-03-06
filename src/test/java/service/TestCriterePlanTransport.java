package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Compartiment;
import com.avancial.app.data.objetsMetier.PlanTransport.Composition;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.GareHoraire;
import com.avancial.app.data.objetsMetier.PlanTransport.Horaire;
import com.avancial.app.data.objetsMetier.PlanTransport.MapTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tosp;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;
import com.avancial.app.data.objetsMetier.PlanTransport.Voiture;
import com.avancial.app.service.filtrePlanTransport.CritereEt;
import com.avancial.app.service.filtrePlanTransport.CritereOu;
import com.avancial.app.service.filtrePlanTransport.CriterePlanTransport;
import com.avancial.app.service.filtrePlanTransport.CritereTrancheOD;
import com.avancial.app.service.filtrePlanTransport.FiltreEtPlanTransport;
import com.avancial.app.service.filtrePlanTransport.FiltreEtRegimePlanTransport;
import com.avancial.app.service.filtrePlanTransport.FiltreGareDessertePlanTransport;
import com.avancial.app.service.filtrePlanTransport.FiltreGareDesserteRegimePlanTransport;
import com.avancial.app.service.filtrePlanTransport.FiltreOuPlanTransport;
import com.avancial.app.service.filtrePlanTransport.FiltreOuRegimePlanTransport;
import com.avancial.app.service.filtrePlanTransport.FiltreRegimePlanTransport;
import com.avancial.app.service.filtrePlanTransport.FiltreSousRegimePlanTransport;
import com.avancial.app.service.filtrePlanTransport.FiltreStatutTranche;
import com.avancial.app.service.filtrePlanTransport.ICritere;
import com.avancial.app.service.filtrePlanTransport.IFiltre;
import com.avancial.socle.utils.ListUtils;

import factory.PlanTransportFactory;
import junit.framework.Assert;

public class TestCriterePlanTransport {

   private Gare                     orig                = new Gare("ORIG");
   private Gare                     orig2               = new Gare("OR2");
   private Gare                     dest                = new Gare("DEST");
   private OrigineDestination       origineDestination1 = new OrigineDestination(this.orig, this.dest, PlanTransportFactory.generateRegime(PlanTransportFactory.generateDate(0), PlanTransportFactory.generateDate(5)));
   private OrigineDestination       origineDestination2 = new OrigineDestination(this.orig2, this.dest, PlanTransportFactory.generateRegime(PlanTransportFactory.generateDate(5), PlanTransportFactory.generateDate(15)));

   private Tosp                     tosp1               = new Tosp("tosp1", PlanTransportFactory.generateRegime(PlanTransportFactory.generateDate(0), PlanTransportFactory.generateDate(10)));
   private Tosp                     tosp2               = new Tosp("tosp2", PlanTransportFactory.generateRegime(PlanTransportFactory.generateDate(10), PlanTransportFactory.generateDate(15)));

   private Gare                     gare1               = new Gare("1");
   private Gare                     gare2               = new Gare("2");
   private Gare                     gare3               = new Gare("3");
   private Gare                     gare4               = new Gare("4");
   private GareHoraire              gareHoraire1        = new GareHoraire(this.gare1, new Horaire());
   private GareHoraire              gareHoraire2        = new GareHoraire(this.gare2, new Horaire());
   private GareHoraire              gareHoraire3        = new GareHoraire(this.gare3, new Horaire());
   private GareHoraire              gareHoraire4        = new GareHoraire(this.gare4, new Horaire());
   private List<GareHoraire>        gareHoraires1       = Arrays.asList(this.gareHoraire1, this.gareHoraire4);
   private List<GareHoraire>        gareHoraires2       = Arrays.asList(this.gareHoraire1, this.gareHoraire2, this.gareHoraire4);
   private List<GareHoraire>        gareHoraires3       = Arrays.asList(this.gareHoraire1, this.gareHoraire3, this.gareHoraire4);
   private List<GareHoraire>        gareHoraires4       = Arrays.asList(this.gareHoraire1, this.gareHoraire2, this.gareHoraire3, this.gareHoraire4);
   private Desserte                 desserte1           = new Desserte(this.gareHoraires1, PlanTransportFactory.generateRegime(PlanTransportFactory.generateDate(0), PlanTransportFactory.generateDate(4)));
   private Desserte                 desserte2           = new Desserte(this.gareHoraires2, PlanTransportFactory.generateRegime(PlanTransportFactory.generateDate(5), PlanTransportFactory.generateDate(9)));
   private Desserte                 desserte3           = new Desserte(this.gareHoraires3, PlanTransportFactory.generateRegime(PlanTransportFactory.generateDate(10), PlanTransportFactory.generateDate(14)));
   private Desserte                 desserte4           = new Desserte(this.gareHoraires4, PlanTransportFactory.generateRegime(PlanTransportFactory.generateDate(15), PlanTransportFactory.generateDate(19)));
   private List<ASousRegimeTranche> dessertes1          = Arrays.asList((ASousRegimeTranche) this.desserte1, (ASousRegimeTranche) this.desserte2, (ASousRegimeTranche) this.desserte3, (ASousRegimeTranche) this.desserte4);

   private Regime                   regimeTranche       = PlanTransportFactory.generateRegime(PlanTransportFactory.generateDate(0), PlanTransportFactory.generateDate(15));

   private List<ASousRegimeTranche> ods1                = Arrays.asList((ASousRegimeTranche) this.origineDestination1);
   private List<ASousRegimeTranche> ods2                = Arrays.asList((ASousRegimeTranche) this.origineDestination2);
   private List<ASousRegimeTranche> ods1Et2             = Arrays.asList((ASousRegimeTranche) this.origineDestination1, (ASousRegimeTranche) this.origineDestination2);
   private List<ASousRegimeTranche> tosps1              = Arrays.asList((ASousRegimeTranche) this.tosp1);
   private List<ASousRegimeTranche> tosps2              = Arrays.asList((ASousRegimeTranche) this.tosp2);
   private List<ASousRegimeTranche> tosps1Et2           = Arrays.asList((ASousRegimeTranche) this.tosp1, (ASousRegimeTranche) this.tosp2);

   @Test
   public void main() {
      CriterePlanTransport filtrePlanTransport = new CriterePlanTransport();

      Tranche tranche1 = new Tranche();
      tranche1.addAttributsField(this.ods1Et2);
      List<Tranche> tranches3 = new ArrayList<>();
      tranches3.add(tranche1);
      Train train3 = new Train(tranches3, "1", true);
      PlanTransport planTransport2 = new PlanTransport();
      planTransport2.getTrains().add(train3);
      Tranche tranche3 = new Tranche();
      tranche3.addAttributsField(this.ods1Et2);
      List<Tranche> tranches2 = new ArrayList<>();
      tranches2.add(tranche3);
      Train train2 = new Train(tranches2, "2", true);
      planTransport2.getTrains().add(train2);

      ICritere<Tranche> critereOD = new CritereTrancheOD(this.origineDestination1);
      PlanTransport planTransport = this.createPlanTransportTestOd();
      filtrePlanTransport.filtre(planTransport, critereOD);
      Assert.assertEquals("Test CritereTrancheOD", planTransport2, planTransport);

      ICritere<Tranche> critereOD2 = new CritereTrancheOD(this.origineDestination2);
      ICritere<Tranche> critereEt = new CritereEt<Tranche>(critereOD, critereOD2);
      planTransport = this.createPlanTransportTestOd();
      filtrePlanTransport.filtre(planTransport, critereEt);
      Assert.assertEquals("Test CritereTrainTrancheEt", planTransport2, planTransport);

      ICritere<Tranche> critereOu = new CritereOu<Tranche>(critereOD, critereOD2);
      planTransport = this.createPlanTransportTestOd();
      filtrePlanTransport.filtre(planTransport, critereOu);
      List<ASousRegimeTranche> ods2 = new ArrayList<>();
      ods2.add(this.origineDestination2);
      Tranche tranche2 = new Tranche();
      tranche2.addAttributsField(ods2);
      planTransport2.getTrains().get(0).getTranches().add(tranche2);
      Assert.assertEquals("Test CritereTrainTrancheOu", planTransport2, planTransport);
   }

   @Test
   public void filtreOd() {
      Tranche tranche1 = new Tranche();
      tranche1.addAttributsField(this.ods1);
      Tranche tranche2 = new Tranche();
      tranche2.addAttributsField(this.ods2);
      Tranche tranche3 = new Tranche();
      tranche3.addAttributsField(this.ods1Et2);

      List<Tranche> tranches1 = new ArrayList<>();
      tranches1.add(tranche1);
      List<Tranche> tranches2 = new ArrayList<>();
      tranches2.add(tranche1);

      Train train1 = new Train(tranches1, "1", true);
      Train train2 = new Train(tranches2, "2", true);

      PlanTransport planTransportExpected = new PlanTransport();

      planTransportExpected.getTrains().add(train1);
      planTransportExpected.getTrains().add(train2);

      PlanTransport planTransportFiltre = this.createPlanTransportTestOd();

      IFiltre<PlanTransport> filtreOD = new FiltreSousRegimePlanTransport(this.origineDestination1);
      PlanTransport planTransportRes = filtreOD.filtreParCritere(planTransportFiltre);
      Assert.assertTrue("Test FiltreTrancheOD1", this.comparePlanTransport(planTransportExpected, planTransportRes));

      IFiltre<PlanTransport> filtreOD2 = new FiltreSousRegimePlanTransport(this.origineDestination2);
      planTransportRes = filtreOD2.filtreParCritere(planTransportFiltre);
      tranches1.clear();
      tranches1.add(tranche2);
      tranches1.add(tranche2);
      tranches2.clear();
      tranches2.add(tranche2);
      Assert.assertTrue("Test FiltreTrancheOD2", this.comparePlanTransport(planTransportExpected, planTransportRes));

      IFiltre<PlanTransport> filtreOu = new FiltreOuPlanTransport(filtreOD, filtreOD2);
      planTransportRes = filtreOu.filtreParCritere(planTransportFiltre);
      tranches1.clear();
      tranches1.add(tranche3);
      tranches1.add(tranche2);
      tranches2.clear();
      tranches2.add(tranche3);
      Assert.assertTrue("Test FiltreOuOD1OD2", this.comparePlanTransport(planTransportExpected, planTransportRes));

      IFiltre<PlanTransport> filtreEt = new FiltreEtPlanTransport(filtreOD, filtreOD2);
      planTransportRes = filtreEt.filtreParCritere(planTransportFiltre);
      planTransportExpected.getTrains().clear();
      Assert.assertTrue("Test FiltreEtOD1OD2", this.comparePlanTransport(planTransportExpected, planTransportRes));
   }

   @Test
   public void filtreTosp() {
      Tranche tranche1 = new Tranche();
      tranche1.addAttributsField(this.tosps1);
      Tranche tranche2 = new Tranche();
      tranche2.addAttributsField(this.tosps2);
      Tranche tranche3 = new Tranche();
      tranche3.addAttributsField(this.tosps1Et2);

      List<Tranche> tranches1 = new ArrayList<>();
      tranches1.add(tranche1);
      List<Tranche> tranches2 = new ArrayList<>();
      tranches2.add(tranche1);

      Train train1 = new Train(tranches1, "1", true);
      Train train2 = new Train(tranches2, "2", true);

      PlanTransport planTransportExpected = new PlanTransport();

      planTransportExpected.getTrains().add(train1);
      planTransportExpected.getTrains().add(train2);

      PlanTransport planTransportFiltre = this.createPlanTransportTestTosp();
      IFiltre<PlanTransport> filtreTosp1 = new FiltreSousRegimePlanTransport(this.tosp1);
      PlanTransport planTransportRes = filtreTosp1.filtreParCritere(planTransportFiltre);
      Assert.assertTrue("Test FiltreTrancheTosp1", this.comparePlanTransport(planTransportExpected, planTransportRes));

      IFiltre<PlanTransport> filtreTosp2 = new FiltreSousRegimePlanTransport(this.tosp2);
      planTransportRes = filtreTosp2.filtreParCritere(planTransportFiltre);
      tranches1.clear();
      tranches1.add(tranche2);
      tranches1.add(tranche2);
      planTransportExpected.getTrains().remove(train2);
      Assert.assertTrue("Test FiltreTrancheTosp2", this.comparePlanTransport(planTransportExpected, planTransportRes));

      IFiltre<PlanTransport> filtreOu = new FiltreOuPlanTransport(filtreTosp1, filtreTosp2);
      planTransportRes = filtreOu.filtreParCritere(planTransportFiltre);
      tranches1.clear();
      tranches1.add(tranche3);
      tranches1.add(tranche2);
      planTransportExpected.getTrains().add(train2);
      tranches2.clear();
      tranches2.add(tranche1);
      Assert.assertTrue("Test FiltreOuTosp1Tosp2", this.comparePlanTransport(planTransportExpected, planTransportRes));

      IFiltre<PlanTransport> filtreEt = new FiltreEtPlanTransport(filtreTosp1, filtreTosp2);
      planTransportRes = filtreEt.filtreParCritere(planTransportFiltre);
      planTransportExpected.getTrains().clear();
      Assert.assertTrue("Test FiltreEtTosp1Tosp2", this.comparePlanTransport(planTransportExpected, planTransportRes));
   }

   @Test
   public void filtreWebServices() {
      
      TypeEquipement typeEquipement = new TypeEquipement();
      typeEquipement.setTypeEquipement("equipement");
      Composition composition = new Composition();
      composition.setCodeRm("composition");
      Tosp tosp = new Tosp();
      tosp.setOureCode("tosp");

      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.DATE, -10);
      Date date1 = cal.getTime();
      cal.add(Calendar.DATE, 20);
      Date date2 = cal.getTime();

      /**
       * Regime Compo
       */

      List<Voiture> voitures = new ArrayList<>();

      Voiture v1 = new Voiture("001", new ArrayList<Compartiment>());
      Voiture v2 = new Voiture("003", new ArrayList<Compartiment>());
      Voiture v3 = new Voiture("003", new ArrayList<Compartiment>());

      voitures.add(v1);
      voitures.add(v2);
      voitures.add(v3);

      Composition compo1 = new Composition("A", "ESA", "15001H", "C01", voitures, new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

      List<Voiture> voitures2 = new ArrayList<>();

      Voiture v4 = new Voiture("004", new ArrayList<Compartiment>());
      Voiture v5 = new Voiture("005", new ArrayList<Compartiment>());
      Voiture v6 = new Voiture("006", new ArrayList<Compartiment>());

      voitures2.add(v4);
      voitures2.add(v5);
      voitures2.add(v6);

      Composition compo2 = new Composition("H", "ESH", "16001J", "C01", voitures2, new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

      Composition compo4 = new Composition("A", "ESA", "11001J", "composition", voitures2, new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

      List<Voiture> voitures3 = new ArrayList<>();

      Voiture v7 = new Voiture("007", new ArrayList<Compartiment>());
      Voiture v8 = new Voiture("008", new ArrayList<Compartiment>());
      Voiture v9 = new Voiture("009", new ArrayList<Compartiment>());

      voitures3.add(v7);
      voitures3.add(v8);
      voitures3.add(v9);

      Composition compo3 = new Composition("B", "DKT", "15001K", "D031", voitures3, new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

      List<Composition> listCompo = new ArrayList<>();
      listCompo.add(compo1);
      listCompo.add(compo2);
      listCompo.add(compo4);
      listCompo.add(compo3);

      /**
       * TypeEquipement
       */
      TypeEquipement te1 = new TypeEquipement("TGT", new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      TypeEquipement te2 = new TypeEquipement("equipement", new Regime("Mo-EA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      TypeEquipement te3 = new TypeEquipement("TIT", new Regime("Mo-AA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      TypeEquipement te4 = new TypeEquipement("TBT", new Regime("Mo-RA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      TypeEquipement te5 = new TypeEquipement("TNT", new Regime("Mo-TA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

      List<TypeEquipement> listTe = new ArrayList<>();
      listTe.add(te1);
      listTe.add(te2);
      listTe.add(te3);
      listTe.add(te4);
      listTe.add(te5);

      /**
       * Tosp
       */

      Tosp tosp1 = new Tosp("ddd", new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      Tosp tosp2 = new Tosp("ddd", new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      Tosp tosp3 = new Tosp("tosp", new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      Tosp tosp4 = new Tosp("aaa", new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

      List<Tosp> liste = new ArrayList<>();
      liste.add(tosp1);
      liste.add(tosp2);
      liste.add(tosp3);
      liste.add(tosp4);

      /**
       *  
       */
      MapTranche map = new MapTranche();
      map.put(TypeEquipement.class, listTe);
      map.put(Composition.class, listCompo);
      map.put(Tosp.class, liste);

      Tranche tranche = new Tranche();
      tranche.setNumeroTranche("10");
      tranche.setRegime(new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      tranche.setTrancheStatut(EnumTrancheStatut.Ouvert);
      tranche.setAttributs(map);

      List<Tranche> tranches = new ArrayList<>();
      tranches.add(tranche);
      Train train = new Train();
      train.setNumeroTrain("train");
      train.setTranches(tranches);
      train.setValidForRR(true);

      List<Train> trains = new ArrayList<>();
      trains.add(train);
      PlanTransport pdt = new PlanTransport();
      pdt.setCompagnie(EnumCompagnies.ES);
      pdt.setTrains(trains);

      IFiltre<PlanTransport> filtreTosp = new FiltreSousRegimePlanTransport(tosp);
      IFiltre<PlanTransport> filtreTypeEqp = new FiltreSousRegimePlanTransport(typeEquipement);
      IFiltre<PlanTransport> filtreComposition = new FiltreSousRegimePlanTransport(composition);
      IFiltre<PlanTransport> filtreEt = new FiltreEtRegimePlanTransport(filtreTosp, filtreTypeEqp, filtreComposition);

      PlanTransport res = new PlanTransport();
      res = filtreEt.filtreParCritere(pdt);

      /**
       * Regime Compo
       */
      List<Composition> listCompo10 = new ArrayList<>();
      listCompo10.add(compo4);

      /**
       * TypeEquipement
       */

      List<TypeEquipement> listTe10 = new ArrayList<>();
      listTe10.add(te2);

      /**
       * Tosp
       */
      List<Tosp> liste10 = new ArrayList<>();
      liste10.add(tosp3);

      /**
       *  
       */
      MapTranche map10 = new MapTranche();
      map10.put(TypeEquipement.class, listTe10);
      map10.put(Composition.class, listCompo10);
      map10.put(Tosp.class, liste10);

      Tranche tranche10 = new Tranche();
      tranche10.setNumeroTranche("10");
      tranche10.setRegime(new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      tranche10.setTrancheStatut(EnumTrancheStatut.Ouvert);
      tranche10.setAttributs(map10);

      List<Tranche> tranches10 = new ArrayList<>();
      tranches10.add(tranche10);
      Train train10 = new Train();
      train10.setNumeroTrain("train");
      train10.setTranches(tranches);
      train10.setValidForRR(true);

      List<Train> trains10 = new ArrayList<>();
      trains10.add(train10);
      PlanTransport pdt10 = new PlanTransport();
      pdt10.setCompagnie(EnumCompagnies.ES);
      pdt10.setTrains(trains10);

      Assert.assertEquals("Test FiltreWebServices ", res, pdt10);
   }

   @Test
   public void filtreRegime() {
      Tranche tranche1 = new Tranche();
      tranche1.setRegime(this.regimeTranche);
      tranche1.addAttributsField(this.ods1Et2);

      Train train1 = new Train();
      train1.setTranches(Arrays.asList(tranche1));

      PlanTransport planTransport = new PlanTransport();
      planTransport.getTrains().add(train1);

      PlanTransport planTransportExpected = planTransport.clone();
      planTransportExpected.getTrains().get(0).getTranches().get(0).setRegime(PlanTransportFactory.generateRegime(PlanTransportFactory.generateDate(0), PlanTransportFactory.generateDate(5)));

      IFiltre<PlanTransport> filtreRegimeOd1 = new FiltreRegimePlanTransport(this.origineDestination1);
      PlanTransport planTransportRes = filtreRegimeOd1.filtreParCritere(planTransport);
      Assert.assertTrue("Test FiltreRegime Od1", ListUtils.compareLists(planTransportExpected.getTrains().get(0).getTranches().get(0).getRegime().getListeJours(), planTransportRes.getTrains().get(0).getTranches().get(0).getRegime().getListeJours()));

      planTransportExpected.getTrains().get(0).getTranches().get(0).setRegime(PlanTransportFactory.generateRegime(PlanTransportFactory.generateDate(5), PlanTransportFactory.generateDate(15)));

      IFiltre<PlanTransport> filtreRegimeOd2 = new FiltreRegimePlanTransport(this.origineDestination2);
      planTransportRes = filtreRegimeOd2.filtreParCritere(planTransport);
      Assert.assertTrue("Test FiltreRegime Od2", ListUtils.compareLists(planTransportExpected.getTrains().get(0).getTranches().get(0).getRegime().getListeJours(), planTransportRes.getTrains().get(0).getTranches().get(0).getRegime().getListeJours()));

      planTransportExpected.getTrains().get(0).getTranches().get(0).setRegime(PlanTransportFactory.generateRegime(PlanTransportFactory.generateDate(0), PlanTransportFactory.generateDate(15)));

      IFiltre<PlanTransport> filtreRegimeOd1OuOd2 = new FiltreOuRegimePlanTransport(filtreRegimeOd1, filtreRegimeOd2);
      planTransportRes = filtreRegimeOd1OuOd2.filtreParCritere(planTransport);
      Assert.assertTrue("Test FiltreRegime Od1OuOd2", ListUtils.compareLists(planTransportExpected.getTrains().get(0).getTranches().get(0).getRegime().getListeJours(), planTransportRes.getTrains().get(0).getTranches().get(0).getRegime().getListeJours()));

      planTransportExpected.getTrains().get(0).getTranches().get(0).getRegime().getListeJours().clear();

      IFiltre<PlanTransport> filtreRegimeOd1EtOd2 = new FiltreEtRegimePlanTransport(filtreRegimeOd1, filtreRegimeOd2);
      planTransportRes = filtreRegimeOd1EtOd2.filtreParCritere(planTransport);
      Assert.assertTrue("Test FiltreRegime Od1EtOd2", ListUtils.compareLists(planTransportExpected.getTrains().get(0).getTranches().get(0).getRegime().getListeJours(), planTransportRes.getTrains().get(0).getTranches().get(0).getRegime().getListeJours()));
   }

   @SuppressWarnings("unchecked")
   @Test
   public void filtreDesserte() {
      Tranche tranche = new Tranche();
      tranche.setRegime(PlanTransportFactory.generateRegime(PlanTransportFactory.generateDate(0), PlanTransportFactory.generateDate(19)));
      tranche.addAttributsField(this.dessertes1);

      Train train = new Train();
      train.getTranches().add(tranche);

      PlanTransport planTransport = new PlanTransport();
      planTransport.getTrains().add(train);

      PlanTransport planTransportExpected = planTransport.clone();
      Tranche trancheExpected = planTransportExpected.getTrains().get(0).getTranches().get(0);
      List<ASousRegimeTranche> dessertesExpected = (List<ASousRegimeTranche>) trancheExpected.getAttributsField(Desserte.class);

      PlanTransport planTransportRes;

      IFiltre<PlanTransport> filtreDesserte = new FiltreGareDessertePlanTransport(Arrays.asList(this.gare2));
      dessertesExpected.remove(this.desserte1);
      dessertesExpected.remove(this.desserte3);
      planTransportRes = filtreDesserte.filtreParCritere(planTransport);
      Assert.assertTrue("Test FiltreGareDesserte", this.comparePlanTransport(planTransportExpected, planTransportRes));

      IFiltre<PlanTransport> filtreDesserteRegime = new FiltreGareDesserteRegimePlanTransport(Arrays.asList(this.gare2));
      dessertesExpected.add(this.desserte1);
      dessertesExpected.add(this.desserte3);
      List<Date> datesRegime = new ArrayList<>();
      datesRegime.addAll(PlanTransportFactory.getListDate(PlanTransportFactory.generateDate(5), PlanTransportFactory.generateDate(9)));
      datesRegime.addAll(PlanTransportFactory.getListDate(PlanTransportFactory.generateDate(15), PlanTransportFactory.generateDate(19)));
      trancheExpected.getRegime().setListeJours(datesRegime);
      planTransportRes = filtreDesserteRegime.filtreParCritere(planTransport);
      Assert.assertTrue("Test FiltreGareDesserteRegime", this.comparePlanTransport(planTransportExpected, planTransportRes));
   }

   @Test
   public void filtreStatutTranche() {
      Tranche tranche1 = new Tranche();
      tranche1.setTrancheStatut(EnumTrancheStatut.Ouvert);
      Tranche tranche2 = new Tranche();
      tranche2.setTrancheStatut(EnumTrancheStatut.Ferme);

      Train train = new Train();
      train.getTranches().add(tranche1);
      train.getTranches().add(tranche2);

      Train trainTrancheOuverte = new Train();
      train.getTranches().add(tranche1);

      Train trainTrancheFerme = new Train();
      train.getTranches().add(tranche2);

      IFiltre<Train> critereStatutOuvert = (IFiltre<Train>) new FiltreStatutTranche(EnumTrancheStatut.Ouvert);
      IFiltre<Train> critereStatutFerme = (IFiltre<Train>) new FiltreStatutTranche(EnumTrancheStatut.Ferme);

      Assert.assertEquals("Test Critere ", trainTrancheOuverte, critereStatutOuvert.filtreParCritere(train));
      Assert.assertEquals("Test Critere ", trainTrancheFerme, critereStatutFerme.filtreParCritere(train));
      // Assert.assertEquals("Test", true, true);

   }

   /**
    * Création d'un plan de transport composé ainsi:
    * <table>
    * <tr>
    * <td>Train1</td>
    * <td>Tranche1</td>
    * <td>OD1</td>
    * </tr>
    * <tr>
    * <td></td>
    * <td></td>
    * <td>OD2</td>
    * </tr>
    * <tr>
    * <td></td>
    * <td>Tranche2</td>
    * <td>OD2</td>
    * <tr>
    * <td>Train2</td>
    * <td>Tranche3</td>
    * <td>OD1</td>
    * </tr>
    * <tr>
    * <td></td>
    * <td></td>
    * <td>OD2</td>
    * </tr>
    * </table>
    * 
    * @return
    */
   private PlanTransport createPlanTransportTestOd() {
      List<ASousRegimeTranche> ods1 = new ArrayList<>();
      ods1.add(this.origineDestination2);
      ods1.add(this.origineDestination1);
      Tranche tranche1 = new Tranche();
      tranche1.addAttributsField(ods1);
      List<ASousRegimeTranche> ods2 = new ArrayList<>();
      ods2.add(this.origineDestination2);
      Tranche tranche2 = new Tranche();
      tranche2.addAttributsField(ods2);
      List<Tranche> tranches1 = new ArrayList<>();
      tranches1.add(tranche1);
      tranches1.add(tranche2);
      Train train1 = new Train(tranches1, "1", true);
      PlanTransport planTransport = new PlanTransport();
      planTransport.getTrains().add(train1);

      List<ASousRegimeTranche> ods3 = new ArrayList<>();
      ods3.add(this.origineDestination1);
      ods3.add(this.origineDestination2);
      Tranche tranche3 = new Tranche();
      tranche3.addAttributsField(ods3);
      List<Tranche> tranches2 = new ArrayList<>();
      tranches2.add(tranche3);
      Train train2 = new Train(tranches2, "2", true);
      planTransport.getTrains().add(train2);

      return planTransport;
   }

   /**
    * Création d'un plan de transport composé ainsi:
    * <table>
    * <tr>
    * <td>Train1</td>
    * <td>Tranche1</td>
    * <td>Tosp1</td>
    * </tr>
    * <tr>
    * <td></td>
    * <td></td>
    * <td>Tosp2</td>
    * </tr>
    * <tr>
    * <td></td>
    * <td>Tranche2</td>
    * <td>Tosp2</td>
    * <tr>
    * <td>Train2</td>
    * <td>Tranche3</td>
    * <td>Tosp1</td>
    * </tr>
    * </table>
    * 
    * @return
    */
   private PlanTransport createPlanTransportTestTosp() {
      List<ASousRegimeTranche> tosps1 = new ArrayList<>();
      tosps1.add(this.tosp1);
      tosps1.add(this.tosp2);
      Tranche tranche1 = new Tranche();
      tranche1.addAttributsField(tosps1);
      List<ASousRegimeTranche> tosps2 = new ArrayList<>();
      tosps2.add(this.tosp2);
      Tranche tranche2 = new Tranche();
      tranche2.addAttributsField(tosps2);
      List<Tranche> tranches1 = new ArrayList<>();
      tranches1.add(tranche1);
      tranches1.add(tranche2);
      Train train1 = new Train(tranches1, "1", true);
      PlanTransport planTransport = new PlanTransport();
      planTransport.getTrains().add(train1);

      List<ASousRegimeTranche> tosps3 = new ArrayList<>();
      tosps3.add(this.tosp1);
      Tranche tranche3 = new Tranche();
      tranche3.addAttributsField(tosps3);
      List<Tranche> tranches2 = new ArrayList<>();
      tranches2.add(tranche3);
      Train train2 = new Train(tranches2, "2", true);
      planTransport.getTrains().add(train2);

      return planTransport;
   }

   public boolean comparePlanTransport(PlanTransport pdt1, PlanTransport pdt2) {
      if (!pdt1.equals(pdt2)) {
         return false;
      }

      int index;
      Train train2;
      Tranche tranche2;
      for (Train train1 : pdt1.getTrains()) {
         index = pdt2.getTrains().indexOf(train1);
         train2 = pdt2.getTrains().get(index);
         for (Tranche tranche1 : train1.getTranches()) {
            index = train2.getTranches().indexOf(tranche1);
            tranche2 = train2.getTranches().get(index);
            if (!tranche1.getAttributs().equals(tranche2.getAttributs())) {
               return false;
            }
         }
      }
      return true;
   }
}
