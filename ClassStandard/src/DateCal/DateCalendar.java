package DateCal;

import java.util.Calendar;
import java.util.Date;

import static StringCls.StringMain.show;

public class DateCalendar {
 //   static Calendar clr = Calendar.getInstance();
    public static void main(String[] args) {
        addCalendar();
        setCalendar();

    }

    static void addCalendar(){
        Calendar clr = Calendar.getInstance();
        Date tggl = clr.getTime();


        show(String.valueOf(tggl));
        clr.add(Calendar.YEAR, -5);
        clr.add(Calendar.MONTH, -3);
        Date tgl = clr.getTime();
        show(String.valueOf(tgl));


    }

    static void setCalendar(){
        Calendar clr = Calendar.getInstance();
        // Atur tanggal
       Date tggl = clr.getTime();

        show(String.valueOf(tggl));

        clr.set(Calendar.YEAR, 2024);
        clr.set(Calendar.MONTH, Calendar.OCTOBER);
        clr.set(Calendar.HOUR_OF_DAY, 10);
        clr.set(Calendar.DAY_OF_MONTH, 5);
        Date tgl = clr.getTime();
       show(String.valueOf(tgl));


    }
}

// System Class
