package com.avancial.app.export;

import java.util.HashMap;
import java.util.Map;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.Composition;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.Repas;
import com.avancial.app.data.objetsMetier.PlanTransport.Restriction;
import com.avancial.app.data.objetsMetier.PlanTransport.ServiceABord;
import com.avancial.app.data.objetsMetier.PlanTransport.Specification;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;

public class PrintExcelSousRegimeTranche implements IPrintExcelSousRegimeTranche {

    private Map<Class<?>, IPrintExcelSousRegimeTranche> map = new HashMap<>();

    /**
     * Constructeur
     */
    public PrintExcelSousRegimeTranche() {
       this.map.put(CodeSat.class, new PrintExcelCodeSat());
       this.map.put(Composition.class, new PrintExcelComposition());
       this.map.put(Desserte.class, new PrintExcelDesserte());
       this.map.put(Distribution.class, new PrintExcelDistribution());
//       this.map.put(FareProfile.class, new PrintExcelFareProfile());
       this.map.put(OrigineDestination.class, new PrintExcelSousRegimeOD());
       this.map.put(Repas.class, new PrintExcelSousRegimeRepas());
       this.map.put(Restriction.class, new PrintExcelSousRegimeRestriction());
       this.map.put(ServiceABord.class, new PrintExcelSousRegimeServiceABord());
       this.map.put(Specification.class, new PrintExcelSousRegimeSpecification());
       this.map.put(TypeEquipement.class, new PrintExcelSousRegimeTypeEquipement());
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
