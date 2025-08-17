INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'BA24',
    6,
    20,
    120,
    (SELECT id FROM cartons WHERE name='PC-A-19'),
    (SELECT id FROM trays WHERE name='DE163'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'BA25',
    NULL,
    NULL,
    105,
    (SELECT id FROM cartons WHERE name='ERBL205190'),
    (SELECT id FROM trays WHERE name='BE205190'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'BA26',
    4,
    24,
    96,
    (SELECT id FROM cartons WHERE name='PC-A-8'),
    (SELECT id FROM trays WHERE name='DE235162'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'BA27',
    4,
    20,
    80,
    (SELECT id FROM cartons WHERE name='PC-A-38'),
    (SELECT id FROM trays WHERE name='DE206153'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'BA28',
    6,
    20,
    120,
    (SELECT id FROM cartons WHERE name='PC-A-81'),
    (SELECT id FROM trays WHERE name='DE163140'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CH01',
    3,
    24,
    72,
    (SELECT id FROM cartons WHERE name='PC-A-118'),
    (SELECT id FROM trays WHERE name='DJ172'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CH03',
    5,
    30,
    150,
    (SELECT id FROM cartons WHERE name='PC-A-11'),
    (SELECT id FROM trays WHERE name='DE165'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CH08',
    5,
    24,
    120,
    (SELECT id FROM cartons WHERE name='PC-A-118'),
    (SELECT id FROM trays WHERE name='DJ144'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CH09',
    3,
    30,
    90,
    (SELECT id FROM cartons WHERE name='PC-A-32'),
    (SELECT id FROM trays WHERE name='DJ172'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CH11',
    3,
    30,
    90,
    (SELECT id FROM cartons WHERE name='PC-A-8'),
    (SELECT id FROM trays WHERE name='DE191'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CH19',
    6,
    20,
    120,
    (SELECT id FROM cartons WHERE name='PC-A-2'),
    (SELECT id FROM trays WHERE name='144B'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CH22',
    3,
    24,
    72,
    (SELECT id FROM cartons WHERE name='PC-A-118'),
    (SELECT id FROM trays WHERE name='DJ172'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CH24',
    3,
    24,
    72,
    (SELECT id FROM cartons WHERE name='PC-A-40'),
    (SELECT id FROM trays WHERE name='DJ172'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CH30',
    3,
    25,
    75,
    (SELECT id FROM cartons WHERE name='PC-A-95'),
    (SELECT id FROM trays WHERE name='DE191'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CH31',
    5,
    25,
    125,
    (SELECT id FROM cartons WHERE name='PC-A-95'),
    (SELECT id FROM trays WHERE name='DE165'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CH32',
    5,
    20,
    100,
    (SELECT id FROM cartons WHERE name='PC-A-97'),
    (SELECT id FROM trays WHERE name='DE165'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CH33',
    3,
    30,
    90,
    (SELECT id FROM cartons WHERE name='PC-A-8'),
    (SELECT id FROM trays WHERE name='DE191'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CH34',
    3,
    24,
    72,
    (SELECT id FROM cartons WHERE name='PC-A-118'),
    (SELECT id FROM trays WHERE name='DJ165'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CH35',
    6,
    25,
    150,
    (SELECT id FROM cartons WHERE name='PC-A-95'),
    (SELECT id FROM trays WHERE name='144B'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CM06',
    NULL,
    NULL,
    36,
    (SELECT id FROM cartons WHERE name='ERBL267A'),
    (SELECT id FROM trays WHERE name='BE267A'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CM07',
    NULL,
    NULL,
    54,
    (SELECT id FROM cartons WHERE name='ERBL267A'),
    (SELECT id FROM trays WHERE name='BE267A'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'CM08',
    NULL,
    NULL,
    52,
    (SELECT id FROM cartons WHERE name='ERBL229A'),
    (SELECT id FROM trays WHERE name='BE229A'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'DA10',
    5,
    24,
    120,
    (SELECT id FROM cartons WHERE name='PC-A-11'),
    (SELECT id FROM trays WHERE name='DE165'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'DA11',
    NULL,
    NULL,
    75,
    (SELECT id FROM cartons WHERE name='EBL2'),
    (SELECT id FROM trays WHERE name='BE178'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'FD11',
    5,
    28,
    140,
    (SELECT id FROM cartons WHERE name='PC-A-41'),
    (SELECT id FROM trays WHERE name='DE178134'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'FD12',
    5,
    20,
    100,
    (SELECT id FROM cartons WHERE name='PC-A-58'),
    (SELECT id FROM trays WHERE name='DE178134'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'FD14',
    6,
    20,
    120,
    (SELECT id FROM cartons WHERE name='PC-A-19'),
    (SELECT id FROM trays WHERE name='144B'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'FT13',
    5,
    16,
    80,
    (SELECT id FROM cartons WHERE name='PC-A-106'),
    (SELECT id FROM trays WHERE name='DE180113A'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'FT14',
    6,
    20,
    120,
    (SELECT id FROM cartons WHERE name='PC-A-131'),
    (SELECT id FROM trays WHERE name='DE156130'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'FT15',
    3,
    20,
    60,
    (SELECT id FROM cartons WHERE name='PC-A-19'),
    (SELECT id FROM trays WHERE name='DE187A'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'FT16',
    5,
    16,
    80,
    (SELECT id FROM cartons WHERE name='PC-A-106'),
    (SELECT id FROM trays WHERE name='DE180113A'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'FT17',
    5,
    24,
    120,
    (SELECT id FROM cartons WHERE name='PC-A-165'),
    (SELECT id FROM trays WHERE name='DG180113'),
    'GM6'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'FT19',
    4,
    20,
    80,
    (SELECT id FROM cartons WHERE name='PC-A-12'),
    (SELECT id FROM trays WHERE name='DE185135'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'FT20',
    4,
    30,
    120,
    (SELECT id FROM cartons WHERE name='PC-A-170'),
    (SELECT id FROM trays WHERE name='DG206139'),
    'GM6'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'FT21',
    4,
    16,
    64,
    (SELECT id FROM cartons WHERE name='PC-A-97'),
    (SELECT id FROM trays WHERE name='DE185135'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'GM05',
    3,
    25,
    75,
    (SELECT id FROM cartons WHERE name='PC-A-95'),
    (SELECT id FROM trays WHERE name='DE184'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'HY08',
    6,
    24,
    144,
    (SELECT id FROM cartons WHERE name='PC-A-86'),
    (SELECT id FROM trays WHERE name='DG152'),
    'HG5'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'HY10',
    6,
    24,
    144,
    (SELECT id FROM cartons WHERE name='PC-A-103'),
    (SELECT id FROM trays WHERE name='DG152'),
    'GM6'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'HY12',
    6,
    20,
    120,
    (SELECT id FROM cartons WHERE name='PC-A-145'),
    (SELECT id FROM trays WHERE name='DE152'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'HY13',
    6,
    24,
    144,
    (SELECT id FROM cartons WHERE name='PC-A-86'),
    (SELECT id FROM trays WHERE name='DG152'),
    'GM6'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'HY14',
    3,
    18,
    54,
    (SELECT id FROM cartons WHERE name='PC-A-102'),
    (SELECT id FROM trays WHERE name='DG178'),
    'HG5'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'HY15',
    3,
    24,
    72,
    (SELECT id FROM cartons WHERE name='PC-A-155'),
    (SELECT id FROM trays WHERE name='DG178'),
    'GM6'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'HY16',
    3,
    24,
    72,
    (SELECT id FROM cartons WHERE name='PC-A-154'),
    (SELECT id FROM trays WHERE name='DG178'),
    'HG5'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'ID04',
    4,
    24,
    96,
    (SELECT id FROM cartons WHERE name='PC-A-138'),
    (SELECT id FROM trays WHERE name='DG172A'),
    'GM6'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'ID05',
    6,
    24,
    144,
    (SELECT id FROM cartons WHERE name='PC-A-165'),
    (SELECT id FROM trays WHERE name='DG144B'),
    'GM6'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'JM01',
    6,
    20,
    120,
    (SELECT id FROM cartons WHERE name='PC-A-2'),
    (SELECT id FROM trays WHERE name='144B'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'JM02',
    5,
    20,
    100,
    (SELECT id FROM cartons WHERE name='PC-A-97'),
    (SELECT id FROM trays WHERE name='DE165'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'JM03',
    3,
    24,
    72,
    (SELECT id FROM cartons WHERE name='PC-A-118'),
    (SELECT id FROM trays WHERE name='DJ165'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'JM04',
    5,
    25,
    125,
    (SELECT id FROM cartons WHERE name='PC-A-95'),
    (SELECT id FROM trays WHERE name='DE165'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'KB13',
    6,
    30,
    180,
    (SELECT id FROM cartons WHERE name='PC-A-8'),
    (SELECT id FROM trays WHERE name='144B'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'KB22',
    3,
    24,
    72,
    (SELECT id FROM cartons WHERE name='PC-A-40'),
    (SELECT id FROM trays WHERE name='DJ172'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'KB23',
    6,
    20,
    180,
    (SELECT id FROM cartons WHERE name='PC-A-2'),
    (SELECT id FROM trays WHERE name='144B'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'KB24',
    6,
    25,
    180,
    (SELECT id FROM cartons WHERE name='PC-A-19'),
    (SELECT id FROM trays WHERE name='144B'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'KB28',
    2,
    25,
    50,
    (SELECT id FROM cartons WHERE name='PC-A-19'),
    (SELECT id FROM trays WHERE name='DE271'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'KG01',
    6,
    24,
    144,
    (SELECT id FROM cartons WHERE name='PC-A-149'),
    (SELECT id FROM trays WHERE name='DG152'),
    'HG5'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'LR08',
    4,
    28,
    112,
    (SELECT id FROM cartons WHERE name='PC-A-120'),
    (SELECT id FROM trays WHERE name='DE200155'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'MA02',
    6,
    16,
    96,
    (SELECT id FROM cartons WHERE name='PC-A-14'),
    (SELECT id FROM trays WHERE name='144B'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'MA03',
    6,
    20,
    120,
    (SELECT id FROM cartons WHERE name='PC-A-12'),
    (SELECT id FROM trays WHERE name='144B'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'MA04',
    5,
    16,
    80,
    (SELECT id FROM cartons WHERE name='PC-A-97'),
    (SELECT id FROM trays WHERE name='DE178114'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'MA05',
    4,
    36,
    144,
    (SELECT id FROM cartons WHERE name='PC-A-49'),
    (SELECT id FROM trays WHERE name='DG144'),
    'HG5'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'MA06',
    4,
    45,
    180,
    (SELECT id FROM cartons WHERE name='PC-A-52'),
    (SELECT id FROM trays WHERE name='DG144'),
    'HG5'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'MA07',
    5,
    24,
    120,
    (SELECT id FROM cartons WHERE name='PC-A-165'),
    (SELECT id FROM trays WHERE name='DG178114'),
    'GM6'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'MA09',
    4,
    45,
    180,
    (SELECT id FROM cartons WHERE name='PC-A-164'),
    (SELECT id FROM trays WHERE name='DG144'),
    'HG5'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'MM05',
    6,
    30,
    180,
    (SELECT id FROM cartons WHERE name='PC-A-8'),
    (SELECT id FROM trays WHERE name='144B'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'MM07',
    6,
    24,
    144,
    (SELECT id FROM cartons WHERE name='PC-A-147'),
    (SELECT id FROM trays WHERE name='DG152'),
    'GM6'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'MM08',
    4,
    45,
    180,
    (SELECT id FROM cartons WHERE name='PC-A-164'),
    (SELECT id FROM trays WHERE name='DG144'),
    'HG5'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'RB02',
    3,
    30,
    90,
    (SELECT id FROM cartons WHERE name='PC-A-32'),
    (SELECT id FROM trays WHERE name='DJ172'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'RB04',
    3,
    24,
    72,
    (SELECT id FROM cartons WHERE name='PC-A-40'),
    (SELECT id FROM trays WHERE name='DJ172'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'RB05',
    6,
    20,
    120,
    (SELECT id FROM cartons WHERE name='PC-A-2'),
    (SELECT id FROM trays WHERE name='144B'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'RB06',
    5,
    30,
    150,
    (SELECT id FROM cartons WHERE name='PC-A-11'),
    (SELECT id FROM trays WHERE name='DE165'),
    'J7'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'RN01',
    4,
    24,
    96,
    (SELECT id FROM cartons WHERE name='PC-A-80'),
    (SELECT id FROM trays WHERE name='DE172A-A/B'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'RN02',
    NULL,
    NULL,
    90,
    (SELECT id FROM cartons WHERE name='EBL3'),
    (SELECT id FROM trays WHERE name='EB172'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'RN03',
    6,
    16,
    96,
    (SELECT id FROM cartons WHERE name='PC-A-106'),
    (SELECT id FROM trays WHERE name='144B'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'SM10',
    6,
    24,
    144,
    (SELECT id FROM cartons WHERE name='PC-A-149'),
    (SELECT id FROM trays WHERE name='DG152'),
    'HG5'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'VL08',
    5,
    20,
    100,
    (SELECT id FROM cartons WHERE name='PC-A-12'),
    (SELECT id FROM trays WHERE name='DE163B'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'WG22',
    5,
    20,
    100,
    (SELECT id FROM cartons WHERE name='PC-A-11'),
    (SELECT id FROM trays WHERE name='DE165'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'WG24',
    6,
    16,
    96,
    (SELECT id FROM cartons WHERE name='PC-A-90'),
    (SELECT id FROM trays WHERE name='DE152'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'WG25',
    5,
    16,
    80,
    (SELECT id FROM cartons WHERE name='PC-A-58'),
    (SELECT id FROM trays WHERE name='DE165'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'WG27',
    3,
    16,
    48,
    (SELECT id FROM cartons WHERE name='PC-A-58'),
    (SELECT id FROM trays WHERE name='DE178'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'WG28',
    6,
    16,
    96,
    (SELECT id FROM cartons WHERE name='PC-A-90'),
    (SELECT id FROM trays WHERE name='DE152'),
    'EURO'
);

INSERT INTO products (
    name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet
) VALUES (
    'WG29',
    5,
    16,
    80,
    (SELECT id FROM cartons WHERE name='PC-A-58'),
    (SELECT id FROM trays WHERE name='DE165'),
    'EURO'
);
