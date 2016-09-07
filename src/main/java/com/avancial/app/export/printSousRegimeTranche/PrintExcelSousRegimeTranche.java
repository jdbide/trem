package com.avancial.app.export.printSousRegimeTranche;

import java.util.HashMap;
import java.util.Map;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.Composition;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;
import com.avancial.app.data.objetsMetier.PlanTransport.FareProfile;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.Repas;
import com.avancial.app.data.objetsMetier.PlanTransport.Restriction;
import com.avancial.app.data.objetsMetier.PlanTransport.ServiceABord;
import com.avancial.app.data.objetsMetier.PlanTransport.Specification;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;

/**
 * Retourne les valeurs d'impression dans un fichier Excel pour un objet
 * {@code ASousRegimeTranche}, grâce à une Map qui appelle les instances de
 * {@code IPrintExcelSousRegimeTranche} adéquates.
 * 
 * @author heloise.guillemaud
 *
 */
public class PrintExcelSousRegimeTranche implements IPrintExcelSousRegimeTranche {

    /**
     * Map entre une classe héritant de {@code ASousRegimeTranche} et une
     * instance de l'implémentation correspondante de
     * {@code IPrintExcelSousRegimeTranche}
     */
    private Map<Class<?>, IPrintExcelSousRegimeTranche> map = new HashMap<>();

    /**
     * Constructeur qui remplit la map
     */
    public PrintExcelSousRegimeTranche() {
        this.map.put(CodeSat.class, new PrintExcelCodeSat());
        this.map.put(Composition.class, new PrintExcelComposition());
        this.map.put(Desserte.class, new PrintExcelDesserte());
        this.map.put(Distribution.class, new PrintExcelDistribution());
        this.map.put(FareProfile.class, new PrintExcelFareProfile());
        this.map.put(OrigineDestination.class, new PrintExcelOD());
        this.map.put(Repas.class, new PrintExcelRepas());
        this.map.put(Restriction.class, new PrintExcelRestriction());
        this.map.put(ServiceABord.class, new PrintExcelServiceABord());
        this.map.put(Specification.class, new PrintExcelSpecification());
        this.map.put(TypeEquipement.class, new PrintExcelTypeEquipement());
    }

    @Override
    public String printValue(ASousRegimeTranche sousRegimeTranche) {
        IPrintExcelSousRegimeTranche printExcelSousRegimeTranche = this.map.get(sousRegimeTranche.getClass());
        if (printExcelSousRegimeTranche != null) {
            return printExcelSousRegimeTranche.printValue(sousRegimeTranche);
        }
        return null;
    }

    @Override
    public String printRegime(ASousRegimeTranche sousRegimeTranche) {
        IPrintExcelSousRegimeTranche printExcelSousRegimeTranche = this.map.get(sousRegimeTranche.getClass());
        if (printExcelSousRegimeTranche != null) {
            return printExcelSousRegimeTranche.printRegime(sousRegimeTranche);
        }
        return null;
    }

}
