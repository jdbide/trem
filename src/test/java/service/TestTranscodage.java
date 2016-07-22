/**
 * 
 */
package service;

import java.io.File;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.avancial.app.data.databean.importMotriceBrut.ImportTMDKAPPEntity;
import com.avancial.app.service.JeuDonneeService;
import com.avancial.socle.data.model.databean.IhmPageDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.utils.Convertisseur;
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
    private static final SimpleDateFormat FORMAT_DATE_YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    private Date dateDebutService;
    @Inject
    @Socle_PUSocle
    EntityManager em;

    @Test
    public void testTranscodage() {

        try {
            this.dateDebutService = FORMAT_DATE_DDMMYYYY.parse("07/12/2015");
            String valeur = Convertisseur.hexToBin("5B42403733336333323134");
            TypedQuery<ImportTMDKAPPEntity> query = this.em.createNamedQuery("ImportTMDKAPP.getKht",
                    ImportTMDKAPPEntity.class);
            ImportTMDKAPPEntity tmdkappDataBean = query.getSingleResult();
            Date datedebutService2 = FORMAT_DATE_YYYYMMDD.parse(tmdkappDataBean.getKAPP_ORI());
            UtilsTranscodageRegime transcodageRegime = new UtilsTranscodageRegime(valeur, this.dateDebutService);
            UtilsTranscodageRegime transcodageRegime1 = new UtilsTranscodageRegime(
                    "101101101000010010000000011011100110011001100110110001100110011001100100011000100110100",
                    this.dateDebutService);
            System.out.println(transcodageRegime.executeTranscodage());
            System.out.println(transcodageRegime1.executeTranscodage());
            // this.dateDebutService = FORMAT_DATE_DDMMYYYY.parse("07/12/2015");
            // String valeur = new BigInteger("5B42403733336333323134",
            // 16).toString(2);
            // TypedQuery<ImportTMDKAPPEntity> query =
            // this.em.createNamedQuery("ImportTMDKAPP.getKht",
            // ImportTMDKAPPEntity.class);
            // ImportTMDKAPPEntity tmdkappDataBean = query.getSingleResult();
            // Date dateDebutService2 =
            // FORMAT_DATE_YYYYMMDD.parse(tmdkappDataBean.getKAPP_ORI());
            // UtilsTranscodageRegime transcodageRegime = new
            // UtilsTranscodageRegime(valeur, dateDebutService2);
            // UtilsTranscodageRegime transcodageRegime1 = new
            // UtilsTranscodageRegime(
            // "101101101000010010000000011011100110011001100110110001100110011001100100011000100110100",
            // this.dateDebutService);
            // Assert.assertSame(transcodageRegime.executeTranscodage(),
            // transcodageRegime1.executeTranscodage());

        }
        catch (NumberFormatException | ParseException ex) {
            ex.printStackTrace();
        }

    }

}
