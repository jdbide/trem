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
 * Objet métier représentant une page de l'ihm
 * 
 * @author bruno.legloahec
 *
 */
@MappedSuperclass
public class Page {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "idIhmPage")
   private Long    id;
   @Column(name = "libelleIhmPage")
   private String  libelle;
   @Column(name = "toutRoleIhmPage")
   private boolean toutRole;
   @Column(name = "publiqueIhmPage")
   private boolean publique;
   @Column(name = "actifIhmPage")
   private boolean actif;
   @Column(name = "ordreIhmPage")
   private Long    ordre;
   @Column(name = "lienIhmPage")
   private String  lien;
   @Column(name = "idIhmChapitre")
   private Long    idChapitre;

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

   public String getLien() {
      return this.lien;
   }

   public void setLien(String lien) {
      this.lien = lien;
   }

   public Long getIdChapitre() {
      return this.idChapitre;
   }

   public void setIdChapitre(Long idChapitre) {
      this.idChapitre = idChapitre;
   }

   public boolean isToutRole() {
      return this.toutRole;
   }

   public void setToutRole(boolean toutRole) {
      this.toutRole = toutRole;
   }

   public boolean isPublique() {
      return this.publique;
   }

   public void setPublique(boolean publique) {
      this.publique = publique;
   }
}
