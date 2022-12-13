package CustomExceptions;

import java.awt.*;

public class NoMediaFoundException extends Exception {

    public NoMediaFoundException() {


        super("Der er ikke nogle medier, der matcher din søgning");


    }


}
