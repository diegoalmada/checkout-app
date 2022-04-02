package br.com.diegoalmada.checkout.service.client;

import io.grpc.ManagedChannel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest
public class GrpcClientServiceSuccessfullyTest {

    @Autowired
    private GrpcClientService client;

    @Test
    @DisplayName("Should return managed channel instance when the information of the grpc client is correct")
    void shouldReturnManagedChannelInstanceWhenTheInformationIsCorrect() {
        assertInstanceOf(ManagedChannel.class, this.client.getChannel());
    }
}
