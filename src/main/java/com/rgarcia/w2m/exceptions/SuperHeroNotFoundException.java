package com.rgarcia.w2m.exceptions;

public class SuperHeroNotFoundException extends RuntimeException {
    public SuperHeroNotFoundException() {
        super("Superhero not found");
    }

    public SuperHeroNotFoundException(String message) {
        super(message);
    }

    public SuperHeroNotFoundException(Throwable cause) {
        super(cause);
    }

    public SuperHeroNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }			
}
