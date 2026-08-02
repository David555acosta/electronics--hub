INSERT INTO productos(codigo, nombre, descripcion, precio, imagen)
VALUES (1, 'pc facha', 'facherisima', 100.00, 'facha.png');

-- 2. Insertar Rol
INSERT INTO rol (id, rol)
VALUES (1, 'USER');

-- 3. Insertar Usuario


-- 4. Relacionar Usuario y Rol (Se ejecuta al final cuando ambos ID ya existen)
INSERT INTO user_roles(usuario_id, rol_id)
VALUES (1, 1);





