INSERT INTO product_corner (product_id, corner_id, is_lotted)
SELECT
    (SELECT id FROM products WHERE name = p_name),
    (SELECT id FROM corners WHERE name = c_name),
    false AS is_lotted
FROM (
         VALUES
             ('KB22', 'CP750'),
             ('KB23', 'CP750'),
             ('KB24', 'CP850'),
             ('KB28', 'CP850')
     ) AS p(p_name, c_name);

INSERT INTO product_corner (product_id, corner_id, is_lotted)
SELECT
    (SELECT id FROM products WHERE name = p_name),
    (SELECT id FROM corners WHERE name = c_name),
    true AS is_lotted
FROM (
         VALUES
             ('CH11', 'CP950'),
             ('CH19', 'CP850'),
             ('CH22', 'CP950'),
             ('CH30', 'CP900'),
             ('CH33', 'CP950'),
             ('CH34', 'CP950'),
             ('CH35', 'CP900'),
             ('FT17', 'CP900'),
             ('GM05', 'CP900'),
             ('HY08', 'CP800'),
             ('HY10', 'CP850'),
             ('HY13', 'CP850'),
             ('HY14', 'CP750'),
             ('HY15', 'CP900'),
             ('HY16', 'CP800'),
             ('ID04', 'CP950'),
             ('ID05', 'CP900'),
             ('JM01', 'CP850'),
             ('JM02', 'CP900'),
             ('JM03', 'CP950'),
             ('JM04', 'CP900'),
             ('KB13', 'CP950'),
             ('KB22', 'CP850'),
             ('KB23', 'CP850'),
             ('KB28', 'CP950'),
             ('MA05', 'CP900'),
             ('MA06', 'CP950'),
             ('MA07', 'CP900'),
             ('MM05', 'CP950'),
             ('MM07', 'CP900'),
             ('MM08', 'CP900'),
             ('RB02', 'CP900'),
             ('RB04', 'CP850'),
             ('RB05', 'CP850'),
             ('RB06', 'CP1000')
     ) AS p(p_name, c_name);