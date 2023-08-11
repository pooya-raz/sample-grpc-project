package com.example.grpc.client;

import com.example.grpc.CreateBatchResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BatchClientTest {

    @Test
    void create() {
        final var expected = CreateBatchResponse.newBuilder()
                .setBatchId("batchId")
                .setSku("sku")
                .setSupplierId("supplierId")
                .build();
        final var actual = new BatchClient().create();
        assertThat(actual).isEqualTo(expected);
    }
}