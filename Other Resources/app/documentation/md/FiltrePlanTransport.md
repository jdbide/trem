# Documentation filtres des plans de transport

En fonction des besoins, plusieurs filtres ont été mis en place sur les plans de transport.

* **Filtre par période** : pour l'import et le contrôle, on ne veut que les dates dans une
période donnée dans les régimes
* **Critères pour une Tranche** : pour le contrôle, on veut filtrer les tranches par
rapport à certains critères (l'origine-destination notamment)
* **Filtre des données** : utilisé pour filter directement un plan de transport en
fonction d'un critère
* **Filtre des régimes Tranche** : pour la recherche et le calendrier, on veut filtrer
les dates de circulation d'un train (donc les dates du régime Tranche) en fonction de
certains critères de recherche

## Filtre par période
Ce filtre a été mis en place directement au chargement des plans de transport : lors
de l'instanciation des régimes pour l'objet métier PlanTransport, on filtre la liste
des jours en fonction de la période donnée. Si la liste des jours est vide après ce
filtre, alors la donnée n'est pas ajoutée au plan de transport.

## Critères pour une Tranche
L'idée ici était de définir des critères afin de sélectionner une tranche. Ces critères
peuvent être combinés, grâce à l'implémentation de filtres "ou" et "et", pour construire
des formules logiques. Pour l'implémentation, on peut se référer au [design pattern
filtre](https://www.tutorialspoint.com/design_pattern/filter_pattern.htm).  
L'interface générique `ICritere<T>` possède une unique méthode `boolean satisfaitCritere(T
 object)` qui retourne si oui ou non l'objet en paramètre obéit au critère implémenté.

## Filtre des données
Ce filtre suit le même design pattern "filtre" que les critères ci-dessus, mais
l'interface n'est pas la même : `IFiltre<T>` possède une unique méthode `T
filtreParCritere(T object)`, qui retourne une copie de l'objet donné en paramètre,
mais filtré par rapport au critère implémenté.  
La classe `FiltreSousRegimeTranche` est une implémentation prenant en attribut
n'importe quel objet `ASousRegimeTranche` en tant que filtre.

## Filtre des régimes Tranche
Ces filtres constituent une implémentation particulière de l'interface précédente.
Ils sont utilisés pour filtrer les dates de circulation dans une tranche, notamment
pour l'onglet de recherche. Pour ce faire, on enlève du régime de la tranche toutes
les dates des sous-régimes des attributs qui ne correspondent pas aux critères du filtre.  
Ces filtres sont implémentés dans les classes :
* `FiltreOuRegimePlanTransport`
* `FiltreEtRegimePlanTransport`
* `FiltreRegimeTranche`
* `FiltreRegimePlanTransport`
