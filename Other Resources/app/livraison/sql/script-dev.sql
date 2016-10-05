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
-- Tables de référence + Tables motrice regime
-- ---------------------------------------
DROP TABLE IF EXISTS `tremas_motrice_ref_stop`;

drop table if exists tremas_motrice_regime_composition_coach;

drop table if exists tremas_motrice_regime_composition;

drop table if exists tremas_motrice_regime_distribution;

drop table if exists tremas_motrice_regime_eqptype;

drop table if exists tremas_motrice_regime_fareprofile;

drop table if exists tremas_motrice_regime_mealtype;

drop table if exists tremas_motrice_regime_od;

drop table if exists tremas_motrice_regime_restriction;

drop table if exists tremas_motrice_regime_satcode;

drop table if exists tremas_motrice_regime_service;

drop table if exists tremas_motrice_regime_specificity;

drop table if exists tremas_motrice_regime_stop;

drop table if exists tremas_motrice_regime_tosp;

drop table if exists tremas_motrice_ref_codediagramme;

drop table if exists tremas_motrice_ref_compositionclass;

drop table if exists tremas_motrice_ref_distribution;

drop table if exists tremas_motrice_ref_eqptype;

drop table if exists tremas_motrice_ref_fareprofilecode;

drop table if exists tremas_motrice_ref_gare;

drop table if exists tremas_motrice_ref_mealtype;

drop table if exists tremas_motrice_ref_od;

drop table if exists tremas_motrice_ref_ramecode;

drop table if exists tremas_motrice_ref_satcode;

drop table if exists tremas_motrice_ref_service;

drop table if exists tremas_motrice_ref_serviceclass;

drop table if exists tremas_motrice_ref_tosp;

