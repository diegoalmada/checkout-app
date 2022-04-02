package br.com.diegoalmada.checkout.service.checkout;

import br.com.diegoalmada.checkout.dto.ProductCart;
import br.com.diegoalmada.checkout.dto.PurchaseResponse;
import java.util.List;

public interface CheckoutService {
    PurchaseResponse purchase(List<ProductCart> products);
}
