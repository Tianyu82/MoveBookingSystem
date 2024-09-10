Requirements:
1. Multiple cinemas
2. Each cinema has multiple halls and each hall can run one movie show at a time
3. Each Movie will have multiple shows
4. Customers should be able to search movies by their title
5. Once the customer selects a movie, the service should display the cinemas running that movie and its available shows
6. The service should show the customer the seating arrangement of the cinema hall. The customer should be able to select multiple seats according to their preference
7. The customer should be able to distinguish between available seats and booked ones
8. Assume the platform can only book movies today, tomorrow, and the day after tomorrow


How does the user interact with the system?
Users select the title of the movie that they want to see, and the date
The system returns all the movie shows on that date

Users select a specific show
The system returns the seat arrangement of the show

Users select seats
The system returns a boolean value indicating the success of the booking


What classes does the system need? For simplicity, I will use float to represent the starting and ending time of a movie show

Movie:
    String movieName;
    float length;


Seat:
    Seat(int seatID, boolean isReserved);


Show:
    private int showID;
    private float start;
    private float end;
    private int date;   //0-> today, 1-> tomorrow, 2-> the day after tomorrow ...
    private Movie movie;
    public Show(int showID, float start, Movie movie);


Hall: 
    private int hallID;
    private int capacity;
    private Seat[] seats;
    private PriorityQueue<Show> shows;  //increasing order by the starting time
    private Map<String, List<Show>>; //find shows by their movieName 
    public Hall(int hallID, int capacity);
    public Show addShow(Show show);
    public List<Show> displaySelectedShow(String movieName);


Cinema: 
    private string name;
    private string address;
    private List<Hall> halls;
    public Cinema(String name, String address);
    public Hall addHall(Hall hall);
    public List<Show> displaySelectedShow(String movieName);


Platform: 
    private Map<String, Cinema> cinemas;  //find cinema by its name
    public boolean addCinema(Cinema cinema);
    public void displayAllShows();
    public List<Show> displaySelectedShow(String movieName);


Booking: