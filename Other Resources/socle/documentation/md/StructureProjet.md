# Structure d'un projet
Un projet est composé de 2 parties.
La première qui contient le code source et les test du socle ainsi que toutes les ressources associées reprend la structure d'un project maven.
La seconde partie est libre. Elle contient la documentation, les scripts sql de livraison, les scripts ant de mise à jour, etc.

###### 1. Répertoires racine d'un projet

|Répertoire|Commentaire|
|----------|-----------|
|.git|Répertoire de GIT|
|.settings|Fichiers de configuration du projet|
|src|Répertoire du code source|
|Other resources|Documentation, scripts SQL de livraison, ...
|target|Répertoire utilisé par Maven.<br/>  _<span class='icon icon-alert native-key-bindings'> Ne doit pas être inclus dans le contrôle des ressources</span>_|

###### 2. src/main/java

- _src/com/avancial_  
code source de l'application.
Il est composé de 2 pakages :  
 - app : le code source de l'application
 - socle : le code source du code

###### 3. /src/main/resources
Ressources de l'application.
- _/_  
hibernate.cfg.xml : fichier de configuration d'hibernate.  
- _/META-INF_  
persistence.xml : fichier de configuration de la persistence
- _/com/avancial/app/resources/_  
app.properties : fichier des propriétés de l'application

|Propriété|Valeur|
|----------|-----------|
|Version|Version actuelle de l'application|

- _/com/avancial/app/resources/internationalization/_
Message_fr.properties : contient tous les éléments textes qui seront affichés en français dans l'application

- _/com/avancial/socle/resources/_  
socle.properties : fichier des propriétés du socle

|Propriété|Valeur|
|----------|-----------|
|Version|Version actuelle du socle|
|date_livraison|date de livraison du socle sur la branche de production|

<span class='icon icon-tag'>NB : la présentation de cette arborescence sous eclipse ou sous un autre éditeur de code sera différente.</span>

###### 4. _src/main/webapp_
Contient ce qui va être déployé sur le tomcat hors Java cad l'application client. Le contenu de ce répertoire est placé dans WebContent dans un projet Eclipse.

###### 2. Tests
Les tests sont placés dans /main/test.
Ils sont organisés comme le code source sauf qu'il n'y a pas de webapp.
Ils doivent être organisés en packages reprennant la même organisation que le code source
