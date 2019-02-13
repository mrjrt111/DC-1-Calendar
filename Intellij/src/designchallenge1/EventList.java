package designchallenge1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class EventList implements PopupAdapter {
    public JScrollPane scrollPane;

    public JButton Add, Edit;
    public JList eventList;

    public JColorChooser colorChooser;
    public JFrame frame;
    public Container pane;
    public JPanel eventPanel;

    PopupAdapter Popup;
    ArrayList<CalendarEvent> events = new ArrayList<>();

    int month, day, year;

    public EventList() {

        eventList = new JList (events.toArray());
        eventList.setVisible(true);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //eventList.setBounds(15, 25, 260, 400);
        eventList.setPreferredSize(new Dimension (260, 400));

        scrollPane = new JScrollPane(eventList);
        scrollPane.setBounds (15, 25, 260, 400);

        Add = new JButton("Add");
        Add.setVisible(true);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup = new EventAdder();
                Popup.open();

                ((EventAdder)Popup).setDay(day);
                ((EventAdder)Popup).setNumMonth(month-1);
                ((EventAdder)Popup).setYear(year);
            }
        });
        Add.setBounds(50, 450, 80, 20);

        Edit = new JButton("Delete");
        Edit.setVisible(true);
        Edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        Edit.setBounds(150, 450, 80, 20);


        frame = new JFrame("Events");
        frame.setSize(300, 530);
        frame.setLayout(null);
        pane = frame.getContentPane();
        pane.setLayout(null);

        eventPanel = new JPanel(null);
        eventPanel.setBorder(BorderFactory.createTitledBorder(""));
        eventPanel.setBounds(0, 0, 300, 500);

        pane.add(eventPanel);
        eventPanel.add(scrollPane);
        eventPanel.add(Add);
        eventPanel.add(Edit);
    }

    public void open() {
        frame.setVisible(true);
        eventPanel.setVisible(true);
        eventList.setVisible(true);
    }

    public void setEvents (ArrayList<CalendarEvent> events) {
        this.events = events;
        ArrayList<String> eventName = new ArrayList<>();
        for (CalendarEvent e: events)
            eventName.add(e.getHoliday());

        eventList.setListData(eventName.toArray());

        //for (int i = 0; i < events.size(); i++)
        //    System.out.println ("HOI: " + events.get(i).getHoliday());
    };

    public void setDate (int month, int day, int year)
    {
        this.month = month;
        this.day = day;
        this.year = year;

        String date = month + "/" + day + "/" + year;
        System.out.println ("setdate: " + date);
        eventPanel.setBorder(BorderFactory.createTitledBorder(date));
    }
}
