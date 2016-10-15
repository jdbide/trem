package traitement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

import com.avancial.app.data.databean.EStatus;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.JeuDonneesService;
import com.avancial.app.service.traiteObjetMetier.ITraiteObjetMetier;
import com.avancial.app.service.traiteObjetMetier.TraiteObjetMetierRegimeFactory;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.ihm.menu.model.databean.PageDataBean;
import com.avancial.socle.persistence.EntityManagerProducerSocle;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

@RunWith(Arquillian.class)
public class RemplissageObjMetierTestHibernate {
   @Deployment
   public static WebArchive createDeployment() {
      File[] lib = Maven.resolver().resolve("org.jboss.weld.servlet:weld-servlet:2.1.0.CR1").withTransitivity().as(File.class);

      WebArchive jar = ShrinkWrap.create(WebArchive.class)
            .addPackage(CodeSat.class.getPackage())
            .addPackage(PageDataBean.class.getPackage())
            .addClass(JeuDonneesService.class)
            .addPackage(Socle_PUSocle.class.getPackage())
            .addPackage(EntityManagerProducerSocle.class.getPackage())
            .addAsWebInfResource("WEB-INF/beans.xml", "beans.xml")
            .addAsLibraries(lib).addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml").setWebXML("WEB-INF/web.xml")
            .addAsManifestResource("META-INF/context.xml", "context.xml");

      System.out.println(jar.toString(true));

      return jar;
   }

   @Inject
   @Socle_PUSocle
   EntityManager em;

