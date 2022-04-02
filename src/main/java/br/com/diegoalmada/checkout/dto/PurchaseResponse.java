package br.com.diegoalmada.checkout.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PurchaseResponse {

    @Getter
    @JsonProperty("total_amount")
    private Long totalAmount;

    @Getter
    @JsonProperty("total_amount_with_discount")
    private Long totalAmountDiscount;

    @Getter
    @JsonProperty("total_discount")
    private Long totalDiscount;

    private List<ProductReview> products;

    public PurchaseResponse() {
        this.totalAmount = 0L;
        this.totalAmountDiscount = 0L;
        this.totalDiscount = 0L;
        this.products = new ArrayList<>();
    }

    public List<ProductReview> getProducts() {
        return Collections.unmodifiableList(this.products);
    }

    public void addProductReview(Product product, Integer quantity, Float discount) {
        ProductReview productReview = ProductReview
                .builder()
                .id(product.getId())
                .discountPercent(discount)
                .unitAmount(product.getAmount())
                .quantity(quantity)
                .gift(product.getGift())
                .build();
        this.products.add(productReview);
        updateTotalValues(productReview);
    }

    private void updateTotalValues(ProductReview productReview) {
        totalAmount += productReview.getTotalAmount();
        totalDiscount += productReview.getDiscount();
        totalAmountDiscount += productReview.getAmountWithDiscount();
    }
}
