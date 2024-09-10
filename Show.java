import java.util.PriorityQueue;

public class Show {
    private int showID;
    private float start;
    private float end;
    private int date;
    private Movie movie;
    private Hall hall;

    public Show(int showID, float start, int date, Movie movie, Hall hall) {
        this.showID = showID;
        this.start = start;
        this.end = start + movie.getLength();
        this.date = date;
        this.movie = movie;
        this.hall = hall;
    }

    public int getShowID(){
        return this.showID;
    }

    public Hall getHall() {
        return hall;
    }

    public Movie getMovie(){
        return this.movie;
    }

    public float getStart(){
        return this.start;
    }

    public float getEnd(){
        return this.end;
    }

    public int getDate(){
        return this.date;
    }
}
