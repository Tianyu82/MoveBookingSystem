import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Platform {
    private Map<String, Cinema> cinemas;   //{"FamilyTime": cinemaA, "Welax": cinemaB, "RoyalDog": cinemaC}

    public Platform() {
        cinemas = new HashMap<>();
    }

    // Display available seats for a selected show on a specific date
    public List<Seat> displayAvailableSeats(Show show, int date) {
        return show.getHall().getAvailableSeats(date);
    }

    // Adds a cinema to the platform 
    public boolean addCinema(Cinema cinema) {
        if (cinema == null || cinemas.containsKey(cinema.getName())) {
            return false;  // Return false if cinema is null or already exists
        }
        cinemas.put(cinema.getName(), cinema);
        return true;  // Return true if cinema was successfully added
    }

    // Book seats for a show on a specific date using seat IDs
    public boolean bookSeats(Show show, int[] seatIds, int date) {
        if (show == null) {
            System.out.println("Invalid show object provided.");
            return false;
        }
        Seat[] seats = show.getHall().getSeats();
        
        // Check if each seat ID is valid and not already reserved
        for (int seatId : seatIds) {
            if (seatId >= seats.length || seats[seatId].isReserved(date)) {
                System.out.println("Seat booking failed. Seat ID " + seatId + " is either invalid or already reserved for this date.");
                return false; // Booking fails if any seat ID is invalid or already reserved
            }
        }

        // Reserve all valid and available seats
        for (int seatId : seatIds) {
            seats[seatId].reserve(date);
        }

        return true; // Booking successful
    }

    // Displays all shows across all cinemas on the platform
    public List<Show> getAllShows() {
        List<Show> allShows = new ArrayList<>();
        for (Cinema cinema : cinemas.values()) {
            for (Hall hall : cinema.getHalls()) {
                allShows.addAll(hall.getAllShows());
            }
        }
        return allShows;
    }

    // Prints all shows with detailed information
    public void printAllShows() {
        List<Show> allShows = getAllShows();
        for (Show show : allShows) {
            Hall hall = show.getHall();
            Cinema cinema = hall.getCinema();
            System.out.println("Cinema: " + cinema.getName() +
                               ", Hall ID: " + hall.getHallID() +
                               ", Show ID: " + show.getShowID() +
                               ", Movie: " + show.getMovie().movieName +
                               ", Start Time: " + show.getStart() +
                               ", End Time: " + show.getEnd() +
                               ", Date: " + formatDate(show.getDate()));
        }
    }

    private String formatDate(int date) {
        switch (date) {
            case 0: return "Today";
            case 1: return "Tomorrow";
            case 2: return "Day After Tomorrow";
            default: return "Invalid date";
        }
    }

    // Method to print all shows that match a given movie title
    public void printSelectedShow(String movieTitle) {
        for (Cinema cinema : cinemas.values()) {
            for (Hall hall : cinema.getHalls()) {
                for (Show show : hall.getAllShows()) {
                    if (show.getMovie().getMovieName().equalsIgnoreCase(movieTitle)) {
                        System.out.println("Cinema: " + cinema.getName() +
                                           ", Hall ID: " + hall.getHallID() +
                                           ", Show ID: " + show.getShowID() +
                                           ", Movie: " + show.getMovie().getMovieName() +
                                           ", Start Time: " + show.getStart() +
                                           ", End Time: " + show.getEnd() +
                                           ", Date: " + formatDate(show.getDate()));
                    }
                }
            }
        }
    }

    public Show findShowByID(int showID) {
        for (Cinema cinema : cinemas.values()) {
            for (Hall hall : cinema.getHalls()) {
                for (Show show : hall.getAllShows()) {
                    if (show.getShowID() == showID) {
                        return show;
                    }
                }
            }
        }
        return null;
    }

    public List<Seat> displayAvailableSeats(Show show) {
        if (show == null) {
            System.out.println("Invalid show object provided.");
            return new ArrayList<>();
        }
        return show.getHall().getAvailableSeats(show.getDate());
    }

    public List<Seat> displayAndPrintAvailableSeats(Show show) {
        if (show == null) {
            System.out.println("Invalid show object provided.");
            return null;
        }
        List<Seat> availableSeats = displayAvailableSeats(show);
        printAvailableSeats(availableSeats, show);
        return availableSeats;
    }

    private void printAvailableSeats(List<Seat> seats, Show show) {
        if (show == null) {
            System.out.println("Invalid show object provided.");
            return;
        }
        if (seats.isEmpty()) {
            System.out.println("No available seats for show ID: " + show.getShowID());
        } else {
            System.out.println("Available seats for show ID: " + show.getShowID() + ":");
            for (Seat seat : seats) {
                System.out.println("Seat ID: " + seat.getSeatID() + " (Available)");
            }
        }
    }
    
}
