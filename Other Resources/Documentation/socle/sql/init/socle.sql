/*
Navicat MySQL Data Transfer

Source Server         : socle - DEV
Source Server Version : 50516
Source Host           : caliban:3306
Source Database       : socle

Target Server Type    : MYSQL
Target Server Version : 50516
File Encoding         : 65001

Date: 2016-03-31 16:21:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `socle_item2role`
-- ----------------------------
DROP TABLE IF EXISTS `socle_item2role`;
CREATE TABLE `socle_item2role` (
  `idItem2Role` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `idRole` int(11) unsigned NOT NULL,
  `nameItem2Role` varchar(30) COLLATE utf8_bin NOT NULL,
  `isRendered` bit(1) NOT NULL DEFAULT b'0' COMMENT 'Indique si l''élément peut être affiché pour le détenteur du rôle',
  `isEditable` bit(1) NOT NULL DEFAULT b'0' COMMENT 'Indique Indique si l''élément peut être edité pour le détenteur du rôle',
  `isAddable` bit(1) NOT NULL DEFAULT b'0' COMMENT 'Indique si l''élément peut être ajouté pour le détenteur du rôle',
  `isDeletable` bit(1) NOT NULL DEFAULT b'0' COMMENT 'Indique si l''élément peut être effacé pour le détenteur du rôle',
  PRIMARY KEY (`idItem2Role`),
  KEY `FK_item2Role_idRole` (`idRole`),
  CONSTRAINT `FK_item2Role_idRole` FOREIGN KEY (`idRole`) REFERENCES `socle_role` (`idRole`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table utilisée pour gérer les droits des users sur l''ihm par rapports à leur rôle';

-- ----------------------------
-- Records of socle_item2role
-- ----------------------------
INSERT INTO `socle_item2role` VALUES ('1', '1', 'role.xhtml', '', '', '', '');
INSERT INTO `socle_item2role` VALUES ('2', '1', 'refreshItem2Role', '', '', '', '');
INSERT INTO `socle_item2role` VALUES ('3', '1', 'job.xhtml', '', '', '', '');
INSERT INTO `socle_item2role` VALUES ('4', '1', 'jobPlanif.xhtml', '', '', '', '');
INSERT INTO `socle_item2role` VALUES ('5', '2', 'robot.xhtml', '', '', '', '');
INSERT INTO `socle_item2role` VALUES ('6', '1', 'jobSupervision.xhtml', '', '', '', '');
INSERT INTO `socle_item2role` VALUES ('7', '1', 'user.xhtml', '', '', '', '');
INSERT INTO `socle_item2role` VALUES ('8', '1', 'item2Role.xhtml', '', '', '', '');

-- ----------------------------
-- Table structure for `socle_job`
-- ----------------------------
DROP TABLE IF EXISTS `socle_job`;
CREATE TABLE `socle_job` (
  `idJob` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `libelleJob` varchar(50) COLLATE utf8_bin NOT NULL,
  `nomTechniquejob` varchar(50) COLLATE utf8_bin NOT NULL,
  `classeJob` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idJob`),
  UNIQUE KEY `indUnique_nomTechniquejob` (`nomTechniquejob`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of socle_job
-- ----------------------------
INSERT INTO `socle_job` VALUES ('12', 'jobtest', 'TestJob', 'com.avancial.socle.jobs.JobTest');

-- ----------------------------
-- Table structure for `socle_job_planif`
-- ----------------------------
DROP TABLE IF EXISTS `socle_job_planif`;
CREATE TABLE `socle_job_planif` (
  `idJobPlanif` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `idJobPlanifType` int(11) unsigned NOT NULL,
  `idJob` int(11) unsigned NOT NULL,
  `libelleJobPlanif` varchar(50) COLLATE utf8_bin NOT NULL,
  `nomTechniqueJobPlanif` varchar(35) COLLATE utf8_bin NOT NULL,
  `secondesJobPlanif` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `minutesJobPlanif` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `heuresJobPlanif` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `jourMoisJobPlanif` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `moisJobPlanif` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `jourSemaineJobPlanif` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `anneeJobPlanif` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`idJobPlanif`),
  UNIQUE KEY `indUnique_technicalNameJobPlanif` (`nomTechniqueJobPlanif`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of socle_job_planif
-- ----------------------------
INSERT INTO `socle_job_planif` VALUES ('49', '3', '12', 'Test log', 'JOB_TEST_LOG', '0', '0/2', '*', '*', '*', '?', '*');

-- ----------------------------
-- Table structure for `socle_job_planif_type`
-- ----------------------------
DROP TABLE IF EXISTS `socle_job_planif_type`;
CREATE TABLE `socle_job_planif_type` (
  `idJobPlanifType` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `libelleJobPlanifType` varchar(35) COLLATE utf8_bin NOT NULL,
  `nomTechniqueJobPlanifType` varchar(35) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idJobPlanifType`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of socle_job_planif_type
-- ----------------------------
INSERT INTO `socle_job_planif_type` VALUES ('1', 'cron', 'SOCLE_JOBTYPECRON');
INSERT INTO `socle_job_planif_type` VALUES ('2', 'daily', 'SOCLE_JOBTYPEDAILY');
INSERT INTO `socle_job_planif_type` VALUES ('3', 'now', 'SOCLE_JOBTYPENOW');

-- ----------------------------
-- Table structure for `socle_log_job`
-- ----------------------------
DROP TABLE IF EXISTS `socle_log_job`;
CREATE TABLE `socle_log_job` (
  `idLogJob` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id du log du Job',
  `idJobPlanif` int(11) unsigned NOT NULL COMMENT 'Id de la planification de job auquel ce log fait référence',
  `idUser` int(11) unsigned NOT NULL COMMENT 'Id de l''utilisateur qui a lancé le job',
  `libelleJob` varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'Libellé du job qui a été lancé',
  `dateDebutLogJob` datetime NOT NULL COMMENT 'Date à laquelle l''exécution du job a débuté',
  `dateFinLogJob` datetime NOT NULL COMMENT 'Date à laquelle l''exécution du job s''est arrêtée',
  `etatOkLogJob` bit(1) NOT NULL COMMENT 'Etat OK/KO du job à la fin de son exécution',
  PRIMARY KEY (`idLogJob`),
  KEY `FK_log_job_idJobPlanif` (`idJobPlanif`),
  KEY `FK_log_job_idUser` (`idUser`),
  CONSTRAINT `FK_log_job_idJobPlanif` FOREIGN KEY (`idJobPlanif`) REFERENCES `socle_job_planif` (`idJobPlanif`),
  CONSTRAINT `FK_log_job_idUser` FOREIGN KEY (`idUser`) REFERENCES `socle_user` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Logs se référant à l''exécution d''un Job';

-- ----------------------------
-- Records of socle_log_job
-- ----------------------------
INSERT INTO `socle_log_job` VALUES ('7', '49', '1', 'Login', '2016-02-18 14:10:39', '2016-03-31 14:08:00', '');

-- ----------------------------
-- Table structure for `socle_log_job_detail`
-- ----------------------------
DROP TABLE IF EXISTS `socle_log_job_detail`;
CREATE TABLE `socle_log_job_detail` (
  `idLogJobDetail` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id du log détail pour le Job',
  `idLogJob` int(11) unsigned NOT NULL COMMENT 'Id du log se référant à l''exécution du Job',
  `severiteLogJobDetail` int(4) NOT NULL COMMENT 'Sévérité, ou encore gravité, du log',
  `messageLogJobDetail` varchar(100) COLLATE utf8_bin NOT NULL COMMENT 'Message décrivant l''événement du Job que l''on veut logger',
  `messageExceptionLogJobDetail` text COLLATE utf8_bin COMMENT 'Eventuel message d''exception intervenue pendant l''exécution du Job',
  `dateLogJobDetail` datetime NOT NULL COMMENT 'Date à laquelle le log est effectué',
  PRIMARY KEY (`idLogJobDetail`),
  KEY `FK_log_job_detail_idLogJob` (`idLogJob`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Logs donnant des détails d''exécution de Jobs';

-- ----------------------------
-- Records of socle_log_job_detail
-- ----------------------------
INSERT INTO `socle_log_job_detail` VALUES ('1', '7', '1', 'Tentative déconnexion...', null, '2016-02-18 14:10:51');

-- ----------------------------
-- Table structure for `socle_ref_directory`
-- ----------------------------
DROP TABLE IF EXISTS `socle_ref_directory`;
CREATE TABLE `socle_ref_directory` (
  `idRefDirectory` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `technicalNameRefDirectory` varchar(30) NOT NULL,
  `pathRefDirectory` varchar(100) NOT NULL,
  `commentsRefDirectory` text CHARACTER SET utf8mb4,
  PRIMARY KEY (`idRefDirectory`),
  UNIQUE KEY `indUnique_RefDirectoryTechnicalName` (`technicalNameRefDirectory`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of socle_ref_directory
-- ----------------------------
INSERT INTO `socle_ref_directory` VALUES ('1', 'SOCLE_tmp', 'd:\\was\\tmp', null);
INSERT INTO `socle_ref_directory` VALUES ('3', 'APP_SSIM', 'd:\\was\\ssim', 'Chemin d\'accès à la SSIM');

-- ----------------------------
-- Table structure for `socle_role`
-- ----------------------------
DROP TABLE IF EXISTS `socle_role`;
CREATE TABLE `socle_role` (
  `idRole` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Roles des utilisateurs',
  `technicalNameRole` varchar(20) NOT NULL COMMENT 'Nom technique',
  `labelRole` varchar(100) NOT NULL,
  PRIMARY KEY (`idRole`),
  UNIQUE KEY `indUnique_technicalNameUserRole` (`technicalNameRole`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of socle_role
-- ----------------------------
INSERT INTO `socle_role` VALUES ('1', 'SOCLE_adminMOE', 'Administrateur MOE');
INSERT INTO `socle_role` VALUES ('2', 'SOCLE_supervision', 'Supervision');
INSERT INTO `socle_role` VALUES ('40', 'SOCLE_test', 'test');
INSERT INTO `socle_role` VALUES ('43', 'SOCLE_test2', 'test 5');
INSERT INTO `socle_role` VALUES ('44', 'SOCLE_test3', 'test 12');
INSERT INTO `socle_role` VALUES ('109', 'SOCLE_test4', 'test4');
INSERT INTO `socle_role` VALUES ('115', 'zae', 'zae');
INSERT INTO `socle_role` VALUES ('116', 'dada', 'efferf');

-- ----------------------------
-- Table structure for `socle_user`
-- ----------------------------
DROP TABLE IF EXISTS `socle_user`;
CREATE TABLE `socle_user` (
  `idUser` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nomUser` varchar(35) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `prenomUser` varchar(35) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `loginUser` varchar(50) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `passwordUser` varchar(56) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `mailUser` varchar(100) DEFAULT NULL,
  `cpUser` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `robotUser` bit(1) NOT NULL DEFAULT b'0',
  `tomcatRoleUser` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `telephonePro1User` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `telephonePro2User` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `telephonePortable1User` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `fax1User` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `adresseNumeroRueUser` varchar(10) CHARACTER SET latin1 DEFAULT NULL,
  `adresseNomRueUser` varchar(75) CHARACTER SET latin1 DEFAULT NULL,
  `adresseComplement1User` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `adresseComplement2User` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `adresseCodepostalUser` varchar(10) CHARACTER SET latin1 DEFAULT NULL,
  `adresseVilleUser` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `attribut1User` varchar(35) CHARACTER SET latin1 DEFAULT NULL,
  `attribut2User` varchar(35) CHARACTER SET latin1 DEFAULT NULL,
  `attribut3User` varchar(35) CHARACTER SET latin1 DEFAULT NULL,
  `attribut4User` varchar(35) CHARACTER SET latin1 DEFAULT NULL,
  `attribut5User` varchar(35) CHARACTER SET latin1 DEFAULT NULL,
  `idUserCreateUser` int(11) unsigned DEFAULT NULL,
  `dateCreateUser` datetime DEFAULT NULL,
  `idUserUpdateUser` int(11) unsigned DEFAULT NULL,
  `dateUpdateUser` datetime DEFAULT NULL,
  `commentaireUtilisateurUser` varchar(75) CHARACTER SET latin1 DEFAULT NULL,
  `dateLastUpdateUtilisateurUser` datetime DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `indUnique_loginUser` (`loginUser`) USING BTREE,
  UNIQUE KEY `indUnique_cpUser` (`cpUser`) USING BTREE,
  UNIQUE KEY `indUniqueMailUser` (`mailUser`) USING BTREE,
  KEY `idUserCreateUser_FK` (`idUserCreateUser`),
  KEY `idUserUpdateUser_FK` (`idUserUpdateUser`),
  CONSTRAINT `socle_user_ibfk_1` FOREIGN KEY (`idUserCreateUser`) REFERENCES `socle_user` (`idUser`),
  CONSTRAINT `socle_user_ibfk_2` FOREIGN KEY (`idUserUpdateUser`) REFERENCES `socle_user` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of socle_user
-- ----------------------------
INSERT INTO `socle_user` VALUES ('1', 'admin', 'admin', 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'admin@avancial.com', '0123456A', '', 'user', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '2015-01-13 09:54:50', '1', '2015-01-13 17:41:28', null, null);
INSERT INTO `socle_user` VALUES ('2', 'quentin', 'quentin', 'quentin', 'd6b8e48afb2534b213e391cab43016505747a234', null, null, '', 'user', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '2015-01-13 09:54:50', '1', '2015-01-13 09:54:59', null, null);
INSERT INTO `socle_user` VALUES ('3', 'andrieu', 'liana', '8008286j', '0123456B', 'liana.andrieu@avancial.com', '0123456B', '', 'user', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '2015-01-01 00:00:00', '1', '2015-01-13 00:00:00', null, null);
INSERT INTO `socle_user` VALUES ('4', 'Erdogan', 'Caglar', 'caglar', '1234', 'caglar.erdogan@avancial.com', '1234567X', '', 'user', null, null, '0612345678', '0123456789', '40', 'avenue des Terroirs de France', null, null, '75012', 'Paris', null, null, null, null, null, '4', '2015-01-01 00:00:00', '4', '2015-01-01 00:00:00', 'Test', null);
INSERT INTO `socle_user` VALUES ('5', 'robot', 'nagios', 'robot.nagios', 'cc5f9e08af8fd1a9859605fd4cdc0347a133159b', null, null, '', 'user', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '2015-12-18 11:25:06', '1', '2015-12-18 11:25:11', null, null);
INSERT INTO `socle_user` VALUES ('8', 'test', 'testo', 'jojo54', 'bernard', 'jojo@moi.fr', null, '', null, null, null, '041810', '18018', '512', 'rue', null, null, '51000', 'lille', null, null, null, null, null, null, null, null, null, 'aucun', null);

-- ----------------------------
-- Table structure for `socle_user2`
-- ----------------------------
DROP TABLE IF EXISTS `socle_user2`;
CREATE TABLE `socle_user2` (
  `idUser` int(8) NOT NULL,
  `codePostalUser` varchar(5) COLLATE utf8_bin DEFAULT NULL,
  `commentaireUser` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `cpUser` varchar(8) COLLATE utf8_bin NOT NULL,
  `dateAjoutUser` datetime NOT NULL,
  `dateEditionUser` datetime NOT NULL,
  `emailUser` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `faxUser` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `idAjoutUser` int(8) NOT NULL,
  `idEditionUser` int(8) NOT NULL,
  `loginUser` varchar(8) COLLATE utf8_bin NOT NULL,
  `nomUser` varchar(32) COLLATE utf8_bin NOT NULL,
  `numeroUser` smallint(4) DEFAULT NULL,
  `passwordUser` varchar(32) COLLATE utf8_bin NOT NULL,
  `prenomUser` varchar(32) COLLATE utf8_bin NOT NULL,
  `robotUser` bit(1) NOT NULL,
  `rueUser` varchar(128) COLLATE utf8_bin NOT NULL,
  `telephonePersonnelUser` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `telephoneProfessionnelUser` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `tomcatRoleUser` varchar(8) COLLATE utf8_bin NOT NULL,
  `villeUser` varchar(64) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `socle_user_ix_loginUser` (`loginUser`) USING BTREE,
  UNIQUE KEY `socle_user_ix_cpUser` (`cpUser`) USING BTREE,
  UNIQUE KEY `socle_user_ix_emailUser` (`emailUser`) USING BTREE,
  KEY `socle_user_ix_idAjoutUser` (`idAjoutUser`) USING BTREE,
  KEY `socle_user_ix_idEditionUser` (`idEditionUser`) USING BTREE,
  CONSTRAINT `socle_user_fk_idAjoutUser` FOREIGN KEY (`idAjoutUser`) REFERENCES `socle_user2` (`idUser`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `socle_user_fk_idEditionUser` FOREIGN KEY (`idEditionUser`) REFERENCES `socle_user2` (`idUser`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of socle_user2
-- ----------------------------

-- ----------------------------
-- Table structure for `socle_user2role`
-- ----------------------------
DROP TABLE IF EXISTS `socle_user2role`;
CREATE TABLE `socle_user2role` (
  `idUser2Role` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `idUser` int(11) unsigned NOT NULL,
  `idRole` int(11) unsigned NOT NULL,
  PRIMARY KEY (`idUser2Role`),
  UNIQUE KEY `socle_user_ix_userRole` (`idUser`,`idRole`) USING BTREE,
  KEY `socle_user_ix_idRole` (`idRole`) USING BTREE,
  KEY `socle_user_ix_idUser` (`idUser`) USING BTREE,
  CONSTRAINT `socle_user_fk_idRole` FOREIGN KEY (`idRole`) REFERENCES `socle_role` (`idRole`),
  CONSTRAINT `socle_user_fk_idUser` FOREIGN KEY (`idUser`) REFERENCES `socle_user` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of socle_user2role
-- ----------------------------
INSERT INTO `socle_user2role` VALUES ('1', '1', '1');
INSERT INTO `socle_user2role` VALUES ('2', '1', '40');
INSERT INTO `socle_user2role` VALUES ('3', '5', '2');
