-- BA24
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('BA24',
                      6,
                      20,
                      120,
                      (SELECT id FROM carton WHERE name='PC-A-19'),
                      (SELECT id FROM tray WHERE name='DE163'),
                      'EURO',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='BA24'), (SELECT id FROM divider WHERE name='EA'));

-- BA25
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('BA25',
                      NULL,
                      NULL,
                      105,
                      (SELECT id FROM carton WHERE name='ERBL205190'),
                      (SELECT id FROM tray WHERE name='BE205190'),
                      'EURO',
                      'BE920B)'
                     );

-- BA26
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('BA26',
                      4,
                      24,
                      96,
                      (SELECT id FROM carton WHERE name='PC-A-8'),
                      (SELECT id FROM tray WHERE name='DE235162'),
                      'EURO',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='BA26'), (SELECT id FROM divider WHERE name='EA'));

-- BA27
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('BA27',
                      4,
                      20,
                      80,
                      (SELECT id FROM carton WHERE name='PC-A-38'),
                      (SELECT id FROM tray WHERE name='DE206153'),
                      'EURO',
                      NULL
                     );

-- BA28
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('BA28',
                      6,
                      20,
                      120,
                      (SELECT id FROM carton WHERE name='PC-A-81'),
                      (SELECT id FROM tray WHERE name='DE163140'),
                      'EURO',
                      NULL
                     );

-- CH01
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CH01',
                      3,
                      24,
                      72,
                      (SELECT id FROM carton WHERE name='PC-A-118'),
                      (SELECT id FROM tray WHERE name='DJ172'),
                      'J7',
                      NULL
                     );

-- CH03
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CH03',
                      5,
                      30,
                      150,
                      (SELECT id FROM carton WHERE name='PC-A-11'),
                      (SELECT id FROM tray WHERE name='DE165'),
                      'J7',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='CH03'), (SELECT id FROM divider WHERE name='EA'));

-- CH08
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CH08',
                      5,
                      24,
                      120,
                      (SELECT id FROM carton WHERE name='PC-A-118'),
                      (SELECT id FROM tray WHERE name='DJ144'),
                      'J7',
                      NULL
                     );

-- CH09
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CH09',
                      3,
                      30,
                      90,
                      (SELECT id FROM carton WHERE name='PC-A-32'),
                      (SELECT id FROM tray WHERE name='DJ172'),
                      'J7',
                      NULL
                     );

-- CH11
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CH11',
                      3,
                      30,
                      90,
                      (SELECT id FROM carton WHERE name='PC-A-8'),
                      (SELECT id FROM tray WHERE name='DE191'),
                      'J7',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='CH11'), (SELECT id FROM divider WHERE name='EA'));

-- CH19
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CH19',
                      6,
                      20,
                      120,
                      (SELECT id FROM carton WHERE name='PC-A-2'),
                      (SELECT id FROM tray WHERE name='144B'),
                      'J7',
                      NULL
                     );

-- CH22
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CH22',
                      3,
                      24,
                      72,
                      (SELECT id FROM carton WHERE name='PC-A-118'),
                      (SELECT id FROM tray WHERE name='DJ172'),
                      'J7',
                      NULL
                     );

-- CH24
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CH24',
                      3,
                      24,
                      72,
                      (SELECT id FROM carton WHERE name='PC-A-40'),
                      (SELECT id FROM tray WHERE name='DJ172'),
                      'J7',
                      NULL
                     );

-- CH30
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CH30',
                      3,
                      25,
                      75,
                      (SELECT id FROM carton WHERE name='PC-A-95'),
                      (SELECT id FROM tray WHERE name='DE191'),
                      'J7',
                      NULL
                     );

-- CH31
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CH31',
                      5,
                      25,
                      125,
                      (SELECT id FROM carton WHERE name='PC-A-95'),
                      (SELECT id FROM tray WHERE name='DE165'),
                      'J7',
                      NULL
                     );

-- CH32
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CH32',
                      5,
                      20,
                      100,
                      (SELECT id FROM carton WHERE name='PC-A-97'),
                      (SELECT id FROM tray WHERE name='DE165'),
                      'J7',
                      NULL
                     );

-- CH33
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CH33',
                      3,
                      30,
                      90,
                      (SELECT id FROM carton WHERE name='PC-A-8'),
                      (SELECT id FROM tray WHERE name='DE191'),
                      'J7',
                      NULL
                     );

