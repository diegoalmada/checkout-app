package br.com.diegoalmada.checkout.controller;

import br.com.diegoalmada.checkout.exception.ProductsBadRequest;
import br.com.diegoalmada.checkout.dto.PurchaseRequest;
import br.com.diegoalmada.checkout.dto.PurchaseResponse;
import br.com.diegoalmada.checkout.service.checkout.CheckoutService;
import br.com.diegoalmada.checkout.service.external.HashProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(
        path = "/checkout",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CheckoutController {

    private final CheckoutService checkoutService;

    private final HashProductService hashProductServiceApi;

    public CheckoutController(
            CheckoutService checkoutService,
            HashProductService hashProductServiceApi
    ) {
        this.checkoutService = checkoutService;
        this.hashProductServiceApi = hashProductServiceApi;
    }

    @PostMapping
    public ResponseEntity<PurchaseResponse> purchase(
           @Valid @RequestBody PurchaseRequest purchaseRequest,
           BindingResult errors
    ) {

        if (errors.hasErrors()) {
           throw new ProductsBadRequest();
        }
        return new ResponseEntity<>(
                this.checkoutService.purchase(purchaseRequest.getProductCarts()),
                HttpStatus.OK
        );
    }
}
