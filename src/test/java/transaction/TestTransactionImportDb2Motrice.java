/**
 * 
 */
package transaction;

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

import com.avancial.socle.data.model.databean.IhmPageDataBean;
import com.avancial.socle.persistence.EntityManagerProducerExterne;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

import data.model.databean.Socle_PUExterne;

/**
 * @author bruno.legloahec
 *
 */
@RunWith(Arquillian.class)
public class TestTransactionImportDb2Motrice {

   @Deployment
   public static WebArchive createDeployment() {
      File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity().as(File.class);

      WebArchive jar = ShrinkWrap.create(WebArchive.class).addPackage(IhmPageDataBean.class.getPackage())
            .addPackage(Socle_PUSocle.class.getPackage()).addClass(EntityManagerProducerSocle.class).addPackage(EntityManagerProducerExterne.class.getPackage()).addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
            .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
            .setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

      System.out.println(jar.toString(true));

      return jar;
   }

   @Inject
   @Socle_PUSocle
   EntityManager emSocle;
   
   @Inject
   @Socle_PUExterne
   EntityManager em;
   
   @Test
   public void nativeQuery() {
      TransactionImportDb2Motrice transac = new TransactionImportDb2Motrice(this.emSocle,this.em,"F$MDRP1");
      transac.execute();
   }
}
