/**
 * 
 */
package com.avancial.app.data.dto.filtrePlanTransport;

import java.util.List;
import com.avancial.socle.utils.ListUtils;

/**
 * @author hamza.laterem
 *
 */
public class ServicesDto {
    private String codeEquipement;
    List<ServiceABoardDto> services;
    List<MealServiceDto> mealServices;

    public ServicesDto() {
    }

    /**
     * @return the codeEquipement
     */
    public String getCodeEquipement() {
        return this.codeEquipement;
    }

    /**
     * @param codeEquipement
     *            the codeEquipement to set
     */
    public void setCodeEquipement(String codeEquipement) {
        this.codeEquipement = codeEquipement;
    }

    /**
     * @return the services
     */
    public List<ServiceABoardDto> getServices() {
        return this.services;
    }

    /**
     * @param services
     *            the services to set
     */
    public void setServices(List<ServiceABoardDto> services) {
        this.services = services;
    }

    /**
     * @return the mealServices
     */
    public List<MealServiceDto> getMealServices() {
        return this.mealServices;
    }

    /**
     * @param mealServices
     *            the mealServices to set
     */
    public void setMealServices(List<MealServiceDto> mealServices) {
        this.mealServices = mealServices;
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
        result = prime * result + ((this.codeEquipement == null) ? 0 : this.codeEquipement.hashCode());
        result = prime * result + ((this.mealServices == null) ? 0 : this.mealServices.hashCode());
        result = prime * result + ((this.services == null) ? 0 : this.services.hashCode());
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
        ServicesDto other = (ServicesDto) obj;
        if (this.codeEquipement == null) {
            if (other.codeEquipement != null)
                return false;
        }
        else if (!this.codeEquipement.equals(other.codeEquipement))
            return false;
        if (this.mealServices == null) {
            if (other.mealServices != null)
                return false;
        }
        else if (!ListUtils.compareLists(this.mealServices, (other.mealServices)))
            return false;
        if (this.services == null) {
            if (other.services != null)
                return false;
        }
        else if (!ListUtils.compareLists(this.services, (other.services)))
            return false;
        return true;
    }

}