-- Datos iniciales para MS-Camiones

-- Tipos de camión
INSERT INTO tipos_camion (id, nombre, capacidad_peso_max_kg, capacidad_volumen_max_m3) VALUES
  (1, 'Chasis Corto', 8000, 30),
  (2, 'Chasis Largo', 12000, 45);

-- Depósitos
INSERT INTO depositos (id, nombre, direccion, latitud, longitud, costo_estadia_diario) VALUES
  (1, 'Depósito Central', 'Av. Siempreviva 742', -31.4201, -64.1888, 2500),
  (2, 'Depósito Norte', 'Ruta 9 km 123', -30.9876, -64.3210, 2200);

-- Camiones
INSERT INTO camiones (id, patente, estado, consumo_combustible_por_km, costo_por_km, transportista_id, tipo_camion_id) VALUES
  (1, 'ABC123', 'DISPONIBLE', 5.5, 120.0, 10, 1),
  (2, 'XYZ789', 'EN_RUTA', 6.2, 140.0, 11, 2);
