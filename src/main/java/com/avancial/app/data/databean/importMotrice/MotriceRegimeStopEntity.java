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
@Table(name = "tremas_motrice_regime_stop")
@NamedQuery(name = "MotriceRegimeStop.getAll", query = "SELECT t FROM MotriceRegimeStopEntity t")
public class MotriceRegimeStopEntity {
   
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long idMotriceRegimeStop;
   
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="idMotriceRegime")
   private MotriceRegimeEntity motriceRegime;

}
