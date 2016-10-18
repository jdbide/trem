ALTER TABLE `tremas_datasource`
ADD UNIQUE INDEX `KEYUnique_datasource_nomTechniqueDataSource` (`nomTechniqueDataSource`) USING BTREE,
ADD INDEX `KEY_datasource_idDatasource` (`idDatasource`) USING BTREE ;

ALTER TABLE `tremas_compagnie`
DROP INDEX `compagnie_UnomTechnique` ,
ADD UNIQUE INDEX `KEYUnique_compagnie_nomTechniqueCompagnie` (`nomTechniqueCompagnie`) USING BTREE ,
DROP INDEX `compagnie_NidCompagnie` ,
ADD INDEX `KEY_compagnie_idCompagnie` (`idCompagnie`) USING BTREE ;


ALTER TABLE `tremas_environnement`
DROP INDEX `environnement_UnomTechnique` ,
ADD UNIQUE INDEX `KEYUnique_environnement_nomTechniqueEnvironnement` (`nomTechniqueEnvironnement`) USING BTREE ,
DROP INDEX `environnement_NidEnvironnement` ,
ADD INDEX `KEY_environnement_idEnvironnement` (`idEnvironnement`) USING BTREE ;

ALTER TABLE `tremas_compagnie_environnement`
DROP INDEX `KEYUnique_nomTechniqueCompagnieEnvironnement` ,
ADD UNIQUE INDEX `KEYUnique_compagnie_environnement_nomTechnique` (`nomTechniqueCompagnieEnvironnement`) USING BTREE ,

DROP INDEX `indImportDonnees_idCompagnie_idEnvironnement` ,
ADD UNIQUE INDEX `KEYUnique_compagnie_environnement_idCompagnie_idEnvironnement` (`idCompagnie`, `idEnvironnement`) USING BTREE ,

DROP INDEX `tremas_compagnie_environnement_fkDatasource` ,
ADD INDEX `PK_compagnie_environnement_idDatasource` (`idDataSource`) USING BTREE ,

DROP INDEX `compagnie_environnement_FKidEnvironnement` ,
ADD INDEX `PK_compagnie_environnement_idEnvironnement` (`idEnvironnement`) USING BTREE ,
ADD INDEX `PK_compagnie_environnement_idCompagnie` (`idCompagnie`) USING BTREE ,

ADD INDEX `KEY_compagnie_environnement_idCompagnieEnvironnement` (`idCompagnieEnvironnement`) USING BTREE ;


ALTER TABLE `tremas_compagnie_environnement` DROP FOREIGN KEY `compagnie_environnement_FKidCompagnie`;

ALTER TABLE `tremas_compagnie_environnement` ADD CONSTRAINT `FK_compagnie_environnement_idCompagnie` FOREIGN KEY (`idCompagnie`) REFERENCES `tremas_compagnie` (`idCompagnie`) ON DELETE SET NULL ON UPDATE SET NULL;

ALTER TABLE `tremas_compagnie_environnement` DROP FOREIGN KEY `compagnie_environnement_FKidEnvironnement`;

ALTER TABLE `tremas_compagnie_environnement` ADD CONSTRAINT `FK_compagnie_environnement_idEnvironnement` FOREIGN KEY (`idEnvironnement`) REFERENCES `tremas_environnement` (`idEnvironnement`) ON DELETE SET NULL ON UPDATE SET NULL;

ALTER TABLE `tremas_compagnie_environnement` DROP FOREIGN KEY `tremas_compagnie_environnement_fkDatasource`;

ALTER TABLE `tremas_compagnie_environnement` ADD CONSTRAINT `FK_compagnie_environnement_idDatasource` FOREIGN KEY (`idDataSource`) REFERENCES `tremas_datasource` (`idDatasource`) ON DELETE SET NULL ON UPDATE SET NULL;


ALTER TABLE `tremas_jeu_donnees`
ADD INDEX `KEY_jeu_donnees_idJeuDonnees` (`idJeuDonnees`) USING BTREE ,
DROP INDEX `FK_jeu_donnees_idCompagnieEnvironnement` ,
ADD INDEX `PK_jeu_donnees_idCompagnieEnvironnement` (`idCompagnieEnvironnement`) USING BTREE ;

