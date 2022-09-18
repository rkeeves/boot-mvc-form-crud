package io.github.rkeeves.bootmvcformcrud.exc;

public class TodoAppException extends RuntimeException {

    public TodoAppException() {
        super();
    }

    public TodoAppException(String message) {
        super(message);
    }
}
