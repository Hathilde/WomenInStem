import java.util.*;
// Serier er en subklasse af Medier og arver dermed felter og metoder fra Medier.
public class Serier extends Medier {
    //Serier har sit eget felt som er en tekststreng som kaldes seasonsAndEpisodes.
    String seasonsAndEpisodes;
    //Konstruktøren tager 6 argumenter
    Serier (String title, String year, List <String> genre, String rating, String imgPath, String seasonsAndEpisodes){

        super(title, year, genre, rating, imgPath);
        this.seasonsAndEpisodes = seasonsAndEpisodes;
    }

    //get-metode for tekststrengen seasonsAndEpisodes
    public String getSeasonsAndEpisodes() {
        return seasonsAndEpisodes;

    }

    // //toString()-metoden kalder super-klassen Mediers toString()-metode og tilføjer tekststrengen seasonsAndEpisodes ved kald på getSeasonsAndEpisodes().
    public String toString(){
        return super.toString() + "<br>" + "Sæsoner og episoder " + getSeasonsAndEpisodes();

    }
}