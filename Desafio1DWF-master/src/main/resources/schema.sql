-- Creación de la tabla Evento
CREATE TABLE IF NOT EXISTS evento (
    id BIGINT PRIMARY KEY,
    nombre_evento VARCHAR(100) NOT NULL,
    dia_inicio DATE NOT NULL,
    dia_fin DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    tipo_evento VARCHAR(50) NOT NULL,
    rango_edad VARCHAR(10) NOT NULL,
    requerimiento_salud BOOLEAN NOT NULL,
    costo_total DECIMAL(10,2) NOT NULL
);

-- Creación de la tabla Inscripcion
CREATE TABLE IF NOT EXISTS inscripcion (
    id BIGINT PRIMARY KEY,
    nombre_deportista VARCHAR(100) NOT NULL,
    documento_identificacion VARCHAR(20) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    edad_deportista INTEGER NOT NULL,
    condicion_medica BOOLEAN NOT NULL,
    evento_id BIGINT NOT NULL,
    FOREIGN KEY (evento_id) REFERENCES evento(id)
);