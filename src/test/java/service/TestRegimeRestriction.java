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
import com.avancial.app.data.databean.importMotrice.MotriceRegimeRestrictionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.service.JeuDonneesService;
import com.avancial.app.service.traiteMotriceRegime.ITraiteMotriceRegime;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeFactory;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;
import com.avancial.socle.ihm.menu.model.databean.PageDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

@RunWith(Arquillian.class)
public class TestRegimeRestriction {
   @Deployment
   public static WebArchive createDeployment() {
      File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity().as(File.class);

      WebArchive jar = ShrinkWrap.create(WebArchive.class).addPackage(PageDataBean.class.getPackage()).addClass(JeuDonneesService.class).addPackage(Socle_PUSocle.class.getPackage()).addPackage(EntityManagerProducerSocle.class.getPackage()).addPackage(EntityManagerProducerSocle.class.getPackage())
            .addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib).addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml").setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

      System.out.println(jar.toString(true));

      return jar;
   }

   @Inject
   @Socle_PUSocle
   EntityManager em;

   @Test
   public void testRegimeDistribution() throws Exception {
      this.em.clear();

      JeuDonneeEntity jeuDonneeEntity = new JeuDonneeEntity();
      jeuDonneeEntity.setIdJeuDonnees(3);

      Query query = this.em.createNamedQuery("selectMotriceTrainTranche");

      List<Object[]> trainsTranches = query.getResultList();
      long cpt = 1;
      MotriceTrainTrancheEntity motriceTrainTrancheEntity;
      TraiteMotriceRegimeFactory traiteMotriceRegimeFactory = new TraiteMotriceRegimeFactory();
      ITraiteMotriceRegime traiteMotriceRegime = traiteMotriceRegimeFactory.getTraiteMotriceRegime(MotriceRegimeRestrictionEntity.class);
      MapIdTablesMotriceRegime mapIdTablesMotriceRegime = new MapIdTablesMotriceRegime(this.em);
      MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime = new MapGeneratorTablesMotriceRegime(this.em.unwrap(Session.class), 250);
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

         traiteMotriceRegime.traite(motriceTrainTrancheEntity, mapIdTablesMotriceRegime, mapGeneratorTablesMotriceRegime, this.em, null);

      }

      mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class).executeRequest();

      mapGeneratorTablesMotriceRegime.get(MotriceRegimeRestrictionEntity.class).executeRequest();

   }

}
