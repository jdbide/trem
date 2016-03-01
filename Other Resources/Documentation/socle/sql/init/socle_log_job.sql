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
