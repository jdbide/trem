/**
 * 
 */
package com.avancial.app.data.dto;

import java.util.Date;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;

/**
 * DTO permettant de filtrer un plan de transport
 * 
 * @author gabriel.gagnier
 *
 */
public class FiltrePlanTransportDto {

    private int idCompagnieEnvironnement;
    private List<String> numerosTrains;
    private List<String> numerosTranches;
    private Date debutPeriode;
    private Date finPeriode;
    private int idOrigineDestination;
    private List<Integer> idsStops;
    private int idTosp;
    private int idCodeRM;
    private int idCodeEquipement;
    private EnumTrancheStatut status;
    /**
     * Boolean qui represente si l'od selectionné doit se prendre en aller/retour
     */
    private boolean roundTrip;

    /**
     * Constructeur vide
     */
    public FiltrePlanTransportDto() {
        super();
    }

    /**
     * @return the idCompagnieEnvironnement
     */
    public int getIdCompagnieEnvironnement() {
        return this.idCompagnieEnvironnement;
    }

    /**
     * @param idCompagnieEnvironnement
     *            the idCompagnieEnvironnement to set
     */
    public void setIdCompagnieEnvironnement(int idCompagnieEnvironnement) {
        this.idCompagnieEnvironnement = idCompagnieEnvironnement;
    }

    /**
     * @return the numerosTrains
     */
    public List<String> getNumerosTrains() {
        return this.numerosTrains;
    }

    /**
     * @param numerosTrains
     *            the numerosTrains to set
     */
    public void setNumerosTrains(List<String> numerosTrains) {
        this.numerosTrains = numerosTrains;
    }

    /**
     * @return the numerosTranches
     */
    public List<String> getNumerosTranches() {
        return this.numerosTranches;
    }

    /**
     * @param numerosTranches
     *            the numerosTranches to set
     */
    public void setNumerosTranches(List<String> numerosTranches) {
        this.numerosTranches = numerosTranches;
    }

    /**
     * @return the debutPeriode
     */
    public Date getDebutPeriode() {
        return this.debutPeriode;
    }

    /**
     * @param debutPeriode
     *            the debutPeriode to set
     */
    public void setDebutPeriode(Date debutPeriode) {
        this.debutPeriode = debutPeriode;
    }

    /**
     * @return the finPeriode
     */
    public Date getFinPeriode() {
        return this.finPeriode;
    }

    /**
     * @param finPeriode
     *            the finPeriode to set
     */
    public void setFinPeriode(Date finPeriode) {
        this.finPeriode = finPeriode;
    }

    /**
     * @return the idOrigineDestination
     */
    public int getIdOrigineDestination() {
        return this.idOrigineDestination;
    }

    /**
     * @param idOrigineDestination
     *            the idOrigineDestination to set
     */
    public void setIdOrigineDestination(int idOrigineDestination) {
        this.idOrigineDestination = idOrigineDestination;
    }

    /**
     * @return the idsStops
     */
    public List<Integer> getIdsStops() {
        return this.idsStops;
    }

    /**
     * @param idsStops
     *            the idsStops to set
     */
    public void setIdsStops(List<Integer> idsStops) {
        this.idsStops = idsStops;
    }

    /**
     * @return the idTosp
     */
    public int getIdTosp() {
        return this.idTosp;
    }

    /**
     * @param idTosp
     *            the idTosp to set
     */
    public void setIdTosp(int idTosp) {
        this.idTosp = idTosp;
    }

    /**
     * @return the idCodeRM
     */
    public int getIdCodeRM() {
        return this.idCodeRM;
    }

    /**
     * @param idCodeRM
     *            the idCodeRM to set
     */
    public void setIdCodeRM(int idCodeRM) {
        this.idCodeRM = idCodeRM;
    }

    /**
     * @return the idCodeEquipement
     */
    public int getIdCodeEquipement() {
        return this.idCodeEquipement;
    }

    /**
     * @param idCodeEquipement
     *            the idCodeEquipement to set
     */
    public void setIdCodeEquipement(int idCodeEquipement) {
        this.idCodeEquipement = idCodeEquipement;
    }

    /**
     * @return the status
     */
    public EnumTrancheStatut getStatus() {
        return this.status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(EnumTrancheStatut status) {
        this.status = status;
    }

   /**
    * @return the roundTrip
    */
   public boolean isRoundTrip() {
      return roundTrip;
   }

   /**
    * @param roundTrip the roundTrip to set
    */
   public void setRoundTrip(boolean roundTrip) {
      this.roundTrip = roundTrip;
   }



}
