package com.vida.usuario.controller.grpc;

import com.vida.usuario.dto.UserCreateDTO;
import com.vida.usuario.exception.UserException;
import com.vida.usuario.service.UserService;
import net.devh.boot.grpc.server.service.GrpcService;
import io.grpc.stub.StreamObserver;
import com.vida.usuario.grpc.UserServiceGrpc;
import com.vida.usuario.grpc.UserCreateRequest;
import com.vida.usuario.grpc.UserResponse;

@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private final UserService userService;

    public UserGrpcService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void registrar(UserCreateRequest request,
                          StreamObserver<UserResponse> responseObserver) {

        UserCreateDTO dto = new UserCreateDTO(
                request.getNombres(),
                request.getApellidos(),
                request.getEmail(),
                request.getPassword(),
                request.getTelefono()
        );

        userService.registrar(dto)
                .subscribe(
                        userResponseDTO -> {
                            UserResponse response = UserResponse.newBuilder()
                                    .setId(userResponseDTO.id())
                                    .setNombres(userResponseDTO.nombres())
                                    .setApellidos(userResponseDTO.apellidos())
                                    .setEmail(userResponseDTO.email())
                                    .setEstado(userResponseDTO.estado())
                                    .setTelefono(userResponseDTO.telefono())
                                    .build();
                            responseObserver.onNext(response);
                            responseObserver.onCompleted();
                        },
                        error -> {
                            io.grpc.Status grpcStatus = io.grpc.Status.INTERNAL;
                            if (error instanceof UserException userEx) {
                                grpcStatus = userEx.getStatus();
                            }
                            responseObserver.onError(
                                    grpcStatus
                                            .withDescription(error.getMessage())
                                            .asRuntimeException());
                        }
                );
    }
}
