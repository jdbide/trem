CREATE TABLE `socle_log_job` (
`idLogJob`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Id du log du Job' ,
`idJobPlanif`  int(11) UNSIGNED NOT NULL COMMENT 'Id de la planification de job auquel ce log fait référence' ,
`idUser`  int(11) UNSIGNED NOT NULL COMMENT 'Id de l\'utilisateur qui a lancé le job' ,
`libelleJob`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'Libellé du job qui a été lancé' ,
`dateDebutLogJob`  datetime NOT NULL COMMENT 'Date à laquelle l\'exécution du job a débuté' ,
`dateFinLogJob`  datetime NOT NULL COMMENT 'Date à laquelle l\'exécution du job s\'est arrêtée' ,
`etatOkLogJob`  bit NOT NULL COMMENT 'Etat OK/KO du job à la fin de son exécution' ,
PRIMARY KEY (`idLogJob`),
CONSTRAINT `FK_log_job_idJobPlanif` FOREIGN KEY (`idJobPlanif`) REFERENCES `socle_job_planif` (`idJobPlanif`),
CONSTRAINT `FK_log_job_idUser` FOREIGN KEY (`idUser`) REFERENCES `socle_user` (`idUser`)
)
COMMENT='Logs se référant à l\'exécution d\'un Job'
;

CREATE TABLE `socle_log_job_detail` (
`idLogJobDetail`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Id du log détail pour le Job' ,
`idLogJob`  int(11) UNSIGNED NOT NULL COMMENT 'Id du log se référant à l\'exécution du Job' ,
`severiteLogJobDetail`  int(4) NOT NULL COMMENT 'Sévérité, ou encore gravité, du log' ,
`messageLogJobDetail`  varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'Message décrivant l\'événement du Job que l\'on veut logger' ,
`messageExceptionLogJobDetail`  text NULL COMMENT 'Eventuel message d\'exception intervenue pendant l\'exécution du Job' ,
`dateLogJobDetail`  datetime NOT NULL COMMENT 'Date à laquelle le log est effectué' ,
PRIMARY KEY (`idLogJobDetail`),
CONSTRAINT `FK_log_job_detail_idLogJob` FOREIGN KEY (`idLogJob`) REFERENCES `socle_log_job` (`idLogJob`)
)
COMMENT='Logs donnant des détails d\'exécution de Jobs'
;

