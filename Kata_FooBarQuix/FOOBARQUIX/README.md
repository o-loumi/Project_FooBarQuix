# Projet REST - FooBarQuix

## Description
Ce projet fournit une API REST permettant de transformer un nombre en suivant des règles spécifiques :
- Si le nombre est divisible par 3 ou contient un 3, la transformation ajoute "FOO".
- Si le nombre est divisible par 5 ou contient un 5, la transformation ajoute "BAR".
- Si le nombre contient un 7, la transformation ajoute "QUIX".

L'ordre de priorité est donné par la règle de divisibilité, puis par la règle de présence dans le nombre.

## Prérequis
- Java 17
- Maven 3.6+
- Spring Boot 3.x

## Installation
1. Clonez le projet :
   ```bash
   git clone <repository-url>
   cd FOOBARQUIX

2. Installez les dépendances :
   mvn clean install

3. Lancement de l'application :
   mvn spring-boot:run
   L'application sera accessible sur http://localhost:8080.

## Endpoints

### `GET /api/{number}`
Transforme le nombre selon les règles.

- **Paramètre** :
   - `{number}` : Nombre entier à transformer.

- **Exemple** :
  ```bash
  curl http://localhost:8080/api/33

## Exemples de réponse

- Pour `33` :
   "FOOFOOFOO"

- Pour `57` :
  "FOOBARQUIX"

## Documentation OpenAPI

La documentation interactive de l'API est disponible à l'adresse suivante :  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)


## Tests
Pour exécuter les tests unitaires et d'intégration :
   mvn test

