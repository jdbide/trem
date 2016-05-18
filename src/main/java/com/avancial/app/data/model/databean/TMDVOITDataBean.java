package com.avancial.app.data.model.databean;

import java.util.Date;
import javax.annotation.concurrent.Immutable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VMDVOIT0", schema = "F$MDRP2")
@Immutable
public class TMDVOITDataBean {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String VOIT_TRCH_COD_CIE;
    private String VOIT_TRCH_NUM_TRA1;
    private String VOIT_TRCH_IND_FER;
    private Long VOIT_TRCH_NUM;
    private Long VOIT_NUM;
    private String VOIT_NUM_RESA;
    private Long VOIT_NUM_VOIT;
    private String VOIT_COD_ORIG;
    private Long VOIT_SENS_ORIG;
    private Long VOIT_NUM_ORIG;
    private Long VOIT_TYVO_NUM_TYP;
    private String VOIT_CIES_COD_GERE;
    private String VOIT_IND_ORIG;
    private Boolean VOIT_REGI_UTIL;
    private String VOIT_USER;
    private Date VOIT_DHDO;
    private Long VOIT_ROUL_NUM;
    private Long VOIT_INDCE_CLASST;
    
    public String getVOIT_TRCH_COD_CIE() {
        return this.VOIT_TRCH_COD_CIE;
    }
    public void setVOIT_TRCH_COD_CIE(String vOIT_TRCH_COD_CIE) {
        this.VOIT_TRCH_COD_CIE = vOIT_TRCH_COD_CIE;
    }
    public String getVOIT_TRCH_NUM_TRA1() {
        return this.VOIT_TRCH_NUM_TRA1;
    }
    public void setVOIT_TRCH_NUM_TRA1(String vOIT_TRCH_NUM_TRA1) {
        this.VOIT_TRCH_NUM_TRA1 = vOIT_TRCH_NUM_TRA1;
    }
    public String getVOIT_TRCH_IND_FER() {
        return this.VOIT_TRCH_IND_FER;
    }
    public void setVOIT_TRCH_IND_FER(String vOIT_TRCH_IND_FER) {
        this.VOIT_TRCH_IND_FER = vOIT_TRCH_IND_FER;
    }
    public Long getVOIT_TRCH_NUM() {
        return this.VOIT_TRCH_NUM;
    }
    public void setVOIT_TRCH_NUM(Long vOIT_TRCH_NUM) {
        this.VOIT_TRCH_NUM = vOIT_TRCH_NUM;
    }
    public Long getVOIT_NUM() {
        return this.VOIT_NUM;
    }
    public void setVOIT_NUM(Long vOIT_NUM) {
        this.VOIT_NUM = vOIT_NUM;
    }
    public String getVOIT_NUM_RESA() {
        return this.VOIT_NUM_RESA;
    }
    public void setVOIT_NUM_RESA(String vOIT_NUM_RESA) {
        this.VOIT_NUM_RESA = vOIT_NUM_RESA;
    }
    public Long getVOIT_NUM_VOIT() {
        return this.VOIT_NUM_VOIT;
    }
    public void setVOIT_NUM_VOIT(Long vOIT_NUM_VOIT) {
        this.VOIT_NUM_VOIT = vOIT_NUM_VOIT;
    }
    public String getVOIT_COD_ORIG() {
        return this.VOIT_COD_ORIG;
    }
    public void setVOIT_COD_ORIG(String vOIT_COD_ORIG) {
        this.VOIT_COD_ORIG = vOIT_COD_ORIG;
    }
    public Long getVOIT_SENS_ORIG() {
        return this.VOIT_SENS_ORIG;
    }
    public void setVOIT_SENS_ORIG(Long vOIT_SENS_ORIG) {
        this.VOIT_SENS_ORIG = vOIT_SENS_ORIG;
    }
    public Long getVOIT_NUM_ORIG() {
        return this.VOIT_NUM_ORIG;
    }
    public void setVOIT_NUM_ORIG(Long vOIT_NUM_ORIG) {
        this.VOIT_NUM_ORIG = vOIT_NUM_ORIG;
    }
    public Long getVOIT_TYVO_NUM_TYP() {
        return this.VOIT_TYVO_NUM_TYP;
    }
    public void setVOIT_TYVO_NUM_TYP(Long vOIT_TYVO_NUM_TYP) {
        this.VOIT_TYVO_NUM_TYP = vOIT_TYVO_NUM_TYP;
    }
    public String getVOIT_CIES_COD_GERE() {
        return this.VOIT_CIES_COD_GERE;
    }
    public void setVOIT_CIES_COD_GERE(String vOIT_CIES_COD_GERE) {
        this.VOIT_CIES_COD_GERE = vOIT_CIES_COD_GERE;
    }
    public String getVOIT_IND_ORIG() {
        return this.VOIT_IND_ORIG;
    }
    public void setVOIT_IND_ORIG(String vOIT_IND_ORIG) {
        this.VOIT_IND_ORIG = vOIT_IND_ORIG;
    }
    public Boolean getVOIT_REGI_UTIL() {
        return this.VOIT_REGI_UTIL;
    }
    public void setVOIT_REGI_UTIL(Boolean vOIT_REGI_UTIL) {
        this.VOIT_REGI_UTIL = vOIT_REGI_UTIL;
    }
    public String getVOIT_USER() {
        return this.VOIT_USER;
    }
    public void setVOIT_USER(String vOIT_USER) {
        this.VOIT_USER = vOIT_USER;
    }
    public Date getVOIT_DHDO() {
        return this.VOIT_DHDO;
    }
    public void setVOIT_DHDO(Date vOIT_DHDO) {
        this.VOIT_DHDO = vOIT_DHDO;
    }
    public Long getVOIT_ROUL_NUM() {
        return this.VOIT_ROUL_NUM;
    }
    public void setVOIT_ROUL_NUM(Long vOIT_ROUL_NUM) {
        this.VOIT_ROUL_NUM = vOIT_ROUL_NUM;
    }
    public Long getVOIT_INDCE_CLASST() {
        return this.VOIT_INDCE_CLASST;
    }
    public void setVOIT_INDCE_CLASST(Long vOIT_INDCE_CLASST) {
        this.VOIT_INDCE_CLASST = vOIT_INDCE_CLASST;
    }
}
