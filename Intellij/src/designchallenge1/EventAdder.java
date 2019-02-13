package designchallenge1;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/*POOP*/

public class EventAdder implements PopupAdapter{
    public JScrollPane eventAdderScrollPane;

    public JTextField JTextFieldEventAdder;
    public JButton okEventAdderButton, cancelEventAdderButton;

    public JColorChooser colorChooserEventAdder;
    public JFrame frameEventAdder;
    public JPanel colorPanelEventAdder;


    public Color colorEventAdder;
    public int rgbEventAdder;
    public int day, month, year;

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

        JTextFieldEventAdder = new JTextField("Enter JTextFieldEventAdder", JLabel.BOTTOM);
        JTextFieldEventAdder.setOpaque(true);
        JTextFieldEventAdder.setFont(new Font("SansSerif", Font.PLAIN, 12));
        JTextFieldEventAdder.setBounds(10, 20, 550, 30);

        okEventAdderButton = new JButton("Ok");
        okEventAdderButton.setVisible(true);
        okEventAdderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(JTextFieldEventAdder.getText());
                frameEventAdder.setVisible(false);
                //System.out.println("BEFORE: " + day + '/' + month + '/' + year);
                cEvent.add(new CalendarEvent(month, day, year, JTextFieldEventAdder.getText(), colorEventAdder));
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
        okEventAdderButton.setBounds(50, 400, 80, 30);

        cancelEventAdderButton = new JButton("Cancel");
        cancelEventAdderButton.setVisible(true);
        cancelEventAdderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameEventAdder.setVisible(false);
            }
        });
        cancelEventAdderButton.setBounds(150, 400, 80, 30);

        colorChooserEventAdder = new JColorChooser(Color.BLACK);
        colorChooserEventAdder.setBounds(10, 65, 550, 300);
        colorChooserEventAdder.setBorder(null);
        colorChooserEventAdder.getSelectionModel().addChangeListener(new ColorSelection());

        colorPanelEventAdder = new JPanel (null);
        colorPanelEventAdder.setBorder(BorderFactory.createTitledBorder("Color"));
        colorPanelEventAdder.setPreferredSize(new Dimension(605, 765));

        eventAdderScrollPane = new JScrollPane(colorPanelEventAdder);
        /*eventAdderScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        eventAdderScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);*/
        eventAdderScrollPane.setBounds(0, 0, 605, 465);

        frameEventAdder = new JFrame ("Color");
        frameEventAdder.setSize(620, 500);
        frameEventAdder.setLayout(null);

        frameEventAdder.add(eventAdderScrollPane, BorderLayout.CENTER);

        colorPanelEventAdder.add(colorChooserEventAdder);
        colorPanelEventAdder.add(JTextFieldEventAdder);
        colorPanelEventAdder.add(okEventAdderButton);
        colorPanelEventAdder.add(cancelEventAdderButton);
    }

    public void open() {
        colorChooserEventAdder.setVisible(true);
        frameEventAdder.setVisible(true);
        colorPanelEventAdder.setVisible(true);
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
            colorEventAdder = colorChooserEventAdder.getColor();
            rgbEventAdder = colorEventAdder.getRGB();
            //System.out.println (colorEventAdder + ": " + rgbEventAdder);
            JTextFieldEventAdder.setForeground(colorEventAdder);
        }
    }

}
