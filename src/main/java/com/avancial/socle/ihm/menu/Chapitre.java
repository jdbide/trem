/**
 * 
 */
package com.avancial.socle.ihm.menu;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author bruno.legloahec
 *
 */
@MappedSuperclass
public class Chapitre {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "idIhmChapitre")
   private Long    id;
   @Column(name = "libelleIhmChapitre")
   private String  libelle;
   @Column(name = "actifIhmChapitre")
   private boolean actif;
   @Column(name = "ordreIhmChapitre")
   private Long    ordre;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

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

}
