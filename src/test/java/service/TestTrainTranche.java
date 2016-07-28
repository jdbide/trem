/**
 * 
 */
package service;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Session;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeODEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.service.JeuDonneeService;
import com.avancial.app.service.traiteMotriceRegime.ITraiteMotriceRegime;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeFactory;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;
import com.avancial.socle.data.model.databean.IhmPageDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author bruno.legloahec
 *
 */
@RunWith(Arquillian.class)
public class TestTrainTranche {
    @Deployment
    public static WebArchive createDeployment() {
        File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity()
                .as(File.class);

        WebArchive jar = ShrinkWrap.create(WebArchive.class).addPackage(IhmPageDataBean.class.getPackage())
                .addClass(JeuDonneeService.class).addPackage(Socle_PUSocle.class.getPackage())
                .addPackage(EntityManagerProducerSocle.class.getPackage())
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
    public void testTranscodage() {
        this.em.clear();

        JeuDonneeEntity jeuDonneeEntity = new JeuDonneeEntity();
        jeuDonneeEntity.setIdJeuDonnees(3);

        Query query = this.em.createNamedQuery("selectMotriceTrainTranche");

        List<Object[]> trainsTranches = query.getResultList();
        long cpt = 1;
        MotriceTrainTrancheEntity motriceTrainTrancheEntity;
        for (Object[] record : trainsTranches) {
            motriceTrainTrancheEntity = new MotriceTrainTrancheEntity();
            motriceTrainTrancheEntity.setIdMotriceTrainTranche(cpt++);
            motriceTrainTrancheEntity.setJeuDonnee(jeuDonneeEntity);
            motriceTrainTrancheEntity.setTrainNumberMotriceTrainTranche((String) record[0]);
            motriceTrainTrancheEntity.setTrancheNumberMotriceTrainTranche((String) record[1]);
            motriceTrainTrancheEntity.setValidForRRMotriceTrainTranche(((BigInteger) record[2]).intValue() == 1);
            motriceTrainTrancheEntity.setTrancheStatusMotriceTrainTranche((String) record[3]);

             this.em.getTransaction().begin();
             this.em.persist(motriceTrainTrancheEntity);
             this.em.getTransaction().commit();

//            Query queryRDesserte = this.em.createNativeQuery(
//                    "SELECT desserte.GADS_INPT_RR_GAR AS station, desserte.GADS_DEB_ARRET AS arrivalHour, desserte.GADS_FIN_ARRET AS departureHour, distrib.DSTR_REGI AS regime "
//                            + "FROM tremas_import_tmdgads AS desserte "
//                            + "INNER JOIN tremas_import_tmddstr AS distrib ON desserte.GADS_DSTR_COD_CIE = distrib.DSTR_TRA1_COD_CIE "
//                            + "AND desserte.GADS_DSTR_NUM_TRA1 = distrib.DSTR_TRA1_NUM_TRA1 "
//                            + "AND desserte.GADS_DSTR_IND_FER = distrib.DSTR_TRA1_IND_FER "
//                            + "AND desserte.GADS_DSTR_NUM = distrib.DSTR_NUM "
//                            + "INNER JOIN tremas_import_tmdcath AS cat ON desserte.GADS_DSTR_COD_CIE = cat.CATH_CIRR_COD_CIE "
//                            + "AND desserte.GADS_DSTR_NUM_TRA1 = cat.CATH_TRCH_NUM_TRA1 "
//                            + "AND desserte.GADS_DSTR_IND_FER = cat.CATH_TRCH_IND_FER "
//                            + "AND desserte.GADS_DSTR_NUM = cat.CATH_NUM " + "WHERE cat.CATH_SSIM = ? "
//                            + "AND cat.CATH_TRCH_NUM_TRA1 = ?");
//            queryRDesserte.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
//            queryRDesserte.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());
//
//            List<Object[]> rDesserte = queryRDesserte.getResultList();
        }

        // PropertyMap<Object[], MotriceTrainTrancheEntity> userMap = new
        // PropertyMap<Object[], MotriceTrainTrancheEntity>() {
        // @Override
        // protected void configure() {
        // map().setTrainNumberMotriceTrainTranche((String) this.source[0]);
        // map().setTrancheNumberMotriceTrainTranche((String) this.source[1]);
        // map().setValidForRRMotriceTrainTranche(
        // new Boolean("1".equals(this.source[2].toString()) ? "true" :
        // "false"));
        // map().setTrancheStatusMotriceTrainTranche((String) this.source[3]);
        //
        // }
        // };
        //
        // ModelMapper modelMapper = new ModelMapper();
        // modelMapper.addMappings(userMap);
        //
        // for (Object[] record : trainsTranches) {
        // motriceTrainTrancheEntity = modelMapper.map(record,
        // MotriceTrainTrancheEntity.class);
        // motriceTrainTrancheEntity.setJeuDonnee(jeuDonneeEntity);
        //
        // this.em.getTransaction().begin();
        // this.em.persist(motriceTrainTrancheEntity);
        // this.em.getTransaction().commit();
        // }

    }

//    public void testMotriceRegime() throws Exception {
//        MotriceTrainTrancheEntity motriceTrainTrancheEntity = new MotriceTrainTrancheEntity();
//        motriceTrainTrancheEntity.setIdMotriceTrainTranche((long) 1);
//        motriceTrainTrancheEntity.setJeuDonnee(new JeuDonneeEntity());
//        motriceTrainTrancheEntity.setTrainNumberMotriceTrainTranche("");
//        motriceTrainTrancheEntity.setTrancheNumberMotriceTrainTranche("");
//        motriceTrainTrancheEntity.setValidForRRMotriceTrainTranche(true);
//        motriceTrainTrancheEntity.setTrancheStatusMotriceTrainTranche("");
//
//        TraiteMotriceRegimeFactory traiteMotriceRegimeFactory = new TraiteMotriceRegimeFactory();
//        ITraiteMotriceRegime traiteMotriceRegime = traiteMotriceRegimeFactory
//                .getTraiteMotriceRegime(MotriceRegimeODEntity.class);
//        MapIdTablesMotriceRegime mapIdTablesMotriceRegime = new MapIdTablesMotriceRegime();
//        MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime = new MapGeneratorTablesMotriceRegime(
//                this.em.unwrap(Session.class), 250);
//        traiteMotriceRegime.traite(motriceTrainTrancheEntity, mapIdTablesMotriceRegime, mapGeneratorTablesMotriceRegime,
//                this.em);
//
//        mapGeneratorTablesMotriceRegime.get(MotriceRegimeODEntity.class).executeRequest();
//        mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class).executeRequest();
//    }
}
