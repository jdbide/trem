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

import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.service.JeuDonneeService;
import com.avancial.app.service.traiteObjetMetier.TraiteObjetMetierRegimeFactory;
import com.avancial.app.traitement.TraitementObjetMetier;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.data.model.databean.IhmPageDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

@RunWith(Arquillian.class)
public class RemplissageObjMetierTest {
    @Deployment
    public static WebArchive createDeployment() {
        File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity()
                .as(File.class);

        WebArchive jar = ShrinkWrap.create(WebArchive.class)
                 .addPackage(CodeSat.class.getPackage())
                 .addPackage(IhmPageDataBean.class.getPackage())
                 .addClass(JeuDonneeService.class)
                 .addClass(TraiteObjetMetierRegimeFactory.class)
                 .addClass(MapPlansDeTransport.class)
                 .addClass(TraitementObjetMetier.class)
                 .addPackage(Socle_PUSocle.class.getPackage())
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

    @Test
    public void testRemplissageObjetMetier() throws Exception {
       try {
          this.em.clear();
          try {
              this.traitementObjetMetier.execute();
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
