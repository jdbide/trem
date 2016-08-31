/**
 * 
 */
package com.avancial.sequenceur;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avancial.socle.ihm.menu.model.databean.PageDataBean;

/**
 * @author bruno
 *
 */
@RunWith(Arquillian.class)
public class TestSequenceur {

   @Deployment
   public static WebArchive createDeployment() {
      File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity().as(File.class);

      WebArchive jar = ShrinkWrap.create(WebArchive.class).addPackage(PageDataBean.class.getPackage())
            // .addPackage(IhmPageDao.class.getPackage())

            // .addClass(PhraseBuilder.class)
            // .addAsManifestResource("arquillian.xml")
            .addPackage(SequenceurSaturn.class.getPackage()).addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
            // .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

      System.out.println(jar.toString(true));

      return jar;
   }

   @Inject
   @Named
   ISequenceur        sequenceur;

   @Inject
   ASequenceurContext context;

   /**
    * Test du séquenceur
    */
   @Test
   public void sequenceurInjection() {
      Assert.assertNotNull(sequenceur);
   }

   @Test
   public void testSequenceur() {
      IEtape etape = new Etape1("Etape 1");
      this.sequenceur.AjouteEtape(etape);

      etape = new Etape2("Etape 2");
      etape.setLancerEtapeSuivante(true);
      this.sequenceur.AjouteEtape(etape);

      etape = new Etape3("Etape 3");
      etape.setLancerEtapeSuivante(false);
      this.sequenceur.AjouteEtape(etape);

      sequenceur.avance(context);
      Assert.assertTrue(this.sequenceur.getNomEtapeEnCours().equals("Etape 1"));

      sequenceur.avance(context);
      Assert.assertTrue(this.sequenceur.getNomEtapeEnCours().equals("Etape 3"));

      Assert.assertTrue(this.sequenceur.avance(context) == false);

   }
}
