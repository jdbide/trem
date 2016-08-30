package com.avancial.app.export;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;

public interface IGenerateExcelColonneNew {

    public void generate(List<ASousRegimeTranche> listeAttributs, int numColonne);
}
