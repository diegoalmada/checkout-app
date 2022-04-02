package br.com.diegoalmada.checkout.service.checkout;

import br.com.diegoalmada.checkout.dto.ProductCart;
import br.com.diegoalmada.checkout.dto.PurchaseResponse;
import br.com.diegoalmada.checkout.service.discount.DiscountClientService;
import br.com.diegoalmada.checkout.service.external.HashProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CheckoutServiceTest {

    private CheckoutService checkoutService;

    @Autowired
    private HashProductService hashProductService;

    @Autowired
    private DiscountClientService discountClientService;

    @BeforeEach
    public void init() {
        this.checkoutService = new CheckoutServiceImpl(hashProductService, discountClientService);
    }

    @Test
    @DisplayName("Should return empty response and totals are zero when cart is empty")
    public void shouldReturnEmptyProductResponseAndTotalsAreZeroWhenProductCartIsEmpty() {
        PurchaseResponse response = checkoutService.purchase(new ArrayList<>());
        assertTrue(response.getProducts().isEmpty());
        assertEquals(0, response.getTotalAmount());
        assertEquals(0, response.getTotalDiscount());
        assertEquals(0, response.getTotalAmountDiscount());
    }

    @Test
    @DisplayName("Should product response is empty when hash api is empty")
    public void shouldProductResponseIsEmptyWhenHashApiIsEmpty() {
        HashProductService hashProductService = mock(HashProductService.class);
        DiscountClientService discountClientService = mock(DiscountClientService.class);

        List<ProductCart> cart = new ArrayList<>();
        cart.add(new ProductCart(1, 1));
        cart.add(new ProductCart(2, 10));

        this.checkoutService = new CheckoutServiceImpl(hashProductService, discountClientService);

        when(hashProductService.getNonGiftProducts()).thenReturn(Collections.emptyList());

        PurchaseResponse response = this.checkoutService.purchase(cart);
        assertTrue(response.getProducts().isEmpty());
        assertEquals(0, response.getTotalAmount());
        assertEquals(0, response.getTotalDiscount());
        assertEquals(0, response.getTotalAmountDiscount());
    }

    @Test
    @DisplayName("should discount attributes are zero when grpc service is unavailable")
    public void shouldDiscountAttributesAreZeroWhenGrpcClientIsUnavailable() {
        DiscountClientService discountClientService = mock(DiscountClientService.class);

        List<ProductCart> cart = new ArrayList<>();
        cart.add(new ProductCart(1, 1));
        cart.add(new ProductCart(2, 10));

        this.checkoutService = new CheckoutServiceImpl(hashProductService, discountClientService);

        when(discountClientService.apply(anyInt())).thenReturn(0F);

        PurchaseResponse response = this.checkoutService.purchase(cart);

        assertTrue(response.getProducts().size() > 0);
        assertEquals(0, response.getTotalDiscount());
        assertEquals(response.getTotalAmount(), response.getTotalAmountDiscount());
    }

    @Test
    @DisplayName("should discount is greater than zero when discount service works")
    public void shouldDiscountGreaterThanZeroWhenDiscountServiceWorks() {
        DiscountClientService discountClientService = mock(DiscountClientService.class);

        this.checkoutService = new CheckoutServiceImpl(hashProductService, discountClientService);

        List<ProductCart> cart = new ArrayList<>();
        cart.add(new ProductCart(1, 1));
        cart.add(new ProductCart(2, 10));

        when(discountClientService.apply(anyInt())).thenReturn(0.1F);

        PurchaseResponse response = this.checkoutService.purchase(cart);

        assertTrue(response.getProducts().size() > 0);
        assertTrue(response.getTotalDiscount() > 0);
        assertTrue(response.getTotalAmount() > response.getTotalAmountDiscount());
    }
}
