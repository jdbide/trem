package com.avancial.app.data.databean.importMotriceBrut;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDTYVO")
@NamedQuery(name = "ImportTMDTYVO.getAll", query = "SELECT t FROM ImportTMDTYVOEntity t")
public class ImportTMDTYVOEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idTMDTYVO;
    private String TYVO_NUM_TYP;
    private String TYVO_DIAV_COD;
    private String TYVO_MARV_COD_MARQ;
    private String TYVO_CIES_COD_CIE;
    private String TYVO_TYEQ_COD;
    private String TYVO_PART_TYP;
    private String TYVO_USER;
    private String TYVO_DHDO;
    private String TYVO_MVT_MAJ;
    private String TYVO_DAT_PEREMP;

    /**
     * @return the idTMDPCDD
     */
    public Long getIdTMDTYVO() {
        return this.idTMDTYVO;
    }

    /**
     * @param idTMDPCDD
     *            the idTMDPCDD to set
     */
    public void setIdTMDTYVO(Long idTMDTYVO) {
        this.idTMDTYVO = idTMDTYVO;
    }

    /**
     * @return the tYVO_NUM_TYP
     */
    public String getTYVO_NUM_TYP() {
        return this.TYVO_NUM_TYP;
    }

    /**
     * @param tYVO_NUM_TYP
     *            the tYVO_NUM_TYP to set
     */
    public void setTYVO_NUM_TYP(String tYVO_NUM_TYP) {
        this.TYVO_NUM_TYP = tYVO_NUM_TYP;
    }

    /**
     * @return the tYVO_DIAV_COD
     */
    public String getTYVO_DIAV_COD() {
        return this.TYVO_DIAV_COD;
    }

    /**
     * @param tYVO_DIAV_COD
     *            the tYVO_DIAV_COD to set
     */
    public void setTYVO_DIAV_COD(String tYVO_DIAV_COD) {
        this.TYVO_DIAV_COD = tYVO_DIAV_COD;
    }

    /**
     * @return the tYVO_MARV_COD_MARQ
     */
    public String getTYVO_MARV_COD_MARQ() {
        return this.TYVO_MARV_COD_MARQ;
    }

    /**
     * @param tYVO_MARV_COD_MARQ
     *            the tYVO_MARV_COD_MARQ to set
     */
    public void setTYVO_MARV_COD_MARQ(String tYVO_MARV_COD_MARQ) {
        this.TYVO_MARV_COD_MARQ = tYVO_MARV_COD_MARQ;
    }

    /**
     * @return the tYVO_CIES_COD_CIE
     */
    public String getTYVO_CIES_COD_CIE() {
        return this.TYVO_CIES_COD_CIE;
    }

    /**
     * @param tYVO_CIES_COD_CIE
     *            the tYVO_CIES_COD_CIE to set
     */
    public void setTYVO_CIES_COD_CIE(String tYVO_CIES_COD_CIE) {
        this.TYVO_CIES_COD_CIE = tYVO_CIES_COD_CIE;
    }

    /**
     * @return the tYVO_TYEQ_COD
     */
    public String getTYVO_TYEQ_COD() {
        return this.TYVO_TYEQ_COD;
    }

    /**
     * @param tYVO_TYEQ_COD
     *            the tYVO_TYEQ_COD to set
     */
    public void setTYVO_TYEQ_COD(String tYVO_TYEQ_COD) {
        this.TYVO_TYEQ_COD = tYVO_TYEQ_COD;
    }

    /**
     * @return the tYVO_PART_TYP
     */
    public String getTYVO_PART_TYP() {
        return this.TYVO_PART_TYP;
    }

    /**
     * @param tYVO_PART_TYP
     *            the tYVO_PART_TYP to set
     */
    public void setTYVO_PART_TYP(String tYVO_PART_TYP) {
        this.TYVO_PART_TYP = tYVO_PART_TYP;
    }

    /**
     * @return the tYVO_USER
     */
    public String getTYVO_USER() {
        return this.TYVO_USER;
    }

    /**
     * @param tYVO_USER
     *            the tYVO_USER to set
     */
    public void setTYVO_USER(String tYVO_USER) {
        this.TYVO_USER = tYVO_USER;
    }

    /**
     * @return the tYVO_DHDO
     */
    public String getTYVO_DHDO() {
        return this.TYVO_DHDO;
    }

    /**
     * @param tYVO_DHDO
     *            the tYVO_DHDO to set
     */
    public void setTYVO_DHDO(String tYVO_DHDO) {
        this.TYVO_DHDO = tYVO_DHDO;
    }

    /**
     * @return the tYVO_MVT_MAJ
     */
    public String getTYVO_MVT_MAJ() {
        return this.TYVO_MVT_MAJ;
    }

    /**
     * @param tYVO_MVT_MAJ
     *            the tYVO_MVT_MAJ to set
     */
    public void setTYVO_MVT_MAJ(String tYVO_MVT_MAJ) {
        this.TYVO_MVT_MAJ = tYVO_MVT_MAJ;
    }

    /**
     * @return the tYVO_DAT_PEREMP
     */
    public String getTYVO_DAT_PEREMP() {
        return this.TYVO_DAT_PEREMP;
    }

    /**
     * @param tYVO_DAT_PEREMP
     *            the tYVO_DAT_PEREMP to set
     */
    public void setTYVO_DAT_PEREMP(String tYVO_DAT_PEREMP) {
        this.TYVO_DAT_PEREMP = tYVO_DAT_PEREMP;
    }

}
