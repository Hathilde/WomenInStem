import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

    public abstract class Medier {
        private String title;
        private String year;
        private List<String> genre;
        private String rating;

        private String imgPath;

        private HashSet<String> allGenres;

        Medier (String title, String year, List <String> genre, String rating, String imgPath ){
            this.title = title;
            this.year = year;
            this.genre = genre; //new ArrayList<>();
            this.rating = rating;
            this.imgPath = imgPath;
            this.allGenres = allGenres;


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

        public HashSet<String> returnListOfGenres() {
            allGenres = new HashSet<>();
            return returnListOfGenres();
        }

            public String toString(){
                String title =  "Titlen er " + getTitle() + ". ";
                String year = "Årene den er lavet er" + getYear() + ". ";
                String genre = "Genren er " + getGenre() + ". ";
                String IMDBrating = "IMDB-ratingen er " + getRating() + ". ";

                String finalString = title + "<br>" + year + "<br>" + genre + "<br>" + IMDBrating;

                return finalString;
            }
        }


