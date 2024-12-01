# Projet Batch - FooBarQuix

## Description
Ce projet Batch permet de lire un fichier contenant des nombres, d'appliquer des transformations spécifiques sur ces nombres, et d'écrire les résultats dans un fichier de sortie.

---

## Prérequis
- Java 17
- Maven 3.6+
- Spring Boot 3.x

---

## Installation

1. Clonez ce dépôt :
   ```bash
   git clone <repository-url>
   cd Batch

2. Installez les dépendances :
   mvn clean install

## Configuration
Fichier d'entrée :
Le fichier d'entrée doit être nommé input.txt et placé dans le dossier src/main/resources/static/.
Chaque ligne doit contenir un seul nombre entier.


Fichier de sortie :
Le fichier de sortie sera généré dans le dossier src/main/resources sous le nom output.txt.

## Lancement du batch
mvn spring-boot:run

## Fonctionnement du batch

Le batch va :

1. Lire le fichier d'entrée `input.txt`, ligne par ligne.
2. Appliquer les transformations sur chaque ligne (en fonction des règles de divisibilité et de présence des chiffres).
3. Écrire les résultats dans le fichier de sortie `output.txt`.

### Format du fichier de sortie

Le fichier `output.txt` contiendra les transformations de chaque nombre du fichier d'entrée sous la forme suivante :
[number] -> [transformed number]

### Exemple

#### Contenu du fichier d'entrée (`input.txt`) : 
33 57 9

#### Contenu du fichier de sortie (`output.txt`) après traitement :
33 FOOFOOFOO
57 FOOBARQUIX
9  FOO
   


