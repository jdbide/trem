package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_traintranche")
@NamedQuery(name = "MotriceTrainTranche.getAll", query = "SELECT t FROM MotriceTrainTrancheEntity t")
public class MotriceTrainTrancheEntity {
   
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long idMotriceTrainTranche;

}
