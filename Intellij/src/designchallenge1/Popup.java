package designchallenge1;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Popup implements PopupAdapter{
    public JScrollPane scrollPane;

    public JTextField event;
    public JButton ok, cancel;

    public JColorChooser colorChooser;
    public JFrame frame;
    public JPanel colorPanel;

    public Popup () {
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

    class ColorSelection implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            System.out.println ("IN");
            Color color = colorChooser.getColor();
            int rgb = color.getRGB();
            System.out.println (color + ": " + rgb);
            event.setForeground(color);
        }
    }
}
