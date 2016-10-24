package service;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.fileImport.FileTypeNotExpectedException;
import com.avancial.app.fileImport.excelImport.AExcelImportContext;
import com.avancial.app.fileImport.excelImport.ExcelImportException;
import com.avancial.app.fileImport.excelImport.SocleExcelReadFile;
import com.avancial.app.service.CompagnieService;
import com.avancial.app.service.JeuDonneesService;
import com.avancial.app.service.RefTablesMotriceRegimeService;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.ComparePlanTransportControl;
import com.avancial.app.service.controlePlanTransport.excelImport.eurostar.EurostarDessertesImportProcess;
import com.avancial.app.service.traiteMotriceRegime.ITraiteMotriceRegime;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeFactory;
import com.avancial.app.service.traiteObjetMetier.TraiteObjetMetierRegimeFactory;
import com.avancial.app.traitement.TraitementMotrice;
import com.avancial.app.traitement.TraitementObjetMetier;
import com.avancial.app.utilitaire.GetEntiteService;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.app.utilitaire.pattern.structuredProcess.StructuredProcessException;
import com.avancial.socle.data.model.databean.LogTraitementDataBean;
import com.avancial.socle.ihm.menu.model.databean.PageDataBean;
import com.avancial.socle.logging.ALogBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.scheduler.CdiJobFactory;
import com.avancial.socle.scheduler.service.JobPlanifService;
import com.avancial.socle.traitement.ATraitement;
import com.avancial.socle.utils.ListUtils;
import factory.PlanTransportFactory;
import junit.framework.Assert;

@RunWith(Arquillian.class)
public class TestControlePlanTransport {
	
	@Deployment
    public static WebArchive createDeployment() {
        File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity()
                .as(File.class);

        WebArchive jar = ShrinkWrap.create(WebArchive.class).addPackage(MotriceRegimeEntity.class.getPackage())
                .addPackage(ITraiteMotriceRegime.class.getPackage()).addPackage(ALogBean.class.getPackage())
                .addPackage(ATraitement.class.getPackage()).addPackage(CodeSat.class.getPackage())
                .addPackage(PageDataBean.class.getPackage()).addClass(JeuDonneesService.class)
                .addClass(TraiteObjetMetierRegimeFactory.class).addClass(MapPlansDeTransport.class)
                .addClass(TraitementObjetMetier.class).addClass(RefTablesMotriceRegimeService.class)
                .addClass(TraiteMotriceRegimeFactory.class).addClass(MapPlansDeTransport.class)
                .addClass(TraitementMotrice.class).addPackage(Socle_PUSocle.class.getPackage())
                .addPackage(EntityManagerProducerSocle.class.getPackage()).addPackage(CdiJobFactory.class.getPackage())
                .addPackage(LogTraitementDataBean.class.getPackage()).addPackage(CompagnieService.class.getPackage())
                .addPackage(GetEntiteService.class.getPackage()).addPackage(JobPlanifService.class.getPackage())
                .addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
                .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml").setWebXML("WEB-INF/web.xml")
                .addAsManifestResource("META-INF/context.xml", "context.xml");

        System.out.println(jar.toString(true));

        return jar;
    }
	
//    @Test
    public void testPlanTransport() {
        MapPlansDeTransport mapPlansDeTransport = PlanTransportFactory.createDataForControle();
        PlanTransport pdtXls = mapPlansDeTransport.getPlanTransportDraft();
        PlanTransport pdtBase = mapPlansDeTransport.getPlanTransportActive();
        ComparePlanTransportControl comp = new ComparePlanTransportControl();
        MapComparaisonPlanTransport res;
        MapComparaisonPlanTransport expected = new MapComparaisonPlanTransport();
        List<AComparaisonPlanTransport<IPlanTransport>> listRes = new ArrayList<>();
        List<AComparaisonPlanTransport<IPlanTransport>>  listExp = new ArrayList<>();

        ComparaisonControlePlanTransport<Tranche> compTrancheEgal = new ComparaisonControlePlanTransport<>();
        compTrancheEgal.setAncienField(pdtBase.getTrainByNumeroTrain("trainEgal").getTranches().get(0));
        compTrancheEgal.setNouveauField(pdtXls.getTrainByNumeroTrain("trainEgal").getTranches().get(0));
        compTrancheEgal.setDateCircul(
                pdtXls.getTrainByNumeroTrain("trainEgal").getTranches().get(0).getRegime().getListeJours().get(0));
        compTrancheEgal.setNumeroTranche("trancheEgal");
        compTrancheEgal.setNumeroTrain("trainEgal");
        compTrancheEgal.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.CONTROL);
        compTrancheEgal.setRegimeTranche(pdtXls.getTrainByNumeroTrain("trainEgal").getTranches().get(0).getRegime());

