import java.time.LocalDate;
import java.util.*;

public class Tark1 {
    public static void main(String[] args) {
        Train t = new Train();
        City c = new City();
//      CoachType ct=new CoachType(1400); //You do not have to instantiate enum objects like this. Enum objects are created at the time of declaration, see below.
        Coach c3 = new Coach();
        Seat s = new Seat();

        //Also, here is the sample code to create a train object in a very rudimentary way. We can optimize it a lot, but for now, this can be used as a starting point.

        String trainNumber = "12345";
        String[] cities = new String[]{"Rajkot", "Ahmedabad", "Surat", "Mumbai"};
        int[] distance = new int[]{0, 200, 500, 800};
        String[] coaches = new String[]{"S1", "S2", "S3", "B1", "B2", "A1", "H1"};
        int[] seatsPerCoach = new int[]{70, 60, 55, 50, 40, 30, 20};

        //Here, we are creating a train object with the train number 12345
        Train rajkotToMumbaiTrain = new Train();
        rajkotToMumbaiTrain.trainno = "12345";

        //Here, we are adding cities to the train object. We are also adding the distance of each city from the source city Rajkot.
        for (int i = 0; i < cities.length; i++) {
            City city = new City();
            city.name = cities[i];
            city.dist = distance[i];
            rajkotToMumbaiTrain.cities.add(city);
        }

        //Here, we are adding coaches to the train object. We are also adding seats to each coach.
        for (int i = 0; i < coaches.length; i++) {
            Coach coach = new Coach();

            String coachName = coaches[i];

            coach.coachName = coachName;
            if(coachName.startsWith("S")) {
                coach.coachType = CoachType.Sleeper;
            } else if(coachName.startsWith("B")) {
                coach.coachType = CoachType.TIER_3;
            } else if(coachName.startsWith("A")) {
                coach.coachType = CoachType.TIER_2;
            } else if(coachName.startsWith("H")) {
                coach.coachType = CoachType.TIER_1;
            }

            coach.seats = new ArrayList<>();
            for (int j = 0; j < seatsPerCoach[i]; j++) {
                Seat seat = new Seat();
                seat.seatNo = coach.coachName + "-" + (j + 1);
                seat.ReservedDates = new ArrayList<>();
                coach.seats.add(seat);
            }
            rajkotToMumbaiTrain.coaches.add(coach);
        }

        //Now print all the details to validate the train object.
        System.out.println("Train Number: " + rajkotToMumbaiTrain.trainno);
        for (City city : rajkotToMumbaiTrain.cities) {
            System.out.println("City: " + city.name + ", Distance: " + city.dist);
        }

        for (Coach coach : rajkotToMumbaiTrain.coaches) {
            System.out.println("Coach: " + coach.coachName + ", Coach Type: " + coach.coachType + ", Total Seats: " + coach.seats.size());
            for (Seat seat : coach.seats) {
                System.out.print(seat.seatNo + " ");
            }
            System.out.println();
        }
    }
}

class City {
    String name;
    int dist;
}

enum CoachType {
    Sleeper(1), //Here, a new enum object is created with the name Sleeper and fareperkm=1
    TIER_3(2), //Here, another enum object is created with the name TIER_3 and fareperkm=2
    TIER_2(3), //Similarly, another enum object is created with the name TIER_2 and fareperkm=3
    TIER_1(4);

    int fareperkm;

    CoachType(int fare) {
        this.fareperkm = fare;
    }

    int calculateFare(int distance, int passenger) {
        return this.fareperkm * distance * passenger;
    }
}

class Coach {
    String coachName;
    CoachType coachType;
    List<Seat> seats;

}

class Train {
    String trainno;
    List<City> cities = new ArrayList<>();
    List<Coach> coaches = new ArrayList<>();
}

class Seat {
    String seatNo;
    List<Date> ReservedDates = new ArrayList<>();
}

class Date {
    LocalDate date;
    City source;
    City destination;

    Date(LocalDate date) {
        this.date = date;
        this.source = source;
        this.destination = destination;
    }
}
