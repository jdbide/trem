CREATE TABLE `socle_log_job_detail` (
`idLogJobDetail`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Id du log d�tail pour le Job' ,
`idLogJob`  int(11) UNSIGNED NOT NULL COMMENT 'Id du log se r�f�rant � l\'ex�cution du Job' ,
`severiteLogJobDetail`  int(4) NOT NULL COMMENT 'S�v�rit�, ou encore gravit�, du log' ,
`messageLogJobDetail`  varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'Message d�crivant l\'�v�nement du Job que l\'on veut logger' ,
`messageExceptionLogJobDetail`  text NULL COMMENT 'Eventuel message d\'exception intervenue pendant l\'ex�cution du Job' ,
`dateLogJobDetail`  datetime NOT NULL COMMENT 'Date � laquelle le log est effectu�' ,
PRIMARY KEY (`idLogJobDetail`),
CONSTRAINT `FK_log_job_detail_idLogJob` FOREIGN KEY (`idLogJob`) REFERENCES `socle_log_job` (`idLogJob`)
)
COMMENT='Logs donnant des d�tails d\'ex�cution de Jobs'
;

