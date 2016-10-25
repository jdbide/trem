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
public class CompositionDto {
    private Integer capacity;
    private List<VoitureDto> voitures;

    /**
     * constructeur vide
     */
    public CompositionDto() {

    }

    /**
     * @return the capacity
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * @param capacity
     *            the capacity to set
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the voitures
     */
    public List<VoitureDto> getVoitures() {
        return voitures;
    }

    /**
     * @param voitures
     *            the voitures to set
     */
    public void setVoitures(List<VoitureDto> voitures) {
        this.voitures = voitures;
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
        result = prime * result + ((this.capacity == null) ? 0 : this.capacity.hashCode());
        result = prime * result + ((this.voitures == null) ? 0 : this.voitures.hashCode());
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
        CompositionDto other = (CompositionDto) obj;
        if (this.capacity == null) {
            if (other.capacity != null)
                return false;
        }
        else if (!this.capacity.equals(other.capacity))
            return false;
        if (this.voitures == null) {
            if (other.voitures != null)
                return false;
        }
        else if (!ListUtils.compareLists(this.voitures, other.voitures))
            return false;
        return true;
    }

}
