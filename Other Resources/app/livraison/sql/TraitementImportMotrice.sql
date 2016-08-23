drop table if exists tremas_motrice_regime_composition_coach;

drop table if exists tremas_motrice_regime_composition;

drop table if exists tremas_motrice_regime_distribution;

drop table if exists tremas_motrice_regime_eqptype;

drop table if exists tremas_motrice_regime_fareprofile;

drop table if exists tremas_motrice_regime_mealtype;

drop table if exists tremas_motrice_regime_restriction;

drop table if exists tremas_motrice_regime_satcode;

drop table if exists tremas_motrice_regime_service;

drop table if exists tremas_motrice_regime_specificity;

drop table if exists tremas_motrice_regime_stop;

drop table if exists tremas_motrice_regime_od;

drop table if exists tremas_motrice_regime;

drop table if exists tremas_motrice_ref_regime_type;

drop table if exists tremas_motrice_traintranche;

drop table if exists tremas_jeu_donnees;

drop table if exists tremas_motrice_ref_stop;

drop table if exists tremas_motrice_ref_distribution;

drop table if exists tremas_motrice_ref_satcode;

drop table if exists tremas_motrice_ref_fareprofilcode;

drop table if exists tremas_motrice_ref_eqptype;

drop table if exists tremas_motrice_ref_service;

drop table if exists tremas_motrice_ref_mealtype;

drop table if exists tremas_motrice_ref_codediagramme;

drop table if exists tremas_motrice_ref_compositionclass;

drop table if exists tremas_motrice_ref_serviceclass;

drop table if exists tremas_motrice_ref_ramecode;

drop table if exists tremas_ref_tables_motrice_regime;

