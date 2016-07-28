# Documentation traitement import brute

Cette documentation a pour but d'expliquer le fonctionnement des traitement des données avant l'insertion brute dans les tables de Tremas.

Pour des explications plus techniques, veuillez-vous reporter à la javadoc.

### Structure de la documentation

* Map
* Traitements

### Map

Pour vérifier si une donnée importé a besoin d'être taiter nous avons créé une Map:** MapTraitementImportBrut ** extends _HashMap< String, ITraitementDonnees >_ (src\main\java\com\avancial\app\utilitaire\MapTraitementImportBrut.java).

_String:_ Contient le nom des champs qui ont besoin d'un traitement.
_ITraitementDonnees:_ Contient une instance du traitement a effectuer sur ce champ.

### Traitements

Les traitements de donnée d'import brute sont placé dans src\main\java\com\avancial\app\utilitaire\ .

Ils doivent implementer ITraitementDonnees qui contitent:  _public String execute(String donnee) throws ParseException_

Ceci nous permet d'utiliser la Map de cette façon : _mapTraitementImportBrut.get(colonne).execute(donnee)_
