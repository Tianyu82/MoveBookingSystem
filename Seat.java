import java.util.HashMap;
import java.util.Map;

public class Seat {
    int seatID;
    Map<Integer, Boolean> reservations;  // Key is the date, value is whether it is reserved

    public Seat(int seatID) {
        this.seatID = seatID;
        this.reservations = new HashMap<>();
    }

    public int getSeatID(){
        return this.seatID;
    }

    public void reserve(int date) {
        reservations.put(date, true);
    }

    public boolean isReserved(int date) {
        return reservations.getOrDefault(date, false);
    }
}
