package com.avancial.app.service.traiteMotriceRegime;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeCompositionCoachEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeCompositionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Composition;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Voiture;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeComposition implements ITraiteMotriceRegime {

    @Override
    public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity,
            MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
            MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager,
            AtomicReference<Tranche> atomicTranche) {
        /* Composition */
       Date debutPeriode = motriceTrainTrancheEntity.getJeuDonnee().getDateDebutPeriode();
       
        // TODO ENLEVER LA CLASSE EN DUR
        Query queryRCompo = entityManager.createNativeQuery(
                "SELECT /*classe.classeRefRameClasse*/ 'A' AS classCodeMotriceRegimeComposition, tyvo.TYVO_DIAV_COD AS diagCodeMotriceRegimeComposition, voiture.VOIT_COD_ORIG AS rameCodeMotriceRegimeComposition, LPAD( voiture.VOIT_NUM_RESA, 3, '0' ) AS coachNumberMotriceRegimeCompositionCoach, voiture.VOIT_REGI_UTIL AS periodMotriceRegime FROM tremas_import_tmdvoit AS voiture INNER JOIN tremas_import_tmdcath AS cara ON voiture.VOIT_TRCH_COD_CIE = cara.CATH_TRCH_COD_CIE AND voiture.VOIT_TRCH_NUM_TRA1 = cara.CATH_TRCH_NUM_TRA1 AND voiture.VOIT_TRCH_IND_FER = cara.CATH_TRCH_IND_FER AND voiture.VOIT_TRCH_NUM = cara.CATH_TRCH_NUM LEFT JOIN tremas_ref_rame_classe AS classe ON voiture.VOIT_NUM_RESA = classe.numResaRefRameClasse AND voiture.VOIT_COD_ORIG = classe.codeRameRefRameClasse INNER JOIN tremas_import_tmdtyvo AS tyvo ON voiture.VOIT_TYVO_NUM_TYP = tyvo.TYVO_NUM_TYP WHERE cara.CATH_SSIM = ? AND cara.CATH_TRCH_NUM_TRA1 = ? ORDER BY voiture.VOIT_REGI_UTIL, classe.classeRefRameClasse, voiture.VOIT_COD_ORIG, tyvo.TYVO_DIAV_COD");
        queryRCompo.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
        queryRCompo.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());

        List<Object[]> listeCompo = queryRCompo.getResultList();
        AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
        AtomicLong idRegimeCompo = mapIdTablesMotriceRegime.get(MotriceRegimeCompositionEntity.class);
        AtomicLong idRegimeCompoCoach = mapIdTablesMotriceRegime.get(MotriceRegimeCompositionCoachEntity.class);

        List<ASousRegimeTranche> listeCompositions = (List<ASousRegimeTranche>) atomicTranche.get()
                .getAttributsField(Composition.class);
        if (listeCompositions == null) {
            listeCompositions = new ArrayList<ASousRegimeTranche>();
        }

        String oldRegime = "";
        String oldClasse = "";
        String oldDiag = "";
        String oldRameCode = "";

        MotriceRegimeCompositionEntity compositionEntity = null;
        List<MotriceRegimeCompositionEntity> compositions = null;
        MotriceRegimeEntity regimeEntity = null;
        Map<String, String> rameCodes = new HashMap<>();
        List<Voiture> voitures = new ArrayList<>();
        for (Object[] compo : listeCompo) {

            if (!oldRegime.equals(compo[4])) {
                /* Nouveau Regime */

                /* Insertion des compositions du régime précédent */
                if (compositions != null) {
                    this.ajoutCompositionsRegime(rameCodes, compositions, listeCompositions,
                            mapGeneratorTablesMotriceRegime, entityManager);
                }
                compositions = new ArrayList<>();
                rameCodes.clear();

                /* Insertion du nouveau régime */
                mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class).addValue(idRegime.incrementAndGet(),
                        compo[4], 11, motriceTrainTrancheEntity.getIdMotriceTrainTranche());
            }
            oldRegime = (String) compo[4];

            if (!oldClasse.equals(compo[0]) || !oldDiag.equals(compo[1]) || !oldRameCode.equals(compo[2])) {
                /* Nouvelle Composition */
                oldClasse = (String) compo[0];
                oldDiag = (String) compo[1];
                oldRameCode = (String) compo[2];

                /* On sette la nouvelle Composition */
                regimeEntity = new MotriceRegimeEntity();
                regimeEntity.setIdMotriceRegime(idRegime.get());
                compositionEntity = new MotriceRegimeCompositionEntity(idRegimeCompo.incrementAndGet(),
                        (String) compo[0], (String) compo[1], (String) compo[2], (String) compo[3], null, regimeEntity);
                compositions.add(compositionEntity);

                voitures = new ArrayList<>();
                try {
                  listeCompositions.add(new Composition((String) compo[0], (String) compo[1], (String) compo[2], null,
                           voitures, new Regime((String) compo[4], debutPeriode)));
               } catch (ParseException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
            }
            // insertion des numéros de voiture liés à la compo
            mapGeneratorTablesMotriceRegime.get(MotriceRegimeCompositionCoachEntity.class)
                    .addValue(idRegimeCompoCoach.getAndIncrement(), compo[3], idRegimeCompo.get());
            voitures.add(new Voiture((String) compo[3], null));

            rameCodes.put(((String) compo[3]).equals("0") ? "RC1" : "RC2", (String) compo[2]);
        }

        /* Insertion des Composition du dernier Regime */
        this.ajoutCompositionsRegime(rameCodes, compositions, listeCompositions, mapGeneratorTablesMotriceRegime,
                entityManager);

        atomicTranche.get().addAttributsField(listeCompositions);

    }

    private void ajoutCompositionsRegime(Map<String, String> rameCodes,
            List<MotriceRegimeCompositionEntity> compositionEntities, List<ASousRegimeTranche> compositions,
            MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager) {
        /* TODO Récupération du rmCode */
        String rmCode = null;
        String rameCode1 = rameCodes.get("RC1");
        String rameCode2 = rameCodes.get("RC2");
//        if (rameCode1 != null && rameCode2 != null) {
            Query query = entityManager.createNativeQuery(
                    "SELECT rm.codeRmRefCodeRm FROM tremas_ref_code_rm rm WHERE rm.rame1RefCodeRm = ? AND rm.rame2RefCodeRm = ?;");
//            StringBuilder builder = new StringBuilder();
//            query.setParameter(1, builder.append(rameCode1.substring(0, 2)).append(rameCode1.substring(rameCode1.length()-1)));
//            query.setParameter(2, builder.append(rameCode2.substring(0, 2)).append(rameCode2.substring(rameCode2.length()-1)));
            query.setParameter(1, "15H");
            query.setParameter(2, "14B");
            rmCode = (String) query.getSingleResult();
//        } else {
//            System.err.println("Erreur récupération rmCode");
//            return;
//        }

        /* Insertions */
        for (MotriceRegimeCompositionEntity composition : compositionEntities) {
            mapGeneratorTablesMotriceRegime.get(MotriceRegimeCompositionEntity.class).addValue(
                    composition.getIdMotriceRegimeComposition(), composition.getClassCodeMotriceRegimeComposition(),
                    composition.getDiagCodeMotriceRegimeComposition(),
                    composition.getRameCodeMotriceRegimeComposition(), rmCode,
                    composition.getMotriceRegime().getIdMotriceRegime());
        }
        for (ASousRegimeTranche sousRegime : compositions) {
            Composition composition = (Composition) sousRegime;
            if (composition.getCodeRm() == null) {
                composition.setCodeRm(rmCode);
            }
        }
    }
}
