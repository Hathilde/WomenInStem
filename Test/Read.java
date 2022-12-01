import org.junit.Assert;
import org.junit.Test;


public class Read {

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


        System.out.println(dataReaderFilm.getSortedMediaObjects().get(0));

        System.out.println(dataReaderSerier.getSortedMediaObjects().get(0));


    }
}