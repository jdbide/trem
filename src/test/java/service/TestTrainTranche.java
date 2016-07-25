/**
 * 
 */
package service;

import java.io.File;
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
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.service.JeuDonneeService;
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
				.addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
				.setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

		System.out.println(jar.toString(true));

		return jar;
	}

	@Inject
	@Socle_PUSocle
	EntityManager em;

	@Test
	public void testTranscodage() {
		this.em.clear();

		JeuDonneeEntity jeuDonneeEntity = new JeuDonneeEntity();
		jeuDonneeEntity.setIdJeuDonnees(1);

		Query query = this.em.createNativeQuery(
				"SELECT tranche.TRCH_TRA1_NUM_TRA1 AS trainNumberMotriceTrainTranche, categorie.CATH_SSIM AS trancheNumberMotriceTrainTranche, IF ( train.TRA1_NUM_TRAIN IS NULL, 0, 1 ) AS validForRRMotriceTrainTranche, categorie.CATH_ETAT_TRCH AS trancheStatusMotriceTrainTranche FROM tremas_import_tmdtrch AS tranche LEFT JOIN tremas_import_tmdtra1 AS train ON tranche.TRCH_TRA1_COD_CIE = train.TRA1_CIES_COD_CIE AND tranche.TRCH_TRA1_NUM_TRA1 = train.TRA1_NUM_TRAIN AND tranche.TRCH_TRA1_IND_FER = train.TRA1_IND_FER_ROUTE INNER JOIN tremas_import_tmdcath AS categorie ON tranche.TRCH_TRA1_COD_CIE = categorie.CATH_CIRR_COD_CIE AND tranche.TRCH_TRA1_NUM_TRA1 = categorie.CATH_TRCH_NUM_TRA1 AND tranche.TRCH_TRA1_IND_FER = categorie.CATH_TRCH_IND_FER AND tranche.TRCH_NUM = categorie.CATH_NUM");

		List<Object[]> trainsTranches = query.getResultList();

		MotriceTrainTrancheEntity motriceTrainTrancheEntity;
		for (Object[] record : trainsTranches) {
			motriceTrainTrancheEntity = new MotriceTrainTrancheEntity();
			motriceTrainTrancheEntity.setJeuDonnee(jeuDonneeEntity);
			motriceTrainTrancheEntity.setTrainNumberMotriceTrainTranche((String) record[0]);
			motriceTrainTrancheEntity.setTrancheNumberMotriceTrainTranche((String) record[1]);
			motriceTrainTrancheEntity
					.setValidForRRMotriceTrainTranche(new Boolean("1".equals(record[2].toString()) ? "true" : "false"));
			motriceTrainTrancheEntity.setTrancheStatusMotriceTrainTranche((String) record[3]);

			this.em.getTransaction().begin();
			this.em.persist(motriceTrainTrancheEntity);
			this.em.getTransaction().commit();
		}

//		PropertyMap<Object[], MotriceTrainTrancheEntity> userMap = new PropertyMap<Object[], MotriceTrainTrancheEntity>() {
//			@Override
//			protected void configure() {
//				map().setTrainNumberMotriceTrainTranche((String) this.source[0]);
//				map().setTrancheNumberMotriceTrainTranche((String) this.source[1]);
//				map().setValidForRRMotriceTrainTranche(
//						new Boolean("1".equals(this.source[2].toString()) ? "true" : "false"));
//				map().setTrancheStatusMotriceTrainTranche((String) this.source[3]);
//
//			}
//		};
//
//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.addMappings(userMap);
//
//		for (Object[] record : trainsTranches) {
//			motriceTrainTrancheEntity = modelMapper.map(record, MotriceTrainTrancheEntity.class);
//			motriceTrainTrancheEntity.setJeuDonnee(jeuDonneeEntity);
//
//			this.em.getTransaction().begin();
//			this.em.persist(motriceTrainTrancheEntity);
//			this.em.getTransaction().commit();
//		}

	}

}
