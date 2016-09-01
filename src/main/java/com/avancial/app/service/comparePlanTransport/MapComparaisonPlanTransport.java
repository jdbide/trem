package com.avancial.app.service.comparePlanTransport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;

/**
 * Map contenant des objets {@link ComparaisonPlanTransport} indexés par le type
 * de comparaison et la classe des attributs comparés (null s'il n'y a pas
 * d'attributs dans la comparaison, c'est-à-dire dans les cas NEW, DELETE et
 * UNCHANGED)
 * 
 * @author heloise.guillemaud
 *
 */
public class MapComparaisonPlanTransport {

    private class MapComparaisonPlanTransportCle {
        private EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport;
        private Class<?> attributComparaisonClasse;

        public MapComparaisonPlanTransportCle(EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport,
                Class<?> attributComparaisonClasse) {
            super();
            this.typeComparaisonPlanTransport = typeComparaisonPlanTransport;
            this.attributComparaisonClasse = attributComparaisonClasse;
        }

        @Override
        public boolean equals(Object obj) {
            MapComparaisonPlanTransportCle cle = (MapComparaisonPlanTransportCle) obj;
            return this.typeComparaisonPlanTransport.equals(cle.typeComparaisonPlanTransport)
                    && ((this.attributComparaisonClasse == null && cle.attributComparaisonClasse == null)
                            || (this.attributComparaisonClasse != null && cle.attributComparaisonClasse != null
                                    && this.attributComparaisonClasse.equals(cle.attributComparaisonClasse)));
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + (this.typeComparaisonPlanTransport == null ? 0 : this.typeComparaisonPlanTransport.hashCode());
            result = prime * result
                    + (this.attributComparaisonClasse == null ? 0 : this.attributComparaisonClasse.hashCode());
            return result;
        }

    }

    private Map<MapComparaisonPlanTransportCle, List<ComparaisonPlanTransport<IPlanTransport>>> mapComparaison;

    public MapComparaisonPlanTransport() {
        super();
        this.mapComparaison = new HashMap<>();
    }

    /**
     * Insertion d'une comparaison dans la map
     * 
     * @param comparaisonPlanTransport
     */
    public void putComparaison(ComparaisonPlanTransport<IPlanTransport> comparaisonPlanTransport) {
        Class<?> classeCle = null;
        if (comparaisonPlanTransport.getAncienField() != null) {
            classeCle = comparaisonPlanTransport.getAncienField().getClass();
        }

        MapComparaisonPlanTransportCle cle = new MapComparaisonPlanTransportCle(
                comparaisonPlanTransport.getTypeComparaisonPlanTransport(), classeCle);
        List<ComparaisonPlanTransport<IPlanTransport>> list = this.mapComparaison.get(cle);
        if (list == null) {
            list = new ArrayList<>();
        }

        // TODO dans le RegimeSplit, ajouter "dans l'ordre"
        list.add(comparaisonPlanTransport);
        this.mapComparaison.put(cle, list);
    }

    /**
     * Récupération d'une liste de comparaisons à partir du type de comparaison
     * et de la classe des attributs de comparaison. Pour les types de
     * comparaison NEW, DELETE et UNCHANGED, la classe doit être à null.
     * 
     * @param typeComparaisonPlanTransport
     * @param classeAttributComparaison
     * @return
     */
    public List<ComparaisonPlanTransport<IPlanTransport>> getComparaison(
            EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport, Class<?> classeAttributComparaison) {
        List<ComparaisonPlanTransport<IPlanTransport>> res = this.mapComparaison
                .get(new MapComparaisonPlanTransportCle(typeComparaisonPlanTransport, classeAttributComparaison));
        if (res == null) {
            return new ArrayList<>();
        }
        return res;
    }

    /**
     * Récupération de la liste de toutes les comparaisons d'un certain type
     * contenues dans la map.
     * 
     * @param typeComparaisonPlanTransport
     * @return
     */
    public List<ComparaisonPlanTransport<IPlanTransport>> getComparaison(
            EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport) {
        List<ComparaisonPlanTransport<IPlanTransport>> res = new ArrayList<>();
        for (MapComparaisonPlanTransportCle cle : this.mapComparaison.keySet()) {
            if (cle.typeComparaisonPlanTransport.equals(typeComparaisonPlanTransport)) {
                res.addAll(this.mapComparaison.get(cle));
            }
        }
        return res;
    }

    public void putAll(MapComparaisonPlanTransport mapComparaisonPlanTransport) {
        for (List<ComparaisonPlanTransport<IPlanTransport>> comparaisons : mapComparaisonPlanTransport.mapComparaison
                .values()) {
            for (ComparaisonPlanTransport<IPlanTransport> comparaison : comparaisons) {
                this.putComparaison(comparaison);
            }
        }
    }

    public void clear() {
        this.mapComparaison.clear();
    }

    public Collection<List<ComparaisonPlanTransport<IPlanTransport>>> values() {
        return this.mapComparaison.values();
    }

    public int size() {
        return this.mapComparaison.size();
    }
}
