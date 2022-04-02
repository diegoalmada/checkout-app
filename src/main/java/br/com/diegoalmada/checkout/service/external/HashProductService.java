package br.com.diegoalmada.checkout.service.external;

import br.com.diegoalmada.checkout.dto.Product;
import java.util.List;

public interface HashProductService {

    List<Product> getNonGiftProducts();
}
