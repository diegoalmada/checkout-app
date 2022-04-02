package br.com.diegoalmada.checkout.service.checkout;

import br.com.diegoalmada.checkout.dto.Product;
import br.com.diegoalmada.checkout.dto.ProductCart;
import br.com.diegoalmada.checkout.dto.PurchaseResponse;
import br.com.diegoalmada.checkout.service.discount.DiscountClientService;
import br.com.diegoalmada.checkout.service.external.HashProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final HashProductService hashProductService;

    private final DiscountClientService discountClientService;

    public CheckoutServiceImpl(HashProductService hashProductService, DiscountClientService discountClientService) {
        this.hashProductService = hashProductService;
        this.discountClientService = discountClientService;
    }

    @Override
    public PurchaseResponse purchase(List<ProductCart> cart) {
        List<Product> productsApi = this.hashProductService.getNonGiftProducts();
        PurchaseResponse purchaseResponse = new PurchaseResponse();
        productsApi
                .stream()
                .forEach(productApi -> {
                    Optional<ProductCart> productCart = cart
                            .stream()
                            .filter(prod -> Objects.equals(productApi.getId(),
                                    prod.getProductId()))
                            .findFirst();

                    if(productCart.isPresent()) {
                        float discount = discountClientService.apply(productApi.getId());
                        purchaseResponse.addProductReview(productApi, productCart.get().getQuantity(), discount);
                    }
                });

        return purchaseResponse;
    }
}
