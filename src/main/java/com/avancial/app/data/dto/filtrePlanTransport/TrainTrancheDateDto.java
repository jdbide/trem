/**
 * 
 */
package com.avancial.app.data.dto.filtrePlanTransport;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.avancial.socle.utils.ListUtils;

/**
 * Dto representant une ligne du resultat de search. Clef: n° Train, n° Tranche,
 * Date du jour
 * 
 * @author hamza.laterem
 *
 */
public class TrainTrancheDateDto {

    private Date dateJour;
    private String numeroTrain;
    private String origine;
    private String destination;
    private String mandatoryBooking;
    private String validForRR;
    private String numeroTranche;
    private String status;
    private String company;
    private String indicateurDistribution;
    private HashMap<String, Integer> globalClass;
    private String codeSat;
    private String codeTosp;
    private Date loadedOn;
    // TODO private LastModifDto lastModif;
    private RMCodeDto rmCode;
    private ServicesDto service;
    private CompositionDto composition;
    private List<StopsDto> stops;

    /**
     * constructeur vide
     * 
     * @author gabriel.gagnier
     */
    public TrainTrancheDateDto() {
    }

    /**
     * @return the dateJour
     */
    public Date getDateJour() {
        return this.dateJour;
    }

    /**
     * @param dateJour
     *            the dateJour to set
     */
    public void setDateJour(Date dateJour) {
        this.dateJour = dateJour;
    }

    /**
     * @return the numeroTrain
     */
    public String getNumeroTrain() {
        return this.numeroTrain;
    }

    /**
     * @param numeroTrain
     *            the numeroTrain to set
     */
    public void setNumeroTrain(String numeroTrain) {
        this.numeroTrain = numeroTrain;
    }

    /**
     * @return the origine
     */
    public String getOrigine() {
        return this.origine;
    }

    /**
     * @param origine
     *            the origine to set
     */
    public void setOrigine(String origine) {
        this.origine = origine;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return this.destination;
    }

    /**
     * @param destination
     *            the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @return the mandatoryBooking
     */
    public String getMandatoryBooking() {
        return this.mandatoryBooking;
    }

    /**
     * @param mandatoryBooking
     *            the mandatoryBooking to set
     */
    public void setMandatoryBooking(String mandatoryBooking) {
        this.mandatoryBooking = mandatoryBooking;
    }

    /**
     * @return the validForRR
     */
    public String getValidForRR() {
        return this.validForRR;
    }

    /**
     * @param validForRR
     *            the validForRR to set
     */
    public void setValidForRR(String validForRR) {
        this.validForRR = validForRR;
    }

    /**
     * @return the numeroTranche
     */
    public String getNumeroTranche() {
        return this.numeroTranche;
    }

    /**
     * @param numeroTranche
     *            the numeroTranche to set
     */
    public void setNumeroTranche(String numeroTranche) {
        this.numeroTranche = numeroTranche;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return this.company;
    }

    /**
     * @param company
     *            the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the indicateurDistribution
     */
    public String getIndicateurDistribution() {
        return this.indicateurDistribution;
    }

    /**
     * @param indicateurDistribution
     *            the indicateurDistribution to set
     */
    public void setIndicateurDistribution(String indicateurDistribution) {
        this.indicateurDistribution = indicateurDistribution;
    }

    /**
     * @return the globalClass
     */
    public HashMap<String, Integer> getGlobalClass() {
        return this.globalClass;
    }

    /**
     * @param globalClass
     *            the globalClass to set
     */
    public void setGlobalClass(HashMap<String, Integer> globalClass) {
        this.globalClass = globalClass;
    }

    /**
     * @return the codeSat
     */
    public String getCodeSat() {
        return this.codeSat;
    }

    /**
     * @param codeSat
     *            the codeSat to set
     */
    public void setCodeSat(String codeSat) {
        this.codeSat = codeSat;
    }

    /**
     * @return the codeTosp
     */
    public String getCodeTosp() {
        return this.codeTosp;
    }

    /**
     * @param codeTosp
     *            the codeTosp to set
     */
    public void setCodeTosp(String codeTosp) {
        this.codeTosp = codeTosp;
    }

    /**
     * @return the loadedOn
     */
    public Date getLoadedOn() {
        return this.loadedOn;
    }

    /**
     * @param loadedOn
     *            the loadedOn to set
     */
    public void setLoadedOn(Date loadedOn) {
        this.loadedOn = loadedOn;
    }

    /**
     * @return the rmCode
     */
    public RMCodeDto getRmCode() {
        return this.rmCode;
    }

    /**
     * @param rmCode
     *            the rmCode to set
     */
    public void setRmCode(RMCodeDto rmCode) {
        this.rmCode = rmCode;
    }

    /**
     * @return the service
     */
    public ServicesDto getService() {
        return this.service;
    }

    /**
     * @param service
     *            the service to set
     */
    public void setService(ServicesDto service) {
        this.service = service;
    }

