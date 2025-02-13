DELETE FROM prestamos;
DELETE FROM clientes;

INSERT INTO clientes (nombre, email, telefono, direccion) VALUES
('Goku', 'goku@dbz.com', '12345678', 'Kame House'),
('Vegeta', 'vegeta@dbz.com', '12345678', 'Planeta Vegeta'),
('Gohan', 'gohan@dbz.com', '12345678', 'Planeta Tierra'),
('Piccolo', 'pico@dbz.com', '12345678', 'Planeta Namekusei'),
('Trunks', 'trunko@dbz.com', '12345678', 'Planeta Tierra'),
('Goten', 'goten@dbz.com', '12345678', 'Planeta Tierra'),
('Krilin', 'krillin@dbz.com', '12345678', 'Planeta Tierra'),
('Yamcha', 'yamcha@dbz.com', '12345678', 'Planeta Tierra'),
('Tenshinhan', 'tenchi@dbz.com', '12345678', 'Planeta Tierra'),
('Chaoz', 'chaoz@dbz.com', '12345678', 'Planeta Tierra');

-- DO $$
-- BEGIN
--     FOR i IN 1..50 LOOP
--         INSERT INTO prestamos (monto, interes, duracion_meses, estado, cliente_id)
--         VALUES (random() * 10000, random() * 10, random() * 12, 'Pendiente', (SELECT id FROM clientes ORDER BY random() LIMIT 1));
--     END LOOP;
-- END $$;

INSERT INTO prestamos (monto, interes, duracion_meses, estado, cliente_id)
SELECT
        random() * 10000,
        random() * 10,
        (random() * 12)::int,
        'Pendiente',
        id
FROM clientes CROSS JOIN generate_series(1, 5);

-- DO $$
-- BEGIN
--     FOR i IN 1..15 LOOP
--         INSERT INTO prestamos (monto, interes, duracion_meses, estado, cliente_id)
--         VALUES (random() * 10000, random() * 10, random() * 12, 'Aprobado', (SELECT id FROM clientes ORDER BY random() LIMIT 1));
--     END LOOP;
-- END $$;

INSERT INTO prestamos (monto, interes, duracion_meses, estado, cliente_id)
SELECT
        random() * 10000,
        random() * 10,
        (random() * 12)::int,
        'Aprobado',
        id
FROM clientes CROSS JOIN generate_series(1, 2);  -- 2 préstamos por cliente = ~20 préstamos

-- DO $$
-- BEGIN
--     FOR i IN 1..25 LOOP
--         INSERT INTO prestamos (monto, interes, duracion_meses, estado, cliente_id)
--         VALUES (random() * 10000, random() * 10, random() * 12, 'Rechazado', (SELECT id FROM clientes ORDER BY random() LIMIT 1));
--     END LOOP;
-- END $$;

INSERT INTO prestamos (monto, interes, duracion_meses, estado, cliente_id)
SELECT
        random() * 10000,
        random() * 10,
        (random() * 12)::int,
        'Rechazado',
        id
FROM clientes CROSS JOIN generate_series(1, 3);  -- 3 préstamos por cliente = ~30 préstamos