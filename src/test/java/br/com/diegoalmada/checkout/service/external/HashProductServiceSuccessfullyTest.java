package br.com.diegoalmada.checkout.service.external;

import br.com.diegoalmada.checkout.dto.Product;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HashProductServiceSuccessfullyTest {

    @Autowired
    private HashProductService hashProductService;

    @Test
    @DisplayName("Should retrieve products when api is available")
    void shouldRetrieveProductsWhenApiIsAvailable() throws IOException {
        List<Product> products = this.hashProductService.getNonGiftProducts();
        assertTrue(products.size() > 0);
    }
}
