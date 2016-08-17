/**
 * 
 */
package data.model.databean;

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

import com.avancial.app.data.databean.DatasourceEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeDistributionEntity;
import com.avancial.app.persistence.EntityManagerFactoryProviderDb2;
import com.avancial.socle.data.model.databean.IhmChapitreDataBean;
import com.avancial.socle.data.model.databean.IhmPageDataBean;
import com.avancial.socle.data.model.databean.IhmRubriqueDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author bruno.legloahec
 *
 */
@RunWith(Arquillian.class)
public class TestIhmPageDataBean {

	@Deployment
	public static WebArchive createDeployment() {
		File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity()
				.as(File.class);

		WebArchive jar = ShrinkWrap.create(WebArchive.class).addPackage(IhmPageDataBean.class.getPackage())
				// .addPackage(IhmPageDao.class.getPackage())

				// .addClass(PhraseBuilder.class)
				// .addAsManifestResource("arquillian.xml")
				.addPackage(EntityManagerProducerSocle.class.getPackage())
				.addPackage(EntityManagerFactoryProviderDb2.class.getPackage())
				.addAsWebInfResource("WEB-INF/beans.xml", "beans.xml").addAsLibraries(lib)
				.addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
				// .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.setWebXML("WEB-INF/web.xml").addAsManifestResource("META-INF/context.xml", "context.xml");

		System.out.println("==> " + jar.toString(true));

		return jar;
	}

	@Inject
	IhmPageDataBean page;

	@Inject
	IhmChapitreDataBean chapitre;

	@Inject
	IhmRubriqueDataBean rubrique;

	// @Inject
	// IhmPageDao dao;

	/*
	 * @Hamza : Tutoriel :
	 * http://rmannibucau.developpez.com/tutoriels/cdi/introduction-cdi/
	 */
	@Inject
	@Socle_PUSocle
	EntityManager em;

	EntityManager emDb2;

	@Test
	public void testGetId() {
//		DatasourceEntity test = new DatasourceEntity();
//
//		test.setActifDatasource(true);
//		test.setCommentaireDataSource("");
//		test.setDriverClassName("");
//		test.setLibelleDataSource("Test Seb");
//		test.setNomTechniqueDataSource("");
//		test.setUrl("");
//
//		this.em.getTransaction().begin();
//		this.em.persist(test);
//		// this.em.getTransaction().commit();
//		System.out.println("===> " + test.getIdDatasource());
//
//		DatasourceEntity test2 = new DatasourceEntity();
//
//		test2.setActifDatasource(true);
//		test2.setCommentaireDataSource("");
//		test2.setDriverClassName("");
//		test2.setLibelleDataSource("Test Ham");
//		test2.setNomTechniqueDataSource("");
//		test2.setUrl("");
//		try {
//			this.em.persist(test2);
//			this.em.getTransaction().commit();
//			System.out.println("===> " + test2.getIdDatasource());
//		} catch (Exception e) {
//			System.err.println("Erreur ===========");
//		}
		
		Long lastId = this.em.createNamedQuery("getLastId", Long.class).getSingleResult();
		System.out.println("lastId = " + lastId);
	}

	// public void testInjection() {
	// try {
	// this.emDb2 = EntityManagerFactoryProviderDb2.getInstance("dbad_tremas",
	// "!tremas-12").createEntityManager();
	// } catch (Throwable ex) {
	// ex.printStackTrace();
	// }
	// Assert.assertNotNull(this.emDb2);
	// System.out.println("Injection de EntityManagerProviderDb2 !");
	// Assert.assertNotNull(this.em);
	// System.out.println("Injection de l'entity manager !");
	// Assert.assertNotNull(this.page);
	// System.out.println("Injection de l'entity page !");
	// Assert.assertNotNull(this.chapitre);
	// System.out.println("Injection de l'entity chapitre !");
	// Assert.assertNotNull(this.rubrique);
	// System.out.println("Injection de l'entity rubrique !");
	// }

	// public void testConcuranceEntityManager2() {
	// EntityManager nEm = NoteEntityManager.getInstance().getEntityManager();
	// EntityManager myEm2 =
	// Persistence.createEntityManagerFactory("PU_socle").createEntityManager();
	// RoleDataBean role = new RoleDataBean();
	// role.setLabelRole("Test - hamza");
	// role.setTechnicalNameRole("TEST - HAMZA");
	// try {
	// EntityTransaction t1 = nEm.getTransaction();
	// EntityTransaction t2 = myEm2.getTransaction();
	//
	// t1.begin();
	// t2.begin();
	//
	// this.rubrique.setLibelleIhmRubrique("Rubrique - Hamza");
	// nEm.persist(this.rubrique);
	// myEm2.persist(role);
	//
	// t1.commit();
	// t2.commit();
	//
	// // nEm.close();
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// }
	//
	// public void concuranceEntityManager() {
	// EntityManagerFactory emf =
	// Persistence.createEntityManagerFactory("PU_socle");
	// System.out.println(emf.getProperties());
	// EntityManager myEm = emf.createEntityManager();
	// RoleDataBean role = new RoleDataBean();
	// role.setLabelRole("Test - hamza");
	// role.setTechnicalNameRole("TEST - HAMZA");
	// try {
	// EntityTransaction t1 = myEm.getTransaction();
	// EntityTransaction t2 = myEm.getTransaction();
	//
	// t1.begin();
	// t2.begin();
	//
	// this.rubrique.setLibelleIhmRubrique("Rubrique - Hamza");
	// myEm.persist(this.rubrique);
	// myEm.persist(role);
	//
	// t1.commit();
	// t2.commit();
	//
	// myEm.close();
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// }
	//
	// public void initPage(String name, EntityManager myEM) {
	// myEM.getTransaction().begin();
	// this.rubrique.setLibelleIhmRubrique("Rubrique - " + name);
	// myEM.persist(this.rubrique);
	// System.out.println("Ajout d'un rubrique name : \"Rubrique - " + name +
	// "\"");
	//
	// this.chapitre.setLibelleIhmChapitre("Chapitre - " + name);
	// this.chapitre.setIhmRubriqueTypeDataBean(this.rubrique);
	// myEM.persist(this.chapitre);
	// System.out.println("Ajout d'un chapitre name : \"Chapitre - " + name +
	// "\"");
	//
	// this.page.setLibelleIhmPage("Page - " + name);
	// this.page.setIhmChapitreDataBean(this.chapitre);
	// myEM.persist(this.page);
	// System.out.println("Ajout d'un chapitre Page : \"Page - " + name + "\"");
	//
	// myEM.flush();
	// myEM.getTransaction().commit();
	// myEM.close();
	// }
	//
	// public void addPageWithPUSocle() {
	// System.out.println("Utilisation de PU_SOCLE (Serveu: \"caliban\" => bdd :
	// \"tremas_prod\")");
	// initPage("tremas_prod", this.em);
	//
	// Assert.assertNotNull(this.rubrique);
	// Assert.assertNotNull(this.chapitre);
	// Assert.assertNotNull(this.page);
	// }

