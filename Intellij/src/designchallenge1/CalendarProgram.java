/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge1;

/**
 *
 * @author Arturo III
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class CalendarProgram{

	/**** Day Components ****/
	public int yearBound, monthBound, dayBound, yearToday, monthToday;

	/**** Swing Components ****/
	public JLabel monthLabel, yearLabel;
	public JButton btnPrev, btnNext;
	public JComboBox cmbYear;
	public JFrame frmMain;
	public Container pane;
	public JScrollPane scrollCalendarTable;
	public JPanel calendarPanel;

	/**** Calendar Table Components ***/
	public JTable calendarTable;
	public DefaultTableModel modelCalendarTable;

	/**NEW ATTRIBUTES**/
	ArrayList<CalendarEvent> loadedEvents;
	ArrayList <CalendarEvent> createdEvents;

	boolean hasConstructed;

	/** Event List **/
	public JScrollPane scrollPane;
	public JButton Add, Ok;
	public JList eventList;
	public JColorChooser colorChooser;
	public JFrame eventListFrame;
	public Container eventListPane;
	public JPanel eventPanel;
	PopupAdapter popupAdapter;

	/**Event Adder**/
	public JScrollPane eventAdderScrollPane;

	public JTextField JTextFieldEventAdder;
	public JButton okEventAdderButton, cancelEventAdderButton;

	public JColorChooser colorChooserEventAdder;
	public JFrame frameEventAdder;
	public JPanel colorPanelEventAdder;


	public Color colorEventAdder;
	public int rgbEventAdder;


	/** Method used to change month and year**/
	public void refreshCalendar(int month, int year)
	{
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int nod, som, i, j;

		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		if (month == 0 && year <= yearBound-10)
			btnPrev.setEnabled(false);
		if (month == 11 && year >= yearBound+100)
			btnNext.setEnabled(false);

		monthLabel.setText(months[month]);
		monthLabel.setBounds(320-monthLabel.getPreferredSize().width/2, 50, 360, 50);

		cmbYear.setSelectedItem(""+year);

		for (i = 0; i < 6; i++)
			for (j = 0; j < 7; j++)
				modelCalendarTable.setValueAt(null, i, j);

		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);

		for (i = 1; i <= nod; i++)
		{
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;
			modelCalendarTable.setValueAt(i, row, column);
		}

		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer());
		System.out.println("Refreshed\nMonth" + month + "\nYear"+ year);
		if (hasConstructed)
			insertEventNumber(month, year);


	}

	public CalendarProgram()
	{

		hasConstructed = false;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {}



		frmMain = new JFrame ("Calendar Application");
		frmMain.setSize(660, 750);
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		monthLabel = new JLabel ("January");
		yearLabel = new JLabel ("Change year:");
		cmbYear = new JComboBox();
		btnPrev = new JButton ("<<");
		btnNext = new JButton (">>");
		modelCalendarTable = new DefaultTableModel()
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				return true;
			}
		};

		calendarTable = new JTable(modelCalendarTable);


		scrollCalendarTable = new JScrollPane(calendarTable);
		calendarPanel = new JPanel(null);

		calendarPanel.setBorder(BorderFactory.createTitledBorder("Calendar"));

		btnPrev.addActionListener(new btnPrev_Action());
		btnNext.addActionListener(new btnNext_Action());
		cmbYear.addActionListener(new cmbYear_Action());

		pane.add(calendarPanel);
		calendarPanel.add(monthLabel);
		calendarPanel.add(yearLabel);
		calendarPanel.add(cmbYear);
		calendarPanel.add(btnPrev);
		calendarPanel.add(btnNext);
		calendarPanel.add(scrollCalendarTable);

		calendarPanel.setBounds(0, 0, 640, 670);
		monthLabel.setBounds(320-monthLabel.getPreferredSize().width/2, 50, 200, 50);
		yearLabel.setBounds(20, 610, 160, 40);
		cmbYear.setBounds(460, 610, 160, 40);
		btnPrev.setBounds(20, 50, 100, 50);
		btnNext.setBounds(520, 50, 100, 50);
		scrollCalendarTable.setBounds(20, 100, 600, 500);

		frmMain.setResizable(false);
		frmMain.setVisible(true);

		GregorianCalendar cal = new GregorianCalendar();
		dayBound = cal.get(GregorianCalendar.DAY_OF_MONTH);
		monthBound = cal.get(GregorianCalendar.MONTH);
		yearBound = cal.get(GregorianCalendar.YEAR);
		monthToday = monthBound;
		yearToday = yearBound;

		String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
		for (int i=0; i<7; i++){
			modelCalendarTable.addColumn(headers[i]);
		}

		calendarTable.getParent().setBackground(calendarTable.getBackground()); //Set background

		calendarTable.getTableHeader().setResizingAllowed(false);
		calendarTable.getTableHeader().setReorderingAllowed(false);

		calendarTable.setColumnSelectionAllowed(true);
		calendarTable.setRowSelectionAllowed(true);
		calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		calendarTable.setRowHeight(76);
		modelCalendarTable.setColumnCount(7);
		modelCalendarTable.setRowCount(6);

		for (int i = yearBound-100; i <= yearBound+100; i++)
		{
			cmbYear.addItem(String.valueOf(i));
		}



		try {
			ParserAbstract csvReader = new CsvReader("Sample Files/Philippine Holidays.csv");
			ParserAbstract psvReader = new psvReader("Sample Files/DLSU Unicalendar.psv");

			InterpreterAdapter adapter = new InterpreterAdapter(((CsvReader)csvReader).getContent());
			loadedEvents = adapter.dataToCalendarEvents();
			InterpreterAdapter adapter2 = new InterpreterAdapter(((psvReader)psvReader).getContent());
			//System.out.println(adapter.dataToCalendarEvents().size());
			System.out.println("adapter2 " + adapter2.dataToCalendarEvents().size());
			for (int i = 0; i<adapter2.dataToCalendarEvents().size(); i++)
				loadedEvents.add(adapter2.dataToCalendarEvents().get(i));

			//loadedEvents.addAll(adapter.dataToCalendarEvents());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Files to be loaded not found");
		}
		refreshCalendar (monthBound, yearBound); //Refresh calendar
		insertEventNumber(monthBound, yearBound);

		hasConstructed = true;

		calendarTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent evt)
			{
				int col = calendarTable.getSelectedColumn();
				int row = calendarTable.getSelectedRow();

				String str = String.valueOf(modelCalendarTable.getValueAt(row, col));
				String[] days = str.split(" ");
				String day = days[0];

				showEventList(Integer.valueOf(day), monthBound, yearBound);
				EventToCalendar sort = new EventToCalendar(loadedEvents);
				loadedEvents = sort.eventsInMonth(monthBound, yearBound);
				ArrayList<CalendarEvent> same = new ArrayList<>();

				for (int i = 0; i< loadedEvents.size(); i++)
					if (Integer.valueOf(day) == loadedEvents.get(i).getDay()) {
						same.add(loadedEvents.get(i));
					}



				eventPanel.setBorder(BorderFactory.createTitledBorder(Integer.toString(monthBound+1)+"/"+Integer.valueOf(day)+"/"+yearToday));
				setEvents(same);

			}
		});

		frmMain.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing (WindowEvent e)
			{
				try {
					new MDYEventColorCsvWriter("saveFile.csv", loadedEvents);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});

		DayChecker dayChecker = new DayChecker(loadedEvents);
		dayChecker.checkEvents();
	}


	class btnPrev_Action implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			if (monthToday == 0)
			{
				monthToday = 11;
				yearToday -= 1;
			}
			else
			{
				monthToday -= 1;
			}
			refreshCalendar(monthToday, yearToday);
		}
	}
	class btnNext_Action implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			if (monthToday == 11)
			{
				monthToday = 0;
				yearToday += 1;
			}
			else
			{
				monthToday += 1;
			}
			refreshCalendar(monthToday, yearToday);
		}
	}
	class cmbYear_Action implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			if (cmbYear.getSelectedItem() != null)
			{
				String b = cmbYear.getSelectedItem().toString();
				yearToday = Integer.parseInt(b);
				refreshCalendar(monthToday, yearToday);
			}
		}
	}

	private void insertEventNumber(int month, int year)
	{
		int nod, som, eventCounter;
		EventToCalendar eventToCalendar = new EventToCalendar(loadedEvents);
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);
		for (int i = 1; i <= nod; i++)
		{
			eventCounter = 0;
			int row = (i+som-2)/7;
			int column  =  (i+som-2)%7;
			System.out.println("pass");
			for (int j = 0; j<eventToCalendar.eventsInMonth(month, year).size(); j++)
			{
				if (i == eventToCalendar.eventsInMonth(month, year).get(j).getDay())
					eventCounter++;
			}
			if (eventCounter>0)
				modelCalendarTable.setValueAt(i + "  Events: "+ eventCounter, row, column);
			else
			modelCalendarTable.setValueAt(i, row, column);
		}

	}


	public void showEventList (int day, int month, int year)
	{
		eventList = new JList (loadedEvents.toArray());
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
				showEventAdder(day, month, year);
			}
		});
		Add.setBounds(50, 450, 80, 20);

		Ok = new JButton("Ok");
		Ok.setVisible(true);
		Ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eventListFrame.setVisible(false);
				refreshCalendar(month, year);
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

		eventListFrame.setVisible(true);
		eventPanel.setVisible(true);
		eventList.setVisible(true);

		int monthDisplay = monthBound+1;
	}


	public void setEvents(ArrayList<CalendarEvent> loadedEvents)
	{
		for (int i = 0; i< loadedEvents.size(); i++)
			System.out.println ("koko: " + i + " - " + loadedEvents.get(i).getHoliday());
		ArrayList<String> eventName = new ArrayList<>();
		//ArrayList<Color> colorEventAdder = new ArrayList<>();
		for (CalendarEvent e: loadedEvents) {
			eventName.add(e.getHoliday());
			//colorEventAdder.add(e.getColor());
		}

		eventList.setListData(eventName.toArray());
		eventList.setCellRenderer(new CalendarProgram.ListRender());
	}
	class ListRender extends DefaultListCellRenderer
	{
		public Component getListCellRendererComponent (JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			for (int i = 0; i< loadedEvents.size(); i++) {
				if (value.equals(loadedEvents.get(i).getHoliday()))
					setForeground(loadedEvents.get(i).getColor());
			}
			return this;
		}
	}

	public void showEventAdder (int day, int month, int year)
	{
		JTextFieldEventAdder = new JTextField("Enter Event", JLabel.BOTTOM);
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
				//cEvent.add(new CalendarEvent(month, day, year, JTextFieldEventAdder.getText(), colorEventAdder));
				System.out.println(loadedEvents.size());
				loadedEvents.add (new CalendarEvent(month, day, year, JTextFieldEventAdder.getText(), colorEventAdder));
				System.out.println(month+"/"+day+"/"+year);
				eventListFrame.setVisible(false);
				refreshCalendar(month, year);
				DayChecker dayChecker = new DayChecker(loadedEvents);
				dayChecker.checkEvents();
			}
		});
		okEventAdderButton.setBounds(50, 400, 80, 30);

		cancelEventAdderButton = new JButton("Cancel");
		cancelEventAdderButton.setVisible(true);
		cancelEventAdderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameEventAdder.setVisible(false);
				eventListFrame.setVisible(false);

			}
		});
		cancelEventAdderButton.setBounds(150, 400, 80, 30);

		colorChooserEventAdder = new JColorChooser(Color.BLACK);
		colorChooserEventAdder.setBounds(10, 65, 550, 300);
		colorChooserEventAdder.setBorder(null);
		colorChooserEventAdder.getSelectionModel().addChangeListener(new CalendarProgram.ColorSelection());

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

		colorChooserEventAdder.setVisible(true);
		frameEventAdder.setVisible(true);
		colorPanelEventAdder.setVisible(true);
	}

	class ColorSelection implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			colorEventAdder = colorChooserEventAdder.getColor();
			rgbEventAdder = colorEventAdder.getRGB();
			JTextFieldEventAdder.setForeground(colorEventAdder);
		}
	}

}


