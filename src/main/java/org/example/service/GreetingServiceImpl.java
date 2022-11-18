package org.example.service;

import io.grpc.stub.StreamObserver;
import org.example.grpc.GreetingServiceGrpc;
import org.example.grpc.GreetingServiceOuterClass;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request,
                         StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {
        responseObserver.onNext(createResponse(request.getName()));
        responseObserver.onCompleted();
    }

    @Override
    public void greetings(GreetingServiceOuterClass.MultipleHelloRequest request,
                          StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {
        int responseCount = request.getResponseCount();
        while (responseCount-- > 0) {
            waitASecond();
            responseObserver.onNext(createResponse(request.getName()));

        }
        responseObserver.onCompleted();
    }

    private GreetingServiceOuterClass.HelloResponse createResponse(String name) {
        return GreetingServiceOuterClass.HelloResponse
                .newBuilder()
                .setGreeting("Hello there, " + name)
                .build();
    }

    private void waitASecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted, " + e.getMessage());
        }
    }
}
