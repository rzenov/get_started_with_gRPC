package org.example;

import io.grpc.stub.StreamObserver;
import org.example.grpc.GreetingServiceGrpc;
import org.example.grpc.GreetingServiceOuterClass;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request,
                         StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {
        GreetingServiceOuterClass.HelloResponse helloResponse = GreetingServiceOuterClass.HelloResponse
                .newBuilder()
                .setGreeting("Hello there, " + request.getName())
                .build();
        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
    }
}
