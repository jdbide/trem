# Procédure de livraison socle 2
#### 1. Les étapes à suvire:

+ Vérifier que vous avez la version la plus à jour du code
+ Vérifier que vous n'avez pas de modifications à committer
+ Mettre à jour la version de l'application si nécessaire (app/resources/app.properties)
+ Modifier le fichier context.xml
+ Supprimer tout les éléments qui ne sont pas nécessaires pour l'environnement ciblé.
+ Décommenter si nécessaire les lignes de l'environnement
+ Faire un export du fichier .war
+ Faire un revert sur le context.xml
+ Vérifier les différences de versions des environments afin de jouer les requêtes si nécessaire.
Faire la même chose pour la partie socle si besoin
+ Se rendre sur l'application
+ http://[nom de l'appli].rec-avancial.com/[nom de l'appli] pour la livraison en recette 
+ http://[nom de l'appli].si-avancial.com/[nom de l'appli] pour la livraison en prod
+ Se connecter au manager tomcat
+ http://[nom de l'appli].rec-avancial.com/manager/html
+ http://[nom de l'appli].si-avancial.com/manager/html
+ Retirer l'application du serveur
+ Déployer la nouvelle version
+ Vérifier que l'application fonctionne.
+ Ne pas hésiter à s'y connecter
+ Faire le nécessaire sous Jira
+ Il faut distinguer dans quelle branche on est situé
+ Mettre à jour la branche master 
+ Faire le merge vers la branche master
