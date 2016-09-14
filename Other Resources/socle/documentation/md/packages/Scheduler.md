# Partie Scheduler  


###### 1.Scheduler
 La classe  SocleScheduler qui existe dans le package com.avancial.socle.scheduler est implementée de l'interface ISocleScheduler et permet de:
- Planifier les jobs actifs en utilsant la méthode  scheduleActiveJobs().
- Supprimer tous les jobs de la planification en utilisant la méthode unscheduleAllJobs().
- Charger la liste des jobs actif à partir de la base de données chargeActiveJobs().

###### 2.Initialisation de l'application
La servlet SocleInit qui exite dans le package com.avancial.socle.init, permet de :
- Initialiser l'application en utilisant la méthode init()
- Recharger tous les jobs actifs lors de l'intialisation de l'application en utilsant la méthode reload();

###### 3. Servlet implementation class SchedulerReload
La servlet SchedulerReload qui exite dans le package com.avancial.socle.faces.servlets, permet de recharger tous les jobs actifs  en utilsant la méthode reload();
