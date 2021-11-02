package org.mystery_muscle.random_gohome_booster.exception;

public class MMException extends RuntimeException {

    public MMException(){
        super();
    }
    public MMException(String message, Throwable cause){
        super(message, cause);
    }
    public MMException(String message) {
        super(message);
    }
}
