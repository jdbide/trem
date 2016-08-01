package data.model.databean;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.avancial.app.persistence.EntityManagerFactoryProviderDb2;
import com.avancial.app.service.JeuDonneeService;
import com.avancial.app.traitement.TraitementImportDb2Motrice;
import com.avancial.app.traitement.TraitementImportJeuDonnees;
import com.avancial.app.utilitaire.MapTraitementImportBrut;
import com.avancial.app.utilitaire.SchemaMotrice;
import com.avancial.socle.data.model.databean.IhmPageDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

@RunWith(Arquillian.class)
public class TestImport {    
    @Deployment
    public static WebArchive createDeployment() {
        File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity()
                .as(File.class);

        WebArchive jar = ShrinkWrap.create(WebArchive.class).addPackage(IhmPageDataBean.class.getPackage())
                .addClass(JeuDonneeService.class).addPackage(Socle_PUSocle.class.getPackage())
                .addPackage(EntityManagerProducerSocle.class.getPackage())
                .addPackage(EntityManagerProducerSocle.class.getPackage())
                .addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
                .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
                .setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

        System.out.println(jar.toString(true));

        return jar;
    }
    
    @Inject
    @Socle_PUSocle
    EntityManager entityManagerSocle;

    EntityManager entityManagerDb2;
    @Test
    public void testImportTMDVOIT() {
        try {
            this.entityManagerSocle.clear();

            String userDb2 = "ejmt013";

            String passwdDb2 = "Isab1000";


            try {
                this.entityManagerDb2 = EntityManagerFactoryProviderDb2
                        .getInstance(userDb2, passwdDb2)
                        .createEntityManager();
            }
            catch (Throwable ex) {
                throw ex;
            }
            
            TraitementImportDb2Motrice traitementImportDb2Motrice = new TraitementImportDb2Motrice(this.entityManagerSocle, this.entityManagerDb2, "F$MDRP2");
            traitementImportDb2Motrice.execute();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
