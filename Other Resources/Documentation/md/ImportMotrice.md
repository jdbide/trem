# Documentation import des tables motrice

Cette documentation a pour but d'expliquer le fonctionnement de l'import des tables motrice de Tremas.

Pour des explications plus techniques, veuillez-vous reporter à la javadoc.

### Structure de la documentation

* Import brut
* Intégration dans le modèle Tremas du plan de transport


### Import brut

L'import brut depuis la base motrice DB2.

Le schema rentré pour la connexion à DB2 représente un transporteur :
* F$MDRP2 -> EuroStar Production
* F$MDRO2 -> EuroStar Recette
* F$MDRP3 -> Thalys Production
* F$MDRO3 -> Thalys Recette

Les tables de DB2 sont importées dans des tables identiques dans la base Tremas.
> Les régimes sont traduits dans la base Tremas. Nous avons aussi ajouté des identifiants aux enregistrements des tables (qui n'étaient pas présents dans DB2)

Ajout d'une table à importer:
> Le nom de la table DB2 doit être ajouté à la table tremas_ref_tables_motrice.

> Son entité doit être créée dans src\main\java\com\avancial\app\data\databean\importMotriceBrut\

> Si la table contient un champ devant être traité (tel que les champs regimes) avant d'être inseré dans la base de Tremas, le nom de ce champ ainsi que l'instance de son traitement doivent être ajouté à la map src\main\java\com\avancial\app\utilitaire\MapTraitementImportBrut.java

### Intégration dans le model Tremas du plan de transport
Pour des raisons de performance, nous utilisons des requêtes SQL natives pour insérer dans les tables motrice_regime. À chaque import, nous insérons les données table par table, en une (ou plusieurs) requête(s) de la forme:

    INSERT INTO table VALUES (...), (...), ...;

Nous utilisons une interface *IMultipleInsertRequestGenerator* pour générer ces requêtes, et nous avons besoin d'une instance par table. Le nombre de valeurs ajoutées en une seule requête est paramétrable.

Puisque les tables sont vidées au début de chaque import, nous pouvons initialiser nous-mêmes les id des données, et ainsi remplir toutes les contraintes de foreign keys.

À cause de ces contraintes, nous sommes obligés de setter les valeurs des entités une à une.
