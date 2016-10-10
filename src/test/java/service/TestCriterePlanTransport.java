package service;

import java.util.ArrayList;
import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tosp;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.filtrePlanTransport.CritereEt;
import com.avancial.app.service.filtrePlanTransport.CritereOu;
import com.avancial.app.service.filtrePlanTransport.CriterePlanTransport;
import com.avancial.app.service.filtrePlanTransport.CritereTrancheOD;
import com.avancial.app.service.filtrePlanTransport.FiltreEtPlanTransport;
import com.avancial.app.service.filtrePlanTransport.FiltreOuPlanTransport;
import com.avancial.app.service.filtrePlanTransport.FiltreSousRegimePlanTransport;
import com.avancial.app.service.filtrePlanTransport.ICritere;
import com.avancial.app.service.filtrePlanTransport.IFiltre;

import junit.framework.Assert;

public class TestCriterePlanTransport {

   private Gare               orig                = new Gare("ORIG");
   private Gare               orig2               = new Gare("OR2");
   private Gare               dest                = new Gare("DEST");
   private OrigineDestination origineDestination1 = new OrigineDestination(this.orig, this.dest, new Regime());
   private OrigineDestination origineDestination2 = new OrigineDestination(this.orig2, this.dest, new Regime());
   private Tosp               tosp1               = new Tosp("tosp1", new Regime());
   private Tosp               tosp2               = new Tosp("tosp2", new Regime());

   // @Test
   public void main() {
      CriterePlanTransport filtrePlanTransport = new CriterePlanTransport();

      List<ASousRegimeTranche> ods1 = new ArrayList<>();
      ods1.add(this.origineDestination2);
      ods1.add(this.origineDestination1);
      Tranche tranche1 = new Tranche();
      tranche1.addAttributsField(ods1);
      List<Tranche> tranches3 = new ArrayList<>();
      tranches3.add(tranche1);
      Train train3 = new Train(tranches3, "1", true);
      PlanTransport planTransport2 = new PlanTransport();
      planTransport2.getTrains().add(train3);
      List<ASousRegimeTranche> ods3 = new ArrayList<>();
      ods3.add(this.origineDestination1);
      ods3.add(this.origineDestination2);
      Tranche tranche3 = new Tranche();
      tranche3.addAttributsField(ods3);
      List<Tranche> tranches2 = new ArrayList<>();
      tranches2.add(tranche3);
      Train train2 = new Train(tranches2, "2", true);
      planTransport2.getTrains().add(train2);

      ICritere<Tranche> critereOD = new CritereTrancheOD(this.origineDestination1);
      PlanTransport planTransport = this.createPlanTransportTestOd();
      filtrePlanTransport.filtre(planTransport, critereOD);
      Assert.assertEquals("Test CritereTrancheOD", planTransport2, planTransport);

      ICritere<Tranche> critereOD2 = new CritereTrancheOD(this.origineDestination2);
      ICritere<Tranche> critereEt = new CritereEt<Tranche>(critereOD, critereOD2);
      planTransport = this.createPlanTransportTestOd();
      filtrePlanTransport.filtre(planTransport, critereEt);
      Assert.assertEquals("Test CritereTrainTrancheEt", planTransport2, planTransport);

      ICritere<Tranche> critereOu = new CritereOu<Tranche>(critereOD, critereOD2);
      planTransport = this.createPlanTransportTestOd();
      filtrePlanTransport.filtre(planTransport, critereOu);
      List<ASousRegimeTranche> ods2 = new ArrayList<>();
      ods2.add(this.origineDestination2);
      Tranche tranche2 = new Tranche();
      tranche2.addAttributsField(ods2);
      planTransport2.getTrains().get(0).getTranches().add(tranche2);
      Assert.assertEquals("Test CritereTrainTrancheOu", planTransport2, planTransport);
   }

