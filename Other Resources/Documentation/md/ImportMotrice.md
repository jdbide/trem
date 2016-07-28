# Documentation import des tables motrice

Cette documentation a pour but d'expliquer le fonnctionnement de l'import des tables motrice de Tremas.

Pour des explications plus techniques, veuillez-vous reporter à la javadoc.

### Structure de la documentation

* Import brute
* Intégration dans le model Tremas du plan de transport


### Import brute

L'import brute depuis la base motrice DB2.

Le schemas rentrer pour la connexion a DB2 represente un transporteur :
* F$MDRP2 -> EuroStar Production
* F$MDRO2 -> EuroStar Recette
* F$MDRP3 -> Thalys Production
* F$MDRO3 -> Thalys Recette

Les tables de DB2 sont importé dans des tables identique dans la base Tremas.
> Les regimes sont traduits dans la base Tremas. Nous avons aussi ajouter des identifiant au enregistrement des tables (qui n'etait pas présent dans DB2)

Ajout d'une table à importer:
> Le nom de la table DB2 doit être ajouter a la table tremas_ref_tables_motrice.

> Son entité doit etre créé dans src\main\java\com\avancial\app\data\databean\importMotriceBrut\

> Si la table contient un champ devant etre traiter (tel que les champs regimes) avant d'être inserer dans la base de Tremas, le nom de se champ ainsi que l'instance de son traitement doivent etre ajouter a la map src\main\java\com\avancial\app\utilitaire\MapTraitementImportBrut.java

### Intégration dans le model Tremas du plan de transport
