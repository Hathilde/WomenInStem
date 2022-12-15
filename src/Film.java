import java.util.*;

// Film er en subklasse af Medier og arver dermed felter og metoder fra Medier.
public class Film extends Medier {

    //Konstruktøren tager 5 argumenter
    Film (String title, String year, List <String> genre, String rating, String imgPath){
        //Film kalder på super-klassen Mediers
        super(title, year, genre, rating, imgPath);
    }

    //toString()-metoden kalder super-klassen Mediers toString()-metode.
    public String toString(){
        return super.toString();
    }
}

