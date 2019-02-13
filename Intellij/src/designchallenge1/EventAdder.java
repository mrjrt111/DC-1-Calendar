package designchallenge1;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.util.ArrayList;
/*POOP*/

public class EventAdder implements PopupAdapter{
    public JScrollPane scrollPane;

    public JTextField event;
    public JButton ok, cancel;

    public JColorChooser colorChooser;
    public JFrame frame;
    public JPanel colorPanel;

    public int day, month, year;
    public Color color;
    public int rgb;

    DataSavingAbstract CSVWriter;
    ArrayList<CalendarEvent> cEvent;
    //ArrayList<CalendarEvent> cEvent = new ArrayList<>();

    SmsController SMS;
    FbController FB;

    public EventAdder() {
        this.day = 0;
        this.month = 0;
        this.year = 0;
        this.cEvent = new ArrayList<>();

        event = new JTextField("Enter event", JLabel.BOTTOM);
        event.setOpaque(true);
        event.setFont(new Font("SansSerif", Font.PLAIN, 12));
        event.setBounds(10, 20, 550, 30);

        ok = new JButton("Ok");
        ok.setVisible(true);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(event.getText());
                frame.setVisible(false);
                //System.out.println("BEFORE: " + day + '/' + month + '/' + year);
                cEvent.add(new CalendarEvent(month, day, year, event.getText(), color));
                for (int i =0; i<cEvent.size(); i++)
                    System.out.println ("Apple: " + cEvent.get(i).getHoliday());
                //System.out.println (cEvent.get(0).getDay());

                /*CSVWriter = new MDYEventColorCsvWriter("Events Supreme.csv", cEvent);
                try {
                    CSVWriter.saveData();
                    System.out.println (cEvent.get(0).getDay());
                } catch (IOException e1) { e1.printStackTrace(); }

                SMS = new SmsController();
                FB = new FbController();

                SMS.update(cEvent);
                FB.update(cEvent);*/

                //System.out.println("Event: " + day + '/' + month + '/' + year);
            }
        });
        ok.setBounds(50, 400, 80, 30);

        cancel = new JButton("Cancel");
        cancel.setVisible(true);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        cancel.setBounds(150, 400, 80, 30);

        colorChooser = new JColorChooser(Color.BLACK);
        colorChooser.setBounds(10, 65, 550, 300);
        colorChooser.setBorder(null);
        colorChooser.getSelectionModel().addChangeListener(new ColorSelection());

        colorPanel = new JPanel (null);
        colorPanel.setBorder(BorderFactory.createTitledBorder("Color"));
        colorPanel.setPreferredSize(new Dimension(605, 765));

        scrollPane = new JScrollPane(colorPanel);
        /*scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);*/
        scrollPane.setBounds(0, 0, 605, 465);

        frame = new JFrame ("Color");
        frame.setSize(620, 500);
        frame.setLayout(null);

        frame.add(scrollPane, BorderLayout.CENTER);

        colorPanel.add(colorChooser);
        colorPanel.add(event);
        colorPanel.add(ok);
        colorPanel.add(cancel);
    }

    public void open() {
        colorChooser.setVisible(true);
        frame.setVisible(true);
        colorPanel.setVisible(true);
    }

    public int getDay (){ return day; }
    public int getMonth(){ return month; }

    public void setDay (int day) {
        this.day = day;
    }
    public void setMonth (String month) {
        switch(month) {
            case "January": this.month = 0;
                break;
            case "February": this.month = 1;
                break;
            case "March": this.month = 2;
                break;
            case "April": this.month = 3;
                break;
            case "May": this.month = 4;
                break;
            case "June": this.month = 5;
                break;
            case "July": this.month = 6;
                break;
            case "August": this.month = 7;
                break;
            case "September": this.month = 8;
                break;
            case "October": this.month = 9;
                break;
            case "November": this.month = 10;
                break;
            case "December": this.month = 11;
                break;
            default: this.month = 0;
                break;
        }
    }

    public void setNumMonth (int month){
        this.month = month;
        //System.out.println ("month: " + month);
    }

    public void setYear (int year) {
        this.year = year;
    }

    public ArrayList<CalendarEvent> getEvents () {
        //System.out.println(cEvent.size());
        for (int i =0; i<cEvent.size(); i++)
            System.out.println ("Banana: " + cEvent.get(i).getHoliday());
        return cEvent;
    }

    class ColorSelection implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            //System.out.println ("IN");
            color = colorChooser.getColor();
            rgb = color.getRGB();
            //System.out.println (color + ": " + rgb);
            event.setForeground(color);
        }
    }

}
