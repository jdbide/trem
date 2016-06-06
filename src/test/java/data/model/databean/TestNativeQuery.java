/**
 * 
 */
package data.model.databean;

import java.io.File;
import java.sql.PreparedStatement;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avancial.socle.data.model.databean.IhmPageDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author bruno.legloahec
 *
 */
@RunWith(Arquillian.class)
public class TestNativeQuery {

   @Deployment
   public static WebArchive createDeployment() {
      File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity().as(File.class);

      WebArchive jar = ShrinkWrap.create(WebArchive.class).addPackage(IhmPageDataBean.class.getPackage())
            // .addPackage(IhmPageDao.class.getPackage())

            // .addClass(PhraseBuilder.class)
            // .addAsManifestResource("arquillian.xml")
            .addPackage(Socle_PUSocle.class.getPackage()).addClass(EntityManagerProducerSocle.class).addPackage(EntityManagerProducerExterne.class.getPackage()).addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
            .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml").addAsWebInfResource("hibernate.cfg.xml", "classes/hibernate.cfg.xml")
            // .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

      System.out.println(jar.toString(true));

      return jar;
   }

   /*
    * @Hamza : Tutoriel : http://rmannibucau.developpez.com/tutoriels/cdi/introduction-cdi/
    */
   @Inject
   @Socle_PUExterne
   EntityManager em;

   @Inject
   @Socle_PUSocle
   EntityManager emSocle;

   private int   count;

   @Test
   public void nativeQuery() {

      Query query = this.em.createNativeQuery("select * from f$mdrp1.TMDGADS");
      // where GADS_DSTR_COD_CIE='BC'

      List<Object[]> liste = query.getResultList();

      System.out.println("***" + liste.size());

      StringBuilder sqlQuery = new StringBuilder();
      StringBuilder sqlValues = new StringBuilder();
      sqlQuery.append("insert into tremas_import_tmdgads1 values ");

      try {

         // Class.forName("com.mysql.jdbc.Driver");
         //
         // System.out.println("Driver O.K.");
         //
         // String url = "jdbc:mysql://caliban/tremas";
         //
         // String user = "dbad_tremas";
         //
         // String passwd = "!tremas-12";
         //
         // Connection conn = DriverManager.getConnection(url, user, passwd);

         System.out.println("Connexion effective !");
         final int limit = 1000;
         PreparedStatement ps = null;
         this.emSocle.getTransaction().begin();
         for (Object[] objects : liste) {
            if (sqlValues.length() == 0)
               sqlValues.append("(null,");
            else
               sqlValues.append(",(null,");

            for (int i = 0; i < objects.length; i++) {
               sqlValues.append("'").append(objects[i]).append("'");
               if (i != objects.length - 1)
                  sqlValues.append(",");
            }
            sqlValues.append(")");

            if (++this.count % limit == 0) {

               query = this.emSocle.createNativeQuery(sqlQuery.toString() + sqlValues.toString());
               query.executeUpdate();

               // ps.executeBatch();
               // ps.clearBatch();
               // System.out.println("Flush");
               // ps = conn.prepareStatement(sqlQuery.toString() + sqlValues.toString());
               // ps.executeUpdate();
               // System.out.println(sqlQuery.toString() + sqlValues.toString());
               sqlValues.setLength(0);

            }

         }
         if (sqlValues.length() > 0) {

            query = this.emSocle.createNativeQuery(sqlQuery.toString() + sqlValues.toString());
            query.executeUpdate();

         }
         this.emSocle.flush();
         this.emSocle.getTransaction().commit();

      } catch (Exception e) {

         e.printStackTrace();

      } finally {
         System.out.println("count : " + this.count);
      }

   }
}
