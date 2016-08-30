package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

/**
 * Chaîne de responsabilité qui renvoie le résultat de comparaison entre deux
 * objets métier d'un plan de transport
 * 
 * @author heloise.guillemaud
 *
 */
public interface IChaineComparePlanTransport {

    /**
     * Sette le successeur du maillon de la chaîne
     * 
     * @param chaineComparePlanTransport
     *            Maillon à ajouter à la suite de la chaîne
     */
    public void setSuccesseur(IChaineComparePlanTransport chaineComparePlanTransport);

    /**
     * Traitement de la chaîne : comparaison entre deux objets métier d'un plan
     * de transport.
     * 
     * @param comparableAncien
     *            Objet métier d'un jeu de données moins récent
     * @param comparableNouveau
     *            Objet métier d'un jeu de données plus récent
     * @return Map contenant les {@link IComparaisonPlanTransport} représentant
     *         les différences (ou les ressemblances) entre les deux objets
     *         métier.
     * @throws Exception
     */
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception;
}
