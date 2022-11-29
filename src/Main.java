import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        System.out.println("This is our main!");
        test1();
        ReadData.returnListOfAllMedia();

    }

    public static void test1() {
        ReadData.film();
        ReadData.series();

    }
}
