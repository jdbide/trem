package traitement;

import java.io.File;
import java.text.SimpleDateFormat;
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
import com.avancial.app.data.objetsMetier.PlanTransport.ARegimeComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.JeuDonneeService;
import com.avancial.socle.data.model.databean.IhmPageDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

@RunWith(Arquillian.class)
public class RemplissageObjMetierTest {
    @Deployment
    public static WebArchive createDeployment() {
        File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity()
                .as(File.class);

        WebArchive jar = ShrinkWrap.create(WebArchive.class).addPackage(CodeSat.class.getPackage())
                .addPackage(IhmPageDataBean.class.getPackage()).addClass(JeuDonneeService.class)
                .addPackage(Socle_PUSocle.class.getPackage()).addPackage(EntityManagerProducerSocle.class.getPackage())
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

    @Test
    public void testRemplissageDesserteObjMetier() throws Exception {
        this.em.clear();
        SimpleDateFormat formatter = new SimpleDateFormat("HHmm");

        /* Creation du plan de transport */
        PlanTransport planTransport = new PlanTransport(EnumCompagnies.ES, new ArrayList<Train>());

        Query query = this.em.createNativeQuery(
                "SELECT DISTINCT train.trainNumberMotriceTrainTranche AS numeroTrain, train.validForRRMotriceTrainTranche AS validePourRR "
                        + "FROM tremas_motrice_traintranche AS train ");

        List<Object[]> trains = query.getResultList();

        /* Remplissage de la liste des trains */
        for (Object[] resTrain : trains) {
            Train train = new Train(new ArrayList<Tranche>(), (String) resTrain[0], true);

            query = this.em.createNativeQuery(
                    "SELECT tranche.idMotriceTrainTranche AS idMotriceTrainTranche, tranche.trancheNumberMotriceTrainTranche, tranche.trancheStatusMotriceTrainTranche AS trancheStatut, regime.periodMotriceRegime AS regimeTranche "
                            + "FROM tremas_motrice_traintranche AS tranche "
                            + "INNER JOIN tremas_motrice_regime AS regime ON regime.idMotriceTrainTranche = tranche.idMotriceTrainTranche "
                            + "WHERE regime.idMotriceRefRegimeType = 1 "
                            + "AND tranche.trainNumberMotriceTrainTranche = ?");
            query.setParameter(1, (String) resTrain[0]);

            List<Object[]> tranches = query.getResultList();

            /* Remplissage de la liste des tranches */
            for (Object[] resTranche : tranches) {
                Tranche tranche = new Tranche();
                Regime regime = new Regime();
                regime.setCodeRegime((String) resTranche[3]);
                tranche.setRegime(regime);
                tranche.setNumeroTranche((String) resTranche[1]);

                query = this.em.createNativeQuery(
                        "SELECT distrib.distribIndexMotriceRegimeDistribution AS indiceDistribution, regime.periodMotriceRegime AS regimePeriod "
                                + "FROM tremas_motrice_regime_distribution AS distrib "
                                + "INNER JOIN tremas_motrice_regime AS regime ON regime.idMotriceRegime = distrib.idMotriceRegime "
                                + "WHERE regime.idMotriceRefRegimeType = 10 AND regime.idMotriceTrainTranche = ?");
                query.setParameter(1, resTranche[0]);

                List<Object[]> distributions = query.getResultList();

                List<ARegimeComparable> listeDistribution = new ArrayList<ARegimeComparable>();

                /* Remplissage de la liste des distributions */
                for (Object[] resDistribution : distributions) {
                    Regime regimeDistri = new Regime();
                    regimeDistri.setCodeRegime((String) resDistribution[1]);
                    listeDistribution.add(new Distribution((String) resDistribution[0], regimeDistri));
                }
                /* Ajout de la liste des distributions à la tranche */
                tranche.addAttributsField(listeDistribution);
                /* Ajout de la liste de la tranche au train */
                train.getTranches().add(tranche);
            }
            /* Ajout du train au plan de transport */
            planTransport.getTrains().add(train);
        }

        Distribution distribution;
        for (Train train : planTransport.getTrains()) {
            for (Tranche tranche : train.getTranches()) {
                for (ARegimeComparable regimeComparable : tranche.getAttributsField(Distribution.class)) {
                    distribution = (Distribution) regimeComparable;
                    System.out.println("(" + train.getNumeroTrain() + ", " + tranche.getNumeroTranche() + ", "
                            + tranche.getRegime().getCodeRegime() + ", " + distribution.getIndiceDistribution() + ", "
                            + distribution.getRegime().getCodeRegime() + ")");
                }
            }
        }

    }

}