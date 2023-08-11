package com.example.grpc.client;

import com.example.grpc.BatchResponse;
import com.example.grpc.BatchServiceGrpc;
import com.example.grpc.CreateBatchRequest;
import com.example.grpc.ListRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class BatchClient {

    public static final ManagedChannel CHANNEL =
            ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();

    public BatchResponse create() {
        final var request = CreateBatchRequest.newBuilder()
                .setBatchId("batchId")
                .setSku("sku")
                .setSupplierId("supplierId")
                .build();
        return BatchServiceGrpc.newBlockingStub(CHANNEL).create(request);
    }

    public Iterator<BatchResponse> list() {
        final var request = ListRequest.newBuilder().build();
        return BatchServiceGrpc.newBlockingStub(CHANNEL).list(request);
    }
}
