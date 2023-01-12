package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.service.FileServiceImpl;
import org.example.service.GreetingServiceImpl;

import java.io.IOException;

/**
 * Hello world!
 */
public class ServerApp {
    private final static int DEFAULT_GRPC_PORT = 6565;
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(DEFAULT_GRPC_PORT)
                .addService(new GreetingServiceImpl())
                .addService(new FileServiceImpl())
                .build();
        server.start();
        System.out.println("Server is started");
        server.awaitTermination();
    }
}
