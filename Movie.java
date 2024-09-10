public class Movie {
    String movieName;
    float length;

    public Movie(String movieName, float length) {
        this.movieName = movieName;
        this.length = length;
    }

    public String getMovieName(){
        return this.movieName;
    }

    public float getLength(){
        return this.length;
    }
}