ALTER TABLE `tremas_jeu_donnees_control`
DROP INDEX `indCompagnieEnvironnement_idCompagnieEnvironnement` ,
ADD INDEX `PK_jeu_donnees_control_idCompagnieEnvironnement` (`idCompagnieEnvironnement`) USING BTREE ,
ADD INDEX `KEY_jeu_donnees_control_idJeuDonneesControl` (`idJeuDonneesControl`) USING BTREE ;

ALTER TABLE `tremas_jeu_donnees_control` DROP FOREIGN KEY `compagnieEnvironnement_idCompagnieEnvironnement_FK`;

ALTER TABLE `tremas_jeu_donnees_control` ADD CONSTRAINT `FK_jeu_donnees_control_idCompagnieEnvironnement` FOREIGN KEY (`idCompagnieEnvironnement`) REFERENCES `tremas_compagnie_environnement` (`idCompagnieEnvironnement`) ON DELETE RESTRICT ON UPDATE RESTRICT;


ALTER TABLE `tremas_motrice_traintranche`
DROP INDEX `KEY_motrice_regime_traintranche_idJeuDonnees` ,
ADD INDEX `PK_motrice_regime_traintranche_idJeuDonnees` (`idJeuDonnees`) USING BTREE ,
ADD INDEX `KEY_motrice_regime_traintranche_idMotriceTrainTranche` (`idMotriceTrainTranche`) USING BTREE ;

ALTER TABLE `tremas_motrice_regime`
DROP INDEX `FK_motrice_regime_idMotriceRefRegimeType` ,
ADD INDEX `PK_motrice_regime_idMotriceRefRegimeType` (`idMotriceRefRegimeType`) USING BTREE ,
DROP INDEX `FK_motrice_regime_idMotriceTrainTranche` ,
ADD INDEX `PK_motrice_regime_idMotriceTrainTranche` (`idMotriceTrainTranche`) USING BTREE ,
ADD INDEX `KEY_motrice_regime_idMotriceRegime` (`idMotriceRegime`) USING BTREE ;

ALTER TABLE `tremas_motrice_regime_tosp`
DROP INDEX `FK_motrice_regime_tosp_idMotriceRefTosp` ,
ADD INDEX `PK_motrice_regime_tosp_idMotriceRefTosp` (`idMotriceRefTosp`) USING BTREE ,
DROP INDEX `FK_motrice_regime_tosp_idMotriceRegime` ,
ADD INDEX `PK_motrice_regime_tosp_idMotriceRegime` (`idMotriceRegime`) USING BTREE ,
ADD INDEX `KEY_motrice_regime_tosp_idMotriceRegimeTosp` (`idMotriceRegimeTosp`) USING BTREE ;

ALTER TABLE `tremas_motrice_regime_stop`
DROP INDEX `FK_motrice_regime_stop_idMotriceRefGare` ,
ADD INDEX `PK_motrice_regime_stop_idMotriceRefGare` (`idMotriceRefGare`) USING BTREE ,
DROP INDEX `FK_motrice_regime_stop_idMotriceRegime` ,
ADD INDEX `PK_motrice_regime_stop_idMotriceRegime` (`idMotriceRegime`) USING BTREE ,
ADD INDEX `KEY_motrice_regime_stop_idMotriceRegimeStop` (`idMotriceRegimeStop`) USING BTREE ;

ALTER TABLE `tremas_motrice_regime_specificity`
DROP INDEX `FK_motrice_regime_specificity_idMotriceRegime` ,
ADD INDEX `PK_motrice_regime_specificity_idMotriceRegime` (`idMotriceRegime`) USING BTREE ,
ADD INDEX `KEY_motrice_regime_specificity_idMotriceRegimeSpecificity` (`idMotriceRegimeSpecificity`) USING BTREE ;

