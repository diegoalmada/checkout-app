package br.com.diegoalmada.checkout.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PurchaseRequest {

    @JsonProperty("products")
    @NotEmpty
    private List<@Valid ProductCart> productCarts;
}
