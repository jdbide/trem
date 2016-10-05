/**
 * 
 */
package data.model.databean;

import java.io.File;
import java.util.Date;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneesControlEntity;
import com.avancial.app.data.databean.EStatus;
import com.avancial.app.data.databean.EStatusControl;
import com.avancial.app.service.CompagnieEnvironnementService;
import com.avancial.app.service.JeuDonneesControlService;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.service.AService;

/**
 * @author hamza.laterem
 *
 */
@RunWith(Arquillian.class)
public class TestJeuDonneesControl {
   @Deployment
   public static WebArchive createDeployment() {
       File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity()
               .as(File.class);

       WebArchive jar = ShrinkWrap.create(WebArchive.class)
                .addPackage(CompagnieEnvironnementEntity.class.getPackage())
               .addClass(JeuDonneesControlService.class)
               .addClass(CompagnieEnvironnementService.class)
               .addClass(EStatus.class)
               .addClass(EStatusControl.class)
               .addPackage(AService.class.getPackage())
               
//                .addClass(TraitementImportDb2Motrice.class)
               .addPackage(Socle_PUSocle.class.getPackage())
               .addPackage(EntityManagerProducerSocle.class.getPackage())
               .addPackage(EntityManagerProducerSocle.class.getPackage())
               .addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
               .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
               .setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

       System.out.println(jar.toString(true));

       return jar;
   }
   
   @Inject
   private CompagnieEnvironnementService compagnieEnvironnementService;
   
   @Inject
   private JeuDonneesControlService jeuDonneesControlService;
   /**
    * 
    */
   public TestJeuDonneesControl() {
      // TODO Auto-generated constructor stub
   }
   
   @Test
   public void addJeuDonneesControl () {
      JeuDonneesControlEntity jdce = new JeuDonneesControlEntity();
      
      jdce.setTitleJeuDonneesControl("Test");
      jdce.setDateCreateJeuDonneesControl(new Date());
      jdce.setDateLastUpdateJeuDonneesControl(new Date());
      jdce.setIdJeuDonnees(1000);
      jdce.setIdUtilisateurCreateJeuDonneesControl(1);
      jdce.setIdUtilisateurLastUpdateJeuDonneesControl(2);
      jdce.setPathFileImportJeuDonneesControlTimeTable("");
      jdce.setPathFileImportJeuDonneesControlYield("");
      jdce.setPathFileReportJeuDonneesControl("");
      jdce.setStatusJeuDonnees(EStatus.ACTIVE.toString());
      jdce.setStatusJeuDonneesControl(EStatusControl.LOADING);
      jdce.setCompagnieEnvironnement(compagnieEnvironnementService.getCompagnieEnvironnementById(1));
      
      try {
         this.jeuDonneesControlService.save(jdce);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}
