package CustomExceptions;

public class NoMediaFoundException extends Exception {

    public NoMediaFoundException() {

        super("Der er ikke nogle medier, som matcher din søgning");


    }


}
