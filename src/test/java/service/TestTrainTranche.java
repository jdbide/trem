/**
 * 
 */
package service;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Session;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefRegimeTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeODEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeSatcodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeStopEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.service.JeuDonneeService;
import com.avancial.app.service.traiteMotriceRegime.ITraiteMotriceRegime;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeFactory;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;
import com.avancial.socle.data.model.databean.IhmPageDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author bruno.legloahec
 *
 */
@RunWith(Arquillian.class)
public class TestTrainTranche {
	@Deployment
	public static WebArchive createDeployment() {
		File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity()
				.as(File.class);

		WebArchive jar = ShrinkWrap.create(WebArchive.class).addPackage(IhmPageDataBean.class.getPackage())
				.addClass(JeuDonneeService.class).addPackage(Socle_PUSocle.class.getPackage())
				.addPackage(EntityManagerProducerSocle.class.getPackage())
				.addPackage(EntityManagerProducerSocle.class.getPackage())
				.addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
				.addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml").setWebXML("WEB-INF/web.xml")
				.addAsManifestResource("META-INF/context.xml", "context.xml");

		System.out.println(jar.toString(true));

		return jar;
	}

	@Inject
	@Socle_PUSocle
	EntityManager em;

	@Test
	public void testTranscodage() throws Exception {
		this.em.clear();

		JeuDonneeEntity jeuDonneeEntity = new JeuDonneeEntity();
		jeuDonneeEntity.setIdJeuDonnees(2);

		Query query = this.em.createNativeQuery(
                "SELECT tranche.TRCH_TRA1_NUM_TRA1 AS trainNumberMotriceTrainTranche, categorie.CATH_SSIM AS trancheNumberMotriceTrainTranche, IF ( train.TRA1_NUM_TRAIN IS NULL, 0, 1 ) AS validForRRMotriceTrainTranche, categorie.CATH_ETAT_TRCH AS trancheStatusMotriceTrainTranche, train.TRA1_REGI_VAL_TRTH AS regime FROM tremas_import_tmdtrch AS tranche LEFT JOIN tremas_import_tmdtra1 AS train ON tranche.TRCH_TRA1_COD_CIE = train.TRA1_CIES_COD_CIE AND tranche.TRCH_TRA1_NUM_TRA1 = train.TRA1_NUM_TRAIN AND tranche.TRCH_TRA1_IND_FER = train.TRA1_IND_FER_ROUTE INNER JOIN tremas_import_tmdcath AS categorie ON tranche.TRCH_TRA1_COD_CIE = categorie.CATH_CIRR_COD_CIE AND tranche.TRCH_TRA1_NUM_TRA1 = categorie.CATH_TRCH_NUM_TRA1 AND tranche.TRCH_TRA1_IND_FER = categorie.CATH_TRCH_IND_FER AND tranche.TRCH_NUM = categorie.CATH_NUM");

		List<Object[]> trainsTranches = query.getResultList();
		long cpt = 1034;
		MotriceTrainTrancheEntity motriceTrainTrancheEntity;
		MapIdTablesMotriceRegime mapIdTablesMotriceRegime = new MapIdTablesMotriceRegime();
		MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime = new MapGeneratorTablesMotriceRegime(
				this.em.unwrap(Session.class), 250);
		
		TraiteMotriceRegimeFactory traiteMotriceRegimeFactory = new TraiteMotriceRegimeFactory();
		ITraiteMotriceRegime traiteMotriceRegime = traiteMotriceRegimeFactory
				.getTraiteMotriceRegime(MotriceRegimeStopEntity.class);		
		
		MotriceRegimeEntity motriceRegimeEntity;

		MotriceRefRegimeTypeEntity motriceRefRegimeTypeEntity = new MotriceRefRegimeTypeEntity();
		motriceRefRegimeTypeEntity.setIdMotriceRefRegimeType((long) 1);
		motriceRefRegimeTypeEntity.setLabelRegimeType("Regime train tranche");
		
//		for (Object[] record : trainsTranches) {
			motriceTrainTrancheEntity = new MotriceTrainTrancheEntity();
			motriceTrainTrancheEntity.setIdMotriceTrainTranche(++cpt);
			motriceTrainTrancheEntity.setJeuDonnee(jeuDonneeEntity);
			motriceTrainTrancheEntity.setTrainNumberMotriceTrainTranche("009012");
			motriceTrainTrancheEntity.setTrancheNumberMotriceTrainTranche("009012");
			motriceTrainTrancheEntity.setValidForRRMotriceTrainTranche(true);
			motriceTrainTrancheEntity.setTrancheStatusMotriceTrainTranche("O");

//			this.em.getTransaction().begin();
//			this.em.persist(motriceTrainTrancheEntity);
//			this.em.getTransaction().commit();
			
			motriceRegimeEntity = new MotriceRegimeEntity();
			motriceRegimeEntity.setMotriceRefRegimeType(motriceRefRegimeTypeEntity);
			motriceRegimeEntity.setPeriodMotriceRegime("(LU+ME+JE+DI*07125/29026+12+15125,01+02+09+12016,02+06+12+26026)MOINS(LU+JE+DI*21125/29125,ME+DI*27016/07026,14+16+17+30125,06+07+11016,04+08+14+22+24+28026)");
			motriceRegimeEntity.setMotriceTrainTranche(motriceTrainTrancheEntity);
			
//			this.em.getTransaction().begin();
//			this.em.persist(motriceRegimeEntity);
//			this.em.getTransaction().commit();

			traiteMotriceRegime.traite(motriceTrainTrancheEntity, mapIdTablesMotriceRegime, mapGeneratorTablesMotriceRegime,
					this.em);
//		}
		
		mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class).executeRequest();
		mapGeneratorTablesMotriceRegime.get(MotriceRegimeSatcodeEntity.class).executeRequest();

		
	}

}
