
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public  class Start extends JFrame{

   public Clip clip;
    public Clip clip2;

      public void loadAudio(String pathName) {
         try {
            clip = AudioSystem.getClip();
            
            File audioFile = new File(pathName);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip.open(audioStream);
            
         } catch (LineUnavailableException e) {
            e.printStackTrace();
         } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      
      public void loadAudio2(String pathName) {
          try {
             clip2 = AudioSystem.getClip();
             
             File audioFile = new File(pathName);
             AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
             clip2.open(audioStream);
            
          } catch (LineUnavailableException e) {
             e.printStackTrace();
          } catch (UnsupportedAudioFileException e) {
             e.printStackTrace();
          } catch (IOException e) {
             e.printStackTrace();
          }
       }
   
   private MyPanel panel = new MyPanel();
   Container startCon = getContentPane();
   static public String name;
   
   class MyPanel extends JPanel{
      
      private ImageIcon icon = new ImageIcon("image/space5.jpg");
      private Image img = icon.getImage();
      
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.drawImage(img,0,0,getWidth(), getHeight(), this);
      
      }
      
      public MyPanel() {
          loadAudio("audio/2.wav");
          clip.start();
          
         setLayout(null);
         JLabel gameLbl = new JLabel("SHOOTING GAME");
         gameLbl.setBounds(60,130,100,40);
         gameLbl.setSize(350,50);
         gameLbl.setForeground(new Color(143, 188, 143));
         gameLbl.setFont(gameLbl.getFont().deriveFont(30.0f));
         add(gameLbl);
         JLabel headerLbl = new JLabel("<<INPUT YOUR NAME>>");
         headerLbl.setBounds(80,220,100,40);
         headerLbl.setSize(250,40);
         headerLbl.setForeground(Color.WHITE);
         headerLbl.setFont(headerLbl.getFont().deriveFont(18.0f));
         add(headerLbl);
         JLabel nameLbl = new JLabel("NAME : ");
         nameLbl.setBounds(70,270,100,30);
         nameLbl.setForeground(Color.white);
         nameLbl.setFont(nameLbl.getFont().deriveFont(18.0f));
         add(nameLbl);
         JTextField userName = new JTextField();
         userName.setPreferredSize(new Dimension(100,20));
         userName.setBounds(150,270,150,30);
         add(userName);
         JButton okBtn = new JButton("OK");
         okBtn.setBackground(new Color(95, 158, 160));
         add(okBtn);
         okBtn.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent arg0) {
            	   loadAudio2("audio/버튼클릭.wav");
            	   clip2.start();
                     clip.stop();

                  name = userName.getText();
                   dispose();
                   new SecondFrame();
               }
           });

         okBtn.setBounds(140,370,100,40);
         add(okBtn);
      }
   }
   public Start() {
      setTitle("Shooting Game");
      setVisible(true);
      setBounds(500, 500, 400, 600);
       Toolkit tk = Toolkit.getDefaultToolkit();
       Dimension screenSize = tk.getScreenSize();// 화면사이즈
       setLocation(screenSize.width / 2 - 300, screenSize.height / 2 - 300);// 화면 중앙에 창 띄우기
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setContentPane(panel);
     
   }
   


 
}