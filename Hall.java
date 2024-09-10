import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Hall {
    private int hallID;
    private int capacity;
    private Seat[] seats;
    private PriorityQueue<Show> shows;
    private Map<String, List<Show>> showsByMovieName;
    private Cinema cinema;  // Reference to the parent cinema

    public Hall(int hallID, int capacity, Cinema cinema) {
        this.hallID = hallID;
        this.capacity = capacity;
        this.seats = new Seat[capacity];
        for (int i = 0; i < capacity; i++) {
            seats[i] = new Seat(i);
        }
        this.shows = new PriorityQueue<>(Comparator.comparing(show -> show.getStart()));
        this.showsByMovieName = new HashMap<>();
        this.cinema = cinema;  // Storing reference to the cinema
    }

    public Cinema getCinema(){
        return this.cinema;
    }

    public int getHallID(){
        return this.hallID;
    }

    // Adds a show to the hall if there is no time overlap with existing shows
    public boolean addShow(Show show) {
        if (show == null || !canScheduleShow(show)) {
            return false;  // Return false if show is null or overlaps
        }
        shows.add(show);
        showsByMovieName.putIfAbsent(show.getMovie().getMovieName(), new ArrayList<>());
        showsByMovieName.get(show.getMovie().getMovieName()).add(show);
        return true;
    }

    // Checks if the show can be scheduled without overlapping with existing shows
    private boolean canScheduleShow(Show newShow) {
        for (Show existingShow : shows) {
            if (showsOverlap(existingShow, newShow)) {
                return false;  // There is a time overlap
            }
        }
        return true;  // No overlap, can schedule
    }

    // Helper method to determine if two shows overlap
    private boolean showsOverlap(Show show1, Show show2) {
        // Check if the shows are on the same date
        if (show1.getDate() != show2.getDate()) {
            return false;
        }
        // Check for overlap in times
        return show1.getStart() < show2.getEnd() && show2.getStart() < show1.getEnd();
    }

    public List<Seat> getAvailableSeats(int date) {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isReserved(date)) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    // Display all shows of a specified movie in this hall
    public List<Show> displaySelectedShow(String movieName) {
        return showsByMovieName.getOrDefault(movieName, new ArrayList<>());
    }

    // Returns a clone of the seats array to prevent external modifications to the original seats
    public Seat[] getSeats() {
        return Arrays.copyOf(seats, seats.length);
    }

    // Returns all shows in this hall
    public List<Show> getAllShows() {
        return new ArrayList<>(shows);
    }

    // Print all shows with cinema information
    public void printAllShows() {
        List<Show> allShows = getAllShows();
        for (Show show : allShows) {
            System.out.println("Cinema: " + cinema.getName() +  // Using the cinema reference
                               ", Hall ID: " + hallID +
                               ", Show ID: " + show.getShowID() +
                               ", Movie: " + show.getMovie().movieName +
                               ", Start Time: " + show.getStart() +
                               ", End Time: " + show.getEnd() +
                               ", Date: " + show.getDate());
        }
    }
}
