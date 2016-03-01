/*
Navicat MySQL Data Transfer

Source Server         : socle - DEV
Source Server Version : 50516
Source Host           : caliban:3306
Source Database       : socle

Target Server Type    : MYSQL
Target Server Version : 50516
File Encoding         : 65001

Date: 2015-09-04 18:03:15
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table utilisée pour gérer les droits des users sur l''ihm par rapports à leur rôle';

-- ----------------------------
-- Records of socle_item2role
-- ----------------------------
INSERT INTO `socle_item2role` VALUES ('1', '1', 'pageRole', '', '', '', '');
INSERT INTO `socle_item2role` VALUES ('2', '1', 'refreshItem2Role', '', '', '', '');
INSERT INTO `socle_item2role` VALUES ('3', '1', 'pageJob', '', '', '', '');
INSERT INTO `socle_item2role` VALUES ('4', '1', 'pageJobPlanif', '', '', '', '');

-- ----------------------------
-- Table structure for `socle_job`
-- ----------------------------
DROP TABLE IF EXISTS `socle_job`;
CREATE TABLE `socle_job` (
  `idJob` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `libelleJob` varchar(50) COLLATE utf8_bin NOT NULL,
  `nomTechniquejob` varchar(50) COLLATE utf8_bin NOT NULL,
  `classeJob` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idJob`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of socle_job
-- ----------------------------
INSERT INTO `socle_job` VALUES ('3', 'Test', 'SOCLE_TEST', 'com.avancial.socle.jobs.JobTest');
INSERT INTO `socle_job` VALUES ('5', 'test', 'SOCLE_TEST1', 'test');

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
  PRIMARY KEY (`idJobPlanif`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of socle_job_planif
-- ----------------------------
INSERT INTO `socle_job_planif` VALUES ('1', '1', '3', '*', '*', '*', '*', '*', '*', '*', '*', '*');
INSERT INTO `socle_job_planif` VALUES ('2', '1', '3', 'test', 'test', '*', '*', '*', '*', '*', '*', '*');
INSERT INTO `socle_job_planif` VALUES ('3', '1', '3', '*', '*', '*', '*', '*', '*', '*', '*', '*');
INSERT INTO `socle_job_planif` VALUES ('4', '1', '3', '*', '*', '*', '*', '*', '*', '*', '*', '*');
INSERT INTO `socle_job_planif` VALUES ('5', '1', '3', '*', '*', '*', '*', '*', '*', '*', '*', '*');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of socle_ref_directory
-- ----------------------------
INSERT INTO `socle_ref_directory` VALUES ('1', 'SOCLE_tmp', 'd:\\was\\tmp', null);
INSERT INTO `socle_ref_directory` VALUES ('2', 'SOCLE_Test', 'd:\\RépertoireTest', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of socle_role
-- ----------------------------
INSERT INTO `socle_role` VALUES ('1', 'SOCLE_adminMOE', 'Administrateur MOE');
INSERT INTO `socle_role` VALUES ('40', 'SOCLE_test', 'test');
INSERT INTO `socle_role` VALUES ('42', 'SOCLE_test1', 'test15');
INSERT INTO `socle_role` VALUES ('43', 'SOCLE_test2', 'test 5');
INSERT INTO `socle_role` VALUES ('44', 'SOCLE_test4', 'test4');

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
  `idUserCreateUser` int(11) unsigned NOT NULL,
  `dateCreateUser` datetime NOT NULL,
  `idUserUpdateUser` int(11) unsigned NOT NULL,
  `dateUpdateUser` datetime NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of socle_user
-- ----------------------------
INSERT INTO `socle_user` VALUES ('1', 'admin', 'admin', 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'admin@avancial.com', '0123456A', '', 'user', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '2015-01-13 09:54:50', '1', '2015-01-13 17:41:28', null, null);
INSERT INTO `socle_user` VALUES ('2', 'quentin', 'quentin', 'quentin', 'd6b8e48afb2534b213e391cab43016505747a234', null, null, '', 'user', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '2015-01-13 09:54:50', '1', '2015-01-13 09:54:59', null, null);
INSERT INTO `socle_user` VALUES ('3', 'andrieu', 'liana', '8008286j', '0123456B', 'liana.andrieu@avancial.com', '0123456B', '', 'user', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '2015-01-01 00:00:00', '1', '2015-01-13 00:00:00', null, null);
INSERT INTO `socle_user` VALUES ('4', 'Erdogan', 'Caglar', 'caglar', '1234', 'caglar.erdogan@avancial.com', '1234567X', '', 'user', null, null, '0612345678', '0123456789', '40', 'avenue des Terroirs de France', null, null, '75012', 'Paris', null, null, null, null, null, '4', '2015-01-01 00:00:00', '4', '2015-01-01 00:00:00', 'Test', null);

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
  CONSTRAINT `socle_user_fk_idUser` FOREIGN KEY (`idUser`) REFERENCES `socle_user` (`idUser`),
  CONSTRAINT `socle_user_fk_idRole` FOREIGN KEY (`idRole`) REFERENCES `socle_role` (`idRole`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of socle_user2role
-- ----------------------------
INSERT INTO `socle_user2role` VALUES ('1', '1', '1');
INSERT INTO `socle_user2role` VALUES ('2', '1', '40');
