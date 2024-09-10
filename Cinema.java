import java.util.*;

public class Cinema {
    private String name;
    private String address;
    private List<Hall> halls;

    public Cinema(String name, String address) {
        this.name = name;
        this.address = address;
        this.halls = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }

    public Hall addHall(Hall hall) {
        halls.add(hall);
        return hall;
    }

    public List<Hall> getHalls(){
        return this.halls;
    }

    public List<Show> displaySelectedShow(String movieName) {
        List<Show> shows = new ArrayList<>();
        for (Hall hall : halls) {
            shows.addAll(hall.displaySelectedShow(movieName));
        }
        return shows;
    }
}

