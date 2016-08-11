ALTER TABLE `socle_job`
MODIFY COLUMN `idJob`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id du job' FIRST ,
MODIFY COLUMN `libelleJob`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'Libellé du job' AFTER `idJob`,
MODIFY COLUMN `nomTechniquejob`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'Nom technique du job' AFTER `libelleJob`,
MODIFY COLUMN `classeJob`  varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'Classe du job à lancer' AFTER `nomTechniquejob`,
ADD COLUMN `actifJob`  bit(1) NOT NULL DEFAULT b'0' COMMENT 'Détermine si le job est actif' AFTER `classeJob`;


ALTER TABLE `socle_job_planif`
ADD COLUMN `actifJobPlanif`  bit(1) NOT NULL DEFAULT b'0' COMMENT 'Détermine si la planification est active ou pas' AFTER `anneeJobPlanif`;