/**
 * 
 */
package excel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avancial.app.data.databean.EStatus;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.Composition;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;
import com.avancial.app.data.objetsMetier.PlanTransport.FareProfile;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Repas;
import com.avancial.app.data.objetsMetier.PlanTransport.Restriction;
import com.avancial.app.data.objetsMetier.PlanTransport.ServiceABord;
import com.avancial.app.data.objetsMetier.PlanTransport.Specification;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonDifferentielPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.IComparaisonPlanTransport;
import com.avancial.app.export.ExcelRapportDifferentiel;
import com.avancial.app.service.JeuDonneesService;
import com.avancial.app.service.comparePlanTransport.ComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.service.traiteObjetMetier.TraiteObjetMetierRegimeFactory;
import com.avancial.app.traitement.TraitementObjetMetier;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.ihm.menu.model.databean.PageDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.utils.FileUtils;

import factory.PlanTransportFactory;

/**
 * @author hamza.laterem
 *
 */
@RunWith(Arquillian.class)
public class TestGenerateExcel {
    @Deployment
    public static WebArchive createDeployment() {
        File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity()
                .as(File.class);

        WebArchive jar = ShrinkWrap.create(WebArchive.class).addPackage(ExcelRapportDifferentiel.class.getPackage())
                .addClass(JeuDonneesService.class).addClass(TraiteObjetMetierRegimeFactory.class)
                .addClass(FileUtils.class).addPackage(CodeSat.class.getPackage())
                .addPackage(PageDataBean.class.getPackage()).addClass(JeuDonneesService.class)
                .addClass(TraiteObjetMetierRegimeFactory.class).addClass(MapPlansDeTransport.class)
                .addClass(TraitementObjetMetier.class).addPackage(Socle_PUSocle.class.getPackage())
                .addPackage(EntityManagerProducerSocle.class.getPackage())
                // .addAsManifestResource("arquillian.xml")
                .addPackage(EntityManagerProducerSocle.class.getPackage())
                .addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
                .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
                // .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

        System.out.println(jar.toString(true));

        return jar;
    }

    @Inject
    ExcelRapportDifferentiel excelRapportDifferentiel;

    @Inject
    TraitementObjetMetier traitementObjetMetier;

    /**
     * @return
     * 
     */
    // @Test
    public void TestInject() {
        Assert.assertNotNull(this.excelRapportDifferentiel);
        System.out.println(this.excelRapportDifferentiel.getClass());
        System.out.println(this.excelRapportDifferentiel.getClass().getName());
        System.out.println(this.excelRapportDifferentiel.getClass().getPackage());
        System.out.println(this.excelRapportDifferentiel.getClass().getSimpleName());
        System.out.println(this.excelRapportDifferentiel.getClass().getCanonicalName());

        System.out.println("<==== OBJECT METIERS ====>");
        System.out.println(CodeSat.class.getSimpleName());
        System.out.println(Composition.class.getSimpleName());
        System.out.println(Desserte.class.getSimpleName());
        System.out.println(Distribution.class.getSimpleName());
        System.out.println(FareProfile.class.getSimpleName());
        System.out.println(OrigineDestination.class.getSimpleName());
        System.out.println(Repas.class.getSimpleName());
        System.out.println(Restriction.class.getSimpleName());
        System.out.println(ServiceABord.class.getSimpleName());
        System.out.println(Specification.class.getSimpleName());
        System.out.println(TypeEquipement.class.getSimpleName());
    }