   // @Test
   public void filtreOd() {
      List<ASousRegimeTranche> ods1 = new ArrayList<>();
      ods1.add(this.origineDestination1);
      List<ASousRegimeTranche> ods2 = new ArrayList<>();
      ods2.add(this.origineDestination2);
      List<ASousRegimeTranche> ods3 = new ArrayList<>();
      ods3.add(this.origineDestination1);
      ods3.add(this.origineDestination2);
      Tranche tranche1 = new Tranche();
      tranche1.addAttributsField(ods1);
      Tranche tranche2 = new Tranche();
      tranche2.addAttributsField(ods2);
      Tranche tranche3 = new Tranche();
      tranche3.addAttributsField(ods3);

      List<Tranche> tranches1 = new ArrayList<>();
      tranches1.add(tranche1);
      List<Tranche> tranches2 = new ArrayList<>();
      tranches2.add(tranche1);

      Train train1 = new Train(tranches1, "1", true);
      Train train2 = new Train(tranches2, "2", true);

      PlanTransport planTransportExpected = new PlanTransport();

      planTransportExpected.getTrains().add(train1);
      planTransportExpected.getTrains().add(train2);

      PlanTransport planTransportFiltre = this.createPlanTransportTestOd();

      IFiltre<PlanTransport> filtreOD = new FiltreSousRegimePlanTransport(this.origineDestination1);
      PlanTransport planTransportRes = filtreOD.filtreParCritere(planTransportFiltre);
      Assert.assertTrue("Test FiltreTrancheOD1", this.comparePlanTransport(planTransportExpected, planTransportRes));

      IFiltre<PlanTransport> filtreOD2 = new FiltreSousRegimePlanTransport(this.origineDestination2);
      planTransportRes = filtreOD2.filtreParCritere(planTransportFiltre);
      tranches1.clear();
      tranches1.add(tranche2);
      tranches1.add(tranche2);
      tranches2.clear();
      tranches2.add(tranche2);
      Assert.assertTrue("Test FiltreTrancheOD2", this.comparePlanTransport(planTransportExpected, planTransportRes));

      IFiltre<PlanTransport> filtreOu = new FiltreOuPlanTransport(filtreOD, filtreOD2);
      planTransportRes = filtreOu.filtreParCritere(planTransportFiltre);
      tranches1.clear();
      tranches1.add(tranche3);
      tranches1.add(tranche2);
      tranches2.clear();
      tranches2.add(tranche3);
      Assert.assertTrue("Test FiltreOuOD1OD2", this.comparePlanTransport(planTransportExpected, planTransportRes));

      IFiltre<PlanTransport> filtreEt = new FiltreEtPlanTransport(filtreOD, filtreOD2);
      planTransportRes = filtreEt.filtreParCritere(planTransportFiltre);
      planTransportExpected.getTrains().clear();
      Assert.assertTrue("Test FiltreEtOD1OD2", this.comparePlanTransport(planTransportExpected, planTransportRes));
   }

   // @Test
   public void filtreTosp() {
      List<ASousRegimeTranche> tosps1 = new ArrayList<>();
      tosps1.add(this.tosp1);
      List<ASousRegimeTranche> tosps2 = new ArrayList<>();
      tosps2.add(this.tosp2);
      List<ASousRegimeTranche> tosps3 = new ArrayList<>();
      tosps3.add(this.tosp1);
      tosps3.add(this.tosp2);
      Tranche tranche1 = new Tranche();
      tranche1.addAttributsField(tosps1);
      Tranche tranche2 = new Tranche();
      tranche2.addAttributsField(tosps2);
      Tranche tranche3 = new Tranche();
      tranche3.addAttributsField(tosps3);

      List<Tranche> tranches1 = new ArrayList<>();
      tranches1.add(tranche1);
      List<Tranche> tranches2 = new ArrayList<>();
      tranches2.add(tranche1);

      Train train1 = new Train(tranches1, "1", true);
      Train train2 = new Train(tranches2, "2", true);

      PlanTransport planTransportExpected = new PlanTransport();

      planTransportExpected.getTrains().add(train1);
      planTransportExpected.getTrains().add(train2);

      PlanTransport planTransportFiltre = this.createPlanTransportTestTosp();

      IFiltre<PlanTransport> filtreTosp1 = new FiltreSousRegimePlanTransport(this.tosp1);
      PlanTransport planTransportRes = filtreTosp1.filtreParCritere(planTransportFiltre);
      Assert.assertTrue("Test FiltreTrancheTosp1", this.comparePlanTransport(planTransportExpected, planTransportRes));

      IFiltre<PlanTransport> filtreTosp2 = new FiltreSousRegimePlanTransport(this.tosp2);
      planTransportRes = filtreTosp2.filtreParCritere(planTransportFiltre);
      tranches1.clear();
      tranches1.add(tranche2);
      tranches1.add(tranche2);
      planTransportExpected.getTrains().remove(train2);
      Assert.assertTrue("Test FiltreTrancheTosp2", this.comparePlanTransport(planTransportExpected, planTransportRes));

      IFiltre<PlanTransport> filtreOu = new FiltreOuPlanTransport(filtreTosp1, filtreTosp2);
      planTransportRes = filtreOu.filtreParCritere(planTransportFiltre);
      tranches1.clear();
      tranches1.add(tranche3);
      tranches1.add(tranche2);
      planTransportExpected.getTrains().add(train2);
      tranches2.clear();
      tranches2.add(tranche1);
      Assert.assertTrue("Test FiltreOuTosp1Tosp2", this.comparePlanTransport(planTransportExpected, planTransportRes));

      IFiltre<PlanTransport> filtreEt = new FiltreEtPlanTransport(filtreTosp1, filtreTosp2);
      planTransportRes = filtreEt.filtreParCritere(planTransportFiltre);
      planTransportExpected.getTrains().clear();
      Assert.assertTrue("Test FiltreEtTosp1Tosp2", this.comparePlanTransport(planTransportExpected, planTransportRes));
   }

