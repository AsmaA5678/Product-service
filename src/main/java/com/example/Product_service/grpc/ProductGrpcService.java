package com.example.Product_service.grpc;


import com.example.Product_service.service.ProductService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import com.example.Product_service.proto.ProductRequest;
import com.example.Product_service.proto.ProductResponse;
import com.example.Product_service.proto.ProductServiceGrpc;

@GrpcService
public class ProductGrpcService extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductService productService;

    public ProductGrpcService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void checkStock(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        boolean inStock = productService.checkStock(request.getProductId(), request.getQuantity());

        ProductResponse response = ProductResponse.newBuilder()
                .setAvailable(inStock)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
