import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Train {

  long trainno;
  String source,dest;
  int soudist,destdist;
  String[] coaches;
  int[] availseats;
  int len;
Map<LocalDate,int[]>seatMatrix=new HashMap<>();
 Train(long t,String s,String d,int sd,int dd,String[] c,int[] a){
    this.trainno=t;
    this.source=s;
    this.dest=d;
    this.soudist=sd;
    this.destdist=dd;
    this.coaches=c;
    this.availseats=a;
}
void disp()
    {
        
        System.out.println("The train "+this.trainno+" is available from "+this.source+" to "+this.dest);
   }
void manage(LocalDate date)
    {
    seatMatrix.put(date, new int[4]);
        for(int i=0;i<coaches.length;i++)
        {
            if(coaches[i].charAt(0)=='S')
            {
                seatMatrix.get(date)[0] = availseats[i];
            }
            else if(coaches[i].charAt(0)=='A')
            {
                seatMatrix.get(date)[1] = availseats[i];
            }
            else if(coaches[i].charAt(0)=='B')
            {
                seatMatrix.get(date)[2] = availseats[i];
            }
            else 
            {
                seatMatrix.get(date)[3] = availseats[i];
            }
        }
    }   
    
    
    void book(String source,String dest,LocalDate date,String coa,int num,int ticknum)
    {
            if (coa.charAt(0) == 'S' && (seatMatrix.get(date)[0]>=num))
            {
                seatMatrix.get(date)[0]-=num;
                System.out.println(ticknum+" "+Math.abs(this.soudist-this.destdist)*1*num);
            }
            else if(coa.charAt(0) == '3' && (seatMatrix.get(date)[1]>=num))
            {
                seatMatrix.get(date)[1]-=num;
                System.out.println(ticknum+" "+Math.abs(this.soudist-this.destdist)*2*num);
            } 
            else if(coa.charAt(0) == '2' && (seatMatrix.get(date)[2]>=num))
            {
                seatMatrix.get(date)[2]-=num;
                System.out.println(ticknum+" "+Math.abs(this.soudist-this.destdist)*3*num);
            }
            else if(coa.charAt(0) == '1' && (seatMatrix.get(date)[3]>=num))
            {
                seatMatrix.get(date)[3]-=num;
                System.out.println(ticknum+" "+Math.abs(this.soudist-this.destdist)*4*num);
            }
            else 
            {
                System.out.println("Not enough seats available!Try with a lesser number");
            }    
        
    }
public static void main(String[] args) {
        int ticknum=10000001;
        String p[]={"S1","S2","B1","A1","H1"};
        String s[]={"S1","S2","S3","B1","B2"};
        int q[]={72,72,72,48,24};
        int r[]={15,20,50,36,48};
        LocalDate date1 = LocalDate.of(2023, 6, 16);//This info comes from database
        LocalDate date2 = LocalDate.of(2023, 10, 21);


        Train t1 = new Train(17726, "Rajkot","Mumbai",0,750,p,q);
        Train t2=new Train(37392,"Ahmedabad","Surat",0,300,s,r);
        System.out.println("Welcome to the Railways!");
        t1.disp();
        t2.disp();
        t1.manage(date1);
        t2.manage(date2);
        
        while(true)
        {
            Scanner sc=new Scanner(System.in);
            System.out.println("Write book for booking else exit for exit");
            String in=sc.nextLine(),out="exit";

            if(in.equals(out))
            {
                System.out.println("Thank You for choosing Indian Railways");
                System.out.println("We hope to serve you again some day");
                break;
            }
            else
            {
                System.out.println("Please enter the booking details in the format(in space-separated form):");
                System.out.println("Source Destination Date(YYYY/MM/DD) Coach Number-of-passengers");
                System.out.println("The following is the convention for respective type of coach");
                System.out.println("SL-Sleeper");
                System.out.println("3A-3 Tier AC");
                System.out.println("2A-2 Tier AC");
                System.out.println("1A-First Class AC");
                String input=sc.nextLine();
                String[] inputs=input.split(" ");
                String source=inputs[0];
                String dest=inputs[1];
                LocalDate date=LocalDate.parse(inputs[2]);
                String coa=inputs[3];
                int num=Integer.parseInt(inputs[4]);
                
                // System.out.println("The following display the details of available trains");
                if(t1.source.equals(source) && t1.dest.equals(dest))
                {
                    t1.book(source,dest,date,coa,num,ticknum);
                    ticknum++;
                    
                }
                else if(t2.source.equals(source) && t2.dest.equals(dest))
                {
                    t2.book(source,dest,date,coa,num,ticknum);
                    ticknum++;
                }
                else
                {
                    System.out.println("No such trains available");
                }
            }
        }
        
    }
}
//output
Welcome to the Railways!
The train 17726 is available from Rajkot to Mumbai
The train 37392 is available from Ahmedabad to Surat
Write book for booking else exit for exit
book
Please enter the booking details in the format(in space-separated form):
Source Destination Date(YYYY/MM/DD) Coach Number-of-passengers
The following is the convention for respective type of coach
SL-Sleeper
3A-3 Tier AC
2A-2 Tier AC
1A-First Class AC
Rajkot Mumbai 2023-06-16 3A 40
10000001 60000
Write book for booking else exit for exit
book
Please enter the booking details in the format(in space-separated form):
Source Destination Date(YYYY/MM/DD) Coach Number-of-passengers
The following is the convention for respective type of coach
SL-Sleeper
3A-3 Tier AC
2A-2 Tier AC
1A-First Class AC
Rajkot Mumbai 2023-06-16 3A 40
Not enough seats available!Try with a lesser number
Write book for booking else exit for exit
book
Please enter the booking details in the format(in space-separated form):
Source Destination Date(YYYY/MM/DD) Coach Number-of-passengers
The following is the convention for respective type of coach
SL-Sleeper
3A-3 Tier AC
2A-2 Tier AC
1A-First Class AC
Ahmedabad Surat 2023-10-21 SL 40
10000003 12000
Write book for booking else exit for exit
book
Please enter the booking details in the format(in space-separated form):
Source Destination Date(YYYY/MM/DD) Coach Number-of-passengers
The following is the convention for respective type of coach
SL-Sleeper
3A-3 Tier AC
2A-2 Tier AC
1A-First Class AC
Ahmedabad Surat 2023-10-21 SL 40
Not enough seats available!Try with a lesser number
Write book for booking else exit for exit
book
Please enter the booking details in the format(in space-separated form):
Source Destination Date(YYYY/MM/DD) Coach Number-of-passengers
The following is the convention for respective type of coach
SL-Sleeper
3A-3 Tier AC
2A-2 Tier AC
1A-First Class AC
Ahmedabad Rajkot 2023-10-21 SL 40
No such trains available
Write book for booking else exit for exit
exit
Thank You for choosing Indian Railways
We hope to serve you again some day
