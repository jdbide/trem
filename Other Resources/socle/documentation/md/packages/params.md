# Gestion des Parametres

Ce package permet de gérer les paramètres applicatifs quelque soit leur nature (Application ou Socle), leur type (paramètres de versions, de répertoires, etc...)
et quelque soit leur provenance (fichier plat, sgbd, ...)

### Fonctionnement des paramètres du socle (cad communs à toutes les applications)

La classe principale est com.avancial.socle.params.AParamGetter. Elle est chargée de récupérer les paramètres sous forme de collection (map)
Par défaut, le sockle charge les paramètres standards.

* socle ,
* app et
* directories


Le fonctionnement le plus commun est d'appeler la méthode getParam qui demande le nom de la collection ainsi que le nom du paramètre. Ces informations sont stockées dans la classe d'énumération SOCLE_params.


### Fonctionnement des paramètres spécifiques à une application.

Pour les paramètres spécifiques à une application, il fauda utiliser la classe  com.avancial.app.model.managedbean.ParamGetterManagedBean.Il faut ajouter les collections à charger dals le constructeur.

### Configuration de l'application sur le fichier app.properties situé sous le repertoire (src/main/resources/com/avavancial/app/)
 - Les parametres de l'application sont:
 - La version de l'application
 - Le nom de l'application
 - La date du livraison de l'application

### Configuration du socle sur le fichier socle.properties situé dans le répertoire src/main/resources/com/avancial/socle/
Les paramètres du socle sont :
- La version du socle
- La date de livraison du socle

Ces paramètres sont accessibles à l'aide des constantes énumérées dans le fichier SOCLE_params et de la classe AParamGetter.


###### 1.  package Params
La classe AParamGetter située sous le package com.avancial.socle.params permet de:
- Récupérer le chemin d'accès au répertoire web-inf utilisé pour atteindre les fichiers de paramètres (.properties)  
- Récupérer le paramètre sous forme de bean
- Récupérer tous les paramètres associés à un type


###### 2. Test des paramètres
 La classe testRecupParametres située sous le repertoire (src/test/java/Params) sert à tester la récuperation des paramètres du socle et de l'application qui sont déja déclarés comme des constantes de type énuméré
 dans la classe "SOCLE_params" située sous le repertoire (com.avancial.socle.resources.constants)
