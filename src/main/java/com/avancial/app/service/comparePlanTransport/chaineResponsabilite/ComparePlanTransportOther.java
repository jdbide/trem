package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.service.comparePlanTransport.CompareTrain;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;

public class ComparePlanTransportOther extends AChaineComparePlanTransport {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        System.out.println("ComparePlanTransportOther");
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        PlanTransport pdtAncien = (PlanTransport) comparableAncien;
        PlanTransport pdtNouveau = (PlanTransport) comparableNouveau;

        IComparePlanTransport comparePlanTransport = new CompareTrain();
        for (Train trainNouveau : pdtNouveau.getTrains()) {
            for (Train trainAncien : pdtAncien.getTrains()) {
                if (trainNouveau.equals(trainAncien)) {
                    res.addAll(comparePlanTransport.compare(trainAncien, trainNouveau));
                }
            }
        }
        res.addAll(this.successeurCompare(comparableAncien, comparableNouveau));
        return res;
    }

}