   /**
    * Création d'un plan de transport composé ainsi:
    * <table>
    * <tr>
    * <td>Train1</td>
    * <td>Tranche1</td>
    * <td>OD1</td>
    * </tr>
    * <tr>
    * <td></td>
    * <td></td>
    * <td>OD2</td>
    * </tr>
    * <tr>
    * <td></td>
    * <td>Tranche2</td>
    * <td>OD2</td>
    * <tr>
    * <td>Train2</td>
    * <td>Tranche3</td>
    * <td>OD1</td>
    * </tr>
    * <tr>
    * <td></td>
    * <td></td>
    * <td>OD2</td>
    * </tr>
    * </table>
    * 
    * @return
    */
   private PlanTransport createPlanTransportTestOd() {
      List<ASousRegimeTranche> ods1 = new ArrayList<>();
      ods1.add(this.origineDestination2);
      ods1.add(this.origineDestination1);
      Tranche tranche1 = new Tranche();
      tranche1.addAttributsField(ods1);
      List<ASousRegimeTranche> ods2 = new ArrayList<>();
      ods2.add(this.origineDestination2);
      Tranche tranche2 = new Tranche();
      tranche2.addAttributsField(ods2);
      List<Tranche> tranches1 = new ArrayList<>();
      tranches1.add(tranche1);
      tranches1.add(tranche2);
      Train train1 = new Train(tranches1, "1", true);
      PlanTransport planTransport = new PlanTransport();
      planTransport.getTrains().add(train1);

      List<ASousRegimeTranche> ods3 = new ArrayList<>();
      ods3.add(this.origineDestination1);
      ods3.add(this.origineDestination2);
      Tranche tranche3 = new Tranche();
      tranche3.addAttributsField(ods3);
      List<Tranche> tranches2 = new ArrayList<>();
      tranches2.add(tranche3);
      Train train2 = new Train(tranches2, "2", true);
      planTransport.getTrains().add(train2);

      return planTransport;
   }

   /**
    * Création d'un plan de transport composé ainsi:
    * <table>
    * <tr>
    * <td>Train1</td>
    * <td>Tranche1</td>
    * <td>Tosp1</td>
    * </tr>
    * <tr>
    * <td></td>
    * <td></td>
    * <td>Tosp2</td>
    * </tr>
    * <tr>
    * <td></td>
    * <td>Tranche2</td>
    * <td>Tosp2</td>
    * <tr>
    * <td>Train2</td>
    * <td>Tranche3</td>
    * <td>Tosp1</td>
    * </tr>
    * </table>
    * 
    * @return
    */
   private PlanTransport createPlanTransportTestTosp() {
      List<ASousRegimeTranche> tosps1 = new ArrayList<>();
      tosps1.add(this.tosp1);
      tosps1.add(this.tosp2);
      Tranche tranche1 = new Tranche();
      tranche1.addAttributsField(tosps1);
      List<ASousRegimeTranche> tosps2 = new ArrayList<>();
      tosps2.add(this.tosp2);
      Tranche tranche2 = new Tranche();
      tranche2.addAttributsField(tosps2);
      List<Tranche> tranches1 = new ArrayList<>();
      tranches1.add(tranche1);
      tranches1.add(tranche2);
      Train train1 = new Train(tranches1, "1", true);
      PlanTransport planTransport = new PlanTransport();
      planTransport.getTrains().add(train1);

      List<ASousRegimeTranche> tosps3 = new ArrayList<>();
      tosps3.add(this.tosp1);
      Tranche tranche3 = new Tranche();
      tranche3.addAttributsField(tosps3);
      List<Tranche> tranches2 = new ArrayList<>();
      tranches2.add(tranche3);
      Train train2 = new Train(tranches2, "2", true);
      planTransport.getTrains().add(train2);

      return planTransport;
   }

   public boolean comparePlanTransport(PlanTransport pdt1, PlanTransport pdt2) {
      if (!pdt1.equals(pdt2)) {
         return false;
      }

      int index;
      Train train2;
      Tranche tranche2;
      for (Train train1 : pdt1.getTrains()) {
         index = pdt2.getTrains().indexOf(train1);
         train2 = pdt2.getTrains().get(index);
         for (Tranche tranche1 : train1.getTranches()) {
            index = train2.getTranches().indexOf(tranche1);
            tranche2 = train2.getTranches().get(index);
            if (!tranche1.getAttributs().equals(tranche2.getAttributs())) {
               return false;
            }
         }
      }
      return true;
   }
}
