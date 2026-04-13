package com.vida.usuario.dto;

import java.io.Serializable;

public record UserResponseDTO(
        Long id,
        String nombres,
        String apellidos,
        String email,
        String estado,
        String telefono
) implements Serializable {
}
