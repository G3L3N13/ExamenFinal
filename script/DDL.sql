-- database: ../DataBase/EcuFauna.sqlite
DROP TABLE IF EXISTS OGHormiga;

DROP TABLE IF EXISTS OGCatalogoGeografia;

DROP TABLE IF EXISTS OGCatalogoAlimento;

DROP TABLE IF EXISTS OGCatalogoTipoGeografia;

DROP TABLE IF EXISTS OGCatalogoTipoAlimento;

CREATE TABLE
    CatalogoTipoAlimento (
        idCatalogoTipoAl INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        nombre VARCHAR(50) NOT NULL UNIQUE,
        descripcion VARCHAR(100) NOT NULL,
        estado VARCHAR(1) NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X')),
        FechaCreacion DATETIME DEFAULT (CURRENT_TIMESTAMP)
    );


CREATE TABLE
    CatalogoTipoGeografia (
        idCatalogoTipoGeo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        nombre VARCHAR(50) NOT NULL UNIQUE,
        descripcion VARCHAR(100) NOT NULL,
        estado VARCHAR(1) NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X')),
        FechaCreacion DATETIME DEFAULT (CURRENT_TIMESTAMP)
    );

CREATE TABLE
    CatalogoGeografia (
        idCatalogoGeo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        idCatalogoTipoGeo INTEGER NOT NULL REFERENCES OGCatalogoTipoGeografia (idCatalogoTipoGeo),
        idRegion INTEGER REFERENCES OG
    CatalogoGeografia (idCatalogoGeo),
        nombre VARCHAR(50) NOT NULL UNIQUE,
        descripcion VARCHAR(100) NOT NULL,
        estado VARCHAR(1) NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X')),
        FechaCreacion DATETIME DEFAULT (CURRENT_TIMESTAMP)
    );

CREATE TABLE
    Hormiga (
        idHormiga INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        idSexo INTEGER NOT NULL REFERENCES OGCatalogoAlimento (idCatalogoAl),
        idGenoAlimento INTEGER NOT NULL REFERENCES OGCatalogoAlimento (idCatalogoAl),
        idIngestaNativa INTEGER NOT NULL REFERENCES OGCatalogoAlimento (idCatalogoAl),
        idProvincia INTEGER NOT NULL REFERENCES OG
    CatalogoGeografia (idCatalogoGeo),
        tipoHormiga VARCHAR(50) NOT NULL,
        nombre VARCHAR(50) NOT NULL UNIQUE,
        estado VARCHAR(1) NOT NULL DEFAULT 'A' CHECK (estado IN ('A', 'X')),
        FechaCreacion DATETIME DEFAULT (CURRENT_TIMESTAMP)
    );

CREATE TABLE
    CatalogoEvoluciones (
        idEvolucion INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        nombreEvolucion VARCHAR(50) NOT NULL UNIQUE,
        descripcion VARCHAR(100) NOT NULL
    );
