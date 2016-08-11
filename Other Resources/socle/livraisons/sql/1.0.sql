/* Table des rubriques */
CREATE TABLE `socle_ihm_rubrique` (
  `idRubrique` int(11) NOT NULL AUTO_INCREMENT,
  `libelleRubrique` varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'Nom de la rubrique',
  `actifRubrique` tinyint(1) NOT NULL,
  `ordreRubrique` int(11) NOT NULL COMMENT 'Ordre d''affichage dans le menu par rapport aux autres rubriques',
  PRIMARY KEY (`idRubrique`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Liste des rubriques du menu';

/* Table des chapitres */
CREATE TABLE `socle_ihm_chapitre` (
  `idChapitre` int(11) NOT NULL AUTO_INCREMENT,
  `idRubrique` int(11) NOT NULL COMMENT 'Id de la rubrique contenant le chapitre',
  `libelleChapitre` varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'Nom du chapitre',
  `actifChapitre` tinyint(1) NOT NULL,
  `ordreChapitre` int(11) NOT NULL COMMENT 'Ordre d''affichage par rapport aux autres chapitres de la rubrique',
  PRIMARY KEY (`idChapitre`),
  KEY `FK_ihm_chapitre_idRubrique` (`idRubrique`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Liste des chapitres du menu du socle';

/* Table des pages */
CREATE TABLE `socle_ihm_page` (
  `idPage` int(11) NOT NULL AUTO_INCREMENT,
  `idChapitre` int(11) NOT NULL COMMENT 'Id du chapitre contenant la page',
  `libellePage` varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'Nom de la page',
  `actifPage` tinyint(1) NOT NULL,
  `ordrePage` int(11) NOT NULL COMMENT 'Ordre d''affichage par rapport aux autres pages du chapitre',
  `lienPage` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'URL de la page html',
  PRIMARY KEY (`idPage`),
  KEY `FK_ihm_page_idChapitre` (`idChapitre`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Liste des pages du menu du socle';

