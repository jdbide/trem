package com.avancial.app.service.filtrePlanTransport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.FareProfile;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tosp;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * Filtre une {@link Tranche} à partir d'une valeur d'une donnée de type
 * {@link ASousRegimeTranche} : dans la liste de valeurs de la même classe, on
 * ne retient que les données qui ont la même valeur que celle donnée au
 * constructeur.<br>
 * Exemple : si l'on donne au constructeur un objet de classe {@link Tosp} dont
 * la valeur du code TOSP est "01", on va retourner une tranche dont la liste de
 * {@link Tosp} a été filtrée par rapport au code "01" (on enlève tous les
 * {@link Tosp} dont le code n'est pas "01"), mais les listes de
 * {@link CodeSat}, {@link FareProfile}... ne sont pas modifiées.
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltreSousRegimeTranche extends AFiltreTranche {

    /**
     * 
     * @param aSousRegimeTranche
     *            Valeur pour le filtre
     */
    public FiltreSousRegimeTranche(ASousRegimeTranche aSousRegimeTranche) {
        super();
        this.aSousRegimeTranche = aSousRegimeTranche;
    }
    
    public FiltreSousRegimeTranche(){
        super();
    }

    @Override
    protected Tranche filtreCritere(Tranche object) {
        Tranche tranche = object.clone();
        List<Date> datesTrancheAttributFiltre = new ArrayList<>();

        /* Boucle sur la liste d'objets de la classe donnée */
        if (object.getAttributsField(this.aSousRegimeTranche.getClass()) != null) {
            /* On ré-initialise les données pour la classe à filtrer */
            tranche.getAttributs().remove(this.aSousRegimeTranche.getClass());

            /* Nouvelle liste de données pour la classe à filtrer */
            List<ASousRegimeTranche> donnees = new ArrayList<>();
            for (ASousRegimeTranche aSousRegimeTranche : object.getAttributsField(this.aSousRegimeTranche.getClass())) {
                /*
                 * Si la valeur est égale à celle donnée au constructeur, on
                 * l'ajoute à la tranche résultat
                 */
                if (this.aSousRegimeTranche.equals(aSousRegimeTranche)) {
                    donnees.add(aSousRegimeTranche);
                    datesTrancheAttributFiltre = aSousRegimeTranche.getRegime().getListeJours();
                    /*
                     * On peut sortir de la boucle : il y a au plus un élément
                     * d'une valeur dans la liste
                     */
                    break;
                }
            }
            tranche.addAttributsField(donnees);
            Regime regime = tranche.getRegime();
            regime.setListeJours(this.intersection(tranche.getRegime().getListeJours(), datesTrancheAttributFiltre));
            tranche.setRegime(regime);
        }
        return tranche;
    }
    
    /**
     * renvois une list de date contenant l'intersection de dates1 avec dates2
     * @param dates1
     * @param dates2
     * @return
     */
    private List<Date> intersection(List<Date> dates1, List<Date> dates2){
        List<Date> res = new ArrayList<>();
        for (Date date1 : dates1) {
            if (dates2.contains(date1)) {
                res.add(date1);
            }
        }
        return res;
    }

}
