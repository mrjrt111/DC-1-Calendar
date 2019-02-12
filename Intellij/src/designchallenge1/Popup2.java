package designchallenge1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Popup2 implements PopupAdapter{
    public JButton Add, Edit;
    public JList eventList;

    public JColorChooser colorChooser;
    public JFrame frame;
    public Container pane;
    public JPanel eventPanel;

    PopupAdapter Popup;
    ArrayList<CalendarEvent> events = new ArrayList<>();

    public Popup2 () {
        eventList = new JList (events.toArray());
        eventList.setListData(events.toArray());
        for (int i = 0; i < events.size(); i++)
            System.out.println ("HOI: " + events.get(i).getHoliday());
        eventList.setVisible(true);
        eventList.setBounds(15, 25, 260, 400);

        Add = new JButton("Add");
        Add.setVisible(true);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Popup = new Popup();
                Popup.open();
            }
        });
        Add.setBounds(50, 450, 80, 20);

        Edit = new JButton("Edit");
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
        eventPanel.setBorder(BorderFactory.createTitledBorder("Color"));
        eventPanel.setBounds(0, 0, 300, 500);

        pane.add(eventPanel);
        eventPanel.add(eventList);
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
        for (int i = 0; i < events.size(); i++)
            System.out.println ("Holiday: " + events.get(i).getHoliday());
        System.out.println ("SIZE: " + events.size());
    };
}
