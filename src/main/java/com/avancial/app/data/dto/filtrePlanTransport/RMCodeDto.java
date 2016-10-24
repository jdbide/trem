/**
 * 
 */
package com.avancial.app.data.dto.filtrePlanTransport;

/**
 * @author hamza.laterem
 *
 */
public class RMCodeDto {
    private String rmCode;
    private String codeRame1;
    private String codeRame2;
    private String fareProfileCode;

    /**
     * constructeur vide
     */
    public RMCodeDto() {
    }

    /**
     * @return the codeRame1
     */
    public String getCodeRame1() {
        return codeRame1;
    }

    /**
     * @param codeRame1
     *            the codeRame1 to set
     */
    public void setCodeRame1(String codeRame1) {
        this.codeRame1 = codeRame1;
    }

    /**
     * @return the codeRame2
     */
    public String getCodeRame2() {
        return codeRame2;
    }

    /**
     * @param codeRame2
     *            the codeRame2 to set
     */
    public void setCodeRame2(String codeRame2) {
        this.codeRame2 = codeRame2;
    }

    /**
     * @return the fareProfileCode
     */
    public String getFareProfileCode() {
        return fareProfileCode;
    }

    /**
     * @param fareProfileCode
     *            the fareProfileCode to set
     */
    public void setFareProfileCode(String fareProfileCode) {
        this.fareProfileCode = fareProfileCode;
    }

    /**
     * @return the rmCode
     */
    public String getRmCode() {
        return rmCode;
    }

    /**
     * @param rmCode
     *            the rmCode to set
     */
    public void setRmCode(String rmCode) {
        this.rmCode = rmCode;
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
        result = prime * result + ((this.codeRame1 == null) ? 0 : this.codeRame1.hashCode());
        result = prime * result + ((this.codeRame2 == null) ? 0 : this.codeRame2.hashCode());
        result = prime * result + ((this.fareProfileCode == null) ? 0 : this.fareProfileCode.hashCode());
        result = prime * result + ((this.rmCode == null) ? 0 : this.rmCode.hashCode());
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
        RMCodeDto other = (RMCodeDto) obj;
        if (this.codeRame1 == null) {
            if (other.codeRame1 != null)
                return false;
        }
        else if (!this.codeRame1.equals(other.codeRame1))
            return false;
        if (this.codeRame2 == null) {
            if (other.codeRame2 != null)
                return false;
        }
        else if (!this.codeRame2.equals(other.codeRame2))
            return false;
        if (this.fareProfileCode == null) {
            if (other.fareProfileCode != null)
                return false;
        }
        else if (!this.fareProfileCode.equals(other.fareProfileCode))
            return false;
        if (this.rmCode == null) {
            if (other.rmCode != null)
                return false;
        }
        else if (!this.rmCode.equals(other.rmCode))
            return false;
        return true;
    }

}
