import java.util.*;

    public abstract class Medier {
        // 5 private felter //
        private String title;
        private String year;
        private List<String> genre;
        private String rating;
        private String imgPath;

        // Konstruktøren tager 5 argumenter//
        Medier (String title, String year, List <String> genre, String rating, String imgPath ){
            // Ved brug af this. tildeles værdien af parameteren til instans-variablen for det aktuelle objekt.
            this.title = title;
            this.year = year;
            this.genre = genre;
            this.rating = rating;
            this.imgPath = imgPath;
        }

        //Get-metoder//

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

        public String getImgPath() {
            return imgPath;
        }

        //toString()-metoden returnerer objektet repræsenteret som tekststrenge.
        public String toString(){
            String title =  "Titlen er " + getTitle() + ". ";
            String year = "Årene den er lavet er" + getYear() + ". ";
            String genre = "Genren er " + getGenre() + ". ";
            String IMDBrating = "IMDB-ratingen er " + getRating() + ". ";

            String finalString = title + "<br>" + year + "<br>" + genre + "<br>" + IMDBrating;

            return finalString;
        }
    }