/**
 * 
 */
package factory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.formula.functions.T;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
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
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeRestriction;
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
import com.avancial.app.data.objetsMetier.PlanTransport.Tosp;
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

    public static MapPlansDeTransport createDataForControle() {

        List<Train> listTrain_base = new ArrayList<>();
        List<Train> listTrain_xls = new ArrayList<>();

        Train trainEgal = new Train();
        Train trainControleBase = new Train();
        Train trainControleXls = new Train();

        Regime regimeEgal = generateRegime(generateDate(-1), generateDate(0));

        List<Tranche> tranchesEgal = new ArrayList<>();
        Tranche trancheEgal = new Tranche();
        trancheEgal.setNumeroTranche("trancheEgal");
        trancheEgal.setTrancheStatut(EnumTrancheStatut.Ouvert);
        trancheEgal.setRegime(regimeEgal);
        tranchesEgal.add(trancheEgal);

        trainEgal.setNumeroTrain("trainEgal");
        trainEgal.setValidForRR(true);
        trainEgal.setTranches(tranchesEgal);

        listTrain_base.add(trainEgal);

        trainEgal = new Train();
        trancheEgal = new Tranche();
        trancheEgal.setNumeroTranche("trancheEgal");
        trancheEgal.setTrancheStatut(EnumTrancheStatut.Ferme);
        trancheEgal.setRegime(regimeEgal);
        tranchesEgal = new ArrayList<>();
        tranchesEgal.add(trancheEgal);

        trainEgal.setNumeroTrain("trainEgal");
        trainEgal.setValidForRR(true);
        trainEgal.setTranches(tranchesEgal);

        listTrain_xls.add(trainEgal);

        List<Tranche> tranchesControleBase = new ArrayList<>();
        List<Tranche> tranchesControleXls = new ArrayList<>();

        Tranche trancheControleBaseStatut = new Tranche();
        trancheControleBaseStatut.setNumeroTranche("trancheControleStatut");
        trancheControleBaseStatut.setTrancheStatut(EnumTrancheStatut.Ferme);
        trancheControleBaseStatut.setRegime(generateRegime(generateDate(-1), generateDate(0)));
        tranchesControleBase.add(trancheControleBaseStatut);

        Tranche trancheControleBaseRepas = new Tranche();
        trancheControleBaseRepas.setNumeroTranche("trancheControleCodeSat");
        trancheControleBaseRepas.setTrancheStatut(EnumTrancheStatut.Ouvert);
        trancheControleBaseRepas.setRegime(generateRegime(generateDate(-3), generateDate(-2)));
        List<ASousRegimeTranche> attributs = new ArrayList<>();
        CodeSat codeSat = new CodeSat();
        codeSat.setCodeSat("base");
        codeSat.setRegime(generateRegime(generateDate(-3), generateDate(-2)));
        attributs.add(codeSat);
        MapTranche map = new MapTranche();
        map.put(codeSat.getClass(), attributs);
        
        List<ASousRegimeTranche> attributsRepas = new ArrayList<>();
        Repas repas1 = new Repas();
        attributsRepas.add(repas1);
        Repas repas2 = new Repas();
        attributsRepas.add(repas2);
        map.put(repas1.getClass(), attributsRepas);
        
        trancheControleBaseRepas.setAttributs(map);
        tranchesControleBase.add(trancheControleBaseRepas);

        trainControleBase.setNumeroTrain("trainControle");
        trainControleBase.setValidForRR(true);
        trainControleBase.setTranches(tranchesControleBase);
        listTrain_base.add(trainControleBase);

        Tranche trancheControleXlsStatut = new Tranche();
        trancheControleXlsStatut.setNumeroTranche("");
        trancheControleXlsStatut.setTrancheStatut(EnumTrancheStatut.Ouvert);
        trancheControleXlsStatut.setRegime(generateRegime(generateDate(-1), generateDate(0)));
        tranchesControleXls.add(trancheControleXlsStatut);

        Tranche trancheControleXlsRepas = new Tranche();
        trancheControleXlsRepas.setNumeroTranche("");
        trancheControleXlsRepas.setTrancheStatut(EnumTrancheStatut.Ouvert);
        trancheControleXlsRepas.setRegime(generateRegime(generateDate(-3), generateDate(-2)));
        List<ASousRegimeTranche> attributsXls = new ArrayList<>();
        CodeSat codeSatXls = new CodeSat();
        codeSatXls.setCodeSat("xls");
        codeSatXls.setRegime(generateRegime(generateDate(-3), generateDate(-2)));
        attributsXls.add(codeSatXls);
        MapTranche mapXls = new MapTranche();
        mapXls.put(codeSatXls.getClass(), attributsXls);
        
        List<ASousRegimeTranche> attributsRepasXls = new ArrayList<>();
        Repas repasXls1 = new Repas(EnumTypeRepas.diner, new Horaire());
        attributsRepasXls.add(repasXls1);
        Repas repasXls2 = new Repas(EnumTypeRepas.diner, new Horaire());
        attributsRepasXls.add(repasXls2);
        mapXls.put(repasXls1.getClass(), attributsRepasXls);
        
        trancheControleXlsRepas.setAttributs(mapXls);
        tranchesControleXls.add(trancheControleXlsRepas);

        trainControleXls.setNumeroTrain("trainControle");
        trainControleXls.setValidForRR(true);
        trainControleXls.setTranches(tranchesControleXls);
        listTrain_xls.add(trainControleXls);

        PlanTransport pt_xls = new PlanTransport(EnumCompagnies.ES, listTrain_xls);
        PlanTransport pt_base = new PlanTransport(EnumCompagnies.ES, listTrain_base);

        MapPlansDeTransport mapPlansDeTransport = new MapPlansDeTransport();

        mapPlansDeTransport.setMapPlansDeTransport(new JeuDonneeEntity(), pt_xls, new JeuDonneeEntity(), pt_base);

        return mapPlansDeTransport;
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
        Regime regime_tranche_train_active_delete = new Regime("Sa-Fr; From 01/06/2016 to 10/12/2016", date1, date2,
                new ArrayList<Date>());
        tranche_train_active_delete.setRegime(regime_tranche_train_active_delete);
        train_active_delete.getTranches().add(tranche_train_active_delete);

        listTrain_active.add(train_active_delete);

        // Cas Unchanged : Train active n°4
        Train train_commun = new Train();
        train_commun.setNumeroTrain("4");
        Tranche tranche_train_commune = new Tranche();
        tranche_train_commune.setNumeroTranche("8");
        Regime regime_tranche_train_commun = new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2,
                new ArrayList<Date>());
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
        tranche_train_draft_5
                .setRegime(new Regime("Mo-We; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

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
        OrigineDestination od1 = new OrigineDestination(g, g2,
                new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
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
        Distribution d1 = new Distribution("C",
                new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
        Distribution d2 = new Distribution("B",
                new Regime("Mo-Fr; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
        Distribution d3 = new Distribution("D",
                new Regime("Mo-AZ; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

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
        TypeEquipement te1 = new TypeEquipement("TGT",
                new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
        TypeEquipement te2 = new TypeEquipement("TGR",
                new Regime("Mo-EA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
        TypeEquipement te3 = new TypeEquipement("TIT",
                new Regime("Mo-AA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
        TypeEquipement te4 = new TypeEquipement("TBT",
                new Regime("Mo-RA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
        TypeEquipement te5 = new TypeEquipement("TNT",
                new Regime("Mo-TA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

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
        Restriction restr1 = new Restriction(g, g2, null,
                new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
        Restriction restr2 = new Restriction(null, g, null,
                new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
        Restriction restr3 = new Restriction(g, null, null,
                new Regime("Mo-ZA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));
        Restriction restr4 = new Restriction(g, null, null,
                new Regime("Mo-AA; From 01/06/2016 to 10/12/2016", date1, date2, new ArrayList<Date>()));

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

    public static MapPlansDeTransport createDataForResultatFiltre() {

        /**
         * creation plan de transport
         */
        PlanTransport pdt = new PlanTransport();
        pdt.setCompagnie(EnumCompagnies.ES);

        /**
         * creation des trains
         */
        Train train0 = new Train();
        train0.setNumeroTrain("0");
        train0.setValidForRR(true);
        Train train1 = new Train();
        train1.setNumeroTrain("1");
        train1.setValidForRR(false);

        /**
         * creation des tranches
         */
        Tranche tranche00 = new Tranche();
        tranche00.setNumeroTranche("00");
        tranche00.setRegime(generateRegime(generateDate(0), generateDate(1)));
        tranche00.setTrancheStatut(EnumTrancheStatut.Ouvert);
        Tranche tranche01 = new Tranche();
        tranche01.setNumeroTranche("01");
        tranche01.setRegime(generateRegime(generateDate(-1), generateDate(0)));
        tranche01.setTrancheStatut(EnumTrancheStatut.Ferme);
        Tranche tranche10 = new Tranche();
        tranche10.setNumeroTranche("10");
        tranche10.setRegime(generateRegime(generateDate(-1), generateDate(1)));
        tranche10.setTrancheStatut(EnumTrancheStatut.Ouvert);

        /**
         * Init Od
         */
        Gare g = new Gare("GBSPX");
        Gare g2 = new Gare("GBEBB");
        Gare g3 = new Gare("GARE3");
        Gare g4 = new Gare("GARE4");

        OrigineDestination od00 = new OrigineDestination(g, g2, generateRegime(generateDate(0), generateDate(1)));
        OrigineDestination od01 = new OrigineDestination(g3, g2, generateRegime(generateDate(-1), generateDate(0)));
        OrigineDestination od10 = new OrigineDestination(g2, g4, generateRegime(generateDate(-1), generateDate(1)));
        List<OrigineDestination> ods00 = new ArrayList<>();
        ods00.add(od00);
        List<OrigineDestination> ods01 = new ArrayList<>();
        ods01.add(od01);
        List<OrigineDestination> ods10 = new ArrayList<>();
        ods10.add(od10);

        /**
         * Regime Distrib
         */
        Distribution d00 = new Distribution("C", generateRegime(generateDate(0), generateDate(1)));
        Distribution d01 = new Distribution("B", generateRegime(generateDate(-1), generateDate(0)));
        Distribution d10 = new Distribution("D", generateRegime(generateDate(-1), generateDate(1)));
        List<Distribution> distributions00 = new ArrayList<>();
        distributions00.add(d00);
        List<Distribution> distributions01 = new ArrayList<>();
        distributions01.add(d01);
        List<Distribution> distributions10 = new ArrayList<>();
        distributions10.add(d10);

        /**
         * CodeSat
         */

        CodeSat codeSat00 = new CodeSat("1", generateRegime(generateDate(0), generateDate(1)));
        CodeSat codeSat01 = new CodeSat("2", generateRegime(generateDate(-1), generateDate(0)));
        CodeSat codeSat10 = new CodeSat("3", generateRegime(generateDate(-1), generateDate(1)));
        List<CodeSat> codeSats00 = new ArrayList<>();
        codeSats00.add(codeSat00);
        List<CodeSat> codeSats01 = new ArrayList<>();
        codeSats01.add(codeSat01);
        List<CodeSat> codeSats10 = new ArrayList<>();
        codeSats10.add(codeSat10);

        /**
         * Tosp
         */

        Tosp tosp00 = new Tosp("1", generateRegime(generateDate(0), generateDate(1)));
        Tosp tosp01 = new Tosp("2", generateRegime(generateDate(-1), generateDate(0)));
        Tosp tosp10 = new Tosp("3", generateRegime(generateDate(-1), generateDate(1)));
        List<Tosp> tosps00 = new ArrayList<>();
        tosps00.add(tosp00);
        List<Tosp> tosps01 = new ArrayList<>();
        tosps01.add(tosp01);
        List<Tosp> tosps10 = new ArrayList<>();
        tosps10.add(tosp10);

        /**
         * FareProfile
         */

        FareProfile fareProfile00 = new FareProfile("1", generateRegime(generateDate(0), generateDate(1)));
        FareProfile fareProfile01 = new FareProfile("2", generateRegime(generateDate(-1), generateDate(0)));
        FareProfile fareProfile10 = new FareProfile("3", generateRegime(generateDate(-1), generateDate(1)));
        List<FareProfile> fp00 = new ArrayList<>();
        fp00.add(fareProfile00);
        List<FareProfile> fp01 = new ArrayList<>();
        fp01.add(fareProfile01);
        List<FareProfile> fp10 = new ArrayList<>();
        fp10.add(fareProfile10);

        /**
         * Regime Compo
         */

        List<Voiture> voitures00 = new ArrayList<>();
        List<Siege> sieges = new ArrayList<>();
        sieges.add(new Siege("1"));
        sieges.add(new Siege("2"));
        sieges.add(new Siege("3"));
        sieges.add(new Siege("4"));
        sieges.add(new Siege("5"));
        sieges.add(new Siege("6"));
        sieges.add(new Siege("7"));
        sieges.add(new Siege("8"));

        List<Compartiment> compartiments = new ArrayList<>();
        compartiments.add(new Compartiment("1", sieges));
        compartiments.add(new Compartiment("2", sieges));
        compartiments.add(new Compartiment("3", sieges));

        Voiture v1 = new Voiture("001", compartiments);
        Voiture v2 = new Voiture("002", compartiments);
        Voiture v3 = new Voiture("003", compartiments);

        voitures00.add(v1);
        voitures00.add(v2);
        voitures00.add(v3);

        Composition compo00 = new Composition("A", "ESA", "15001H", "C01", voitures00,
                generateRegime(generateDate(0), generateDate(1)));

        List<Voiture> voitures01 = new ArrayList<>();
        Voiture v4 = new Voiture("004", compartiments);
        Voiture v5 = new Voiture("005", compartiments);
        Voiture v6 = new Voiture("006", compartiments);

        voitures01.add(v4);
        voitures01.add(v5);
        voitures01.add(v6);

        Composition compo01 = new Composition("H", "ESH", "16001J", "C01", voitures01,
                generateRegime(generateDate(-1), generateDate(0)));

        List<Voiture> voitures10 = new ArrayList<>();

        Voiture v7 = new Voiture("007", compartiments);
        Voiture v8 = new Voiture("008", compartiments);
        Voiture v9 = new Voiture("009", compartiments);

        voitures10.add(v7);
        voitures10.add(v8);
        voitures10.add(v9);

        Composition compo10 = new Composition("B", "DKT", "15001K", "D031", voitures10,
                generateRegime(generateDate(-1), generateDate(1)));
        List<Composition> compos00 = new ArrayList<>();
        compos00.add(compo00);
        List<Composition> compos01 = new ArrayList<>();
        compos01.add(compo01);
        List<Composition> compos10 = new ArrayList<>();
        compos10.add(compo10);

        /**
         * TypeEquipement
         */
        TypeEquipement te00 = new TypeEquipement("TGT", generateRegime(generateDate(0), generateDate(1)));
        TypeEquipement te01 = new TypeEquipement("TGR", generateRegime(generateDate(-1), generateDate(0)));
        TypeEquipement te10 = new TypeEquipement("TIT", generateRegime(generateDate(-1), generateDate(1)));
        List<TypeEquipement> tes00 = new ArrayList<>();
        tes00.add(te00);
        List<TypeEquipement> tes01 = new ArrayList<>();
        tes01.add(te01);
        List<TypeEquipement> tes10 = new ArrayList<>();
        tes10.add(te10);

        /**
         * ServiceAbord
         */
        ServiceABord sab00 = new ServiceABord("NY", EnumClasseService.Premiere, g, g2,
                generateRegime(generateDate(0), generateDate(1)));
        ServiceABord sab01 = new ServiceABord("NE", EnumClasseService.Premiere, g, g2,
                generateRegime(generateDate(-1), generateDate(0)));
        ServiceABord sab10 = new ServiceABord("BAR", EnumClasseService.Toute, g3, g,
                generateRegime(generateDate(-1), generateDate(1)));
        List<ServiceABord> sabs00 = new ArrayList<>();
        sabs00.add(sab00);
        List<ServiceABord> sabs01 = new ArrayList<>();
        sabs01.add(sab01);
        List<ServiceABord> sabs10 = new ArrayList<>();
        sabs10.add(sab10);

        /**
         * Repas
         */

        Repas repas00 = new Repas(EnumTypeRepas.Dejeuner, new Horaire(generateDate(0), generateDate(1)),
                generateRegime(generateDate(0), generateDate(1)));
        Repas repas01 = new Repas(EnumTypeRepas.diner, new Horaire(generateDate(-1), generateDate(0)),
                generateRegime(generateDate(-1), generateDate(0)));
        Repas repas10 = new Repas(EnumTypeRepas.Snack, new Horaire(generateDate(-1), generateDate(1)),
                generateRegime(generateDate(-1), generateDate(1)));
        List<Repas> r00 = new ArrayList<>();
        r00.add(repas00);
        List<Repas> r01 = new ArrayList<>();
        r01.add(repas01);
        List<Repas> r10 = new ArrayList<>();
        r10.add(repas10);

        /**
         * Specification
         */

        Specification spe00 = new Specification(v1, EnumEtatSpecification.Blocked,
                generateRegime(generateDate(0), generateDate(1)));
        List<Specification> spes00 = new ArrayList<>();
        spes00.add(spe00);

        

        /**
         * Init Desserte
         */
        Desserte des00 = new Desserte();
        des00.setRegime(generateRegime(generateDate(0), generateDate(1)));
        Desserte des01 = new Desserte();
        des01.setRegime(generateRegime(generateDate(-1), generateDate(0)));
        Desserte des10 = new Desserte();
        des10.setRegime(generateRegime(generateDate(-1), generateDate(1)));

        Horaire h = new Horaire();
        h.setHoraireDebut(generateDate(0));
        h.setHoraireFin(generateDate(1));

        GareHoraire gh1 = new GareHoraire(g, h);
        GareHoraire gh2 = new GareHoraire(g2, h);
        GareHoraire gh3 = new GareHoraire(g3, h);
        GareHoraire gh4 = new GareHoraire(g4, h);

        List<GareHoraire> lGh = new ArrayList<>();
        List<GareHoraire> lGh1 = new ArrayList<>();
        List<GareHoraire> lGh2 = new ArrayList<>();

        lGh.add(gh1);
        lGh.add(gh2);
        
        lGh1.add(gh1);
        lGh1.add(gh2);

        lGh2.add(gh3);
        lGh2.add(gh4);

        des00.setGareHoraires(lGh);
        des01.setGareHoraires(lGh);
        des10.setGareHoraires(lGh2);

        List<Desserte> dess00  = new ArrayList<>();
        dess00.add(des00);
        List<Desserte> dess01  = new ArrayList<>();
        dess01.add(des01);
        List<Desserte> dess10 = new ArrayList<>();
        dess10.add(des10);

        /**
         * replissage des attributs tranche
         */
        MapTranche mapTranche00 = new MapTranche();
        mapTranche00.put(OrigineDestination.class, ods00);
        mapTranche00.put(Distribution.class, distributions00);
        mapTranche00.put(CodeSat.class, codeSats00);
        mapTranche00.put(Tosp.class, tosps00);
        mapTranche00.put(FareProfile.class, fp00);
        mapTranche00.put(Composition.class, compos00);
        mapTranche00.put(TypeEquipement.class, tes00);
        mapTranche00.put(ServiceABord.class, sabs00);
        mapTranche00.put(Repas.class, r00);
        mapTranche00.put(Specification.class, spes00);
        mapTranche00.put(Desserte.class, dess00);

        MapTranche mapTranche01 = new MapTranche();
        mapTranche01.put(OrigineDestination.class, ods01);
        mapTranche01.put(Distribution.class, distributions01);
        mapTranche01.put(CodeSat.class, codeSats01);
        mapTranche01.put(Tosp.class, tosps01);
        mapTranche01.put(FareProfile.class, fp01);
        mapTranche01.put(Composition.class, compos01);
        mapTranche01.put(TypeEquipement.class, tes01);
        mapTranche01.put(ServiceABord.class, sabs01);
        mapTranche01.put(Repas.class, r01);
        mapTranche01.put(Desserte.class, dess01);

        MapTranche mapTranche10 = new MapTranche();
        mapTranche10.put(OrigineDestination.class, ods10);
        mapTranche10.put(Distribution.class, distributions10);
        mapTranche10.put(CodeSat.class, codeSats10);
        mapTranche10.put(Tosp.class, tosps10);
        mapTranche10.put(FareProfile.class, fp10);
        mapTranche10.put(Composition.class, compos10);
        mapTranche10.put(TypeEquipement.class, tes10);
        mapTranche10.put(ServiceABord.class, sabs10);
        mapTranche10.put(Repas.class, r10);
        mapTranche10.put(Desserte.class, dess10);

        /**
         * setage des mapTranche
         */
        tranche00.setAttributs(mapTranche00);
        tranche01.setAttributs(mapTranche01);
        tranche10.setAttributs(mapTranche10);
        
        List<Tranche> tranches0 = new ArrayList<>();
        tranches0.add(tranche00);
        tranches0.add(tranche01);
        List<Tranche> tranches1 = new ArrayList<>();
        tranches1.add(tranche10);
        
        train0.setTranches(tranches0);
        train1.setTranches(tranches1);
        
        List<Train> trains = new ArrayList<>();
        trains.add(train0);
        trains.add(train1);

        pdt.setTrains(trains);
        

        MapPlansDeTransport mapPlansDeTransport = new MapPlansDeTransport();

        mapPlansDeTransport.setMapPlansDeTransport(new JeuDonneeEntity(),null, new JeuDonneeEntity(), pdt);

        return mapPlansDeTransport;
    }

    public static MapPlansDeTransport createDataForCompareTrancheSplit() {
        List<Train> trainsDraft = new ArrayList<>();
        List<Train> trainsActive = new ArrayList<>();
        PlanTransport pdtDraft;
        PlanTransport pdtActive;

        MapPlansDeTransport mapPlansDeTransport = new MapPlansDeTransport();

        ArrayList<Tranche> tranchesAncien = new ArrayList<>();
        ArrayList<Tranche> tranchesNouveau = new ArrayList<>();

        Tranche trancheAncienModify = getTrancheAncienModify();
        tranchesAncien.add(trancheAncienModify);

        Tranche trancheAncienDelete = getTrancheAncienDelete();
        tranchesAncien.add(trancheAncienDelete);

        Tranche trancheAncienSplit = getTrancheAncienSplit();
        tranchesAncien.add(trancheAncienSplit);

        Tranche trancheNouveauModify = getTrancheNouveauModify();
        tranchesNouveau.add(trancheNouveauModify);

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

    public static Tranche getTrancheNouveauModify() {
        Regime regimeModify = generateRegime(generateDate(-2), generateDate(-1));
        regimeModify.setCodeRegime("Modify");

        Tranche trancheNouveauModify = new Tranche();
        trancheNouveauModify.setNumeroTranche("Modify");
        trancheNouveauModify.setRegime(regimeModify);
        trancheNouveauModify.setTrancheStatut(EnumTrancheStatut.Ouvert);
        return trancheNouveauModify;
    }

    public static Tranche getTrancheAncienModify() {
        Regime regimeModify = generateRegime(generateDate(-2), generateDate(-1));
        regimeModify.setCodeRegime("Modify");

        Tranche trancheAncienModify = new Tranche();
        trancheAncienModify.setNumeroTranche("Modify");
        trancheAncienModify.setRegime(regimeModify);
        trancheAncienModify.setTrancheStatut(EnumTrancheStatut.Ferme);
        return trancheAncienModify;
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
     * @return Liste des dates contenues entre dateDebut et dateFin (les deux
     *         incluses)
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
     * @return Regime dont la liste de dates contient celles contenues entre
     *         dateDebut et dateFin (les deux incluses, et settées en DateDebut
     *         et DateFin du régime)
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
     *            Nombre de jours positif ou négatif
     * @return Date correspondant à la date du jour à laquelle on a ajouté le
     *         nombre de jours en paramètre. Si le nombre de jours est négatif,
     *         on soustrait des jours à la date du jour.
     */
    public static Date generateDate(int nbJours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateRef);
        calendar.add(Calendar.DATE, nbJours);
        return calendar.getTime();
    }

}
