package com.avancial.app.export;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Composition;
import com.avancial.app.data.objetsMetier.PlanTransport.Voiture;

public class PrintExcelComposition extends APrintExcelSousRegimeTranche {

    @Override
    public String printValue(ASousRegimeTranche sousRegimeTranche) {
        if (sousRegimeTranche.getClass().equals(Composition.class)) {
            Composition composition = (Composition) sousRegimeTranche;
            /* Numéros de voiture */
            for (Voiture voiture : composition.getVoitures()) {
                this.stringBuilder.append(voiture.getNumeroVoiture()).append(",");
            }
            this.stringBuilder.deleteCharAt(this.stringBuilder.length()-1);
            
            this.stringBuilder.append(" = ");
            
            /* Code Rame */
            this.stringBuilder.append(composition.getCodeRame()).append(",");
            /* Code Classe */
            this.stringBuilder.append(composition.getCodeClasse()).append(",");
            /* Code Diag */
            this.stringBuilder.append(composition.getCodeDiag());
            return this.stringBuilder.toString();
        }
        return null;
    }

}
