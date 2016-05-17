/**
 * 
 */
package security;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avancial.socle.data.model.databean.RoleDataBean;
import com.avancial.socle.data.model.databean.User2RoleDataBean;
import com.avancial.socle.data.model.databean.UserDataBean;
import com.avancial.socle.model.managedbean.IhmManagedBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.resources.annotations.Role;

/**
 * @author bruno.legloahec
 *
 */
@RunWith(Arquillian.class)
public class TestSecurityManager {
   @Deployment
   public static WebArchive createDeployment() {
      File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity().as(File.class);

      WebArchive jar = ShrinkWrap.create(WebArchive.class).addClass(Role.class)
            // .addPackage(SecurityManagedBean.class.getPackage())
            .addClass(IhmManagedBean.class)
            // .addEntityManagerProducerMemoire.class.getPackage())
            // .addPackage(JobBean.class.getPackage())
            .addClass(Socle_PUSocle.class)
            // .addPackage(Socle_PUExterne.class.getPackage())
            .addClass(EntityManagerProducerSocle.class)
            // .addPackage(RoleDao.class.getPackage())
            // .addAsManifestResource("arquillian.xml")
            .addClass(MonObjetNoInterception.class).addClass(MonObjetInterception.class).addClass(UserDataBean.class).addClass(RoleDataBean.class).addClass(User2RoleDataBean.class)

            .addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib).addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
            // .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

      System.out.println(jar.toString(true));

      return jar;
   }

   // @Inject
   // SecurityManagedBean sm;
   //
   @Inject
   IhmManagedBean         ihm;
   //
   @Inject
   UserDataBean           user;
   //
   @Inject
   RoleDataBean           role;

   @Inject
   User2RoleDataBean      user2role;

   @Inject
   MonObjetNoInterception monObjetNoInterception;

   @Inject
   MonObjetInterception   monObjetInterception;

   @Inject
   @Socle_PUSocle
   EntityManager          em;

   @PostConstruct
   public void init() {

   }

   @Test
   public void testAnnotationSM() {

      // On considère que l'utilisateur n'a pas le rôle requis

      Assert.assertNull(monObjetNoInterception.maMethode());

      Assert.assertTrue(monObjetInterception.maMethode().equals("Interception!"));

   }
}
