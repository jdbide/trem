/**
 * 
 */
package data.model.databean;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avancial.app.persistence.EntityManagerProviderDb2;
import com.avancial.socle.data.model.databean.IhmChapitreDataBean;
import com.avancial.socle.data.model.databean.IhmPageDataBean;
import com.avancial.socle.data.model.databean.IhmRubriqueDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author bruno.legloahec
 *
 */
@RunWith(Arquillian.class)
public class TestIhmPageDataBean {

   @Deployment
   public static WebArchive createDeployment() {
      File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity().as(File.class);

      WebArchive jar = ShrinkWrap.create(WebArchive.class).addPackage(IhmPageDataBean.class.getPackage())
            // .addPackage(IhmPageDao.class.getPackage())

            // .addClass(PhraseBuilder.class)
            // .addAsManifestResource("arquillian.xml")
            .addPackage(EntityManagerProducerSocle.class.getPackage()).addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib).addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
            .addPackage(EntityManagerProviderDb2.class.getPackage()).addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib).addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
            // .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

      System.out.println(jar.toString(true));

      return jar;
   }

   @Inject
   IhmPageDataBean     page;

   @Inject
   IhmChapitreDataBean chapitre;

   @Inject
   IhmRubriqueDataBean rubrique;

   // @Inject
   // IhmPageDao dao;

   /*
    * @Hamza : Tutoriel : http://rmannibucau.developpez.com/tutoriels/cdi/introduction-cdi/
    */
   @Inject
   @Socle_PUSocle
   EntityManager       em;  
    
   
   public void initPage() {
      

      
      em.getTransaction().begin();
      // rubrique.setIdRubrique(1L);
      rubrique.setLibelleIhmRubrique("Rubrique");

      em.persist(rubrique);

      // chapitre.setIdChapitre(1L);
      chapitre.setLibelleIhmChapitre("Chapitre");
      chapitre.setIhmRubriqueTypeDataBean(rubrique);
      em.persist(chapitre);

      // page.setIdPage(1L);
      page.setLibelleIhmPage("Test");
      page.setIhmChapitreDataBean(chapitre);
      em.persist(page);
      em.flush();
      em.getTransaction().commit();

      // page.setIdPage(2L);
      // page.setLibellePage("Test2");
      // em.persist(page);
   }

   @Test
   public void testPageInjection() {
      initPage();
      Assert.assertTrue(null != page);
      IhmPageDataBean test;
      // test = em.find(IhmPageDataBean.class, "Test");
      ArrayList<IhmPageDataBean> toto = (ArrayList<IhmPageDataBean>) em.createQuery("From IhmPageDataBean").getResultList();

      test = (IhmPageDataBean) em.createQuery("From IhmPageDataBean p where p.libelleIhmPage='Test'").getSingleResult();

      Assert.assertTrue(test.getLibelleIhmPage().equals("Test"));
   }

   @Test
   public void testDaoInjection() {
      Assert.assertTrue(null != this.em);
   }
      
   @Test
   public void testInjectionConfPersist() {
   // dynamic configuration
      Map<String, String> props = new HashMap<>();
      props.put("javax.persistence.jdbc.user", "dbad_tremas");
      props.put("javax.persistence.jdbc.password", "!tremas-12");            
      
      EntityManager mm = null;
      try {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-db2", props);
         
         
         mm = emf.createEntityManager();
      }
      catch (Throwable ex) 
      {
                  ex.printStackTrace();
          //throw new RuntimeException("Error al crear la factoria de JPA:->"+ ex.getMessage());
      }
            
      mm.getTransaction().begin();
      // rubrique.setIdRubrique(1L);
      rubrique.setLibelleIhmRubrique("Rubrique");

      mm.persist(rubrique);

      // chapitre.setIdChapitre(1L);
      chapitre.setLibelleIhmChapitre("Chapitre");
      chapitre.setIhmRubriqueTypeDataBean(rubrique);
      mm.persist(chapitre);

      // page.setIdPage(1L);
      page.setLibelleIhmPage("Test");
      page.setIhmChapitreDataBean(chapitre);
      mm.persist(page);
      mm.flush();
      mm.getTransaction().commit();
      
   }
   
   EntityManagerProviderDb2 emDb2;
   
   @Test
   public void testInjectionConfPersistWithEntityManagerProviderDb2() {         
      EntityManager mm = null;
      
      try {
         this.emDb2 = EntityManagerProviderDb2.getInstance("dbad_tremas", "!tremas-12");      
         mm = this.emDb2.getEm();
      }
      catch (Throwable ex) 
      {
                  ex.printStackTrace();
          //throw new RuntimeException("Error al crear la factoria de JPA:->"+ ex.getMessage());
      }
            
      mm.getTransaction().begin();
      // rubrique.setIdRubrique(1L);
      rubrique.setLibelleIhmRubrique("Rubrique testInjectionConfPersistWithEntityManagerProviderDb2");

      mm.persist(rubrique);

      // chapitre.setIdChapitre(1L);
      chapitre.setLibelleIhmChapitre("Chapitre");
      chapitre.setIhmRubriqueTypeDataBean(rubrique);
      mm.persist(chapitre);

      // page.setIdPage(1L);
      page.setLibelleIhmPage("Test");
      page.setIhmChapitreDataBean(chapitre);
      mm.persist(page);
      mm.flush();
      mm.getTransaction().commit();
      
   }

}
