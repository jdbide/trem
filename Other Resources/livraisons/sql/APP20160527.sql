--CREATION de la base tremas

CREATE TABLE `tremas_import_tmdavth` (
  `idTMDAVTH` bigint(20) NOT NULL AUTO_INCREMENT,
  `AVTH_DHDO` varchar(255) DEFAULT NULL,
  `AVTH_LIBS_AVAL_COD` varchar(255) DEFAULT NULL,
  `AVTH_TRCH_COD_CIE` varchar(255) DEFAULT NULL,
  `AVTH_TRCH_IND_FER` varchar(255) DEFAULT NULL,
  `AVTH_TRCH_NUM` varchar(255) DEFAULT NULL,
  `AVTH_TRCH_NUM_TRA1` varchar(255) DEFAULT NULL,
  `AVTH_USER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDAVTH`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdavtr` (
  `idTMDAVTR` bigint(20) NOT NULL AUTO_INCREMENT,
  `AVTR_DHDO` varchar(255) DEFAULT NULL,
  `AVTR_LIBS_AVAL_COD` varchar(255) DEFAULT NULL,
  `AVTR_TRA1_COD_CIE` varchar(255) DEFAULT NULL,
  `AVTR_TRA1_IND_FER` varchar(255) DEFAULT NULL,
  `AVTR_TRA1_NUM_TRA1` varchar(255) DEFAULT NULL,
  `AVTR_USER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDAVTR`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdcath` (
  `idTMDCATH` bigint(20) NOT NULL AUTO_INCREMENT,
  `CATH_CIRR_COD_CIE` varchar(255) DEFAULT NULL,
  `CATH_DHDO` varchar(255) DEFAULT NULL,
  `CATH_EN_RESA` varchar(255) DEFAULT NULL,
  `CATH_ETAT_TRCH` varchar(255) DEFAULT NULL,
  `CATH_NUM` varchar(255) DEFAULT NULL,
  `CATH_REGI` varchar(255) DEFAULT NULL,
  `CATH_SSIM` varchar(255) DEFAULT NULL,
  `CATH_TRCH_COD_CIE` varchar(255) DEFAULT NULL,
  `CATH_TRCH_IND_FER` varchar(255) DEFAULT NULL,
  `CATH_TRCH_NUM` varchar(255) DEFAULT NULL,
  `CATH_TRCH_NUM_TRA1` varchar(255) DEFAULT NULL,
  `CATH_USER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDCATH`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdcatr` (
  `idTMDCATR` bigint(20) NOT NULL AUTO_INCREMENT,
  `CATR_DHDO` varchar(255) DEFAULT NULL,
  `CATR_IND_TRA1_RESA` varchar(255) DEFAULT NULL,
  `CATR_LIBS_OURE_COD` varchar(255) DEFAULT NULL,
  `CATR_LIBS_QLCO_COD` varchar(255) DEFAULT NULL,
  `CATR_NUM` varchar(255) DEFAULT NULL,
  `CATR_PARITE` varchar(255) DEFAULT NULL,
  `CATR_READ_COD` varchar(255) DEFAULT NULL,
  `CATR_REGI` varchar(255) DEFAULT NULL,
  `CATR_TRA1_COD_CIE` varchar(255) DEFAULT NULL,
  `CATR_TRA1_IND_FER` varchar(255) DEFAULT NULL,
  `CATR_TRA1_NUM_TRA1` varchar(255) DEFAULT NULL,
  `CATR_TYEQ_COD` varchar(255) DEFAULT NULL,
  `CATR_USER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDCATR`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdcdcl` (
  `idTMDCDCL` bigint(20) NOT NULL AUTO_INCREMENT,
  `CDCL_CDEM_COD_CIE` varchar(255) DEFAULT NULL,
  `CDCL_CDEM_IND_FER` varchar(255) DEFAULT NULL,
  `CDCL_CDEM_NUM_COND` varchar(255) DEFAULT NULL,
  `CDCL_CDEM_NUM_TRA1` varchar(255) DEFAULT NULL,
  `CDCL_CLBA_COD` varchar(255) DEFAULT NULL,
  `CDCL_DHDO` varchar(255) DEFAULT NULL,
  `CDCL_USER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDCDCL`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdcdds` (
  `idTMDCDDS` bigint(20) NOT NULL AUTO_INCREMENT,
  `CDDS_CDEM_COD_CIE` varchar(255) DEFAULT NULL,
  `CDDS_CDEM_IND_FER` varchar(255) DEFAULT NULL,
  `CDDS_CDEM_NUM_COND` varchar(255) DEFAULT NULL,
  `CDDS_CDEM_NUM_TRA1` varchar(255) DEFAULT NULL,
  `CDDS_DHDO` varchar(255) DEFAULT NULL,
  `CDDS_INPT_RR_DESC` varchar(255) DEFAULT NULL,
  `CDDS_INPT_RR_MONT` varchar(255) DEFAULT NULL,
  `CDDS_USER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDCDDS`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdcdem` (
  `idTMDCDEM` bigint(20) NOT NULL AUTO_INCREMENT,
  `CDEM_DHDO` varchar(255) DEFAULT NULL,
  `CDEM_LIBS_TYCO_COD` varchar(255) DEFAULT NULL,
  `CDEM_NUM_CONDITION` varchar(255) DEFAULT NULL,
  `CDEM_REGI` varchar(255) DEFAULT NULL,
  `CDEM_TRA1_COD_CIE` varchar(255) DEFAULT NULL,
  `CDEM_TRA1_IND_FER` varchar(255) DEFAULT NULL,
  `CDEM_TRA1_NUM_TRA1` varchar(255) DEFAULT NULL,
  `CDEM_USER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDCDEM`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmddstr` (
  `idTMDDSTR` bigint(20) NOT NULL AUTO_INCREMENT,
  `DSTR_TRA1_COD_CIE` varchar(255) DEFAULT NULL,
  `DSTR_TRA1_NUM_TRA1` varchar(255) DEFAULT NULL,
  `DSTR_TRA1_IND_FER` varchar(255) DEFAULT NULL,
  `DSTR_NUM` decimal(10,0) DEFAULT NULL,
  `DSTR_REGI` varchar(255) DEFAULT NULL,
  `DSTR_ETAT_TRA1` varchar(255) DEFAULT NULL,
  `DSTR_COD_MISSION` varchar(255) DEFAULT NULL,
  `DSTR_TYP_DES` varchar(255) DEFAULT NULL,
  `DSTR_REF_IHM` varchar(255) DEFAULT NULL,
  `DSTR_LIBS_IND_JALO` varchar(255) DEFAULT NULL,
  `DSTR_USER` varchar(255) DEFAULT NULL,
  `DSTR_DHDO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDDSTR`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdetvo` (
  `idTMDETVO` bigint(20) NOT NULL AUTO_INCREMENT,
  `ETVO_DHDO` varchar(255) DEFAULT NULL, 
  `ETVO_NUM` varchar(255) DEFAULT NULL,
  `ETVO_REGI` varchar(255) DEFAULT NULL,
  `ETVO_USER` varchar(255) DEFAULT NULL,
  `ETVO_VOIT_COD_CIE` varchar(255) DEFAULT NULL,
  `ETVO_VOIT_IND_FER` varchar(255) DEFAULT NULL,
  `ETVO_VOIT_NUM` varchar(255) DEFAULT NULL,
  `ETVO_VOIT_NUM_TRA1` varchar(255) DEFAULT NULL,
  `ETVO_VOIT_TRCH_NUM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDETVO`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdgads` (
  `idTMDGADS` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `GADS_CAST_COD_STAT` varchar(255) DEFAULT NULL,
  `GADS_DEB_ARRET` varchar(255) DEFAULT NULL,
  `GADS_DHDO` varchar(255) DEFAULT NULL,
  `GADS_DSTR_COD_CIE` varchar(255) DEFAULT NULL,
  `GADS_DSTR_IND_FER` varchar(255) DEFAULT NULL,
  `GADS_DSTR_NUM` varchar(255) DEFAULT NULL,
  `GADS_DSTR_NUM_TRA1` varchar(255) DEFAULT NULL,
  `GADS_FIN_ARRET` varchar(255) DEFAULT NULL,
  `GADS_IND_CIRC_THO` varchar(255) DEFAULT NULL,
  `GADS_IND_PT_FRONT` varchar(255) DEFAULT NULL,
  `GADS_INPT_RR_GAR` varchar(255) DEFAULT NULL,
  `GADS_NUM_GAR` varchar(255) DEFAULT NULL,
  `GADS_TYP_ARRET` varchar(255) DEFAULT NULL,
  `GADS_USER` varchar(255) DEFAULT NULL,
  `GADS_VAL_PARITE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDGADS`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdkapp` (
  `idTMDKAPP` bigint(20) NOT NULL AUTO_INCREMENT,
  `KAPP_APP` varchar(255) DEFAULT NULL,
  `KAPP_CON` varchar(255) DEFAULT NULL,
  `KAPP_DEX` varchar(255) DEFAULT NULL,
  `KAPP_DHDO` varchar(255) DEFAULT NULL,
  `KAPP_DTR` varchar(255) DEFAULT NULL,
  `KAPP_GLO` varchar(255) DEFAULT NULL,
  `KAPP_INF` varchar(255) DEFAULT NULL,
  `KAPP_MOD` varchar(255) DEFAULT NULL,
  `KAPP_ORI` varchar(255) DEFAULT NULL,
  `KAPP_QAL` varchar(255) DEFAULT NULL,
  `KAPP_SER` varchar(255) DEFAULT NULL,
  `KAPP_TIT` varchar(255) DEFAULT NULL,
  `KAPP_TRA` varchar(255) DEFAULT NULL,
  `KAPP_USER` varchar(255) DEFAULT NULL,
  `KAPP_VAL` varchar(255) DEFAULT NULL,
  `KAPP_VER` varchar(255) DEFAULT NULL,
  `KAPP_VES` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDKAPP`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdpare` (
  `idTMDPARE` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARE_DHDO` varchar(255) DEFAULT NULL,
  `PARE_H_DEB_SERV` varchar(255) DEFAULT NULL,
  `PARE_H_FIN_SERV` varchar(255) DEFAULT NULL,
  `PARE_IND_PLACE` varchar(255) DEFAULT NULL,
  `PARE_IND_VOIT_REST` varchar(255) DEFAULT NULL,
  `PARE_NB_REPAS` varchar(255) DEFAULT NULL,
  `PARE_NUM_REST` varchar(255) DEFAULT NULL,
  `PARE_REGI` varchar(255) DEFAULT NULL,
  `PARE_RESP_VOIT_NUM` varchar(255) DEFAULT NULL,
  `PARE_SVTH_COD_CIE` varchar(255) DEFAULT NULL,
  `PARE_SVTH_IND_FER` varchar(255) DEFAULT NULL,
  `PARE_SVTH_NUM` varchar(255) DEFAULT NULL,
  `PARE_SVTH_NUM_TRA1` varchar(255) DEFAULT NULL,
  `PARE_SVTH_SERV_COD` varchar(255) DEFAULT NULL,
  `PARE_SVTH_TRCH_NUM` varchar(255) DEFAULT NULL,
  `PARE_TYRE_COD_REP` varchar(255) DEFAULT NULL,
  `PARE_USER` varchar(255) DEFAULT NULL,
  `PARE_VRES_NUM_SERV` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDPARE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdrame` (
  `idTMDRAME` bigint(20) NOT NULL AUTO_INCREMENT,
  `RAME_DHDO` varchar(255) DEFAULT NULL,
  `RAME_NUM` varchar(255) DEFAULT NULL,
  `RAME_NUM_PREM_VOIT` varchar(255) DEFAULT NULL,
  `RAME_RAMC_COD` varchar(255) DEFAULT NULL,
  `RAME_REGI` varchar(255) DEFAULT NULL,
  `RAME_TRCH_COD_CIE` varchar(255) DEFAULT NULL,
  `RAME_TRCH_IND_FER` varchar(255) DEFAULT NULL,
  `RAME_TRCH_NUM` varchar(255) DEFAULT NULL,
  `RAME_TRCH_NUM_TRA1` varchar(255) DEFAULT NULL,
  `RAME_USER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDRAME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdrcth` (
  `idTMDRCTH` bigint(20) NOT NULL AUTO_INCREMENT,
  `RCTH_CLBA_COD` varchar(255) DEFAULT NULL,
  `RCTH_DHDO` varchar(255) DEFAULT NULL,
  `RCTH_INPT_RR_D` varchar(255) DEFAULT NULL,
  `RCTH_INPT_RR_F` varchar(255) DEFAULT NULL,
  `RCTH_REGI` varchar(255) DEFAULT NULL,
  `RCTH_RESA_TYP` varchar(255) DEFAULT NULL,
  `RCTH_TRCH_COD_CIE` varchar(255) DEFAULT NULL,
  `RCTH_TRCH_IND_FER` varchar(255) DEFAULT NULL,
  `RCTH_TRCH_NUM` varchar(255) DEFAULT NULL,
  `RCTH_TRCH_NUM_TRA1` varchar(255) DEFAULT NULL,
  `RCTH_USER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDRCTH`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdspco` (
  `idTMDSPCO` bigint(20) NOT NULL AUTO_INCREMENT,
  `SPCO_COMP_NUM` varchar(255) DEFAULT NULL,
  `SPCO_DHDO` varchar(255) DEFAULT NULL,
  `SPCO_REGI` varchar(255) DEFAULT NULL,
  `SPCO_SPEC_COD` varchar(255) DEFAULT NULL,
  `SPCO_USER` varchar(255) DEFAULT NULL,
  `SPCO_VOIT_COD_CIE` varchar(255) DEFAULT NULL,
  `SPCO_VOIT_IND_FER` varchar(255) DEFAULT NULL,
  `SPCO_VOIT_NUM` varchar(255) DEFAULT NULL,
  `SPCO_VOIT_NUM_TRA1` varchar(255) DEFAULT NULL,
  `SPCO_VOIT_TRCH_NUM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDSPCO`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdsppl` (
  `idTMDSPPL` bigint(20) NOT NULL AUTO_INCREMENT,
  `SPPL_DHDO` varchar(255) DEFAULT NULL,
  `SPPL_PCDD_NUM_COMP` varchar(255) DEFAULT NULL,
  `SPPL_PCDD_NUM_PLAC` varchar(255) DEFAULT NULL,
  `SPPL_REGI` varchar(255) DEFAULT NULL,
  `SPPL_SPEC_COD` varchar(255) DEFAULT NULL,
  `SPPL_USER` varchar(255) DEFAULT NULL,
  `SPPL_VOIT_COD_CIE` varchar(255) DEFAULT NULL,
  `SPPL_VOIT_IND_FER` varchar(255) DEFAULT NULL,
  `SPPL_VOIT_NUM` varchar(255) DEFAULT NULL,
  `SPPL_VOIT_NUM_TRA1` varchar(255) DEFAULT NULL,
  `SPPL_VOIT_TRCH_NUM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDSPPL`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdsvth` (
  `idTMDSVTH` bigint(20) NOT NULL AUTO_INCREMENT,
  `SVTH_COD_ORIGINE` varchar(255) DEFAULT NULL,
  `SVTH_DHDO` varchar(255) DEFAULT NULL,
  `SVTH_IND_SPTH` varchar(255) DEFAULT NULL,
  `SVTH_INPT_RR_D` varchar(255) DEFAULT NULL,
  `SVTH_INPT_RR_F` varchar(255) DEFAULT NULL,
  `SVTH_LIBS_SERV_COD` varchar(255) DEFAULT NULL,
  `SVTH_NUM` varchar(255) DEFAULT NULL,
  `SVTH_REGI` varchar(255) DEFAULT NULL,
  `SVTH_TRCH_COD_CIE` varchar(255) DEFAULT NULL,
  `SVTH_TRCH_IND_FER` varchar(255) DEFAULT NULL,
  `SVTH_TRCH_NUM` varchar(255) DEFAULT NULL,
  `SVTH_TRCH_NUM_TRA1` varchar(255) DEFAULT NULL,
  `SVTH_TYP_CLAS` varchar(255) DEFAULT NULL,
  `SVTH_USER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDSVTH`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdtath` (
  `idTMDTATH` bigint(20) NOT NULL AUTO_INCREMENT,
  `TATH_CD_VAL` varchar(255) DEFAULT NULL,
  `TATH_DHDO` varchar(255) DEFAULT NULL,
  `TATH_INPT_RR_D` varchar(255) DEFAULT NULL,
  `TATH_INPT_RR_F` varchar(255) DEFAULT NULL,
  `TATH_PRIX_HORS_SYS` varchar(255) DEFAULT NULL,
  `TATH_REGI` varchar(255) DEFAULT NULL,
  `TATH_TRCH_COD_CIE` varchar(255) DEFAULT NULL,
  `TATH_TRCH_IND_FER` varchar(255) DEFAULT NULL,
  `TATH_TRCH_NUM` varchar(255) DEFAULT NULL,
  `TATH_TRCH_NUM_TRA1` varchar(255) DEFAULT NULL,
  `TATH_TYP_TAX` varchar(255) DEFAULT NULL,
  `TATH_USER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDTATH`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdtra1` (
  `idTMDTRA1` bigint(20) NOT NULL AUTO_INCREMENT,
  `TRA1_AUT_MOD_THOR` varchar(255) DEFAULT NULL,
  `TRA1_CIES_COD_CIE` varchar(255) DEFAULT NULL,
  `TRA1_DAT_DER_MOD` varchar(255) DEFAULT NULL,
  `TRA1_DAT_MOD_THOR` varchar(255) DEFAULT NULL,
  `TRA1_DHDO` varchar(255) DEFAULT NULL,
  `TRA1_IND_CDEM` varchar(255) DEFAULT NULL,
  `TRA1_IND_COMPO` varchar(255) DEFAULT NULL,
  `TRA1_IND_ECOLE` varchar(255) DEFAULT NULL,
  `TRA1_IND_FER_ROUTE` varchar(255) DEFAULT NULL,
  `TRA1_LIBS_GERE_COD` varchar(255) DEFAULT NULL,
  `TRA1_NAT_MOD_THOR` varchar(255) DEFAULT NULL,
  `TRA1_NOM_TRAIN` varchar(255) DEFAULT NULL,
  `TRA1_NUM_TRAIN` varchar(255) DEFAULT NULL,
  `TRA1_REGI_NON_EXTR` varchar(255) DEFAULT NULL,
  `TRA1_REGI_VAL` varchar(255) DEFAULT NULL,
  `TRA1_REGI_VAL_TRTH` varchar(255) DEFAULT NULL,
  `TRA1_USER` varchar(255) DEFAULT NULL,
  `TRA1_COMMENTAIRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDTRA1`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdtrch` (
  `idTMDTRCH` bigint(20) NOT NULL AUTO_INCREMENT,
  `TRCH_COD_SENS_AUTO` varchar(255) DEFAULT NULL,
  `TRCH_DAT_DER_MOD` varchar(255) DEFAULT NULL,
  `TRCH_DHDO` varchar(255) DEFAULT NULL,
  `TRCH_IND_TGV` varchar(255) DEFAULT NULL,
  `TRCH_IND_VAL_DC` varchar(255) DEFAULT NULL,
  `TRCH_INPT_RR_DEST` varchar(255) DEFAULT NULL,
  `TRCH_INPT_RR_ORIG` varchar(255) DEFAULT NULL,
  `TRCH_LIBS_SEMA_COD` varchar(255) DEFAULT NULL,
  `TRCH_NUM` varchar(255) DEFAULT NULL,
  `TRCH_REGI_VAL` varchar(255) DEFAULT NULL,
  `TRCH_REGI_VAL_DC` varchar(255) DEFAULT NULL,
  `TRCH_TRA1_COD_CIE` varchar(255) DEFAULT NULL,
  `TRCH_TRA1_IND_FER` varchar(255) DEFAULT NULL,
  `TRCH_TRA1_NUM_TRA1` varchar(255) DEFAULT NULL,
  `TRCH_USER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDTRCH`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_import_tmdvoit` (
  `idTMDVOIT` bigint(20) NOT NULL AUTO_INCREMENT,
  `VOIT_CIES_COD_GERE` varchar(255) DEFAULT NULL,
  `VOIT_COD_ORIG` varchar(255) DEFAULT NULL,
  `VOIT_DHDO` varchar(255) DEFAULT NULL,
  `VOIT_INDCE_CLASST` varchar(255) DEFAULT NULL,
  `VOIT_IND_ORIG` varchar(255) DEFAULT NULL,
  `VOIT_NUM` varchar(255) DEFAULT NULL,
  `VOIT_NUM_ORIG` varchar(255) DEFAULT NULL,
  `VOIT_NUM_RESA` varchar(255) DEFAULT NULL,
  `VOIT_NUM_VOIT` varchar(255) DEFAULT NULL,
  `VOIT_REGI_UTIL` varchar(255) DEFAULT NULL,
  `VOIT_ROUL_NUM` varchar(255) DEFAULT NULL,
  `VOIT_SENS_ORIG` varchar(255) DEFAULT NULL,
  `VOIT_TRCH_COD_CIE` varchar(255) DEFAULT NULL,
  `VOIT_TRCH_IND_FER` varchar(255) DEFAULT NULL,
  `VOIT_TRCH_NUM` varchar(255) DEFAULT NULL,
  `VOIT_TRCH_NUM_TRA1` varchar(255) DEFAULT NULL,
  `VOIT_TYVO_NUM_TYP` varchar(255) DEFAULT NULL,
  `VOIT_USER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTMDVOIT`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_jeu_donnees` (
  `idJeuDonnees` int(11) NOT NULL AUTO_INCREMENT,
  `actifJeuDonnees` bit(1) NOT NULL,
  `statusJeuDonnees` tinyint(1) NOT NULL,
  `commentaireJeuDonnees` longtext NOT NULL,
  `dateCreateJeuDonnees` datetime NOT NULL,
  `dateLastUpdateJeuDonnees` datetime NOT NULL,
  `idUtilisateurCreateJeuDonnees` int(11) NOT NULL,
  `idUtilisateurLastUpdateJeuDonnees` int(11) NOT NULL,
  `libelleJeuDonnees` varchar(50) NOT NULL,
  `nomTechniqueJeuDonnees` varchar(50) NOT NULL,
  `ordreJeuDonnees` int(11) NOT NULL,
  PRIMARY KEY (`idJeuDonnees`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tremas_tables_motrice` (
  `idTablesMotrice` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelleTablesMotrice` varchar(255) DEFAULT NULL,
  `actifTablesMotrice` tinyint(1) NOT NULL,
  PRIMARY KEY (`idTablesMotrice`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tremas_tables_motrice
-- ----------------------------
INSERT INTO `tremas_tables_motrice` VALUES ('1', 'TMDAVTR', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('2', 'TMDAVTH', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('3', 'TMDCATH', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('4', 'TMDCATR', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('5', 'TMDCDCL', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('6', 'TMDCDDS', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('7', 'TMDCDEM', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('8', 'TMDDSTR', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('9', 'TMDETVO', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('10', 'TMDGADS', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('11', 'TMDKAPP', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('12', 'TMDPARE', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('13', 'TMDRAME', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('14', 'TMDRCTH', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('15', 'TMDSPCO', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('16', 'TMDSPPL', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('17', 'TMDSVTH', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('18', 'TMDTATH', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('19', 'TMDTRA1', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('20', 'TMDTRCH', '1');
INSERT INTO `tremas_tables_motrice` VALUES ('21', 'TMDVOIT', '1');