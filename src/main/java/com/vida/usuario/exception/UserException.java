package com.vida.usuario.exception;

import io.grpc.Status;

public class UserException extends RuntimeException{
    private final Status status;

    public UserException(String message, Status status){
        super(message);
        this.status=status;
    }

    public Status getStatus() {
        return status;
    }
}
