package com.avancial.app.export.generateColonneNew;

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
 * Factory retournant l'implémentation de {@link IGenerateExcelColonneNew}
 * correspondant à une classe fille de {@link ASousRegimeTranche} de field.
 * 
 * @author heloise.guillemaud
 *
 */
public class GenerateExcelColonneNewFactory {

    private Map<Class<?>, IGenerateExcelColonneNew> map = new HashMap<>();

    public GenerateExcelColonneNewFactory() {
        this.map.put(CodeSat.class, new GenerateColonneCodeSat());
        this.map.put(Composition.class, new GenerateColonneComposition());
        this.map.put(Desserte.class, new GenerateColonneDesserte());
        this.map.put(Distribution.class, new GenerateColonneDistribution());
        this.map.put(FareProfile.class, new GenerateColonneFareProfile());
        this.map.put(OrigineDestination.class, new GenerateColonneOD());
        this.map.put(Repas.class, new GenerateColonneRepas());
        this.map.put(Restriction.class, new GenerateColonneRestriction());
        this.map.put(ServiceABord.class, new GenerateColonneServiceABord());
        this.map.put(Specification.class, new GenerateColonneSpecification());
        this.map.put(TypeEquipement.class, new GenerateColonneTypeEquipement());
    }

    public IGenerateExcelColonneNew get(Class<?> classe) {
        return this.map.get(classe);
    }
}
