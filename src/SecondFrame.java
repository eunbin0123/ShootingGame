
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


public class SecondFrame extends JFrame {
   JButton nextbtn = new JButton("next");
   public static String store;
   public static String store2;
   
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
   String rbtn1Src = "image/rocket6.png";
   ImageIcon rocket1Icon = new ImageIcon("image/rocket6.png"); 
   Image rocket1Img = rocket1Icon.getImage();
   Image rocket1ImgScaled = rocket1Img.getScaledInstance(55, 90, java.awt.Image.SCALE_SMOOTH);
   ImageIcon rocket1IconScaled = new ImageIcon(rocket1ImgScaled);
   JButton rbtn1 = new JButton(rocket1IconScaled); // 로켓 버튼1
   
   String rbtn2Src = "image/rocket1.png";
   ImageIcon rocket2Icon = new ImageIcon("image/rocket1.png"); 
   Image rocket2Img = rocket2Icon.getImage();
   Image rocket2ImgScaled = rocket2Img.getScaledInstance(60, 90, java.awt.Image.SCALE_SMOOTH);
   ImageIcon rocket2IconScaled = new ImageIcon(rocket2ImgScaled);
   JButton rbtn2 = new JButton(rocket2IconScaled); // 로켓 버튼2
   
   String rbtn3Src = "image/rocket7.png";
   ImageIcon rocket3Icon = new ImageIcon("image/rocket7.png"); 
   Image rocket3Img = rocket3Icon.getImage();
   Image rocket3ImgScaled = rocket3Img.getScaledInstance(60, 95,  java.awt.Image.SCALE_SMOOTH);
   ImageIcon rocket3IconScaled = new ImageIcon(rocket3ImgScaled);
   JButton rbtn3 = new JButton(rocket3IconScaled); // 로켓 버튼3
   
   
   String weapon1Src = "fire.png";
   ImageIcon weapon1Icon = new ImageIcon("fire.png"); 
   Image weapon1Img = weapon1Icon.getImage();
   Image weapon1ImgScaled = weapon1Img.getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH);
   ImageIcon weapon1IconScaled = new ImageIcon(weapon1ImgScaled);
   JButton wbtn1 = new JButton(weapon1IconScaled); // 무기버튼1
   
   String weapon2Src = "image/weapon6.png";
   ImageIcon weapon2Icon = new ImageIcon("image/weapon6.png"); 
   Image weapon2Img = weapon2Icon.getImage();
   Image weapon2ImgScaled = weapon2Img.getScaledInstance(95, 95, java.awt.Image.SCALE_SMOOTH);
   ImageIcon weapon2IconScaled = new ImageIcon(weapon2ImgScaled);
   JButton wbtn2 = new JButton(weapon2IconScaled); // 무기버튼2
   
   String weapon3Src = "image/weapon8.png";
   ImageIcon weapon3Icon = new ImageIcon("image/weapon8.png"); 
   Image weapon3Img = weapon3Icon.getImage();
   Image weapon3ImgScaled = weapon3Img.getScaledInstance(95, 95, java.awt.Image.SCALE_SMOOTH);
   ImageIcon weapon3IconScaled = new ImageIcon(weapon3ImgScaled);
   JButton wbtn3 = new JButton(weapon3IconScaled); // 무기버튼3
   
   
   Spanel span = new Spanel();
   
   
   class Spanel extends JPanel {
      
      
      private ImageIcon icon = new ImageIcon("image/space5.jpg");
       private Image img = icon.getImage();
       JLabel chooseR = new JLabel("Choose Your Rocket");
      JLabel chooseW = new JLabel("Choose Your Weapon");
      
       public void paintComponent(Graphics g) {
          super.paintComponent(g);
          g.drawImage(img,0,0,getWidth(), getHeight(), this);
       
       }
       
      
       public Spanel() {
          loadAudio2("audio/3.wav");
           clip2.start();
          setLayout(null);
          chooseR.setBounds(45,20,100,40);
          chooseR.setSize(350,100);
          chooseR.setForeground(new Color(143, 188, 143));
          chooseR.setFont(chooseR.getFont().deriveFont(30.0f));
          add(chooseR);
          
          //로켓버튼1 붙이기
          rbtn1.setBounds(45, 110, 50, 50);
          rbtn1.setSize(100,100);
          rbtn1.setContentAreaFilled(false);
          rbtn1.setBorderPainted(false);
          rbtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
               loadAudio("audio/버튼클릭.wav");
               clip.start();
               SecondFrame.store = rbtn1Src;
            }
         });
          add(rbtn1);
          
          //로켓버튼2 붙이기
          rbtn2.setBounds(145, 110, 50, 50);
          rbtn2.setSize(100,100);
          rbtn2.setContentAreaFilled(false);
          rbtn2.setBorderPainted(false);
          rbtn2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                loadAudio("audio/버튼클릭.wav");
                 clip.start();
               SecondFrame.store = rbtn2Src;
               
            }
         });
          add(rbtn2);
          
          //로켓버튼3 붙이기
          rbtn3.setBounds(245, 110, 50, 50);
          rbtn3.setSize(100,100);
          rbtn3.setContentAreaFilled(false);
          rbtn3.setBorderPainted(false);
          rbtn3.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                loadAudio("audio/버튼클릭.wav");
                 clip.start();
               SecondFrame.store = rbtn3Src;
               
            }
         });
          add(rbtn3);
          
          //"무기고르시오" 라벨
          chooseW.setBounds(35,230,100,40);
          chooseW.setSize(350,100);
          chooseW.setForeground(new Color(143, 188, 143));
          chooseW.setFont(chooseW.getFont().deriveFont(30.0f));
          add(chooseW);
          
          //무기버튼1 붙이기
          wbtn1.setBounds(45, 320, 50, 50);
          wbtn1.setSize(100,100);
          wbtn1.setContentAreaFilled(false);
          wbtn1.setBorderPainted(false);
          wbtn1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                loadAudio("audio/버튼클릭.wav");
                 clip.start();
               SecondFrame.store2 = weapon1Src;
               
            }
         });
          add(wbtn1);
          
          //무기버튼2 붙이기
          wbtn2.setBounds(145, 320, 50, 50);
          wbtn2.setSize(100,100);
          wbtn2.setContentAreaFilled(false);
          wbtn2.setBorderPainted(false);
          wbtn2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                loadAudio("audio/버튼클릭.wav");
                 clip.start();
               SecondFrame.store2 = weapon2Src;
               
            }
         });
          add(wbtn2);
          
          //무기버튼3 붙이기
          wbtn3.setBounds(245, 320, 50, 50);
          wbtn3.setSize(100,100);
          wbtn3.setContentAreaFilled(false);
          wbtn3.setBorderPainted(false);
          wbtn3.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                loadAudio("audio/버튼클릭.wav");
                 clip.start();
               SecondFrame.store2 = weapon3Src;
               
            }
         });
          add(wbtn3);
          
       
           nextbtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    loadAudio("audio/버튼클릭.wav");
                   clip2.stop();

                     clip.start();
                   dispose();
                   new MyFrame();
                }
                
            });
            nextbtn.setBounds(150,480, 100, 50);
            nextbtn.setSize(100,35);
            nextbtn.setBackground(new Color(95, 158, 160));
            add(nextbtn);
      }
       
       
      
   }

   
   public SecondFrame() {
      setTitle("슈팅게임");
      setBounds(500, 500, 400, 600);
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension screenSize = tk.getScreenSize();// 화면사이즈
      setLocation(screenSize.width / 2 - 300, screenSize.height / 2 - 300);// 화면 중앙에 창 띄우기      
      setContentPane(span);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
   
   }

}