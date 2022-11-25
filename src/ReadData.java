
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.*;

public class ReadData {
    private static ArrayList<String> filmListOfTitles;

    public ReadData(String name){
        this.filmListOfTitles = new ArrayList<>();
    }

    public static void main(String[] args) {
        film();
    }

    public static void film() {
        File file = new File("./film.txt");
        // List<String> filmListOfTitles = new ArrayList<>();
        String[] singleFilmMetadata = new String[3];

        List<Film> listeMedAlleFilm = new ArrayList<>();

        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                // filmListOfTitles.add(s.nextLine());

                singleFilmMetadata = s.nextLine().split(";");
                String title = singleFilmMetadata[0];

                String year = singleFilmMetadata[1];

                String genreListe = singleFilmMetadata[2];
                String[] genreArray = genreListe.split(",");
                List<String> genre = new ArrayList<>(Arrays.asList(genreArray));

                String rating = singleFilmMetadata[3];
                //System.out.println(singleFilmMetadata[3]);

                //System.out.println(genre);
                Film film = new Film(title, year, genre, rating);
                listeMedAlleFilm.add(film);

                //System.out.println(Arrays.toString(genreListe.toArray()));

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(singleFilmMetadata[].length);


        System.out.println(listeMedAlleFilm.get(99));
        System.out.println(listeMedAlleFilm.get(1));

        //for (String genre : genreListe) {
        //System.out.println(genre);
        //System.out.println(genre);


    }

}