    /**
     * @return the composition
     */
    public CompositionDto getComposition() {
        return this.composition;
    }

    /**
     * @param composition
     *            the composition to set
     */
    public void setComposition(CompositionDto composition) {
        this.composition = composition;
    }

    /**
     * @return the stops
     */
    public List<StopsDto> getStops() {
        return this.stops;
    }

    /**
     * @param stops
     *            the stops to set
     */
    public void setStops(List<StopsDto> stops) {
        this.stops = stops;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.codeSat == null) ? 0 : this.codeSat.hashCode());
        result = prime * result + ((this.codeTosp == null) ? 0 : this.codeTosp.hashCode());
        result = prime * result + ((this.company == null) ? 0 : this.company.hashCode());
        result = prime * result + ((this.composition == null) ? 0 : this.composition.hashCode());
        result = prime * result + ((this.dateJour == null) ? 0 : this.dateJour.hashCode());
        result = prime * result + ((this.destination == null) ? 0 : this.destination.hashCode());
        result = prime * result + ((this.globalClass == null) ? 0 : this.globalClass.hashCode());
        result = prime * result + ((this.indicateurDistribution == null) ? 0 : this.indicateurDistribution.hashCode());
        result = prime * result + ((this.loadedOn == null) ? 0 : this.loadedOn.hashCode());
        result = prime * result + ((this.mandatoryBooking == null) ? 0 : this.mandatoryBooking.hashCode());
        result = prime * result + ((this.numeroTrain == null) ? 0 : this.numeroTrain.hashCode());
        result = prime * result + ((this.numeroTranche == null) ? 0 : this.numeroTranche.hashCode());
        result = prime * result + ((this.origine == null) ? 0 : this.origine.hashCode());
        result = prime * result + ((this.rmCode == null) ? 0 : this.rmCode.hashCode());
        result = prime * result + ((this.service == null) ? 0 : this.service.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.stops == null) ? 0 : this.stops.hashCode());
        result = prime * result + ((this.validForRR == null) ? 0 : this.validForRR.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TrainTrancheDateDto other = (TrainTrancheDateDto) obj;
        if (this.codeSat == null) {
            if (other.codeSat != null)
                return false;
        }
        else if (!this.codeSat.equals(other.codeSat))
            return false;
        if (this.codeTosp == null) {
            if (other.codeTosp != null)
                return false;
        }
        else if (!this.codeTosp.equals(other.codeTosp))
            return false;
        if (this.company == null) {
            if (other.company != null)
                return false;
        }
        else if (!this.company.equals(other.company))
            return false;
        if (this.composition == null) {
            if (other.composition != null)
                return false;
        }
        else if (!this.composition.equals(other.composition))
            return false;
        if (this.dateJour == null) {
            if (other.dateJour != null)
                return false;
        }
        else if (!this.dateJour.equals(other.dateJour))
            return false;
        if (this.destination == null) {
            if (other.destination != null)
                return false;
        }
        else if (!this.destination.equals(other.destination))
            return false;
        if (this.globalClass == null) {
            if (other.globalClass != null)
                return false;
        }
        else if (!this.globalClass.equals(other.globalClass))
            return false;
        if (this.indicateurDistribution == null) {
            if (other.indicateurDistribution != null)
                return false;
        }
        else if (!this.indicateurDistribution.equals(other.indicateurDistribution))
            return false;
        if (this.loadedOn == null) {
            if (other.loadedOn != null)
                return false;
        }
        else if (!this.loadedOn.equals(other.loadedOn))
            return false;
        if (this.mandatoryBooking == null) {
            if (other.mandatoryBooking != null)
                return false;
        }
        else if (!this.mandatoryBooking.equals(other.mandatoryBooking))
            return false;
        if (this.numeroTrain == null) {
            if (other.numeroTrain != null)
                return false;
        }
        else if (!this.numeroTrain.equals(other.numeroTrain))
            return false;
        if (this.numeroTranche == null) {
            if (other.numeroTranche != null)
                return false;
        }
        else if (!this.numeroTranche.equals(other.numeroTranche))
            return false;
        if (this.origine == null) {
            if (other.origine != null)
                return false;
        }
        else if (!this.origine.equals(other.origine))
            return false;
        if (this.rmCode == null) {
            if (other.rmCode != null)
                return false;
        }
        else if (!this.rmCode.equals(other.rmCode))
            return false;
        if (this.service == null) {
            if (other.service != null)
                return false;
        }
        else if (!this.service.equals(other.service))
            return false;
        if (this.status == null) {
            if (other.status != null)
                return false;
        }
        else if (!this.status.equals(other.status))
            return false;
        if (this.stops == null) {
            if (other.stops != null)
                return false;
        }
        else if (!ListUtils.compareLists(this.stops, other.stops))
            return false;
        if (this.validForRR == null) {
            if (other.validForRR != null)
                return false;
        }
        else if (!this.validForRR.equals(other.validForRR))
            return false;
        return true;
    }

}
