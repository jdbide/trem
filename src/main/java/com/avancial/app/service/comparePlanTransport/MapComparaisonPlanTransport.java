package com.avancial.app.service.comparePlanTransport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;

/**
 * Map contenant des objets {@link AComparaisonPlanTransport} indexés par le type
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

    private Map<MapComparaisonPlanTransportCle, List<AComparaisonPlanTransport<IPlanTransport>>> mapComparaison;

    public MapComparaisonPlanTransport() {
        super();
        this.mapComparaison = new HashMap<>();
    }

    /**
     * Insertion d'une comparaison dans la map
     * 
     * @param comparaisonPlanTransport
     */
    public void putComparaison(AComparaisonPlanTransport<IPlanTransport> comparaisonPlanTransport) {
        Class<?> classeCle = null;
        if (comparaisonPlanTransport.getAncienField() != null) {
            classeCle = comparaisonPlanTransport.getAncienField().getClass();
        }

        MapComparaisonPlanTransportCle cle = new MapComparaisonPlanTransportCle(
                comparaisonPlanTransport.getTypeComparaisonPlanTransport(), classeCle);
        List<AComparaisonPlanTransport<IPlanTransport>> list = this.mapComparaison.get(cle);
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
    public List<AComparaisonPlanTransport<IPlanTransport>> getComparaison(
            EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport, Class<?> classeAttributComparaison) {
        List<AComparaisonPlanTransport<IPlanTransport>> res = this.mapComparaison
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
    public List<AComparaisonPlanTransport<IPlanTransport>> getComparaison(
            EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport) {
        List<AComparaisonPlanTransport<IPlanTransport>> res = new ArrayList<>();
        for (MapComparaisonPlanTransportCle cle : this.mapComparaison.keySet()) {
            if (cle.typeComparaisonPlanTransport.equals(typeComparaisonPlanTransport)) {
                res.addAll(this.mapComparaison.get(cle));
            }
        }
        return res;
    }

    /**
     * Ajout dans la map de toutes les données présentes dans celle en
     * paramètre.
     * 
     * @param mapComparaisonPlanTransport
     *            Map dont on veut importer les données
     */
    public void putAll(MapComparaisonPlanTransport mapComparaisonPlanTransport) {
        for (List<AComparaisonPlanTransport<IPlanTransport>> comparaisons : mapComparaisonPlanTransport.mapComparaison
                .values()) {
            for (AComparaisonPlanTransport<IPlanTransport> comparaison : comparaisons) {
                this.putComparaison(comparaison);
            }
        }
    }

    /**
     * Enlève tous les mappings de la map.
     */
    public void clear() {
        this.mapComparaison.clear();
    }

    /**
     * 
     * @return Une collection de toutes les valeurs contenues dans la map
     */
    public Collection<List<AComparaisonPlanTransport<IPlanTransport>>> values() {
        return this.mapComparaison.values();
    }

    /**
     * 
     * @return Le nombre de paires clé-valeur dans la map, soit le nombre de
     *         types de comparaison différents présents dans la map
     */
    public int size() {
        return this.mapComparaison.size();
    }
}
