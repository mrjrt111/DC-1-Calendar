package designchallenge1;

import sms.SMS;
import sms.SMSView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;

public class SmsController implements ObserverInterface {
    @Override
    public void update(ArrayList<CalendarEvent> event)
    {
        SMSView view = new SMSView();
        view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Calendar calendar = Calendar.getInstance();
        view.setTitle("SMS");
        for (CalendarEvent e:event) {
            calendar.set(e.getYear(), e.getMonth(), e.getDay());
            view.sendSMS(new SMS(e.getSchedEvent(), calendar, e.getColor()));


        }

    }
}
