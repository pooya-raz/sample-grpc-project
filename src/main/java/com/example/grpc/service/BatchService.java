package com.example.grpc.service;

import com.example.grpc.BatchResponse;
import com.example.grpc.BatchServiceGrpc;
import com.example.grpc.CreateBatchRequest;
import com.example.grpc.ListRequest;
import com.example.grpc.domain.Batch;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.ArrayList;
import java.util.List;

@GRpcService
public class BatchService extends BatchServiceGrpc.BatchServiceImplBase {
    List<Batch> batches = new ArrayList<>();

    @Override
    public void create(CreateBatchRequest request, StreamObserver<BatchResponse> responseObserver) {
        validate(request, responseObserver);
        batches.add(new Batch(request.getBatchId(), request.getSku(), request.getSupplierId()));
        final var response = BatchResponse.newBuilder()
                .setSku(request.getSku())
                .setBatchId(request.getBatchId())
                .setSupplierId(request.getSupplierId())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void list(ListRequest request, StreamObserver<BatchResponse> responseObserver) {
        for (Batch batch : batches) {
            final var response = BatchResponse.newBuilder()
                    .setSku(batch.sku())
                    .setBatchId(batch.batchId())
                    .setSupplierId(batch.supplierId())
                    .build();
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }

    private void validate(CreateBatchRequest request, StreamObserver<BatchResponse> responseObserver) {
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
