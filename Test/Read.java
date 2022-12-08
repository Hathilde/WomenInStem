import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class Read {
 


    @Test
    public void createReadDataClass() {
        ReadData dataReaderFilm = new ReadData("./film.txt");
        ReadData dataReaderSerier = new ReadData("./serier.txt");
        dataReaderFilm.reader();
        dataReaderSerier.reader();
    }

    @Test
    public void testListsOfMedia() {
        ReadData dataReader = new ReadData("hej");


    }
    @Test
    public void testSpecificDataOfMedia() {
        ReadData dataReaderFilm = new ReadData("./film.txt");
        ReadData dataReaderSerier = new ReadData("./serier.txt");
        dataReaderFilm.reader();
        dataReaderSerier.reader();
        // assert(dataReaderFilm.getSortedMediaObjects().get(99), "test");

        System.out.println();
        System.out.println(dataReaderFilm.getSortedMediaObjects());
        System.out.println(dataReaderFilm.getSortedMediaObjects());
     

    }

   @Test
    public void testArrayOfGenre() {

        ReadData dataReaderFilm = new ReadData("./film.txt");
            dataReaderFilm.reader();
            dataReaderFilm.getGenreArray();

       ReadData dataReaderSerier = new ReadData("./serier.txt");
            dataReaderSerier.reader();
            dataReaderSerier.getGenreArray();

       System.out.println(Arrays.toString(dataReaderFilm.getGenreArray()));
       System.out.println(Arrays.toString(dataReaderSerier.getGenreArray()));


    }
}