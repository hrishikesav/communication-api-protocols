package com.hrishi.api;

import com.hrishi.api.grpc.server.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.Arrays;

public class GrpcServer {
    public static void main(String[] args) {
        System.out.println("Starting gRPC server!");
        Server server = ServerBuilder.forPort(8080)
                .addService(new HelloServiceImpl()).build();
        try {
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Shutdown gRPC server!");
    }
}
