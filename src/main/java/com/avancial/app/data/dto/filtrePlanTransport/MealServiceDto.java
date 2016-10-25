/**
 * 
 */
package com.avancial.app.data.dto.filtrePlanTransport;

import java.util.Date;

/**
 * @author hamza.laterem
 *
 */
public class MealServiceDto {
    private String numeroCoach;
    private String mealType;
    private String mealCode;
    private String type;
    private Date starting;
    private Date ending;

    /**
     * constructor vide
     */
    public MealServiceDto() {
        super();
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
     * @return the mealType
     */
    public String getMealType() {
        return this.mealType;
    }

    /**
     * @param mealType
     *            the mealType to set
     */
    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    /**
     * @return the mealCode
     */
    public String getMealCode() {
        return this.mealCode;
    }

    /**
     * @param mealCode
     *            the mealCode to set
     */
    public void setMealCode(String mealCode) {
        this.mealCode = mealCode;
    }

    /**
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the starting
     */
    public Date getStarting() {
        return this.starting;
    }

    /**
     * @param starting
     *            the starting to set
     */
    public void setStarting(Date starting) {
        this.starting = starting;
    }

    /**
     * @return the ending
     */
    public Date getEnding() {
        return this.ending;
    }

    /**
     * @param ending
     *            the ending to set
     */
    public void setEnding(Date ending) {
        this.ending = ending;
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
        result = prime * result + ((this.ending == null) ? 0 : this.ending.hashCode());
        result = prime * result + ((this.mealCode == null) ? 0 : this.mealCode.hashCode());
        result = prime * result + ((this.mealType == null) ? 0 : this.mealType.hashCode());
        result = prime * result + ((this.numeroCoach == null) ? 0 : this.numeroCoach.hashCode());
        result = prime * result + ((this.starting == null) ? 0 : this.starting.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
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
        MealServiceDto other = (MealServiceDto) obj;
        if (this.ending == null) {
            if (other.ending != null)
                return false;
        }
        else if (!this.ending.equals(other.ending))
            return false;
        if (this.mealCode == null) {
            if (other.mealCode != null)
                return false;
        }
        else if (!this.mealCode.equals(other.mealCode))
            return false;
        if (this.mealType == null) {
            if (other.mealType != null)
                return false;
        }
        else if (!this.mealType.equals(other.mealType))
            return false;
        if (this.numeroCoach == null) {
            if (other.numeroCoach != null)
                return false;
        }
        else if (!this.numeroCoach.equals(other.numeroCoach))
            return false;
        if (this.starting == null) {
            if (other.starting != null)
                return false;
        }
        else if (!this.starting.equals(other.starting))
            return false;
        if (this.type == null) {
            if (other.type != null)
                return false;
        }
        else if (!this.type.equals(other.type))
            return false;
        return true;
    }

}
