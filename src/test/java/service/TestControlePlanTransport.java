package service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.ComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.controlePlanTransport.ControlePlanTransport;
import com.avancial.socle.utils.ListUtils;

import junit.framework.Assert;

public class TestControlePlanTransport {
	@Test
	public void testPlanTransport() {
		Train train1 = new Train();
		train1.setNumeroTrain("1");
		List<Train> trains1 = new ArrayList<>();
		trains1.add(train1);
		Tranche tranche1 = new Tranche();
		tranche1.setNumeroTranche("10");
		train1.getTranches().add(tranche1);
		
		Train train2 = new Train();
		train2.setNumeroTrain("1");
		List<Train> trains2 = new ArrayList<>();
		trains2.add(train2);
		Tranche tranche2 = new Tranche();
		tranche2.setNumeroTranche("10");
		train2.getTranches().add(tranche2);

		PlanTransport p1 = new PlanTransport(EnumCompagnies.ES, trains1);
		PlanTransport p2 = new PlanTransport(EnumCompagnies.ES, trains2);

		ComparaisonControlePlanTransport<IPlanTransport> cpt = new ComparaisonControlePlanTransport<>();
		cpt.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.CONTROL);
		cpt.setNumeroTrain("1");
		cpt.setNumeroTranche("10");

		List<AComparaisonPlanTransport<IPlanTransport>> expected = new ArrayList<>();
		expected.add(cpt);

		IComparePlanTransport comparePlanTransport = new ControlePlanTransport();
		try {
			Assert.assertTrue("Compare CONTROL PlanTransport", ListUtils.compareLists(expected, comparePlanTransport
					.compare(p1, p2).getComparaison(EnumTypeComparaisonPlanTransport.CONTROL, null)));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
