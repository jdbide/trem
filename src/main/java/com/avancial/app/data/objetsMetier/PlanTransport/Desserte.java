package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.ArrayList;
import java.util.List;
import com.avancial.socle.utils.ListUtils;

public class Desserte extends ASousRegimeTranche {

   private List<GareHoraire> gareHoraires;

   private Regime            regime;

   public Desserte() {
      this.gareHoraires = new ArrayList<>();
      this.regime = new Regime();
   }

   public Desserte clone() {
      Desserte res = new Desserte();
      List<GareHoraire> resGareHoraires = new ArrayList<>();
      if (this.regime != null) {
         res.setRegime(this.regime.clone());
      } else {
         res.setRegime(null);
      }
      if (this.gareHoraires != null) {
         for (GareHoraire gareHoraire : this.gareHoraires) {
            resGareHoraires.add(gareHoraire.clone());
         }
         res.setGareHoraires(resGareHoraires);

      } else {
         res.setGareHoraires(null);
      }
      return res;
   }

   public Desserte(List<GareHoraire> gareHoraires, Regime regime) {
      super();
      this.gareHoraires = gareHoraires;
      this.regime = regime;
   }

   @Override
   public boolean equals(Object obj) {
      Desserte desserte = (Desserte) obj;
      if (ListUtils.compareLists(desserte.getGareHoraires(), this.gareHoraires)) {
         return true;
      }
      return false;
   }

   public List<GareHoraire> getGareHoraires() {
      return this.gareHoraires;
   }

   public void setGareHoraires(List<GareHoraire> gareHoraires) {
      this.gareHoraires = gareHoraires;
   }

   public Regime getRegime() {
      return this.regime;
   }

   public void setRegime(Regime regime) {
      this.regime = regime;
   }

}