ALTER TABLE `tremas_motrice_regime_service`
DROP INDEX `FK_motrice_regime_service_idDestinationMotriceRefGare` ,
ADD INDEX `PK_motrice_regime_service_idDestinationMotriceRefGare` (`idDestinationMotriceRefGare`) USING BTREE ,
DROP INDEX `FK_motrice_regime_service_idMotriceRefServiceClass` ,
ADD INDEX `PK_motrice_regime_service_idMotriceRefServiceClass` (`idMotriceRefServiceClass`) USING BTREE ,
DROP INDEX `FK_motrice_regime_service_idMotriceRefService` ,
ADD INDEX `PK_motrice_regime_service_idMotriceRefService` (`idMotriceRefService`) USING BTREE ,
DROP INDEX `FK_motrice_regime_service_idMotriceRegime` ,
ADD INDEX `PK_motrice_regime_service_idMotriceRegime` (`idMotriceRegime`) USING BTREE ,
DROP INDEX `FK_motrice_regime_service_idOrigineMotriceRefGare` ,
ADD INDEX `PK_motrice_regime_service_idOrigineMotriceRefGare` (`idOrigineMotriceRefGare`) USING BTREE ,
ADD INDEX `KEY_motrice_regime_service` (`idMotriceRegimeService`) USING BTREE ;

ALTER TABLE `tremas_motrice_regime_satcode`
DROP INDEX `FK_motrice_regime_satcode_idMotriceRefSatcode` ,
ADD INDEX `PK_motrice_regime_satcode_idMotriceRefSatcode` (`idMotriceRefSatcode`) USING BTREE ,
DROP INDEX `FK_motrice_regime_satcode_idMotriceRegime` ,
ADD INDEX `PK_motrice_regime_satcode_idMotriceRegime` (`idMotriceRegime`) USING BTREE ,
ADD INDEX `KEY_motrice_regime_satcode` (`idMotriceRegimeSatcode`) USING BTREE ;

ALTER TABLE `tremas_motrice_regime_restriction`
DROP INDEX `FK_motrice_regime_restriction_idDestinationMotriceRefGare` ,
ADD INDEX `PK_motrice_regime_restriction_idDestinationMotriceRefGare` (`idDestinationMotriceRefGare`) USING BTREE ,
DROP INDEX `FK_motrice_regime_restriction_idMotriceRegime` ,
ADD INDEX `PK_motrice_regime_restriction_idMotriceRegime` (`idMotriceRegime`) USING BTREE ,
DROP INDEX `FK_motrice_regime_restriction_idOrigineMotriceRefGare` ,
ADD INDEX `PK_motrice_regime_restriction_idOrigineMotriceRefGare` (`idOrigineMotriceRefGare`) USING BTREE ,
ADD INDEX `KEY_motrice_regime_restriction` (`idMotriceRegimeRestriction`) USING BTREE ;

ALTER TABLE `tremas_motrice_regime_od`
DROP INDEX `FK_motrice_regime_od_idMotriceRefOd` ,
ADD INDEX `PK_motrice_regime_od_idMotriceRefOd` (`idMotriceRefOd`) USING BTREE ,
DROP INDEX `FK_motrice_regime_od_idMotriceRegime` ,
ADD INDEX `PK_motrice_regime_od_idMotriceRegime` (`idMotriceRegime`) USING BTREE ,
ADD INDEX `KEY_motrice_regime_od` (`idMotriceRegimeOD`) USING BTREE ;

ALTER TABLE `tremas_motrice_regime_mealtype`
DROP INDEX `FK_motrice_regime_mealtype_idMotriceRefMealType` ,
ADD INDEX `PK_motrice_regime_mealtype_idMotriceRefMealType` (`idMotriceRefMealType`) USING BTREE ,
DROP INDEX `FK_motrice_regime_mealtype_idMotriceRegime` ,
ADD INDEX `PK_motrice_regime_mealtype_idMotriceRegime` (`idMotriceRegime`) USING BTREE ,
ADD INDEX `KEY_motrice_regime_mealtype` (`idMotriceRegimeMealType`) USING BTREE ;

ALTER TABLE `tremas_motrice_regime_fareprofile`
DROP INDEX `FK_motrice_regime_fareprofile_idMotriceRefFareProfile` ,
ADD INDEX `PK_motrice_regime_fareprofile_idMotriceRefFareProfile` (`idMotriceRefFareProfile`) USING BTREE ,
DROP INDEX `FK_motrice_regime_fareprofile_idMotriceRegime` ,
ADD INDEX `PK_motrice_regime_fareprofile_idMotriceRegime` (`idMotriceRegime`) USING BTREE ,
ADD INDEX `KEY_motrice_regime_fareprofile` (`idMotriceRegimeFareProfile`) USING BTREE ;

