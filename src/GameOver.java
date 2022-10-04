
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

public class GameOver extends JFrame {
   private MyPanel panel = new MyPanel();
   Container startCon = getContentPane();
   
     JLabel sum; //몬스터 죽인 수 라벨
      JLabel score; //점수 라벨
      JLabel monsterpass;//지나간 몬스터
      JLabel lifebar;//생명바
      JLabel lifebar2;//생명 닳은 표시
      MyFrame mf;
     
      JLabel myscore;
   
      public Clip clip;
     

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

     
   class MyPanel extends JPanel {
      private ImageIcon icon = new ImageIcon("image/space5.jpg");
      private Image img = icon.getImage();
      
      JLabel myscore;
      
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
      }

      public MyPanel() {
         loadAudio("audio/게임오버.wav");
         clip.start();
          MyThread th = new MyThread(sum, score, monsterpass, lifebar2, lifebar, mf, myscore);
          
         setLayout(null);
//         
//         myscore.setText("SCORE : 0");
//         myscore.setBounds(90,180,100,40);
//         myscore.setSize(200,50);
//         myscore.setForeground(Color.BLACK);
//         myscore.setFont(score.getFont().deriveFont(32.0f));
//         add(myscore);
         
         JLabel goLbl = new JLabel("GAME OVER");
         goLbl.setBounds(95, 130, 100, 40);
         goLbl.setSize(350, 50);
         goLbl.setForeground(new Color(143, 188, 143));
         goLbl.setFont(goLbl.getFont().deriveFont(32.0f));
         add(goLbl);
         
         JLabel restartLbl = new JLabel("GAME RESTART?");
         restartLbl.setBounds(130, 230, 100, 40);
         restartLbl.setSize(350, 50);
         restartLbl.setForeground(Color.WHITE);
         restartLbl.setFont(restartLbl.getFont().deriveFont(15.0f));
         add(restartLbl);
         
         JButton restartBtn = new JButton("YES");
         restartBtn.setBounds(130, 290, 100, 40);
         restartBtn.setSize(60, 30);
         restartBtn.setBackground(new Color(95, 158, 160));
         
         JButton exitBtn = new JButton("NO");
         exitBtn.setBounds(200, 290, 100, 40);
         exitBtn.setSize(60, 30);
         exitBtn.setBackground(new Color(95, 158, 160));
         add(exitBtn);
         
         restartBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               dispose();
               new GameStart();
            }
         });
         
         add(restartBtn);
         exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               System.exit(0);
            }
         });
         
         
      }
   }

   public GameOver() {
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