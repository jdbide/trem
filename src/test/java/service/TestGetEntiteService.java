package service;

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

import com.avancial.app.data.databean.importMotrice.MotriceRegimeCompositionCoachEntity;
import com.avancial.app.data.databean.importMotriceBrut.ImportTMDAVTREntity;
import com.avancial.app.utilitaire.GetEntiteService;
import com.avancial.socle.ihm.menu.model.databean.PageDataBean;

@RunWith(Arquillian.class)
public class TestGetEntiteService {
    @Deployment
    public static WebArchive createDeployment() {
        File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity()
                .as(File.class);

        WebArchive jar = ShrinkWrap.create(WebArchive.class).addPackage(PageDataBean.class.getPackage())
                // .addPackage(IhmPageDao.class.getPackage())

                .addClass(GetEntiteService.class)
                // .addAsManifestResource("arquillian.xml")
                .addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
                .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
                // .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

        System.out.println(jar.toString(true));

        return jar;
    }

    @Inject
    GetEntiteService entiteService;

    @Test
    public void testService() throws ClassNotFoundException {
        Assert.assertEquals(GetEntiteService.getNomEntiteFromTableMotrice("tmdavtr"), "TMDAVTREntity");
        Assert.assertEquals(GetEntiteService.getNomEntiteImportFromTableMotrice("tmdavtr"), "ImportTMDAVTREntity");

//        Assert.assertEquals(entiteService.getClasseEntiteFromTableMotrice("tmdavtr"), TMDAVTREntity.class);
        Assert.assertEquals(GetEntiteService.getClasseEntiteImportFromTableMotrice("tmdavtr"), ImportTMDAVTREntity.class);
        
        Assert.assertEquals(GetEntiteService.getNomEntiteFromNomEntiteImportMotrice("ImportTMDAVTREntity"), "TMDAVTREntity");
        Assert.assertEquals(GetEntiteService.getNomEntiteImportFromNomEntiteMotrice("TMDAVTREntity"), "ImportTMDAVTREntity");
        
//        Assert.assertEquals(entiteService.getClasseEntiteFromNomEntiteMotrice("TMDAVTREntity"), TMDAVTREntity.class);
        Assert.assertEquals(GetEntiteService.getClasseEntiteImportFromNomEntiteImportMotrice("ImportTMDAVTREntity"), ImportTMDAVTREntity.class);
        
//        Assert.assertEquals(entiteService.getClasseEntiteFromClasseEntiteImportMotrice(ImportTMDAVTREntity.class), TMDAVTREntity.class);
//        Assert.assertEquals(entiteService.getClasseEntiteImportFromClasseEntiteMotrice(TMDAVTREntity.class), ImportTMDAVTREntity.class);
        
        Assert.assertEquals(GetEntiteService.getNomTableImportFromTableMotrice("TMDAVTR"), "ImportTMDAVTR");
        
        Assert.assertEquals("MotriceRegimeCompositionCoach", GetEntiteService.getNomFromEntiteTableMotriceRegime(MotriceRegimeCompositionCoachEntity.class.getSimpleName()));
        
        Assert.assertEquals("TMDAVTR", GetEntiteService.getTableFromView("VMDAVTR0"));
        Assert.assertEquals("VMDAVTR0", GetEntiteService.getTableView("TMDAVTR"));
        
        Assert.assertTrue(GetEntiteService.isTableView("VMDAVTR0"));
        Assert.assertFalse(GetEntiteService.isTableView("TMDAVTR"));
    }

}