    // @Test
    public void TestPlanTransportFactory() {
        MapPlansDeTransport mapPlansDeTransport = PlanTransportFactory.createDataForCompare();
        Assert.assertNotNull(mapPlansDeTransport);

        // Delete
        ComparaisonDifferentielPlanTransport<IPlanTransport> comparaisonDelete = new ComparaisonDifferentielPlanTransport<>();
        comparaisonDelete.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.DELETE);
        comparaisonDelete.setNumeroTrain("3");
        comparaisonDelete.setNumeroTranche("6");
        // New
        ComparaisonDifferentielPlanTransport<IPlanTransport> comparaisonNew = new ComparaisonDifferentielPlanTransport<>();
        comparaisonDelete.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.NEW);
        comparaisonDelete.setNumeroTrain("5");
        comparaisonDelete.setNumeroTranche("10");

        // Unchanged
        ComparaisonDifferentielPlanTransport<IPlanTransport> comparaisonUnchanged = new ComparaisonDifferentielPlanTransport<>();
        comparaisonUnchanged.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.UNCHANGED);
        comparaisonUnchanged.setNumeroTrain("4");
        comparaisonUnchanged.setNumeroTranche("8");

        // Modify
        ComparaisonDifferentielPlanTransport<IPlanTransport> comparaisonModify = new ComparaisonDifferentielPlanTransport<>();
        comparaisonModify.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.MODIFY);
        comparaisonModify.setNumeroTrain("2");
        comparaisonModify.setNumeroTranche("4");

        //
        ComparaisonDifferentielPlanTransport<IPlanTransport> comparaisonRegimSplit = new ComparaisonDifferentielPlanTransport<>();
        comparaisonRegimSplit.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.REGIMESPLIT);
        comparaisonRegimSplit.setNumeroTrain("2");
        comparaisonRegimSplit.setNumeroTranche("4");

        //
        // ComparaisonPlanTransport<IPlanTransport> comparaisonDelete2 = new
        // ComparaisonPlanTransport<>();
        // comparaisonDelete2.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.DELETE);
        // comparaisonDelete2.setNumeroTrain("2");
        // comparaisonDelete2.setNumeroTranche("4");

        //
        // ComparaisonPlanTransport<IPlanTransport> comparaisonUnchanged2 = new
        // ComparaisonPlanTransport<>();
        // comparaisonUnchanged2.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.UNCHANGED);
        // comparaisonUnchanged2.setNumeroTrain("2");
        // comparaisonUnchanged2.setNumeroTranche("4");
        //
        List<IComparaisonPlanTransport> expected = new ArrayList<>();
        expected.add(comparaisonDelete);
        expected.add(comparaisonNew);
        expected.add(comparaisonUnchanged);
        expected.add(comparaisonModify);
        expected.add(comparaisonRegimSplit);
        // expected.add(comparaisonDelete2);
        // expected.add(comparaisonUnchanged2);
        try {
            IComparePlanTransport comparePlanTransport = new ComparePlanTransport();
            MapComparaisonPlanTransport compare = new MapComparaisonPlanTransport();
            compare = comparePlanTransport.compare(mapPlansDeTransport.get(EStatus.ACTIVE).getPlanTransport(),
                    mapPlansDeTransport.get(EStatus.DRAFT).getPlanTransport());
            System.out.println("");
            // Assert.assertTrue("Compare",
            // ListUtils.compareLists(expected, compare));
        }
        catch (Exception ex) {
            System.err.println("====== Erreur dans le test");
        }
    }

    // @Test
    public void TestGenerateExcelDonneesParDefautEtInject() {
        try {
            MapPlansDeTransport mapPlansDeTransport = new MapPlansDeTransport();
            this.traitementObjetMetier.setMapPlansDeTransport(mapPlansDeTransport);
            this.traitementObjetMetier.setEnvironnementCompagnie("ES_PROD");

            this.traitementObjetMetier.execute();
            mapPlansDeTransport.setPlanTransportDraft(new JeuDonneeEntity(), mapPlansDeTransport.getPlanTransportActive());
            mapPlansDeTransport.setPlanTransportActive(new JeuDonneeEntity(), new PlanTransport());

            IComparePlanTransport comparePlanTransport = new ComparePlanTransport();

            this.excelRapportDifferentiel.setDatas(comparePlanTransport.compare(
                    mapPlansDeTransport.getPlanTransportActive(), mapPlansDeTransport.getPlanTransportDraft()));
            this.excelRapportDifferentiel.setMapPlansDeTransport(mapPlansDeTransport);

            mapPlansDeTransport.getPlanTransportActive();
            mapPlansDeTransport.getPlanTransportDraft();

            this.excelRapportDifferentiel.generate();
            if (!FileUtils.existFile("D:/was_tmp/tremas/export.xls")) {
                Assert.assertTrue(false);
            }
            else
                Assert.assertTrue(true);

        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println(e.getMessage());
            Assert.assertTrue(false);
        }
        Assert.assertNotNull(this.excelRapportDifferentiel);
    }

    @Test
    public void TestGenerateExcelLocalAvecParamsAndInject2() {
        try {
            MapPlansDeTransport mapPlansDeTransport = new MapPlansDeTransport();
            mapPlansDeTransport.putAll(PlanTransportFactory.createDataForCompare());
            // this.traitementObjetMetier.setMapPlansDeTransport(mapPlansDeTransport);
            // this.traitementObjetMetier.setEnvironnementCompagnie("ES_PROD");

            // this.traitementObjetMetier.execute();
            // mapPlansDeTransport.setPlanTransportDraft(mapPlansDeTransport.getPlanTransportActive());
            // mapPlansDeTransport.setPlanTransportActive(new PlanTransport());

            IComparePlanTransport comparePlanTransport = new ComparePlanTransport();

            this.excelRapportDifferentiel.setDatas(comparePlanTransport.compare(
                    mapPlansDeTransport.getPlanTransportActive(), mapPlansDeTransport.getPlanTransportDraft()));
            this.excelRapportDifferentiel.setMapPlansDeTransport(mapPlansDeTransport);

            // Ajout des params
            this.excelRapportDifferentiel.setFileName("Test_TestGenerateExcelLocalAvecParamsAndInject");
            this.excelRapportDifferentiel.setFilePath("D:/was_tmp/tremas/export/");
            this.excelRapportDifferentiel.setXlsx(true);

            this.excelRapportDifferentiel.generate();
            if (!FileUtils.existFile("D:/was_tmp/tremas/export/Test_TestGenerateExcelLocalAvecParamsAndInject.xlsx")) {
                Assert.assertTrue(false);
            }
            else
                Assert.assertTrue(true);

        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println(e.getMessage());
            Assert.assertTrue(false);
        }
        Assert.assertNotNull(this.excelRapportDifferentiel);
    }

}