-- CH34
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CH34',
                      3,
                      24,
                      72,
                      (SELECT id FROM carton WHERE name='PC-A-118'),
                      (SELECT id FROM tray WHERE name='DJ165'),
                      'J7',
                      NULL
                     );

-- CH35
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CH35',
                      6,
                      25,
                      150,
                      (SELECT id FROM carton WHERE name='PC-A-95'),
                      (SELECT id FROM tray WHERE name='144B'),
                      'J7',
                      NULL
                     );

-- CM06
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CM06',
                      NULL,
                      NULL,
                      36,
                      (SELECT id FROM carton WHERE name='ERBL267A'),
                      (SELECT id FROM tray WHERE name='BE267A'),
                      'EURO',
                      'BE760B)'
                     );

-- CM07
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CM07',
                      NULL,
                      NULL,
                      54,
                      (SELECT id FROM carton WHERE name='ERBL267A'),
                      (SELECT id FROM tray WHERE name='BE267A'),
                      'EURO',
                      'BE900B)'
                     );

-- CM08
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('CM08',
                      NULL,
                      NULL,
                      52,
                      (SELECT id FROM carton WHERE name='ERBL229A'),
                      (SELECT id FROM tray WHERE name='BE229A'),
                      'EURO',
                      'BE770B)'
                     );

-- DA10
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('DA10',
                      5,
                      24,
                      120,
                      (SELECT id FROM carton WHERE name='PC-A-11'),
                      (SELECT id FROM tray WHERE name='DE165'),
                      'EURO',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='DA10'), (SELECT id FROM divider WHERE name='EA'));

-- DA11
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('DA11',
                      NULL,
                      NULL,
                      75,
                      (SELECT id FROM carton WHERE name='EBL2'),
                      (SELECT id FROM tray WHERE name='BE178'),
                      'EURO',
                      'BE840B)'
                     );

-- FD11
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('FD11',
                      5,
                      28,
                      140,
                      (SELECT id FROM carton WHERE name='PC-A-41'),
                      (SELECT id FROM tray WHERE name='DE178134'),
                      'EURO',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='FD11'), (SELECT id FROM divider WHERE name='EA'));

-- FD12
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('FD12',
                      5,
                      20,
                      100,
                      (SELECT id FROM carton WHERE name='PC-A-58'),
                      (SELECT id FROM tray WHERE name='DE178134'),
                      'EURO',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='FD12'), (SELECT id FROM divider WHERE name='EA'));

-- FD14
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('FD14',
                      6,
                      20,
                      120,
                      (SELECT id FROM carton WHERE name='PC-A-19'),
                      (SELECT id FROM tray WHERE name='144B'),
                      'EURO',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='FD14'), (SELECT id FROM divider WHERE name='EA'));

-- FT13
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('FT13',
                      5,
                      16,
                      80,
                      (SELECT id FROM carton WHERE name='PC-A-106'),
                      (SELECT id FROM tray WHERE name='DE180113A'),
                      'EURO',
                      NULL
                     );

-- FT14
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('FT14',
                      6,
                      20,
                      120,
                      (SELECT id FROM carton WHERE name='PC-A-131'),
                      (SELECT id FROM tray WHERE name='DE156130'),
                      'EURO',
                      NULL
                     );

-- FT15
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('FT15',
                      3,
                      20,
                      60,
                      (SELECT id FROM carton WHERE name='PC-A-19'),
                      (SELECT id FROM tray WHERE name='DE187A'),
                      'EURO',
                      NULL
                     );

-- FT16
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('FT16',
                      5,
                      16,
                      80,
                      (SELECT id FROM carton WHERE name='PC-A-106'),
                      (SELECT id FROM tray WHERE name='DE180113A'),
                      'EURO',
                      NULL
                     );

-- FT17
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('FT17',
                      5,
                      24,
                      120,
                      (SELECT id FROM carton WHERE name='PC-A-165'),
                      (SELECT id FROM tray WHERE name='DG180113'),
                      'GM6',
                      NULL
                     );

-- FT19
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('FT19',
                      4,
                      20,
                      80,
                      (SELECT id FROM carton WHERE name='PC-A-12'),
                      (SELECT id FROM tray WHERE name='DE185135'),
                      'EURO',
                      NULL
                     );

