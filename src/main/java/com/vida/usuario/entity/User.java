package com.vida.usuario.entity;

import com.vida.usuario.entity.enums.EstadoUsuario;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@Table(name = "users",schema = "users")
public class User implements Serializable {
    private static final long serialVersionUID=1L;

    public static final int NOMBRE_MAXI=90;
    public static final int EMAIL_MAX=120;
    public static final int TEL_MAX=20;

    @Id
    private Long id;

    @NotBlank
    @Size(max = EMAIL_MAX)
    @Column("email")
    private  String email;

    @NotBlank
    @Size(max = NOMBRE_MAXI)
    @Column("nombres")
    private  String nombres;

    @NotBlank
    @Size(max = NOMBRE_MAXI)
    @Column("apellidos")
    private  String apellidos;

    @NotBlank
    @Column("password")
    private  String password;

    @NotBlank
    @Column("estado")
    private String estado;

    @Column("fecha_creacion")
    private LocalDateTime fechaCreacion;

    @NotBlank
    @Size(max=TEL_MAX)
    @Column("telefono")
    private String telefono;

    public User(){

    }

    public User(String email, String nombres, String apellidos, String password, String estado, LocalDateTime fechaCreacion, String telefono) {
        this.email = email;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.password = password;
        this.estado = EstadoUsuario.PENDIENTE.getDbCodigo();
        this.fechaCreacion = LocalDateTime.now();
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() !=o.getClass()) return false;
        User users=(User) o;
        return Objects.equals(id, users.id)||Objects.equals(email, users.email);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, email);
    }

    @Override
    public String toString(){
        return "Usuario[id=" + id + ", email=" + email + ", estado=" + estado + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
