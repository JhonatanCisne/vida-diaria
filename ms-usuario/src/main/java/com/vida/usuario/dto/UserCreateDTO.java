package com.vida.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record UserCreateDTO (
       @NotBlank(message = "El nombre no puede estar vacío")
       @Size(min = 2, max = 90, message = "El nombre debe tener entre 2 y 90 caracteres")
       @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo debe contener letras")
       String nombres,

       @NotBlank(message = "El apellido no puede estar vacío")
       @Size(min = 2, max = 90, message = "El apellido debe tener entre 2 y 90 caracteres")
       @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El apellido solo debe contener letras")
       String apellidos,

       @NotBlank(message = "El email es obligatorio")
       @Email(message = "Debe proporcionar un formato de email válido")
       @Size(max = 120)
       String email,

       @NotBlank(message = "La contraseña es obligatoria")
       @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
       @Pattern(
               regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
               message = "La contraseña debe contener al menos una mayúscula, una minúscula, un número y un carácter especial"
       )
       String password,

       @NotBlank(message = "El telefono es obligatio")
       @Size(min =7, max = 20, message = "El telefono debe tener entre 7 y 20 caracteres")
       @Pattern(regexp = "^\\+?[0-9 ]+$", message = "El formato del teléfono es inválido")
       String telefono
) implements Serializable {
    public UserCreateDTO{
        if (email != null) email = email.toLowerCase().trim();
        if (nombres !=null) nombres = nombres.trim();
        if (apellidos!=null) apellidos = apellidos.trim();
    }
}
