package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.HashMap;
import java.util.List;

public class MapTranche extends HashMap<Class<? extends IPlanTransportComparable>, List<IPlanTransportComparable>> {

    private static final long serialVersionUID = 1L;

    /**
     * @return the previous value associated with key, or null if there was no
     *         mapping for key (A null return can also indicate that the map
     *         previously associated null with key.) or the runtime class of the
     *         list values is not equal to the key.
     */
    @Override
    public List<IPlanTransportComparable> put(Class<? extends IPlanTransportComparable> key,
            List<IPlanTransportComparable> value) {
        if (value != null && value.size() > 0 && value.get(0).getClass().equals(key)) {
            return super.put(key, value);
        }
        return null;
    }

}
