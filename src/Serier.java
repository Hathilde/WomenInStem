import java.util.*;
public class Serier extends Medier {
   // int episoder;
   // int sæsoner;
    String seasonsAndEpisodes;

    Serier (String title, String year, List <String> genre, String rating, String imgPath, String seasonsAndEpisodes){
        super(title, year, genre, rating, imgPath);
       // this.episoder = episoder; //instansiere episoder
       // this.sæsoner = sæsoner;
        this.seasonsAndEpisodes = seasonsAndEpisodes;
    }

    public String getSeasonsAndEpisodes() {
        return seasonsAndEpisodes;


    }
    @Override
    public String toString(){
        return super.toString() + "<br>" + "Sæsoner og episoder " + getSeasonsAndEpisodes();

    }

}