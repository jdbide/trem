ALTER TABLE `socle_log_job`
MODIFY COLUMN `dateFinLogJob`  datetime NULL COMMENT 'Date à laquelle l\'exécution du job s\'est arrêtée' AFTER `dateDebutLogJob`;