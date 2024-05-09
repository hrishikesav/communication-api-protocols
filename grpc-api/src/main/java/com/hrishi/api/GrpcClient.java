package com.hrishi.api;

import com.hrishi.api.grpc.HelloRequest;
import com.hrishi.api.grpc.HelloResponse;
import com.hrishi.api.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) {
        System.out.println("Starting gRPC client!");
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(managedChannel);

        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("Hrishi").setLastName("Kesav").build());

        managedChannel.shutdown();

        System.out.println("Shutdown gRPC client!");
    }
}
