package com.vida.usuario.entity.enums;

public enum EstadoUsuario {
    PENDIENTE(0, "P","PENDIENTE DE ACTIVACION"),
    ACTIVO(1, "A", "USUARIO ACTIVO"),
    INACTIVO(2, "I", "USUARIO DESHABILITADO"),
    BLOQUEADO(3,"B","ACCESO BLOQUEADO");

    private final int grpcId;
    private final String dbCodigo;
    private final String descripcion;

    EstadoUsuario(int grpcId, String dbCodigo, String descripcion){
        this.grpcId=grpcId;
        this.dbCodigo=dbCodigo;
        this.descripcion=descripcion;
    }

    /*Busqueda inversa*/
    public static EstadoUsuario fromGrpcId(int id){
        for (EstadoUsuario estado:EstadoUsuario.values()){
            if (estado.grpcId==id){
                return estado;
            }
        }
        return PENDIENTE;
    }

    public boolean puedeActivarse(){
        return this == PENDIENTE || this == INACTIVO;
    }

    @Override
    public String toString(){
        return name() + "("+descripcion+")";
    }

    public int getGrpcId() {
        return grpcId;
    }

    public String getDbCodigo() {
        return dbCodigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
