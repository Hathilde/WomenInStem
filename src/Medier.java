import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

    public abstract class Medier {
        private String title;
        private String year;
        private List<String> genre;
        private String rating;

        private String imgPath;





        Medier (String title, String year, List <String> genre, String rating, String imgPath){
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


        public String getImgPath() {
            return imgPath;
        }



        public String toString(){
            String title =  "Titlen er " + getTitle() + ". ";
            String year = "Årene den er lavet er" + getYear() + ". ";
            String genre = "Genren er " + getGenre() + ". ";
            String IMDBrating = "IMDB-ratingen er " + getRating() + ". ";


            String finalString = title + "<br>" + year + "<br>" + genre + "<br>" + IMDBrating + "<br>";

            return finalString;
        }
    }


