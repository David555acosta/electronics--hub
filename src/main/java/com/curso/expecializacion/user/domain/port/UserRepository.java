package com.curso.expecializacion.user.domain.port;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.user.domain.Usuario;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Usuario upsert(Usuario user);

    void deleteById(Integer id);

    Optional<Usuario> findByUserName(String username);

    Usuario update(Usuario usuario);
}