create table tremas_motrice_ref_codediagramme (
    idMotriceRefCodeDiagramme bigint not null auto_increment,
    labelCodeDiagramme varchar(3) not null,
    idCompagnie integer,
    primary key (idMotriceRefCodeDiagramme)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_compositionclass (
    idMotriceRefCompositionClass bigint not null auto_increment,
    labelCompositionClass varchar(1) not null,
    idCompagnie integer,
    primary key (idMotriceRefCompositionClass)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_distribution (
    idMotriceRefDistribution bigint not null auto_increment,
    labelDistribution varchar(1) not null,
    idCompagnie integer,
    primary key (idMotriceRefDistribution)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_eqptype (
    idMotriceRefEqpType bigint not null auto_increment,
    labelEqpType varchar(3) not null,
    idCompagnie integer,
    primary key (idMotriceRefEqpType)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_fareprofilecode (
    idMotriceRefFareProfileCode bigint not null auto_increment,
    labelFareProfileCode varchar(3) not null,
    idCompagnie integer,
    primary key (idMotriceRefFareProfileCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_gare (
    idMotriceRefGare bigint not null auto_increment,
    codeGareMotriceRefGare varchar(5) not null,
    labelMotriceRefGare varchar(50),
    idCompagnie integer,
    primary key (idMotriceRefGare)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_mealtype (
    idMotriceRefMealType bigint not null auto_increment,
    codeMealType varchar(1) not null,
    labelMealType varchar(50),
    idCompagnie integer,
    primary key (idMotriceRefMealType)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_od (
    idMotriceRefOd bigint not null auto_increment,
    codeGareDestinationMotriceRefOd varchar(5) not null,
    codeGareOrigineMotriceRefOd varchar(5) not null,
    idCompagnie integer,
    primary key (idMotriceRefOd)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_ramecode (
    idMotriceRefRameCode bigint not null auto_increment,
    labelRameCode varchar(6) not null,
    idCompagnie integer,
    primary key (idMotriceRefRameCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_satcode (
    idMotriceRefSatCode bigint not null auto_increment,
    labelSatCode varchar(3) not null,
    idCompagnie integer,
    primary key (idMotriceRefSatCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_service (
    idMotriceRefService bigint not null auto_increment,
    labelService varchar(2) not null,
    idCompagnie integer,
    primary key (idMotriceRefService)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_serviceclass (
    idMotriceRefServiceClass bigint not null auto_increment,
    labelServiceClass varchar(1) not null,
    libelleServiceClass varchar(6),
    idCompagnie integer,
    primary key (idMotriceRefServiceClass)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_tosp (
    idMotriceRefTosp bigint not null auto_increment,
    codeMotriceRefTosp varchar(50) not null,
    idCompagnie integer,
    primary key (idMotriceRefTosp)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_composition (
    idMotriceRegimeComposition bigint not null auto_increment,
    rmCodeMotriceRegimeComposition varchar(3) not null,
    idMotriceRefCodeDiagramme bigint,
    idMotriceRefCompositionClass bigint,
    idMotriceRefRameCode bigint,
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
    idMotriceRefDistribution bigint,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeDistribution)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_eqptype (
    idMotriceRegimeEqpType bigint not null auto_increment,
    idMotriceRefEqpType bigint,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeEqpType)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_fareprofile (
    idMotriceRegimeFareProfile bigint not null auto_increment,
    idMotriceRefFareProfile bigint,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeFareProfile)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_mealtype (
    idMotriceRegimeMealType bigint not null auto_increment,
    beginServiceHourRegimeMealType varchar(8) not null,
    endServiceHourMotriceRegimeMealType varchar(8) not null,
    idMotriceRefMealType bigint,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeMealType)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_od (
    idMotriceRegimeOD bigint not null auto_increment,
    idMotriceRefOd bigint,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeOD)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_restriction (
    idMotriceRegimeRestriction bigint not null auto_increment,
    typeMotriceRegimeRestriction varchar(255) not null,
    idDestinationMotriceRefGare bigint,
    idMotriceRegime bigint,
    idOrigineMotriceRefGare bigint,
    primary key (idMotriceRegimeRestriction)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_satcode (
    idMotriceRegimeSatcode bigint not null auto_increment,
    idMotriceRefSatcode bigint,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeSatcode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_service (
    idMotriceRegimeService bigint not null auto_increment,
    idDestinationMotriceRefGare bigint,
    idMotriceRefServiceClass bigint,
    idMotriceRefService bigint,
    idMotriceRegime bigint,
    idOrigineMotriceRefGare bigint,
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
    idMotriceRefGare bigint,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeStop)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_tosp (
    idMotriceRegimeTosp bigint not null auto_increment,
    idMotriceRefTosp bigint,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeTosp)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

alter table tremas_motrice_ref_codediagramme 
    add constraint FK_motrice_ref_codediagramme_idCompagnie 
    foreign key (idCompagnie) 
    references tremas_compagnie (idCompagnie);

alter table tremas_motrice_ref_compositionclass 
    add constraint FK_motrice_ref_compositionclass_idCompagnie 
    foreign key (idCompagnie) 
    references tremas_compagnie (idCompagnie);

alter table tremas_motrice_ref_distribution 
    add constraint FK_motrice_ref_distribution_idCompagnie 
    foreign key (idCompagnie) 
    references tremas_compagnie (idCompagnie);

alter table tremas_motrice_ref_eqptype 
    add constraint FK_motrice_ref_eqptype_idCompagnie 
    foreign key (idCompagnie) 
    references tremas_compagnie (idCompagnie);

alter table tremas_motrice_ref_fareprofilecode 
    add constraint FK_motrice_ref_fareprofilecode_idCompagnie 
    foreign key (idCompagnie) 
    references tremas_compagnie (idCompagnie);

alter table tremas_motrice_ref_gare 
    add constraint FK_motrice_ref_gare_idCompagnie 
    foreign key (idCompagnie) 
    references tremas_compagnie (idCompagnie);

alter table tremas_motrice_ref_mealtype 
    add constraint FK_motrice_ref_mealtype_idCompagnie 
    foreign key (idCompagnie) 
    references tremas_compagnie (idCompagnie);

alter table tremas_motrice_ref_od 
    add constraint FK_motrice_ref_od_idCompagnie 
    foreign key (idCompagnie) 
    references tremas_compagnie (idCompagnie);

alter table tremas_motrice_ref_ramecode 
    add constraint FK_motrice_ref_ramecode_idCompagnie 
    foreign key (idCompagnie) 
    references tremas_compagnie (idCompagnie);

alter table tremas_motrice_ref_satcode 
    add constraint FK_motrice_ref_satcode_idCompagnie 
    foreign key (idCompagnie) 
    references tremas_compagnie (idCompagnie);

alter table tremas_motrice_ref_service 
    add constraint FK_motrice_ref_service_idCompagnie 
    foreign key (idCompagnie) 
    references tremas_compagnie (idCompagnie);

alter table tremas_motrice_ref_serviceclass 
    add constraint FK_motrice_ref_serviceclass_idCompagnie 
    foreign key (idCompagnie) 
    references tremas_compagnie (idCompagnie);

alter table tremas_motrice_ref_tosp 
    add constraint FK_motrice_ref_tosp_idCompagnie 
    foreign key (idCompagnie) 
    references tremas_compagnie (idCompagnie);

alter table tremas_motrice_regime_composition 
    add constraint FK_motrice_regime_composition_idMotriceRefCodeDiagramme 
    foreign key (idMotriceRefCodeDiagramme) 
    references tremas_motrice_ref_codediagramme (idMotriceRefCodeDiagramme);

alter table tremas_motrice_regime_composition 
    add constraint FK_motrice_regime_composition_idMotriceRefCompositionClass 
    foreign key (idMotriceRefCompositionClass) 
    references tremas_motrice_ref_compositionclass (idMotriceRefCompositionClass);

alter table tremas_motrice_regime_composition 
    add constraint FK_motrice_regime_composition_idMotriceRefRameCode 
    foreign key (idMotriceRefRameCode) 
    references tremas_motrice_ref_ramecode (idMotriceRefRameCode);

alter table tremas_motrice_regime_composition 
    add constraint FK_motrice_regime_composition_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_composition_coach 
    add constraint FK_motrice_regime_composition_coach_idMotriceRegimeComposition 
    foreign key (idMotriceRegimeComposition) 
    references tremas_motrice_regime_composition (idMotriceRegimeComposition);

alter table tremas_motrice_regime_distribution 
    add constraint FK_motrice_regime_distribution_idMotriceRefDistribution 
    foreign key (idMotriceRefDistribution) 
    references tremas_motrice_ref_distribution (idMotriceRefDistribution);

alter table tremas_motrice_regime_distribution 
    add constraint FK_motrice_regime_distribution_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_eqptype 
    add constraint FK_motrice_regime_eqptype_idMotriceRefEqpType 
    foreign key (idMotriceRefEqpType) 
    references tremas_motrice_ref_eqptype (idMotriceRefEqpType);

alter table tremas_motrice_regime_eqptype 
    add constraint FK_motrice_regime_eqptype_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_fareprofile 
    add constraint FK_motrice_regime_fareprofile_idMotriceRefFareProfile 
    foreign key (idMotriceRefFareProfile) 
    references tremas_motrice_ref_fareprofilecode (idMotriceRefFareProfileCode);

alter table tremas_motrice_regime_fareprofile 
    add constraint FK_motrice_regime_fareprofile_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_mealtype 
    add constraint FK_motrice_regime_mealtype_idMotriceRefMealType 
    foreign key (idMotriceRefMealType) 
    references tremas_motrice_ref_mealtype (idMotriceRefMealType);

alter table tremas_motrice_regime_mealtype 
    add constraint FK_motrice_regime_mealtype_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_od 
    add constraint FK_motrice_regime_od_idMotriceRefOd 
    foreign key (idMotriceRefOd) 
    references tremas_motrice_ref_od (idMotriceRefOd);

alter table tremas_motrice_regime_od 
    add constraint FK_motrice_regime_od_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_restriction 
    add constraint FK_motrice_regime_restriction_idDestinationMotriceRefGare 
    foreign key (idDestinationMotriceRefGare) 
    references tremas_motrice_ref_gare (idMotriceRefGare);

alter table tremas_motrice_regime_restriction 
    add constraint FK_motrice_regime_restriction_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_restriction 
    add constraint FK_motrice_regime_restriction_idOrigineMotriceRefGare 
    foreign key (idOrigineMotriceRefGare) 
    references tremas_motrice_ref_gare (idMotriceRefGare);

alter table tremas_motrice_regime_satcode 
    add constraint FK_motrice_regime_satcode_idMotriceRefSatcode 
    foreign key (idMotriceRefSatcode) 
    references tremas_motrice_ref_satcode (idMotriceRefSatCode);

alter table tremas_motrice_regime_satcode 
    add constraint FK_motrice_regime_satcode_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_service 
    add constraint FK_motrice_regime_service_idDestinationMotriceRefGare 
    foreign key (idDestinationMotriceRefGare) 
    references tremas_motrice_ref_gare (idMotriceRefGare);

alter table tremas_motrice_regime_service 
    add constraint FK_motrice_regime_service_idMotriceRefServiceClass 
    foreign key (idMotriceRefServiceClass) 
    references tremas_motrice_ref_serviceclass (idMotriceRefServiceClass);

alter table tremas_motrice_regime_service 
    add constraint FK_motrice_regime_service_idMotriceRefService 
    foreign key (idMotriceRefService) 
    references tremas_motrice_ref_service (idMotriceRefService);

alter table tremas_motrice_regime_service 
    add constraint FK_motrice_regime_service_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_service 
    add constraint FK_motrice_regime_service_idOrigineMotriceRefGare 
    foreign key (idOrigineMotriceRefGare) 
    references tremas_motrice_ref_gare (idMotriceRefGare);

alter table tremas_motrice_regime_specificity 
    add constraint FK_motrice_regime_specificity_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_stop 
    add constraint FK_motrice_regime_stop_idMotriceRefGare 
    foreign key (idMotriceRefGare) 
    references tremas_motrice_ref_gare (idMotriceRefGare);

alter table tremas_motrice_regime_stop 
    add constraint FK_motrice_regime_stop_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

alter table tremas_motrice_regime_tosp 
    add constraint FK_motrice_regime_tosp_idMotriceRefTosp 
    foreign key (idMotriceRefTosp) 
    references tremas_motrice_ref_tosp (idMotriceRefTosp);

alter table tremas_motrice_regime_tosp 
    add constraint FK_motrice_regime_tosp_idMotriceRegime 
    foreign key (idMotriceRegime) 
    references tremas_motrice_regime (idMotriceRegime);

-- ---------------------------------------
-- Ajout index Unique
-- ---------------------------------------

ALTER TABLE `tremas_motrice_ref_codediagramme`
ADD UNIQUE INDEX `ref_codediagramme_Ulabel_idCompagnie` (`labelCodeDiagramme`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_compositionclass`
ADD UNIQUE INDEX `ref_compositionclass_Ulabel_idCompagnie` (`labelCompositionClass`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_distribution`
ADD UNIQUE INDEX `ref_distribution_Ulabel_idCompagnie` (`labelDistribution`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_eqptype`
ADD UNIQUE INDEX `ref_eqptype_Ulabel_idCompagnie` (`labelEqpType`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_fareprofilecode`
ADD UNIQUE INDEX `ref_fareprofilecode_Ulabel_idCompagnie` (`labelFareProfileCode`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_mealtype`
ADD UNIQUE INDEX `ref_mealtype_Ucode_idCompagnie` (`codeMealType`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_ramecode`
ADD UNIQUE INDEX `ref_ramecode_Ulabel_idCompagnie` (`labelRameCode`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_satcode`
ADD UNIQUE INDEX `ref_satcode_UlabelSatCode_idCompagnie` (`labelSatCode`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_service`
ADD UNIQUE INDEX `ref_service_Ulabel_idCompagnie` (`labelService`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_serviceclass`
ADD UNIQUE INDEX `ref_serviceclass_Ulabel_idCompagnie` (`labelServiceClass`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_gare`
ADD UNIQUE INDEX `ref_gare_UcodeGare_idCompagnie;` (`codeGareMotriceRefGare`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_od`
ADD UNIQUE INDEX `ref_od_UcodeGareOrigine_codeGareDestination_idCompagnie` (`codeGareDestinationMotriceRefOd`, `codeGareOrigineMotriceRefOd`, `idCompagnie`) ;

ALTER TABLE `tremas_motrice_ref_tosp`
ADD UNIQUE INDEX `ref_tosp_Ucode_idCompagnie` (`codeMotriceRefTosp`, `idCompagnie`) ;

