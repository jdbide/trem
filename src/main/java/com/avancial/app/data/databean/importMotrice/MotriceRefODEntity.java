package com.avancial.app.data.databean.importMotrice;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.avancial.app.data.databean.CompagnieEntity;

@Entity
@Table(name = "tremas_motrice_ref_od")
@NamedQueries({ @NamedQuery(name = "MotriceRefOD.getAll", query = "SELECT t FROM MotriceRefODEntity t"),
      @NamedQuery(name = "MotriceRefOD.getUnique", query = "SELECT t FROM MotriceRefODEntity t where t.codeGareOrigineMotriceRefOd = :codeGareOrigine "
            + "and t.codeGareDestinationMotriceRefOd = :codeGareDestination and t.compagnie = :compagnie"),
      @NamedQuery(name = "MotriceRefOD.getByCompagnie", query = "SELECT t FROM MotriceRefODEntity t where t.compagnie = :compagnie"), })
public class MotriceRefODEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long                    idMotriceRefOd;

   @Column(length = 5, nullable = false)
   private String                  codeGareOrigineMotriceRefOd;

   @Column(length = 5, nullable = false)
   private String                  codeGareDestinationMotriceRefOd;

   @ManyToOne
   @JoinColumn(name = "idCompagnie")
   @ForeignKey(name = "FK_motrice_ref_od_idCompagnie")
   private CompagnieEntity         compagnie;

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "motriceRefODEntity")
   private List<MotriceRefOd2gareEntity> motriceRefOd2gares;

   public Long getIdMotriceRefOd() {
      return this.idMotriceRefOd;
   }

   public void setIdMotriceRefOd(Long idMotriceRefOd) {
      this.idMotriceRefOd = idMotriceRefOd;
   }

   public String getCodeGareOrigineMotriceRefOd() {
      return this.codeGareOrigineMotriceRefOd;
   }

   public void setCodeGareOrigineMotriceRefOd(String codeGareOrigineMotriceRefOd) {
      this.codeGareOrigineMotriceRefOd = codeGareOrigineMotriceRefOd;
   }

   public String getCodeGareDestinationMotriceRefOd() {
      return this.codeGareDestinationMotriceRefOd;
   }

   public void setCodeGareDestinationMotriceRefOd(String codeGareDestinationMotriceRefOd) {
      this.codeGareDestinationMotriceRefOd = codeGareDestinationMotriceRefOd;
   }

   public CompagnieEntity getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(CompagnieEntity compagnie) {
      this.compagnie = compagnie;
   }

   public List<MotriceRefOd2gareEntity> getMotriceRefOd2gares() {
      return this.motriceRefOd2gares;
   }

   public void setMotriceRefOd2gares(List<MotriceRefOd2gareEntity> motriceRefOd2gares) {
      this.motriceRefOd2gares = motriceRefOd2gares;
   }

}
