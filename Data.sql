INSERT INTO Employe (Id, Nom, Prenom, Type, Date_embauche, Ville, PostID, SalaireID) VALUES (employe_seq.NEXTVAL, 'Youssef', 'Bouali', 'Full-Time', TO_DATE('2022-01-10', 'YYYY-MM-DD'), 'Casablanca', 1, 1);
INSERT INTO Employe (Id, Nom, Prenom, Type, Date_embauche, Ville, PostID, SalaireID) VALUES (employe_seq.NEXTVAL, 'Hamza', 'Anouar', 'Part-Time', TO_DATE('2023-03-15', 'YYYY-MM-DD'), 'Marrakech', 2, 2);
INSERT INTO Employe (Id, Nom, Prenom, Type, Date_embauche, Ville, PostID, SalaireID) VALUES (employe_seq.NEXTVAL, 'Oussama', 'Lamzily', 'Contract', TO_DATE('2021-11-20', 'YYYY-MM-DD'), 'Agadir', 3, 3);



INSERT INTO Machine (Id, Nom, DepartementID) VALUES (machine_seq.NEXTVAL, 'Printer', 1);
INSERT INTO Machine (Id, Nom, DepartementID) VALUES (machine_seq.NEXTVAL, 'Server', 2);
INSERT INTO Machine (Id, Nom, DepartementID) VALUES (machine_seq.NEXTVAL, 'Calculator', 3);









INSERT INTO Hotel (Id, Addresse, Nombre_pieces, Categorie) VALUES (hotel_seq.NEXTVAL, '123 Marrakech', 20, '5_Stars');
INSERT INTO Hotel (Id, Addresse, Nombre_pieces, Categorie) VALUES (hotel_seq.NEXTVAL, '456 Fes', 10, '4_Stars');

INSERT INTO Directeur (Id, Prenom, Nom) VALUES (directeur_seq.NEXTVAL, 'Oussama', 'Lamzily');
INSERT INTO Directeur (Id, Prenom, Nom) VALUES (directeur_seq.NEXTVAL, 'Youssef', 'Bouali');
INSERT INTO Directeur (Id, Prenom, Nom) VALUES (directeur_seq.NEXTVAL, 'Hamza', 'Anouar');

INSERT INTO Gestion (Id, HotelID, DirecteurID, Date_dr) VALUES (gestion_seq.NEXTVAL, 1, 1, TO_DATE('2023-01-01', 'YYYY-MM-DD'));
INSERT INTO Gestion (Id, HotelID, DirecteurID, Date_dr) VALUES (gestion_seq.NEXTVAL, 2, 2, TO_DATE('2023-02-01', 'YYYY-MM-DD'));

INSERT INTO Service (Id, Nom, DirecteurID) VALUES (service_seq.NEXTVAL, 'Payment', 1);
INSERT INTO Service (Id, Nom, DirecteurID) VALUES (service_seq.NEXTVAL, 'Administration', 1);





INSERT INTO Chambre (Id, Numero, Nombre_lits, Prix, HotelID) VALUES (chambre_seq.NEXTVAL, 1, 2, 100, 1);
INSERT INTO Chambre (Id, Numero, Nombre_lits, Prix, HotelID) VALUES (chambre_seq.NEXTVAL, 2, 3, 150, 1);

INSERT INTO Personne (Id, Prenom, Nom, Type) VALUES (personne_seq.NEXTVAL, 'Hamza', 'Anouar', 'Guest');
INSERT INTO Personne (Id, Prenom, Nom, Type) VALUES (personne_seq.NEXTVAL, 'Mohammed', 'Mohammed', 'Staff');

INSERT INTO Occupation (Id, Date_min, Date_max, Date_eng, ChambreID, PersonneID) VALUES (occupation_seq.NEXTVAL, TO_DATE('2024-06-01', 'YYYY-MM-DD'), TO_DATE('2024-06-05', 'YYYY-MM-DD'), TO_DATE('2024-05-25', 'YYYY-MM-DD'), 1, 1);



COMMIT;