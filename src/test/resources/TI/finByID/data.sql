INSERT INTO productos(codigo , nombre , descripcion , precio , imagen)
VALUES (1 , 'pc facha', 'facherisima' , 100.00 , 'facha.png');


INSERT INTO rol (id, name)
VALUES (1, 'ROLE_USER');

-- 2. Insertar el Usuario
-- Nota: La contraseña está encriptada en BCrypt (equivale a la palabra 'password123')
INSERT INTO usuario (id, username, email, password)
VALUES (1, 'david_dev', 'david@example.com', '$2a$10$e8R6.V2I5dO4iX/uC3G2.Oq4h9fW9xK4kY2M5Z1u0T8y3X5a7L9mS');
