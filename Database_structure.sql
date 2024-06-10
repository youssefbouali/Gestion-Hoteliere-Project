CREATE TABLE Departement (
    Id NUMBER PRIMARY KEY NOT NULL,
    Nom VARCHAR2(50)
);

CREATE TABLE Post (
    Id NUMBER PRIMARY KEY NOT NULL,
    Nom VARCHAR2(100) NOT NULL,
    DepartementID NUMBER,
    CONSTRAINT fk_departement_post FOREIGN KEY (DepartementID) REFERENCES Departement(Id)
);

CREATE TABLE Salaire (
    Id NUMBER PRIMARY KEY NOT NULL,
    Montant NUMBER NOT NULL
);

CREATE TABLE Employe (
    Id NUMBER PRIMARY KEY NOT NULL,
    Nom VARCHAR2(50) NOT NULL,
    Prenom VARCHAR2(50) NOT NULL,
    Type VARCHAR2(50) CHECK (Type IN ('Full-Time', 'Part-Time', 'Contract')) NOT NULL,
    Date_embauche DATE NOT NULL,
    Ville VARCHAR2(50) NOT NULL,
    PostID NUMBER,
    CONSTRAINT fk_post_employe FOREIGN KEY (PostID) REFERENCES Post(Id),
    SalaireID NUMBER,
    CONSTRAINT fk_salaire_employe FOREIGN KEY (SalaireID) REFERENCES Salaire(Id)
);

CREATE TABLE Machine (
    Id NUMBER PRIMARY KEY NOT NULL,
    Nom VARCHAR2(100) NOT NULL,
    DepartementID NUMBER,
    CONSTRAINT fk_departement_machine FOREIGN KEY (DepartementID) REFERENCES Departement(Id)
);




CREATE SEQUENCE employe_seq START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE salaire_seq START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE post_seq START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE departement_seq START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE machine_seq START WITH 1 INCREMENT BY 1 NOCYCLE;



CREATE INDEX employe_nom_idx ON Employe(Prenom, Nom);
CREATE INDEX salaire_montant_idx ON Salaire(Montant);
CREATE INDEX post_nom_idx ON Post(Nom);
CREATE INDEX departement_nom_idx ON Departement(Nom);
CREATE INDEX machine_nom_idx ON Machine(Nom);








CREATE TABLE Hotel (
    Id NUMBER PRIMARY KEY NOT NULL,
    Addresse VARCHAR2(50) NOT NULL,
    Nombre_pieces NUMBER Not NULL,
    Categorie VARCHAR2(50) Not NULL
);

CREATE TABLE Directeur (
    Id NUMBER PRIMARY KEY NOT NULL,
    Prenom VARCHAR2(50) NOT NULL,
    Nom VARCHAR2(50) NOT NULL
);

CREATE TABLE Gestion (
    Id NUMBER PRIMARY KEY NOT NULL,
    HotelID NUMBER,
    DirecteurID NUMBER,
    Date_dr DATE NOT NULL,
    CONSTRAINT fk_hotel FOREIGN KEY (HotelID) REFERENCES Hotel(Id),
    CONSTRAINT fk_directeur FOREIGN KEY (DirecteurID) REFERENCES Directeur(Id)
);

CREATE TABLE Service (
    Id NUMBER PRIMARY KEY NOT NULL,
    Nom VARCHAR2(50) NOT NULL,
    DirecteurID NUMBER,
    CONSTRAINT fk_directeur_service FOREIGN KEY (DirecteurID) REFERENCES Directeur(Id)
);







CREATE SEQUENCE hotel_seq START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE directeur_seq START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE gestion_seq START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE service_seq START WITH 1 INCREMENT BY 1 NOCYCLE;






CREATE INDEX idx_hotel_addresse ON Hotel(Addresse);
CREATE INDEX idx_directeur_nom ON Directeur(Nom);
CREATE INDEX idx_gestion_hotelid ON Gestion(HotelID);
CREATE INDEX idx_gestion_directeurid ON Gestion(DirecteurID);
CREATE INDEX idx_service_nom ON Service(Nom);






CREATE TABLE Chambre (
    Id NUMBER PRIMARY KEY,
    Numero NUMBER NOT NULL,
    Nombre_lits NUMBER NOT NULL,
    Prix NUMBER NOT NULL,
    HotelID NUMBER
);
-- ,   CONSTRAINT fk_hotel FOREIGN KEY (HotelID) REFERENCES Hotel(Id)

CREATE TABLE Personne (
    Id NUMBER PRIMARY KEY,
    Prenom VARCHAR2(50) NOT NULL,
    Nom VARCHAR2(50) NOT NULL,
    Type VARCHAR2(50) NOT NULL
);

CREATE TABLE Occupation (
    Id NUMBER PRIMARY KEY,
    Date_min DATE NOT NULL,
    Date_max DATE NOT NULL,
    Date_eng DATE NOT NULL,
    ChambreID NUMBER,
    PersonneID NUMBER,
    CONSTRAINT fk_chambre FOREIGN KEY (ChambreID) REFERENCES Chambre(Id),
    CONSTRAINT fk_personne FOREIGN KEY (PersonneID) REFERENCES Personne(Id)
);





CREATE SEQUENCE chambre_seq START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE personne_seq START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE occupation_seq START WITH 1 INCREMENT BY 1 NOCYCLE;





CREATE INDEX idx_chambre_numero ON Chambre(Numero);
CREATE INDEX idx_occupation_dates ON Occupation(Date_min, Date_max);
CREATE INDEX idx_personne_nom ON Personne(Prenom, Nom);