-- ----------------------------
-- Table structure for `socle_user2role`
-- ----------------------------
DROP TABLE IF EXISTS `socle_user2role`;
CREATE TABLE `socle_user2role` (
  `idUser2Role` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `idUser` int(11) unsigned NOT NULL,
  `idRole` int(11) unsigned NOT NULL,
  PRIMARY KEY (`idUser2Role`),
  UNIQUE KEY `indUnique_UserRole` (`idUser`,`idRole`),
  KEY `FK_Role_User2Role` (`idRole`),
  CONSTRAINT `FK_Role_User2Role` FOREIGN KEY (`idRole`) REFERENCES `socle_role` (`idRole`),
  CONSTRAINT `FK_User_User2Role` FOREIGN KEY (`idUser`) REFERENCES `socle_user` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table utilisée pour gérer les droits des users sur l''ihm par rapports à leur rôle';

