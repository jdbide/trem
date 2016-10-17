package com.avancial.app.service.filtrePlanTransport;

import java.util.ArrayList;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.FareProfile;
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
class FiltreSousRegimeTranche extends AFiltreTranche {

    private ASousRegimeTranche aSousRegimeTranche;

    /**
     * 
     * @param aSousRegimeTranche
     *            Valeur pour le filtre
     */
    public FiltreSousRegimeTranche(ASousRegimeTranche aSousRegimeTranche) {
        super();
        this.aSousRegimeTranche = aSousRegimeTranche;
    }

    @Override
    protected Tranche filtreCritere(Tranche object) {
        Tranche tranche = object.clone();

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
                    /*
                     * On peut sortir de la boucle : il y a au plus un élément
                     * d'une valeur dans la liste
                     */
                    break;
                }
            }
            tranche.addAttributsField(donnees);
        }
        return tranche;
    }

}
