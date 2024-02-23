package com.neudesic.MediAssists.exceptions;

import lombok.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Data
@AllArgsConstructor
public class BindingResultException extends RuntimeException {

    BindingResult bindingResult;

    public BindingResultException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public BindingResultException() {
       super();
    }
}
