import java.util.List;

public class Main {
    public static void main(String[] args){
        Cinema cinemaA = new Cinema("FamilyTime", "123 Main Street");
        Cinema cinemaB = new Cinema("Welax", "777 Broadway Street");
        
        Movie movie1 = new Movie("The Best Man", 2.5f);
        Movie movie2 = new Movie("Why Women Kill", 2.0f);
        Movie movie3 = new Movie("The Sad Spirits", 2.5f);
        Movie movie4 = new Movie("Sophie's Friends", 1.5f);

        Platform platform = new Platform();
        platform.addCinema(cinemaA);
        platform.addCinema(cinemaB);

        int showIDCounter=0;

        //initialize cinemaA
        Hall hall_a1 = new Hall(0, 5, cinemaA);
        Hall hall_a2 = new Hall(1, 3, cinemaA);
        for(int day=0; day<3; day++){
            hall_a1.addShow(new Show(showIDCounter++, 11.0f, day, movie2, hall_a1));
            hall_a1.addShow(new Show(showIDCounter++, 14.0f, day, movie1, hall_a1));
            hall_a1.addShow(new Show(showIDCounter++, 17.0f, day, movie4, hall_a1));
            hall_a1.addShow(new Show(showIDCounter++, 20.0f, day, movie3, hall_a1));
            hall_a2.addShow(new Show(showIDCounter++, 11.0f, day, movie1, hall_a2));
            hall_a2.addShow(new Show(showIDCounter++, 14.0f, day, movie2, hall_a2));
            hall_a2.addShow(new Show(showIDCounter++, 17.0f, day, movie3, hall_a2));
            hall_a2.addShow(new Show(showIDCounter++, 20.0f, day, movie4, hall_a2));
        }
        cinemaA.addHall(hall_a1);
        cinemaA.addHall(hall_a2);


        //initialize cinemaB
        Hall hall_b1 = new Hall(0, 4, cinemaB);
        Hall hall_b2 = new Hall(1, 2, cinemaB);
        for(int day=0; day<3; day++){
            hall_b1.addShow(new Show(showIDCounter++, 11.0f, day, movie3, hall_b1));
            hall_b1.addShow(new Show(showIDCounter++, 14.0f, day, movie1, hall_b1));
            hall_b1.addShow(new Show(showIDCounter++, 17.0f, day, movie4, hall_b1));
            hall_b1.addShow(new Show(showIDCounter++, 20.0f, day, movie2, hall_b1));
            hall_b2.addShow(new Show(showIDCounter++, 11.0f, day, movie4, hall_b2));
            hall_b2.addShow(new Show(showIDCounter++, 14.0f, day, movie2, hall_b2));
            hall_b2.addShow(new Show(showIDCounter++, 17.0f, day, movie3, hall_b2));
            hall_b2.addShow(new Show(showIDCounter++, 20.0f, day, movie1, hall_b2));
        }
        cinemaB.addHall(hall_b1);
        cinemaB.addHall(hall_b2);
        
        // platform.printAllShows();
        platform.printSelectedShow("The Best Man");
        Show selectedShow = platform.findShowByID(17);  //17 is the user input
        List<Seat> availableSeats = platform.displayAndPrintAvailableSeats(selectedShow);
        int[] seatIdsChosen = {1,2};
        platform.bookSeats(selectedShow, seatIdsChosen, selectedShow.getDate());
        List<Seat> availableSeatsAfter = platform.displayAndPrintAvailableSeats(selectedShow);


        //another search
        Show selectedShow2 = platform.findShowByID(9);  //9 is the user input
        List<Seat> availableSeats2= platform.displayAndPrintAvailableSeats(selectedShow2);
    }
}
