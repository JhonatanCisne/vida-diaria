package com.vida.usuario.mapper;

import com.vida.usuario.dto.UserResponseDTO;
import com.vida.usuario.entity.User;
import com.vida.usuario.entity.enums.EstadoUsuario;
import com.vida.usuario.dto.UserCreateDTO;
import java.time.LocalDateTime;

public class UserMapper {

    public static User toEntity(UserCreateDTO dto) {
        if (dto == null) return null;
        User user = new User();

        user.setEmail(dto.email());
        user.setNombres(dto.nombres());
        user.setApellidos(dto.apellidos());
        user.setTelefono(dto.telefono());
        user.setEstado(EstadoUsuario.PENDIENTE.getDbCodigo());
        user.setFechaCreacion(LocalDateTime.now());

        return user;
    }

    public static UserResponseDTO toResponse(User entity) {
        if (entity == null) return null;
        return new UserResponseDTO(
                entity.getId(),
                entity.getNombres(),
                entity.getApellidos(),
                entity.getEmail(),
                entity.getEstado(),
                entity.getTelefono()
        );
    }
}
