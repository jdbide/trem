package service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.ComparePlanTransportControl;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.utils.ListUtils;
import factory.PlanTransportFactory;
import junit.framework.Assert;

public class TestControlePlanTransport {
    @Test
    public void testPlanTransport() {
        MapPlansDeTransport mapPlansDeTransport = PlanTransportFactory.createDataForControle();
        PlanTransport pdtXls = mapPlansDeTransport.getPlanTransportDraft();
        PlanTransport pdtBase = mapPlansDeTransport.getPlanTransportActive();
        ComparePlanTransportControl comp = new ComparePlanTransportControl();
        MapComparaisonPlanTransport res;
        MapComparaisonPlanTransport expected = new MapComparaisonPlanTransport();
        List<AComparaisonPlanTransport<IPlanTransport>> listRes = new ArrayList<>();
        List<AComparaisonPlanTransport<IPlanTransport>>  listExp = new ArrayList<>();

        ComparaisonControlePlanTransport<Tranche> compTrancheEgal = new ComparaisonControlePlanTransport<>();
        compTrancheEgal.setAncienField(pdtBase.getTrainByNumeroTrain("trainEgal").getTranches().get(0));
        compTrancheEgal.setNouveauField(pdtXls.getTrainByNumeroTrain("trainEgal").getTranches().get(0));
        compTrancheEgal.setDateCircul(
                pdtXls.getTrainByNumeroTrain("trainEgal").getTranches().get(0).getRegime().getListeJours().get(0));
        compTrancheEgal.setNumeroTranche("trancheEgal");
        compTrancheEgal.setNumeroTrain("trainEgal");
        compTrancheEgal.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.CONTROL);
        compTrancheEgal.setRegimeTranche(pdtXls.getTrainByNumeroTrain("trainEgal").getTranches().get(0).getRegime());

        ComparaisonControlePlanTransport<Tranche> compTrancheSatut = new ComparaisonControlePlanTransport<>();
        compTrancheSatut.setAncienField(pdtBase.getTrainByNumeroTrain("trainControle").getTranches().get(0));
        compTrancheSatut.setNouveauField(pdtXls.getTrainByNumeroTrain("trainControle").getTranches().get(0));
        compTrancheSatut.setDateCircul(
                pdtXls.getTrainByNumeroTrain("trainControle").getTranches().get(0).getRegime().getListeJours().get(0));
        compTrancheSatut.setNumeroTranche("trancheControleStatut");
        compTrancheSatut.setNumeroTrain("trainControle");
        compTrancheSatut.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.CONTROL);
        compTrancheSatut.setRegimeTranche(pdtXls.getTrainByNumeroTrain("trainControle").getTranches().get(0).getRegime());
        
        ComparaisonControlePlanTransport<CodeSat> compCodeSat = new ComparaisonControlePlanTransport<>();
        compCodeSat.setAncienField((CodeSat) pdtBase.getTrainByNumeroTrain("trainControle").getTranches().get(1).getAttributs().get(CodeSat.class).get(0));
        compCodeSat.setNouveauField((CodeSat) pdtXls.getTrainByNumeroTrain("trainControle").getTranches().get(1).getAttributs().get(CodeSat.class).get(0));
        compCodeSat.setDateCircul(
                pdtXls.getTrainByNumeroTrain("trainControle").getTranches().get(1).getRegime().getListeJours().get(0));
        compCodeSat.setNumeroTranche("trancheControleCodeSat");
        compCodeSat.setNumeroTrain("trainControle");
        compCodeSat.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.CONTROL);
        compCodeSat.setRegimeTranche(pdtXls.getTrainByNumeroTrain("trainControle").getTranches().get(1).getRegime());

        expected.putComparaison(compTrancheEgal);
        expected.putComparaison(compTrancheSatut);
        expected.putComparaison(compCodeSat);
        try {
            res = comp.compare(pdtBase, pdtXls);
            listRes = res.getComparaison(EnumTypeComparaisonPlanTransport.CONTROL);
            listExp = expected.getComparaison(EnumTypeComparaisonPlanTransport.CONTROL);
            System.out.println("youpi");
            Assert.assertTrue("Compare",
                    ListUtils.compareLists(listExp, listRes));

        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
