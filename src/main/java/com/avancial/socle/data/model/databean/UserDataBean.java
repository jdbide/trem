package com.avancial.socle.data.model.databean;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author caglar.erdogan
 *
 */
@Entity
@Table(name = "socle_user")
@NamedQueries({@NamedQuery(name = "UserDataBean.getAll", query = "SELECT t FROM UserDataBean t"),
        @NamedQuery(name = "UserDataBean.getById",
                query = "SELECT t FROM UserDataBean t WHERE t.idUser = :idUser")})
public class UserDataBean extends AbstractDataBean {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String nomUser;
    private String prenomUser;
    private String loginUser;
    private String passwordUser;
    private String mailUser;
    private String cpUser;
    private String telephonePro1User;
    private String telephonePro2User;
    private String telephonePortable1User;
    private String fax1User;
    private String adresseNumeroRueUser;
    private String adresseNomRueUser;
    private String adresseComplement1User;
    private String adresseComplement2User;
    private String adresseCodePostalUser;
    private String adresseVilleUser;
    private String attribut1User;
    private String attribut2User;
    private String attribut3User;
    private String attribut4User;
    private String attribut5User;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreateUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdateUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastUpdateUtilisateurUser;
    @OneToOne
    @JoinColumn(name = "idUserCreateUser")
    private UserDataBean userCreateUser;
    @OneToOne
    @JoinColumn(name = "idUserUpdateUser")
    private UserDataBean userUpdateUser;
    private boolean robotUser;
    private String tomcatRoleUser;
    private String commentaireUtilisateurUser;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "socle_user2role", joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idRole"))
    private List<RoleDataBean> roles;

    public Long getIdUser() {
        return this.idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getLoginUser() {
        return this.loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getPasswordUser() {
        return this.passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getNomUser() {
        return this.nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return this.prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public String getCpUser() {
        return this.cpUser;
    }

    public void setCpUser(String cpUser) {
        this.cpUser = cpUser;
    }

    public String getMailUser() {
        return this.mailUser;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public String getTelephonePro1User() {
        return this.telephonePro1User;
    }

    public void setTelephonePro1User(String telephonePro1User) {
        this.telephonePro1User = telephonePro1User;
    }

    public String getTelephonePro2User() {
        return this.telephonePro2User;
    }

    public void setTelephonePro2User(String telephonePro2User) {
        this.telephonePro2User = telephonePro2User;
    }

    public String getTelephonePortable1User() {
        return this.telephonePortable1User;
    }

    public void setTelephonePortable1User(String telephonePortable1User) {
        this.telephonePortable1User = telephonePortable1User;
    }

    public String getFax1User() {
        return this.fax1User;
    }

    public void setFax1User(String fax1User) {
        this.fax1User = fax1User;
    }

    public String getAdresseNumeroRueUser() {
        return this.adresseNumeroRueUser;
    }

    public void setAdresseNumeroRueUser(String adresseNumeroRueUser) {
        this.adresseNumeroRueUser = adresseNumeroRueUser;
    }

    public String getAdresseNomRueUser() {
        return this.adresseNomRueUser;
    }

    public void setAdresseNomRueUser(String adresseNomRueUser) {
        this.adresseNomRueUser = adresseNomRueUser;
    }

    public String getAdresseComplement1User() {
        return this.adresseComplement1User;
    }

    public void setAdresseComplement1User(String adresseComplement1User) {
        this.adresseComplement1User = adresseComplement1User;
    }

    public String getAdresseComplement2User() {
        return this.adresseComplement2User;
    }

    public void setAdresseComplement2User(String adresseComplement2User) {
        this.adresseComplement2User = adresseComplement2User;
    }

    public String getAdresseCodepostalUser() {
        return this.adresseCodePostalUser;
    }

    public void setAdresseCodepostalUser(String adresseCodepostalUser) {
        this.adresseCodePostalUser = adresseCodepostalUser;
    }

    public String getAdresseVilleUser() {
        return this.adresseVilleUser;
    }

    public void setAdresseVilleUser(String adresseVilleUser) {
        this.adresseVilleUser = adresseVilleUser;
    }

    public String getAttribut1User() {
        return this.attribut1User;
    }

    public void setAttribut1User(String attribut1User) {
        this.attribut1User = attribut1User;
    }

    public String getAttribut2User() {
        return this.attribut2User;
    }

    public void setAttribut2User(String attribut2User) {
        this.attribut2User = attribut2User;
    }

    public String getAttribut3User() {
        return this.attribut3User;
    }

    public void setAttribut3User(String attribut3User) {
        this.attribut3User = attribut3User;
    }

    public String getAttribut4User() {
        return this.attribut4User;
    }

    public void setAttribut4User(String attribut4User) {
        this.attribut4User = attribut4User;
    }

    public String getAttribut5User() {
        return this.attribut5User;
    }

    public void setAttribut5User(String attribut5User) {
        this.attribut5User = attribut5User;
    }

    public Date getDateCreateUser() {
        return this.dateCreateUser;
    }

    public void setDateCreateUser(Date dateCreateUser) {
        this.dateCreateUser = dateCreateUser;
    }

    public Date getDateUpdateUser() {
        return this.dateUpdateUser;
    }

    public void setDateUpdateUser(Date dateUpdateUser) {
        this.dateUpdateUser = dateUpdateUser;
    }

    public UserDataBean getUserCreateUser() {
        return this.userCreateUser;
    }

    public void setUserCreateUser(UserDataBean userCreateUser) {
        this.userCreateUser = userCreateUser;
    }

    public UserDataBean getUserUpdateUser() {
        return this.userUpdateUser;
    }

    public void setUserUpdateUser(UserDataBean userUpdateUser) {
        this.userUpdateUser = userUpdateUser;
    }

    public boolean getRobotUser() {
        return this.robotUser;
    }

    public void setRobotUser(boolean robotUser) {
        this.robotUser = robotUser;
    }

    public String getTomcatRoleUser() {
        return this.tomcatRoleUser;
    }

    public void setTomcatRoleUser(String tomcatRoleUser) {
        this.tomcatRoleUser = tomcatRoleUser;
    }

    public String getCommentaireUtilisateurUser() {
        return this.commentaireUtilisateurUser;
    }

    public void setCommentaireUtilisateurUser(String commentaireUtilisateurUser) {
        this.commentaireUtilisateurUser = commentaireUtilisateurUser;
    }

    public String getAdresseCodePostalUser() {
        return this.adresseCodePostalUser;
    }

    public void setAdresseCodePostalUser(String adresseCodePostalUser) {
        this.adresseCodePostalUser = adresseCodePostalUser;
    }

    public Date getDateLastUpdateUtilisateurUser() {
        return this.dateLastUpdateUtilisateurUser;
    }

    public void setDateLastUpdateUtilisateurUser(Date dateLastUpdateUtilisateurUser) {
        this.dateLastUpdateUtilisateurUser = dateLastUpdateUtilisateurUser;
    }

    public List<RoleDataBean> getRoles() {
        return this.roles;
    }

    public void setRoles(List<RoleDataBean> roles) {
        this.roles = roles;
    }

    public String getNomComplet() {
        return this.nomUser.toUpperCase() + " " + this.prenomUser;
    }
}