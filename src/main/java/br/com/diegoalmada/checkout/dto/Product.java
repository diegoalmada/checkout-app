package br.com.diegoalmada.checkout.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Product {

    private Integer id;

    private String title;

    private String description;

    private Long amount;

    @JsonProperty("is_gift")
    private Boolean gift;
}
