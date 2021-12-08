package Entity;

public class Date{
    private int day, year;
    private String month;

public Date(int d, String m, int y){
day = d;
month = m;
year = y;
}
   public int getDay(){
       return day;
   }
public String getMonth(){
    return month;
}
public int getYear(){
    return year;
}
}