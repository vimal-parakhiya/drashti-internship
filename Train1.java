import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Milestone1.Train;

public class Train1 {
String trainno;
  String[] stop;
  int[] dist;
  String[] coaches;
  int[] availseats;
  Map<LocalDate,int[]>trackInd=new HashMap<>();
Map<LocalDate,int[]>seatMatrix=new HashMap<>();//to keep a seatmatrix of SL 1A 2A 3A
Map<LocalDate,int[]>seat=new HashMap<>();//to keep the track of source and destination of the user of a train with multiple stops
Map<Integer,String[]>bookedtkt=new HashMap<>();//to keep a track of all booking details for later printing
int fair;
int c1=0,c2=0,c3=0,c4=0;//variables will count how many coach names per coach type
Train1(String t,String[] st,int[] dist,String[] c,int[] a){
    this.trainno=t;
    this.stop=st;
    this.dist=dist;
    this.coaches=c;
    this.availseats=a;
}

int totalseats()
{
    int sum=0;
    for(int i=0;i<this.availseats.length;i++)
    {
        sum+=this.availseats[i];
    }
    return sum;
}
void disp()
{
    System.out.print("The train "+this.trainno+" is available from ");
    for(int i=0;i<this.stop.length;i++)
    {
        System.out.print(stop[i]+" ");
    }
    System.out.println();
}

void manage(LocalDate date)
    {
        if (!this.seatMatrix.containsKey(date)) 
        { 
            this.seatMatrix.put(date, new int[4]);
            for(int i=0;i<coaches.length;i++)
                {
                      if(coaches[i].charAt(0)=='S')
                      {
                          seatMatrix.get(date)[0] += availseats[i];
                          c1++;
                      }
                      else if(coaches[i].charAt(0)=='A')
                      {
                          seatMatrix.get(date)[1] += availseats[i];
                          c2++;
                      }
                      else if(coaches[i].charAt(0)=='B')
                      {
                          seatMatrix.get(date)[2] += availseats[i];
                          c3++;
                      }
                      else 
                      {
                          seatMatrix.get(date)[3] += availseats[i];
                          c4++;
                      }
                }
                int[] dummyarr=new int[this.stop.length-1];
                for(int i=0;i<stop.length-1;i++)
                {
                    dummyarr[i]=totalseats();
                }
                this.seat.put(date,new int[this.stop.length-1]);
                seat.put(date,dummyarr);
        }
        int[] arr=new int[c1+c2+c3+c4];
        for(int i=0;i<arr.length;i++)
        {
            arr[i]=0;
        }
        if(!this.trackInd.containsKey(date))
        {
            this.trackInd.put(date,arr);

        }
    } 
    int srcind,destind;
    int fare;
    boolean checkTrain(String source,String dest)
    {
        int c=0;
        for(int i=0;i<this.stop.length;i++)
        {
            if(this.stop[i].equals(source))
            {
                this.srcind=i;
                c++;
            }
            if(this.stop[i].equals(dest))
            {
                this.destind=i;
                c++;
            }
        }
        if(c==2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    boolean canBook(LocalDate date,String coa,int num)
    {
        int check=0;
        for(int j=this.srcind;j<this.destind;j++)
        {
            if(this.seat.get(date)[j]>=num)
            {
                check++;
            }
            else
            {
                break;
            }
        }

            if(check==this.destind-this.srcind)
            {
                if (coa.charAt(0)== 'S' && (seatMatrix.get(date)[0]>=num))
                {
                    return true;
                }
                else if(coa.charAt(0) == '3' && (seatMatrix.get(date)[1]>=num))
                {
                    return true;
                } 
                else if(coa.charAt(0) == '2' && (seatMatrix.get(date)[2]>=num))
                {
                    return true;
                }
                else if(coa.charAt(0) == '1' && (seatMatrix.get(date)[3]>=num))
                {
                    return true;
                }
                else 
                {
                    return false;
                }
            }   
            else
            {
                return false;
            }
    }
    boolean book(String source,String dest,LocalDate date,String coa,int num,int ticknum)
    {
       
            for(int j=srcind;j<destind;j++)
            {
                this.seat.get(date)[j]-=num;
            }
            if (coa.charAt(0)== 'S')
            {
                System.out.println(seatMatrix.get(date)[0]-=num);
                this.fare=Math.abs(this.dist[srcind]-this.dist[destind])*1*num;
                System.out.println(ticknum+" "+this.fare);
                return true;
            }
            
            else if(coa.charAt(0) == '3')
            {
                seatMatrix.get(date)[1]-=num;
                this.fare=Math.abs(this.dist[srcind]-this.dist[destind])*2*num;
                System.out.println(ticknum+" "+this.fare);
                return true;
            } 
            else if(coa.charAt(0) == '2')
            {
                seatMatrix.get(date)[2]-=num;
                this.fare=Math.abs(this.dist[srcind]-this.dist[destind])*3*num;
                System.out.println(ticknum+" "+this.fare);
                return true;
            }
            else if(coa.charAt(0) == '1')
            {
                seatMatrix.get(date)[3]-=num;
                this.fare=Math.abs(this.dist[srcind]-this.dist[destind])*4*num;
                System.out.println(ticknum+" "+this.fare);
                return true;
            }
            else 
            {
                System.out.println("Please enter a valid coach among given coaches in the reservation system");
                return false;
            }
    }
    String getSeat(LocalDate date,String coa,int num)
    {
        String seatNo="";
        int[] arr = trackInd.get(date);
        if (coa.charAt(0)== 'S')
        {
            for(int i=0;i<c1;i++)
            {
                if(arr[i]<availseats[i] && this.availseats[i]-arr[i]>=num)
                {
                    for(int j=arr[i]+1;j<=num;j++)
                    {
                        seatNo+=" S"+(i+1)+"-"+j+" ";
                    }
                    arr[i]+=num;
                    break;
                }
                else
                {
                    int newnum=availseats[i]-arr[i];
                    for(int j=arr[i]+1;j<=availseats[i];j++)
                    {
                        seatNo+=" S"+ (i+1)+"-"+j+" ";
                    }
                    arr[i]+=newnum;
                    num-=newnum;
                    continue;
                }
            }
        trackInd.put(date,arr);   
        }
        else if(coa.charAt(0) == '3')
        {
            for(int k=0;k<c2;k++)
            {
                for(int i=c1;i<c1+c2;i++)
                {
                    if(arr[i]<availseats[i] && availseats[i]-arr[i]>=num)
                    {
                        for(int j=arr[i]+1;j<=num;j++)
                        {
                            seatNo+=" B"+(k+1)+"-"+j+" ";
                        }
                        arr[i]+=num;
                        break;
                    }
                    else
                    {
                        int newnum=availseats[i]-arr[i];
                        for(int j=arr[i]+1;j<=availseats[i];j++)
                        {
                            seatNo+=" B"+ (k+1)+"-"+j+" ";
                        }
                        arr[i]+=newnum;
                        num-=newnum;
                        continue;
                    }
                }
            }
        trackInd.put(date,arr);   
        }
        else if(coa.charAt(0) == '2')
        {
            for(int k=0;k<c3;k++)
            {
                for(int i=c1+c2;i<c1+c2+c3;i++)
                {
                    if(arr[i]<availseats[i] && availseats[i]-arr[i]>=num)
                    {
                        for(int j=arr[i]+1;j<=num;j++)
                        {
                            seatNo+=" A"+(k+1)+"-"+j+" ";
                        }
                        arr[i]+=num;
                        break;
                    }
                    else
                    {
                        int newnum=availseats[i]-arr[i];
                        for(int j=arr[i]+1;j<=availseats[i];j++)
                        {
                            seatNo+=" A"+ (k+1)+"-"+j+" ";
                        }
                        arr[i]+=newnum;
                        num-=newnum;
                        continue;
                    }
                }
            }
        trackInd.put(date,arr);  
        }
        else //if(coa.charAt(0) == '1')
        {
            for(int k=0;k<c4;k++)
            {
                for(int i=c1+c2+c3;i<c1+c2+c3+c4;i++)
                {
                    if(arr[i]<availseats[i] && availseats[i]-arr[i]>=num)
                    {
                        for(int j=arr[i]+1;j<=num;j++)
                        {
                            seatNo+=" H"+(k+1)+"-"+j+" ";
                        }
                        arr[i]+=num;
                        break;
                    }
                    else
                    {
                        int newnum=availseats[i]-arr[i];
                        for(int j=arr[i]+1;j<=availseats[i];j++)
                        {
                            seatNo+=" H"+ (k+1)+"-"+j+" ";
                        }
                        arr[i]+=newnum;
                        num-=newnum;
                        continue;
                    }
                }
            }
        trackInd.put(date,arr); 
        }
    return seatNo;    
    }
    void generatePNR(int ticknum,String trainnum)
    {
        System.out.print(trainnum+" ");
        String[] details = this.bookedtkt.get(ticknum);
        for(int i=0;i<details.length;i++)
        {
            System.out.print(details[i]+" ");
        }
    }
  

    public static void main(String[] args) {
        int ticknum=10000001;
        String[] stop1={"Rajkot","Ahmedabad","Vadodra","Surat","Mumbai"};
        String[] stop2={"Ahmedabad","Anand","Vadodra","Bharuch","Surat"};
        String[] stop3={"Vadodra","Dahod","Indore"};
        int[] dist1={0,200,300,500,750};
        int[] dist2={0,50,100,200,300};
        int[] dist3={0,150,350};
        String coach1[]={"S1","S2","B1","A1","H1"};
        String coach2[]={"S1","S2","S3","B1","B2"};
        String coach3[]={"S1","S2","B1","A1"};
        int numofseats1[]={72,72,72,48,24};
        int numofseats2[]={15,20,50,36,48};
        int numofseats3[]={72,72,72,48};
        Map<Integer,String[]>bookedtkt=new HashMap<>();
        
        Train1[] trains = new Train1[3];
        trains[0] = new Train1("17726",stop1,dist1,coach1,numofseats1);
        trains[1] =new Train1("37392",stop2,dist2,coach2,numofseats2);
        trains[2]=new Train1("29772",stop3,dist3,coach3,numofseats3);
        System.out.println("Welcome to the Railways!");
        for (Train1 train : trains) {
            train.disp();
        }
        
        while(true)
        {
            Scanner sc=new Scanner(System.in);
            System.out.println("Write BOOK for booking");
            System.out.println("Write PNR for ticket details");
            System.out.println("Write REPORT for report of booked tickets");
            System.out.println("Write EXIT to exit");
            
            String in=sc.nextLine();

            if(in.equals("EXIT"))
            {
                System.out.println("Thank You for choosing Indian Railways");
                System.out.println("We hope to serve you again!");
                break;
            }
            else if(in.equals("REPORT"))
            {
                if(ticknum!=10000001)
                {
                    for (Integer key : bookedtkt.keySet()) {
                        System.out.print(key+" ");
                        System.out.print(bookedtkt.get(key)[2]+" ");
                        System.out.print(bookedtkt.get(key)[4]+" ");
                        System.out.print(bookedtkt.get(key)[0]+" ");
                        System.out.print(bookedtkt.get(key)[1]+" ");
                        System.out.print(bookedtkt.get(key)[3]+" ");
                        System.out.print(bookedtkt.get(key)[5]);
                        System.out.println();
                    }
                    
                }
                else
                {
                    System.out.println("No booked tickets found! Book a ticket to get data!");
                }
            System.out.println();
            }
            else if(in.equals("PNR"))
            {
                if(ticknum==10000001)
                {
                    System.out.println("No booked tickets found.Book a ticket now!");
                }
                else
                {
                    System.out.println("Enter the PNR number for booking details");
                    int inp=sc.nextInt();
                    sc.nextLine();
                    if(bookedtkt.containsKey(inp))
                    {
                        String[] details = bookedtkt.get(inp);
                        System.out.print(details[4]+" ");
                        System.out.print(details[0]+" ");
                        System.out.print(details[1]+" ");
                        System.out.print(details[2]+" ");
                        System.out.print(details[3]+" ");
                        System.out.print(details[5]);

                    } 
                    else
                    {
                        System.out.println("There is no such PNR for booked ticket");
                    }
                }
            System.out.println();
            }
            else if(in.equals("BOOK"))
            {
                int check=0;
                int check1=0;
                int flag1=0;
                int flag2=0;
                System.out.println("Please enter the booking details in the format(in space-separated form):");
                System.out.println("Source Destination Date(YYYY-MM-DD) Coach Number-of-passengers");
                System.out.println("The following is the convention for respective type of coach");
                System.out.println("SL-Sleeper");
                System.out.println("3A-3 Tier AC");
                System.out.println("2A-2 Tier AC");
                System.out.println("1A-First Class AC");
                String input=sc.nextLine();
                String[] inputs=input.split(" ");
                if(inputs.length!=5)
                {
                    System.out.println("The input was invalid! Try again with the proper format instructed!");
                }
                else{
                    String source=inputs[0];
                    String dest=inputs[1];
                    LocalDate date=LocalDate.parse(inputs[2]);
                    String coa=inputs[3];
                    int num=Integer.parseInt(inputs[4]);
                    for (Train1 train : trains) {
                        train.manage(date);
                    }
                    for(Train1 train:trains)
                    {
                        if(train.checkTrain(source,dest)==true && train.canBook(date, coa, num)==true)
                        {
                            check++;
                            System.out.println(train.trainno);
                        }
                        else if(train.checkTrain(source, dest)==false)
                        {
                            flag1++;
                        }
                        else if(train.canBook(date, coa, ticknum)==false)
                        {
                            flag2++;
                        }
                    }
                    String trainnum="";
                    if(check!=0)
                    {
                        System.out.println();
                        System.out.println("Enter the train number which you want to book");
                        trainnum=sc.nextLine();
                        for (Train1 train : trains) 
                        {
                            if(train.trainno.equals(trainnum) && train.book(source,dest,date,coa,num,ticknum)==true)
                            {
                                String[] result = {source, dest, date.toString(),String.valueOf(train.fare),trainnum,train.getSeat(date,coa,num)};
                                bookedtkt.put(ticknum, result);
                                check1++;
                                ticknum++;
                                System.out.println("Ticket Booked Successfully!");
                            }
                        }
                        if(check1==0)
                        {
                            System.out.println("Enter a valid train number from the given available train numbers");
                        }
                    }    
                    else
                    {
                        if(flag1==trains.length)
                        {
                            System.out.println("There are not enough seats available for the details provided.Try with a lesser number");
                        }
                        else if(flag2==trains.length)
                        {
                            System.out.println("No train is not available for given source and destination");
                        }
                        else
                        {
                            System.out.println("Either number of seats not available or source and destination both invalid.Try any one alternative to book tickets!");
                        }
                    }
                    System.out.println();
                }
               
            }
        else
        {
            System.out.println("Please enter a valid keyword for respective operation as listed below!");
        }
        } 
    }
}



