package designchallenge1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class EventList implements PopupAdapter {
    public JScrollPane scrollPane;
    public JButton Add, Ok;
    public JList eventList;
    public JColorChooser colorChooser;
    public JFrame eventListFrame;
    public Container eventListPane;
    public JPanel eventPanel;
    PopupAdapter popupAdapter;
    ArrayList<CalendarEvent> events;

    int month, day, year;

    public EventList() {
        this.events = new ArrayList<>();

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
                popupAdapter = new EventAdder();
                popupAdapter.open();

                ((EventAdder) popupAdapter).setDay(day);
                ((EventAdder) popupAdapter).setNumMonth(month-1);
                ((EventAdder) popupAdapter).setYear(year);
            }
        });
        Add.setBounds(50, 450, 80, 20);

        Ok = new JButton("Ok");
        Ok.setVisible(true);
        Ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("O");
                //System.out.println ("EVENTSIZE: " + ((EventAdder)popupAdapter).getEvents().size());
                if (((EventAdder) popupAdapter).getEvents() != null) {
                    System.out.println ("Not null");
                    /*if (!((EventAdder)popupAdapter).getEvents().isEmpty()) {
                        System.out.print("not Empty");
                        /*events.addAll(((EventAdder) popupAdapter).getEvents());
                        for (int i = 0; i < events.size(); i++)
                            System.out.println("kaka: " + i + " - " + events.get(i).getHoliday());
                        setEvents(events);*/
                    //}
                    //System.out.print("Empty");
                }
                System.out.println ("null");

                eventListFrame.setVisible(false);
            }
        });
        Ok.setBounds(150, 450, 80, 20);


        eventListFrame = new JFrame("Events");
        eventListFrame.setSize(300, 530);
        eventListFrame.setLayout(null);
        eventListPane = eventListFrame.getContentPane();
        eventListPane.setLayout(null);

        eventPanel = new JPanel(null);
        eventPanel.setBorder(BorderFactory.createTitledBorder(""));
        eventPanel.setBounds(0, 0, 300, 500);

        eventListPane.add(eventPanel);
        eventPanel.add(scrollPane);
        eventPanel.add(Add);
        eventPanel.add(Ok);
    }

    public void open() {
        eventListFrame.setVisible(true);
        eventPanel.setVisible(true);
        eventList.setVisible(true);
    }

    public void setEvents (ArrayList<CalendarEvent> events) {
        this.events = events;
        for (int i =0; i<events.size(); i++)
            System.out.println ("koko: " + i + " - " + events.get(i).getHoliday());
        ArrayList<String> eventName = new ArrayList<>();
        //ArrayList<Color> colorEventAdder = new ArrayList<>();
        for (CalendarEvent e: events) {
            eventName.add(e.getHoliday());
            //colorEventAdder.add(e.getColor());
        }

        eventList.setListData(eventName.toArray());
        eventList.setCellRenderer(new ListRender());
        //eventList.setCellRenderer(new MyListCellThing());

        //for (int i = 0; i < events.size(); i++)
        //    System.out.println ("HOI: " + events.get(i).getHoliday());
    };

    public ArrayList<CalendarEvent> getEvent() {
        System.out.println ("in");
        System.out.println(events.size());
        for (int i =0; i<events.size(); i++)
            System.out.println ("kiki: " + i + " - " + events.get(i).getHoliday());
        return events;
    }

    public void setDate (int month, int day, int year)
    {
        this.month = month;
        this.day = day;
        this.year = year;

        String date = month + "/" + day + "/" + year;
        System.out.println ("setdate: " + date);
        eventPanel.setBorder(BorderFactory.createTitledBorder(date));
    }

    class ListRender extends DefaultListCellRenderer
    {
        public Component getListCellRendererComponent (JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            for (int i=0; i<events.size(); i++) {
                if (value.equals(events.get(i).getHoliday()))
                    setForeground(events.get(i).getColor());
            }
            return this;
        }
    }
}
