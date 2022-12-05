import java.util.ArrayList;
import java.util.List;

    public abstract class Medier {
        private final String title;
        private final String year;
        private final List<String> genre;
        private final String rating;

        private final String imgPath;

        Medier (String title, String year, List <String> genre, String rating, String imgPath ){
            this.title = title;
            this.year = year;
            this.genre = genre; //new ArrayList<>();
            this.rating = rating;
            this.imgPath = imgPath;


        }

        public String getYear() {
            return year;
        }

        public String getRating() {
            return rating;
        }

        public List<String> getGenre() {
            return genre;
        }

        public String getTitle() {
            return title;
        }

        // Lav setter evt.

        public String getImgPath() {
            return imgPath;
        }
    }


