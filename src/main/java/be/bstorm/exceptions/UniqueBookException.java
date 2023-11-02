package be.bstorm.exceptions;

public class UniqueBookException extends RuntimeException{

    public UniqueBookException() {
        super("Le titre du livre est déja utilisé.");
    }

    public UniqueBookException(String message) {
        super(message);
    }
}
