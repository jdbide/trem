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
import com.avancial.app.service.JeuDonneeService;
import com.avancial.app.service.RefTablesMotriceRegimeService;
import com.avancial.app.service.traiteMotriceRegime.ITraiteMotriceRegime;
import com.avancial.app.traitement.TraitementMotrice;
import com.avancial.socle.data.model.databean.IhmPageDataBean;
import com.avancial.socle.logging.ALogBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

@RunWith(Arquillian.class)
public class TraitementMotriceTest {
    @Deployment
    public static WebArchive createDeployment() {
        File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity()
                .as(File.class);

        WebArchive jar = ShrinkWrap.create(WebArchive.class)
                .addPackage(MotriceRegimeEntity.class.getPackage())
                .addPackage(JeuDonneeEntity.class.getPackage())
                .addPackage(TraitementMotrice.class.getPackage())
                .addPackage(ITraiteMotriceRegime.class.getPackage())
                .addPackage(IhmPageDataBean.class.getPackage())
                .addPackage(Socle_PUSocle.class.getPackage())
                .addPackage(EntityManagerProducerSocle.class.getPackage())
                .addPackage(ALogBean.class.getPackage())
                .addClass(JeuDonneeService.class)
                .addClass(RefTablesMotriceRegimeService.class)
                .addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
                .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml").setWebXML("WEB-INF/web.xml")
                .addAsManifestResource("META-INF/context.xml", "context.xml");

        System.out.println(jar.toString(true));

        return jar;
    }

    @Inject
    @Socle_PUSocle
    EntityManager entityManagerSocle;
    
    @Inject
    TraitementMotrice traitementMotrice;

    @Test
    public void main() {
        try {
            this.entityManagerSocle.clear();
            
            JeuDonneeEntity jeuDonneeEntity = new JeuDonneeEntity();
            jeuDonneeEntity.setIdJeuDonnees(2);
            this.traitementMotrice.setJeuDonneeEntity(jeuDonneeEntity);

            try {
                this.traitementMotrice.execute();
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
