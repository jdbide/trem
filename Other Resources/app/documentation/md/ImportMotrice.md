# Documentation import des tables motrice

Cette documentation a pour but d'expliquer le fonctionnement de l'import des tables motrice de Tremas.

Pour des explications plus techniques, veuillez-vous reporter à la javadoc.

## Structure de la documentation

* Import brut
* Intégration dans le modèle Tremas du plan de transport


## Import brut

L'import brut depuis la base motrice DB2.

Le schéma utilisé pour la connexion à DB2 représente un transporteur :
* F$MDRP2 -> EuroStar Production
* F$MDRO2 -> EuroStar Recette
* F$MDRP3 -> Thalys Production
* F$MDRO3 -> Thalys Recette

Les tables de DB2 sont importées dans des tables identiques dans la base Tremas.
> Les régimes sont traduits dans la base Tremas. Nous avons aussi ajouté des identifiants aux enregistrements des tables (qui n'étaient pas présents dans DB2)

Ajout d'une table à importer:
> Le nom de la table DB2 doit être ajouté à la table tremas_ref_tables_motrice.

> Son entité doit être créée dans src\main\java\com\avancial\app\data\databean\importMotriceBrut\

> Si la table contient un champ devant être traité (tel que les champs régime) avant d'être inseré dans la base de Tremas, le nom de ce champ ainsi que l'instance de son traitement doivent être ajoutés à la map src\main\java\com\avancial\app\utilitaire\MapTraitementImportBrut.java

## Intégration dans le modèle Tremas du plan de transport
Pour des raisons de performance, nous utilisons des requêtes SQL natives pour insérer dans les tables motrice_regime. À chaque import, nous insérons les données table par table, en une (ou plusieurs) requête(s) de la forme:

    INSERT INTO table VALUES (...), (...), ...;

Nous utilisons une interface *IMultipleInsertRequestGenerator* pour générer ces requêtes, et nous avons besoin d'une instance par table. Le nombre de valeurs ajoutées en une seule requête est paramétrable.

Puisque les tables sont vidées au début de chaque import, nous pouvons initialiser nous-mêmes les id des données, et ainsi remplir toutes les contraintes de *foreign keys*.

À cause de ces contraintes, nous sommes obligés de setter les valeurs des entités une à une. Pour importer toutes les données dans le modèle, nous bouclons sur les train-tranche, et pour chaque nous récupérons tous les types de données (dessertes, services...).

Pour pouvoir insérer toutes les données d'un coup à la fin, des instances de *IMultipleInsertRequestGenerator* et de *Long* représentant les id sont partagées dans tous les traitements, au travers de deux *Map* dont les clés sont les classes des entités MotriceRegime.

L'algorithme de l'import est le suivant:
> 1. Initialisation des objets partagés par toutes les entités:
  * Liste des entités MotriceRegime (donnée de référence)
  * MapId
  * MapGenerator  
2. Chargement des train-tranche à partir des tables d'import brut
3. Boucle sur les train-tranche:
  * Insertion du régime lié au train-tranche
  * Insertion du train-tranche
  * Boucle sur les entités MotriceRegime
    * Traitement des données par train-tranche:  
      * Chargement du type de données et son régime associé
      * Ajout des valeurs dans les générateurs
      * Incrémentations des ids
4. Exécution des requêtes d'insertion dans les tables:  
**Attention** à cause des contraintes de *foreign keys*, l'insertion doit se faire dans un ordre particulier : d'abord les entités qui sont référencées dans des clés étrangères (donc en tout premier il faut remplir la table motrice_regime), en dernier les entités qui possèdent des clés étrangères (par exmemple, on remplit motrice_regime_composition avant motrice_regime_composition_coach)

## Intégration des données de référence
Lors de l'import, nous alimentons des tables de référence tremas_ref_motrice_xxx pour les données des plans de transport dans Motrice : par exemple, les codes Gare, code Sat, types d'équipement...

Pour remplir les tables, nous partons de nos tables d'import brut "tremas_import_tmdxxxx", en même temps que l'intégration dans le modèle Tremas. Toutes les données de référence sont liées à une compagnie (Eurostar ou Thalys).

Pour insérer dans les tables, on vérifie si la donnée existe déjà:
* si oui, on récupère l'id de la donnée, pour l'insérer dans la table tremas_motrice_regime_xxx correspondante
* sinon, on insère la donnée avant de récupérer son id.

Pour l'insertion, nous utilisons un EntityManager avec les entités de référence créées.
