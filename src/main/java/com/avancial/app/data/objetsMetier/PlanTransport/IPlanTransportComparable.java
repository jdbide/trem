package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.List;

public interface IPlanTransportComparable {

    /**
     * Compare un objet d'un jeu de données avec celui d'un autre jeu de données
     * moins récent.
     * 
     * @param autre
     *            Objet de même type issu d'un jeu de données moins récent
     * @return
     * @throws Exception
     *             L'objet autre est de classe différente
     */
    public List<IComparaisonPlanTransport> compare(IPlanTransportComparable autre) throws Exception;

}
