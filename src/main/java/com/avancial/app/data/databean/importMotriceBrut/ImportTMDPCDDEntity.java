package com.avancial.app.data.databean.importMotriceBrut;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDPCDD")
@NamedQuery(name = "ImportTMDPCDD.getAll", query = "SELECT t FROM ImportTMDPCDDEntity t")
public class ImportTMDPCDDEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idTMDPCDD;
    private String PCDD_COMP_COD_DIAG;
    private String PCDD_COMP_NUM_COMP;
    private String PCDD_NUM_PLACE;
    private String PCDD_PAPL_COD;
    private String PCDD_USER;
    private String PCDD_DHDO;
    private String PCDD_MVT_MAJ;
    private String PCDD_CLBA_COD;

    /**
     * @return the idTMDPCDD
     */
    public Long getIdTMDPCDD() {
        return this.idTMDPCDD;
    }

    /**
     * @param idTMDPCDD
     *            the idTMDPCDD to set
     */
    public void setIdTMDPCDD(Long idTMDPCDD) {
        this.idTMDPCDD = idTMDPCDD;
    }

    /**
     * @return the pCDD_COMP_COD_DIAG
     */
    public String getPCDD_COMP_COD_DIAG() {
        return this.PCDD_COMP_COD_DIAG;
    }

    /**
     * @param pCDD_COMP_COD_DIAG
     *            the pCDD_COMP_COD_DIAG to set
     */
    public void setPCDD_COMP_COD_DIAG(String pCDD_COMP_COD_DIAG) {
        this.PCDD_COMP_COD_DIAG = pCDD_COMP_COD_DIAG;
    }

    /**
     * @return the pCDD_COMP_NUM_COMP
     */
    public String getPCDD_COMP_NUM_COMP() {
        return this.PCDD_COMP_NUM_COMP;
    }

    /**
     * @param pCDD_COMP_NUM_COMP
     *            the pCDD_COMP_NUM_COMP to set
     */
    public void setPCDD_COMP_NUM_COMP(String pCDD_COMP_NUM_COMP) {
        this.PCDD_COMP_NUM_COMP = pCDD_COMP_NUM_COMP;
    }

    /**
     * @return the pCDD_NUM_PLACE
     */
    public String getPCDD_NUM_PLACE() {
        return this.PCDD_NUM_PLACE;
    }

    /**
     * @param pCDD_NUM_PLACE
     *            the pCDD_NUM_PLACE to set
     */
    public void setPCDD_NUM_PLACE(String pCDD_NUM_PLACE) {
        this.PCDD_NUM_PLACE = pCDD_NUM_PLACE;
    }

    /**
     * @return the pCDD_PAPL_COD
     */
    public String getPCDD_PAPL_COD() {
        return this.PCDD_PAPL_COD;
    }

    /**
     * @param pCDD_PAPL_COD
     *            the pCDD_PAPL_COD to set
     */
    public void setPCDD_PAPL_COD(String pCDD_PAPL_COD) {
        this.PCDD_PAPL_COD = pCDD_PAPL_COD;
    }

    /**
     * @return the pCDD_USER
     */
    public String getPCDD_USER() {
        return this.PCDD_USER;
    }

    /**
     * @param pCDD_USER
     *            the pCDD_USER to set
     */
    public void setPCDD_USER(String pCDD_USER) {
        this.PCDD_USER = pCDD_USER;
    }

    /**
     * @return the pCDD_DHDO
     */
    public String getPCDD_DHDO() {
        return this.PCDD_DHDO;
    }

    /**
     * @param pCDD_DHDO
     *            the pCDD_DHDO to set
     */
    public void setPCDD_DHDO(String pCDD_DHDO) {
        this.PCDD_DHDO = pCDD_DHDO;
    }

    /**
     * @return the pCDD_MVT_MAJ
     */
    public String getPCDD_MVT_MAJ() {
        return this.PCDD_MVT_MAJ;
    }

    /**
     * @param pCDD_MVT_MAJ
     *            the pCDD_MVT_MAJ to set
     */
    public void setPCDD_MVT_MAJ(String pCDD_MVT_MAJ) {
        this.PCDD_MVT_MAJ = pCDD_MVT_MAJ;
    }

    /**
     * @return the pCDD_CLBA_COD
     */
    public String getPCDD_CLBA_COD() {
        return this.PCDD_CLBA_COD;
    }

    /**
     * @param pCDD_CLBA_COD
     *            the pCDD_CLBA_COD to set
     */
    public void setPCDD_CLBA_COD(String pCDD_CLBA_COD) {
        this.PCDD_CLBA_COD = pCDD_CLBA_COD;
    }

}
