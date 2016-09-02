package com.avancial.app.export;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.Composition;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;
import com.avancial.app.data.objetsMetier.PlanTransport.FareProfile;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.Repas;
import com.avancial.app.data.objetsMetier.PlanTransport.Restriction;
import com.avancial.app.data.objetsMetier.PlanTransport.ServiceABord;
import com.avancial.app.data.objetsMetier.PlanTransport.Specification;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonPlanTransport;
import com.avancial.app.export.printSousRegimeTranche.PrintExcelSousRegimeTranche;

public abstract class AExcelRapportDifferentielSheet implements IExcelRapportDifferentielSheet {

    protected PrintExcelSousRegimeTranche printExcelSousRegimeTranche = new PrintExcelSousRegimeTranche();

    private Map<Class<?>, String> mapNomField = new HashMap<>();

    public AExcelRapportDifferentielSheet() {
        this.mapNomField.put(CodeSat.class, "CodeSat");
        this.mapNomField.put(Desserte.class, "Stop");
        this.mapNomField.put(Distribution.class, "Distribution");
        this.mapNomField.put(FareProfile.class, "FareProfile");
        this.mapNomField.put(OrigineDestination.class, "OD");
        this.mapNomField.put(Repas.class, "MealType");
        this.mapNomField.put(Restriction.class, "Restriction");
        this.mapNomField.put(ServiceABord.class, "Services");
        this.mapNomField.put(Specification.class, "Specificities");
        this.mapNomField.put(TypeEquipement.class, "EquipmentType");
        this.mapNomField.put(Composition.class, "Composition");
    }

    protected void generateTrainTrancheField(ExcelTools excelTools,
            ComparaisonPlanTransport<IPlanTransport> comparaison, Color couleur) {
        /* Numéro de train */
        excelTools.createCellTexteWithStyle(1, comparaison.getNumeroTrain(),
                excelTools.addColor(excelTools.styleBorder, couleur));
        /* Numéro de tranche */
        excelTools.createCellTexteWithStyle(2, comparaison.getNumeroTranche(),
                excelTools.addColor(excelTools.styleBorder, couleur));
    }

    protected String getNomField(Class<?> classeField) {
        return this.mapNomField.get(classeField);
    }

}
