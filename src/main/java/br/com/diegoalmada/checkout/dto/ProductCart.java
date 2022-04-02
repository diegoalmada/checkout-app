package br.com.diegoalmada.checkout.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@AllArgsConstructor
public class ProductCart {

    @JsonAlias("id")
    @NotNull
    @Positive
    private Integer productId;

    @NotNull
    @Positive
    private Integer quantity;
}
