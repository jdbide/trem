/**
 * 
 */
package factory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.Compartiment;
import com.avancial.app.data.objetsMetier.PlanTransport.Composition;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumClasseService;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumEtatSpecification;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeRepas;
import com.avancial.app.data.objetsMetier.PlanTransport.FareProfile;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.GareHoraire;
import com.avancial.app.data.objetsMetier.PlanTransport.Horaire;
import com.avancial.app.data.objetsMetier.PlanTransport.MapTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Repas;
import com.avancial.app.data.objetsMetier.PlanTransport.Restriction;
import com.avancial.app.data.objetsMetier.PlanTransport.ServiceABord;
import com.avancial.app.data.objetsMetier.PlanTransport.Siege;
import com.avancial.app.data.objetsMetier.PlanTransport.Specification;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;
import com.avancial.app.data.objetsMetier.PlanTransport.Voiture;
import com.avancial.app.utilitaire.MapPlansDeTransport;

/**
 * @author hamza.laterem
 *
 */
public class PlanTransportFactory {

   /**
    * 
    */
   public PlanTransportFactory() {
   }

   public static MapPlansDeTransport createDataForCompare() {

      List<Train> listTrain_active = new ArrayList<>();
      List<Train> listTrain_draft = new ArrayList<>();

      // Cas Modify Train active n° 1
      // Train train_active_modify = new Train();
      // train_active_modify.setNumeroTrain("1");

      // Cas Regime split & Modify : Train active n° 2

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
      Regime regime1 = new Regime("Mo-We; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>());
      Regime regime2 = new Regime("Mo-Th; From 01/06/2016 to 10/12/2016", date1, date3, new ArrayList<Date>());
      Regime regime3 = new Regime("Fr; From 01/06/2016 to 10/12/2016", date3, date2, new ArrayList<Date>());

      CodeSat codeSat1 = new CodeSat("1", regime1);
      CodeSat codeSat2 = new CodeSat("2", regime1);
      CodeSat codeSat3 = new CodeSat("3", regime2);
      CodeSat codeSat4 = new CodeSat("4", regime2);

      FareProfile fareProfile1 = new FareProfile("G45", regime1);
      FareProfile fareProfile2 = new FareProfile("E87", regime2);
      FareProfile fareProfile3 = new FareProfile("S54", regime3);

      Repas repas1 = new Repas(EnumTypeRepas.Dejeuner, new Horaire(), regime1);
      Repas repas2 = new Repas(EnumTypeRepas.Dejeuner, new Horaire(), regime1);

      listCodeSat1.add(codeSat1);
      listCodeSat1.add(codeSat3);
      listCodeSat2.add(codeSat2);
      listCodeSat2.add(codeSat4);

      listFareProfile1.add(fareProfile1);
      listFareProfile2.add(fareProfile2);
      listFareProfile2.add(fareProfile3);

      listRepas1.add(repas1);
      listRepas1.add(repas2);
      listRepas2.add(repas2);

      MapTranche mapTranche1 = new MapTranche();
      MapTranche mapTranche2 = new MapTranche();

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
      tranche1.setNumeroTranche("4");
      tranche2.setNumeroTranche("4");
      tranche1.setRegime(regimeTranche);
      tranche2.setRegime(regimeTranche);
      tranche1.setTrancheStatut(EnumTrancheStatut.Ouvert);
      tranche2.setTrancheStatut(EnumTrancheStatut.Ouvert);

      Train train_active_regimeSplit = new Train();
      train_active_regimeSplit.setNumeroTrain("2");
      train_active_regimeSplit.getTranches().add(tranche1);

      Train train_draft_regimeSplit = new Train();
      train_draft_regimeSplit.setNumeroTrain("2");
      train_draft_regimeSplit.getTranches().add(tranche2);

      listTrain_active.add(train_active_regimeSplit);
      listTrain_draft.add(train_draft_regimeSplit);

      // Cas DELETE : Train active n°3
      Train train_active_delete = new Train();
      train_active_delete.setNumeroTrain("3");
      Tranche tranche_train_active_delete = new Tranche();
      tranche_train_active_delete.setNumeroTranche("6");
      Regime regime_tranche_train_active_delete = new Regime("Sa-Fr; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>());
      tranche_train_active_delete.setRegime(regime_tranche_train_active_delete);
      train_active_delete.getTranches().add(tranche_train_active_delete);

      listTrain_active.add(train_active_delete);

      // Cas Unchanged : Train active n°4
      Train train_commun = new Train();
      train_commun.setNumeroTrain("4");
      Tranche tranche_train_commune = new Tranche();
      tranche_train_commune.setNumeroTranche("8");
      Regime regime_tranche_train_commun = new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>());
      tranche_train_commune.setRegime(regime_tranche_train_commun);
      train_commun.getTranches().add(tranche_train_commune);
      Tranche tranche_train_commune_2 = new Tranche();
      tranche_train_commune_2.setNumeroTranche("9");
      tranche_train_commune_2.setRegime(regime_tranche_train_commun);
      train_commun.getTranches().add(tranche_train_commune_2);

      listTrain_draft.add(train_commun);
      listTrain_active.add(train_commun);

      // Cas New : Train Draft n°5
      Train train_draft_5 = new Train();
      train_draft_5.setNumeroTrain("5");
      Tranche tranche_train_draft_5 = new Tranche();
      tranche_train_draft_5.setNumeroTranche("10");
      tranche_train_draft_5.setRegime(new Regime("Mo-We; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

      /**
       * Init Dessert
       */
      Desserte des = new Desserte();
      des.setRegime(new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      Desserte des2 = new Desserte();
      des2.setRegime(new Regime("Mo-AZ; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

      Gare g = new Gare("GBSPX");
      Gare g2 = new Gare("GBEBB");
      Gare g3 = new Gare("GARE3");
      Gare g4 = new Gare("GARE4");

      Horaire h = new Horaire();

      GareHoraire gh1 = new GareHoraire(g, h);
      GareHoraire gh2 = new GareHoraire(g2, h);
      GareHoraire gh3 = new GareHoraire(g3, h);
      GareHoraire gh4 = new GareHoraire(g4, h);

      List<GareHoraire> lGh = new ArrayList<>();
      List<GareHoraire> lGh2 = new ArrayList<>();

      lGh.add(gh1);
      lGh.add(gh2);

      lGh2.add(gh3);
      lGh2.add(gh4);

      des.setGareHoraires(lGh);
      des2.setGareHoraires(lGh2);

      List<Desserte> listDes = new ArrayList<>();
      listDes.add(des);
      listDes.add(des2);

      /**
       * Init Od
       */
      OrigineDestination od1 = new OrigineDestination(g, g2, new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      OrigineDestination od2 = new OrigineDestination(g3, g2,
            new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      OrigineDestination od3 = new OrigineDestination(g2, g4,
            new Regime("Mo-AZ; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

      List<OrigineDestination> listOd = new ArrayList<>();
      listOd.add(od1);
      listOd.add(od2);
      listOd.add(od3);

      /**
       * Regime Distrib
       */
      Distribution d1 = new Distribution("C", new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      Distribution d2 = new Distribution("B", new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      Distribution d3 = new Distribution("D", new Regime("Mo-AZ; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

      List<Distribution> listDistri = new ArrayList<>();
      listDistri.add(d1);
      listDistri.add(d2);
      listDistri.add(d3);

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

      Composition compo1 = new Composition("A", "ESA", "15001H", "C01", voitures,
            new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

      List<Voiture> voitures2 = new ArrayList<>();

      Voiture v4 = new Voiture("004", new ArrayList<Compartiment>());
      Voiture v5 = new Voiture("005", new ArrayList<Compartiment>());
      Voiture v6 = new Voiture("006", new ArrayList<Compartiment>());

      voitures2.add(v4);
      voitures2.add(v5);
      voitures2.add(v6);

      Composition compo2 = new Composition("H", "ESH", "16001J", "C01", voitures2,
            new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

      Composition compo4 = new Composition("A", "ESA", "11001J", "C01", voitures2,
            new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

      List<Voiture> voitures3 = new ArrayList<>();

      Voiture v7 = new Voiture("007", new ArrayList<Compartiment>());
      Voiture v8 = new Voiture("008", new ArrayList<Compartiment>());
      Voiture v9 = new Voiture("009", new ArrayList<Compartiment>());

      voitures3.add(v7);
      voitures3.add(v8);
      voitures3.add(v9);

      Composition compo3 = new Composition("B", "DKT", "15001K", "D031", voitures3,
            new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

      List<Composition> listCompo = new ArrayList<>();
      listCompo.add(compo1);
      listCompo.add(compo2);
      listCompo.add(compo4);
      listCompo.add(compo3);

      /**
       * Regime CodeSat
       */
      /**
       * FareProfile
       */
      /**
       * TypeEquipement
       */
      TypeEquipement te1 = new TypeEquipement("TGT", new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      TypeEquipement te2 = new TypeEquipement("TGR", new Regime("Mo-EA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
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
       * ServiceAbord
       */
      ServiceABord sab1 = new ServiceABord("NY", EnumClasseService.Premiere, g, g2,
            new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      ServiceABord sab2 = new ServiceABord("NE", EnumClasseService.Premiere, g, g2,
            new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      ServiceABord sab3 = new ServiceABord("BAR", EnumClasseService.Toute, g3, g,
            new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      ServiceABord sab4 = new ServiceABord("AH", EnumClasseService.Premiere, g4, g3,
            new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      ServiceABord sab5 = new ServiceABord("IT", EnumClasseService.Premiere, g, g2,
            new Regime("Mo-AA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      ServiceABord sab6 = new ServiceABord("DZ", EnumClasseService.Toute, g2, g3,
            new Regime("Mo-AA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      ServiceABord sab7 = new ServiceABord("FR", EnumClasseService.Toute, g3, g4,
            new Regime("Mo-TA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

      List<ServiceABord> listServiceABord = new ArrayList<>();
      listServiceABord.add(sab1);
      listServiceABord.add(sab2);
      listServiceABord.add(sab3);
      listServiceABord.add(sab4);
      listServiceABord.add(sab5);
      listServiceABord.add(sab6);
      listServiceABord.add(sab7);

      /**
       * Specificities
       */
      Siege s1 = new Siege("011");
      Siege s2 = new Siege("012");
      Siege s3 = new Siege("013");
      Siege s4 = new Siege("014");
      Siege s5 = new Siege("015");
      Siege s6 = new Siege("020");
      Siege s7 = new Siege("021");

      List<Siege> listS1 = new ArrayList<>();

      listS1.add(s1);
      listS1.add(s2);
      listS1.add(s3);
      listS1.add(s4);
      listS1.add(s5);
      listS1.add(s6);
      listS1.add(s7);

      Compartiment c1 = new Compartiment("010", listS1);
      Compartiment c2 = new Compartiment("018", null);

      List<Compartiment> listCompart = new ArrayList<>();
      List<Compartiment> listCompart2 = new ArrayList<>();
      listCompart.add(c1);
      listCompart2.add(c2);

      v1.setCompartiments(listCompart);
      v2.setCompartiments(listCompart2);

      Specification sp1 = new Specification(v1, EnumEtatSpecification.Blocked,
            new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      Specification sp2 = new Specification(v2, EnumEtatSpecification.Blocked,
            new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date2, date3, new ArrayList<Date>()));
      Specification sp3 = new Specification(v3, EnumEtatSpecification.Blocked,
            new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date3, new ArrayList<Date>()));

      Specification sp4 = new Specification(v3, EnumEtatSpecification.Blocked,
            new Regime("Mo-ZE; From 01/06/2016 to 10/12/2016", date1, date3, new ArrayList<Date>()));

      List<Specification> lsp = new ArrayList<>();
      lsp.add(sp1);
      lsp.add(sp2);
      lsp.add(sp3);
      lsp.add(sp4);

      /**
       * Regime Restriction
       */
      Restriction restr1 = new Restriction(g, g2, null, new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      Restriction restr2 = new Restriction(null, g, null, new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      Restriction restr3 = new Restriction(g, null, null, new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
      Restriction restr4 = new Restriction(g, null, null, new Regime("Mo-AA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

      List<Restriction> restrictions = new ArrayList<>();

      restrictions.add(restr1);
      restrictions.add(restr2);
      restrictions.add(restr3);
      restrictions.add(restr4);

      MapTranche mapTranche5 = new MapTranche();
      mapTranche5.put(fareProfile2.getClass(), listFareProfile2);
      mapTranche5.put(repas2.getClass(), listRepas1);
      mapTranche5.put(des.getClass(), listDes);
      mapTranche5.put(od1.getClass(), listOd);
      mapTranche5.put(d1.getClass(), listDistri);
      mapTranche5.put(compo2.getClass(), listCompo);
      mapTranche5.put(codeSat1.getClass(), listCodeSat1);
      mapTranche5.put(te5.getClass(), listTe);
      mapTranche5.put(sab1.getClass(), listServiceABord);
      mapTranche5.put(repas1.getClass(), listRepas1);
      mapTranche5.put(sp1.getClass(), lsp);
      mapTranche5.put(restr1.getClass(), restrictions);

      // mapTranche5.put();

      tranche_train_draft_5.getAttributs().putAll(mapTranche5);
      train_draft_5.getTranches().add(tranche_train_draft_5);

      listTrain_draft.add(train_draft_5);

      // Cas New : Train Draft n°6
      Train train_draft_6 = new Train();
      train_draft_6.setNumeroTrain("6");
      Tranche tranche_train_draft_6 = new Tranche();
      tranche_train_draft_6.setNumeroTranche("12");

      MapTranche mapTranche51 = new MapTranche();
      mapTranche51.put(fareProfile2.getClass(), listFareProfile2);
      mapTranche51.put(repas2.getClass(), listRepas2);
      mapTranche51.put(des.getClass(), listDes);
      mapTranche51.put(od1.getClass(), listOd);
      mapTranche51.put(d1.getClass(), listDistri);

      tranche_train_draft_6.getAttributs().putAll(mapTranche51);
      train_draft_6.getTranches().add(tranche_train_draft_6);

      listTrain_draft.add(train_draft_6);

      PlanTransport pt_draft = new PlanTransport(EnumCompagnies.ES, listTrain_draft);
      PlanTransport pt_active = new PlanTransport(EnumCompagnies.ES, listTrain_active);

      MapPlansDeTransport mapPlansDeTransport = new MapPlansDeTransport();

      mapPlansDeTransport.setMapPlansDeTransport(new JeuDonneeEntity(), pt_draft, new JeuDonneeEntity(), pt_active);

      return mapPlansDeTransport;
   }

   public static Date dateRef = new Date();

   public static MapPlansDeTransport createDataForCompareTrancheSplit() {
      List<Train> trainsDraft = new ArrayList<>();
      List<Train> trainsActive = new ArrayList<>();
      PlanTransport pdtDraft;
      PlanTransport pdtActive;

      MapPlansDeTransport mapPlansDeTransport = new MapPlansDeTransport();

      ArrayList<Tranche> tranchesAncien = new ArrayList<>();
      ArrayList<Tranche> tranchesNouveau = new ArrayList<>();

      Tranche trancheAncienDelete = getTrancheAncienDelete();
      tranchesAncien.add(trancheAncienDelete);

      Tranche trancheAncienSplit = getTrancheAncienSplit();
      tranchesAncien.add(trancheAncienSplit);

      Tranche trancheNouveauNew = getTrancheNouveauNew();
      tranchesNouveau.add(trancheNouveauNew);

      Tranche trancheNouveauSplit1 = getTrancheNouveauSplit1();
      tranchesNouveau.add(trancheNouveauSplit1);

      Tranche trancheNouveauSplit2 = getTrancheNouveauSplit2();
      tranchesNouveau.add(trancheNouveauSplit2);

      Train trainAncien = new Train();
      trainAncien.setNumeroTrain("1");
      trainAncien.setTranches(tranchesAncien);
      trainAncien.setValidForRR(true);
      Train trainNouveau = new Train();
      trainNouveau.setNumeroTrain("1");
      trainNouveau.setTranches(tranchesNouveau);
      trainNouveau.setValidForRR(true);

      trainsDraft.add(trainNouveau);
      trainsActive.add(trainAncien);

      pdtDraft = new PlanTransport(EnumCompagnies.ES, trainsDraft);
      pdtActive = new PlanTransport(EnumCompagnies.ES, trainsActive);

      mapPlansDeTransport.setMapPlansDeTransport(new JeuDonneeEntity(), pdtDraft, new JeuDonneeEntity(), pdtActive);

      return mapPlansDeTransport;
   }

   public static Tranche getTrancheAncienDelete() {
      Regime regimeDelete = generateRegime(generateDate(-10), generateDate(-9));
      regimeDelete.setCodeRegime("Delete");

      Tranche trancheAncienDelete = new Tranche();
      trancheAncienDelete.setNumeroTranche("Delete");
      trancheAncienDelete.setRegime(regimeDelete);
      trancheAncienDelete.setTrancheStatut(EnumTrancheStatut.Ouvert);
      return trancheAncienDelete;
   }

   public static Tranche getTrancheNouveauNew() {
      Regime regimeNew = generateRegime(generateDate(-50), generateDate(-45));
      Tranche trancheNouveauNew = new Tranche();
      trancheNouveauNew.setNumeroTranche("New");
      trancheNouveauNew.setRegime(regimeNew);
      trancheNouveauNew.setTrancheStatut(EnumTrancheStatut.Ouvert);
      return trancheNouveauNew;
   }

   public static Tranche getTrancheAncienSplit() {
      Regime regimeSplitAncien = generateRegime(generateDate(-40), generateDate(-20));
      regimeSplitAncien.setCodeRegime("Split");

      Tranche trancheAncienSplit = new Tranche();
      trancheAncienSplit.setNumeroTranche("Split");
      trancheAncienSplit.setRegime(regimeSplitAncien);
      trancheAncienSplit.setTrancheStatut(EnumTrancheStatut.Ouvert);
      return trancheAncienSplit;
   }

   public static Tranche getTrancheNouveauSplit1() {
      Regime regimeSplitNouveau1 = generateRegime(generateDate(-40), generateDate(-30));
      regimeSplitNouveau1.setCodeRegime("Split");

      Tranche trancheNouveauSplit1 = new Tranche();
      trancheNouveauSplit1.setNumeroTranche("Split");
      trancheNouveauSplit1.setRegime(regimeSplitNouveau1);
      trancheNouveauSplit1.setTrancheStatut(EnumTrancheStatut.Ouvert);
      return trancheNouveauSplit1;
   }

   public static Tranche getTrancheNouveauSplit2() {
      Regime regimeSplitNouveau2 = generateRegime(generateDate(-30), generateDate(-20));
      regimeSplitNouveau2.setCodeRegime("Split");

      Tranche trancheNouveauSplit2 = new Tranche();
      trancheNouveauSplit2.setNumeroTranche("Split");
      trancheNouveauSplit2.setRegime(regimeSplitNouveau2);
      trancheNouveauSplit2.setTrancheStatut(EnumTrancheStatut.Ouvert);
      return trancheNouveauSplit2;
   }

   /**
    * 
    * @param dateDebut
    * @param dateFin
    * @return Liste des dates contenues entre dateDebut et dateFin (les deux incluses)
    */
   public static List<Date> getListDate(Date dateDebut, Date dateFin) {
      Calendar calendar = Calendar.getInstance();
      List<Date> dates = new ArrayList<>();
      Date dateTmp = dateDebut;
      while (dateTmp.compareTo(dateFin) < 0) {
         dates.add(dateTmp);
         calendar.setTime(dateTmp);
         calendar.add(Calendar.DATE, 1);
         dateTmp = calendar.getTime();
      }
      return dates;
   }

   /**
    * 
    * @param dateDebut
    * @param dateFin
    * @return Regime dont la liste de dates contient celles contenues entre dateDebut et dateFin (les deux incluses, et settées en DateDebut et
    *         DateFin du régime)
    */
   public static Regime generateRegime(Date dateDebut, Date dateFin) {
      Regime regime = new Regime();
      regime.setDateDebut(dateDebut);
      regime.setDateFin(dateFin);
      regime.setListeJours(getListDate(dateDebut, dateFin));
      return regime;
   }

   /**
    * 
    * @param nbJours
    *           Nombre de jours positif ou négatif
    * @return Date correspondant à la date du jour à laquelle on a ajouté le nombre de jours en paramètre. Si le nombre de jours est négatif, on
    *         soustrait des jours à la date du jour.
    */
   public static Date generateDate(int nbJours) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(dateRef);
      calendar.add(Calendar.DATE, nbJours);
      return calendar.getTime();
   }

}
