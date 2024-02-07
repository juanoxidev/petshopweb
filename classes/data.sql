-- Insertar datos de ejemplo en la tabla de usuarios 
INSERT INTO usuario (nombre, contrasenia) VALUES ('juanoxi', '1234');

-- c/ credenciales encriptadas https://bcrypt-generator.com/
INSERT INTO usuario (nombre, contrasenia) VALUES ('user', '$2y$10$hy70lbR/1853wNlsMV8bN./EPBZXc2McXXpfcZWchVnhc2YYDFKzy');
-- Insertar datos de ejemplo en la tabla de duenios
INSERT INTO duenio (nombre, direccion) VALUES ('Juan', 'Lavalleja 541');
INSERT INTO duenio (nombre, direccion) VALUES ('Maxi', 'Calle 123');
INSERT INTO duenio (nombre, direccion) VALUES ('Natalia', 'Avenida 235');
INSERT INTO duenio (nombre, direccion) VALUES ('Antonella', 'Segui 287');

-- Insertar datos de ejemplo en la tabla de mascotas
INSERT INTO mascota (nombre, especie, edad, duenio_id) VALUES ('Chano','Aspiradora Robot',2,2);
INSERT INTO mascota (nombre, especie, edad, duenio_id) VALUES ('Momo','Gato',2,1);
INSERT INTO mascota (nombre, especie, edad, duenio_id) VALUES ('Pepe','Perro',3,3);
INSERT INTO mascota (nombre, especie, edad, duenio_id) VALUES ('Bondiola','Perro',5,4);
INSERT INTO mascota (nombre, especie, edad, duenio_id) VALUES ('aaa','Perro',3,1);
INSERT INTO mascota (nombre, especie, edad, duenio_id) VALUES ('AAA','Perro',3,2);