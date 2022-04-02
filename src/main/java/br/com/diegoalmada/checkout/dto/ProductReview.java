package br.com.diegoalmada.checkout.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
public class ProductReview {

    @Getter
    private Integer id;

    @Getter
    private Integer quantity;

    @JsonProperty("unit_amount")
    private Long unitAmount;

    private Float discountPercent;

    @JsonIgnore
    private Float amountWithDiscount;

    @Getter
    @JsonProperty("is_gift")
    private Boolean gift;

    public Long getUnitAmount() {
        if(quantity == 0) {
            return 0L;
        }
        return unitAmount;
    }

    @JsonProperty("total_amount")
    public Long getTotalAmount() {
        return unitAmount * quantity;
    }

    @JsonProperty("discount")
    public Long getDiscount(){
        return (long) (getTotalAmount() * discountPercent);
    }

    public Long getAmountWithDiscount() {
        return getTotalAmount() - getDiscount();
    }
}
