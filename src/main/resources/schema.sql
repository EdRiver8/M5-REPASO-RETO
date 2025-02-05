CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    direccion VARCHAR(100) NOT NULL
);

CREATE TABLE prestamos (
    id SERIAL PRIMARY KEY,
    monto DECIMAL(10, 2) NOT NULL,
    interes DECIMAL(5, 2) NOT NULL,
    duracion_meses INT NOT NULL,
    estado VARCHAR(20) NOT NULL,
    cliente_id INT REFERENCES cliente(id)
);