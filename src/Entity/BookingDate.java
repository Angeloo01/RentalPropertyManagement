package Entity;

public class BookingDate{
Date start;
Date end;

BookingDate(Date s, Date e){
    start = s;
    end = e;
}
Date getStart(){
    return start;
}
Date getEnd(){
    return end;
}
}