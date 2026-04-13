package com.vida.usuario.reposiotory;

import com.vida.usuario.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<User> findByEmail(String email);

    Mono<Boolean> existsByEmail(String email);

    @Query("SELECT * FROM usuarios.usuarios WHERE telefono = :telefono AND estado = 'A'")
    Mono<User> findActiveByTelefono(String telefono);
}
