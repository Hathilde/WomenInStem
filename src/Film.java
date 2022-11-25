import java.util.*;
public class Film extends Medier {

    Film (String title, String year, List <String> genre, String rating ){
        super(title, year, genre, rating);
    }

    @Override
    public String toString(){
        return "Titlen er " + getTitle() + ". Året den er lavet er"
                + getYear() + ". Genren er " + getGenre() + ". IMDB-ratingen er"
                + getRating() + ".";

    }

}

