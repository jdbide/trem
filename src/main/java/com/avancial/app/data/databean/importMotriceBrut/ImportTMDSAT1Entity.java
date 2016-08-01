package com.avancial.app.data.databean.importMotriceBrut;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDSAT1")
@NamedQuery(name = "ImportTMDSAT1.getAll", query = "SELECT t FROM ImportTMDSAT1Entity t")
public class ImportTMDSAT1Entity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long   idTMDSAT1;
    private String SAT1_COD_SAT;
    private String SAT1_TYEQ_COD_EQP;
    private String SAT1_NUM;
    private String SAT1_USER;
    private String SAT_DHDO;
    /**
     * @return the idTMDSAT1
     */
    public Long getIdTMDSAT1() {
        return this.idTMDSAT1;
    }
    /**
     * @param idTMDSAT1 the idTMDSAT1 to set
     */
    public void setIdTMDSAT1(Long idTMDSAT1) {
        this.idTMDSAT1 = idTMDSAT1;
    }
    /**
     * @return the sAT1_COD_SAT
     */
    public String getSAT1_COD_SAT() {
        return this.SAT1_COD_SAT;
    }
    /**
     * @param sAT1_COD_SAT the sAT1_COD_SAT to set
     */
    public void setSAT1_COD_SAT(String sAT1_COD_SAT) {
        this.SAT1_COD_SAT = sAT1_COD_SAT;
    }
    /**
     * @return the sAT1_TYEQ_COD_EQP
     */
    public String getSAT1_TYEQ_COD_EQP() {
        return this.SAT1_TYEQ_COD_EQP;
    }
    /**
     * @param sAT1_TYEQ_COD_EQP the sAT1_TYEQ_COD_EQP to set
     */
    public void setSAT1_TYEQ_COD_EQP(String sAT1_TYEQ_COD_EQP) {
        this.SAT1_TYEQ_COD_EQP = sAT1_TYEQ_COD_EQP;
    }
    /**
     * @return the sAT1_NUM
     */
    public String getSAT1_NUM() {
        return this.SAT1_NUM;
    }
    /**
     * @param sAT1_NUM the sAT1_NUM to set
     */
    public void setSAT1_NUM(String sAT1_NUM) {
        this.SAT1_NUM = sAT1_NUM;
    }
    /**
     * @return the sAT1_USER
     */
    public String getSAT1_USER() {
        return this.SAT1_USER;
    }
    /**
     * @param sAT1_USER the sAT1_USER to set
     */
    public void setSAT1_USER(String sAT1_USER) {
        this.SAT1_USER = sAT1_USER;
    }
    /**
     * @return the sAT_DHDO
     */
    public String getSAT_DHDO() {
        return this.SAT_DHDO;
    }
    /**
     * @param sAT_DHDO the sAT_DHDO to set
     */
    public void setSAT_DHDO(String sAT_DHDO) {
        this.SAT_DHDO = sAT_DHDO;
    }
    
    
}
