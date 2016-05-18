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

