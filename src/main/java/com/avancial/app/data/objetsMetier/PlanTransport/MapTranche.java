package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapTranche extends HashMap<Class<?>, List<? extends ARegimeComparable>> {

    private static final long serialVersionUID = 1L;

    /**
     * @return the previous value associated with key, or null if there was no
     *         mapping for key (A null return can also indicate that the map
     *         previously associated null with key.) or the runtime class of the
     *         list values is not equal to the key.
     */
    @Override
    public List<? extends ARegimeComparable> put(Class<?> key,
            List<? extends ARegimeComparable> value) {
        if (value != null && value.size() > 0 && value.get(0).getClass().equals(key)) {
            return super.put(key, value);
        }
        return null;
    }
    
    public MapTranche clone() {
        HashMap<String, String> test = new HashMap<>();
        test.clone();
        MapTranche mapTranche = new MapTranche();
        for (Class<?> comparable : this.keySet()) {
            List<ARegimeComparable> objects = new ArrayList<>();
            for (ARegimeComparable object : this.get(comparable)) {
                objects.add(object.clone());
            }
            mapTranche.put(comparable, objects);
        }
        return mapTranche;
    }

}
