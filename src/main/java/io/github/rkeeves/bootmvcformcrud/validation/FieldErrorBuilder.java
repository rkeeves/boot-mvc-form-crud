package io.github.rkeeves.bootmvcformcrud.validation;

import lombok.Builder;
import org.springframework.validation.FieldError;

@Builder
public class FieldErrorBuilder {

    private String objectName;

    private String fieldName;

    private String rejectedValue;

    private String message;

    public FieldError toFieldError() {
        return new FieldError(objectName, fieldName, rejectedValue, false, null, null, message);
    }
}
