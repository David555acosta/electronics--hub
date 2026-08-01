-- 1. Eliminar relaciones y registros dependientes primero
DELETE FROM user_roles;
DELETE FROM productos;

-- 2. Eliminar entidades principales al final
DELETE FROM usuario;
DELETE FROM rol;



