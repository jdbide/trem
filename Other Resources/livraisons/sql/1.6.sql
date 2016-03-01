INSERT INTO socle_user(`nomUser`,
                       `prenomUser`,
                       `loginUser`,
                       `passwordUser`,
                       `mailUser`,
                       `cpUser`,
                       `robotUser`,
                       `tomcatRoleUser`,
                       `telephonePro1User`,
                       `telephonePro2User`,
                       `telephonePortable1User`,
                       `fax1User`,
                       `adresseNumeroRueUser`,
                       `adresseNomRueUser`,
                       `adresseComplement1User`,
                       `adresseComplement2User`,
                       `adresseCodepostalUser`,
                       `adresseVilleUser`,
                       `attribut1User`,
                       `attribut2User`,
                       `attribut3User`,
                       `attribut4User`,
                       `attribut5User`,
					   `idUserCreateUser`,
                       `dateCreateUser`,
					   `idUserUpdateUser`,
                       `dateUpdateUser`,
                       `commentaireUtilisateurUser`,
                       `dateLastUpdateUtilisateurUser`)
     VALUES ('robot',
             'nagios',
             'robot.nagios',
             'cc5f9e08af8fd1a9859605fd4cdc0347a133159b',
             NULL,
             NULL,
             FALSE,
             'user',
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL,
			 1,
             '2015-12-18 11:25:06.0',
			 1,
             '2015-12-18 11:25:11.0',
             NULL,
             NULL);

COMMIT;

INSERT INTO socle_role(`technicalNameRole`, `labelRole`)
     VALUES ('SOCLE_supervision', 'Supervision');

COMMIT;

INSERT INTO socle_item2role(`nameItem2Role`,`idRole`,
                            `isRendered`,
                            `isEditable`,
                            `isAddable`,
                            `isDeletable`)
     VALUES ('pageRobot',
	 		(select idRole from socle_role where socle_role.`technicalNameRole`='SOCLE_supervision'), 
             TRUE,
             FALSE,
             FALSE,
             FALSE);

COMMIT;


