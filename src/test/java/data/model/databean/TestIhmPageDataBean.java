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
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avancial.app.persistence.EntityManagerFactoryProviderDb2;
import com.avancial.socle.data.model.databean.IhmChapitreDataBean;
import com.avancial.socle.data.model.databean.IhmPageDataBean;
import com.avancial.socle.data.model.databean.IhmRubriqueDataBean;
import com.avancial.socle.data.model.databean.RoleDataBean;
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
            .addPackage(EntityManagerProducerSocle.class.getPackage())
            .addPackage(EntityManagerFactoryProviderDb2.class.getPackage()).addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib).addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
            // .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

      System.out.println("==> " + jar.toString(true));

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
   
   EntityManager emDb2;
   
   
   
   
   
   @Test 
   public void testInjection() {
      try {
         this.emDb2 = EntityManagerFactoryProviderDb2.getInstance("dbad_tremas", "!tremas-12").createEntityManager();
      } catch (Throwable ex) {
         ex.printStackTrace();
      }
      Assert.assertNotNull(this.emDb2);
      System.out.println("Injection de EntityManagerProviderDb2 !");      
      Assert.assertNotNull(this.em);
      System.out.println("Injection de l'entity manager !");
      Assert.assertNotNull(this.page);
      System.out.println("Injection de l'entity page !");
      Assert.assertNotNull(this.chapitre);
      System.out.println("Injection de l'entity chapitre !");
      Assert.assertNotNull(this.rubrique);
      System.out.println("Injection de l'entity rubrique !");
   }
   
   @Test
   public void testConcuranceEntityManager2 () {
      EntityManager nEm = NoteEntityManager.getInstance().getEntityManager();
      EntityManager myEm2 = Persistence.createEntityManagerFactory("PU_socle").createEntityManager();
      RoleDataBean role = new RoleDataBean();
      role.setLabelRole("Test - hamza");
      role.setTechnicalNameRole("TEST - HAMZA");
      try {
         EntityTransaction  t1 = nEm.getTransaction();
         EntityTransaction  t2 = myEm2.getTransaction();
         
         t1.begin();
         t2.begin();
         
         rubrique.setLibelleIhmRubrique("Rubrique - Hamza");
         nEm.persist(rubrique);
         myEm2.persist(role);
                  
         t1.commit();
         t2.commit();
         
         //nEm.close();         
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }
   
   @Test
   public void concuranceEntityManager () {
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_socle");
      System.out.println(emf.getProperties());
      EntityManager myEm = emf.createEntityManager();
      RoleDataBean role = new RoleDataBean();
      role.setLabelRole("Test - hamza");
      role.setTechnicalNameRole("TEST - HAMZA");
      try {
         EntityTransaction  t1 = myEm.getTransaction();
         EntityTransaction  t2 = myEm.getTransaction();
         
         t1.begin();
         t2.begin();
         
         rubrique.setLibelleIhmRubrique("Rubrique - Hamza");
         myEm.persist(rubrique);
         myEm.persist(role);
                  
         t1.commit();
         t2.commit();
         
         myEm.close();
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }
   
   public void initPage(String name, EntityManager myEM) {
      myEM.getTransaction().begin();
      rubrique.setLibelleIhmRubrique("Rubrique - " + name);
      myEM.persist(rubrique);
      System.out.println("Ajout d'un rubrique name : \"Rubrique - " + name + "\"");

      chapitre.setLibelleIhmChapitre("Chapitre - " + name);
      chapitre.setIhmRubriqueTypeDataBean(rubrique);
      myEM.persist(chapitre);
      System.out.println("Ajout d'un chapitre name : \"Chapitre - " + name + "\"");

      page.setLibelleIhmPage("Page - " + name);
      page.setIhmChapitreDataBean(chapitre);
      myEM.persist(page);
      System.out.println("Ajout d'un chapitre Page : \"Page - " + name + "\"");
      
      myEM.flush();
      myEM.getTransaction().commit();  
      myEM.close();
   }
   
   @Test
   public void addPageWithPUSocle() {
      System.out.println("Utilisation de PU_SOCLE (Serveu: \"caliban\" => bdd : \"tremas_prod\")");
      initPage("tremas_prod", this.em);
      
      Assert.assertNotNull(rubrique);
      Assert.assertNotNull(chapitre);
      Assert.assertNotNull(page);
   }
   
   @Test
   public void addPageWithPUDB2() {
      try {
         this.emDb2 =EntityManagerFactoryProviderDb2.getInstance("dbad_tremas", "!tremas-12").createEntityManager();     
      } catch (Throwable ex) {
         ex.printStackTrace();
      }
      
      System.out.println("Utilisation de PU_SOCLE (Serveu: \"caliban\" => bdd : \"tremas_test\")");
      initPage("tremas_test", this.emDb2);
      
      Assert.assertNotNull(rubrique);
      Assert.assertNotNull(chapitre);
      Assert.assertNotNull(page);
   }
   
   @Test
   public void getPageWithPUSocle() {
      IhmPageDataBean test;

      test = (IhmPageDataBean) this.em.createQuery("From IhmPageDataBean p where p.libelleIhmPage='Page - tremas_prod'").getSingleResult();

      Assert.assertTrue(test.getLibelleIhmPage().equals("Page - tremas_prod"));
   }
   
   @Test
   public void getPageWithPUDB2() {
      try {
         this.emDb2 = EntityManagerFactoryProviderDb2.getInstance("dbad_tremas", "!tremas-12").createEntityManager();
      } catch (Throwable ex) {
         ex.printStackTrace();
      }
      
      IhmPageDataBean test;

      test = (IhmPageDataBean) this.emDb2.createQuery("From IhmPageDataBean p where p.libelleIhmPage='Page - tremas_test'").getSingleResult();

      Assert.assertTrue(test.getLibelleIhmPage().equals("Page - tremas_test"));
   }
   
   /*
      
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
      
   }*/

}
