package br.com.diegoalmada.checkout.service.external;

import br.com.diegoalmada.checkout.exception.ApiUnavailableException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class HashProductServiceFailureTest {

    @Autowired
    private HashProductService hashProductService;

    @Test
    @DisplayName("Should throw exception when api is unavailable")
    void shouldThrowExceptionWhenApiIsUnavailable() {
        assertThrows(ApiUnavailableException.class, () -> this.hashProductService.getNonGiftProducts());
    }
}
