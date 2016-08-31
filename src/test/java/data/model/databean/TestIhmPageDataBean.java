/**
 * 
 */
package data.model.databean;

import java.io.File;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.data.model.databean.RoleDataBean;
import com.avancial.socle.ihm.menu.Rubrique;
import com.avancial.socle.ihm.menu.model.databean.ChapitreDataBean;
import com.avancial.socle.ihm.menu.model.databean.PageDataBean;
import com.avancial.socle.ihm.menu.model.databean.RubriqueDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUMemoire;
import com.avancial.socle.session.Session;

/**
 * @author bruno.legloahec
 *
 */
@RunWith(Arquillian.class)
public class TestIhmPageDataBean {

   @Deployment
   public static WebArchive createDeployment() {
      File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity().as(File.class);

      WebArchive jar = ShrinkWrap.create(WebArchive.class).addPackages(true, Rubrique.class.getPackage())
            // .addPackage(IhmPageDao.class.getPackage())

            // .addClass(PhraseBuilder.class)
            // .addAsManifestResource("arquillian.xml")
            .addPackage(RoleDataBean.class.getPackage()).addPackage(AbstractDao.class.getPackage()).addPackage(EntityManagerProducerSocle.class.getPackage()).addPackage(EntityManagerProducerMemoire.class.getPackage()).addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
            .addPackage(Session.class.getPackage()).addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml").addPackages(true, "com.avancial.socle.authentification")
            // .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

      System.out.println(jar.toString(true));

      return jar;
   }

   @Inject
   PageDataBean     page;

   @Inject
   ChapitreDataBean chapitre;

   @Inject
   RubriqueDataBean rubrique;

   // @Inject
   // IhmPageDao dao;

   @Inject
   @Socle_PUMemoire
   EntityManager    em;

   public void initPage() {

      em.getTransaction().begin();
      // rubrique.setIdRubrique(1L);
      rubrique.setLibelle("Rubrique");

      em.persist(rubrique);

      // chapitre.setIdChapitre(1L);
      chapitre.setLibelle("Chapitre");
      chapitre.setIdRubrique(rubrique.getId());
      em.persist(chapitre);
      // page.setIdPage(1L);
      page.setLibelle("Test");
      page.setIdChapitre(chapitre.getId());
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
      PageDataBean test;
      test = (PageDataBean) em.createQuery("From PageDataBean p where p.libelle='Test'").getSingleResult();

      Assert.assertTrue(test.getLibelle().equals("Test"));
   }

   @Test
   public void testDaoInjection() {
      Assert.assertTrue(null != this.em);
   }

}
