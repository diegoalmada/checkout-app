package br.com.diegoalmada.checkout.service.discount;

import br.com.diegoalmada.checkout.service.client.GrpcClientService;
import discount.DiscountGrpc;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import org.springframework.stereotype.Service;
import static discount.DiscountOuterClass.GetDiscountRequest;
import static discount.DiscountOuterClass.GetDiscountResponse;

@Service
public class DiscountClientServiceImpl implements DiscountClientService {

    private final GrpcClientService client;

    public DiscountClientServiceImpl(GrpcClientService client) {
        this.client = client;
    }

    public float apply(int productId) {
        try  {
            ManagedChannel channel = this.client.getChannel();

            GetDiscountRequest request = GetDiscountRequest
                    .newBuilder()
                    .setProductID(productId)
                    .build();

            GetDiscountResponse response = DiscountGrpc
                    .newBlockingStub(channel)
                    .getDiscount(request);

            return response.getPercentage();
        } catch(StatusRuntimeException e) {
            return 0F;
        }
    }
}
