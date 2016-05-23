package com.avancial.app.data.databean.importMotrice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDRCTH")
public class ImportTMDRCTHDataBean {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idTMDRCTH;
    private String RCTH_TRCH_COD_CIE;
    private String RCTH_TRCH_NUM_TRA1;
    private String RCTH_TRCH_IND_FER;
    private Long RCTH_TRCH_NUM;
    private String RCTH_RESA_TYP;
    private String RCTH_CLBA_COD;
    private String RCTH_INPT_RR_D;
    private Boolean RCTH_REGI;
    private String RCTH_USER;
    private Date RCTH_DHDO;
    
    

   public Long getIdTMDRCTH() {
      return this.idTMDRCTH;
   }

   public void setIdTMDRCTH(Long idTMDRCTH) {
      this.idTMDRCTH = idTMDRCTH;
   }
   public String getRCTH_TRCH_COD_CIE() {
        return this.RCTH_TRCH_COD_CIE;
    }
    public void setRCTH_TRCH_COD_CIE(String rCTH_TRCH_COD_CIE) {
        this.RCTH_TRCH_COD_CIE = rCTH_TRCH_COD_CIE;
    }
    public String getRCTH_TRCH_NUM_TRA1() {
        return this.RCTH_TRCH_NUM_TRA1;
    }
    public void setRCTH_TRCH_NUM_TRA1(String rCTH_TRCH_NUM_TRA1) {
        this.RCTH_TRCH_NUM_TRA1 = rCTH_TRCH_NUM_TRA1;
    }
    public String getRCTH_TRCH_IND_FER() {
        return this.RCTH_TRCH_IND_FER;
    }
    public void setRCTH_TRCH_IND_FER(String rCTH_TRCH_IND_FER) {
        this.RCTH_TRCH_IND_FER = rCTH_TRCH_IND_FER;
    }
    public Long getRCTH_TRCH_NUM() {
        return this.RCTH_TRCH_NUM;
    }
    public void setRCTH_TRCH_NUM(Long rCTH_TRCH_NUM) {
        this.RCTH_TRCH_NUM = rCTH_TRCH_NUM;
    }
    public String getRCTH_RESA_TYP() {
        return this.RCTH_RESA_TYP;
    }
    public void setRCTH_RESA_TYP(String rCTH_RESA_TYP) {
        this.RCTH_RESA_TYP = rCTH_RESA_TYP;
    }
    public String getRCTH_CLBA_COD() {
        return this.RCTH_CLBA_COD;
    }
    public void setRCTH_CLBA_COD(String rCTH_CLBA_COD) {
        this.RCTH_CLBA_COD = rCTH_CLBA_COD;
    }
    public String getRCTH_INPT_RR_D() {
        return this.RCTH_INPT_RR_D;
    }
    public void setRCTH_INPT_RR_D(String rCTH_INPT_RR_D) {
        this.RCTH_INPT_RR_D = rCTH_INPT_RR_D;
    }
    public Boolean getRCTH_REGI() {
        return this.RCTH_REGI;
    }
    public void setRCTH_REGI(Boolean rCTH_REGI) {
        this.RCTH_REGI = rCTH_REGI;
    }
    public String getRCTH_USER() {
        return this.RCTH_USER;
    }
    public void setRCTH_USER(String rCTH_USER) {
        this.RCTH_USER = rCTH_USER;
    }
    public Date getRCTH_DHDO() {
        return this.RCTH_DHDO;
    }
    public void setRCTH_DHDO(Date rCTH_DHDO) {
        this.RCTH_DHDO = rCTH_DHDO;
    }
}