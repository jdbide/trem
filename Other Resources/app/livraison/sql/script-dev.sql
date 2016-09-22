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