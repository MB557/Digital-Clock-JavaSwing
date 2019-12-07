package digitalClock;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

@SuppressWarnings("serial")
public class StopWatch extends JFrame implements ActionListener,Runnable
{
 JLabel disp;
 JButton btn;
 boolean stop=false;
 int i,j,k,l;
 public StopWatch()
 {
  disp=new JLabel();
  disp.setHorizontalAlignment((int) CENTER_ALIGNMENT);
  disp.setForeground(Color.GREEN);
  disp.setBackground(new Color(51,51,51));
  disp.setOpaque(true);
  disp.setFont(new Font("Chess", Font.BOLD, 100));
  
  btn=new JButton("Start");
  btn.setLocation(0,0);
  Container c=getContentPane();
  c.setLayout(new FlowLayout());
  c.setBackground(new Color(51,51,51));
  c.setVisible(true);
  c.add(disp); c.add(btn);
  btn.addActionListener(this);
 }
 public void run()
 {
  for(i=0;;i++)
  {
   for(j=0;j< 60;j++)
   {
    for(k=0;k< 60;k++)
    {
     for(l=0;l< 100;l++)
     {
      if(stop)
      {
       break;
      }
      NumberFormat nf = new DecimalFormat("00");
      disp.setText(nf.format(i)+":"+nf.format(j)+":"+nf.format(k)+":"+nf.format(l));
      try{
       Thread.sleep(10);
      }catch(Exception e){}
     }
    }
   }
  }
 }
 public void actionPerformed(ActionEvent ae)
 {
  Thread t=new Thread(this);
  if(ae.getActionCommand().equals("Start"))
  {
   t.start();
   btn.setText("Stop");
  }
  else
  {
   stop=true;
  }
 }
 public static void main(String[] args) 
 {
  StopWatch s=new StopWatch();
  /*s.setSize(500,100);
  s.setVisible(true);
  s.setTitle("StopWatch");
  s.setForeground(Color.GREEN);
  s.setBackground(new Color(51,51,51));
  s.setFont(new Font("Chess", Font.BOLD, 100));
  s.setDefaultCloseOperation(EXIT_ON_CLOSE);
  */
  s.setLayout(new FlowLayout());
  s.setSize(700,300);
  s.setBackground(new Color(51,51,51));
  s.setVisible(true);
  s.setTitle("StopWatch");
 }
}