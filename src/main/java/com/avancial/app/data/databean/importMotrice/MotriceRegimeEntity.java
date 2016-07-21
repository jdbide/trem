package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_regime_service")
public class MotriceRegimeEntity {
    
    @Id
    private Long idMotriceRegime;

}
