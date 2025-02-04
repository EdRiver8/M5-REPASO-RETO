DELETE FROM prestamo;
DELETE FROM cliente;

INSERT INTO cliente (nombre, email, telefono, direccion) VALUES
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

DO $$
BEGIN
    FOR i IN 1..50 LOOP
        INSERT INTO prestamo (monto, interes, duracion_meses, estado, cliente_id)
        VALUES (random() * 10000, random() * 10, random() * 12, 'Pendiente', (SELECT id FROM cliente ORDER BY random() LIMIT 1));
    END LOOP;
END $$;

DO $$
BEGIN
    FOR i IN 1..15 LOOP
        INSERT INTO prestamo (monto, interes, duracion_meses, estado, cliente_id)
        VALUES (random() * 10000, random() * 10, random() * 12, 'Aprobado', (SELECT id FROM cliente ORDER BY random() LIMIT 1));
    END LOOP;
END $$;

DO $$
BEGIN
    FOR i IN 1..25 LOOP
        INSERT INTO prestamo (monto, interes, duracion_meses, estado, cliente_id)
        VALUES (random() * 10000, random() * 10, random() * 12, 'Rechazado', (SELECT id FROM cliente ORDER BY random() LIMIT 1));
    END LOOP;
END $$;