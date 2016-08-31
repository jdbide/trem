/**
 * 
 */
package params;

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

import com.avancial.app.params.ParamGetter;
import com.avancial.socle.params.AParamGetter;
import com.avancial.socle.params.beans.IParamBean;
import com.avancial.socle.params.exception.ParamCollectionNotLoadedException;
import com.avancial.socle.params.exception.ParamNotFoundException;
import com.avancial.socle.resources.constants.SOCLE_params;

/**
 * @author bruno.legloahec
 *
 */
@RunWith(Arquillian.class)
public class testRecupParametres {
   @Deployment
   public static WebArchive createDeployment() {
      File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity().as(File.class);

      WebArchive jar = ShrinkWrap.create(WebArchive.class).addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
            // .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
            // .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .addClass(ParamGetter.class).setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml").addPackage(AParamGetter.class.getName());

      System.out.println(jar.toString(true));

      return jar;
   }

   @Inject
   ParamGetter paramGetter;

   @Test
   public void testRecupParams() throws ParamNotFoundException, ParamCollectionNotLoadedException {
      Assert.assertNotNull(paramGetter);

      IParamBean param = paramGetter.getParam(SOCLE_params.PARAMS_APP.getValue(), SOCLE_params.PARAMS_APP_NOM_PROJET.getValue());
      Assert.assertTrue("Param app nom projet", param.getValue().equals("socle"));

      param = paramGetter.getParam(SOCLE_params.PARAMS_APP.getValue(), SOCLE_params.PARAMS_APP_VERSION.getValue());
      Assert.assertTrue("Param app version", param.getValue().equals("APP20151019"));

      param = paramGetter.getParam(SOCLE_params.PARAMS_APP.getValue(), SOCLE_params.PARAMS_APP_DATE_LIVRAISON.getValue());
      Assert.assertTrue("Param app date livraison", param.getValue().equals("01.01.2016"));

      param = paramGetter.getParam(SOCLE_params.PARAMS_SOCLE.getValue(), SOCLE_params.PARAMS_SOCLE_VERSION.getValue());
      Assert.assertTrue("Param socle version", param.getValue().equals("1.7"));

      param = paramGetter.getParam(SOCLE_params.PARAMS_SOCLE.getValue(), SOCLE_params.PARAMS_SOCLE_DATE_LIVRAISON.getValue());
      Assert.assertTrue("Param socle date livraison", param.getValue().equals("19.02.16"));

   }

}
