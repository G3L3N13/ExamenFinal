-- database: ../DataBase/EcuFauna.sqlite
INSERT INTO CATALOGOTIPOALIMENTO (
    NOMBRE,
    DESCRIPCION
) VALUES (
    'IngestaNativa',
    'Tipos de ingesta como Carnívoro, Herbívoro, etc.'
), --1
(
    'GenoAlimento',
    'Clasificación de alimentos basados en genética como X, XX, XY'
), --2
(
    'Sexo',
    'Clasificación por sexo como Macho, Hembra, Asexual'
);

--3
INSERT INTO CATALOGOALIMENTO (
    IDCATALOGOTIPOAL,
    NOMBRE,
    DESCRIPCION
) VALUES (
    1,
    'Carnivoro',
    'Tipos de ingesta: Carnívoro'
), --1
(
    1,
    'Herbivoro',
    'Tipos de ingesta: Herbívoro'
), --2
(
    1,
    'Omnivoro',
    'Tipos de ingesta: Omnívoro'
), --3
(
    1,
    'Insectivoro',
    'Tipos de ingesta: Insectívoro'
), --4
(
    2,
    'X',
    'Genotipo X relacionado con GenoAlimento'
), --5
(
    2,
    'XX',
    'Genotipo XX relacionado con GenoAlimento'
), --6
(
    2,
    'XY',
    'Genotipo XY relacionado con GenoAlimento'
), --7
(
    3,
    'Macho',
    'Sexo: Macho'
), --8
(
    3,
    'Hembra',
    'Sexo: Hembra'
), --9
(
    3,
    'Asexual',
    'Clasificación por sexo: Asexual'
);

--10
INSERT INTO CATALOGOTIPOGEOGRAFIA (
    NOMBRE,
    DESCRIPCION
) VALUES (
    'País',
    'Ecuador y otros países'
), --1
(
    'Región',
    'Regiones del Ecuador'
), --2
(
    'Provincia',
    'Provincias del Ecuador'
);

--3
INSERT INTO CATALOGOGEOGRAFIA (
    IDCATALOGOTIPOGEO,
    IDREGION,
    NOMBRE,
    DESCRIPCION
) VALUES (
    1,
    NULL,
    'Ecuador',
    'País en América del Sur'
), -- 1
-- Regiones de Ecuador
(
    2,
    1,
    'Costa',
    'Región de la Costa del Ecuador'
), -- 2
(
    2,
    1,
    'Sierra',
    'Región de la Sierra del Ecuador'
), -- 3
(
    2,
    1,
    'Amazonía',
    'Región de la Amazonía del Ecuador'
), -- 4
(
    2,
    1,
    'Insular',
    'Región Insular del Ecuador'
), -- 5
-- Provincias de la Costa
(
    3,
    2,
    'Esmeraldas',
    'Provincia de la región Costa'
), -- 6
(
    3,
    2,
    'Manabí',
    'Provincia de la región Costa'
), -- 7
(
    3,
    2,
    'Los Ríos',
    'Provincia de la región Costa'
), -- 8
(
    3,
    2,
    'Guayas',
    'Provincia de la región Costa'
), -- 9
(
    3,
    2,
    'Santa Elena',
    'Provincia de la región Costa'
), -- 10
(
    3,
    2,
    'El Oro',
    'Provincia de la región Costa'
), -- 11
-- Provincias de la Sierra
(
    3,
    3,
    'Carchi',
    'Provincia de la región Sierra'
), -- 12
(
    3,
    3,
    'Imbabura',
    'Provincia de la región Sierra'
), -- 13
(
    3,
    3,
    'Pichincha',
    'Provincia de la región Sierra'
), -- 14
(
    3,
    3,
    'Cotopaxi',
    'Provincia de la región Sierra'
), -- 15
(
    3,
    3,
    'Tungurahua',
    'Provincia de la región Sierra'
), -- 16
(
    3,
    3,
    'Bolívar',
    'Provincia de la región Sierra'
), -- 17
(
    3,
    3,
    'Chimborazo',
    'Provincia de la región Sierra'
), -- 18
(
    3,
    3,
    'Cañar',
    'Provincia de la región Sierra'
), -- 19
(
    3,
    3,
    'Azuay',
    'Provincia de la región Sierra'
), -- 20
(
    3,
    3,
    'Loja',
    'Provincia de la región Sierra'
), -- 21
-- Provincias de la Amazonía
(
    3,
    4,
    'Sucumbíos',
    'Provincia de la región Amazonía'
), -- 22
(
    3,
    4,
    'Napo',
    'Provincia de la región Amazonía'
), -- 23
(
    3,
    4,
    'Orellana',
    'Provincia de la región Amazonía'
), -- 24
(
    3,
    4,
    'Pastaza',
    'Provincia de la región Amazonía'
), -- 25
(
    3,
    4,
    'Morona Santiago',
    'Provincia de la región Amazonía'
), -- 26
(
    3,
    4,
    'Zamora Chinchipe',
    'Provincia de la región Amazonía'
), -- 27
-- Provincias de la Región Insular
(
    3,
    5,
    'Galápagos',
    'Provincia de la región Insular'
);

INSERT INTO HORMIGA (
    IDSEXO,
    IDGENOALIMENTO,
    IDINGESTANATIVA,
    IDPROVINCIA,
    TIPOHORMIGA,
    NOMBRE
) VALUES (
    8,
    5,
    1,
    14,
    'Trabajadora',
    'Hormiga1'
),
(
    9,
    6,
    2,
    10,
    'Reina',
    'Hormiga2'
);

-- 28
SELECT
    NOMBRE,
    DESCRIPCION
FROM
    CATALOGOGEOGRAFIA
WHERE
    IDCATALOGOTIPOGEO = 3
    AND IDREGION = (
        SELECT
            IDCATALOGOGEO
        FROM
            CATALOGOGEOGRAFIA
        WHERE
            NOMBRE = 'Costa'
            AND IDCATALOGOTIPOGEO = 2
    );

INSERT INTO CATALOGOEVOLUCIONES (
    NOMBREEVOLUCION,
    DESCRIPCION
) VALUES (
    'Larva',
    'Estado inicial de la hormiga'
),
(
    'Obrera',
    'Hormiga trabajadora'
),
(
    'Reina',
    'Hormiga reproductora'
),
(
    'Soldado',
    'Hormiga defensora del hormiguero'
),
(
    'Guerrero',
    'Evolución avanzada del soldado'
);