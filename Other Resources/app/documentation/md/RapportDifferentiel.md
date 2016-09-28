# Documentation rapport différentiel


## Etapes du traitement
1. Récupération de l'environnement à traiter (compagnie : EuroStar, Thalys; et plateforme : recette, production)
2. Connexion à la base Motrice DB2
3. Import des données de la base motrice
4. Intégration des données brutes de motrice dans nos tables relationnelles SQL
5. Création des objets métier "plan de transport" pour les jeux de données "Draft" et "Active"
6. Comparaison des plans de transport "Draft" et "Active"
7. Génération du rapport différentiel Excel

## Création des objets métier "plan de transport"
Pour la comparaison du rapport différentiel, il y a deux plans de transport à générer :
* celui correspondant au jeu de données actif sur l'environnement, qui a été importé
en base précedemment ("Active");
* celui correspondant à l'import en cours, et représentant la base motrice dans
son état actuel ("Draft").

Dans l'optique d'un gain de vitesse de traitement, il a été décidé de créer le plan de
transport Draft" au moment même de son intégration dans nos bases de données : cela évite
de devoir sauvegarder les données puis de les charger immédiatement après.

### Plan de transport "Draft"
La création du plan de transport s'effectue en même temps que l'import, elle se fait donc
dans la classe TraitementMotrice. Elle est plus spécifiquement gérée par les implémentations
de l'interface ITraiteMotriceRegime : quand on ajoute une ligne en base, on instancie
l'objet du plan de transport correspondant, et on l'ajoute à un objet PlanTransport fourni
au traitement. Il existe une implémentation par ASousRegimeTranche.

Pour compléter le plan de transport, nous utilisons des AtomicReference<T>, qui permettent
de manipuler les objets par référence et ainsi de "transporter" ce contexte entre les classes,
le remplir au fur et à mesure.

### Plan de transport "Active"
Ici, il faut charger les données en base afin d'instancier les objets métier. Les classes
concrètes implémentent l'interface ITraiteObjetMetier, et il y en a une par ASousRegimeTranche
ainsi que pour l'objet Tranche.

La gestion de ce traitement est effectuée dans la classe TraitementObjetMetier. Le chargement tire partie du FetchType.EAGER mis en place dans les associations des entités. On commence par
charger tous les train-tranches du plan de transport, puis pour chaque train-tranche on charge
tous les régimes associés. Comme nous avons fait de l'association bidirectionnelle dans les
entités, on accède directement de l'objet Regime à la liste de ASousRegimeTranche
correspondante. Prenons l'exemple du CodeSat : il suffit de récupérer la liste de
MotriceRegimeSatcodeEntity de l'objet MotriceRegimeEntity, et pour chaque de créer l'objet
métier CodeSat correspondant. On l'ajoute au plan de transport fourni à la classe
TraitementObjetMetier (on utilise ici aussi les AtomicReference).

### Filtre du plan de transport par date
Pour le métier, seules les dates de circulation à J et postérieures sont intéressantes,
il a fallu filtrer les plans de transport en conséquence. Afin encore une fois de gagner
sur le temps de traitement, ce filtrage s'effectue lors du remplissage. Les dates de début
et de fin de période du filtre sont données à la classe de traitement globale
(TraitementMotrice et TraitementObjetMetier). Avant chaque insertion dans le plan de transport,
on enlève les dates dans le régime qui ne sont pas dans cette période : s'il ne reste aucune
date, l'objet n'est pas ajouté. Il ne faut pas oublier ensuite, au niveau du Train, de vérifier
s'il a des tranches qui ont passé le filtre : dans le cas contraire, on ne l'ajoute pas au plan
de transport.

## Comparaison des plans de transport
Pour comparer deux plans de transport, on se base d'une part sur les régimes des tranches et
leurs sous-régimes, et d'autre part sur les valeurs des attributs sur un même régime.

Pour le rapport différentiel, il y a cinq types de comparaison:
<dl>
  <dt>New</dt>
    <dd>Inclut les train-tranches soit complètement nouveaux dans le plan de transport
    Draft (pas présents dans l'Active), soit ceux dont le régime de la tranche a une
    date de fin supérieure à celle dans l'Active, soit enfin ceux qui ont des dates
    supplémentaires dans le régime de la tranche.</dd>
  <dt>Delete</dt>
    <dd>Inclut les train-tranches soit qui sont présents dans le plan de transport Active,
    mais pas dans le Draft, soit qui ont des dates en moins dans le régime de la tranche.</dd>
  <dt>Modify</dt>
    <dd>Inclut les champs (ou attributs) d'un train-tranche du plan de transport Draft
    dont la valeur a changé par rapport au plan de transport Active, sans que le régime
    n'ait été modifié.</dd>
  <dt>Regime Split</dt>
    <dd>Inclut les train-tranches et les attributs qui existaient dans le plan de
    transport Active mais dont la date de fin de régime est antérieure à celle
    du plan de transport Active.</dd>
  <dt>Unchanged</dt>
    <dd>Inclut les train-tranches dont tous les attributs n'ont pas changé entre le
    plan de transport Active et Draft.</dd>
</dl>

Il y a donc plusieurs niveaux de comparaison dans un plan de transport:
* Au premier niveau, en comparant deux plans de transport, on compare deux listes de trains :
on détecte alors des trains en New ou en Delete, et on peut ajouter toutes leurs tranches
dans les onglets correspondants. Ces tranches peuvent être enlevées des plans de transport
pour la suite de la comparaison.
* Au deuxième niveau, on compare deux trains présents dans les deux plans de transport, et on
compare leurs listes de tranches. On peut alors détecter les tranches New et Delete entre les
deux trains, et les enlever pour la suite de la comparaison.
* À ce niveau, il faut encore détecter les tranches en Regime Split, qui seront elles
conservées pour la suite de la comparaison. De même pour les tranches pour lesquelles
seulement des dates sont différentes entre les plans de transport Active et Draft.
* Au troisième niveau, on compare deux tranches présentes dans les deux plans de transport.
Il faut alors comparer leurs maps d'attributs, en comparant deux à deux des listes de même
type (par exemple, on compare la liste de CodeSat d'une tranche du plan de transport Active
avec la liste de CodeSat de la tranche correspondante dans le plan de transport Draft).
On commence par enlever tous les attributs en Unchanged, que l'on trouve à l'identique
dans les deux tranches. On relève alors ensuite les comparaisons de type Modify et Regime
Split. Si, entre deux tranche, il n'y a aucun résultat sur les comparaisons Modify et
Regime Split, c'est qu'elles sont identiques, et on les ajoute à l'onglet Unchanged.
