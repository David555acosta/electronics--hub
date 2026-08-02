INSERT INTO productos(codigo, nombre, descripcion, precio, imagen)
VALUES (1, 'pc facha', 'facherisima', 100.00, 'facha.png');


INSERT INTO rol (id, rol)
VALUES (1, 'ADMIN');

INSERT INTO usuario (id, username, email, password)
VALUES (1, 'david_admin', 'david@example.com', '$2a$10$e8R6.V2I5dO4iX/uC3G2.Oq4h9fW9xK4kY2M5Z1u0T8y3X5a7L9mS');

INSERT INTO user_roles(usuario_id, rol_id)
VALUES (1, 1);



/*------------------------------------------------------------------*/

INSERT INTO rol (id, rol)
VALUES (2, 'USER');

INSERT INTO usuario (id, username, email, password)
VALUES (2, 'jose_user', 'jose@example.com', '$2a$10$wT8vM9Kq4/pZ6s2B0/Q0u.K8M4h9G4E1R6z8J1x0T8y3X5a7L9mS2');


INSERT INTO user_roles(usuario_id, rol_id)
VALUES (2, 2);