-- ----------------------------
-- Table structure for `tremas_datasource`
-- ----------------------------
DROP TABLE IF EXISTS `tremas_datasource`;
CREATE TABLE `tremas_datasource` (
  `idDatasource` int(11) NOT NULL AUTO_INCREMENT,
  `nomTechniqueDataSource` varchar(50) NOT NULL,
  `libelleDataSource` varchar(255) NOT NULL,
  `commentaireDataSource` text NOT NULL,
  `driverClassName` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `actifDatasource` tinyint(1) NOT NULL,
  `schema` varchar(50) NOT NULL,
  PRIMARY KEY (`idDatasource`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tremas_datasource
-- ----------------------------
INSERT INTO `tremas_datasource` VALUES ('1', 'ES_DB_PROD', 'Eurostar db production', ' ', 'com.ibm.db2.jcc.DB2Driver', 'jdbc:db2://prd.aiel.sncf.fr:5018/LILLE_DSNC:retrieveMessagesFromServerOnGetMessage=true;', '1', 'F$MDRP2');
INSERT INTO `tremas_datasource` VALUES ('2', 'ES_DB_REC', 'Eurostart db recette', ' ', 'com.ibm.db2.jcc.DB2Driver', ' jdbc:db2://prd.aiel.sncf.fr:5018/LILLE_DSNC:retrieveMessagesFromServerOnGetMessage=true;', '1', 'F$MDRO2');
INSERT INTO `tremas_datasource` VALUES ('3', 'TH_DB_PROD', 'Thalys db production', ' ', 'com.ibm.db2.jcc.DB2Driver', ' jdbc:db2://prd.aiel.sncf.fr:5018/LILLE_DSNC:retrieveMessagesFromServerOnGetMessage=true;', '1', 'F$MDRP3');
INSERT INTO `tremas_datasource` VALUES ('4', 'TH_DB_REC', 'Thalys db recette', ' ', 'com.ibm.db2.jcc.DB2Driver', ' jdbc:db2://prd.aiel.sncf.fr:5018/LILLE_DSNC:retrieveMessagesFromServerOnGetMessage=true;', '1', 'F$MDRO3');

DROP TABLE IF EXISTS `tremas_compagnie_environnement`;
CREATE TABLE `tremas_compagnie_environnement` (
  `idCompagnieEnvironnement` int(11) NOT NULL AUTO_INCREMENT,
  `libelleCompagnie` varchar(50) NOT NULL,
  `libelleEnvironnement` varchar(50) NOT NULL,
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
  UNIQUE KEY `indImportDonnees_libelleCompagnie_libelleEnvironnement` (`libelleCompagnie`,`libelleEnvironnement`) USING BTREE,
  KEY `tremas_compagnie_environnement_fkDatasource` (`idDataSource`),
  CONSTRAINT `tremas_compagnie_environnement_fkDatasource` FOREIGN KEY (`idDataSource`) REFERENCES `tremas_datasource` (`idDatasource`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tremas_compagnie_environnement
-- ----------------------------
INSERT INTO `tremas_compagnie_environnement` VALUES ('1', 'Eurostar', 'Production', 'ES_PROD', '1', '0', ' ', '-1', '2016-07-25 17:16:36', '-1', '2016-07-25 17:16:41', '1');
INSERT INTO `tremas_compagnie_environnement` VALUES ('2', 'Thalys', 'Production', 'TH_PROD', '1', '1', ' ', '-1', '2016-07-25 17:17:29', '-1', '2016-07-25 17:17:33', '3');
INSERT INTO `tremas_compagnie_environnement` VALUES ('3', 'Eurostar', 'Recette', 'ES_REC', '1', '2', ' ', '-1', '2016-07-25 17:18:07', '-1', '2016-07-25 17:18:11', '2');
INSERT INTO `tremas_compagnie_environnement` VALUES ('4', 'Thalys', 'Recette', 'TH_REC', '1', '3', ' ', '-1', '2016-07-25 17:19:38', '-1', '2016-07-25 17:19:43', '4');


CREATE TABLE `tremas_jeu_donnees` (
  `idJeuDonnees` int(11) NOT NULL AUTO_INCREMENT,
  `dateCreateJeuDonnees` datetime NOT NULL,
  `dateLastUpdateJeuDonnees` datetime NOT NULL,
  `idUtilisateurCreateJeuDonnees` int(11) NOT NULL,
  `idUtilisateurLastUpdateJeuDonnees` int(11) NOT NULL,
  `statusJeuDonnees` enum('LASTACTIVE','ACTIVE','DRAFT','IMPORT') COLLATE utf8_bin NOT NULL DEFAULT 'IMPORT',
  `idCompagnieEnvironnement` int(11) NOT NULL,
  PRIMARY KEY (`idJeuDonnees`),
  KEY `FK_jeu_donnees_idCompagnieEnvironnement` (`idCompagnieEnvironnement`),
  CONSTRAINT `FK_jeu_donnees_idCompagnieEnvironnement` FOREIGN KEY (`idCompagnieEnvironnement`) REFERENCES `tremas_compagnie_environnement` (`idCompagnieEnvironnement`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_regime_type (
    idMotriceRefRegimeType bigint not null auto_increment,
    labelRegimeType varchar(30) not null,
    primary key (idMotriceRefRegimeType)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_stop (
    idMotriceRefStop bigint not null auto_increment,
    labelStop varchar(5) not null,
    primary key (idMotriceRefStop)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_distribution (
    idMotriceRefDistribution bigint not null auto_increment,
    labelDistribution varchar(1) not null,
    primary key (idMotriceRefDistribution)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_satcode (
    idMotriceRefSatCode bigint not null auto_increment,
    labelSatCode varchar(3) not null,
    primary key (idMotriceRefSatCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_fareprofilcode (
    idMotriceRefFareProfileCode bigint not null auto_increment,
    labelFareProfileCode varchar(3) not null,
    primary key (idMotriceRefFareProfileCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_eqptype (
    idMotriceEqpType bigint not null auto_increment,
    labelEqpType varchar(3) not null,
    primary key (idMotriceEqpType)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_service (
    idMotriceRefService bigint not null auto_increment,
    labelService varchar(2) not null,
    primary key (idMotriceRefService)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_mealtype (
    idMotriceRefMealType bigint not null auto_increment,
    labelMealType varchar(1) not null,
    primary key (idMotriceRefMealType)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_codediagramme(
    idMotriceRefCodeDiagramme bigint not null auto_increment,
    labelCodeDiagramme varchar(3) not null,
    primary key (idMotriceRefCodeDiagramme)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_compositionclass(
    idMotriceRefCompositionClass bigint not null auto_increment,
    labelCompositionClass varchar(1) not null,
    primary key (idMotriceRefCompositionClass)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_serviceclass(
    idMotriceRefServiceClass bigint not null auto_increment,
    labelServiceClass varchar(1) not null,
    libelleServiceClass varchar(6) not null,
    primary key (idMotriceRefServiceClass)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_ramecode(
    idMotriceRefRameCode bigint not null auto_increment,
    labelRameCode varchar(6) not null,
    primary key (idMotriceRefRameCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime (
    idMotriceRegime bigint not null auto_increment,
    periodMotriceRegime varchar(255) not null,
    idMotriceRefRegimeType bigint,
    idMotriceTrainTranche bigint,
    primary key (idMotriceRegime)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_composition (
    idMotriceRegimeComposition bigint not null auto_increment,
    classCodeMotriceRegimeComposition varchar(1) not null,
    diagCodeMotriceRegimeComposition varchar(3) not null,
    rameCodeMotriceRegimeComposition varchar(6) not null,
    rmCodeMotriceRegimeComposition varchar(3) not null,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeComposition)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_composition_coach (
    idMotriceRegimeCompositionCoach bigint not null auto_increment,
    coachNumberMotriceRegimeCompositionCoach varchar(3) not null,
    idMotriceRegimeComposition bigint,
    primary key (idMotriceRegimeCompositionCoach)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_distribution (
    idMotriceRegimeDistribution bigint not null auto_increment,
    distribIndexMotriceRegimeDistribution varchar(1) not null,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeDistribution)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_eqptype (
    idMotriceRegimeEqpType bigint not null auto_increment,
    eqpTypeMotriceRegimeEqpType varchar(3) not null,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeEqpType)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_fareprofile (
    idMotriceRegimeFareProfile bigint not null auto_increment,
    fareProfileCodeMotriceRegimeFareProfile varchar(3) not null,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeFareProfile)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_mealtype (
    idMotriceRegimeMealTypeEntity bigint not null auto_increment,
    beginServiceHourRegimeMealType varchar(8) not null,
    endServiceHourMotriceRegimeMealType varchar(8) not null,
    mealTypeMotriceRegimeMealType varchar(1) not null,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeMealTypeEntity)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_od (
    idMotriceRegimeOD bigint not null auto_increment,
    destMotriceRegimeOD varchar(5) not null,
    oriMotriceRegimeOD varchar(5) not null,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeOD)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_restriction (
    idMotriceRegimeRestriction bigint not null auto_increment,
    destinationMotriceRegimeRestriction varchar(5),
    origineMotriceRegimeRestriction varchar(5),
    typeMotriceRegimeRestriction varchar(255) not null,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeRestriction)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_satcode (
    idMotriceRegimeSatcode bigint not null auto_increment,
    satCodeMotriceRegimeSatcode varchar(3) not null,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeSatcode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_service (
    idMotriceRegimeService bigint not null auto_increment,
    classMotriceRegimeService varchar(1) not null,
    destMotriceRegimeService varchar(5) not null,
    origMotriceRegimeService varchar(5) not null,
    serviceCodeMotriceRegimeService varchar(2) not null,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeService)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_specificity (
    idMotriceRegimeSpecificity bigint not null auto_increment,
    coachNumberMotriceRegimeSpecificity varchar(3) not null,
    compartmentNumberMotriceRegimeSpecificity varchar(3) not null,
    seatNumberMotriceRegimeSpecificity varchar(3) not null,
    stateCodeMotriceRegimeSpecificity varchar(2) not null,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeSpecificity)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_stop (
    idMotriceRegimeStop bigint not null auto_increment,
    arrivalHourMotriceRegimeStop varchar(4) not null,
    departureHourMotriceRegimeStop varchar(4) not null,
    stationMotriceRegimeStop varchar(5) not null,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeStop)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_traintranche (
    idMotriceTrainTranche bigint not null auto_increment,
    trainNumberMotriceTrainTranche varchar(6) not null,
    trancheNumberMotriceTrainTranche varchar(6) not null,
    trancheStatusMotriceTrainTranche varchar(1) not null,
    validForRRMotriceTrainTranche bit not null,
    idJeuDonnees integer,
    primary key (idMotriceTrainTranche)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `tremas_ref_code_rm`;
CREATE TABLE `tremas_ref_code_rm` (
  `idRefCodeRm` bigint(20) NOT NULL AUTO_INCREMENT,
  `codeRmRefCodeRm` varchar(3) NOT NULL,
  `libelleRefCodeRm` varchar(50) NOT NULL,
  `rame1RefCodeRm` varchar(5) NOT NULL,
  `rame2RefCodeRm` varchar(5) NOT NULL,
  PRIMARY KEY (`idRefCodeRm`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `tremas_ref_rame_classe`;
CREATE TABLE `tremas_ref_rame_classe` (
  `idRefRameClasse` bigint(20) NOT NULL AUTO_INCREMENT,
  `classeRefRameClasse` varchar(5) NOT NULL,
  `codeRameRefRameClasse` varchar(6) NOT NULL,
  `numCompRefRameClasse` int(11) NOT NULL,
  `numResaRefRameClasse` varchar(3) NOT NULL,
  `rameCodeRefRameClasse` varchar(255) NOT NULL,
  PRIMARY KEY (`idRefRameClasse`)
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=latin1;

alter table tremas_motrice_regime 
    add constraint FK_motrice_regime_idMotriceRefRegimeType 
    foreign key (idMotriceRefRegimeType) 
    references tremas_motrice_ref_regime_type (idMotriceRefRegimeType);

alter table tremas_motrice_regime 
    add constraint FK_motrice_regime_idMotriceTrainTranche 
    foreign key (idMotriceTrainTranche) 
    references tremas_motrice_traintranche (idMotriceTrainTranche);

alter table tremas_motrice_regime_composition 
    add constraint FK_motrice_regime_composition_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_composition_coach 
    add constraint FK_motrice_regime_composition_coach_idMotriceRegimeComposition 
    foreign key (idMotriceRegimeComposition) 
    references tremas_motrice_regime_composition (idMotriceRegimeComposition);

alter table tremas_motrice_regime_distribution 
    add constraint FK_motrice_regime_distribution_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_eqptype 
    add constraint FK_motrice_regime_eqptype_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_fareprofile 
    add constraint FK_motrice_regime_fareprofile_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_mealtype 
    add constraint FK_motrice_regime_mealtype_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_od 
    add constraint FK_motrice_regime_od_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_restriction 
    add constraint FK_motrice_regime_restriction_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_satcode 
    add constraint FK_motrice_regime_satcode_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_service 
    add constraint FK_motrice_regime_service_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_specificity 
    add constraint FK_motrice_regime_specificity_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_stop 
    add constraint FK_motrice_regime_stop_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_traintranche 
    add constraint FK_motrice_regime_traintranche_idJeuDonnees 
    foreign key (idJeuDonnees) 
    references tremas_jeu_donnees (idJeuDonnees);
    
create table tremas_ref_tables_motrice_regime (
    idRefTablesMotriceRegime bigint not null auto_increment,
    actifRefTablesMotriceRegime bit not null,
    libelleRefTablesMotriceRegime varchar(255),
    ordreRefTablesMotriceRegime bigint,
    primary key (idRefTablesMotriceRegime)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
INSERT INTO `tremas_ref_tables_motrice_regime` VALUES ('1', 1, 'MotriceRegimeCompositionEntity', null);
INSERT INTO `tremas_ref_tables_motrice_regime` VALUES ('2', 1, 'MotriceRegimeDistributionEntity', null);
INSERT INTO `tremas_ref_tables_motrice_regime` VALUES ('3', 1, 'MotriceRegimeEqpTypeEntity', null);
INSERT INTO `tremas_ref_tables_motrice_regime` VALUES ('4', 1, 'MotriceRegimeFareProfileEntity', null);
INSERT INTO `tremas_ref_tables_motrice_regime` VALUES ('5', 1, 'MotriceRegimeMealTypeEntity', null);
INSERT INTO `tremas_ref_tables_motrice_regime` VALUES ('6', 1, 'MotriceRegimeRestrictionEntity', null);
INSERT INTO `tremas_ref_tables_motrice_regime` VALUES ('7', 1, 'MotriceRegimeSatcodeEntity', null);
INSERT INTO `tremas_ref_tables_motrice_regime` VALUES ('8', 1, 'MotriceRegimeServiceEntity', null);
INSERT INTO `tremas_ref_tables_motrice_regime` VALUES ('9', 1, 'MotriceRegimeSpecificityEntity', null);
INSERT INTO `tremas_ref_tables_motrice_regime` VALUES ('10', 1, 'MotriceRegimeStopEntity', null);
INSERT INTO `tremas_ref_tables_motrice_regime` VALUES ('11', 1, 'MotriceRegimeODEntity', null);

INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'C30', 'Half-Leisure', '15H', '14B' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'CHE', 'Business (Charter)', '05E', '06E' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'CHF', 'Leisure', '13H', '14H' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'CHG', 'Standard class only', '13B', '14B' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'CHH', 'Leisure', '15H', '16H' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'CHI', 'Leisure', '83H', '84H' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'CHJ', 'Standard class only', '83B', '84B' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'CHK', 'Business (Charter)', '83A', '56E' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'CTA', 'First Class (1 SP)', '91D', '56E' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'CTB', 'First class (2 & 3 SP)', '92H', '56E' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'CTC', 'First class (1, 2 & 3 SP)', '83H', '56E' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'CTD', 'Standard Premier (15 & 16 BP)', '83H', '94A' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'CTE', 'Standard Premier (16 BP)', '83H', '94H' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'CTF', 'Half SP (14, 15, 16 SP)', '83B', '84H' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'CTG', 'Half SP (1 BP, 2 & 3 SP)', '83B', '94H' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'CTH', 'Half BP (1 & 2 BP, 3 SP)', '83B', '94D' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'LDA', 'First Class (16 SP)', '83A', '94D' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'LDB', 'First class (14 & 15 SP)', '83A', '94H' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'LDC', 'First class (14, 15 & 16 SP)', '83A', '84H' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'LDD', 'Standard Premier (1 & 2 BP)', '81H', '84H' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'LDE', 'Standard Premier (1 BP)', '92H', '84H' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'LDF', 'Half SP (1, 2, 3 SP)', '83H', '84B' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'LDG', 'Half SP (1 BP, 2 & 3 SP)', '92H', '84B' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'LDH', 'Half BP (1SP, 2 & 3 BP)', '91D', '84B' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'S30', 'Half-First 12 Business/10+11 Leisure', '13B', '26H' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'SHL', 'First class (9 Leisure)', '21H', '14A' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'SHT', 'Strong Business', '23H', '14A' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'SHU', 'First class (Business/Leisure)', '15H', '14A' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'SHV', 'First class 11+12 Business/Other Leisure', '15H', '24A' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'SHW', 'Half-First 12 Business/Other Leisure', '15H', '26H' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'U30', 'Half-First 9 & 8 Leisure/7 Business', '23H', '14B' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'UKL', 'First class (10 Leisure)', '13A', '21A' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'UKT', 'Strong Business', '13A', '26H' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'UKU', 'First class (Business/Leisure)', '13A', '16H' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'UKV', 'First class (7 & 8 Business)', '21H', '16H' );
INSERT INTO tremas_ref_code_rm ( codeRmRefCodeRm, libelleRefCodeRm, rame1RefCodeRm, rame2RefCodeRm ) VALUES ( 'UKW', 'Half-First (7 Business/Other Leisure)', '23H', '16H' );



INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','02034D','1','34','02D');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','02034D','2','034','02D');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','02034D','3','034','02D');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','02034D','4','034','02D');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('     ','02034D','1','035','02D');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('     ','02034D','1','036','02D');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','02034D','1','037','02D');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','02034D','2','037','02D');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','02034D','3','037','02D');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','02034D','4','037','02D');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','02034D','5','037','02D');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','1','001','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','2','001','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','3','001','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','4','001','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','5','001','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','6','001','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','7','001','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','1','002','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','2','002','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','3','002','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','4','002','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','5','002','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','6','002','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','7','002','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','8','002','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','1','003','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','2','003','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','3','003','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','4','003','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','5','003','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','6','003','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','7','003','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','8','003','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','1','004','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','2','004','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','3','004','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','4','004','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','5','004','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','6','004','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','7','004','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','8','004','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','1','005','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','2','005','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','3','005','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','4','005','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','5','005','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','6','005','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','7','005','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','31001H','8','005','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('     ','31001H','1','006','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','31001H','1','007','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','31001H','2','007','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','31001H','3','007','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','31001H','4','007','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','31001H','5','007','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','31001H','6','007','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','31001H','7','007','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','31001H','1','008','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','31001H','2','008','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','31001H','3','008','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','31001H','4','008','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','31001H','5','008','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','31001H','6','008','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','31001H','7','008','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','31001H','1','009','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','31001H','2','009','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','31001H','3','009','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','31001H','4','009','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','31001H','5','009','31H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','1','001','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','2','001','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','3','001','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','4','001','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','5','001','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','6','001','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','7','001','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','1','002','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','2','002','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','3','002','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','4','002','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','5','002','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','6','002','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','7','002','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','8','002','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','1','003','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','2','003','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','3','003','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','4','003','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','5','003','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','6','003','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','7','003','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','8','003','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','1','004','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','2','004','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','3','004','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','4','004','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','5','004','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','6','004','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','7','004','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','8','004','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','1','005','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','2','005','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','3','005','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','4','005','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','5','005','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','6','005','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','7','005','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','32001H','8','005','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('     ','32001H','1','006','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','32001H','1','007','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','32001H','2','007','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','32001H','3','007','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','32001H','4','007','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','32001H','5','007','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','32001H','6','007','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','32001H','7','007','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','32001H','1','008','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','32001H','2','008','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','32001H','3','008','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','32001H','4','008','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','32001H','5','008','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','32001H','6','008','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','32001H','7','008','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','32001H','1','009','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','32001H','2','009','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','32001H','3','009','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','32001H','4','009','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','32001H','5','009','32H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','1','001','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','2','001','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','3','001','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','4','001','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','5','001','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','6','001','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','7','001','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','1','002','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','2','002','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','3','002','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','4','002','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','5','002','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','6','002','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','7','002','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','8','002','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','1','003','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','2','003','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','3','003','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','4','003','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','5','003','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','6','003','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','7','003','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','8','003','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','1','004','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','2','004','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','3','004','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','4','004','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','5','004','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','6','004','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','7','004','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','8','004','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','1','005','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','2','005','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','3','005','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','4','005','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','5','005','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','6','005','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','7','005','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','33001H','8','005','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('     ','33001H','1','006','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','1','007','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','2','007','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','3','007','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','4','007','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','5','007','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','6','007','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','7','007','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','1','008','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','2','008','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','3','008','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','4','008','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','5','008','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','6','008','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','7','008','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','1','009','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','2','009','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','3','009','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','4','009','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','33001H','5','009','33H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','1','010','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','2','010','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','3','010','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','4','010','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','5','010','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','1','011','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','2','011','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','3','011','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','4','011','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','5','011','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','6','011','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','7','011','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','1','012','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','2','012','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','3','012','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','4','012','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','5','012','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','6','012','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','34010A','7','012','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('     ','34010A','1','013','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','1','014','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','2','014','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','3','014','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','4','014','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','5','014','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','6','014','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','7','014','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','8','014','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','1','015','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','2','015','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','3','015','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','4','015','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','5','015','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','6','015','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','7','015','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','8','015','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','1','016','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','2','016','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','3','016','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','4','016','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','5','016','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','6','016','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','7','016','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','8','016','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','1','017','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','2','017','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','3','017','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','4','017','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','5','017','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','6','017','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','7','017','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','8','017','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','1','018','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','2','018','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','3','018','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','4','018','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','5','018','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','6','018','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010A','7','018','34A');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','1','010','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','2','010','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','3','010','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','4','010','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','5','010','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','1','011','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','2','011','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','3','011','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','4','011','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','5','011','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','6','011','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','7','011','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','1','012','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','2','012','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','3','012','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','4','012','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','5','012','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','6','012','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','7','012','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('     ','34010B','1','013','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','1','014','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','2','014','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','3','014','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','4','014','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','5','014','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','6','014','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','7','014','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','8','014','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','1','015','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','2','015','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','3','015','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','4','015','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','5','015','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','6','015','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','7','015','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','8','015','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','1','016','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','2','016','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','3','016','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','4','016','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','5','016','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','6','016','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','7','016','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','8','016','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','1','017','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','2','017','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','3','017','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','4','017','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','5','017','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','6','017','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','7','017','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','8','017','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','1','018','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','2','018','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','3','018','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','4','018','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','5','018','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','6','018','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010B','7','018','34B');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','1','010','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','2','010','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','3','010','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','4','010','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','5','010','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','1','011','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','2','011','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','3','011','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','4','011','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','5','011','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','6','011','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','7','011','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','1','012','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','2','012','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','3','012','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','4','012','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','5','012','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','6','012','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','34010H','7','012','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('     ','34010H','1','013','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','1','014','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','2','014','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','3','014','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','4','014','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','5','014','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','6','014','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','7','014','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','8','014','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','1','015','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','2','015','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','3','015','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','4','015','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','5','015','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','6','015','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','7','015','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','8','015','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','1','016','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','2','016','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','3','016','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','4','016','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','5','016','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','6','016','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','7','016','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','8','016','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','1','017','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','2','017','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','3','017','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','4','017','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','5','017','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','6','017','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','7','017','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','8','017','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','1','018','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','2','018','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','3','018','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','4','018','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','5','018','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','6','018','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','34010H','7','018','34H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','1','001','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','2','001','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','3','001','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','4','001','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','5','001','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','6','001','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','7','001','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','1','002','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','2','002','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','3','002','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','4','002','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','5','002','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','6','002','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','7','002','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','8','002','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','1','003','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','2','003','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','3','003','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','4','003','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','5','003','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','6','003','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','7','003','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','8','003','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','1','004','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','2','004','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','3','004','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','4','004','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','5','004','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','6','004','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','7','004','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','8','004','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','1','005','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','2','005','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','3','005','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','4','005','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','5','005','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','6','005','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','7','005','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','41001H','8','005','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('     ','41001H','1','006','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','41001H','1','007','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','41001H','2','007','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','41001H','3','007','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','41001H','4','007','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','41001H','5','007','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','41001H','6','007','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','41001H','7','007','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','41001H','1','008','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','41001H','2','008','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','41001H','3','008','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','41001H','4','008','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','41001H','5','008','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','41001H','6','008','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','41001H','7','008','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','41001H','1','009','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','41001H','2','009','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','41001H','3','009','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','41001H','4','009','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','41001H','5','009','41H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','1','001','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','2','001','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','3','001','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','4','001','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','5','001','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','6','001','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','7','001','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','1','002','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','2','002','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','3','002','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','4','002','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','5','002','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','6','002','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','7','002','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','8','002','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','1','003','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','2','003','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','3','003','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','4','003','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','5','003','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','6','003','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','7','003','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','8','003','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','1','004','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','2','004','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','3','004','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','4','004','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','5','004','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','6','004','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','7','004','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','8','004','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','1','005','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','2','005','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','3','005','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','4','005','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','5','005','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','6','005','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','7','005','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('B    ','42001H','8','005','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('     ','42001H','1','006','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','42001H','1','007','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','42001H','2','007','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','42001H','3','007','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','42001H','4','007','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','42001H','5','007','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','42001H','6','007','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('A    ','42001H','7','007','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','42001H','1','008','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','42001H','2','008','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','42001H','3','008','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','42001H','4','008','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','42001H','5','008','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','42001H','6','008','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','42001H','7','008','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','42001H','1','009','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','42001H','2','009','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','42001H','3','009','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','42001H','4','009','42H');
INSERT INTO tremas_ref_rame_classe(classeRefRameClasse,codeRameRefRameClasse,numCompRefRameClasse,numResaRefRameClasse,rameCodeRefRameClasse) VALUES ('H    ','42001H','5','009','42H');

INSERT INTO tremas_motrice_ref_regime_type VALUES(1, "Regime train tranche");
INSERT INTO tremas_motrice_ref_regime_type VALUES(2, "Regime stop");
INSERT INTO tremas_motrice_ref_regime_type VALUES(3, "Regime service");
INSERT INTO tremas_motrice_ref_regime_type VALUES(4, "Regime specificity");
INSERT INTO tremas_motrice_ref_regime_type VALUES(5, "Regime restriction");
INSERT INTO tremas_motrice_ref_regime_type VALUES(6, "Regime satcode");
INSERT INTO tremas_motrice_ref_regime_type VALUES(7, "Regime fareprofile");
INSERT INTO tremas_motrice_ref_regime_type VALUES(8, "Regime eqptype");
INSERT INTO tremas_motrice_ref_regime_type VALUES(9, "Regime mealtype");
INSERT INTO tremas_motrice_ref_regime_type VALUES(10, "Regime distribution");
INSERT INTO tremas_motrice_ref_regime_type VALUES(11, "Regime composition");
INSERT INTO tremas_motrice_ref_regime_type VALUES(12, "Regime OD");

