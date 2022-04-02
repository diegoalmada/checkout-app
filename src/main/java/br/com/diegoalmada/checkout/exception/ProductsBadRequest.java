package br.com.diegoalmada.checkout.exception;

public class ProductsBadRequest extends RuntimeException {

    public ProductsBadRequest() {
        super("Ocorreu um problema ao informar o(s) produto(s)");
    }
}
