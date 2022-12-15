import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ReadData {

    private ArrayList<Medier> allMediaObjects;
    private ArrayList<Medier> allFilmObjects;
    private ArrayList<Medier> allSerierObjects;

    private String[] arrayGenreMenu;

    private ReadData dataReaderFilm;

    private ReadData dataReaderSerier;

    public ReadData() {
        allFilmObjects = new ArrayList<>();
        allSerierObjects = new ArrayList<>();
        allMediaObjects = new ArrayList<>();
    }

    public void createAllMediaObjectsList() {
        reader("./film.txt");
        reader("./serier.txt");
    }

    public void reader(String filePath) {

        File file = new File(filePath);
        String[] singleMediaMetaData;

        Scanner s = null;

        try {
           s = new Scanner(file, "iso-8859-1");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String baseFilmPath = (new File("filmplakater")).getAbsolutePath();
        String baseSeriePath = (new File("serieforsider2")).getAbsolutePath();
        while (s.hasNextLine()) {

            singleMediaMetaData = s.nextLine().trim().split(";");
            String title = singleMediaMetaData[0];

            String years = singleMediaMetaData[1];

            String genreListe = singleMediaMetaData[2];
            String[] genreArray = genreListe.split(",");
            List<String> genre = new ArrayList<>(Arrays.asList(genreArray));

            String rating = singleMediaMetaData[3];

            if (singleMediaMetaData.length > 4) {

                String seasonsAndEpisodes = singleMediaMetaData[4];

                String imgPath = baseSeriePath+ "/" + title + ".jpg";

                Serier serie = new Serier(title, years, genre, rating, imgPath, seasonsAndEpisodes);
                allMediaObjects.add(serie);
                allSerierObjects.add(serie);

            } else {
                String imgPath = baseFilmPath + "/" + title + ".jpg";

                Film film = new Film(title, years, genre, rating, imgPath);
                allMediaObjects.add(film);
                allFilmObjects.add(film);

            }
        }
    }

    public ArrayList<Medier> getAllMediaObjects() {

        return allMediaObjects;
    }
    public ArrayList<Medier> getAllFilmObjects() {

        return allFilmObjects;
    }
    public ArrayList<Medier> getAllSerierObjects() {

        return allSerierObjects;
    }

    public String[] getGenreArray() {

        HashSet<String> hashSetGenre = new HashSet<>();

        for (Medier current : getAllMediaObjects()) {
            for(String genre : current.getGenre()) {
                hashSetGenre.add(genre);
            }
        }

        arrayGenreMenu = new String[hashSetGenre.size()];
             int i = 0;

            for (String current : hashSetGenre) {
                arrayGenreMenu[i++] = current;
            }

        return arrayGenreMenu;

    }

}