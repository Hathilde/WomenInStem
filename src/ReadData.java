
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.*;

public class ReadData {
    String filePath;
    private ArrayList<Medier> sortedMediaObjects;

    public ReadData(String filePath) {
        this.filePath = filePath;

    }

    public void reader() {

        File file = new File(filePath);
        String[] singleMediaMetaData;

        sortedMediaObjects = new ArrayList<>();

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

            } else {
                String imgPath = "filmplakater/" + title + ".jpg";

                Film film = new Film(title, years, genre, rating, imgPath);
                sortedMediaObjects.add(film);

            }
        }
    }

    public ArrayList<Medier> getSortedMediaObjects() {
        return sortedMediaObjects;
    }
}

