INSERT INTO evento (id, nombre_evento, dia_inicio, dia_fin, hora_inicio, hora_fin, tipo_evento, rango_edad, requerimiento_salud, costo_total)
VALUES 
    (1, 'Torneo de Fútbol', '2025-04-15', '2025-04-16', '08:00:00', '17:00:00', 'Deportivo', '15-25', FALSE, 50.00),
    (2, 'Maratón San Salvador', '2025-05-10', '2025-05-10', '06:00:00', '12:00:00', 'Atletismo', '18-45', FALSE, 25.00),
    (3, 'Natación Junior', '2025-03-20', '2025-03-20', '09:00:00', '13:00:00', 'Natación', '10-15', TRUE, 15.00),
    (4, 'Torneo de Baloncesto', '2025-06-05', '2025-06-07', '10:00:00', '18:00:00', 'Deportivo', '16-30', FALSE, 75.00),
    (5, 'Competencia de Ciclismo', '2025-07-12', '2025-07-12', '07:00:00', '15:00:00', 'Ciclismo', '18-50', FALSE, 40.00),
    (6, 'Yoga en el Parque', '2025-03-25', '2025-03-25', '08:00:00', '09:30:00', 'Bienestar', '16-60', FALSE, 0.00),
    (7, 'Campeonato de Ajedrez', '2025-05-20', '2025-05-22', '09:00:00', '18:00:00', 'Estrategia', '12-80', FALSE, 30.00);

-- Inserciones de datos de prueba para la tabla Inscripcion
INSERT INTO inscripcion (id, nombre_deportista, documento_identificacion, telefono, edad_deportista, condicion_medica, evento_id)
VALUES 
    (1, 'Juan Pérez', '12345678-9', '+50312345678', 25, FALSE, 1),
    (2, 'María Rodríguez', '98765432-1', '+50398765432', 19, FALSE, 1),
    (3, 'Carlos Martínez', '11223344-5', '+50311223344', 32, FALSE, 2),
    (4, 'Ana López', '55667788-9', '+50355667788', 14, FALSE, 3),
    (5, 'Pedro Hernández', '12312312-3', '+50312312312', 22, FALSE, 4),
    (6, 'Laura González', '45645645-6', '+50345645645', 29, FALSE, 5),
    (7, 'Roberto Sánchez', '78978978-9', '+50378978978', 45, FALSE, 7),
    (8, 'Carmen Jiménez', '36936936-9', '+50336936936', 35, FALSE, 2),
    (9, 'José Morales', '25825825-8', '+50325825825', 18, FALSE, 6),
    (10, 'Francisco Castro', '14714714-7', '+50314714714', 15, FALSE, 7);