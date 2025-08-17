INSERT INTO product_side (product_id, side_id, is_lotted)
SELECT
    (SELECT id FROM products WHERE name = p_name),
    (SELECT id FROM sides WHERE name = 'JW'),
    false
FROM (
         VALUES
             ('CH01'), ('CH03'), ('CH08'), ('CH09'), ('CH11'),
             ('CH19'), ('CH22'), ('CH24'), ('CH30'), ('CH31'),
             ('CH32'), ('CH33'), ('CH34'), ('CH35'), ('GM05'),
             ('JM01'), ('JM02'), ('JM03'), ('JM04'), ('KB13'),
             ('KB22'), ('KB23'), ('KB24'), ('KB28'), ('MM05'),
             ('RB02'), ('RB04'), ('RB05'), ('RB06')
     ) AS p(p_name);

INSERT INTO product_side (product_id, side_id, is_lotted)
SELECT
    (SELECT id FROM products WHERE name = p_name),
    (SELECT id FROM sides WHERE name = 'GW'),
    false
FROM (
         VALUES
             ('FT17'), ('FT20'),
             ('HY08'), ('HY10'), ('HY13'), ('HY15'), ('HY16'),
             ('ID04'), ('ID05'), ('KG01'),
             ('MA05'), ('MA06'), ('MA07'), ('MA09'),
             ('MM07'), ('MM08'), ('SM10')
     ) AS p(p_name);

INSERT INTO product_side (product_id, side_id, is_lotted)
SELECT
    (SELECT id FROM products WHERE name = p_name),
    (SELECT id FROM sides WHERE name = s_name),
    is_lotted
FROM (
         VALUES

--              normal products
             ('BA25', 'BE920A', false),
             ('BA25', 'BE920B', false),
             ('CM06', 'BE760A', false),
             ('CM06', 'BE760B', false),
             ('CM07', 'BJ900A', false),
             ('CM07', 'BE900B', false),
             ('CM08', 'BE770A', false),
             ('CM08', 'BE770B', false),
             ('DA11', 'BE840A', false),
             ('DA11', 'BE840B', false),
             ('RN02', 'BE880A', false),
             ('RN02', 'BE880B', false),

--              lotted products
             ('CH11', 'BE860A', true),
             ('CH11', 'BJ890B', true),
             ('CH19', 'BE770A', true),
             ('CH19', 'BJ890B', true),
             ('CH22', 'BE870A', true),
             ('CH22', 'BJ890B', true),
             ('CH30', 'BE840A', true),
             ('CH30', 'BJ890B', true),
             ('CH33', 'BE860A', true),
             ('CH33', 'BJ890B', true),
             ('CH34', 'BE870A', true),
             ('CH34', 'BJ890B', true),
             ('CH35', 'BE840A', true),
             ('CH35', 'BJ890B', true),
             ('FT17', 'BG860A', true),
             ('FT17', 'BG860B-S', true),
             ('GM05', 'BG860A', true),
             ('GM05', 'BG860B-S', true),
             ('HY08', 'BG750B', true),
             ('HY08', 'BG750A', true),
             ('HY10', 'BG860B-S', true),
             ('HY10', 'BG860A', true),
             ('HY13', 'BG750B', true),
             ('HY13', 'BG750A', true),
             ('HY14', 'BG690B', true),
             ('HY14', 'BG690A', true),
             ('HY15', 'BG860B-S', true),
             ('HY15', 'BG860A', true),
             ('HY16', 'BG750B', true),
             ('HY16', 'BG750A', true),
             ('ID04', 'BG860B-S', true),
             ('ID04', 'BG860A', true),
             ('ID05', 'BG860B-S', true),
             ('ID05', 'BG860A', true),
             ('JM01', 'BE770A', true),
             ('JM01', 'BJ890B', true),
             ('JM02', 'BE810A', true),
             ('JM02', 'BJ890B', true),
             ('JM03', 'BE870A', true),
             ('JM03', 'BJ890B', true),
             ('JM04', 'BE840A', true),
             ('JM04', 'BJ890B', true),
             ('KB13', 'BE860A', true),
             ('KB13', 'BJ890B', true),
             ('KB22', 'BE760A', true),
             ('KB22', 'BJ890B', true),
             ('KB23', 'BE760A', true),
             ('KB23', 'BJ890B', true),
             ('KB28', 'BE840A', true),
             ('KB28', 'BJ890B', true),
             ('MM05', 'BE860A', true),
             ('MM05', 'BJ890B', true),
             ('RB02', 'BE840A', true),
             ('RB02', 'BJ890B', true),
             ('RB04', 'BE770A', true),
             ('RB04', 'BJ890B', true),
             ('RB05', 'BE770A', true),
             ('RB05', 'BJ890B', true),
             ('RB06', 'BE930A', true),
             ('RB06', 'BJ930B', true)
     ) AS p(p_name, s_name, is_lotted);