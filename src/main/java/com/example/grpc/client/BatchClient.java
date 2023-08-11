package com.example.grpc.client;

import com.example.grpc.BatchResponse;
import com.example.grpc.BatchServiceGrpc;
import com.example.grpc.CreateBatchRequest;
import io.grpc.ManagedChannelBuilder;

public class BatchClient {
    public BatchResponse create() {
        final var channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();
        final var request = CreateBatchRequest.newBuilder()
                .setBatchId("batchId")
                .setSku("sku")
                .setSupplierId("supplierId")
                .build();
        return BatchServiceGrpc.newBlockingStub(channel).create(request);
    }
}
