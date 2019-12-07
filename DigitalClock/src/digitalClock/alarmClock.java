package digitalClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class alarmClock extends Thread implements ItemListener, ActionListener{
	
	JFrame aClock;
	Digitaclock dc;
	JButton start = new JButton("Start");
	JButton cancel = new JButton("cancel");
	JPanel mainPanel = new JPanel();
	JPanel display = new JPanel();
	JPanel Buttons = new JPanel(new FlowLayout());
	JPanel remTimePanel = new JPanel(new FlowLayout());
	
	JComboBox hour = new JComboBox();
	JComboBox minute = new JComboBox();
	JComboBox second = new JComboBox();
	JComboBox ampmSelect = new JComboBox();
	JLabel timeRemLabel = new JLabel();
	
	private int hourVal;
	private int minVal;
	private int secVal;
	private int snoozeVal;
	private int format;
	private int ampm = 0;
	boolean bool;
	boolean cancelFlag = false;
	boolean interruptFlag = false;
	
	public alarmClock(Digitaclock dc, boolean bool, int hourVal, int minVal, int secVal){
		this.dc = dc;
		this.format = dc.hourFormat;
		for(int i = 0 ; i < 60 ; i++) {
			if(i < format)
				hour.addItem(i);
			
			minute.addItem(i);
			second.addItem(i);
		}
		this.hour.setSelectedItem((int)hourVal);
		this.minute.setSelectedItem((int)minVal);
		this.second.setSelectedItem((int)secVal);
		ampmSelect.addItem("AM");
		ampmSelect.addItem("PM");
		
		aClock = new JFrame();
		aClock.setResizable(false);
		aClock.getContentPane().setBackground(new Color(51, 51, 51));
		aClock.getContentPane().setLayout(null);
		aClock.setBackground(Color.BLACK);
		aClock.setTitle("Alarm Clock");
		aClock.setBounds(100, 100, 700, 300);
		aClock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aClock.add(mainPanel);
		mainPanel.setBounds(10,10,680,200);
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		display.setLayout(new BoxLayout(display,BoxLayout.X_AXIS));
		
		mainPanel.setBackground(new Color(51, 51, 51));
		display.setBackground(new Color(51, 51, 51));
		Buttons.setBackground(new Color(51, 51, 51));
		remTimePanel.setBackground(new Color(51, 51, 51));
		
		mainPanel.add(display);
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(Buttons);
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(remTimePanel);
		
		display.add(hour);
		display.add(Box.createHorizontalStrut(10));
		display.add(minute);
		display.add(Box.createHorizontalStrut(10));
		display.add(second);
		display.add(Box.createHorizontalStrut(10));
		display.add(ampmSelect);
		
		
		hour.setForeground(Color.BLACK);
		hour.setAlignmentX(Component.CENTER_ALIGNMENT);
		Dimension d = new Dimension();
		d.setSize(60, 60);
		hour.setMaximumSize(d);
		hour.setMinimumSize(d);
		this.hourVal = hourVal;
		
		minute.setForeground(Color.BLACK);
		minute.setAlignmentX(Component.CENTER_ALIGNMENT);
		minute.setMaximumSize(d);
		minute.setMinimumSize(d);
		this.minVal = minVal;
		
		second.setForeground(Color.BLACK);
		second.setAlignmentX(Component.CENTER_ALIGNMENT);
		second.setMaximumSize(d);
		second.setMinimumSize(d);
		this.secVal = secVal;
		
		ampmSelect.setForeground(Color.BLACK);
		ampmSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
		ampmSelect.setMaximumSize(d);
		ampmSelect.setMinimumSize(d);
		if(format == 12)
			this.ampm = 0;
		else
		{
			ampmSelect.setVisible(false);
		}
		
		Buttons.add(start);
		Buttons.add(cancel);
		cancel.setVisible(false);
		cancel.disable();
		
		remTimePanel.add(timeRemLabel);
		timeRemLabel.setVisible(false);
		timeRemLabel.setForeground(Color.GREEN);
		timeRemLabel.setBackground(new Color(51,51,51));
		
		hour.addItemListener(this);
		minute.addItemListener(this);
		second.addItemListener(this);
		start.addActionListener(this);
		cancel.addActionListener(this);
		this.bool = bool;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start) {
			bool = true;
			if(interruptFlag)
				this.run();
			else
				this.start();
						
		}
		else if(e.getSource() == cancel)
		{	
			alarmClock ac = new alarmClock(dc,false,0,0,0);
			ac.aClock.setVisible(true);
			aClock.setVisible(false);
		}
	}
	
	public void run() {
		start.setVisible(false);
		start.disable();
		cancel.setVisible(true);
		cancel.enable();
		timeRemLabel.setVisible(true);
		timeRemLabel.setText("");
		Date dateCheck = new Date();
		String setTime = "";
		if(dateCheck.getHours()>hourVal)
			setTime = 24+hourVal+":"+minVal+":"+secVal;
		else if(dateCheck.getHours() == hourVal)
		{
			if(dateCheck.getMinutes() > minVal)
				setTime = 24+hourVal+":"+minVal+":"+secVal;
			else if(dateCheck.getMinutes() == minVal)
				{
					if(dateCheck.getSeconds()>secVal)
						setTime = 24+hourVal+":"+minVal+":"+secVal;
					else
						setTime = hourVal+":"+minVal+":"+secVal;
				}
			else
				setTime = hourVal+":"+minVal+":"+secVal;
		}
		else
			setTime = hourVal+":"+minVal+":"+secVal;
		while(bool)
		{
			Date date = new Date();
			if(hourVal == date.getHours() && minVal == date.getMinutes() && secVal == date.getSeconds())
				bool = false;
			SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
			String sysTime = date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
			try 
			{
				Date date1 = form.parse(sysTime);
				Date date2 = form.parse(setTime);
				long remTime = date2.getTime() - date1.getTime();
				long remSec = remTime/1000;
				long remMin = remSec/60;
				long remHour = remMin/60;
				remSec %= 60;
				remMin %= 60;
				timeRemLabel.setText("Time Remaining -> "+remHour+" : "+remMin + " : " + remSec);
			} 
			catch (ParseException e1) 
			{
				e1.printStackTrace();
			}
			
			try 
			{
					Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				e.getMessage();
			}
		}
		if(cancelFlag == true)
			timeRemLabel.setText("Alarm Cancelled");
		else 
		{
				timeRemLabel.setText("TIME UP");
				int opt = JOptionPane.showConfirmDialog(aClock,"Would you like to snooze");
				if(opt == JOptionPane.YES_OPTION)
				{
					bool = true;
					timeRemLabel.setVisible(true);
					timeRemLabel.setText("");
					minVal = minVal + 1;
					if(minVal >= 60)
					{
						minVal %= 60;
						hourVal = (hourVal+1)%24;
					}
					alarmClock ac = new alarmClock(dc,bool,hourVal,minVal,secVal);
					ac.aClock.setVisible(true);
					aClock.setVisible(false);
					ac.start();
				}
		}
		cancel.setText("New");
	}
	
	public static void main(String args[])
	{
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == hour) {
			if(format == 12 && ampm == 1)
				hourVal = ((int)e.getItem())*2;
			else
				hourVal = (int)e.getItem();
		}
		if(e.getSource() == minute)
			minVal = (int)e.getItem();
		
		if(e.getSource() == second)
			secVal = (int)e.getItem();
		
		if(e.getSource() == ampmSelect)
		{
			if((String)e.getItem() == "AM")
				ampm = 0;
			else
				ampm = 1;
		}
		
	}
	
	
}