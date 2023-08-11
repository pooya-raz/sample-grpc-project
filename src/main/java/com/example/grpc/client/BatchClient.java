package com.example.grpc.client;

import com.example.grpc.BatchServiceGrpc;
import com.example.grpc.CreateBatchRequest;
import com.example.grpc.CreateBatchResponse;
import io.grpc.ManagedChannelBuilder;

public class BatchClient {
    public CreateBatchResponse create() {
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
