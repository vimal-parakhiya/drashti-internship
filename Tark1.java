import java.time.LocalDate;
import java.util.*;

public class Tark1 {
    public static void main(String[] args) {
      
        
    }
}

class City {
    String name;
    String dist;
}
enum CoachType{
    Sleeper(1),TIER_3(2),TIER_2(3),TIER_1(4);

    int fareperkm;
    CoachType(int fare)
    {
        this.fareperkm=fare;
    }
    int calculateFare(int distance,int passenger)
    {
        return this.fareperkm*distance*passenger;
    }
}
 class Coach{
    String coachName;
    CoachType coachType;
    List<Seat>seat;

}
class Train{
    String trainno;
    List<City> cities = new ArrayList<>();
    List<Coach>coaches;
}
class Seat{
    int seatNo;
    List<Date>ReservedDates;
}  
class Date{
    LocalDate date;
    City source;
    City destination;

    Date(LocalDate date)
    {
        this.date=date;
        this.source=source;
        this.destination=destination;
    }
}