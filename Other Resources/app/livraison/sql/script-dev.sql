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
