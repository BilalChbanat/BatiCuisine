CREATE TYPE project_status AS ENUM ('EN_COURS', 'TERMINE', 'ANNULE');
CREATE TYPE type_composant AS ENUM ('maindoeuvre', 'material');

CREATE TABLE clients (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         adresse VARCHAR(255),
                         phone VARCHAR(20),
                         isProfessional BOOLEAN
);

CREATE TABLE composants (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            coutunitaire NUMERIC(10, 2),
                            typecomposant type_composant,
                            tauxTVA NUMERIC(5, 2)
);

CREATE TABLE materials (
                           quantite NUMERIC(10, 2),
                           coutTransport NUMERIC(10, 2),
                           coefficientqualite NUMERIC(5, 2)
) INHERITS (composants);

CREATE TABLE maindoeuvres (
                              tauxhoraire NUMERIC(10, 2),
                              heurestravail NUMERIC(10, 2),
                              productiviteOuvrier NUMERIC(5, 2)
) INHERITS (composants);

CREATE TABLE devis (
                       id SERIAL PRIMARY KEY,
                       montantestime NUMERIC(10, 2),
                       dateValidite DATE,
                       accepte BOOLEAN
);

CREATE TABLE projets (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         profitMargin DOUBLE PRECISION,
                         totalCost DOUBLE PRECISION,
                         projectStatus project_status,
                         clientid INTEGER REFERENCES clients(id) ON DELETE SET NULL
);
