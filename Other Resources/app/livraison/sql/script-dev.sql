-- 21 / 09 / 2016
-- ----------------------------
-- Table structure for `tremas_jeu_donnees_control`
-- ----------------------------
DROP TABLE IF EXISTS `tremas_jeu_donnees_control`;
CREATE TABLE `tremas_jeu_donnees_control` (
  `idJeuDonneesControl` int(11) NOT NULL,
  `dateCreateJeuDonneesControl` datetime NOT NULL,
  `dateLastUpdateJeuDonneesControl` datetime NOT NULL,
  `idUtilisateurCreateJeuDonneesControl` int(11) NOT NULL,
  `idUtilisateurLastUpdateJeuDonneesControl` int(11) NOT NULL,
  `statusJeuDonneesControl` enum('finished','loading') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'loading',
  `pathFileImportJeuDonneesControl` varchar(255) NOT NULL,
  `pathFileReportJeuDonneesControl` varchar(255) NOT NULL,
  `idJeuDonnees` int(11) NOT NULL,
  PRIMARY KEY (`idJeuDonneesControl`),
  KEY `FK_jeuDonneesControl_idJeuDonnees` (`idJeuDonnees`) USING BTREE,
  CONSTRAINT `FK_jeuDonneesControl_idJeuDonnees` FOREIGN KEY (`idJeuDonnees`) REFERENCES `tremas_jeu_donnees` (`idJeuDonnees`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tremas_jeu_donnees_control
-- ----------------------------

-- ---------------------------------------
-- Tables Compagnie et Environnement
-- ---------------------------------------

DROP TABLE IF EXISTS `tremas_compagnie`;
CREATE TABLE `tremas_compagnie` (
  `idCompagnie` int(11) NOT NULL AUTO_INCREMENT,
  `libelleCompagnie` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `nomTechniqueCompagnie` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idCompagnie`),
  UNIQUE KEY `compagnie_UnomTechnique` (`nomTechniqueCompagnie`),
  KEY `compagnie_NidCompagnie` (`idCompagnie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `tremas_compagnie` (`libelleCompagnie`, `nomTechniqueCompagnie`) VALUES ('Eurostar', 'ES');
INSERT INTO `tremas_compagnie` (`libelleCompagnie`, `nomTechniqueCompagnie`) VALUES ('Thalys', 'TH');

DROP TABLE IF EXISTS `tremas_environnement`;
CREATE TABLE `tremas_environnement` (
  `idEnvironnement` int(11) NOT NULL AUTO_INCREMENT,
  `libelleEnvironnement` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `nomTechniqueEnvironnement` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idEnvironnement`),
  UNIQUE KEY `environnement_UnomTechnique` (`nomTechniqueEnvironnement`),
  KEY `environnement_NidEnvironnement` (`idEnvironnement`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `tremas_environnement` (`libelleEnvironnement`, `nomTechniqueEnvironnement`) VALUES ('Recette', 'REC');
INSERT INTO `tremas_environnement` (`libelleEnvironnement`, `nomTechniqueEnvironnement`) VALUES ('Production', 'PROD');

DROP TABLE IF EXISTS `tremas_compagnie_environnement`;
CREATE TABLE `tremas_compagnie_environnement` (
  `idCompagnieEnvironnement` int(11) NOT NULL AUTO_INCREMENT,
  `idCompagnie` int(11),
  `idEnvironnement` int(11),
  `nomTechniqueCompagnieEnvironnement` varchar(50) NOT NULL,
  `actifCompagnieEnvironnement` tinyint(1) NOT NULL,
  `ordreCompagnieEnvironnement` int(11) NOT NULL,
  `commentaireCompagnieEnvironnement` text NOT NULL,
  `idUtilisateurCreateCompagnieEnvironnement` int(11) NOT NULL,
  `dateCreateCompagnieEnvironnement` datetime NOT NULL,
  `idUtilisateurLastUpdateCompagnieEnvironnement` int(11) NOT NULL,
  `dateLastUpdateCompagnieEnvironnement` datetime NOT NULL,
  `idDataSource` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCompagnieEnvironnement`),
  UNIQUE KEY `indImportDonnees_nomTechnique` (`nomTechniqueCompagnieEnvironnement`) USING BTREE,
  UNIQUE KEY `indImportDonnees_idCompagnie_idEnvironnement` (`idCompagnie`,`idEnvironnement`) USING BTREE,
  KEY `tremas_compagnie_environnement_fkDatasource` (`idDataSource`),
  CONSTRAINT `tremas_compagnie_environnement_fkDatasource` FOREIGN KEY (`idDataSource`) REFERENCES `tremas_datasource` (`idDatasource`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `compagnie_environnement_FKidCompagnie` FOREIGN KEY (`idCompagnie`) REFERENCES `tremas_compagnie` (`idCompagnie`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `compagnie_environnement_FKidEnvironnement` FOREIGN KEY (`idEnvironnement`) REFERENCES `tremas_environnement` (`idEnvironnement`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `tremas_compagnie_environnement` VALUES (1, 1, 2, 'ES_PROD', 1, 0, ' ', 1, NOW(), 1, NOW(), 1);
INSERT INTO `tremas_compagnie_environnement` VALUES (2, 2, 2, 'TH_PROD', 1, 1, ' ', 1, NOW(), 1, NOW(), 3);
INSERT INTO `tremas_compagnie_environnement` VALUES (3, 1, 1, 'ES_REC', 1, 2, ' ', 1, NOW(), 1, NOW(), 2);
INSERT INTO `tremas_compagnie_environnement` VALUES (4, 2, 1, 'TH_REC', 1, 3, ' ', 1, NOW(), 1, NOW(), 4);

-- ---------------------------------------
-- Tables de référence
-- ---------------------------------------

CREATE TABLE `tremas_motrice_ref_gare` (
`idMotriceRefGare`  int(11) NOT NULL AUTO_INCREMENT ,
`codeGareMotriceRefGare`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
`labelMotriceRefGare`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL ,
`idCompagnie`  int(11) NULL ,
PRIMARY KEY (`idMotriceRefGare`),
CONSTRAINT `ref_gare_FKidCompagnie` FOREIGN KEY (`idCompagnie`) REFERENCES `tremas_compagnie` (`idCompagnie`) ON DELETE RESTRICT ON UPDATE RESTRICT,
UNIQUE INDEX `ref_gare_UcodeGare_idCompagnie` (`codeGareMotriceRefGare`, `idCompagnie`) 
)
;

CREATE TABLE `tremas_motrice_ref_tosp` (
`idMotriceRefTosp`  int(11) NOT NULL ,
`codeMotriceRefTosp`  varchar(50) NOT NULL ,
`idCompagnie`  int(11) NULL ,
PRIMARY KEY (`idMotriceRefTosp`),
CONSTRAINT `ref_tosp_FKidCompagnie` FOREIGN KEY (`idCompagnie`) REFERENCES `tremas_compagnie` (`idCompagnie`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
;

DROP TABLE IF EXISTS `tremas_motrice_ref_stop`;

-- ---------------------------------------
-- Ajout idCompagnie et index Unique
-- ---------------------------------------

ALTER TABLE `tremas_motrice_ref_codediagramme`
ADD COLUMN `idCompagnie`  int(11) NULL AFTER `labelCodeDiagramme`;
ALTER TABLE `tremas_motrice_ref_codediagramme` ADD CONSTRAINT `ref_codediagramme_FKidCompagnie` FOREIGN KEY (`idCompagnie`) REFERENCES `tremas_compagnie` (`idCompagnie`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `tremas_motrice_ref_codediagramme`
ADD UNIQUE INDEX `ref_codediagramme_Ulabel_idCompagnie` (`labelCodeDiagramme`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_compositionclass`
ADD COLUMN `idCompagnie`  int(11) NULL AFTER `labelCompositionClass`;
ALTER TABLE `tremas_motrice_ref_compositionclass` ADD CONSTRAINT `ref_compositionclass_FKidCompagnie` FOREIGN KEY (`idCompagnie`) REFERENCES `tremas_compagnie` (`idCompagnie`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `tremas_motrice_ref_compositionclass`
ADD UNIQUE INDEX `ref_compositionclass_Ulabel_idCompagnie` (`labelCompositionClass`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_distribution`
ADD COLUMN `idCompagnie`  int(11) NULL AFTER `labelDistribution`;
ALTER TABLE `tremas_motrice_ref_distribution` ADD CONSTRAINT `ref_distribution_FKidCompagnie` FOREIGN KEY (`idCompagnie`) REFERENCES `tremas_compagnie` (`idCompagnie`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `tremas_motrice_ref_distribution`
ADD UNIQUE INDEX `ref_distribution_Ulabel_idCompagnie` (`labelDistribution`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_eqptype`
ADD COLUMN `idCompagnie`  int(11) NULL AFTER `idMotriceRefEqpType`;
ALTER TABLE `tremas_motrice_ref_eqptype` ADD CONSTRAINT `ref_eqptype_FKidCompagnie` FOREIGN KEY (`idCompagnie`) REFERENCES `tremas_compagnie` (`idCompagnie`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `tremas_motrice_ref_eqptype`
DROP COLUMN `idMotriceEqpType`,
MODIFY COLUMN `idMotriceRefEqpType`  bigint(20) NOT NULL AUTO_INCREMENT FIRST ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`idMotriceRefEqpType`),
ADD UNIQUE INDEX `ref_eqptype_Ulabel_idCompagnie` (`labelEqpType`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_fareprofilecode`
ADD COLUMN `idCompagnie`  int(11) NULL AFTER `labelFareProfileCode`;
ALTER TABLE `tremas_motrice_ref_fareprofilecode` ADD CONSTRAINT `ref_fareprofilecode_FKidCompagnie` FOREIGN KEY (`idCompagnie`) REFERENCES `tremas_compagnie` (`idCompagnie`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `tremas_motrice_ref_fareprofilecode`
ADD UNIQUE INDEX `ref_fareprofilecode_Ulabel_idCompagnie` (`labelFareProfileCode`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_mealtype`
ADD COLUMN `idCompagnie`  int(11) NULL AFTER `labelMealType`;
ALTER TABLE `tremas_motrice_ref_mealtype` ADD CONSTRAINT `ref_mealtype_FKidCompagnie` FOREIGN KEY (`idCompagnie`) REFERENCES `tremas_compagnie` (`idCompagnie`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `tremas_motrice_ref_mealtype`
MODIFY COLUMN `labelMealType`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL AFTER `idMotriceRefMealType`,
ADD COLUMN `codeMealType`  varchar(1) NOT NULL AFTER `idMotriceRefMealType`;
ALTER TABLE `tremas_motrice_ref_mealtype`
ADD UNIQUE INDEX `ref_mealtype_Ucode_idCompagnie` (`codeMealType`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_ramecode`
ADD COLUMN `idCompagnie`  int(11) NULL AFTER `labelRameCode`;
ALTER TABLE `tremas_motrice_ref_ramecode` ADD CONSTRAINT `ref_ramecode_FKidCompagnie` FOREIGN KEY (`idCompagnie`) REFERENCES `tremas_compagnie` (`idCompagnie`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `tremas_motrice_ref_ramecode`
ADD UNIQUE INDEX `ref_ramecode_Ulabel_idCompagnie` (`labelRameCode`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_satcode`
ADD COLUMN `idCompagnie`  int(11) NULL AFTER `labelSatCode`;
ALTER TABLE `tremas_motrice_ref_satcode` ADD CONSTRAINT `ref_satcode_FKidCompagnie` FOREIGN KEY (`idCompagnie`) REFERENCES `tremas_compagnie` (`idCompagnie`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `tremas_motrice_ref_satcode`
ADD UNIQUE INDEX `ref_satcode_UlabelSatCode_idCompagnie` (`labelSatCode`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_service`
ADD COLUMN `idCompagnie`  int(11) NULL AFTER `labelService`;
ALTER TABLE `tremas_motrice_ref_service` ADD CONSTRAINT `ref_service_FKidCompagnie` FOREIGN KEY (`idCompagnie`) REFERENCES `tremas_compagnie` (`idCompagnie`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `tremas_motrice_ref_service`
ADD UNIQUE INDEX `ref_service_Ulabel_idCompagnie` (`labelService`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_serviceclass`
ADD COLUMN `idCompagnie`  int(11) NULL AFTER `libelleServiceClass`;
ALTER TABLE `tremas_motrice_ref_serviceclass` ADD CONSTRAINT `ref_serviceclass_FKidCompagnie` FOREIGN KEY (`idCompagnie`) REFERENCES `tremas_compagnie` (`idCompagnie`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `tremas_motrice_ref_serviceclass`
DROP INDEX `labelServiceClass`,
ADD UNIQUE INDEX `ref_serviceclass_Ulabel_idCompagnie` (`labelServiceClass`, `idCompagnie`) ;