ALTER TABLE `tremas_motrice_regime_eqptype`
DROP INDEX `FK_motrice_regime_eqptype_idMotriceRefEqpType` ,
ADD INDEX `PK_motrice_regime_eqptype_idMotriceRefEqpType` (`idMotriceRefEqpType`) USING BTREE ,
DROP INDEX `FK_motrice_regime_eqptype_idMotriceRegime` ,
ADD INDEX `PK_motrice_regime_eqptype_idMotriceRegime` (`idMotriceRegime`) USING BTREE ,
ADD INDEX `KEY_motrice_regime_eqptype` (`idMotriceRegimeEqpType`) USING BTREE ;

ALTER TABLE `tremas_motrice_regime_distribution`
DROP INDEX `FK_motrice_regime_distribution_idMotriceRefDistribution` ,
ADD INDEX `PK_motrice_regime_distribution_idMotriceRefDistribution` (`idMotriceRefDistribution`) USING BTREE ,
DROP INDEX `FK_motrice_regime_distribution_idMotriceRegime` ,
ADD INDEX `PK_motrice_regime_distribution_idMotriceRegime` (`idMotriceRegime`) USING BTREE ,
ADD INDEX `KEY_motrice_regime_distribution` (`idMotriceRegimeDistribution`) USING BTREE ;

ALTER TABLE `tremas_motrice_regime_composition_coach`
DROP INDEX `FK_motrice_regime_composition_coach_idMotriceRegimeComposition` ,
ADD INDEX `PK_motrice_regime_composition_coach_idMotriceRegimeComposition` (`idMotriceRegimeComposition`) USING BTREE ,
ADD INDEX `KEY_motrice_regime_composition_coach` (`idMotriceRegimeCompositionCoach`) USING BTREE ;

ALTER TABLE `tremas_motrice_regime_composition`
DROP INDEX `FK_motrice_regime_composition_idMotriceRefCodeDiagramme` ,
ADD INDEX `PK_motrice_regime_composition_idMotriceRefCodeDiagramme` (`idMotriceRefCodeDiagramme`) USING BTREE ,
DROP INDEX `FK_motrice_regime_composition_idMotriceRefCompositionClass` ,
ADD INDEX `PK_motrice_regime_composition_idMotriceRefCompositionClass` (`idMotriceRefCompositionClass`) USING BTREE ,
DROP INDEX `FK_motrice_regime_composition_idMotriceRefRameCode` ,
ADD INDEX `PK_motrice_regime_composition_idMotriceRefRameCode` (`idMotriceRefRameCode`) USING BTREE ,
DROP INDEX `FK_motrice_regime_composition_idMotriceRegime` ,
ADD INDEX `PK_motrice_regime_composition_idMotriceRegime` (`idMotriceRegime`) USING BTREE ,
ADD INDEX `KEY_motrice_regime_composition` (`idMotriceRegimeComposition`) USING BTREE ;


ALTER TABLE `tremas_motrice_ref_tosp`
DROP INDEX `ref_tosp_Ucode_idCompagnie` ,
ADD UNIQUE INDEX `KEYUnique_ref_tosp_Ucode_idCompagnie` (`codeMotriceRefTosp`, `idCompagnie`) USING BTREE ,
DROP INDEX `FK_motrice_ref_tosp_idCompagnie` ,
ADD INDEX `PK_motrice_ref_tosp_idCompagnie` (`idCompagnie`) USING BTREE ,
ADD INDEX `KEY_motrice_ref_tosp` (`idMotriceRefTosp`) USING BTREE ;


ALTER TABLE `tremas_motrice_ref_serviceclass`
DROP INDEX `ref_serviceclass_Ulabel_idCompagnie` ,
ADD UNIQUE INDEX `KEYUnique_ref_serviceclass_Ulabel_idCompagnie` (`labelServiceClass`, `idCompagnie`) USING BTREE ,
DROP INDEX `FK_motrice_ref_serviceclass_idCompagnie` ,
ADD INDEX `PK_motrice_ref_serviceclass_idCompagnie` (`idCompagnie`) USING BTREE ,
ADD INDEX `KEY_motrice_ref_serviceclass` (`idMotriceRefServiceClass`) USING BTREE ;

