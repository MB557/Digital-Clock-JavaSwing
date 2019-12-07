package digitalClock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class DigitalClock extends JFrame implements ItemListener 
{
    //private static final long serialVersionUID = 5924880907001755076L;

    JLabel jltime;
    JLabel jl;
    JComboBox<Integer> jcb;
    JButton jbt,jbt2,jbt3,jbt4;
    NumberFormat format;

    public Timer timer;
    public long initial;
    public long ttime2;
    public String ttime;
    public String buff;
    public long remaining;

    public DigitalClock() 
    {
        JPanel timePanel = new JPanel();
        timePanel.setPreferredSize(new Dimension(700, 300));
        timePanel.setBackground(new Color(51,51,51));

        jltime = new JLabel(" ");
        jltime.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jltime.setForeground(Color.GREEN);
        jltime.setBackground(new Color(51,51,51));
        jltime.setOpaque(true);
        jltime.setFont(new Font("Chess", Font.BOLD, 100));

        timePanel.add(jltime);

        JPanel jp1 = new JPanel();
        jp1.setLayout(new FlowLayout());
        jp1.setBackground(new Color(51,51,51));

        jl = new JLabel("TOTAL TIME (minutes):");
        jl.setFont(new Font("Chess", Font.BOLD, 20));
        jl.setForeground(Color.GREEN);
        jp1.add(jl);

        jcb = new JComboBox<Integer>();
        for (int i = 15; i > 0; i--)
        {
            jcb.addItem(Integer.valueOf(i));
        }
        
        jcb.setSelectedIndex(0);
        ttime = "15";
        jbt = new JButton("START");
        jbt2 = new JButton("RESET");
        jbt3 = new JButton("STOP");
        jbt4 = new JButton("RESUME");
        
        jp1.add(jcb);
        jp1.add(jbt);
        jp1.add(jbt2);
        jp1.add(jbt3);
        jp1.add(jbt4);

        getContentPane().add(jp1, BorderLayout.NORTH);
        getContentPane().add(timePanel, BorderLayout.CENTER);

        Event e = new Event();
        jbt.addActionListener(e);
        jbt2.addActionListener(e);
        jbt3.addActionListener(e);
        jbt4.addActionListener(e);

        jcb.addItemListener(this);

        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Timer");
        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                new DigitalClock();
            }
        });
    }

    // this method will run when user presses the start button
    void updateDisplay(int start) 
    {
        Timeclass tc = new Timeclass();
        timer = new Timer(1000, tc);
        
        if(start == 1000)
        	initial = System.currentTimeMillis();
        else 
        	initial = start;
        
        timer.start();
    }

    // code for what happens when user presses the start or reset button
    public class Event implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String bname = e.getActionCommand();
            
            if (bname.equals("START")) 
                updateDisplay(1000);
            else if(bname.equals("STOP"))
            {
                timer.stop();
                buff = jltime.getText();
                System.out.println(buff);
            }
            else if(bname.equals("RESUME"))
            {
            	timer.start();
            	String Split[] = buff.split(":");
            	int Min = Integer.parseInt(Split[0]);
            	int Sec = Integer.parseInt(Split[1]);
            	int time = (Min*60+Sec)*1000;
                updateDisplay(time);
            }
            else 
            {
                jltime.setText("");
                timer.stop();
                remaining = convertTime();
            }
        }
    }

    // code that is invoked by swing timer for every second passed
    public class Timeclass implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
        	System.out.println(initial);
            remaining = convertTime();
            long current = System.currentTimeMillis();
            long elapsed = current - initial;
            remaining -= elapsed;

            format = NumberFormat.getNumberInstance();
            format.setMinimumIntegerDigits(2);

            if (remaining < 0)
                remaining = (long) 0;
            
            int minutes = (int) (remaining / 60000);
            int seconds = (int) ((remaining % 60000) / 1000);
            System.out.println(remaining+" "+minutes+" "+seconds);
            jltime.setText(format.format(minutes) + ":" + format.format(seconds));

            if(remaining == 0)
            {	
                jltime.setText("Time's Up");
                jltime.setForeground(Color.GREEN);
                timer.stop();
            }
        }
    }

    // get the number of minutes chosen by the user and activate convertTime method
    public void itemStateChanged(ItemEvent ie) 
    {
        ttime = (String) jcb.getSelectedItem().toString();
        convertTime();
    }

    // first need to convert no. of minutes from string to long.
    // then need to convert that to milliseconds.
    public long convertTime()
    {
        ttime2 = Long.parseLong(ttime);
        long converted = (ttime2 * 60000) + 1000;
        return converted;
    }
}

