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
import java.time.*;

/**
 * A simple date input component
 * @author Michael Wihlborg
 */
public class SimpleDateInput extends JPanel
{
	// Variables
	private JSpinner yearSpnr, daySpnr, hourSpnr, minSpnr;
	private SpinnerNumberModel dayMdl;
	private JComboBox monthList;
	
	// Constructor
	public SimpleDateInput()
	{
		// Get current time
		LocalDateTime currentTime = LocalDateTime.now();
		
		// Set layout
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Date row
		JPanel dateRow = new JPanel();
		
		JLabel yearLbl = new JLabel("Year:");
		SpinnerNumberModel yearMdl = new SpinnerNumberModel();
		yearMdl.setValue(currentTime.getYear());
		yearSpnr = new JSpinner(yearMdl);
		
		JLabel monthLbl = new JLabel("Month:");
		String[] monthArray = { "January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December"};
		monthList = new JComboBox(monthArray);
		monthList.setSelectedIndex(currentTime.getMonthValue() - 1);
		JLabel dayLbl = new JLabel("Day:");
		dayMdl = new SpinnerNumberModel(currentTime.getDayOfMonth(), 1, 31, 1);
		daySpnr = new JSpinner(dayMdl);
		setAppropriateDayLimit();
		
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
		
		JLabel minLbl = new JLabel("Minutes:");
		SpinnerNumberModel minMdl = new SpinnerNumberModel(currentTime.getMinute(), 0, 59, 1);
		minSpnr = new JSpinner(minMdl);
		
		timeRow.add(hourLbl);
		timeRow.add(hourSpnr);
		timeRow.add(minLbl);
		timeRow.add(minSpnr);
		
		this.add(timeRow);
	}
	
	// Methods
	public String getDate()
	{
	// TODO: Skriv metod
		return null;
	}
	
	private void setAppropriateDayLimit()
	{
	// TODO: Skriv metod
	}
}