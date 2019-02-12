package designchallenge1;

import designchallenge1.CalendarEvent;
import designchallenge1.ObserverInterface;
import sms.SMS;
import sms.SMSView;

import java.util.ArrayList;
import java.util.Calendar;

public class SmsController implements ObserverInterface {
    @Override
    public void update(ArrayList<CalendarEvent> event)
    {
        SMSView view = new SMSView();
        Calendar calendar = Calendar.getInstance();
        for (CalendarEvent e:event) {
            calendar.set(e.getYear(), e.getMonth() - 1, e.getDay());
            view.sendSMS(new SMS(e.getHoliday(), calendar, e.getColor()));
            /*System.out.println("Current Calendar's Year: " + calendar.get(Calendar.YEAR));
            System.out.println("Current Calendar's Day: " + calendar.get(Calendar.MONTH));
            System.out.println("Current Calendar's Day: " + calendar.get(Calendar.DATE));
            System.out.println("Current MINUTE: " + calendar.get(Calendar.MINUTE));
            System.out.println("Current SECOND: " + calendar.get(Calendar.SECOND));*/
        }

    }
}
