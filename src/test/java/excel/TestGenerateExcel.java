/**
 * 
 */
package excel;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avancial.app.export.ExcelRapportDifferentiel;
import com.avancial.app.utilitaire.FileUtils;
import com.avancial.socle.persistence.EntityManagerProducerSocle;

/**
 * @author hamza.laterem
 *
 */
@RunWith(Arquillian.class)
public class TestGenerateExcel {
   @Deployment
   public static WebArchive createDeployment() {
      File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity().as(File.class);

      WebArchive jar = ShrinkWrap.create(WebArchive.class)
            .addPackage(ExcelRapportDifferentiel.class.getPackage())
            .addClass(FileUtils.class)
            // .addAsManifestResource("arquillian.xml")
            .addPackage(EntityManagerProducerSocle.class.getPackage()).addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib).addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
            // .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

      System.out.println(jar.toString(true));

      return jar;
   }
   
   @Inject
   ExcelRapportDifferentiel excelRapportDifferentiel;
   /**
    * @return 
    * 
    */
   @Test
   public void TestInject() {
      Assert.assertNotNull(this.excelRapportDifferentiel);
   }
   
   @Test
   public void TestGenerateExcelDonneesParDefautEtInject() {
      try {
         this.excelRapportDifferentiel.generate();
         if (!FileUtils.existFile("D:/was_tmp/tremas/export.xls")) {
            Assert.assertTrue(false);
         } else
            Assert.assertTrue(true);
         
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         System.err.println(e.getMessage());
         Assert.assertTrue(false);
      }
      Assert.assertNotNull(this.excelRapportDifferentiel);
   }
   
   @Test
   public void TestGenerateExcelLocalAvecParamsAndInject() {
      try {
         // Ajout des params
         this.excelRapportDifferentiel.setFileName("Test_TestGenerateExcelLocalAvecParamsAndInject");
         this.excelRapportDifferentiel.setFilePath("D:/was_tmp/tremas/export/");
         this.excelRapportDifferentiel.setXlsx(true);
         
         this.excelRapportDifferentiel.generate();
         if (!FileUtils.existFile("D:/was_tmp/tremas/export/Test_TestGenerateExcelLocalAvecParamsAndInject.xlsx")) {
            Assert.assertTrue(false);
         } else
            Assert.assertTrue(true);
         
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         System.err.println(e.getMessage());
         Assert.assertTrue(false);
      }
      Assert.assertNotNull(this.excelRapportDifferentiel);
   }

}
