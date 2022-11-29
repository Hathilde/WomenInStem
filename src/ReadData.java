
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.*;

public class ReadData {
   static ArrayList<Film> sortedFilmObjects;
    static ArrayList<Serier> sortedSeriesObjects;

   static  ArrayList<Medier> alleMedier;


    public ReadData(String name) {

        //this.filmListOfTitles = filmListOfTitles;
        //this.seriesListOfTitles = seriesListOfTitles;
    }


    public static void film() {
        File file = new File("./film.txt");
        // List<String> filmListOfTitles = new ArrayList<>();
        String[] singleFilmMetadata = new String[3];

        List<Film> sortedFilmObjects = new ArrayList<>();

        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                //filmListOfTitles.add(s.nextLine());

                singleFilmMetadata = s.nextLine().split(";");
                String title = singleFilmMetadata[0];

                String year = singleFilmMetadata[1];

                //year.trim();
                //Integer.parseInt(year);

                String genreListe = singleFilmMetadata[2];
                String[] genreArray = genreListe.split(",");
                List<String> genre = new ArrayList<>(Arrays.asList(genreArray));

                String rating = singleFilmMetadata[3];
                //System.out.println(singleFilmMetadata[3]);

                String imgPath = "filmplakater/" + title + ".jpg";

                //System.out.println(genre);
                Film film = new Film(title, year, genre, rating, imgPath);
                sortedFilmObjects.add(film);

                //System.out.println(Arrays.toString(genreListe.toArray()));

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(singleFilmMetadata[].length);


        //System.out.println(sortedFilmObjects.get(99));
        //System.out.println(sortedFilmObjects.get(1));

        //for (String genre : genreListe) {
        //System.out.println(genre);
        //System.out.println(genre);


    }

    public static void series() {
        //BufferedReader inputFile= new BufferedReader(new FileReader("./serier.txt"));
        File file = new File("./serier.txt");
        // List<String> filmListOfTitles = new ArrayList<>();
        //System.out.println(file);
        String[] singleSerieMetadata;

        List<Serier> sortedSeriesObjects = new ArrayList<>();

        try {
            Scanner s = new Scanner(file, "iso-8859-1");
            while (s.hasNextLine()) {

                singleSerieMetadata = s.nextLine().split(";");
                String title = singleSerieMetadata[0];
                //System.out.println(singleSerieMetadata[0]);
                String years = singleSerieMetadata[1];

                String genreListe = singleSerieMetadata[2];
                String[] genreArray = genreListe.split(",");
                List<String> genre = new ArrayList<>(Arrays.asList(genreArray));

                String rating = singleSerieMetadata[3];

                String seasonsAndEpisodes = singleSerieMetadata[4];

                String imgPath = "serieforsider2/" + title + ".jpg";

                Serier serie = new Serier(title, years, genre, rating, imgPath, seasonsAndEpisodes);
                sortedSeriesObjects.add(serie);
                //System.out.println(singleSerieMetadata.length());

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(sortedSeriesObjects.size());
        //System.out.println(sortedSeriesObjects.get(1));

    }



    /* public static void returnListOfAllMedia() {

        alleMedier = new ArrayList<>();



        for (Film current : sortedFilmObjects) {
            alleMedier.add(current);

        }
        for (Serier current : sortedSeriesObjects) {
            alleMedier.add(current);
        }

        System.out.println(alleMedier);

       // return alleMedier;
    }

     */

}




