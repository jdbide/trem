package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_regime_satcode")
@NamedQuery(name = "MotriceRegimeSatcode.getAll", query = "SELECT t FROM MotriceRegimeSatcodeEntity t")
public class MotriceRegimeSatcodeEntity {
   
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long idMotriceRegimeSatcode;
   
   @OneToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="idMotriceRegime")
   private MotriceRegimeEntity motriceRegime;

}