import java.util.*;
public class Serier extends Medier {
    int episoder;
    int sæsoner;

    Serier (String title, String year, List <String> genre, String rating ){
        super(title, year, genre, rating);
        this.episoder = episoder; //instansiere episoder
        this.sæsoner = sæsoner;
    }

    public int getEpisoder() {
        return episoder;
    }

    public int getSæsoner() {
        return sæsoner;
    }

}