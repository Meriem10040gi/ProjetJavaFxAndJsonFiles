-- Création de la base de données CommandeClient
CREATE DATABASE CommandeClient;

-- Sélectionner la base de données CommandeClient

USE CommandeClient;

-- Création de la table Client
CREATE TABLE Client (
                        id VARCHAR(255) PRIMARY KEY,      -- Identifiant unique pour chaque client
                        nom VARCHAR(255) NOT NULL,        -- Nom du client
                        prenom VARCHAR(255) NOT NULL,     -- Prénom du client
                        email VARCHAR(255) NOT NULL,      -- Email du client
                        phone VARCHAR(20) NOT NULL        -- Numéro de téléphone du client
);

-- Création de la table Admin
CREATE TABLE Admin (   id int PRIMARY KEY ,
                       nom VARCHAR(255) NOT NULL,        -- Nom de l'admin
                       prenom VARCHAR(255) NOT NULL,     -- Prénom de l'admin
                       email VARCHAR(255) NOT NULL,      -- Email de l'admin
                       password VARCHAR(255) NOT NULL    -- Mot de passe de l'admin
);

-- Création de la table Commande
CREATE TABLE Commande (
                          id VARCHAR(255) PRIMARY KEY,      -- Identifiant unique pour chaque commande
                          amount DOUBLE NOT NULL,           -- Montant de la commande
                          client_id VARCHAR(255),           -- Référence au client
                          statut VARCHAR(50),               -- Statut de la commande (ex: 'En attente', 'Livrée', etc.)
                          dateAjout BIGINT NOT NULL,        -- Date de création de la commande (en timestamp Unix)

    -- Définition de la clé étrangère liant la commande au client
                          FOREIGN KEY (client_id) REFERENCES Client(id) ON DELETE CASCADE
);

-- insertion du admin
insert into Admin values(1,"admin","admin","admin@gmail.com","admin");
