INSERT INTO productos(codigo, nombre, descripcion, precio, imagen)
VALUES (1, 'pc facha', 'facherisima', 100.00, 'facha.png');

-- 2. Insertar Rol
INSERT INTO rol (id, rol)
VALUES (1, 'USER');

-- 3. Insertar Usuario
INSERT INTO usuario (id, username, email, password)
VALUES (1, 'david_dev', 'david@example.com', '$2a$10$e8R6.V2I5dO4iX/uC3G2.Oq4h9fW9xK4kY2M5Z1u0T8y3X5a7L9mS');

-- 4. Relacionar Usuario y Rol (Se ejecuta al final cuando ambos ID ya existen)
INSERT INTO user_roles(usuario_id, rol_id)
VALUES (1, 1);





