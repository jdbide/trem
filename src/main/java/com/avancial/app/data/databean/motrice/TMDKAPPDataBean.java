package com.avancial.app.data.databean.motrice;

import java.util.Date;
import javax.annotation.concurrent.Immutable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TMDKAPP", schema = "F$MDRP2")
@Immutable
public class TMDKAPPDataBean {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String KAPP_APP;
    private String KAPP_CON;
    private Date KAPP_TRA;
    private Date KAPP_ORI;
    private Date KAPP_DEX;
    private String KAPP_INF;
    private String KAPP_MOD;
    private String KAPP_QAL;
    private String KAPP_SER;
    private String KAPP_TIT;
    private String KAPP_VAL;
    private Long KAPP_VES;
    private Long KAPP_VER;
    private Long KAPP_GLO;
    private String KAPP_USER;
    private Date KAPP_DHDO;
    private Date KAPP_DTR;
    
    public String getKAPP_APP() {
        return this.KAPP_APP;
    }
    public void setKAPP_APP(String kAPP_APP) {
        this.KAPP_APP = kAPP_APP;
    }
    public String getKAPP_CON() {
        return this.KAPP_CON;
    }
    public void setKAPP_CON(String kAPP_CON) {
        this.KAPP_CON = kAPP_CON;
    }
    public Date getKAPP_TRA() {
        return this.KAPP_TRA;
    }
    public void setKAPP_TRA(Date kAPP_TRA) {
        this.KAPP_TRA = kAPP_TRA;
    }
    public Date getKAPP_ORI() {
        return this.KAPP_ORI;
    }
    public void setKAPP_ORI(Date kAPP_ORI) {
        this.KAPP_ORI = kAPP_ORI;
    }
    public Date getKAPP_DEX() {
        return this.KAPP_DEX;
    }
    public void setKAPP_DEX(Date kAPP_DEX) {
        this.KAPP_DEX = kAPP_DEX;
    }
    public String getKAPP_INF() {
        return this.KAPP_INF;
    }
    public void setKAPP_INF(String kAPP_INF) {
        this.KAPP_INF = kAPP_INF;
    }
    public String getKAPP_MOD() {
        return this.KAPP_MOD;
    }
    public void setKAPP_MOD(String kAPP_MOD) {
        this.KAPP_MOD = kAPP_MOD;
    }
    public String getKAPP_QAL() {
        return this.KAPP_QAL;
    }
    public void setKAPP_QAL(String kAPP_QAL) {
        this.KAPP_QAL = kAPP_QAL;
    }
    public String getKAPP_SER() {
        return this.KAPP_SER;
    }
    public void setKAPP_SER(String kAPP_SER) {
        this.KAPP_SER = kAPP_SER;
    }
    public String getKAPP_TIT() {
        return this.KAPP_TIT;
    }
    public void setKAPP_TIT(String kAPP_TIT) {
        this.KAPP_TIT = kAPP_TIT;
    }
    public String getKAPP_VAL() {
        return this.KAPP_VAL;
    }
    public void setKAPP_VAL(String kAPP_VAL) {
        this.KAPP_VAL = kAPP_VAL;
    }
    public Long getKAPP_VES() {
        return this.KAPP_VES;
    }
    public void setKAPP_VES(Long kAPP_VES) {
        this.KAPP_VES = kAPP_VES;
    }
    public Long getKAPP_VER() {
        return this.KAPP_VER;
    }
    public void setKAPP_VER(Long kAPP_VER) {
        this.KAPP_VER = kAPP_VER;
    }
    public Long getKAPP_GLO() {
        return this.KAPP_GLO;
    }
    public void setKAPP_GLO(Long kAPP_GLO) {
        this.KAPP_GLO = kAPP_GLO;
    }
    public String getKAPP_USER() {
        return this.KAPP_USER;
    }
    public void setKAPP_USER(String kAPP_USER) {
        this.KAPP_USER = kAPP_USER;
    }
    public Date getKAPP_DHDO() {
        return this.KAPP_DHDO;
    }
    public void setKAPP_DHDO(Date kAPP_DHDO) {
        this.KAPP_DHDO = kAPP_DHDO;
    }
    public Date getKAPP_DTR() {
        return this.KAPP_DTR;
    }
    public void setKAPP_DTR(Date kAPP_DTR) {
        this.KAPP_DTR = kAPP_DTR;
    }
}
