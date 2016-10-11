package service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.EStatus;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonDifferentielPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.IComparaisonPlanTransport;
import com.avancial.app.service.JeuDonneesService;
import com.avancial.app.service.RefTablesMotriceRegimeService;
import com.avancial.app.service.comparePlanTransport.ComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.traiteMotriceRegime.ITraiteMotriceRegime;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeFactory;
import com.avancial.app.service.traiteObjetMetier.TraiteObjetMetierRegimeFactory;
import com.avancial.app.traitement.TraitementMotrice;
import com.avancial.app.traitement.TraitementObjetMetier;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.ihm.menu.model.databean.PageDataBean;
import com.avancial.socle.logging.ALogBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.traitement.ATraitement;
import com.avancial.socle.utils.ListUtils;

import junit.framework.Assert;

@RunWith(Arquillian.class)
public class TestComparaisonPlansTransport {
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
                .addPackage(EntityManagerProducerSocle.class.getPackage())
                .addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
                .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml").setWebXML("WEB-INF/web.xml")
                .addAsManifestResource("META-INF/context.xml", "context.xml");

        System.out.println(jar.toString(true));

        return jar;
    }

    @Inject
    @Socle_PUSocle
    EntityManager em;

    @Inject
    TraitementObjetMetier traitementObjetMetier;

    @Inject
    TraitementMotrice traitementMotrice;

    @Inject
    MapPlansDeTransport mapPlansDeTransport;

    @Test
    public void testComparaison() throws Exception {
        try {
            this.em.clear();

            JeuDonneeEntity jeuDonneeEntity = new JeuDonneeEntity();
            jeuDonneeEntity.setIdJeuDonnees(2);

            Query query = this.em
                    .createQuery("SELECT t FROM CompagnieEnvironnementEntity t where t.idCompagnieEnvironnement = 1");
            CompagnieEnvironnementEntity compagnieEnvironnement = ((CompagnieEnvironnementEntity) query
                    .getSingleResult());

            jeuDonneeEntity.setCompagnieEnvironnement(compagnieEnvironnement);
            this.traitementMotrice.setJeuDonneeEntity(jeuDonneeEntity);

            try {
                this.traitementObjetMetier.setMapPlansDeTransport(this.mapPlansDeTransport);
                this.traitementObjetMetier
                        .setEnvironnementCompagnie(compagnieEnvironnement.getNomTechniqueCompagnieEnvironnement());
                this.traitementObjetMetier.execute();
                List<AComparaisonPlanTransport<IPlanTransport>> expected = new ArrayList<>();

                for (Train train : this.mapPlansDeTransport.get(EStatus.DRAFT).getPlanTransport().getTrains()) {
                    for (Tranche tranche : train.getTranches()) {
                        ComparaisonDifferentielPlanTransport<IPlanTransport> cpt = new ComparaisonDifferentielPlanTransport<>();
                        cpt.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.NEW);
                        cpt.setNumeroTrain(train.getNumeroTrain());
                        cpt.setNumeroTranche(tranche.getNumeroTranche());
                        expected.add(cpt);
                    }
                }

                IComparePlanTransport comparePlanTransport = new ComparePlanTransport();
                try {
                    Assert.assertTrue("Compare NEW PlanTransport",
                            ListUtils.compareLists(expected,
                                    comparePlanTransport
                                            .compare(this.mapPlansDeTransport.get(EStatus.ACTIVE).getPlanTransport(),
                                                    this.mapPlansDeTransport.get(EStatus.DRAFT).getPlanTransport())
                                            .getComparaison(EnumTypeComparaisonPlanTransport.NEW)));
                }
                catch (Throwable e1) {
                    throw e1;
                }
            }
            catch (Throwable ex) {
                throw ex;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
