package traitement;

import java.io.File;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.service.RefTablesMotriceRegimeService;
import com.avancial.app.service.traiteMotriceRegime.ITraiteMotriceRegime;
import com.avancial.app.traitement.TraitementMiseAJourDonneesRef;
import com.avancial.app.traitement.TraitementMotrice;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.data.model.databean.LogTraitementDataBean;
import com.avancial.socle.ihm.menu.model.databean.PageDataBean;
import com.avancial.socle.logging.ALogBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.traitement.ATraitement;

@RunWith(Arquillian.class)
public class TestTraitementMiseAJourDonneesRef {
   @Deployment
   public static WebArchive createDeployment() {
      File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity().as(File.class);

      WebArchive jar = ShrinkWrap.create(WebArchive.class)
            .addPackage(LogTraitementDataBean.class.getPackage())
            .addClass(TraitementMiseAJourDonneesRef.class)
            .addPackage(MotriceRegimeEntity.class.getPackage()).addPackage(JeuDonneeEntity.class.getPackage())
            .addPackage(ITraiteMotriceRegime.class.getPackage()).addPackage(PageDataBean.class.getPackage())
            .addClass(RefTablesMotriceRegimeService.class).addPackage(ALogBean.class.getPackage()).addPackage(ATraitement.class.getPackage())
            .addClass(TraitementMotrice.class).addClass(MapPlansDeTransport.class).addPackage(EntityManagerProducerSocle.class.getPackage())
            .addPackage(Socle_PUSocle.class.getPackage()).addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
            .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml").setWebXML("WEB-INF/web.xml")
            .addAsManifestResource("META-INF/context.xml", "context.xml");

      System.out.println(jar.toString(true));

      return jar;
   }

   @Inject
   private TraitementMiseAJourDonneesRef traitementMajDonneesRef;

   @Inject
   @Socle_PUSocle
   EntityManager                         em;

   @Test
   public void main() {

      try {
         JeuDonneeEntity jeuDonneeEntity = this.em.find(JeuDonneeEntity.class, 11);

         this.traitementMajDonneesRef.setJeuDonneesEntity(jeuDonneeEntity);
         this.traitementMajDonneesRef.execute();

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}
