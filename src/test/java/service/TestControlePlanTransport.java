package service;

import org.junit.Test;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.ComparePlanTransportControl;
import com.avancial.app.utilitaire.MapPlansDeTransport;

import factory.PlanTransportFactory;

public class TestControlePlanTransport {
	@Test
	public void testPlanTransport() {
		MapPlansDeTransport mapPlansDeTransport = PlanTransportFactory.createDataForControle();
		PlanTransport pdtXls = mapPlansDeTransport.getPlanTransportDraft();
		PlanTransport pdtBase = mapPlansDeTransport.getPlanTransportActive();
		ComparePlanTransportControl comp = new ComparePlanTransportControl();
		MapComparaisonPlanTransport res;
		try {
			res = comp.compare(pdtBase, pdtXls);
			res.getComparaison(EnumTypeComparaisonPlanTransport.CONTROL);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
