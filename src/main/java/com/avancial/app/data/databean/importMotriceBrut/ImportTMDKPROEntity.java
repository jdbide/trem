package com.avancial.app.data.databean.importMotriceBrut;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDKPRO")
@NamedQuery(name = "ImportTMDKPRO.getAll", query = "SELECT t FROM ImportTMDKPROEntity t")
public class ImportTMDKPROEntity {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long   idTMDKPRO;
    private String KPRO_CODE_PROF;
    private String KPRO_ESP_GEN_COD;
    private String KPRO_TYPE_NUM;
    private String KPRO_TAUX_RMP;
    private String KPRO_LST_VOIT;
    private String KPRO_USER;
    private String KPRO_DHDO;
    /**
     * @return the idTMDKPRO
     */
    public Long getIdTMDKPRO() {
        return this.idTMDKPRO;
    }
    /**
     * @param idTMDKPRO the idTMDKPRO to set
     */
    public void setIdTMDKPRO(Long idTMDKPRO) {
        this.idTMDKPRO = idTMDKPRO;
    }
    /**
     * @return the kPRO_CODE_PROF
     */
    public String getKPRO_CODE_PROF() {
        return this.KPRO_CODE_PROF;
    }
    /**
     * @param kPRO_CODE_PROF the kPRO_CODE_PROF to set
     */
    public void setKPRO_CODE_PROF(String kPRO_CODE_PROF) {
        this.KPRO_CODE_PROF = kPRO_CODE_PROF;
    }
    /**
     * @return the kPRO_ESP_GEN_COD
     */
    public String getKPRO_ESP_GEN_COD() {
        return this.KPRO_ESP_GEN_COD;
    }
    /**
     * @param kPRO_ESP_GEN_COD the kPRO_ESP_GEN_COD to set
     */
    public void setKPRO_ESP_GEN_COD(String kPRO_ESP_GEN_COD) {
        this.KPRO_ESP_GEN_COD = kPRO_ESP_GEN_COD;
    }
    /**
     * @return the kPRO_TYPE_NUM
     */
    public String getKPRO_TYPE_NUM() {
        return this.KPRO_TYPE_NUM;
    }
    /**
     * @param kPRO_TYPE_NUM the kPRO_TYPE_NUM to set
     */
    public void setKPRO_TYPE_NUM(String kPRO_TYPE_NUM) {
        this.KPRO_TYPE_NUM = kPRO_TYPE_NUM;
    }
    /**
     * @return the kPRO_TAUX_RMP
     */
    public String getKPRO_TAUX_RMP() {
        return this.KPRO_TAUX_RMP;
    }
    /**
     * @param kPRO_TAUX_RMP the kPRO_TAUX_RMP to set
     */
    public void setKPRO_TAUX_RMP(String kPRO_TAUX_RMP) {
        this.KPRO_TAUX_RMP = kPRO_TAUX_RMP;
    }
    /**
     * @return the kPRO_LST_VOIT
     */
    public String getKPRO_LST_VOIT() {
        return this.KPRO_LST_VOIT;
    }
    /**
     * @param kPRO_LST_VOIT the kPRO_LST_VOIT to set
     */
    public void setKPRO_LST_VOIT(String kPRO_LST_VOIT) {
        this.KPRO_LST_VOIT = kPRO_LST_VOIT;
    }
    /**
     * @return the kPRO_USER
     */
    public String getKPRO_USER() {
        return this.KPRO_USER;
    }
    /**
     * @param kPRO_USER the kPRO_USER to set
     */
    public void setKPRO_USER(String kPRO_USER) {
        this.KPRO_USER = kPRO_USER;
    }
    /**
     * @return the kPRO_DHDO
     */
    public String getKPRO_DHDO() {
        return this.KPRO_DHDO;
    }
    /**
     * @param kPRO_DHDO the kPRO_DHDO to set
     */
    public void setKPRO_DHDO(String kPRO_DHDO) {
        this.KPRO_DHDO = kPRO_DHDO;
    }
    
    

}
