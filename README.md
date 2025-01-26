# Mode opératoire pour jouer au 2048

## Contenu du repository
- Le dossier `Documents` contient le rapport associé à ce projet
- Le dossier `Scripts` contient les scripts SQL permettant de créer les tables, d'insérer des données à l'intérieur et de supprimer les données
- Le dossier `src/main` contient le code complet pour faire fonctionner l'application


## Prérequis pour lancer l'application
- Installer [IntelliJ IDEA](https://www.jetbrains.com/fr-fr/idea/download/?section=windows)
- Installer [pgAdmin 4](https://www.pgadmin.org/download/)
- Télécharger le fichier JAR du JDBC driver de PostgreSQL pour Java à l'adresse suivante : https://jdbc.postgresql.org/download/. Le nom du fichier sera du style `postgresql-XX.X.X.jar`


## Démarches pour lancer l'application

### Clôner le repository
Exécuter la commande suivante dans un terminal à l'endroit où vous souhaitez stocker le projet
```bash
git clone https://github.com/Arthurmgnr/2048.git
```

### Paramètrage de la base de données
1. Ouvrir pgAdmin 4
2. Aller dans `Servers > PostgreSQL XX > Databases`
3. Faire clic droit sur `Databases` puis `Create > Database...`
4. Donner lui un nom dans le champ `Database`
5. Cliquer sur `Save`
6. Faire clic droit sur `PostgreSQL XX` puis `Properties...`
7. Dans l'onglet `Connection`, retenez l'username
8. Il faut également retenir le mot de passe que vous avez défini lors de la première ouverte de pgAdmin 4
9. Faire clic droit sur votre base de données créée précédemment puis `PSQL Tool`
10. Ensuite, il suffit d'exécuter les 2 commandes suivantes :
    - Pour créer les tables :
      ```
      \i '/{chemin_vers_le_repertoire}/Scripts/create.sql'
      ```
    - Pour ajouter les données dans les tables :
      ```
      \i '/{chemin_vers_le_repertoire}/Scripts/insert.sql'
      ```
NB : Vous pouvez vous amuser autant que vous voulez avec la base de données. Il y a aussi un 3e script pour supprimer les tables ainsi que les données dedans, en cas de problème :
```
\i '/{chemin_vers_le_repertoire}/Scripts/drop.sql'
```
    
### Paramètrage du projet
1. Ouvrir IntelliJ IDEA
2. Ouvrir le projet sous IntelliJ
3. Insérer le driver JDBC au projet
    1. Aller dans `File > Project Structure...`
    2. Aller dans `Libraries`
    3. Cliquer sur le `+` situé sous `Name`
    4. Chercher l'emplacement du fichier `postgresql-XX.X.X.jar` téléchargé précédemment
    5. Cliquer sur `OK`
    6. Sélectionner le fichier `.jar` et cliquer sur `Appliquer`
    7. Cliquer sur `OK`
4. Ouvrir le fichier `DBConnection`
    1. Modifier la valeur `2048` dans la variable `url` de la ligne 13 par le nom de votre base de données
    2. Modifier la variable `user` de la ligne 14 par votre identifiant de connexion à votre base de données
    3. Modifier la variable `password` de la ligne 15 par votre mot de passe de connexion à votre base de données

### Lancement de l'application
1. Ouvrir le fichier `Main`
2. Cliquer sur l'icône exécuter