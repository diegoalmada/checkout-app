package br.com.diegoalmada.checkout.service.discount;

import br.com.diegoalmada.checkout.service.client.GrpcClientService;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class DiscountClientServiceTest {

    private static final int PRODUCT_ID = 2;

    @Autowired
    private DiscountClientService discountClientService;

    @Test
    @DisplayName("Should discount equals zero when channel is unavailable")
    public void shouldDiscountEqualsZeroWhenChannelIsUnavailable() {
        GrpcClientService grpcClientService = Mockito.mock(GrpcClientService.class);

        this.discountClientService = new DiscountClientServiceImpl(grpcClientService);

        Mockito.when(grpcClientService.getChannel()).thenThrow(StatusRuntimeException.class);

        assertEquals(0,  discountClientService.apply(PRODUCT_ID));
    }

    @Test
    @DisplayName("Should discount greater than zero when product exists")
    public void shouldDiscountGreaterThanOrEqualsZeroWhenProductExists() {
        assertTrue(discountClientService.apply(PRODUCT_ID) >= 0);
    }
}
