package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.ArrayList;
import java.util.List;
import com.avancial.socle.utils.ListUtils;

public class PlanTransport implements IPlanTransportComparable {

    private EnumCompagnies compagnie;
    private List<Train> trains;

    @Override
    public boolean equals(Object obj) {
        PlanTransport plan = (PlanTransport) obj;
        if (plan.getCompagnie().equals(this.compagnie) && ListUtils.compareLists(plan.getTrains(), this.trains)) {
            return true;
        }
        return false;
    }

    /**
     * @param compagnie
     * @param trains
     */
    public PlanTransport(EnumCompagnies compagnie, List<Train> trains) {
        super();
        this.compagnie = compagnie;
        this.trains = trains;
    }

    public PlanTransport() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the compagnie
     */
    public EnumCompagnies getCompagnie() {
        return this.compagnie;
    }

    /**
     * @param compagnie
     *            the compagnie to set
     */
    public void setCompagnie(EnumCompagnies compagnie) {
        this.compagnie = compagnie;
    }

    /**
     * @return the trains
     */
    public List<Train> getTrains() {
        return this.trains;
    }

    /**
     * @param trains
     *            the trains to set
     */
    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransportComparable autre) throws Exception {
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        PlanTransport autrePlanTransport = (PlanTransport) autre;
        
        Train train, autreTrain;
        for (int cpt = 0; cpt < this.trains.size(); cpt++) {
            train = this.trains.get(cpt);
            for (int cptAutre = cpt + 1; cptAutre < autrePlanTransport.getTrains().size(); cptAutre++) {
                autreTrain = autrePlanTransport.getTrains().get(cptAutre);
                res.addAll(train.compare(autreTrain));
            }
        }
        return res;
    }

}
