package digitalClock;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

    public class Digitaclock implements ActionListener, ItemListener
    {
        private JFrame frmDigitalClock;
        static timeDisplay var;
        String timeZone = "Asia/Kolkata";
        
        int hourFormat = 24;
        JButton timer = new JButton("Timer");
        JButton stopWatch = new JButton("Stop Watch");
        JButton alarmButton = new JButton("Alarm Clock");
        
        JPanel mainPanel = new JPanel();
        JPanel time = new JPanel();
        JPanel buttons = new JPanel(new FlowLayout());
        
        JLabel timeLabel = new JLabel();
        JLabel dayLabel = new JLabel();
        JLabel dateLabel = new JLabel();
        
        JComboBox formatSelect = new JComboBox();
        
        public static void main(String[] args) 
        {
                Digitaclock window = new Digitaclock();
                window.frmDigitalClock.setVisible(true);
                var = new timeDisplay(window);
                
        }

        public Digitaclock() 
        {
        	formatSelect.addItem(24);
        	formatSelect.addItem(12);
        	frmDigitalClock = new JFrame();
    		frmDigitalClock.setResizable(true);
    		frmDigitalClock.getContentPane().setBackground(new Color(51, 51, 51));
    		frmDigitalClock.getContentPane().setLayout(null);
    		frmDigitalClock.setBackground(Color.BLACK);
    		frmDigitalClock.setTitle("Digital Clock");
    		frmDigitalClock.setBounds(100, 100, 700, 300);
    		frmDigitalClock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		frmDigitalClock.add(mainPanel);
    		mainPanel.setBounds(10,10,680,200);
    		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
    		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    		mainPanel.add(time);
    		mainPanel.add(Box.createVerticalGlue());
    		mainPanel.add(buttons);
    		mainPanel.setBackground(new Color(51, 51, 51));
    		time.setBackground(new Color(51, 51, 51));
    		buttons.setBackground(new Color(51, 51, 51));
    		
    		
    		time.setLayout(new BoxLayout(time,BoxLayout.Y_AXIS));
    		
    		dayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    		dayLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    		dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    		dateLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    		timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    		timeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    		time.add(dayLabel);
    		time.add(timeLabel);
    		time.add(dateLabel);
    		
    		dayLabel.setForeground(Color.GREEN);
    		timeLabel.setForeground(Color.GREEN);
    		dateLabel.setForeground(Color.GREEN);
    		dayLabel.setFont(new Font("ChessType",Font.PLAIN,15));
    		dateLabel.setFont(new Font("ChessType",Font.PLAIN,15));
    		timeLabel.setFont(new Font("ChessType",Font.PLAIN,30));
    		
    		Dimension d = new Dimension();
    		d.setSize(60, 60);
    		formatSelect.setMaximumSize(d);
    		formatSelect.setMinimumSize(d);
    		formatSelect.setSelectedItem((int)24);
    		buttons.add(timer);
    		buttons.add(alarmButton);
    		buttons.add(stopWatch);
    		buttons.add(formatSelect);
    		
    		timer.addActionListener(this);
    		alarmButton.addActionListener(this);
    		formatSelect.addItemListener(this);
        }

		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == timer)
			{
				
			}
			else if(e.getSource() == alarmButton)
			{
				alarmClock var = new alarmClock(this,false,0,0,0);
				var.aClock.setVisible(true);
			}
		}
		
		public void itemStateChanged(ItemEvent e) {
			hourFormat = (int)e.getItem();
			System.out.print(hourFormat);
		}
        
    }

