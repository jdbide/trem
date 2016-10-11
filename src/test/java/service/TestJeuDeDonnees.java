/**
 * 
 */
package service;

import java.io.File;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.EStatus;
import com.avancial.app.service.JeuDonneesService;
import com.avancial.socle.ihm.menu.model.databean.PageDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author bruno.legloahec
 *
 */
@RunWith(Arquillian.class)
public class TestJeuDeDonnees {
   @Deployment
   public static WebArchive createDeployment() {
      File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity().as(File.class);

      WebArchive jar = ShrinkWrap.create(WebArchive.class)
            .addPackage(PageDataBean.class.getPackage())
            .addPackage(JeuDonneeEntity.class.getPackage())
            // .addPackage(IhmPageDao.class.getPackage())

            .addClass(JeuDonneesService.class)
            .addPackage(Socle_PUSocle.class.getPackage()).addPackage(EntityManagerProducerSocle.class.getPackage())
            // .addAsManifestResource("arquillian.xml")
            .addPackage(EntityManagerProducerSocle.class.getPackage()).addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib).addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
            // .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

      System.out.println(jar.toString(true));

      return jar;
   }

   @Inject
   JeuDonneesService service;
   
   @Inject
   @Socle_PUSocle
   EntityManager entityManager;

   @Test
   public void TestInjection() {
      Assert.assertNotNull(this.service);
   }
   
   @Test
   public void TestCreateNewJeuDonnees() {
      try {
         Query query = this.entityManager.createQuery("SELECT t FROM CompagnieEnvironnementEntity t where t.idCompagnieEnvironnement = 1");
         CompagnieEnvironnementEntity compagnieEnvironnement = ((CompagnieEnvironnementEntity) query.getSingleResult());
         
         
//         DatasourceEntity datasourceEntity = new DatasourceEntity();
//         
//         datasourceEntity.setActifDatasource(true);
//         datasourceEntity.setCommentaireDataSource("");
//         datasourceEntity.setDriverClassName("");
//         datasourceEntity.setLibelleDataSource("");
//         datasourceEntity.setNomTechniqueDataSource("T");
//         datasourceEntity.setUrl("");
//         
//         this.entityManager.getTransaction().begin();
//         this.entityManager.persist(datasourceEntity);
//         //this.entityManager.flush();
//         this.entityManager.getTransaction().commit();
         
         JeuDonneeEntity tremasJD = new JeuDonneeEntity();
         tremasJD.setCompagnieEnvironnement(compagnieEnvironnement);
         tremasJD.setDateCreateJeuDonnees(new Date());
         tremasJD.setDateLastUpdateJeuDonnees(new Date());
         tremasJD.setIdUtilisateurCreateJeuDonnees(-1);
         tremasJD.setIdUtilisateurLastUpdateJeuDonnees(-1);
         tremasJD.setStatusJeuDonnees(EStatus.IMPORT);
         this.entityManager.getTransaction().begin();
         this.entityManager.persist(tremasJD);
         this.entityManager.flush();
         this.entityManager.getTransaction().commit();
         Assert.assertNotNull(tremasJD.getIdJeuDonnees());
         Assert.assertFalse(tremasJD.getIdJeuDonnees() == 0);
      } catch (Exception e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      }
      
      
   }

}
