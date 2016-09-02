package com.avancial.app.service.traiteObjetMetier;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public interface ITraiteObjetMetier {

   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime, Date dateDebutPeriode);
   
}
