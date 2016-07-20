package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_regime_restriction")
@NamedQuery(name = "MotriceRegimeRestriction.getAll", query = "SELECT t FROM MotriceRegimeRestrictionEntity t")
public class MotriceRegimeRestrictionEntity {
   
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long idMotriceRegimeRestriction;
   
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="idMotriceRegime")
   private MotriceRegimeEntity motriceRegime;

}
