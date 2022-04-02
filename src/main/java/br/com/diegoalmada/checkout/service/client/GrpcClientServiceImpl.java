package br.com.diegoalmada.checkout.service.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientServiceImpl implements GrpcClientService {

    @Value("${grpc.host}")
    private String host;

    @Value("${grpc.port}")
    private Integer port;

    @Override
    public ManagedChannel getChannel() {
        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
    }
}
