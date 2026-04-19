package com.vida.usuario.service;

import com.vida.usuario.dto.UserResponseDTO;
import com.vida.usuario.entity.User;
import com.vida.usuario.dto.UserCreateDTO;
import com.vida.usuario.exception.UserException;
import com.vida.usuario.mapper.UserMapper;
import com.vida.usuario.reposiotory.UserRepository;
import io.grpc.Status;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Mono<UserResponseDTO> registrar(UserCreateDTO dto) {
        return userRepository.existsByEmail(dto.email())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new UserException(
                                "El correo " + dto.email() + " ya se encuentra registrado",
                                Status.ALREADY_EXISTS));
                    }
                    String passwordHashed = passwordEncoder.encode(dto.password());
                    User entity = UserMapper.toEntity(dto);
                    entity.setPassword(passwordHashed);
                    return userRepository.save(entity);
                })
                .map(UserMapper::toResponse)
                .doOnSuccess(res ->
                        System.out.println("Usuario creado exitosamente con ID " + res.id()));
    }
}
