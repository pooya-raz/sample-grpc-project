package com.example.grpc.service;

import com.example.grpc.BatchServiceGrpc;
import com.example.grpc.CreateBatchRequest;
import com.example.grpc.CreateBatchResponse;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class BatchService extends BatchServiceGrpc.BatchServiceImplBase {
    @Override
    public void create(CreateBatchRequest request, StreamObserver<CreateBatchResponse> responseObserver) {
        validate(request, responseObserver);
        final var response = CreateBatchResponse.newBuilder()
                .setSku(request.getSku())
                .setBatchId(request.getBatchId())
                .setSupplierId(request.getSupplierId())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private void validate(CreateBatchRequest request, StreamObserver<CreateBatchResponse> responseObserver) {
        final var error = io.grpc.Status.INVALID_ARGUMENT
                .withDescription("batchId, sku, and supplierId are required")
                .asException();
        if (request.getBatchId().isEmpty()
                || request.getSku().isEmpty()
                || request.getSupplierId().isEmpty()) {
            responseObserver.onError(error);
        }
    }
}
