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
        for (CalendarEvent e:event)
            view.sendSMS(new SMS(e.getHoliday(), calendar, e.getColor() ));

    }
}
