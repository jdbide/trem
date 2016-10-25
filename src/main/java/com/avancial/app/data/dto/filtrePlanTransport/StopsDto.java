/**
 * 
 */
package com.avancial.app.data.dto.filtrePlanTransport;

import java.util.Date;

/**
 * @author hamza.laterem
 *
 */
public class StopsDto {
    private String cityCode;
    private String libelle;
    private Date arrivalTime;
    private Date DepartureTime;
    private String offForbidden;
    private String onForbidden;

    public StopsDto() {
        super();
    }

    /**
     * @return the cityCode
     */
    public String getCityCode() {
        return this.cityCode;
    }

    /**
     * @param cityCode
     *            the cityCode to set
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * @return the libelle
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * @param libelle
     *            the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * @return the arrivalTime
     */
    public Date getArrivalTime() {
        return this.arrivalTime;
    }

    /**
     * @param arrivalTime
     *            the arrivalTime to set
     */
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * @return the departureTime
     */
    public Date getDepartureTime() {
        return this.DepartureTime;
    }

    /**
     * @param departureTime
     *            the departureTime to set
     */
    public void setDepartureTime(Date departureTime) {
        this.DepartureTime = departureTime;
    }

    /**
     * @return the offForbidden
     */
    public String getOffForbidden() {
        return this.offForbidden;
    }

    /**
     * @param offForbidden
     *            the offForbidden to set
     */
    public void setOffForbidden(String offForbidden) {
        this.offForbidden = offForbidden;
    }

    /**
     * @return the onForbidden
     */
    public String getOnForbidden() {
        return this.onForbidden;
    }

    /**
     * @param onForbidden
     *            the onForbidden to set
     */
    public void setOnForbidden(String onForbidden) {
        this.onForbidden = onForbidden;
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
        result = prime * result + ((this.DepartureTime == null) ? 0 : this.DepartureTime.hashCode());
        result = prime * result + ((this.arrivalTime == null) ? 0 : this.arrivalTime.hashCode());
        result = prime * result + ((this.cityCode == null) ? 0 : this.cityCode.hashCode());
        result = prime * result + ((this.libelle == null) ? 0 : this.libelle.hashCode());
        result = prime * result + ((this.offForbidden == null) ? 0 : this.offForbidden.hashCode());
        result = prime * result + ((this.onForbidden == null) ? 0 : this.onForbidden.hashCode());
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
        StopsDto other = (StopsDto) obj;
        if (this.DepartureTime == null) {
            if (other.DepartureTime != null)
                return false;
        }
        else if (!this.DepartureTime.equals(other.DepartureTime))
            return false;
        if (this.arrivalTime == null) {
            if (other.arrivalTime != null)
                return false;
        }
        else if (!this.arrivalTime.equals(other.arrivalTime))
            return false;
        if (this.cityCode == null) {
            if (other.cityCode != null)
                return false;
        }
        else if (!this.cityCode.equals(other.cityCode))
            return false;
        if (this.libelle == null) {
            if (other.libelle != null)
                return false;
        }
        else if (!this.libelle.equals(other.libelle))
            return false;
        if (this.offForbidden == null) {
            if (other.offForbidden != null)
                return false;
        }
        else if (!this.offForbidden.equals(other.offForbidden))
            return false;
        if (this.onForbidden == null) {
            if (other.onForbidden != null)
                return false;
        }
        else if (!this.onForbidden.equals(other.onForbidden))
            return false;
        return true;
    }

}
