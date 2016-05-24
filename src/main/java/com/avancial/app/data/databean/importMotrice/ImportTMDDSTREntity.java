package com.avancial.app.data.databean.importMotrice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_import_TMDDSTR")
@NamedQuery(name = "ImportTMDDSTR.getAll", query= "SELECT t FROM ImportTMDDSTREntity t")
public class ImportTMDDSTREntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String DSTR_TRA1_COD_CIE;
    private String DSTR_TRA1_NUM_TRA1;
    private String DSTR_TRA1_IND_FER;
    private Double DSTR_NUM;
    private String DSTR_REGI;
    private String DSTR_ETAT_TRA1;
    private String DSTR_COD_MISSION;
    private String DSTR_TYP_DES;
    private String DSTR_REF_IHM;
    private String DSTR_LIBS_IND_JALO;
    private String DSTR_USER;
    private Date DSTR_DHDO;
    
    public String getDSTR_TRA1_COD_CIE() {
        return this.DSTR_TRA1_COD_CIE;
    }
    public void setDSTR_TRA1_COD_CIE(String dSTR_TRA1_COD_CIE) {
        this.DSTR_TRA1_COD_CIE = dSTR_TRA1_COD_CIE;
    }
    public String getDSTR_TRA1_NUM_TRA1() {
        return this.DSTR_TRA1_NUM_TRA1;
    }
    public void setDSTR_TRA1_NUM_TRA1(String dSTR_TRA1_NUM_TRA1) {
        this.DSTR_TRA1_NUM_TRA1 = dSTR_TRA1_NUM_TRA1;
    }
    public String getDSTR_TRA1_IND_FER() {
        return this.DSTR_TRA1_IND_FER;
    }
    public void setDSTR_TRA1_IND_FER(String dSTR_TRA1_IND_FER) {
        this.DSTR_TRA1_IND_FER = dSTR_TRA1_IND_FER;
    }
    public Double getDSTR_NUM() {
        return this.DSTR_NUM;
    }
    public void setDSTR_NUM(Double dSTR_NUM) {
        this.DSTR_NUM = dSTR_NUM;
    }
    public String getDSTR_REGI() {
        return this.DSTR_REGI;
    }
    public void setDSTR_REGI(String dSTR_REGI) {
        this.DSTR_REGI = dSTR_REGI;
    }
    public String getDSTR_ETAT_TRA1() {
        return this.DSTR_ETAT_TRA1;
    }
    public void setDSTR_ETAT_TRA1(String dSTR_ETAT_TRA1) {
        this.DSTR_ETAT_TRA1 = dSTR_ETAT_TRA1;
    }
    public String getDSTR_COD_MISSION() {
        return this.DSTR_COD_MISSION;
    }
    public void setDSTR_COD_MISSION(String dSTR_COD_MISSION) {
        this.DSTR_COD_MISSION = dSTR_COD_MISSION;
    }
    public String getDSTR_TYP_DES() {
        return this.DSTR_TYP_DES;
    }
    public void setDSTR_TYP_DES(String dSTR_TYP_DES) {
        this.DSTR_TYP_DES = dSTR_TYP_DES;
    }
    public String getDSTR_REF_IHM() {
        return this.DSTR_REF_IHM;
    }
    public void setDSTR_REF_IHM(String dSTR_REF_IHM) {
        this.DSTR_REF_IHM = dSTR_REF_IHM;
    }
    public String getDSTR_LIBS_IND_JALO() {
        return this.DSTR_LIBS_IND_JALO;
    }
    public void setDSTR_LIBS_IND_JALO(String dSTR_LIBS_IND_JALO) {
        this.DSTR_LIBS_IND_JALO = dSTR_LIBS_IND_JALO;
    }
    public String getDSTR_USER() {
        return this.DSTR_USER;
    }
    public void setDSTR_USER(String dSTR_USER) {
        this.DSTR_USER = dSTR_USER;
    }
    public Date getDSTR_DHDO() {
        return this.DSTR_DHDO;
    }
    public void setDSTR_DHDO(Date dSTR_DHDO) {
        this.DSTR_DHDO = dSTR_DHDO;
    }
}
