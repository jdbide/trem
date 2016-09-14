package com.avancial.app.export.printSousRegimeTranche;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.GareHoraire;

public class PrintExcelDesserte extends APrintExcelSousRegimeTranche {

    private Calendar calendar = Calendar.getInstance();

    @Override
    public String printValue(ASousRegimeTranche sousRegimeTranche) {
        if (sousRegimeTranche.getClass().equals(Desserte.class)) {
            this.stringBuilder.setLength(0);
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            Desserte desserte = (Desserte) sousRegimeTranche;
            /* Première gare */
            for (Iterator<GareHoraire> itGare = desserte.getGareHoraires().iterator(); itGare.hasNext();) {
                GareHoraire gareHoraire = itGare.next();
                if (gareHoraire.getHoraire().getHoraireDebut() == null) {
                    this.calendar.setTime(gareHoraire.getHoraire().getHoraireFin());
                    this.stringBuilder.append(gareHoraire.getGare().getCodeGare()).append(" ( / - ")
                            .append(formatter.format(this.calendar.getTime())).append(")");
                    itGare.remove();
                    break;
                }
            }

            /* Gares suivantes */
            GareHoraire prochaineGare = null;
            while (!desserte.getGareHoraires().isEmpty()) {
                prochaineGare = null;

                /* On cherche la date d'arrivée (de début) la plus tôt */
                for (GareHoraire gareHoraire : desserte.getGareHoraires()) {
                    if (prochaineGare == null) {
                        prochaineGare = gareHoraire;
                    }
                    if (gareHoraire.getHoraire().getHoraireDebut()
                            .before(prochaineGare.getHoraire().getHoraireDebut())) {
                        prochaineGare = gareHoraire;
                    }
                }

                /* On ajoute cette gare */
                this.stringBuilder.append("\n").append(prochaineGare.getGare().getCodeGare()).append(" ( ");
                this.calendar.setTime(prochaineGare.getHoraire().getHoraireDebut());
                this.stringBuilder.append(formatter.format(this.calendar.getTime())).append(" - ");
                if (prochaineGare.getHoraire().getHoraireFin() != null) {
                    this.calendar.setTime(prochaineGare.getHoraire().getHoraireFin());
                    this.stringBuilder.append(formatter.format(this.calendar.getTime())).append(")");
                }
                else {
                    this.stringBuilder.append("/)");
                }
                /* On l'enlève de la liste */
                desserte.getGareHoraires().remove(prochaineGare);
            }
            return this.stringBuilder.toString();
        }
        return null;
    }

}