-- FT20
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('FT20',
                      4,
                      30,
                      120,
                      (SELECT id FROM carton WHERE name='PC-A-170'),
                      (SELECT id FROM tray WHERE name='DG206139'),
                      'GM6',
                      NULL
                     );

-- FT21
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('FT21',
                      4,
                      16,
                      64,
                      (SELECT id FROM carton WHERE name='PC-A-97'),
                      (SELECT id FROM tray WHERE name='DE185135'),
                      'EURO',
                      NULL
                     );

-- GM05
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('GM05',
                      3,
                      25,
                      75,
                      (SELECT id FROM carton WHERE name='PC-A-95'),
                      (SELECT id FROM tray WHERE name='DE184'),
                      'J7',
                      NULL
                     );

-- HY08
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('HY08',
                      6,
                      24,
                      144,
                      (SELECT id FROM carton WHERE name='PC-A-86'),
                      (SELECT id FROM tray WHERE name='DG152'),
                      'HG5',
                      NULL
                     );

-- HY10
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('HY10',
                      6,
                      24,
                      144,
                      (SELECT id FROM carton WHERE name='PC-A-103'),
                      (SELECT id FROM tray WHERE name='DG152'),
                      'GM6',
                      NULL
                     );

-- HY12
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('HY12',
                      6,
                      20,
                      120,
                      (SELECT id FROM carton WHERE name='PC-A-145'),
                      (SELECT id FROM tray WHERE name='DE152'),
                      'EURO',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='HY12'), (SELECT id FROM divider WHERE name='EA'));

-- HY13
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('HY13',
                      6,
                      24,
                      144,
                      (SELECT id FROM carton WHERE name='PC-A-86'),
                      (SELECT id FROM tray WHERE name='DG152'),
                      'GM6',
                      NULL
                     );

-- HY14
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('HY14',
                      3,
                      18,
                      54,
                      (SELECT id FROM carton WHERE name='PC-A-102'),
                      (SELECT id FROM tray WHERE name='DG178'),
                      'HG5',
                      NULL
                     );

-- HY15
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('HY15',
                      3,
                      24,
                      72,
                      (SELECT id FROM carton WHERE name='PC-A-155'),
                      (SELECT id FROM tray WHERE name='DG178'),
                      'GM6',
                      NULL
                     );

-- HY16
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('HY16',
                      3,
                      24,
                      72,
                      (SELECT id FROM carton WHERE name='PC-A-154'),
                      (SELECT id FROM tray WHERE name='DG178'),
                      'HG5',
                      NULL
                     );

-- ID04
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('ID04',
                      4,
                      24,
                      96,
                      (SELECT id FROM carton WHERE name='PC-A-138'),
                      (SELECT id FROM tray WHERE name='DG172A'),
                      'GM6',
                      NULL
                     );

-- ID05
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('ID05',
                      6,
                      24,
                      144,
                      (SELECT id FROM carton WHERE name='PC-A-165'),
                      (SELECT id FROM tray WHERE name='DG144B'),
                      'GM6',
                      NULL
                     );

-- JM01
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('JM01',
                      6,
                      20,
                      120,
                      (SELECT id FROM carton WHERE name='PC-A-2'),
                      (SELECT id FROM tray WHERE name='144B'),
                      'J7',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='JM01'), (SELECT id FROM divider WHERE name='EA'));

-- JM02
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('JM02',
                      5,
                      20,
                      100,
                      (SELECT id FROM carton WHERE name='PC-A-97'),
                      (SELECT id FROM tray WHERE name='DE165'),
                      'J7',
                      NULL
                     );

-- JM03
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('JM03',
                      3,
                      24,
                      72,
                      (SELECT id FROM carton WHERE name='PC-A-118'),
                      (SELECT id FROM tray WHERE name='DJ165'),
                      'J7',
                      NULL
                     );

-- JM04
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('JM04',
                      5,
                      25,
                      125,
                      (SELECT id FROM carton WHERE name='PC-A-95'),
                      (SELECT id FROM tray WHERE name='DE165'),
                      'J7',
                      NULL
                     );

-- KB13
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('KB13',
                      6,
                      30,
                      180,
                      (SELECT id FROM carton WHERE name='PC-A-8'),
                      (SELECT id FROM tray WHERE name='144B'),
                      'J7',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='KB13'), (SELECT id FROM divider WHERE name='EA'));

