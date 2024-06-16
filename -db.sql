CREATE TABLE Hotel (
    adresse VARCHAR2(100),
    nombrePieces NUMBER,
    categorie VARCHAR2(50),
    CONSTRAINT pk_hotel PRIMARY KEY (adresse)
);

CREATE TABLE Chambre (
    numero NUMBER,
    nombreLits NUMBER,
    typeSalleDeBain VARCHAR2(50),
    prix FLOAT,
    estOccupee CHAR(1) CHECK (estOccupee IN ('Y', 'N')),
    adresseHotel VARCHAR2(100),
    CONSTRAINT pk_chambre PRIMARY KEY (numero, adresseHotel),
    CONSTRAINT fk_chambre_hotel FOREIGN KEY (adresseHotel) REFERENCES Hotel(adresse)
);

CREATE TABLE Personne (
    nom VARCHAR2(50),
    prenom VARCHAR2(50),
    CONSTRAINT pk_personne PRIMARY KEY (nom, prenom)
);

CREATE TABLE Adulte (
    nom VARCHAR2(50),
    prenom VARCHAR2(50),
    estAdulte CHAR(1) CHECK (estAdulte IN ('Y', 'N')),
    CONSTRAINT pk_occupant PRIMARY KEY (nom, prenom),
    CONSTRAINT fk_occupant_personne FOREIGN KEY (nom, prenom) REFERENCES Personne(nom, prenom)
);

CREATE TABLE Enfant (
    nom VARCHAR2(50),
    prenom VARCHAR2(50),
    CONSTRAINT pk_enfant PRIMARY KEY (nom, prenom),
    CONSTRAINT fk_enfant_occupant FOREIGN KEY (nom, prenom) REFERENCES Adulte(nom, prenom)
);


CREATE TABLE Location (
    numeroChambre NUMBER,
    adresseHotel VARCHAR2(100),
    nomAdulte VARCHAR2(50),
    prenomAdulte VARCHAR2(50),
    location_date DATE,
    CONSTRAINT pk_location PRIMARY KEY (numeroChambre, adresseHotel, nomAdulte, prenomAdulte, location_date),
    CONSTRAINT fk_location_chambre FOREIGN KEY (numeroChambre, adresseHotel) REFERENCES Chambre(numero, adresseHotel),
    CONSTRAINT fk_location_adulte FOREIGN KEY (nomAdulte, prenomAdulte) REFERENCES Adulte(nom, prenom)
);


















CREATE TABLE Employe (
    nom VARCHAR2(50),
    prenom VARCHAR2(50),
    salaire FLOAT,
    CONSTRAINT pk_employe PRIMARY KEY (nom, prenom),
    CONSTRAINT fk_employe_personne FOREIGN KEY (nom, prenom) REFERENCES Personne(nom, prenom)
);

CREATE TABLE Directeur (
    nom VARCHAR2(50),
    prenom VARCHAR2(50),
    salaire FLOAT,
    CONSTRAINT pk_directeur PRIMARY KEY (nom, prenom),
    CONSTRAINT fk_directeur_personne FOREIGN KEY (nom, prenom) REFERENCES Personne(nom, prenom)
);