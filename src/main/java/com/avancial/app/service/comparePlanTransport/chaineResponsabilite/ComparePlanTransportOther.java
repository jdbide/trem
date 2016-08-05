package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransportComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.service.comparePlanTransport.CompareTrain;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;

public class ComparePlanTransportOther extends AChaineComparePlanTransport {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransportComparable comparableAncien,
            IPlanTransportComparable comparableNouveau) throws Exception {
        System.out.println("ComparePlanTransportOther");
        PlanTransport pdtAncien = (PlanTransport) comparableAncien;
        PlanTransport pdtNouveau = (PlanTransport) comparableNouveau;
        
        IComparePlanTransport comparePlanTransport = new CompareTrain();
        for (Train trainNouveau : pdtNouveau.getTrains()) {
            for (Train trainAncien : pdtAncien.getTrains()) {
                comparePlanTransport.compare(trainAncien, trainNouveau);
            }
        }
        return this.successeurCompare(comparableAncien, comparableNouveau);
    }

}
