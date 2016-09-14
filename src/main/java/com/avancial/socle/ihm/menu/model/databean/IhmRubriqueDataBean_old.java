package com.avancial.socle.ihm.menu.model.databean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.avancial.socle.data.model.databean.AbstractDataBean;

@Entity
@Table(name = "socle_ihm_rubrique")
// @NamedQueries({ @NamedQuery(name = "IhmRubriqueDataBean.findAll", query = "from IhmRubriqueDataBean") })
public class IhmRubriqueDataBean_old extends AbstractDataBean {

   private static final long         serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int                       idIhmRubrique;
   @Column(name = "libelleIhmRubrique")
   private String                    libelleIhmRubrique;
   private boolean                   actifIhmRubrique;
   private Long                      ordreIhmRubrique;

   @OneToMany(fetch = FetchType.LAZY)
   @JoinColumn(name = "idIhmRubrique")
   private List<ChapitreDataBean> chapitres;

   public int getIdIhmRubrique() {
      return this.idIhmRubrique;
   }

   public void setIdIhmRubrique(int idRubrique) {
      this.idIhmRubrique = idRubrique;
   }

   public String getLibelleIhmRubrique() {
      return this.libelleIhmRubrique;
   }

   public void setLibelleIhmRubrique(String libelleIhmRubrique) {
      this.libelleIhmRubrique = libelleIhmRubrique;
   }

   public boolean isActifIhmRubrique() {
      return this.actifIhmRubrique;
   }

   public void setActifIhmRubrique(boolean actifIhmRubrique) {
      this.actifIhmRubrique = actifIhmRubrique;
   }

   public Long getOrdreIhmRubrique() {
      return this.ordreIhmRubrique;
   }

   public void setOrdreIhmRubrique(Long ordreIhmRubrique) {
      this.ordreIhmRubrique = ordreIhmRubrique;
   }

   public List<ChapitreDataBean> getChapitres() {
      return this.chapitres;
   }

   public void setChapitres(List<ChapitreDataBean> chapitres) {
      this.chapitres = chapitres;
   }

}
