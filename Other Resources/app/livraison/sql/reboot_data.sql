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

drop table if exists tremas_motrice_traintranche;

drop table if exists tremas_jeu_donnees;

CREATE TABLE `tremas_jeu_donnees` (
  `idJeuDonnees` int(11) NOT NULL AUTO_INCREMENT,
  `dateCreateJeuDonnees` datetime NOT NULL,
  `dateLastUpdateJeuDonnees` datetime NOT NULL,
  `dateDebutPeriodeJeuDonnees` datetime NOT NULL,
  `idUtilisateurCreateJeuDonnees` int(11) NOT NULL,
  `idUtilisateurLastUpdateJeuDonnees` int(11) NOT NULL,
  `statusJeuDonnees` enum('LASTACTIVE','ACTIVE','DRAFT','IMPORT') COLLATE utf8_bin NOT NULL DEFAULT 'IMPORT',
  `idCompagnieEnvironnement` int(11) NOT NULL,
  PRIMARY KEY (`idJeuDonnees`),
  KEY `FK_jeu_donnees_idCompagnieEnvironnement` (`idCompagnieEnvironnement`),
  CONSTRAINT `FK_jeu_donnees_idCompagnieEnvironnement` FOREIGN KEY (`idCompagnieEnvironnement`) REFERENCES `tremas_compagnie_environnement` (`idCompagnieEnvironnement`)
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