	// public void addPageWithPUDB2() {
	// try {
	// this.emDb2 = EntityManagerFactoryProviderDb2.getInstance("dbad_tremas",
	// "!tremas-12").createEntityManager();
	// } catch (Throwable ex) {
	// ex.printStackTrace();
	// }
	//
	// System.out.println("Utilisation de PU_SOCLE (Serveu: \"caliban\" => bdd :
	// \"tremas_test\")");
	// initPage("tremas_test", this.emDb2);
	//
	// Assert.assertNotNull(this.rubrique);
	// Assert.assertNotNull(this.chapitre);
	// Assert.assertNotNull(this.page);
	// }
	//
	// public void getPageWithPUSocle() {
	// IhmPageDataBean test;
	//
	// test = (IhmPageDataBean) this.em.createQuery("From IhmPageDataBean p
	// where p.libelleIhmPage='Page - tremas_prod'").getSingleResult();
	//
	// Assert.assertTrue(test.getLibelleIhmPage().equals("Page - tremas_prod"));
	// }
	//
	// public void getPageWithPUDB2() {
	// try {
	// this.emDb2 = EntityManagerFactoryProviderDb2.getInstance("dbad_tremas",
	// "!tremas-12").createEntityManager();
	// } catch (Throwable ex) {
	// ex.printStackTrace();
	// }
	//
	// IhmPageDataBean test;
	//
	// test = (IhmPageDataBean) this.emDb2.createQuery("From IhmPageDataBean p
	// where p.libelleIhmPage='Page - tremas_test'").getSingleResult();
	//
	// Assert.assertTrue(test.getLibelleIhmPage().equals("Page - tremas_test"));
	// }

	/*
	 * 
	 * @Test public void testInjectionConfPersist() { // dynamic configuration
	 * Map<String, String> props = new HashMap<>();
	 * props.put("javax.persistence.jdbc.user", "dbad_tremas");
	 * props.put("javax.persistence.jdbc.password", "!tremas-12");
	 * 
	 * EntityManager mm = null; try { EntityManagerFactory emf =
	 * Persistence.createEntityManagerFactory("pu-db2", props);
	 * 
	 * 
	 * mm = emf.createEntityManager(); } catch (Throwable ex) {
	 * ex.printStackTrace(); //throw new
	 * RuntimeException("Error al crear la factoria de JPA:->"+
	 * ex.getMessage()); }
	 * 
	 * mm.getTransaction().begin(); // rubrique.setIdRubrique(1L);
	 * rubrique.setLibelleIhmRubrique("Rubrique");
	 * 
	 * mm.persist(rubrique);
	 * 
	 * // chapitre.setIdChapitre(1L);
	 * chapitre.setLibelleIhmChapitre("Chapitre");
	 * chapitre.setIhmRubriqueTypeDataBean(rubrique); mm.persist(chapitre);
	 * 
	 * // page.setIdPage(1L); page.setLibelleIhmPage("Test");
	 * page.setIhmChapitreDataBean(chapitre); mm.persist(page); mm.flush();
	 * mm.getTransaction().commit();
	 * 
	 * }
	 * 
	 * EntityManagerProviderDb2 emDb2;
	 * 
	 * @Test public void testInjectionConfPersistWithEntityManagerProviderDb2()
	 * { EntityManager mm = null;
	 * 
	 * try { this.emDb2 = EntityManagerProviderDb2.getInstance("dbad_tremas",
	 * "!tremas-12"); mm = this.emDb2.getEm(); } catch (Throwable ex) {
	 * ex.printStackTrace(); //throw new
	 * RuntimeException("Error al crear la factoria de JPA:->"+
	 * ex.getMessage()); }
	 * 
	 * mm.getTransaction().begin(); // rubrique.setIdRubrique(1L); rubrique.
	 * setLibelleIhmRubrique("Rubrique testInjectionConfPersistWithEntityManagerProviderDb2"
	 * );
	 * 
	 * mm.persist(rubrique);
	 * 
	 * // chapitre.setIdChapitre(1L);
	 * chapitre.setLibelleIhmChapitre("Chapitre");
	 * chapitre.setIhmRubriqueTypeDataBean(rubrique); mm.persist(chapitre);
	 * 
	 * // page.setIdPage(1L); page.setLibelleIhmPage("Test");
	 * page.setIhmChapitreDataBean(chapitre); mm.persist(page); mm.flush();
	 * mm.getTransaction().commit();
	 * 
	 * }
	 */

}
