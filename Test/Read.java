import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class Read {
 

    @Test
    public void testListOfMedia() {
        ReadData dataReader = new ReadData();
        dataReader.reader("./film.txt");
        dataReader.reader("./serier.txt");
      // assert(dataReader.getSortedMediaObjects().get(99), "Titlen er Yankee Doodle Dandy. Året den er lavet er 1942. Genren er [ Biography,  Drama,  Musical]. IMDB-ratingen er 7,7."");

        System.out.println(dataReader.getAllMediaObjects().get(99).getImgPath());
        //System.out.println(dataReader.getSortedMediaObjects());

    }
    @Test
    public void testSpecificDataOfMedia() {
        ReadData dataReader = new ReadData();
        dataReader.reader("./film.txt");
        dataReader.reader("./serier.txt");
        // assert(dataReaderFilm.getSortedMediaObjects().get(99), "test");


        //System.out.println(dataReader.getSortedMediaObjects());
     

    }

   @Test
    public void testArrayOfGenre() {
       ReadData dataReader = new ReadData();
       dataReader.reader("./film.txt");
       dataReader.reader("./serier.txt");

       //System.out.println(Arrays.toString(dataReaderFilm.getGenreArray()));
       //System.out.println(Arrays.toString(dataReaderSerier.getGenreArray()));
       System.out.println(Arrays.toString(dataReader.getGenreArray()));

   }

    @Test
    public void testListOfImagePaths() {
        View newView = new View();

        newView.getListOfAllMedia();
        System.out.println(newView.returnListOfImages());
    }

    @Test
    public void testGetListOfAllMedia() {
        View newView = new View();

        System.out.println(newView.getListOfAllMedia());
    }
}