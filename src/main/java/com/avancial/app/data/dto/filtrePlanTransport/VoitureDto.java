/**
 * 
 */
package com.avancial.app.data.dto.filtrePlanTransport;

import java.util.List;

/**
 * @author hamza.laterem
 *
 */
public class VoitureDto {
    private String numeroCoach;
    private String rameCode;
    private String coachClass;
    private String diagCode;
    private int capacity;
    private List<String> specifications;

    /**
     * contructor
     */
    public VoitureDto() {
    }

    /**
     * @return the numeroCoach
     */
    public String getNumeroCoach() {
        return this.numeroCoach;
    }

    /**
     * @param numeroCoach
     *            the numeroCoach to set
     */
    public void setNumeroCoach(String numeroCoach) {
        this.numeroCoach = numeroCoach;
    }

    /**
     * @return the rameCode
     */
    public String getRameCode() {
        return this.rameCode;
    }

    /**
     * @param rameCode
     *            the rameCode to set
     */
    public void setRameCode(String rameCode) {
        this.rameCode = rameCode;
    }

    /**
     * @return the coachClass
     */
    public String getCoachClass() {
        return this.coachClass;
    }

    /**
     * @param coachClass
     *            the coachClass to set
     */
    public void setCoachClass(String coachClass) {
        this.coachClass = coachClass;
    }

    /**
     * @return the diagCode
     */
    public String getDiagCode() {
        return this.diagCode;
    }

    /**
     * @param diagCode
     *            the diagCode to set
     */
    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * @param capacity
     *            the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the specifications
     */
    public List<String> getSpecifications() {
        return this.specifications;
    }

    /**
     * @param specifications
     *            the specifications to set
     */
    public void setSpecifications(List<String> specifications) {
        this.specifications = specifications;
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
        result = prime * result + this.capacity;
        result = prime * result + ((this.coachClass == null) ? 0 : this.coachClass.hashCode());
        result = prime * result + ((this.diagCode == null) ? 0 : this.diagCode.hashCode());
        result = prime * result + ((this.numeroCoach == null) ? 0 : this.numeroCoach.hashCode());
        result = prime * result + ((this.rameCode == null) ? 0 : this.rameCode.hashCode());
        result = prime * result + ((this.specifications == null) ? 0 : this.specifications.hashCode());
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
        VoitureDto other = (VoitureDto) obj;
        if (this.capacity != other.capacity)
            return false;
        if (this.coachClass == null) {
            if (other.coachClass != null)
                return false;
        }
        else if (!this.coachClass.equals(other.coachClass))
            return false;
        if (this.diagCode == null) {
            if (other.diagCode != null)
                return false;
        }
        else if (!this.diagCode.equals(other.diagCode))
            return false;
        if (this.numeroCoach == null) {
            if (other.numeroCoach != null)
                return false;
        }
        else if (!this.numeroCoach.equals(other.numeroCoach))
            return false;
        if (this.rameCode == null) {
            if (other.rameCode != null)
                return false;
        }
        else if (!this.rameCode.equals(other.rameCode))
            return false;
        if (this.specifications == null) {
            if (other.specifications != null)
                return false;
        }
        else if (!this.specifications.equals(other.specifications))
            return false;
        return true;
    }

}
