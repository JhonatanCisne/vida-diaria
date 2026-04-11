CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,           
    nombre VARCHAR(100) NOT NULL,
    contrasena VARCHAR(255) NOT NULL 
);

INSERT INTO usuarios (nombre, contrasena) 
VALUES 
('jhonatan', 'password123'),
('admin', 'admin2026')
ON CONFLICT DO NOTHING;       