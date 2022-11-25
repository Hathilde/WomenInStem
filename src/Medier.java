import java.util.ArrayList;
import java.util.List;

    public abstract class Medier {
        private String title;
        private String year;
        private List<String> genre;
        private String rating;

        Medier (String title, String year, List <String> genre, String rating ){
            this.title = title;
            this.year = year;
            this.genre = genre; //new ArrayList<>();
            this.rating = rating;

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
    }


