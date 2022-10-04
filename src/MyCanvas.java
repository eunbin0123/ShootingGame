
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class MyCanvas extends Canvas {
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

   Image buffImage; // 더블버퍼링을위한버퍼

   Graphics buffg; // 더블버퍼링을위한버퍼
   // 비행기 객체 생성하기
   Ball airplane = new Ball(170, 500, 45, 45, 5, 5);
   //보스객체 삭제
   Ball boss;
   //보스총알
   ArrayList<Ball> bossA = new  ArrayList<>();
   //보스이미지 가져오기
   ImageIcon b_icon = new ImageIcon("monster1.png");
   Image b_img = b_icon.getImage();

   // 몬스터 객체 생성하기
   ArrayList<Ball> mon = new ArrayList<>();

   // 별 객체 셍성하기
   ArrayList<Ball> star = new ArrayList<>();

   ArrayList<Ball> bomb = new ArrayList<>();

   ArrayList<Ball> pluslife = new ArrayList<>();
   
   ArrayList<Ball> plus10 = new ArrayList<>();
   
   ArrayList<Ball> minus10 = new ArrayList<>();
   
   ImageIcon icon = new ImageIcon("image/space5.jpg");
   Image img = icon.getImage();

   SecondFrame sc;
   ImageIcon a_icon = new ImageIcon(sc.store);
   Image a_img = a_icon.getImage();

   ImageIcon s_icon = new ImageIcon(sc.store2);
   Image s_img = s_icon.getImage();

   public void paint(Graphics g) {
      // canvas 호출하면 자동적으로 불린다 : 직접 호출은x
      // 강제적으로 paint 호출하고 싶으면 repaint()->update()->paint()
      buffImage = createImage(getWidth(), getHeight());
      buffg = buffImage.getGraphics();
      update(g);
      
       //폭발 이미지 가져오기
     
      }
   
   public void update(Graphics g) {
    Draw();
    g.drawImage(buffImage, 0, 0, this);
   }
   public void Draw() {

         buffg.drawImage(img, 0, 0, getWidth(), getHeight(), this);
         // setBackground(Color.BLACK);
         System.out.println(sc.store);
         // 비행기 이미지 가져오기

         // Image a_img = (Toolkit.getDefaultToolkit()).getImage("temp");
         buffg.drawImage(a_img, airplane.xpos, airplane.ypos, airplane.width, airplane.height, this);
         
         //보스 이미지 그리기 kill score가 150점 이상일때
         if(MyThread.killscore>=200) {   

            //보스 객체 생성하기*
            boss= new Ball(50,18,230,230);
            buffg.drawImage(b_img, boss.xpos, boss.ypos, boss.width, boss.height, this); 
            
            //보스총알 이미지 가져오기
            for (int i = 0; i < bossA.size(); i++) {
               Image bA_img = (Toolkit.getDefaultToolkit()).getImage("mon.png");
               buffg.drawImage(bA_img, bossA.get(i).xpos, bossA.get(i).ypos, bossA.get(i).width, bossA.get(i).height, this);
            }
            
            if(MyThread.count>=30) {
               buffg.drawImage(img, 0, 0, getWidth(), getHeight(), this);
               buffg.drawImage(a_img, airplane.xpos, airplane.ypos, airplane.width, airplane.height, this);

            }
            
         }
         
         
         // 몬스터 이미지 가져오기
         for (int i = 0; i < mon.size(); i++) {
            Image m_img = (Toolkit.getDefaultToolkit()).getImage("monster.png");
            buffg.drawImage(m_img, mon.get(i).xpos, mon.get(i).ypos, mon.get(i).width, mon.get(i).height, this);
         }

         // 약 이미지
         for (int i = 0; i < pluslife.size(); i++) {
            Image m_img = (Toolkit.getDefaultToolkit()).getImage("medicine4.png");
            buffg.drawImage(m_img, pluslife.get(i).xpos, pluslife.get(i).ypos, pluslife.get(i).width,
                  pluslife.get(i).height, this);
         }
         
         // +10
         for (int i = 0; i < plus10.size(); i++) {
            Image m_img = (Toolkit.getDefaultToolkit()).getImage("102.png");
            buffg.drawImage(m_img, plus10.get(i).xpos, plus10.get(i).ypos, plus10.get(i).width,
                  plus10.get(i).height, this);
         }
         
         // -10
         for (int i = 0; i < minus10.size(); i++) {
            Image m_img = (Toolkit.getDefaultToolkit()).getImage("explosion.png");
            buffg.drawImage(m_img, minus10.get(i).xpos, minus10.get(i).ypos, minus10.get(i).width,
                  minus10.get(i).height, this);
         }
         
         // 별 이미지 가져오기
         for (int i = 0; i < star.size(); i++) {
            buffg.drawImage(s_img, star.get(i).xpos, star.get(i).ypos, star.get(i).width, star.get(i).height, this);
         }
         
         //몬스터 죽음 이미지
         for(int i=0; i<bomb.size(); i++) {
             Image k_img = (Toolkit.getDefaultToolkit()).getImage("explosion.png");
             buffg.drawImage(k_img,bomb.get(i).xpos, bomb.get(i).ypos, bomb.get(i).width, bomb.get(i).height, this);
         }
   }

   public void fire() {
      // 호출할 때 마다 별의 객체를 생성해서 star라고 하는 리스트에 저장
      // 비행기의 정보를 가지고 객체 생성
      star.add(new Ball(airplane.xpos + 12, airplane.ypos - 6, 20, 20, 5, 5));
      // System.out.println(star.size());
   }

}