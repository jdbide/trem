package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;

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
     * @param chaineComparePlanTransport Maillon à ajouter à la suite de la chaîne
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
     * @return Liste de {@link IComparaisonPlanTransport} représentant les
     *         différences (ou les ressemblances) entre les deux objets métier.
     * @throws Exception
     */
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception;
}