ALTER TABLE `tremas_motrice_ref_service`
DROP INDEX `ref_service_Ulabel_idCompagnie` ,
ADD UNIQUE INDEX `KEYUnique_ref_service_label_idCompagnie` (`labelService`, `idCompagnie`) USING BTREE ,
DROP INDEX `FK_motrice_ref_service_idCompagnie` ,
ADD INDEX `PK_motrice_ref_service_idCompagnie` (`idCompagnie`) USING BTREE ,
ADD INDEX `KEY_motrice_ref_service` (`idMotriceRefService`) USING BTREE ;

ALTER TABLE `tremas_motrice_ref_satcode`
DROP INDEX `ref_satcode_UlabelSatCode_idCompagnie` ,
ADD UNIQUE INDEX `KEYUnique_ref_satcode_labelSatCode_idCompagnie` (`labelSatCode`, `idCompagnie`) USING BTREE ,
DROP INDEX `FK_motrice_ref_satcode_idCompagnie` ,
ADD INDEX `PK_motrice_ref_satcode_idCompagnie` (`idCompagnie`) USING BTREE ,
ADD INDEX `KEY_motrice_ref_satcode` (`idMotriceRefSatCode`) USING BTREE ;

ALTER TABLE `tremas_motrice_ref_regime_type`
ADD INDEX `KEY_motrice_ref_regime_type` (`idMotriceRefRegimeType`) USING BTREE ;

ALTER TABLE `tremas_motrice_ref_ramecode`
DROP INDEX `ref_ramecode_Ulabel_idCompagnie` ,
ADD UNIQUE INDEX `KEYUnique_ref_ramecode_label_idCompagnie` (`labelRameCode`, `idCompagnie`) USING BTREE ,
DROP INDEX `FK_motrice_ref_ramecode_idCompagnie` ,
ADD INDEX `PK_motrice_ref_ramecode_idCompagnie` (`idCompagnie`) USING BTREE ,
ADD INDEX `KEY_motrice_ref_ramecode` (`idMotriceRefRameCode`) USING BTREE ;

ALTER TABLE `tremas_motrice_ref_od2gare`
DROP INDEX `ref_od2gare_UidMotriceRefGare_idMotriceRefOd` ,
ADD UNIQUE INDEX `KEYUnique_ref_od2gare_idMotriceRefGare_idMotriceRefOd` (`idMotriceRefGare`, `idMotriceRefOd`) USING BTREE ,
DROP INDEX `FK_motrice_ref_od2gare_idMotriceRefOd` ,
ADD INDEX `PK_motrice_ref_od2gare_idMotriceRefOd` (`idMotriceRefOd`) USING BTREE ,
ADD INDEX `PK_motrice_ref_od2gare_idMotriceRefGare` (`idMotriceRefGare`) USING BTREE ,
ADD INDEX `KEY_motrice_ref_od2gare` (`idMotriceRefOd2gare`) USING BTREE ;

ALTER TABLE `tremas_motrice_ref_od`
DROP INDEX `ref_od_UcodeGareOrigine_codeGareDestination_idCompagnie` ,
ADD UNIQUE INDEX `KEYUnique_ref_od_codeGareOrigine_codeGareDestination_idCompagnie` (`codeGareDestinationMotriceRefOd`, `codeGareOrigineMotriceRefOd`, `idCompagnie`) USING BTREE ,
DROP INDEX `FK_motrice_ref_od_idCompagnie` ,
ADD INDEX `PK_motrice_ref_od_idCompagnie` (`idCompagnie`) USING BTREE ,
ADD INDEX `KEY_motrice_ref_od` (`idMotriceRefOd`) USING BTREE ;

ALTER TABLE `tremas_motrice_ref_mealtype`
DROP INDEX `ref_mealtype_Ucode_idCompagnie` ,
ADD UNIQUE INDEX `KEYUnique_ref_mealtype_code_idCompagnie` (`codeMealType`, `idCompagnie`) USING BTREE ,
DROP INDEX `FK_motrice_ref_mealtype_idCompagnie` ,
ADD INDEX `PK_motrice_ref_mealtype_idCompagnie` (`idCompagnie`) USING BTREE ,
ADD INDEX `KEY_motrice_ref_mealtype` (`idMotriceRefMealType`) USING BTREE ;

