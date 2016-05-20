package com.avancial.app.data.model.databean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TMDAVTR")
public class TMDAVTRInternDataBean  {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idTMDAVTR;
    private String AVTR_TRA1_COD_CIE;
    private String AVTR_TRA1_NUM_TRA1;
    private String AVTR_TRA1_IND_FER;
    private String AVTR_LIBS_AVAL_COD;
    private String AVTR_USER;
    private Date AVTR_DHDO;
    
    public String getAVTR_TRA1_COD_CIE() {
        return this.AVTR_TRA1_COD_CIE;
    }
    public void setAVTR_TRA1_COD_CIE(String aVTR_TRA1_COD_CIE) {
        this.AVTR_TRA1_COD_CIE = aVTR_TRA1_COD_CIE;
    }
    public String getAVTR_TRA1_NUM_TRA1() {
        return this.AVTR_TRA1_NUM_TRA1;
    }
    public void setAVTR_TRA1_NUM_TRA1(String aVTR_TRA1_NUM_TRA1) {
        this.AVTR_TRA1_NUM_TRA1 = aVTR_TRA1_NUM_TRA1;
    }
    public String getAVTR_TRA1_IND_FER() {
        return this.AVTR_TRA1_IND_FER;
    }
    public void setAVTR_TRA1_IND_FER(String aVTR_TRA1_IND_FER) {
        this.AVTR_TRA1_IND_FER = aVTR_TRA1_IND_FER;
    }
    public String getAVTR_LIBS_AVAL_COD() {
        return this.AVTR_LIBS_AVAL_COD;
    }
    public void setAVTR_LIBS_AVAL_COD(String aVTR_LIBS_AVAL_COD) {
        this.AVTR_LIBS_AVAL_COD = aVTR_LIBS_AVAL_COD;
    }
    public String getAVTR_USER() {
        return this.AVTR_USER;
    }
    public void setAVTR_USER(String aVTR_USER) {
        this.AVTR_USER = aVTR_USER;
    }
    public Date getAVTR_DHDO() {
        return this.AVTR_DHDO;
    }
    public void setAVTR_DHDO(Date aVTR_DHDO) {
        this.AVTR_DHDO = aVTR_DHDO;
    }
    public Long getIdTMDAVTR() {
        return this.idTMDAVTR;
    }
    public void setIdTMDAVTR(Long idTMDAVTR) {
        this.idTMDAVTR = idTMDAVTR;
    }
    
    
}