   @Test
   public void testRemplissageDesserteObjMetier() throws Exception {
      this.em.clear();

      TraiteObjetMetierRegimeFactory traiteObjetMetierRegimeFactory = new TraiteObjetMetierRegimeFactory();
      MapPlansDeTransport mapPlansDeTransport = new MapPlansDeTransport();

      /* Creation du plan de transport */
      //PlanTransport planTransport = new PlanTransport(EnumCompagnies.ES, new ArrayList<Train>());
      PlanTransport planTransport = mapPlansDeTransport.get(EStatus.ACTIVE).getPlanTransport();

      Query query = this.em.createQuery("SELECT t FROM MotriceTrainTrancheEntity t", MotriceTrainTrancheEntity.class);

      List<MotriceTrainTrancheEntity> trainsTranches = query.getResultList();
      Train train = new Train();

      String lastTrainNumber = "";

      /* Remplissage de la liste des trains */
      for (MotriceTrainTrancheEntity resTrainTranche : trainsTranches) {
         if (!resTrainTranche.getTrainNumberMotriceTrainTranche().equals(lastTrainNumber)) {
            train = new Train(new ArrayList<Tranche>(), resTrainTranche.getTrainNumberMotriceTrainTranche(), resTrainTranche.getValidForRRMotriceTrainTranche());
         }
         AtomicReference<Tranche> atomicTranche = new AtomicReference<Tranche>(new Tranche());
         atomicTranche.get().setNumeroTranche(resTrainTranche.getTrancheNumberMotriceTrainTranche());

         List<MotriceRegimeEntity> regimeEntities = resTrainTranche.getMotriceRegimeEntities();
         for (MotriceRegimeEntity regime : regimeEntities) {
            System.out.println("Traitement de " + regime.getMotriceRefRegimeType().getLabelRegimeType());
            ITraiteObjetMetier traiteObjetMetier = traiteObjetMetierRegimeFactory.getTraiteMotriceRegime(regime.getMotriceRefRegimeType().getIdMotriceRefRegimeType());
            traiteObjetMetier.traite(atomicTranche, regime, resTrainTranche.getJeuDonnee().getDateDebutPeriode());
         }

         train.getTranches().add(atomicTranche.get());
         planTransport.getTrains().add(train);
         lastTrainNumber = resTrainTranche.getTrainNumberMotriceTrainTranche();
      }
      /* Fin du remplissage du plan de transport */

      /* Affichage de certains resultats */
      System.out.println("RESULTAT DES (Train, Tranche, RegimeTranche) ");
      for (Train tra : planTransport.getTrains()) {
         for (Tranche trch : tra.getTranches()) {
            System.out.println("(" + tra.getNumeroTrain() + ", " + trch.getNumeroTranche() + ", " + trch.getRegime().getCodeRegime() + ")");
         }
      }
//      System.out.println("RESULTAT DES (Train, Tranche, RegimeDistribution, IndiceDistribution) ");
//      for (Train tra : planTransport.getTrains()) {
//         for (Tranche trch : tra.getTranches()) {
//            for (ASousRegimeTranche distribution : trch.getAttributsField(Distribution.class)) {
//               System.out.println("(" + tra.getNumeroTrain() + ", " + trch.getNumeroTranche() + ", " + distribution.getRegime().getCodeRegime() + ", " + ((Distribution) distribution).getIndiceDistribution() + ")");
//            }
//         }
//      }
//      System.out.println("RESULTAT DES (Train, Tranche, RegimeEqpType, EqpType) ");
//      for (Train tra : planTransport.getTrains()) {
//         for (Tranche trch : tra.getTranches()) {
//            for (ASousRegimeTranche typeEquipement : trch.getAttributsField(TypeEquipement.class)) {
//               System.out.println("(" + tra.getNumeroTrain() + ", " + trch.getNumeroTranche() + ", " + typeEquipement.getRegime().getCodeRegime() + ", " + ((TypeEquipement) typeEquipement).getTypeEquipement() + ")");
//            }
//         }
//      }
//      System.out.println("RESULTAT DES (Train, Tranche, RegimeFareProfile, FareProfile) ");
//      for (Train tra : planTransport.getTrains()) {
//         for (Tranche trch : tra.getTranches()) {
//            for (ASousRegimeTranche fareProfile : trch.getAttributsField(FareProfile.class)) {
//               System.out.println("(" + tra.getNumeroTrain() + ", " + trch.getNumeroTranche() + ", " + fareProfile.getRegime().getCodeRegime() + ", " + ((FareProfile) fareProfile).getFareProfileCode() + ")");
//            }
//         }
//      }
//      System.out.println("RESULTAT DES (Train, Tranche, RegimeCodeSat, CodeSat) ");
//      for (Train tra : planTransport.getTrains()) {
//         for (Tranche trch : tra.getTranches()) {
//            for (ASousRegimeTranche codeSat : trch.getAttributsField(CodeSat.class)) {
//               System.out.println("(" + tra.getNumeroTrain() + ", " + trch.getNumeroTranche() + ", " + codeSat.getRegime().getCodeRegime() + ", " + ((CodeSat) codeSat).getCodeSat() + ")");
//            }
//         }
//      }
//      System.out.println("RESULTAT DES (Train, Tranche, RegimeOD, Origine, Destination) ");
//      for (Train tra : planTransport.getTrains()) {
//         for (Tranche trch : tra.getTranches()) {
//            for (ASousRegimeTranche origineDestination : trch.getAttributsField(OrigineDestination.class)) {
//               System.out.println("(" + tra.getNumeroTrain() + ", " + trch.getNumeroTranche() + ", " + origineDestination.getRegime().getCodeRegime() + ", " + ((OrigineDestination) origineDestination).getOrigine().getCodeGare() + ", "
//                     + ((OrigineDestination) origineDestination).getDestination().getCodeGare() + ")");
//            }
//         }
//      }
//      System.out.println("RESULTAT DES (Train, Tranche, RegimeMeal, TypeRepas, HeureDebut, HeureFin) ");
//      for (Train tra : planTransport.getTrains()) {
//         for (Tranche trch : tra.getTranches()) {
//            for (ASousRegimeTranche meal : trch.getAttributsField(Repas.class)) {
//               System.out
//                     .println("(" + tra.getNumeroTrain() + ", " + trch.getNumeroTranche() + ", " + meal.getRegime().getCodeRegime() + ", " + ((Repas) meal).getTypeRepas().getSymbol() + ", " + ((Repas) meal).getHoraire().getHoraireDebut() + ", " + ((Repas) meal).getHoraire().getHoraireFin() + ")");
//            }
//         }
//      }
//      System.out.println("RESULTAT DES (Train, Tranche, RegimeRestriction, OrigineRestriction, DestinationRestriction) ");
//      for (Train tra : planTransport.getTrains()) {
//         for (Tranche trch : tra.getTranches()) {
//            for (ASousRegimeTranche restriction : trch.getAttributsField(Restriction.class)) {
//               System.out.println("(" + tra.getNumeroTrain() + ", " + trch.getNumeroTranche() + ", " + restriction.getRegime().getCodeRegime() + ", " + ((Restriction) restriction).getOrigine().getCodeGare() + ", " + ((Restriction) restriction).getDestination().getCodeGare() + ")");
//            }
//         }
//      }
//      System.out.println("RESULTAT DES (Train, Tranche, RegimeService, ClasseService, TypeService, OrigineService, DestinationService) ");
//      for (Train tra : planTransport.getTrains()) {
//         for (Tranche trch : tra.getTranches()) {
//            for (ASousRegimeTranche service : trch.getAttributsField(ServiceABord.class)) {
//               System.out.println("(" + tra.getNumeroTrain() + ", " + trch.getNumeroTranche() + ", " + service.getRegime().getCodeRegime() + ", " + ((ServiceABord) service).getClasse().getSymbol() + ", " + ((ServiceABord) service).getCodeService() + ", "
//                     + ((ServiceABord) service).getOrigine().getCodeGare() + ", " + ((ServiceABord) service).getDestination().getCodeGare() + ")");
//            }
//         }
//      }
//      System.out.println("RESULTAT DES (Train, Tranche, RegimeDesserte, Gare, HeureArrivee, HeureDepart) ");
//      for (Train tra : planTransport.getTrains()) {
//         for (Tranche trch : tra.getTranches()) {
//            for (ASousRegimeTranche dessertes : trch.getAttributsField(Desserte.class)) {
//               for (GareHoraire stop : ((Desserte) dessertes).getGareHoraires()) {
//                  if (stop.getHoraire().getHoraireDebut() == null) {
//                     System.out.println("(" + tra.getNumeroTrain() + ", " + trch.getNumeroTranche() + ", " + dessertes.getRegime().getCodeRegime() + ", " + stop.getGare().getCodeGare() + ", " + "    " + ", " + stop.getHoraire().getHoraireFin().getHours() + ":"
//                           + stop.getHoraire().getHoraireFin().getMinutes() + ")");
//                  } else if (stop.getHoraire().getHoraireFin() == null) {
//                     System.out.println("(" + tra.getNumeroTrain() + ", " + trch.getNumeroTranche() + ", " + dessertes.getRegime().getCodeRegime() + ", " + stop.getGare().getCodeGare() + ", " + stop.getHoraire().getHoraireDebut().getHours() + ":" + stop.getHoraire().getHoraireDebut().getMinutes()
//                           + ", " + "    " + ")");
//                  } else {
//                     System.out.println("(" + tra.getNumeroTrain() + ", " + trch.getNumeroTranche() + ", " + dessertes.getRegime().getCodeRegime() + ", " + stop.getGare().getCodeGare() + ", " + stop.getHoraire().getHoraireDebut().getHours() + ":" + stop.getHoraire().getHoraireDebut().getMinutes()
//                           + ", " + stop.getHoraire().getHoraireFin().getHours() + ":" + stop.getHoraire().getHoraireFin().getMinutes() + ")");
//                  }
//               }
//            }
//         }
//      }
//      System.out.println("RESULTAT DES (Train, Tranche, RegimeComposition, CodeClasse, CodeDiag, CodeRame, CodeRM, Voiture) ");
//      for (Train tra : planTransport.getTrains()) {
//         for (Tranche trch : tra.getTranches()) {
//            for (ASousRegimeTranche composition : trch.getAttributsField(Composition.class)) {
//               for (Voiture voiture : ((Composition) composition).getVoitures()) {
//                  System.out.println("(" + tra.getNumeroTrain() + ", " + trch.getNumeroTranche() + ", " + composition.getRegime().getCodeRegime() + ", " + ((Composition) composition).getCodeClasse() + ", " + ((Composition) composition).getCodeDiag() + ", " + ((Composition) composition).getCodeRame()
//                        + ", " + ((Composition) composition).getCodeRm() + ", " + voiture.getNumeroVoiture() + ")");
//               }
//            }
//         }
//      }
//      System.out.println("RESULTAT DES (Train, Tranche, RegimeSpecificity, Voiture, Compartiment, Siege) ");
//      for (Train tra : planTransport.getTrains()) {
//         for (Tranche trch : tra.getTranches()) {
//            for (ASousRegimeTranche specification : trch.getAttributsField(Specification.class)) {
//               for (Compartiment compartiment : ((Specification) specification).getVoiture().getCompartiments()) {
//                  for (Siege siege : compartiment.getSieges()) {
//                     System.out.println(
//                           "(" + tra.getNumeroTrain() + ", " + trch.getNumeroTranche() + ", " + specification.getRegime().getCodeRegime() + ", " + ((Specification) specification).getVoiture().getNumeroVoiture() + ", " + compartiment.getNumeroCompartiment() + ", " + siege.getNumeroSiege() + ")");
//                  }
//               }
//            }
//         }
//      }
   }
}
