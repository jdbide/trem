/**
 * 
 */
package com.avancial.app.data.dto.filtrePlanTransport;

/**
 * @author hamza.laterem
 *
 */
public class ServiceABoardDto {
    private String libelle;
    private String code;
    private String classe;
    private String manualAuto;
    private String origine;
    private String destination;

    public ServiceABoardDto() {
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
     * @return the code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the classe
     */
    public String getClasse() {
        return this.classe;
    }

    /**
     * @param classe
     *            the classe to set
     */
    public void setClasse(String classe) {
        this.classe = classe;
    }

    /**
     * @return the manualAuto
     */
    public String getManualAuto() {
        return this.manualAuto;
    }

    /**
     * @param manualAuto
     *            the manualAuto to set
     */
    public void setManualAuto(String manualAuto) {
        this.manualAuto = manualAuto;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.classe == null) ? 0 : this.classe.hashCode());
        result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());
        result = prime * result + ((this.destination == null) ? 0 : this.destination.hashCode());
        result = prime * result + ((this.libelle == null) ? 0 : this.libelle.hashCode());
        result = prime * result + ((this.manualAuto == null) ? 0 : this.manualAuto.hashCode());
        result = prime * result + ((this.origine == null) ? 0 : this.origine.hashCode());
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
        ServiceABoardDto other = (ServiceABoardDto) obj;
        if (this.classe == null) {
            if (other.classe != null)
                return false;
        }
        else if (!this.classe.equals(other.classe))
            return false;
        if (this.code == null) {
            if (other.code != null)
                return false;
        }
        else if (!this.code.equals(other.code))
            return false;
        if (this.destination == null) {
            if (other.destination != null)
                return false;
        }
        else if (!this.destination.equals(other.destination))
            return false;
        if (this.libelle == null) {
            if (other.libelle != null)
                return false;
        }
        else if (!this.libelle.equals(other.libelle))
            return false;
        if (this.manualAuto == null) {
            if (other.manualAuto != null)
                return false;
        }
        else if (!this.manualAuto.equals(other.manualAuto))
            return false;
        if (this.origine == null) {
            if (other.origine != null)
                return false;
        }
        else if (!this.origine.equals(other.origine))
            return false;
        return true;
    }

}
