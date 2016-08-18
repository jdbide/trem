package data.model.databean;

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

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.persistence.EntityManagerFactoryProviderDb2;
import com.avancial.app.service.CompagnieEnvironnementService;
import com.avancial.app.traitement.TraitementImportDb2Motrice;
import com.avancial.socle.data.model.databean.IhmPageDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

@RunWith(Arquillian.class)
public class TestImport {    
    @Deployment
    public static WebArchive createDeployment() {
        File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity()
                .as(File.class);

        WebArchive jar = ShrinkWrap.create(WebArchive.class)
                 .addPackage(IhmPageDataBean.class.getPackage())
                 .addPackage(CompagnieEnvironnementEntity.class.getPackage())
                //.addClass(JeuDonneeService.class)
                 .addClass(TraitementImportDb2Motrice.class)
                .addClass(CompagnieEnvironnementService.class)
                .addPackage(Socle_PUSocle.class.getPackage())
                .addPackage(EntityManagerProducerSocle.class.getPackage())
                .addPackage(EntityManagerProducerSocle.class.getPackage())
                .addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
                .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
                .setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

        System.out.println(jar.toString(true));

        return jar;
    }

    EntityManager entityManagerDb2;
    
    @Inject
    private CompagnieEnvironnementService compagnieEnvironnementService;
    
    @Inject
    private TraitementImportDb2Motrice traitement;

    
    
    @Test
    public void testImportTMDVOIT() {
        try {
            String userDb2 = "ejmt013";

            String passwdDb2 = "Isab1000";
            
            CompagnieEnvironnementEntity compagnieEnvironnementEntity = this.compagnieEnvironnementService.getCompagnieEnvironnementById(1);
                        
            try {
               // Instanciation EntityManagerFactory avec les bonnes données de la dataSource de l'environnement               
               System.err.println("------> Connexion avec la BDD externe");
               this.entityManagerDb2 = EntityManagerFactoryProviderDb2.getInstance(compagnieEnvironnementEntity, userDb2, passwdDb2).createEntityManager();
            } catch (Throwable ex) {
               System.err.println("------> Echec de connexion avec la base de données externe Db2");
               throw ex;
            }
            
            this.traitement.setEntityManagerExterne(this.entityManagerDb2);
            this.traitement.setSchema(compagnieEnvironnementEntity.getDatasource().getSchema());
            try {
               // Thread.sleep(10000);
               System.out.println("------> Import brut");
               traitement.execute();
            } catch (SecurityException e) {
               System.err.println("------> Echec de l'import");
               e.printStackTrace();
               throw e;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
