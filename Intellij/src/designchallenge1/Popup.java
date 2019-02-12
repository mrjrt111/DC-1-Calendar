package designchallenge1;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Popup implements PopupAdapter{
    public JScrollPane scrollPane;

    public JTextField event;
    public JButton ok, cancel;

    public JColorChooser colorChooser;
    public JFrame frame;
    public JPanel colorPanel;

    public int day = 0,
               month = 0,
               year = 0;
    //public String strMonth;
    public Color color;
    public int rgb;

    DataSavingAbstract CSVWriter;
    //CalendarEvent cEvent;
    ArrayList<CalendarEvent> cEvent = new ArrayList<>();
    ParserAbstract CSVReader;
    SmsController SMS;

    public Popup () {
        this.day = 0;
        this.month = 0;
        this.year = 0;
        //this.strMonth = "";

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
                //cEvent = new CalendarEvent(month, day, year, event.getText(), color);
                cEvent.add(new CalendarEvent(month, day, year, event.getText(), color));

                try {
                    CSVReader = new CsvReader("Events Supreme.csv");
                    System.out.println ("READ");
                } catch (Exception e1) { e1.printStackTrace(); }

                CSVWriter = new MDYEventColorCsvWriter("Events Supreme.csv", cEvent);
                try {
                    CSVWriter.saveData();
                } catch (IOException e1) { e1.printStackTrace(); }

                SMS = new SmsController();
                SMS.update(cEvent);

                System.out.println("Event: " + day + '/' + month + '/' + year);
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

    public void setDay (int day) {
        this.day = day;
    }
    public void setMonth (String month) {
        switch(month) {
            case "January": this.month = 1;
                break;
            case "February": this.month = 2;
                break;
            case "March": this.month = 3;
                break;
            case "April": this.month = 4;
                break;
            case "May": this.month = 5;
                break;
            case "June": this.month = 6;
                break;
            case "July": this.month = 7;
                break;
            case "August": this.month = 8;
                break;
            case "September": this.month = 9;
                break;
            case "October": this.month = 10;
                break;
            case "November": this.month = 11;
                break;
            case "December": this.month = 12;
                break;
            default: this.month = 0;
                break;
        }
    }
    public void setYear (int year) {
        this.year = year;
    }

    class ColorSelection implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            System.out.println ("IN");
            color = colorChooser.getColor();
            rgb = color.getRGB();
            System.out.println (color + ": " + rgb);
            event.setForeground(color);
        }
    }

}