ALTER TABLE `tremas_motrice_ref_gare`
DROP INDEX `ref_gare_UcodeGare_idCompagnie;` ,
ADD UNIQUE INDEX `KEYUnique_ref_gare_codeGare_idCompagnie;` (`codeGareMotriceRefGare`, `idCompagnie`) USING BTREE ,
DROP INDEX `FK_motrice_ref_gare_idCompagnie` ,
ADD INDEX `PK_motrice_ref_gare_idCompagnie` (`idCompagnie`) USING BTREE ,
ADD INDEX `KEY_motrice_ref_gare` (`idMotriceRefGare`) USING BTREE ;

ALTER TABLE `tremas_motrice_ref_fareprofilecode`
DROP INDEX `ref_fareprofilecode_Ulabel_idCompagnie` ,
ADD UNIQUE INDEX `KEYUnique_ref_fareprofilecode_label_idCompagnie` (`labelFareProfileCode`, `idCompagnie`) USING BTREE ,
DROP INDEX `FK_motrice_ref_fareprofilecode_idCompagnie` ,
ADD INDEX `PK_motrice_ref_fareprofilecode_idCompagnie` (`idCompagnie`) USING BTREE ,
ADD INDEX `KEY_motrice_ref_fareprofilecode` (`idMotriceRefFareProfileCode`) USING BTREE ;

ALTER TABLE `tremas_motrice_ref_fareprofilcode`
ADD INDEX `KEY_motrice_ref_fareprofilcode` (`idMotriceRefFareProfileCode`) USING BTREE ;

ALTER TABLE `tremas_motrice_ref_eqptype`
DROP INDEX `ref_eqptype_Ulabel_idCompagnie` ,
ADD UNIQUE INDEX `KEYUnique_ref_eqptype_label_idCompagnie` (`labelEqpType`, `idCompagnie`) USING BTREE ,
DROP INDEX `FK_motrice_ref_eqptype_idCompagnie` ,
ADD INDEX `PK_motrice_ref_eqptype_idCompagnie` (`idCompagnie`) USING BTREE ,
ADD INDEX `KEY_motrice_ref_eqptype` (`idMotriceRefEqpType`) USING BTREE ;

ALTER TABLE `tremas_motrice_ref_distribution`
DROP INDEX `ref_distribution_Ulabel_idCompagnie` ,
ADD UNIQUE INDEX `KEYUnique_ref_distribution_label_idCompagnie` (`labelDistribution`, `idCompagnie`) USING BTREE ,
DROP INDEX `FK_motrice_ref_distribution_idCompagnie` ,
ADD INDEX `PK_motrice_ref_distribution_idCompagnie` (`idCompagnie`) USING BTREE ,
ADD INDEX `KEY_motrice_ref_distribution` (`idMotriceRefDistribution`) USING BTREE ;

ALTER TABLE `tremas_motrice_ref_compositionclass`
DROP INDEX `ref_compositionclass_Ulabel_idCompagnie` ,
ADD UNIQUE INDEX `KEYUnique_ref_compositionclass_label_idCompagnie` (`labelCompositionClass`, `idCompagnie`) USING BTREE ,
DROP INDEX `FK_motrice_ref_compositionclass_idCompagnie` ,
ADD INDEX `PK_motrice_ref_compositionclass_idCompagnie` (`idCompagnie`) USING BTREE ,
ADD INDEX `KEY_motrice_ref_compositionclass` (`idMotriceRefCompositionClass`) USING BTREE ;

ALTER TABLE `tremas_motrice_ref_codediagramme`
DROP INDEX `ref_codediagramme_Ulabel_idCompagnie` ,
ADD UNIQUE INDEX `KEYUnique_ref_codediagramme_label_idCompagnie` (`labelCodeDiagramme`, `idCompagnie`) USING BTREE ,
DROP INDEX `FK_motrice_ref_codediagramme_idCompagnie` ,
ADD INDEX `PK_motrice_ref_codediagramme_idCompagnie` (`idCompagnie`) USING BTREE ,
ADD INDEX `KEY_motrice_ref_codediagramme` (`idMotriceRefCodeDiagramme`) USING BTREE ;

















