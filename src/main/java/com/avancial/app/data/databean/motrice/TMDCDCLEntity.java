package com.avancial.app.data.databean.motrice;

import java.util.Date;

import javax.annotation.concurrent.Immutable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TMDCDCLEntityId.class)
@Table(name = "TMDCDCL", schema = "F$MDRP1")
@Immutable
public class TMDCDCLEntity {

    @Id
    private String CDCL_CDEM_COD_CIE;
    @Id
    private String CDCL_CDEM_NUM_TRA1;
    @Id
    private String CDCL_CDEM_IND_FER;
    @Id
    private Long CDCL_CDEM_NUM_COND;
    @Id
    private String CDCL_CLBA_COD;
    private String CDCL_USER;
    private Date CDCL_DHDO;
    
    public String getCDCL_CDEM_COD_CIE() {
        return this.CDCL_CDEM_COD_CIE;
    }
    public void setCDCL_CDEM_COD_CIE(String cDCL_CDEM_COD_CIE) {
        this.CDCL_CDEM_COD_CIE = cDCL_CDEM_COD_CIE;
    }
    public String getCDCL_CDEM_NUM_TRA1() {
        return this.CDCL_CDEM_NUM_TRA1;
    }
    public void setCDCL_CDEM_NUM_TRA1(String cDCL_CDEM_NUM_TRA1) {
        this.CDCL_CDEM_NUM_TRA1 = cDCL_CDEM_NUM_TRA1;
    }
    public String getCDCL_CDEM_IND_FER() {
        return this.CDCL_CDEM_IND_FER;
    }
    public void setCDCL_CDEM_IND_FER(String cDCL_CDEM_IND_FER) {
        this.CDCL_CDEM_IND_FER = cDCL_CDEM_IND_FER;
    }
    public Long getCDCL_CDEM_NUM_COND() {
        return this.CDCL_CDEM_NUM_COND;
    }
    public void setCDCL_CDEM_NUM_COND(Long cDCL_CDEM_NUM_COND) {
        this.CDCL_CDEM_NUM_COND = cDCL_CDEM_NUM_COND;
    }
    public String getCDCL_CLBA_COD() {
        return this.CDCL_CLBA_COD;
    }
    public void setCDCL_CLBA_COD(String cDCL_CLBA_COD) {
        this.CDCL_CLBA_COD = cDCL_CLBA_COD;
    }
    public String getCDCL_USER() {
        return this.CDCL_USER;
    }
    public void setCDCL_USER(String cDCL_USER) {
        this.CDCL_USER = cDCL_USER;
    }
    public Date getCDCL_DHDO() {
        return this.CDCL_DHDO;
    }
    public void setCDCL_DHDO(Date cDCL_DHDO) {
        this.CDCL_DHDO = cDCL_DHDO;
    }
}