-- KB22
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('KB22',
                      3,
                      24,
                      180,
                      (SELECT id FROM carton WHERE name='PC-A-40'),
                      (SELECT id FROM tray WHERE name='DJ172'),
                      'J7',
                      'CP750'
                     );

-- KB23
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('KB23',
                      6,
                      20,
                      180,
                      (SELECT id FROM carton WHERE name='PC-A-2'),
                      (SELECT id FROM tray WHERE name='144B'),
                      'J7',
                      'CP750'
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='KB23'), (SELECT id FROM divider WHERE name='EA'));

-- KB24
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('KB24',
                      6,
                      25,
                      180,
                      (SELECT id FROM carton WHERE name='PC-A-19'),
                      (SELECT id FROM tray WHERE name='144B'),
                      'J7',
                      'CP850'
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='KB24'), (SELECT id FROM divider WHERE name='EA'));

-- KB28
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('KB28',
                      2,
                      25,
                      50,
                      (SELECT id FROM carton WHERE name='PC-A-19'),
                      (SELECT id FROM tray WHERE name='DE271'),
                      'J7',
                      'CP850'
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='KB28'), (SELECT id FROM divider WHERE name='EA'));

-- KG01
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('KG01',
                      6,
                      24,
                      144,
                      (SELECT id FROM carton WHERE name='PC-A-149'),
                      (SELECT id FROM tray WHERE name='DG152'),
                      'HG5',
                      NULL
                     );

-- LR08
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('LR08',
                      4,
                      28,
                      112,
                      (SELECT id FROM carton WHERE name='PC-A-120'),
                      (SELECT id FROM tray WHERE name='DE200155'),
                      'EURO',
                      NULL
                     );

-- MA02
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('MA02',
                      6,
                      16,
                      96,
                      (SELECT id FROM carton WHERE name='PC-A-14'),
                      (SELECT id FROM tray WHERE name='144B'),
                      'EURO',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='MA02'), (SELECT id FROM divider WHERE name='EA'));

-- MA03
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('MA03',
                      6,
                      20,
                      120,
                      (SELECT id FROM carton WHERE name='PC-A-12'),
                      (SELECT id FROM tray WHERE name='144B'),
                      'EURO',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='MA03'), (SELECT id FROM divider WHERE name='EA'));

-- MA04
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('MA04',
                      5,
                      16,
                      80,
                      (SELECT id FROM carton WHERE name='PC-A-97'),
                      (SELECT id FROM tray WHERE name='DE178114'),
                      'EURO',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='MA04'), (SELECT id FROM divider WHERE name='EA'));

-- MA05
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('MA05',
                      4,
                      36,
                      144,
                      (SELECT id FROM carton WHERE name='PC-A-49'),
                      (SELECT id FROM tray WHERE name='DG144'),
                      'HG5',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='MA05'), (SELECT id FROM divider WHERE name='G2-3'));

-- MA06
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('MA06',
                      4,
                      45,
                      180,
                      (SELECT id FROM carton WHERE name='PC-A-52'),
                      (SELECT id FROM tray WHERE name='DG144'),
                      'HG5',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='MA06'), (SELECT id FROM divider WHERE name='G2-3'));

-- MA07
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('MA07',
                      5,
                      24,
                      120,
                      (SELECT id FROM carton WHERE name='PC-A-165'),
                      (SELECT id FROM tray WHERE name='DG178114'),
                      'GM6',
                      NULL
                     );

-- MA09
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('MA09',
                      4,
                      45,
                      180,
                      (SELECT id FROM carton WHERE name='PC-A-164'),
                      (SELECT id FROM tray WHERE name='DG144'),
                      'HG5',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='MA09'), (SELECT id FROM divider WHERE name='G2-3'));

-- MM05
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('MM05',
                      6,
                      30,
                      180,
                      (SELECT id FROM carton WHERE name='PC-A-8'),
                      (SELECT id FROM tray WHERE name='144B'),
                      'J7',
                      NULL
                     );

-- MM07
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('MM07',
                      6,
                      24,
                      144,
                      (SELECT id FROM carton WHERE name='PC-A-147'),
                      (SELECT id FROM tray WHERE name='DG152'),
                      'GM6',
                      NULL
                     );

-- MM08
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('MM08',
                      4,
                      45,
                      180,
                      (SELECT id FROM carton WHERE name='PC-A-164'),
                      (SELECT id FROM tray WHERE name='DG144'),
                      'HG5',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='MM08'), (SELECT id FROM divider WHERE name='G2-3'));

