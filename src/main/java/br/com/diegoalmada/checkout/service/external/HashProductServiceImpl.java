package br.com.diegoalmada.checkout.service.external;

import br.com.diegoalmada.checkout.dto.Product;
import br.com.diegoalmada.checkout.exception.ApiUnavailableException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HashProductServiceImpl implements HashProductService {

    @Value("${hash.products.api}")
    private String hashApi;

    @Override
    public List<Product> getNonGiftProducts() {
        TypeReference<List<Product>> typeReference = new TypeReference<>(){};
        try {
            List<Product> products = new ObjectMapper().readValue(
                    TypeReference.class.getResourceAsStream(hashApi),
                    typeReference
            );
            return products
                    .stream()
                    .filter(product -> !product.getGift())
                    .collect(Collectors.toList());
        } catch(Exception e) {
            throw new ApiUnavailableException(e);
        }
    }
}
