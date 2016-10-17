package com.avancial.app.service.filtrePlanTransport;

import java.util.Date;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.socle.utils.DateUtils;

/**
 * Filtre le régime d'une {@link Tranche} à partir d'une valeur d'une donnée de
 * type {@link ASousRegimeTranche} : du résultat, qui est une copie de la
 * tranche à filtrer, on retire du régime Tranche toutes les dates pour
 * lesquelles la valeur pour la classe de la donnée n'est pas celle
 * demandée.<br>
 * Exemple:<br>
 * Pour une tranche dont le régime est "10 octobre, 11 octobre, 12 octobre", et
 * qui a comme code TOSP "01" les 10 et 11 octobre, et "02" le 12 octobre. Si on
 * filtre avec un code TOSP de valeur "01", on va retirer la date "12 octobre"
 * au régime de la tranche.
 * 
 * @author heloise.guillemaud
 *
 */
class FiltreRegimeTranche extends AFiltreTranche {

    private ASousRegimeTranche aSousRegimeTranche;

    /**
     * 
     * @param aSousRegimeTranche
     *            Valeur pour le filtre
     */
    public FiltreRegimeTranche(ASousRegimeTranche aSousRegimeTranche) {
        super();
        this.aSousRegimeTranche = aSousRegimeTranche;
    }

    @Override
    protected Tranche filtreCritere(Tranche object) {
        Tranche tranche = object.clone();

        /* Boucle sur la liste d'objets de la classe donnée */
        if (object.getAttributsField(this.aSousRegimeTranche.getClass()) != null) {
            for (ASousRegimeTranche aSousRegimeTranche : object.getAttributsField(this.aSousRegimeTranche.getClass())) {
                /*
                 * Si la valeur n'est pas égale à celle donnée au constructeur,
                 * on supprime les dates du sous-régime du régime de la tranche
                 */
                if (!this.aSousRegimeTranche.equals(aSousRegimeTranche)) {
                    for (Date date : aSousRegimeTranche.getRegime().getListeJours()) {
                        DateUtils.removeDayFromDateList(tranche.getRegime().getListeJours(), date);
                    }
                }
            }
        }
        return tranche;
    }

}
