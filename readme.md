# Projet jeux

## Prérequis:

Ce projet a été développé avec :
- jdk17 
- maven (3.8.4)
- node (v16.14.0)

## Téléchargement du projet

```
git clone https://github.com/metaXIII/jeux-angular-springboot.git
```

## Lancement de la base de donnée : 
```
docker-compose up -d
```

## Lancement du Backend : 

Se rendre dans le dossier backend et lancer la commande :
```
mvn spring-boot:run
```

## Lancement du frontend: 

Se rendre dans le dossier frontend et lancer les commandes :
```
npm install
npm start
```

## Reste à faire 
- Rapport de test avec Jacoco
- Dockeriser toute l'application
- Migrer pour une utilisation d'un model à la place de l'entity
- Améliorer le front
- Intégrer github actions.