package br.com.diegoalmada.checkout.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class Error {
    private HttpStatus httpStatus;
    private String message;
}
