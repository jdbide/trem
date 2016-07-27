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

drop table if exists tremas_motrice_regime;

drop table if exists tremas_motrice_ref_regime_type;

drop table if exists tremas_motrice_traintranche;

drop table if exists tremas_jeu_donnees;

create table tremas_jeu_donnees (
        idJeuDonnees integer not null auto_increment,
        actifJeuDonnees bit not null,
        commentaireJeuDonnees longtext not null,
        company varchar(2) not null,
        dateCreateJeuDonnees datetime not null,
        dateLastUpdateJeuDonnees datetime not null,
        environment varchar(255) not null,
        idUtilisateurCreateJeuDonnees integer not null,
        idUtilisateurLastUpdateJeuDonnees integer not null,
        libelleJeuDonnees varchar(50) not null,
        nomTechniqueJeuDonnees varchar(50) not null,
        ordreJeuDonnees integer not null,
        statusJeuDonnees bit not null,
        primary key (idJeuDonnees)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_ref_regime_type (
    idMotriceRefRegimeType bigint not null auto_increment,
    labelRegimeType varchar(30) not null,
    primary key (idMotriceRefRegimeType)
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
    origCodeMotriceRegimeComposition varchar(3) not null,
    rameCodeMotriceRegimeComposition varchar(6) not null,
    rmCodeMotriceRegimeComposition varchar(6) not null,
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
    eqpTypeRegimeEqpType varchar(3) not null,
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
    beginServiceHourRegimeMealType datetime not null,
    endServiceHourRegimeMealType datetime not null,
    mealTypeMotriceRegimeMealType varchar(1) not null,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeMealTypeEntity)
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
    serviceCodeMotriceRegimeService varchar(1) not null,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeService)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_specificity (
    idMotriceRegimeSpecificity bigint not null auto_increment,
    coachNumber varchar(3) not null,
    compartmentNumber varchar(3) not null,
    seatNumber varchar(3) not null,
    stateCode varchar(2) not null,
    idMotriceRegime bigint,
    primary key (idMotriceRegimeSpecificity)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create table tremas_motrice_regime_stop (
    idMotriceRegimeStop bigint not null auto_increment,
    arrivalHour varchar(4) not null,
    departureHour varchar(4) not null,
    station varchar(5) not null,
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
