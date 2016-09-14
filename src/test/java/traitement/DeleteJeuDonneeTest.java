package traitement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avancial.app.data.databean.Status;
import com.avancial.app.service.traiteDeleteRegime.TraiteDeleteDonneesRegimeFactory;
import com.avancial.app.traitement.TraitementDeleteJeuDonnee;
import com.avancial.socle.data.model.databean.LogTraitementDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

@RunWith(Arquillian.class)
public class DeleteJeuDonneeTest {
    @Deployment
    public static WebArchive createDeployment() {
        File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity()
                .as(File.class);

        WebArchive jar = ShrinkWrap.create(WebArchive.class).addClass(TraitementDeleteJeuDonnee.class).addClass(LogTraitementDataBean.class).addClass(TraiteDeleteDonneesRegimeFactory.class)
              .addPackage(Socle_PUSocle.class.getPackage()).addPackage(EntityManagerProducerSocle.class.getPackage())
              .addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
              .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml").setWebXML("WEB-INF/web.xml")
              .addAsManifestResource("META-INF/context.xml", "context.xml");

        System.out.println(jar.toString(true));

        return jar;
    }


    @Inject
    TraitementDeleteJeuDonnee test;

    @Test
   public void testDeleteDraft() throws Exception {
      this.test.setCompagnieEnvironnement("ES_PROD");
      List<Status> status = new ArrayList<>();
      status.add(Status.DRAFT);
      this.test.setStatus(status);
      this.test.execute();

    }
}
