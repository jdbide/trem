package com.avancial.app.service.traiteObjetMetier;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeServiceEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumClasseService;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.ServiceABord;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class TraiteObjetMetierRegimeService implements ITraiteObjetMetier {

	@Override
	public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime, Date dateDebutPeriode) {
		List<ASousRegimeTranche> listeServices = (List<ASousRegimeTranche>) atomicTranche.get()
				.getAttributsField(ServiceABord.class);
		if (listeServices == null) {
			listeServices = new ArrayList<ASousRegimeTranche>();
		}
		for (MotriceRegimeServiceEntity regimeService : regime.getMotriceRegimeServices()) {
			try {
            listeServices.add(new ServiceABord(regimeService.getServiceCodeMotriceRegimeService(),
            		EnumClasseService.getEnumClasseService(regimeService.getClassMotriceRegimeService()),
            		new Gare(regimeService.getOrigMotriceRegimeService()),
            		new Gare(regimeService.getDestMotriceRegimeService()),
            		new Regime(regime.getPeriodMotriceRegime(), dateDebutPeriode)));
         } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
		}
		atomicTranche.get().addAttributsField(listeServices);
	}

}
