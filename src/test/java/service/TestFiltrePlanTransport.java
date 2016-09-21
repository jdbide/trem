package service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.filtrePlanTransport.CritereEt;
import com.avancial.app.service.filtrePlanTransport.CritereOD;
import com.avancial.app.service.filtrePlanTransport.FiltrePlanTransport;
import com.avancial.app.service.filtrePlanTransport.ICritereTrainTranche;

import junit.framework.Assert;

public class TestFiltrePlanTransport {

   private Gare               orig                = new Gare("ORIG");
   private Gare               orig2               = new Gare("OR2");
   private Gare               dest                = new Gare("DEST");
   private OrigineDestination origineDestination  = new OrigineDestination(this.orig, this.dest, new Regime());
   private OrigineDestination origineDestination2 = new OrigineDestination(this.orig2, this.dest, new Regime());

   @Test
   public void main() {
      FiltrePlanTransport filtrePlanTransport = new FiltrePlanTransport();


      List<ASousRegimeTranche> ods1 = new ArrayList<>();
      ods1.add(this.origineDestination2);
      ods1.add(this.origineDestination);
      Tranche tranche1 = new Tranche();
      tranche1.addAttributsField(ods1);
      List<Tranche> tranches3 = new ArrayList<>();
      tranches3.add(tranche1);
      Train train3 = new Train(tranches3, "1", true);
      PlanTransport planTransport2 = new PlanTransport();
      planTransport2.getTrains().add(train3);
      List<ASousRegimeTranche> ods3 = new ArrayList<>();
      ods3.add(this.origineDestination);
      ods3.add(this.origineDestination2);
      Tranche tranche3 = new Tranche();
      tranche3.addAttributsField(ods3);
      List<Tranche> tranches2 = new ArrayList<>();
      tranches2.add(tranche3);
      Train train2 = new Train(tranches2, "2", true);
      planTransport2.getTrains().add(train2);

      ICritereTrainTranche critereOD = new CritereOD(this.origineDestination);
      PlanTransport planTransport = this.createPlanTransportTest();
      filtrePlanTransport.filtre(planTransport, critereOD);
      Assert.assertEquals("Test CritereOD", planTransport2, planTransport);

      ICritereTrainTranche critereOD2 = new CritereOD(this.origineDestination2);
      ICritereTrainTranche critereEt = new CritereEt(critereOD, critereOD2);
      planTransport = this.createPlanTransportTest();
      filtrePlanTransport.filtre(planTransport, critereEt);
      Assert.assertEquals("Test CritereEt", planTransport2, planTransport);
      
      ICritereTrainTranche critereOu = new CritereEt(critereOD, critereOD2);
      planTransport = this.createPlanTransportTest();
      filtrePlanTransport.filtre(planTransport, critereOu);
      List<ASousRegimeTranche> ods2 = new ArrayList<>();
      ods2.add(this.origineDestination2);
      Tranche tranche2 = new Tranche();
      tranche2.addAttributsField(ods2);
      planTransport2.getTrains().get(0).getTranches().add(tranche2);
      Assert.assertEquals("Test CritereOu", planTransport2, planTransport);
   }

   private PlanTransport createPlanTransportTest() {
      List<ASousRegimeTranche> ods1 = new ArrayList<>();
      ods1.add(this.origineDestination2);
      ods1.add(this.origineDestination);
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
      ods3.add(this.origineDestination);
      ods3.add(this.origineDestination2);
      Tranche tranche3 = new Tranche();
      tranche3.addAttributsField(ods3);
      List<Tranche> tranches2 = new ArrayList<>();
      tranches2.add(tranche3);
      Train train2 = new Train(tranches2, "2", true);
      planTransport.getTrains().add(train2);
      
      return planTransport;
   }
}
