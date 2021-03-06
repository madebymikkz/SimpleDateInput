/*
 * SimpleDateInput -- a Swing panel that allows the user to enter
 * a date and time, which can be read out as a String
 * Copyright (c) 2016 Michael Wihlborg
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE. 
 */
package madebymikkz.simpledateinput;

import javax.swing.*;
import java.awt.event.*;
import java.time.*;
import java.util.GregorianCalendar;

/**
 * A simple date input component
 * @author Michael Wihlborg
 */
public class SimpleDateInput extends JPanel
{
	// Variables
	private JSpinner yearSpnr, daySpnr, hourSpnr, minSpnr;
	private SpinnerNumberModel dayMdl;
	private JComboBox<String> monthList;
	
	// Constructor
	/**
	 * Sets up the component
	 */
	public SimpleDateInput()
	{
		// Get current time
		LocalDateTime currentTime = LocalDateTime.now();
		
		// Set layout
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Date row
		JPanel dateRow = new JPanel();
		
		JLabel yearLbl = new JLabel("Year:");
		SpinnerNumberModel yearMdl = new SpinnerNumberModel(currentTime.getYear(), 0, 9999, 1);
		yearSpnr = new JSpinner(yearMdl);
		
		JLabel monthLbl = new JLabel("Month:");
		String[] monthArray = { "January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December"};
		monthList = new JComboBox<String>(monthArray);
		monthList.setSelectedIndex(currentTime.getMonthValue() - 1);
		monthList.addActionListener(new monthLnr());
		JLabel dayLbl = new JLabel("Day:");
		dayMdl = new SpinnerNumberModel(currentTime.getDayOfMonth(), 1, 31, 1);
		daySpnr = new JSpinner(dayMdl);
		
		dateRow.add(yearLbl);
		dateRow.add(yearSpnr);
		dateRow.add(monthLbl);
		dateRow.add(monthList);
		dateRow.add(dayLbl);
		dateRow.add(daySpnr);
		
		this.add(dateRow);
		
		// Time row
		JPanel timeRow = new JPanel();
		
		JLabel hourLbl = new JLabel("Hours:");
		SpinnerNumberModel hourMdl = new SpinnerNumberModel(currentTime.getHour(), 0, 23, 1);
		hourSpnr = new JSpinner(hourMdl);
		hourSpnr.setEditor(new JSpinner.NumberEditor(hourSpnr, "00"));
		
		JLabel minLbl = new JLabel("Minutes:");
		SpinnerNumberModel minMdl = new SpinnerNumberModel(currentTime.getMinute(), 0, 59, 1);
		minSpnr = new JSpinner(minMdl);
		minSpnr.setEditor(new JSpinner.NumberEditor(minSpnr, "00"));
		
		timeRow.add(hourLbl);
		timeRow.add(hourSpnr);
		timeRow.add(minLbl);
		timeRow.add(minSpnr);
		
		this.add(timeRow);
	}
	
	// Methods
	/**
	 * Get the currently entered date as a String
	 * @return the entered date in YYYY-MM-DD format
	 */
	public String getDate()
	{
		int year = ((Integer)yearSpnr.getValue()).intValue();
		int month = monthList.getSelectedIndex() + 1;
		int day = ((Integer)daySpnr.getValue()).intValue();
		return String.format("%04d-%02d-%02d", year, month, day);
	}
	
	/**
	 * Get the currently entered time as a String
	 * @return the entered time in HH:MM format
	 */
	public String getTime()
	{
		int hour = ((Integer)hourSpnr.getValue()).intValue();
		int minute = ((Integer)minSpnr.getValue()).intValue();
		return String.format("%02d:%02d", hour, minute);
	}
	
	// Listeners
	/**
	 * Listener class for the month combo box
	 * @author Michael Wihlborg
	 */
	private class monthLnr implements ActionListener
	{
		@Override
		/**
		 * Called when the user selects a month;
		 * sets the maximum day value to its correct value
		 */
		public void actionPerformed(ActionEvent e)
		{
			int month = monthList.getSelectedIndex() + 1;
			int dayLimit = -1;
			
			// Set day limit
			if (month == 2)				// February
			{
				GregorianCalendar c = new GregorianCalendar();
				if (c.isLeapYear(((Integer)yearSpnr.getValue()).intValue()))
					dayLimit = 29;
				else
					dayLimit = 28;
			}
			else
				switch (month)
				{
					case 1:
					case 3:
					case 5:
					case 7:
					case 8:
					case 10:
					case 12:
						dayLimit = 31;
						break;
					case 4:
					case 6:
					case 9:
					case 11:
						dayLimit = 30;
				}
			
			if (dayLimit > 0)
				dayMdl.setMaximum(dayLimit);
		}
	}
}