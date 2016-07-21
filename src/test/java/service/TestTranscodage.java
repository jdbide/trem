/**
 * 
 */
package service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.avancial.app.service.JeuDonneeService;
import com.avancial.socle.data.model.databean.IhmPageDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import utils.transcodage.UtilsTranscodageRegime;

/**
 * @author bruno.legloahec
 *
 */
@RunWith(Arquillian.class)
public class TestTranscodage {
    @Deployment
    public static WebArchive createDeployment() {
        File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity()
                .as(File.class);

        WebArchive jar = ShrinkWrap.create(WebArchive.class).addPackage(IhmPageDataBean.class.getPackage())
                // .addPackage(IhmPageDao.class.getPackage())

                .addClass(JeuDonneeService.class).addPackage(Socle_PUSocle.class.getPackage())
                .addPackage(EntityManagerProducerSocle.class.getPackage())
                // .addAsManifestResource("arquillian.xml")
                .addPackage(EntityManagerProducerSocle.class.getPackage())
                .addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
                .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
                // .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

        System.out.println(jar.toString(true));

        return jar;
    }

    private static final SimpleDateFormat FORMAT_DATE_DDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
    private Date dateDebutService;

    @Test
    public void testTranscodage() {
        try {
            this.dateDebutService = FORMAT_DATE_DDMMYYYY.parse("07/12/2015");
            String regime = "101101101000010010000000011000100111001001100110011010000000000000000000000000000000000";
            UtilsTranscodageRegime transcodageRegime = new UtilsTranscodageRegime(regime, dateDebutService);

            System.out.println(transcodageRegime.executeTranscodage());
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
