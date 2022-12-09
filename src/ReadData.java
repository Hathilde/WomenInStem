
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.*;

public class ReadData {
    String filePath;
    private ArrayList<Medier> sortedMediaObjects;
    private ArrayList<Film> allFilmObjects;
    private ArrayList<Serier> allSerierObjects;

    private String[] arrayGenreMenu;

    private ReadData dataReaderFilm;

    private ReadData dataReaderSerier;

    public ReadData() {
        allFilmObjects = new ArrayList<>();
        allSerierObjects = new ArrayList<>();
        sortedMediaObjects = new ArrayList<>();
    }

    public void reader(String filePath) {
        //this.filePath = filePath;
        File file = new File(filePath);
        String[] singleMediaMetaData;



        Scanner s = null;

        try {
           s = new Scanner(file, "iso-8859-1");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

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

                String imgPath = "serieforsider2/" + title + ".jpg";

                Serier serie = new Serier(title, years, genre, rating, imgPath, seasonsAndEpisodes);
                sortedMediaObjects.add(serie);
                allSerierObjects.add(serie);

            } else {
                String imgPath = "filmplakater/" + title + ".jpg";

                Film film = new Film(title, years, genre, rating, imgPath);
                sortedMediaObjects.add(film);
                allFilmObjects.add(film);

            }
        }
    }

    public ArrayList<Medier> getSortedMediaObjects() {
        return sortedMediaObjects;
    }
    public ArrayList<Film> getSortedFilmObjects() {
        return allFilmObjects;
    }
    public ArrayList<Serier> getSortedSerierObjects() {
        return allSerierObjects;
    }

    public String[] getGenreArray() {

        HashSet<String> hashSetGenre = new HashSet<>();

        for (Medier current : getSortedMediaObjects()) {
            for(String genre : current.getGenre()) {
                hashSetGenre.add(genre);
            }
        }

        arrayGenreMenu = new String[hashSetGenre.size() + 1];
             int i = 1;

            for (String current : hashSetGenre) {
            arrayGenreMenu[i++] = current;
            }

        return arrayGenreMenu;
       /* opret string array med str. på hashset. (.size) + 1 længere til all genres
                - set all genres til 0 i string array

                for loop, som tilføjer hashset værdierne, og den starter fra int i 1.
                i++
                        return array */
    }
/*
public String[] hejsameddigsa() {
    ReadData dataReaderFilm = new ReadData("./film.txt");
    dataReaderFilm.reader();
    dataReaderFilm.getGenreArray();

    ReadData dataReaderSerier = new ReadData("./serier.txt");
    dataReaderSerier.reader();
    dataReaderSerier.getGenreArray();

    List<String> filmArray = Arrays.asList(dataReaderFilm.getGenreArray());
    List<String> serieArray = Arrays.asList(dataReaderSerier.getGenreArray());

    Set<String> hashButReturnAsArray = new HashSet<>();
    hashButReturnAsArray.addAll(filmArray);
    hashButReturnAsArray.addAll(serieArray);

    System.out.println(hashButReturnAsArray); //1 2 3 4 5 6 7 8


    String[] arrayGenreMenu2 = new String[hashButReturnAsArray.size() + 1];
    int i = 1;

    for (String current : hashButReturnAsArray) {
        arrayGenreMenu2[i++] = current;
    }

    return arrayGenreMenu2;


}
*/


}

