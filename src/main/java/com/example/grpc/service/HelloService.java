package com.example.grpc.service;

import com.example.grpc.HelloRequest;
import com.example.grpc.HelloResponse;
import com.example.grpc.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class HelloService extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void hello(HelloRequest helloRequest, StreamObserver<HelloResponse> responseObserver) {
        final var response = HelloResponse.newBuilder()
                .setGreeting("Hello " + helloRequest.getFirstName())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
