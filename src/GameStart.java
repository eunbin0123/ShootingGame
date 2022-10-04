
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class GameStart extends JFrame {   
   private MyPanel panel = new MyPanel();
   Container GSCon = getContentPane();
   
   
   class Timer extends Thread {
      private JLabel timerLabel;
      
      int count = 3;
      int time;
      String timer;
    
      
      public Timer(JLabel timerLabel) {
         this.timerLabel = timerLabel;
      
      }

      @Override
      public void run() {
         for (time = 3; time >= 0; time--) {
       
            timerLabel.setText(Integer.toString(time));
          
            try {
               Thread.sleep(1200);
               System.out.println(time);
               count --;
               System.out.println(count);
               
               if(count == 1) {
                  System.out.println("end");    
                  dispose();
                  new MyFrame();
                  
               }
            } catch (InterruptedException e) {
               return;
            }
         }
      }

   }
   class MyPanel extends JPanel {
     
      private ImageIcon icon = new ImageIcon("image/space5.jpg");
      private Image img = icon.getImage();

      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

      }
      
      
      public MyPanel() {
         setLayout(null);
         JLabel gsLbl = new JLabel("GAME START!!");
         gsLbl.setBounds(80, 130, 100, 40);
         gsLbl.setSize(350, 50);
         gsLbl.setForeground(new Color(143, 188, 143));
         gsLbl.setFont(gsLbl.getFont().deriveFont(30.0f));
         add(gsLbl);
         JLabel timerLabel = new JLabel();
         timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
         timerLabel.setForeground(Color.white);
         
         
         add(timerLabel);
         
         int a;
         
         
         Timer th = new Timer(timerLabel);
         
         timerLabel.setBounds(160, 140, 300, 300);
         
         
        th.start();
         
      }
      
      
   }

   public GameStart() {
      setTitle("Shooting Game");
      setVisible(true);
      setBounds(500, 500, 400, 600);
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension screenSize = tk.getScreenSize();// 화면사이즈
      setLocation(screenSize.width / 2 - 300, screenSize.height / 2 - 300);// 화면 중앙에 창 띄우기
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setContentPane(panel);
      
     
      
      
   }

   public static void main(String[] args) {
      new GameStart();

   }

}