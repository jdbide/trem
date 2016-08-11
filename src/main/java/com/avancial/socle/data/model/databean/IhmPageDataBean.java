package com.avancial.socle.data.model.databean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "socle_ihm_page")
public class IhmPageDataBean extends AbstractDataBean {

   private static final long   serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long                idIhmPage;
   private String              libelleIhmPage;
   private boolean             actifIhmPage;
   private Long                ordreIhmPage;
   private String              lienIhmPage;

   @ManyToOne
   @JoinColumn(name = "idIhmChapitre", nullable = false)
   @JsonIgnore
   private IhmChapitreDataBean ihmChapitreDataBean;

   @OneToMany()
   @JoinTable(name = "socle_ihm_role2page", joinColumns = @JoinColumn(name = "idIhmPage"), inverseJoinColumns = @JoinColumn(name = "idRole"))
   private List<RoleDataBean>  roles;

   public Long getIdIhmPage() {
      return this.idIhmPage;
   }

   public void setIdIhmPage(Long idPage) {
      this.idIhmPage = idPage;
   }

   public String getLibelleIhmPage() {
      return this.libelleIhmPage;
   }

   public void setLibelleIhmPage(String libelleIhmPage) {
      this.libelleIhmPage = libelleIhmPage;
   }

   public boolean isActifIhmPage() {
      return this.actifIhmPage;
   }

   public void setActifIhmPage(boolean actifIhmPage) {
      this.actifIhmPage = actifIhmPage;
   }

   public Long getOrdreIhmPage() {
      return this.ordreIhmPage;
   }

   public void setOrdreIhmPage(Long ordreIhmPage) {
      this.ordreIhmPage = ordreIhmPage;
   }

   public String getLienIhmPage() {
      return this.lienIhmPage;
   }

   public void setLienIhmPage(String lienIhmPage) {
      this.lienIhmPage = lienIhmPage;
   }

   public IhmChapitreDataBean getIhmChapitreDataBean() {
      return this.ihmChapitreDataBean;
   }

   public void setIhmChapitreDataBean(IhmChapitreDataBean ihmChapitreDataBean) {
      this.ihmChapitreDataBean = ihmChapitreDataBean;
   }

   /**
    * @return the roles
    */
   public List<RoleDataBean> getRoles() {
      return roles;
   }

   /**
    * @param roles
    *           the roles to set
    */
   public void setRoles(List<RoleDataBean> roles) {
      this.roles = roles;
   }

}
