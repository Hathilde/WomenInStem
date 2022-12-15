import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ReadData {

    //Klassen har 6 private felter; 3 Arraylister, et array samt to objekter af ReadData.
    private ArrayList<Medier> allMediaObjects;
    private ArrayList<Medier> allFilmObjects;
    private ArrayList<Medier> allSerierObjects;

    private String[] arrayGenreMenu;

    private ReadData dataReaderFilm;

    private ReadData dataReaderSerier;

    //Konstruktøren instansierer de 3 arraylister.
    public ReadData() {
        allFilmObjects = new ArrayList<>();
        allSerierObjects = new ArrayList<>();
        allMediaObjects = new ArrayList<>();
    }

    //createAllMediaObjectsList() kalder på metoden reader () med de to file-paths.
    public void createAllMediaObjectsList() {
        reader("./film.txt");
        reader("./serier.txt");
    }

    //reader() tager et filePath som argument.
    public void reader(String filePath) {

        File file = new File(filePath);
        String[] singleMediaMetaData;

        Scanner s = null;
        //Ved brug af en scanner, læses filerne ind.
        try {
           s = new Scanner(file, "iso-8859-1");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //De to plakat-filer instantieres
        String baseFilmPath = (new File("filmplakater")).getAbsolutePath();
        String baseSeriePath = (new File("serieforsider2")).getAbsolutePath();

        //while-løkken løber igennem så længe scanneren har en linje at læse.
        //Dataen fra film og serier bliver gemt i et array som kaldes singleMediaMetaData der består af tekststrenge. Daten opdeles ved hvert ";" symbol.
        //Alle titler (som har plads 0 i array'et) gemmes i en tekststreng som kaldes title.
        //Alle årstal (som har plads 1 i array'et) gemmes i en tekststreng som kaldes years.
        //Alle genrer (som har plads 2 i array'et) gemmes i en tekststreng som kaldes genrerListe.
        //Alle ratings (som har plads 3 i array'et) gemmes i en tekststreng som kaldes rating.

        while (s.hasNextLine()) {

            singleMediaMetaData = s.nextLine().trim().split(";");
            String title = singleMediaMetaData[0];

            String years = singleMediaMetaData[1];

            String genreListe = singleMediaMetaData[2];
            String[] genreArray = genreListe.split(",");
            List<String> genre = new ArrayList<>(Arrays.asList(genreArray));

            String rating = singleMediaMetaData[3];

            // Hvis betingelsen opfyldes om at der er yderligere info på mediet end de 4 foreløbige pladser, oprettes et Serieobjekt.
            //Alle sæsoner og episoder (som har plads 4 i array'et) gemmes i en tekststreng som kaldes seasonsAndEpisodes.
            //Billede-pathen kan nu kobles op på det korrekte objekt.
            if (singleMediaMetaData.length > 4) {

                String seasonsAndEpisodes = singleMediaMetaData[4];

                String imgPath = baseSeriePath+ "/" + title + ".jpg";

                Serier serie = new Serier(title, years, genre, rating, imgPath, seasonsAndEpisodes);
                allMediaObjects.add(serie);
                allSerierObjects.add(serie);

                //Ellers oprettes et FilmObjekt.
            } else {
                String imgPath = baseFilmPath + "/" + title + ".jpg";

                Film film = new Film(title, years, genre, rating, imgPath);
                allMediaObjects.add(film);
                allFilmObjects.add(film);

            }
        }
    }

    //----get-metoder ---- //

    public ArrayList<Medier> getAllMediaObjects() {

        return allMediaObjects;
    }
    public ArrayList<Medier> getAllFilmObjects() {

        return allFilmObjects;
    }
    public ArrayList<Medier> getAllSerierObjects() {

        return allSerierObjects;
    }

    //getGenreArray() kører først alle genrer igennem på alle media-objekter. Disse tilføjes til et HashSet
    // som kaldes hashSetGenre.
    public String[] getGenreArray() {

        HashSet<String> hashSetGenre = new HashSet<>();

        for (Medier current : getAllMediaObjects()) {
            for(String genre : current.getGenre()) {
                hashSetGenre.add(genre);
            }
        }
        //Alle genrerne puttes nu ind i et array som kaldes arrayGenreMenu.
        arrayGenreMenu = new String[hashSetGenre.size()];
             int i = 0;

            for (String current : hashSetGenre) {
                arrayGenreMenu[i++] = current;
            }

        return arrayGenreMenu;

    }

}