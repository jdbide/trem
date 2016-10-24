package com.avancial.app.export;

import java.util.HashMap;
import java.util.Map;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.export.control.ExcelRapportControle;
import com.avancial.app.export.control.ExcelRapportControleSheetStatut;
import com.avancial.app.export.control.ExcelRapportControleSheetConfig;
import com.avancial.app.export.control.ExcelRapportControleSheetGdsAvn;
import com.avancial.app.export.control.ExcelRapportControleSheetHoraires;
import com.avancial.app.export.control.ExcelRapportControleSheetProfTarif;
import com.avancial.app.export.control.ExcelRapportControleSheetRepas;
import com.avancial.app.export.control.ExcelRapportControleSheetRestrictions;
import com.avancial.app.export.control.ExcelRapportControleSheetSat;
import com.avancial.app.export.control.ExcelRapportControleSheetSpecif;
import com.avancial.app.export.control.ExcelRapportControleSheetTosp;
import com.avancial.app.export.control.ExcelRapportControleSheetTranche;

/**
 * Factory qui retourne l'implémentation de {@link IExcelRapportComparaisonPlanTransportSheet} à partir du type de comparaison
 * {@link EnumTypeComparaisonPlanTransport}
 * 
 * @author heloise.guillemaud
 *
 */
public class ExcelRapportDifferentielSheetFactory {

   private Map<String, IExcelRapportComparaisonPlanTransportSheet> map = new HashMap<>();

   public ExcelRapportDifferentielSheetFactory() {
      this.map.put(EnumTypeComparaisonPlanTransport.NEW.toString(), new ExcelRapportDifferentielSheetNew());
      this.map.put(EnumTypeComparaisonPlanTransport.DELETE.toString(), new ExcelRapportDifferentielSheetDelete());
      this.map.put(EnumTypeComparaisonPlanTransport.MODIFY.toString(), new ExcelRapportDifferentielSheetModify());
      this.map.put(EnumTypeComparaisonPlanTransport.REGIMESPLIT.toString(), new ExcelRapportDifferentielSheetRegimeSplit());
      this.map.put(EnumTypeComparaisonPlanTransport.UNCHANGED.toString(), new ExcelRapportDifferentielSheetUnchanged());
      this.map.put(ExcelRapportControle.ERR_STATUT, new ExcelRapportControleSheetStatut());
      this.map.put(ExcelRapportControle.ERR_CONFIG, new ExcelRapportControleSheetConfig());
      this.map.put(ExcelRapportControle.ERR_SPECIF, new ExcelRapportControleSheetSpecif());
      this.map.put(ExcelRapportControle.ERR_HORAIRES, new ExcelRapportControleSheetHoraires());
      this.map.put(ExcelRapportControle.ERR_PROFTARIF, new ExcelRapportControleSheetProfTarif());
      this.map.put(ExcelRapportControle.ERR_SAT, new ExcelRapportControleSheetSat());
      this.map.put(ExcelRapportControle.ERR_TRANCHE, new ExcelRapportControleSheetTranche());
      this.map.put(ExcelRapportControle.ERR_GDS_AVN, new ExcelRapportControleSheetGdsAvn());
      this.map.put(ExcelRapportControle.ERR_TOSP, new ExcelRapportControleSheetTosp());
      this.map.put(ExcelRapportControle.ERR_REPAS, new ExcelRapportControleSheetRepas());
      this.map.put(ExcelRapportControle.ERR_RESTRICTIONS, new ExcelRapportControleSheetRestrictions());
   }

   public IExcelRapportComparaisonPlanTransportSheet get(String nomSheet) {
      return this.map.get(nomSheet);
   }
}
