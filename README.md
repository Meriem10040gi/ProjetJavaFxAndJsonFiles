**Description**

    Ce projet Java permet de lire des fichiers JSON contenant des informations sur les commandes et les clients, d’alimenter une base de données avec ces données, de vider le fichier d'entrée après traitement et d'écrire les données traitées dans d'autres fichiers (sortie et erreurs)

 
**Fonctionnalités**

   Lecture de fichiers JSON : Extraction des données sur les clients et commandes depuis un fichier JSON.
   
   Alimentation de la base de données : Les données extraites sont insérées dans une base de données MySQL

   Gestion des fichiers :  
   
       *Le fichier d'entrée est vidé après traitement.  
       
       *Les commandes réussies sont écrites dans un fichier de sortie.  
       
       *Les commandes qui échouent (erreurs SQL, etc.) sont enregistrées dans un fichier d'erreurs.
	
       *Interface utilisateur : Permet de sélectionner les fichiers (entrée, sortie, erreurs) via un navigateur de fichiers.

**Prérequis**

Java Development Kit (JDK) :  

Version requise : 11 ou supérieure.  

MySQL Server : Assurez-vous que MySQL est installé sur votre machine.  

Base de données : Avant de lancer l'application, créez la base de données en exécutant le fichier **database.sql**.
    

**Authentification**

L'email administrateur par défaut est : **admin@gmail.com**
Le mot de passe : **admin**
