package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.List;
import com.avancial.socle.utils.ListUtils;

public class Compartiment {

    private String numeroCompartiment;

    private List<Siege> sieges;

    @Override
    public boolean equals(Object obj) {
        Compartiment compartiment = (Compartiment) obj;
        if (compartiment.getNumeroCompartiment().equals(this.numeroCompartiment)
                && ListUtils.compareLists(compartiment.getSieges(), this.sieges)) {
            return true;
        }
        return false;
    }

    public String getNumeroCompartiment() {
        return this.numeroCompartiment;
    }

    public void setNumeroCompartiment(String numeroCompartiment) {
        this.numeroCompartiment = numeroCompartiment;
    }

    public List<Siege> getSieges() {
        return this.sieges;
    }

    public void setSieges(List<Siege> sieges) {
        this.sieges = sieges;
    }

}
