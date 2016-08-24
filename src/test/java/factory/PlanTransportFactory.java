/**
 * 
 */
package factory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
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
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
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
   
   public static MapPlansDeTransport createDataForCompare () {
      
      List<Train> listTrain_active = new ArrayList<>();
      List<Train> listTrain_draft = new ArrayList<>();

      
      //Cas Modify Train active n° 1 
//      Train train_active_modify = new Train();
//      train_active_modify.setNumeroTrain("1");

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
      
      
      Regime regimeTranche = new Regime("0", new Date(), new Date());
      Regime regime1 = new Regime("Mo-We; From 01/06/2016 to 10/12/2016", date1, date2);
      Regime regime2 = new Regime("Mo-Th; From 01/06/2016 to 10/12/2016", date1, date3);
      Regime regime3 = new Regime("Fr; From 01/06/2016 to 10/12/2016", date3, date2);
      
      CodeSat codeSat1 = new CodeSat("1", regime1);
      CodeSat codeSat2 = new CodeSat("2", regime1);
      
      FareProfile fareProfile1 = new FareProfile("G45", regime1);
      FareProfile fareProfile2 = new FareProfile("E87", regime2);
      FareProfile fareProfile3 = new FareProfile("S54", regime3);
      
      Repas repas1 = new Repas(EnumTypeRepas.Dejeuner, new Horaire(), regime1);
      Repas repas2 = new Repas(EnumTypeRepas.Dejeuner, new Horaire(), regime1);
      
      listCodeSat1.add(codeSat1);
      listCodeSat2.add(codeSat2);

      listFareProfile1.add(fareProfile1);
      listFareProfile2.add(fareProfile2);
      listFareProfile2.add(fareProfile3);

      listRepas1.add(repas1);
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
      Regime regime_tranche_train_active_delete = new Regime("Sa-Fr; From 01/06/2016 to 10/12/2016", date1, date2);
      tranche_train_active_delete.setRegime(regime_tranche_train_active_delete);
      train_active_delete.getTranches().add(tranche_train_active_delete);
      
      listTrain_active.add(train_active_delete);
      
      // Cas Unchanged : Train active n°4
      Train train_commun = new Train();
      train_commun.setNumeroTrain("4");
      Tranche tranche_train_commune = new Tranche();
      tranche_train_commune.setNumeroTranche("8");
      Regime regime_tranche_train_commun = new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2);
      tranche_train_commune.setRegime(regime_tranche_train_commun);
      train_commun.getTranches().add(tranche_train_commune);
      
      listTrain_draft.add(train_commun);
      listTrain_active.add(train_commun);
      
      
      // Cas New : Train Draft n°5
      Train train_draft_5 = new Train();
      train_draft_5.setNumeroTrain("5");
      Tranche tranche_train_draft_5 = new Tranche();
      tranche_train_draft_5.setNumeroTranche("10");
      tranche_train_draft_5.setRegime(new Regime("Mo-We; From 01/06/2016 to 10/12/2016", date1, date2));
      
      /**
       * Init Dessert
       */
      Desserte des = new Desserte();
      des.setRegime(new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2));
      Desserte des2 = new Desserte();
      des2.setRegime(new Regime("Mo-AZ; From 01/06/2016 to 10/12/2016", date1, date2));
      
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
      OrigineDestination od1 = new OrigineDestination(g, g2, new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2));
      OrigineDestination od2 = new OrigineDestination(g3, g2, new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2));
      OrigineDestination od3 = new OrigineDestination(g2, g4, new Regime("Mo-AZ; From 01/06/2016 to 10/12/2016", date1, date2));
      
      List<OrigineDestination> listOd = new ArrayList<>();
      listOd.add(od1);
      listOd.add(od2);
      listOd.add(od3);
      
      /**
       * Regime Distrib
       */
      Distribution d1 =  new Distribution("C", new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2));
      Distribution d2 =  new Distribution("B", new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2));
      Distribution d3 =  new Distribution("D", new Regime("Mo-AZ; From 01/06/2016 to 10/12/2016", date1, date2));
      
      List<Distribution> listDistri = new ArrayList<>();
      listDistri.add(d1);
      listDistri.add(d2);
      listDistri.add(d3);
      
      MapTranche mapTranche5 = new MapTranche();
      mapTranche5.put(fareProfile2.getClass(), listFareProfile2);
      mapTranche5.put(repas2.getClass(), listRepas1);
      mapTranche5.put(des.getClass(), listDes);
      mapTranche5.put(od1.getClass(), listOd);
      mapTranche5.put(d1.getClass(), listDistri);
      
      
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
      mapTranche51.put(repas2.getClass(), listRepas1);
      mapTranche51.put(des.getClass(), listDes);
      mapTranche51.put(od1.getClass(), listOd);
      mapTranche51.put(d1.getClass(), listDistri);
      
      tranche_train_draft_6.getAttributs().putAll(mapTranche51);
      train_draft_6.getTranches().add(tranche_train_draft_6);
      
      listTrain_draft.add(train_draft_6);
      
      PlanTransport pt_draft = new PlanTransport(EnumCompagnies.ES, listTrain_draft);
      PlanTransport pt_active = new PlanTransport(EnumCompagnies.ES, listTrain_active);
      
      MapPlansDeTransport   mapPlansDeTransport = new MapPlansDeTransport();
      
      mapPlansDeTransport.setMapPlansDeTransport(pt_draft, pt_active);
      
      
      return mapPlansDeTransport;
   }

}
