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
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalendarProgram{
	/*CHANGE*/
	PopupAdapter Popup, Popup2;
	/*CHANGE*/

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
	ArrayList<CalendarEvent> events;
	boolean hasConstructed;


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
			CsvReader csvReader = new CsvReader("C:\\Users\\user\\Desktop\\DC-1-Calendar\\Intellij\\Sample Files\\Philippine Holidays.csv");

			CSVInterpreterAdapter adapter = new CSVInterpreterAdapter(csvReader.getContent());
			events = adapter.dataToCalendarEvents();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No CSV file found");
		}
		refreshCalendar (monthBound, yearBound); //Refresh calendar
		insertEventNumber(monthBound, yearBound);
		DayChecker dayChecker = new DayChecker(events);
		//System.out.println("sIZE" + events.size());
		dayChecker.checkEvents();
		hasConstructed = true;

		/*CHANGE*/
		Popup = new EventAdder();
		Popup2 = new EventList();
		/*CHANGE*/

		calendarTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent evt)
			{
				int col = calendarTable.getSelectedColumn();
				int row = calendarTable.getSelectedRow();

				/*CHANGE*/
				//EventAdder.open();
				String str = String.valueOf(modelCalendarTable.getValueAt(row, col));
				//System.out.println("VAL OF str: "+ str);
				String[] days = str.split(" ");
				String day = days[0];
				//System.out.println("VAL OF day: "+ day);
				//((EventAdder)EventAdder).setDay((int)modelCalendarTable.getValueAt(row, col));
				((EventAdder)Popup).setDay(Integer.valueOf(day));
				((EventAdder)Popup).setMonth(monthLabel.getText());
				//((EventAdder)EventAdder).setYear(yearBound);


				Popup2.open();
				EventToCalendar sort = new EventToCalendar(events);
				events = sort.eventsInMonth(((EventAdder)Popup).getMonth(), yearBound);
				ArrayList<CalendarEvent> same = new ArrayList<>();

				for (int i = 0; i<events.size(); i++)
					if (((EventAdder)Popup).getDay() == events.get(i).getDay()) {
						//System.out.println ("event: " + events.get(i).getDay());
						same.add(events.get(i));
					}



				/** JARRETT CHANGE**/

				((EventList) Popup2).setDate(monthToday+1, Integer.valueOf(day), yearToday);
				((EventList)Popup2).setEvents(same);
				//System.out.println("same " + same.size());

				/**CHANGE END**/
				//System.out.println (same.get(0).getHoliday());


				/*System.out.println ((int)modelCalendarTable.getValueAt(row, col));
				System.out.println (monthLabel.getText());
				System.out.println (yearBound);*/
				/*CHANGE*/
			}
		});
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
		EventToCalendar eventToCalendar = new EventToCalendar(events);
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);
		//System.out.println("SIZE " + eventToCalendar.eventsInMonth(month, year).size());
		for (int i = 1; i <= nod; i++)
		{
			eventCounter = 0;
			int row = (i+som-2)/7;
			int column  =  (i+som-2)%7;
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


}
