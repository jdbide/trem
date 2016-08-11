package com.avancial.socle.data.model.databean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "socle_ihm_rubrique")
public class IhmRubriqueDataBean extends AbstractDataBean {

   private static final long         serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long                      idIhmRubrique;
   private String                    libelleIhmRubrique;
   private boolean                   actifIhmRubrique;
   private Long                      ordreIhmRubrique;

   @OneToMany
   @JoinColumn(name = "idIhmRubrique")
   private List<IhmChapitreDataBean> chapitres;

   public Long getIdRubrique() {
      return this.idIhmRubrique;
   }

   public void setIdRubrique(Long idRubrique) {
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

   public List<IhmChapitreDataBean> getChapitres() {
      return this.chapitres;
   }

   public void setChapitres(List<IhmChapitreDataBean> chapitres) {
      this.chapitres = chapitres;
   }

}
