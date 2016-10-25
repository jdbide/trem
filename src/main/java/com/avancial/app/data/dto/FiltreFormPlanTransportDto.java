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
public class FiltreFormPlanTransportDto {

    private Integer idCompagnieEnvironnement;
    private List<String> numerosTrains;
    private List<String> numerosTranches;
    private Date debutPeriode;
    private Date finPeriode;
    private Integer idOrigineDestination;
    private List<Integer> idsStops;
    private Integer idTosp;
    private Integer idCodeRM;
    private Integer idCodeEquipement;
    private EnumTrancheStatut status;
    /**
     * Boolean qui represente si l'od selectionné doit se prendre en aller/retour
     */
    private boolean roundTrip;

    /**
     * Constructeur vide
     */
    public FiltreFormPlanTransportDto() {
        super();
    }

    /**
     * @return the idCompagnieEnvironnement
     */
    public Integer getIdCompagnieEnvironnement() {
        return this.idCompagnieEnvironnement;
    }

    /**
     * @param idCompagnieEnvironnement
     *            the idCompagnieEnvironnement to set
     */
    public void setIdCompagnieEnvironnement(Integer idCompagnieEnvironnement) {
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
    public Integer getIdOrigineDestination() {
        return this.idOrigineDestination;
    }

    /**
     * @param idOrigineDestination
     *            the idOrigineDestination to set
     */
    public void setIdOrigineDestination(Integer idOrigineDestination) {
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
    public Integer getIdTosp() {
        return this.idTosp;
    }

    /**
     * @param idTosp
     *            the idTosp to set
     */
    public void setIdTosp(Integer idTosp) {
        this.idTosp = idTosp;
    }

    /**
     * @return the idCodeRM
     */
    public Integer getIdCodeRM() {
        return this.idCodeRM;
    }

    /**
     * @param idCodeRM
     *            the idCodeRM to set
     */
    public void setIdCodeRM(Integer idCodeRM) {
        this.idCodeRM = idCodeRM;
    }

    /**
     * @return the idCodeEquipement
     */
    public Integer getIdCodeEquipement() {
        return this.idCodeEquipement;
    }

    /**
     * @param idCodeEquipement
     *            the idCodeEquipement to set
     */
    public void setIdCodeEquipement(Integer idCodeEquipement) {
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
