package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.avancial.app.data.dto.filtrePlanTransport.CompositionDto;
import com.avancial.app.data.dto.filtrePlanTransport.MealServiceDto;
import com.avancial.app.data.dto.filtrePlanTransport.RMCodeDto;
import com.avancial.app.data.dto.filtrePlanTransport.ServiceABoardDto;
import com.avancial.app.data.dto.filtrePlanTransport.ServicesDto;
import com.avancial.app.data.dto.filtrePlanTransport.StopsDto;
import com.avancial.app.data.dto.filtrePlanTransport.TrainTrancheDateDto;
import com.avancial.app.data.dto.filtrePlanTransport.VoitureDto;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumClasseService;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumEtatSpecification;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeRepas;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.serviceDto.FiltrePlanTransportServiceDto;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.utils.ListUtils;
import factory.PlanTransportFactory;

public class TestResultatFiltreDto {

    @Test
    public void TestResultatFiltre() {
        MapPlansDeTransport mapPlansDeTransport = PlanTransportFactory.createDataForResultatFiltre();
        PlanTransport pdt = mapPlansDeTransport.getPlanTransportActive();

        FiltrePlanTransportServiceDto filtreDto = new FiltrePlanTransportServiceDto();
        filtreDto.setPlanTransport(pdt);
        List<TrainTrancheDateDto> ttdds = filtreDto.transforme().getListTrainTrancheDateDto();

        Date date0 = PlanTransportFactory.generateDate(-1);
        Date date1 = PlanTransportFactory.generateDate(0);

        List<TrainTrancheDateDto> ttddsExpected = new ArrayList<>();
        TrainTrancheDateDto trainTrancheDateDtoExpected0 = new TrainTrancheDateDto();
        trainTrancheDateDtoExpected0.setGlobalClass(null);
        trainTrancheDateDtoExpected0.setLoadedOn(null);
        trainTrancheDateDtoExpected0.setMandatoryBooking(null);
        trainTrancheDateDtoExpected0.setNumeroTrain("0");
        trainTrancheDateDtoExpected0.setNumeroTranche("00");
        trainTrancheDateDtoExpected0.setDateJour(date1);
        trainTrancheDateDtoExpected0.setCodeSat("1");
        trainTrancheDateDtoExpected0.setCodeTosp("1");
        trainTrancheDateDtoExpected0.setCompany("ES");
        trainTrancheDateDtoExpected0.setDestination("GBEBB");
        trainTrancheDateDtoExpected0.setIndicateurDistribution("C");
        trainTrancheDateDtoExpected0.setOrigine("GBSPX");
        trainTrancheDateDtoExpected0.setStatus(EnumTrancheStatut.Ouvert.toString());
        trainTrancheDateDtoExpected0.setValidForRR("YES");
        CompositionDto compo0 = new CompositionDto();
        compo0.setCapacity(72);
        List<VoitureDto> voitures0 = new ArrayList<>();

        VoitureDto voiture00 = new VoitureDto();
        voiture00.setCapacity(24);
        voiture00.setCoachClass("A");
        voiture00.setDiagCode("ESA");
        voiture00.setNumeroCoach("001");
        voiture00.setRameCode("15001H");
        List<String> specifications00 = new ArrayList<>();
        specifications00.add("Coach 001 - " + EnumEtatSpecification.Blocked);
        voiture00.setSpecifications(specifications00);
        voitures0.add(voiture00);

        VoitureDto voiture01 = new VoitureDto();
        voiture01.setCapacity(24);
        voiture01.setCoachClass("A");
        voiture01.setDiagCode("ESA");
        voiture01.setNumeroCoach("002");
        voiture01.setRameCode("15001H");
        List<String> specifications01 = new ArrayList<>();
        voiture01.setSpecifications(specifications01);
        voitures0.add(voiture01);

        VoitureDto voiture02 = new VoitureDto();
        voiture02.setCapacity(24);
        voiture02.setCoachClass("A");
        voiture02.setDiagCode("ESA");
        voiture02.setNumeroCoach("003");
        voiture02.setRameCode("15001H");
        List<String> specifications02 = new ArrayList<>();
        voiture02.setSpecifications(specifications02);
        voitures0.add(voiture02);

        compo0.setVoitures(voitures0);
        trainTrancheDateDtoExpected0.setComposition(compo0);
        RMCodeDto rmCode0 = new RMCodeDto();
        rmCode0.setCodeRame1("15001H");
        rmCode0.setCodeRame2("");
        rmCode0.setRmCode("C01");
        rmCode0.setFareProfileCode("1");
        trainTrancheDateDtoExpected0.setRmCode(rmCode0);
        ServicesDto service0 = new ServicesDto();
        service0.setCodeEquipement("TGT");
        List<MealServiceDto> mealServices0 = new ArrayList<>();
        MealServiceDto mealService00 = new MealServiceDto();
        mealService00.setEnding(PlanTransportFactory.generateDate(1));
        mealService00.setMealCode("");
        mealService00.setMealType("Meal Service");
        mealService00.setNumeroCoach("001");
        mealService00.setStarting(PlanTransportFactory.generateDate(0));
        mealService00.setType(EnumTypeRepas.Dejeuner.toString());
        MealServiceDto mealService01 = new MealServiceDto();
        mealService01.setEnding(PlanTransportFactory.generateDate(1));
        mealService01.setMealCode("");
        mealService01.setMealType("Meal Service");
        mealService01.setNumeroCoach("002");
        mealService01.setStarting(PlanTransportFactory.generateDate(0));
        mealService01.setType(EnumTypeRepas.Dejeuner.toString());
        MealServiceDto mealService02 = new MealServiceDto();
        mealService02.setEnding(PlanTransportFactory.generateDate(1));
        mealService02.setMealCode("");
        mealService02.setMealType("Meal Service");
        mealService02.setNumeroCoach("003");
        mealService02.setStarting(PlanTransportFactory.generateDate(0));
        mealService02.setType(EnumTypeRepas.Dejeuner.toString());
        mealServices0.add(mealService00);
        mealServices0.add(mealService01);
        mealServices0.add(mealService02);
        service0.setMealServices(mealServices0);
        List<ServiceABoardDto> services0 = new ArrayList<>();
        ServiceABoardDto serviceABord0 = new ServiceABoardDto();
        serviceABord0.setClasse(EnumClasseService.Premiere.toString());
        serviceABord0.setCode("NY");
        serviceABord0.setDestination("GBEBB");
        serviceABord0.setLibelle("");
        serviceABord0.setManualAuto("");
        serviceABord0.setOrigine("GBSPX");
        services0.add(serviceABord0);
        service0.setServices(services0);
        trainTrancheDateDtoExpected0.setService(service0);
        List<StopsDto> stops0 = new ArrayList<>();
        StopsDto stop00 = new StopsDto();
        stop00.setArrivalTime(PlanTransportFactory.generateDate(1));
        stop00.setCityCode("GBSPX");
        stop00.setDepartureTime(PlanTransportFactory.generateDate(0));
        stop00.setLibelle("");
        stop00.setOffForbidden("");
        stop00.setOnForbidden("");
        StopsDto stop01 = new StopsDto();
        stop01.setArrivalTime(PlanTransportFactory.generateDate(1));
        stop01.setCityCode("GBEBB");
        stop01.setDepartureTime(PlanTransportFactory.generateDate(0));
        stop01.setLibelle("");
        stop01.setOffForbidden("");
        stop01.setOnForbidden("");
        stops0.add(stop00);
        stops0.add(stop01);
        trainTrancheDateDtoExpected0.setStops(stops0);

        TrainTrancheDateDto trainTrancheDateDtoExpected1 = new TrainTrancheDateDto();
        trainTrancheDateDtoExpected1.setGlobalClass(null);
        trainTrancheDateDtoExpected1.setLoadedOn(null);
        trainTrancheDateDtoExpected1.setMandatoryBooking(null);
        trainTrancheDateDtoExpected1.setNumeroTrain("0");
        trainTrancheDateDtoExpected1.setNumeroTranche("01");
        trainTrancheDateDtoExpected1.setDateJour(date0);
        trainTrancheDateDtoExpected1.setCodeSat("2");
        trainTrancheDateDtoExpected1.setCodeTosp("2");
        trainTrancheDateDtoExpected1.setCompany("ES");
        trainTrancheDateDtoExpected1.setDestination("GBEBB");
        trainTrancheDateDtoExpected1.setIndicateurDistribution("B");
        trainTrancheDateDtoExpected1.setOrigine("GARE3");
        trainTrancheDateDtoExpected1.setStatus(EnumTrancheStatut.Ferme.toString());
        trainTrancheDateDtoExpected1.setValidForRR("YES");
        CompositionDto compo1 = new CompositionDto();
        compo1.setCapacity(72);
        List<VoitureDto> voitures1 = new ArrayList<>();

        VoitureDto voiture10 = new VoitureDto();
        voiture10.setCapacity(24);
        voiture10.setCoachClass("H");
        voiture10.setDiagCode("ESH");
        voiture10.setNumeroCoach("004");
        voiture10.setRameCode("16001J");
        List<String> specifications10 = new ArrayList<>();
        voiture10.setSpecifications(specifications10);
        voitures1.add(voiture10);

        VoitureDto voiture11 = new VoitureDto();
        voiture11.setCapacity(24);
        voiture11.setCoachClass("H");
        voiture11.setDiagCode("ESH");
        voiture11.setNumeroCoach("005");
        voiture11.setRameCode("16001J");
        List<String> specifications11 = new ArrayList<>();
        voiture11.setSpecifications(specifications11);
        voitures1.add(voiture11);

        VoitureDto voiture12 = new VoitureDto();
        voiture12.setCapacity(24);
        voiture12.setCoachClass("H");
        voiture12.setDiagCode("ESH");
        voiture12.setNumeroCoach("006");
        voiture12.setRameCode("16001J");
        List<String> specifications12 = new ArrayList<>();
        voiture12.setSpecifications(specifications12);
        voitures1.add(voiture12);
        compo1.setVoitures(voitures1);
        trainTrancheDateDtoExpected1.setComposition(compo1);
        RMCodeDto rmCode1 = new RMCodeDto();
        rmCode1.setCodeRame1("16001J");
        rmCode1.setCodeRame2("");
        rmCode1.setRmCode("C01");
        rmCode1.setFareProfileCode("2");
        trainTrancheDateDtoExpected1.setRmCode(rmCode1);
        ServicesDto service1 = new ServicesDto();
        service1.setCodeEquipement("TGR");
        List<MealServiceDto> mealServices1 = new ArrayList<>();
        MealServiceDto mealService10 = new MealServiceDto();
        mealService10.setEnding(PlanTransportFactory.generateDate(0));
        mealService10.setMealCode("");
        mealService10.setMealType("Meal Service");
        mealService10.setNumeroCoach("004");
        mealService10.setStarting(PlanTransportFactory.generateDate(-1));
        mealService10.setType(EnumTypeRepas.diner.toString());
        MealServiceDto mealService11 = new MealServiceDto();
        mealService11.setEnding(PlanTransportFactory.generateDate(0));
        mealService11.setMealCode("");
        mealService11.setMealType("Meal Service");
        mealService11.setNumeroCoach("005");
        mealService11.setStarting(PlanTransportFactory.generateDate(-1));
        mealService11.setType(EnumTypeRepas.diner.toString());
        MealServiceDto mealService12 = new MealServiceDto();
        mealService12.setEnding(PlanTransportFactory.generateDate(0));
        mealService12.setMealCode("");
        mealService12.setMealType("Meal Service");
        mealService12.setNumeroCoach("006");
        mealService12.setStarting(PlanTransportFactory.generateDate(-1));
        mealService12.setType(EnumTypeRepas.diner.toString());
        mealServices1.add(mealService10);
        mealServices1.add(mealService11);
        mealServices1.add(mealService12);
        service1.setMealServices(mealServices1);
        List<ServiceABoardDto> services1 = new ArrayList<>();
        ServiceABoardDto serviceABord1 = new ServiceABoardDto();
        serviceABord1.setClasse(EnumClasseService.Premiere.toString());
        serviceABord1.setCode("NE");
        serviceABord1.setDestination("GBEBB");
        serviceABord1.setLibelle("");
        serviceABord1.setManualAuto("");
        serviceABord1.setOrigine("GBSPX");
        services1.add(serviceABord1);
        service1.setServices(services1);
        trainTrancheDateDtoExpected1.setService(service1);
        List<StopsDto> stops1 = new ArrayList<>();
        StopsDto stop10 = new StopsDto();
        stop10.setArrivalTime(PlanTransportFactory.generateDate(1));
        stop10.setCityCode("GBSPX");
        stop10.setDepartureTime(PlanTransportFactory.generateDate(0));
        stop10.setLibelle("");
        stop10.setOffForbidden("");
        stop10.setOnForbidden("");
        StopsDto stop11 = new StopsDto();
        stop11.setArrivalTime(PlanTransportFactory.generateDate(1));
        stop11.setCityCode("GBEBB");
        stop11.setDepartureTime(PlanTransportFactory.generateDate(0));
        stop11.setLibelle("");
        stop11.setOffForbidden("");
        stop11.setOnForbidden("");
        stops1.add(stop10);
        stops1.add(stop11);
        trainTrancheDateDtoExpected1.setStops(stops1);

        TrainTrancheDateDto trainTrancheDateDtoExpected2 = new TrainTrancheDateDto();
        trainTrancheDateDtoExpected2.setGlobalClass(null);
        trainTrancheDateDtoExpected2.setLoadedOn(null);
        trainTrancheDateDtoExpected2.setMandatoryBooking(null);
        trainTrancheDateDtoExpected2.setNumeroTrain("1");
        trainTrancheDateDtoExpected2.setNumeroTranche("10");
        trainTrancheDateDtoExpected2.setDateJour(date0);
        trainTrancheDateDtoExpected2.setCodeSat("3");
        trainTrancheDateDtoExpected2.setCodeTosp("3");
        trainTrancheDateDtoExpected2.setCompany("ES");
        trainTrancheDateDtoExpected2.setDestination("GARE4");
        trainTrancheDateDtoExpected2.setIndicateurDistribution("D");
        trainTrancheDateDtoExpected2.setOrigine("GBEBB");
        trainTrancheDateDtoExpected2.setStatus(EnumTrancheStatut.Ouvert.toString());
        trainTrancheDateDtoExpected2.setValidForRR("NO");
        CompositionDto compo2 = new CompositionDto();
        compo2.setCapacity(72);
        List<VoitureDto> voitures2 = new ArrayList<>();
        VoitureDto voiture20 = new VoitureDto();
        voiture20.setCapacity(24);
        voiture20.setCoachClass("B");
        voiture20.setDiagCode("DKT");
        voiture20.setNumeroCoach("007");
        voiture20.setRameCode("15001K");
        List<String> specifications20 = new ArrayList<>();
        voiture20.setSpecifications(specifications20);
        voitures2.add(voiture20);

        VoitureDto voiture21 = new VoitureDto();
        voiture21.setCapacity(24);
        voiture21.setCoachClass("B");
        voiture21.setDiagCode("DKT");
        voiture21.setNumeroCoach("008");
        voiture21.setRameCode("15001K");
        List<String> specifications21 = new ArrayList<>();
        voiture21.setSpecifications(specifications21);
        voitures2.add(voiture21);

        VoitureDto voiture22 = new VoitureDto();
        voiture22.setCapacity(24);
        voiture22.setCoachClass("B");
        voiture22.setDiagCode("DKT");
        voiture22.setNumeroCoach("009");
        voiture22.setRameCode("15001K");
        List<String> specifications22 = new ArrayList<>();
        voiture22.setSpecifications(specifications22);
        voitures2.add(voiture22);

        compo2.setVoitures(voitures2);
        trainTrancheDateDtoExpected2.setComposition(compo2);
        RMCodeDto rmCode2 = new RMCodeDto();
        rmCode2.setCodeRame1("15001K");
        rmCode2.setCodeRame2("");
        rmCode2.setRmCode("D031");
        rmCode2.setFareProfileCode("3");
        trainTrancheDateDtoExpected2.setRmCode(rmCode2);
        ServicesDto service2 = new ServicesDto();
        service2.setCodeEquipement("TIT");
        List<MealServiceDto> mealServices2 = new ArrayList<>();
        MealServiceDto mealService20 = new MealServiceDto();
        mealService20.setEnding(PlanTransportFactory.generateDate(1));
        mealService20.setMealCode("");
        mealService20.setMealType("Meal Service");
        mealService20.setNumeroCoach("007");
        mealService20.setStarting(PlanTransportFactory.generateDate(-1));
        mealService20.setType(EnumTypeRepas.Snack.toString());
        MealServiceDto mealService21 = new MealServiceDto();
        mealService21.setEnding(PlanTransportFactory.generateDate(1));
        mealService21.setMealCode("");
        mealService21.setMealType("Meal Service");
        mealService21.setNumeroCoach("008");
        mealService21.setStarting(PlanTransportFactory.generateDate(-1));
        mealService21.setType(EnumTypeRepas.Snack.toString());
        MealServiceDto mealService22 = new MealServiceDto();
        mealService22.setEnding(PlanTransportFactory.generateDate(1));
        mealService22.setMealCode("");
        mealService22.setMealType("Meal Service");
        mealService22.setNumeroCoach("009");
        mealService22.setStarting(PlanTransportFactory.generateDate(-1));
        mealService22.setType(EnumTypeRepas.Snack.toString());
        mealServices2.add(mealService20);
        mealServices2.add(mealService21);
        mealServices2.add(mealService22);
        service2.setMealServices(mealServices2);
        List<ServiceABoardDto> services2 = new ArrayList<>();
        ServiceABoardDto serviceABord2 = new ServiceABoardDto();
        serviceABord2.setClasse(EnumClasseService.Toute.toString());
        serviceABord2.setCode("BAR");
        serviceABord2.setDestination("GBSPX");
        serviceABord2.setLibelle("");
        serviceABord2.setManualAuto("");
        serviceABord2.setOrigine("GARE3");
        services2.add(serviceABord2);
        service2.setServices(services2);
        trainTrancheDateDtoExpected2.setService(service2);
        List<StopsDto> stops2 = new ArrayList<>();
        StopsDto stop20 = new StopsDto();
        stop20.setArrivalTime(PlanTransportFactory.generateDate(1));
        stop20.setCityCode("GARE3");
        stop20.setDepartureTime(PlanTransportFactory.generateDate(0));
        stop20.setLibelle("");
        stop20.setOffForbidden("");
        stop20.setOnForbidden("");
        StopsDto stop21 = new StopsDto();
        stop21.setArrivalTime(PlanTransportFactory.generateDate(1));
        stop21.setCityCode("GARE4");
        stop21.setDepartureTime(PlanTransportFactory.generateDate(0));
        stop21.setLibelle("");
        stop21.setOffForbidden("");
        stop21.setOnForbidden("");
        stops2.add(stop20);
        stops2.add(stop21);
        trainTrancheDateDtoExpected2.setStops(stops2);

        TrainTrancheDateDto trainTrancheDateDtoExpected3 = new TrainTrancheDateDto();
        trainTrancheDateDtoExpected3.setGlobalClass(null);
        trainTrancheDateDtoExpected3.setLoadedOn(null);
        trainTrancheDateDtoExpected3.setMandatoryBooking(null);
        trainTrancheDateDtoExpected3.setNumeroTrain("1");
        trainTrancheDateDtoExpected3.setNumeroTranche("10");
        trainTrancheDateDtoExpected3.setDateJour(date1);
        trainTrancheDateDtoExpected3.setCodeSat("3");
        trainTrancheDateDtoExpected3.setCodeTosp("3");
        trainTrancheDateDtoExpected3.setCompany("ES");
        trainTrancheDateDtoExpected3.setDestination("GARE4");
        trainTrancheDateDtoExpected3.setIndicateurDistribution("D");
        trainTrancheDateDtoExpected3.setOrigine("GBEBB");
        trainTrancheDateDtoExpected3.setStatus(EnumTrancheStatut.Ouvert.toString());
        trainTrancheDateDtoExpected3.setValidForRR("NO");
        CompositionDto compo3 = new CompositionDto();
        compo3.setCapacity(72);
        List<VoitureDto> voitures3 = new ArrayList<>();
        VoitureDto voiture30 = new VoitureDto();
        voiture30.setCapacity(24);
        voiture30.setCoachClass("B");
        voiture30.setDiagCode("DKT");
        voiture30.setNumeroCoach("007");
        voiture30.setRameCode("15001K");
        List<String> specifications30 = new ArrayList<>();
        voiture30.setSpecifications(specifications30);
        voitures3.add(voiture30);

        VoitureDto voiture31 = new VoitureDto();
        voiture31.setCapacity(24);
        voiture31.setCoachClass("B");
        voiture31.setDiagCode("DKT");
        voiture31.setNumeroCoach("008");
        voiture31.setRameCode("15001K");
        List<String> specifications31 = new ArrayList<>();
        voiture31.setSpecifications(specifications31);
        voitures3.add(voiture31);

        VoitureDto voiture32 = new VoitureDto();
        voiture32.setCapacity(24);
        voiture32.setCoachClass("B");
        voiture32.setDiagCode("DKT");
        voiture32.setNumeroCoach("009");
        voiture32.setRameCode("15001K");
        List<String> specifications32 = new ArrayList<>();
        voiture32.setSpecifications(specifications32);
        voitures3.add(voiture32);

        compo3.setVoitures(voitures3);
        trainTrancheDateDtoExpected3.setComposition(compo3);
        RMCodeDto rmCode3 = new RMCodeDto();
        rmCode3.setCodeRame1("15001K");
        rmCode3.setCodeRame2("");
        rmCode3.setRmCode("D031");
        rmCode3.setFareProfileCode("3");
        trainTrancheDateDtoExpected3.setRmCode(rmCode3);
        ServicesDto service3 = new ServicesDto();
        service3.setCodeEquipement("TIT");
        List<MealServiceDto> mealServices3 = new ArrayList<>();
        MealServiceDto mealService30 = new MealServiceDto();
        mealService30.setEnding(PlanTransportFactory.generateDate(1));
        mealService30.setMealCode("");
        mealService30.setMealType("Meal Service");
        mealService30.setNumeroCoach("007");
        mealService30.setStarting(PlanTransportFactory.generateDate(-1));
        mealService30.setType(EnumTypeRepas.Snack.toString());
        MealServiceDto mealService31 = new MealServiceDto();
        mealService31.setEnding(PlanTransportFactory.generateDate(1));
        mealService31.setMealCode("");
        mealService31.setMealType("Meal Service");
        mealService31.setNumeroCoach("008");
        mealService31.setStarting(PlanTransportFactory.generateDate(-1));
        mealService31.setType(EnumTypeRepas.Snack.toString());
        MealServiceDto mealService32 = new MealServiceDto();
        mealService32.setEnding(PlanTransportFactory.generateDate(1));
        mealService32.setMealCode("");
        mealService32.setMealType("Meal Service");
        mealService32.setNumeroCoach("009");
        mealService32.setStarting(PlanTransportFactory.generateDate(-1));
        mealService32.setType(EnumTypeRepas.Snack.toString());
        mealServices3.add(mealService30);
        mealServices3.add(mealService31);
        mealServices3.add(mealService32);
        service3.setMealServices(mealServices3);
        List<ServiceABoardDto> services3 = new ArrayList<>();
        ServiceABoardDto serviceABord3 = new ServiceABoardDto();
        serviceABord3.setClasse(EnumClasseService.Toute.toString());
        serviceABord3.setCode("BAR");
        serviceABord3.setDestination("GBSPX");
        serviceABord3.setLibelle("");
        serviceABord3.setManualAuto("");
        serviceABord3.setOrigine("GARE3");
        services3.add(serviceABord3);
        service3.setServices(services3);
        trainTrancheDateDtoExpected3.setService(service3);
        List<StopsDto> stops3 = new ArrayList<>();
        StopsDto stop30 = new StopsDto();
        stop30.setArrivalTime(PlanTransportFactory.generateDate(1));
        stop30.setCityCode("GARE3");
        stop30.setDepartureTime(PlanTransportFactory.generateDate(0));
        stop30.setLibelle("");
        stop30.setOffForbidden("");
        stop30.setOnForbidden("");
        StopsDto stop31 = new StopsDto();
        stop31.setArrivalTime(PlanTransportFactory.generateDate(1));
        stop31.setCityCode("GARE4");
        stop31.setDepartureTime(PlanTransportFactory.generateDate(0));
        stop31.setLibelle("");
        stop31.setOffForbidden("");
        stop31.setOnForbidden("");
        stops3.add(stop30);
        stops3.add(stop31);
        trainTrancheDateDtoExpected3.setStops(stops3);

        ttddsExpected.add(trainTrancheDateDtoExpected0);
        ttddsExpected.add(trainTrancheDateDtoExpected1);
        ttddsExpected.add(trainTrancheDateDtoExpected2);
        ttddsExpected.add(trainTrancheDateDtoExpected3);

        for (TrainTrancheDateDto trainTrancheDate : ttdds) {
            for (TrainTrancheDateDto trainTrancheDateExpected : ttddsExpected) {
                if (trainTrancheDate.getDateJour().equals(trainTrancheDateExpected.getDateJour())
                        && trainTrancheDate.getNumeroTrain().equals(trainTrancheDateExpected.getNumeroTrain())
                        && trainTrancheDate.getNumeroTranche().equals(trainTrancheDateExpected.getNumeroTranche())) {
                    System.out.println("Train: " + trainTrancheDate.getNumeroTrain() + " Tranche: "
                            + trainTrancheDate.getNumeroTranche() + " Date: "
                            + trainTrancheDate.getDateJour().toString());
                    /**
                     * origine
                     */
                    Assert.assertTrue(
                            "Train: " + trainTrancheDate.getNumeroTrain() + " Tranche: "
                                    + trainTrancheDate.getNumeroTranche() + " Date: "
                                    + trainTrancheDate.getDateJour().toString() + " Origine",
                            trainTrancheDate.getOrigine().equals(trainTrancheDateExpected.getOrigine()));
                    /**
                     * Destination
                     */
                    Assert.assertTrue(
                            "Train: " + trainTrancheDate.getNumeroTrain() + " Tranche: "
                                    + trainTrancheDate.getNumeroTranche() + " Date: "
                                    + trainTrancheDate.getDateJour().toString() + " Destination",
                            trainTrancheDate.getDestination().equals(trainTrancheDateExpected.getDestination()));
                    /**
                     * validForRR
                     */
                    Assert.assertTrue(
                            "Train: " + trainTrancheDate.getNumeroTrain() + " Tranche: "
                                    + trainTrancheDate.getNumeroTranche() + " Date: "
                                    + trainTrancheDate.getDateJour().toString() + " ValidForRR",
                            trainTrancheDate.getValidForRR().equals(trainTrancheDateExpected.getValidForRR()));
                    /**
                     * status
                     */
                    Assert.assertTrue(
                            "Train: " + trainTrancheDate.getNumeroTrain() + " Tranche: "
                                    + trainTrancheDate.getNumeroTranche() + " Date: "
                                    + trainTrancheDate.getDateJour().toString() + " Status",
                            trainTrancheDate.getStatus().equals(trainTrancheDateExpected.getStatus()));
                    /**
                     * compagnie
                     */
                    Assert.assertTrue(
                            "Train: " + trainTrancheDate.getNumeroTrain() + " Tranche: "
                                    + trainTrancheDate.getNumeroTranche() + " Date: "
                                    + trainTrancheDate.getDateJour().toString() + " Company",
                            trainTrancheDate.getCompany().equals(trainTrancheDateExpected.getCompany()));
                    /**
                     * indicateurDistribution
                     */
                    Assert.assertTrue(
                            "Train: " + trainTrancheDate.getNumeroTrain() + " Tranche: "
                                    + trainTrancheDate.getNumeroTranche() + " Date: "
                                    + trainTrancheDate.getDateJour().toString() + " indicateurDistribution",
                            trainTrancheDate.getIndicateurDistribution()
                                    .equals(trainTrancheDateExpected.getIndicateurDistribution()));
                    /**
                     * codeSat
                     */
                    Assert.assertTrue(
                            "Train: " + trainTrancheDate.getNumeroTrain() + " Tranche: "
                                    + trainTrancheDate.getNumeroTranche() + " Date: "
                                    + trainTrancheDate.getDateJour().toString() + " codeSat",
                            trainTrancheDate.getCodeSat().equals(trainTrancheDateExpected.getCodeSat()));
                    /**
                     * codeTosp
                     */
                    Assert.assertTrue(
                            "Train: " + trainTrancheDate.getNumeroTrain() + " Tranche: "
                                    + trainTrancheDate.getNumeroTranche() + " Date: "
                                    + trainTrancheDate.getDateJour().toString() + " codeTosp",
                            trainTrancheDate.getCodeTosp().equals(trainTrancheDateExpected.getCodeTosp()));
                    /**
                     * rmCode
                     */
                    Assert.assertTrue(
                            "Train: " + trainTrancheDate.getNumeroTrain() + " Tranche: "
                                    + trainTrancheDate.getNumeroTranche() + " Date: "
                                    + trainTrancheDate.getDateJour().toString() + " rmCode",
                            trainTrancheDate.getRmCode().equals(trainTrancheDateExpected.getRmCode()));
                    /**
                     * service
                     */
                    Assert.assertTrue(
                            "Train: " + trainTrancheDate.getNumeroTrain() + " Tranche: "
                                    + trainTrancheDate.getNumeroTranche() + " Date: "
                                    + trainTrancheDate.getDateJour().toString() + " service",
                            trainTrancheDate.getService().equals(trainTrancheDateExpected.getService()));
                    /**
                     * composition
                     */
                    Assert.assertTrue(
                            "Train: " + trainTrancheDate.getNumeroTrain() + " Tranche: "
                                    + trainTrancheDate.getNumeroTranche() + " Date: "
                                    + trainTrancheDate.getDateJour().toString() + " composition",
                            trainTrancheDate.getComposition().equals(trainTrancheDateExpected.getComposition()));
                    /**
                     * stops
                     */
                    Assert.assertTrue(
                            "Train: " + trainTrancheDate.getNumeroTrain() + " Tranche: "
                                    + trainTrancheDate.getNumeroTranche() + " Date: "
                                    + trainTrancheDate.getDateJour().toString() + " stops",
                            ListUtils.compareLists(trainTrancheDate.getStops(), trainTrancheDateExpected.getStops()));
                }
            }
        }
        // Assert.assertTrue(ListUtils.compareLists(ttdds, ttddsExpected));

    }
}
