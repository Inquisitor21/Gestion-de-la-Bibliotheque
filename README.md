# Projet de Gestion de Bibliothèque

Ce projet est une application de gestion de bibliothèque développée en Java avec le framework Spring Boot et React pour le Front End. Il permet de gérer les utilisateurs, les documents (livres, CD, DVD, manuels scolaires) et les emprunts.

## Fonctionnalités

- **Gestion des utilisateurs** : Ajout, modification, suppression et consultation des emprunteurs et des préposés.
- **Gestion des documents** : Ajout, modification, suppression et consultation des livres, CD, DVD et manuels scolaires.
- **Gestion des emprunts** : Emprunt et retour de documents, gestion des stocks et des frais de retard.

## Prérequis

- Java 11 ou supérieur
- Maven
- Une base de données (H2, MySQL, PostgreSQL, etc.)

## Installation

1. Clonez le dépôt :
    ```bash
    git clone https://github.com/Inquisitor21/Gestion-de-la-Bibliotheque.git
    cd gestion-bibliotheque
    ```

2. Configurez la base de données dans le fichier `application.properties` :
    ```properties
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=password
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    ```

3. Compilez et exécutez l'application :
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Utilisation

### Endpoints

- **Utilisateur**
    - `POST /creer-emprunteur` : Créer un emprunteur
    - `GET /emprunteurs` : Lister les emprunteurs
    - `GET /emprunts-emprunteur/{id}` : Voir les emprunts d'un emprunteur

- **Document**
    - `POST /creer-livre` : Créer un livre
    - `GET /livres` : Lister les livres
    - `POST /emprunter-livre` : Emprunter un livre
    - `POST /retourner-livre/{id}` : Retourner un livre
    - `POST /retourner-livre-retard/{id}` : Retourner un livre avec retard
    - `POST /payer-amende/{id}` : Payer une amende
