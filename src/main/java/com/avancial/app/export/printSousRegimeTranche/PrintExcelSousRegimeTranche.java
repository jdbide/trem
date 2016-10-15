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
import com.avancial.app.data.objetsMetier.PlanTransport.Tosp;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;

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
    private Map<String, IPrintExcelSousRegimeTranche> map = new HashMap<>();

    private EnumTypeComparaisonPlanTransport typeComparaison = null;

    private String generateKey(Class<?> classe, EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport) {
        if (typeComparaisonPlanTransport == null) {
            return classe.getSimpleName();
        }
        return classe.getSimpleName() + "_" + typeComparaisonPlanTransport;
    }

    /**
     * Constructeur qui remplit la map
     */
    public PrintExcelSousRegimeTranche() {
        this.map.put(CodeSat.class.getSimpleName(), new PrintExcelCodeSat());
        this.map.put(Composition.class.getSimpleName(), new PrintExcelComposition());
        this.map.put(Desserte.class.getSimpleName(), new PrintExcelDesserte());
        this.map.put(Distribution.class.getSimpleName(), new PrintExcelDistribution());
        this.map.put(FareProfile.class.getSimpleName(), new PrintExcelFareProfile());
        this.map.put(OrigineDestination.class.getSimpleName(), new PrintExcelOD());
        this.map.put(Repas.class.getSimpleName(), new PrintExcelRepas());
        this.map.put(Restriction.class.getSimpleName(), new PrintExcelRestriction());
        this.map.put(ServiceABord.class.getSimpleName(), new PrintExcelServiceABord());
        this.map.put(Specification.class.getSimpleName(), new PrintExcelSpecification());
        this.map.put(TypeEquipement.class.getSimpleName(), new PrintExcelTypeEquipement());
        this.map.put(Tosp.class.getSimpleName(), new PrintExcelTosp());
        this.map.put(Tranche.class.getSimpleName(), new PrintExcelTranche());
        this.map.put(generateKey(Tranche.class, EnumTypeComparaisonPlanTransport.MODIFY),
                new PrintExcelTrancheModify());
    }

    @Override
    public String printValue(ASousRegimeTranche sousRegimeTranche) {
        
        //cas ou la clef est defini pour le type de comparaison
        IPrintExcelSousRegimeTranche printExcelSousRegimeTranche = this.map
                .get(generateKey(sousRegimeTranche.getClass(), this.typeComparaison));

        if (printExcelSousRegimeTranche != null) {
            return printExcelSousRegimeTranche.printValue(sousRegimeTranche);
        }
        
        //cas ou la clef n'est pas defini pour le type de comparaison
        printExcelSousRegimeTranche = this.map
                .get(generateKey(sousRegimeTranche.getClass(), null));
        
        if (printExcelSousRegimeTranche != null) {
            return printExcelSousRegimeTranche.printValue(sousRegimeTranche);
        }
        
        return null;
    }

    @Override
    public String printRegime(ASousRegimeTranche sousRegimeTranche) {
        
        //cas ou la clef est defini pour le type de comparaison
        IPrintExcelSousRegimeTranche printExcelSousRegimeTranche = this.map
                .get(generateKey(sousRegimeTranche.getClass(), this.typeComparaison));
        
        if (printExcelSousRegimeTranche != null) {
            return printExcelSousRegimeTranche.printRegime(sousRegimeTranche);
        }
        
        //cas ou la clef n'est pas defini pour le type de comparaison
        printExcelSousRegimeTranche = this.map
                .get(generateKey(sousRegimeTranche.getClass(), null));
        
        if (printExcelSousRegimeTranche != null) {
            return printExcelSousRegimeTranche.printRegime(sousRegimeTranche);
        }
        return null;
    }

    /**
     * @return the typeComparaison
     */
    public EnumTypeComparaisonPlanTransport getTypeComparaison() {
        return this.typeComparaison;
    }

    /**
     * @param typeComparaison
     *            the typeComparaison to set
     */
    public void setTypeComparaison(EnumTypeComparaisonPlanTransport typeComparaison) {
        this.typeComparaison = typeComparaison;
    }

}
