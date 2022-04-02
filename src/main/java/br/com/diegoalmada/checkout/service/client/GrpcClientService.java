package br.com.diegoalmada.checkout.service.client;

import io.grpc.ManagedChannel;

public interface GrpcClientService {
    ManagedChannel getChannel();
}
