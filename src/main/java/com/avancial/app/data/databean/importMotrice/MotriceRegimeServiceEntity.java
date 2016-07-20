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
@Table(name = "tremas_motrice_regime_service")
@NamedQuery(name = "MotriceRegimeService.getAll", query = "SELECT t FROM MotriceRegimeServiceEntity t")
public class MotriceRegimeServiceEntity {
   
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long idMotriceRegimeService;
   
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="idMotriceRegime")
   private MotriceRegimeEntity motriceRegime;

}
