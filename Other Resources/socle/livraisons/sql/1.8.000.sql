
-- version 1.8.00
-- Livré en recette le

ALTER TABLE `socle_ihm_page`
DROP COLUMN `idPage`,
DROP COLUMN `idChapitre`;


ALTER TABLE `socle_ihm_page`
ADD COLUMN `toutRoleIhmPage`  tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'Page affichée quelque soit le rôle de l''''utilisateur. Nécessite d''être connecté quand même.' AFTER `libelleIhmPage`,
ADD COLUMN `publiqueIhmPage`  tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'Page publique affichable sans être connecté.' AFTER `toutRoleIhmPage`;

ALTER TABLE `socle_ihm_rubrique`
DROP COLUMN `idRubrique`;

ALTER TABLE `socle_ihm_chapitre`
DROP COLUMN `idChapitre`,
DROP COLUMN `idRubrique`;