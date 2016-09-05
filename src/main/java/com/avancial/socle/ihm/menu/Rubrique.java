package com.avancial.socle.ihm.menu;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Rubrique {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "idIhmRubrique")
   private Long    id;

   @Column(name = "libelleIhmRubrique")
   private String  libelle;
   @Column(name = "actifIhmRubrique")
   private boolean actif;
   @Column(name = "ordreIhmRubrique")
   private Long    ordre;

   public String getLibelle() {
      return this.libelle;
   }

   public void setLibelle(String libelle) {
      this.libelle = libelle;
   }

   public boolean isActif() {
      return this.actif;
   }

   public void setActif(boolean actif) {
      this.actif = actif;
   }

   public Long getOrdre() {
      return this.ordre;
   }

   public void setOrdre(Long ordre) {
      this.ordre = ordre;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

}