        ComparaisonControlePlanTransport<Tranche> compTrancheSatut = new ComparaisonControlePlanTransport<>();
        compTrancheSatut.setAncienField(pdtBase.getTrainByNumeroTrain("trainControle").getTranches().get(0));
        compTrancheSatut.setNouveauField(pdtXls.getTrainByNumeroTrain("trainControle").getTranches().get(0));
        compTrancheSatut.setDateCircul(
                pdtXls.getTrainByNumeroTrain("trainControle").getTranches().get(0).getRegime().getListeJours().get(0));
        compTrancheSatut.setNumeroTranche("trancheControleStatut");
        compTrancheSatut.setNumeroTrain("trainControle");
        compTrancheSatut.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.CONTROL);
        compTrancheSatut.setRegimeTranche(pdtXls.getTrainByNumeroTrain("trainControle").getTranches().get(0).getRegime());
        
        ComparaisonControlePlanTransport<CodeSat> compCodeSat = new ComparaisonControlePlanTransport<>();
        compCodeSat.setAncienField((CodeSat) pdtBase.getTrainByNumeroTrain("trainControle").getTranches().get(1).getAttributs().get(CodeSat.class).get(0));
        compCodeSat.setNouveauField((CodeSat) pdtXls.getTrainByNumeroTrain("trainControle").getTranches().get(1).getAttributs().get(CodeSat.class).get(0));
        compCodeSat.setDateCircul(
                pdtXls.getTrainByNumeroTrain("trainControle").getTranches().get(1).getRegime().getListeJours().get(0));
        compCodeSat.setNumeroTranche("trancheControleCodeSat");
        compCodeSat.setNumeroTrain("trainControle");
        compCodeSat.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.CONTROL);
        compCodeSat.setRegimeTranche(pdtXls.getTrainByNumeroTrain("trainControle").getTranches().get(1).getRegime());

        expected.putComparaison(compTrancheEgal);
        expected.putComparaison(compTrancheSatut);
        expected.putComparaison(compCodeSat);
        try {
            res = comp.compare(pdtBase, pdtXls);
            listRes = res.getComparaison(EnumTypeComparaisonPlanTransport.CONTROL);
            listExp = expected.getComparaison(EnumTypeComparaisonPlanTransport.CONTROL);
            Assert.assertTrue("Compare",
                    ListUtils.compareLists(listExp, listRes));

        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    @Test
    public void testImportDessertesEurostar() throws FileTypeNotExpectedException {
    	SocleExcelReadFile file = new SocleExcelReadFile("D:\\was_tmp\\tremas\\import\\446\\timetable\\Eurostar Timetable Period D2 2016 V1.0A 2016_01_28.xlsx");
    	EurostarDessertesImportProcess importer = new EurostarDessertesImportProcess();
    	PlanTransport plan = null;
    	try {
			plan = importer.execute(file);
		} catch (StructuredProcessException e) {
			this.showExceptions(e, "testImportDessertesEurostar");
		}
    	Assert.assertEquals("le nombre de trains est incorrecte", 8, plan.getTrains().size());
    	Tranche tranche = plan.getTrainByNumeroTrain("009008").getTranches().get(0);
    	@SuppressWarnings("unchecked")
		List<Desserte> dessertes = (List<Desserte>) tranche.getAttributsField(Desserte.class);
    	Calendar firstDay = new GregorianCalendar();
    	firstDay.setTime(dessertes.get(0).getRegime().getListeJours().get(0));
    	Desserte weekDesserte;
    	Desserte saturdayDesserte;
    	if(firstDay.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
    		saturdayDesserte = dessertes.get(1);
    		weekDesserte = dessertes.get(0);
    	} else {
    		saturdayDesserte = dessertes.get(0);
    		weekDesserte = dessertes.get(1);
    	}
    	Assert.assertEquals("le nombre de gares dans la desserte du samedi du train 9008 est incorrecte", 3, saturdayDesserte.getGareHoraires().size());
    	Calendar depGare2Sem = new GregorianCalendar();
    	depGare2Sem.setTime(weekDesserte.getGareHoraires().get(1).getHoraire().getHoraireFin());
    	Calendar depGare2Sam = new GregorianCalendar();
    	depGare2Sam.setTime(saturdayDesserte.getGareHoraires().get(1).getHoraire().getHoraireFin());
    	Assert.assertEquals("l'horaire de départ de la seconde gare du samedi du train 9008 est incorrecte", 492, depGare2Sam.get(Calendar.HOUR_OF_DAY) * 60 + depGare2Sam.get(Calendar.MINUTE));
    	Assert.assertEquals("l'horaire de départ de la seconde gare de semaine du train 9008 est incorrecte", 492, depGare2Sem.get(Calendar.HOUR_OF_DAY) * 60 + depGare2Sem.get(Calendar.MINUTE));
    }
    
    /**
     * affiche la liste des erreurs d'import.
     * @param e erreur fatale.
     * @param testName nom du test.
     */
    private void showExceptions(StructuredProcessException e, String testName) {
    	System.out.println("une(des) erreure(s) est(sont) survenue(s) lors tu test " + testName + " : ");
		e.printStackTrace();
		AExcelImportContext<?> context = (AExcelImportContext<?>) e.getContext();
		System.out.println("autres erreurs " + (context.getValidationErrors().size() + context.getParsingErrors().size() + context.getExtractionErrors().size()) + " : ");
		if(!context.getParsingErrors().isEmpty()) {
			System.out.println("- de parsing :");
			System.out.println("colonne : " + context.getParsingErrors().get(0).getCell().getColumnIndex() + " / " + "ligne : " + context.getParsingErrors().get(0).getCell().getRowIndex());
			context.getParsingErrors().get(0).printStackTrace();
			for(ExcelImportException error : context.getParsingErrors()) {
				System.out.println("    " + error.getMessage() + " -> " + error.getStackTrace()[0].toString());
			}
		}
		if(!context.getValidationErrors().isEmpty()) {
			System.out.println("- de validation :");
			for(ExcelImportException error : context.getValidationErrors()) {
				System.out.println("    " + error.getMessage() + " -> " + error.getStackTrace()[0].toString());
			}
		}
		if(!context.getExtractionErrors().isEmpty()) {
			System.out.println("- d'extraction :");
			for(ExcelImportException error : context.getExtractionErrors()) {
				System.out.println("    " + error.getMessage() + " -> " + error.getStackTrace()[0].toString());
			}
		}
		Assert.fail("une(des) erreure(s) est(sont) survenue(s) lors tu test " + testName);
    }
}