-- RB02
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('RB02',
                      3,
                      30,
                      90,
                      (SELECT id FROM carton WHERE name='PC-A-32'),
                      (SELECT id FROM tray WHERE name='DJ172'),
                      'HG5',
                      NULL
                     );

-- RB04
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('RB04',
                      3,
                      24,
                      72,
                      (SELECT id FROM carton WHERE name='PC-A-40'),
                      (SELECT id FROM tray WHERE name='DJ172'),
                      'J7',
                      NULL
                     );

-- RB05
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('RB05',
                      6,
                      20,
                      120,
                      (SELECT id FROM carton WHERE name='PC-A-2'),
                      (SELECT id FROM tray WHERE name='144B'),
                      'J7',
                      NULL
                     );

-- RB06
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('RB06',
                      5,
                      30,
                      150,
                      (SELECT id FROM carton WHERE name='PC-A-11'),
                      (SELECT id FROM tray WHERE name='DE165'),
                      'J7',
                      NULL
                     );

-- RN01
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('RN01',
                      4,
                      24,
                      96,
                      (SELECT id FROM carton WHERE name='PC-A-80'),
                      (SELECT id FROM tray WHERE name='DE172A-A/B'),
                      'EURO',
                      NULL
                     );

-- RN02
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('RN02',
                      null null,
                      90,
                      EBL3,
                      (SELECT id FROM carton WHERE name='EB172'),
                      (SELECT id FROM tray WHERE name='EURO'),
                      'null',
                      NULL
                     );

-- RN03
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('RN03',
                      6,
                      16,
                      96,
                      (SELECT id FROM carton WHERE name='PC-A-106'),
                      (SELECT id FROM tray WHERE name='144B'),
                      'EURO',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='RN03'), (SELECT id FROM divider WHERE name='EA'));

-- SM10
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('SM10',
                      6,
                      24,
                      144,
                      (SELECT id FROM carton WHERE name='PC-A-149'),
                      (SELECT id FROM tray WHERE name='DG152'),
                      'HG5',
                      NULL
                     );

-- VL08
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('VL08',
                      5,
                      20,
                      100,
                      (SELECT id FROM carton WHERE name='PC-A-12'),
                      (SELECT id FROM tray WHERE name='DE163B'),
                      'EURO',
                      NULL
                     );

-- WG22
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('WG22',
                      5,
                      20,
                      100,
                      (SELECT id FROM carton WHERE name='PC-A-11'),
                      (SELECT id FROM tray WHERE name='DE165'),
                      'EURO',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='WG22'), (SELECT id FROM divider WHERE name='EA'));

-- WG24
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('WG24',
                      6,
                      16,
                      96,
                      (SELECT id FROM carton WHERE name='PC-A-90'),
                      (SELECT id FROM tray WHERE name='DE152'),
                      'EURO',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='WG24'), (SELECT id FROM divider WHERE name='EA'));

-- WG25
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('WG25',
                      5,
                      16,
                      80,
                      (SELECT id FROM carton WHERE name='PC-A-58 DE165'),
                      (SELECT id FROM tray WHERE name='EURO'),
                      '(EA)',
                      'undefined'
                     );

-- WG27
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('WG27',
                      3,
                      16,
                      48,
                      (SELECT id FROM carton WHERE name='PC-A-58'),
                      (SELECT id FROM tray WHERE name='DE178'),
                      'EURO',
                      NULL
                     );
INSERT INTO divider_product (product_id, dividers_id)
VALUES ((SELECT id FROM product WHERE name='WG27'), (SELECT id FROM divider WHERE name='EA'));

-- WG28
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('WG28',
                      6,
                      16,
                      96,
                      (SELECT id FROM carton WHERE name='PC-A-90'),
                      (SELECT id FROM tray WHERE name='DE152'),
                      'EURO',
                      NULL
                     );

-- WG29
INSERT INTO product
                 (name, filters_per_carton, cartons_per_pallet, filters_per_pallet, carton_id, tray_id, pallet, corner)
                 VALUES
                     ('WG29',
                      5,
                      16,
                      80,
                      (SELECT id FROM carton WHERE name='PC-A-58'),
                      (SELECT id FROM tray WHERE name='DE165'),
                      'EURO',
                      NULL
                     );

