
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyThread extends Thread {
   MyCanvas can;

   static int killscore = 0; // 점수
   int killnum = 0;// 몬스터 죽인 수
   int pass = 0; // 밖으로 나간 몬스터 수
   int keykill = 0;
   int hit = 0;
   int mylife = 100;
   int hitcount = 10;

   // *수정
   int bhit = 0;
   int blife = 100;
   int bhitcount = 10;
   static int count;

   JLabel monsterpass;
   JLabel sum;
   JLabel score;
   JLabel life;
   JLabel life2;
   MyFrame out;
   JLabel myscore;
   // SecondFrame sf;
   // Start st;

   public MyThread(JLabel sum, JLabel score, JLabel monsterpass, JLabel life, JLabel life2, MyFrame out,
         JLabel myscore) {
      this.sum = sum;
      this.score = score;
      this.monsterpass = monsterpass;
      this.life = life;
      this.life2 = life2;
      this.out = out;
      this.myscore = myscore;
//      this.sf =sf;
//      this.st= st;
   }

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

   boolean up, down, left, right;

   void setUp(boolean up) {
      this.up = up;
   }

   void setDown(boolean down) {
      this.down = down;
   }

   void setLeft(boolean left) {
      this.left = left;
   }

   void setRight(boolean right) {
      this.right = right;
   }

   void func() {
      can.fire();
   }

   void setCanvas(MyCanvas can) {
      this.can = can;
   }

   // 자바에서는 충돌을 감지하는 클래스
   // Rectangle : double형의 width height를 보유
   // getArea() : 사각형넓이를 리턴한다.
   // intersects 교차점을 확인

   public void run() {
      while (true) {
         Ball a = can.airplane;
         Rectangle airplaneR = new Rectangle(a.xpos, a.ypos, a.width, a.height); // 비행기를 상자에 담는다.

         // 수정*
         if (killscore >= 200 && count <= 30) {
            Ball boss = can.boss;
            Rectangle bossR = new Rectangle(boss.xpos, boss.ypos, boss.width, boss.height);// 보스몬스터를 상자에 담는다


            // 총알과 보스와의 충돌
            for (int k = 0; k < can.star.size(); k++) {
               
               Ball s = can.star.get(k);
               s.ypos -= s.speedy;// 별을 위로 쏘기
               if (s.ypos <= 0) {
                  can.star.remove(k);// 배열 수 줄이기
               }

               Rectangle starR = new Rectangle(s.xpos, s.ypos, s.width, s.height);// 별을 상자에 담기
               

               
              
               if (starR.intersects(bossR)) {
                 //can.minus10.add(minus);
                  Ball minus = new Ball(can.star.get(k).xpos, can.star.get(k).ypos, 30, 30);
                 
                 can.minus10.add(minus);
                  can.star.remove(k);
                  loadAudio("audio/으악.wav");
                  clip.start();
                  bhit += 10;
                  blife -= 10;
                  System.out.println("blife: " + blife);
                  count++;
                  System.out.println(count);

                  try {
                     sleep(50);
                  } catch (InterruptedException e) {

                  }
                  
                  can.minus10.remove(minus);

               }
            }

            // 보스총알 움직임
            for (int i = 0; i < can.bossA.size(); i++) {

               if (i % 2 == 0) {
                  can.bossA.get(i).xpos += 2;
                  can.bossA.get(i).ypos += 2;
               } else if (i % 2 != 0) {
                  can.bossA.get(i).xpos -= 2;
                  can.bossA.get(i).ypos += 2;
               }

               if (can.bossA.get(i).ypos >= 600) {
                  can.bossA.remove(i);
               }

               if (count >=   30) {
                  can.bossA.remove(i);
                }
               
            }

            // 비행기와 보스총알 충돌
            for (int j = 0; j < can.bossA.size(); j++) {

               Ball bA = can.bossA.get(j);

               Rectangle bossAR = new Rectangle(bA.xpos, bA.ypos, bA.width, bA.height);// 몬스터를 상자에 담는다

               if (airplaneR.intersects(bossAR)) {

                  can.bossA.remove(j);
                  hit += 10;
                  mylife -= 10;
                  System.out.println("mylife: " + mylife);

                  life.setSize(hit, 20);
                  life2.setLocation(240 + hit, 50); // 노란색 바의 위치를 뒤로 옮기면서
                  life2.setSize(140 - hit, 20);// 가로길이를 줄인다

               } /////// 요거!!!!!!!!!!!!!!!!!!!!
            }
         }

         // 수정범위끝*

         // 비행기와 몬스터 충돌
         for (int j = 0; j < can.mon.size(); j++) {

            Ball m = can.mon.get(j);

            Rectangle monR = new Rectangle(m.xpos, m.ypos, m.width, m.height);// 몬스터를 상자에 담는다

            if (airplaneR.intersects(monR)) {
               loadAudio("audio/MonDie.wav");
               clip.start();
               can.mon.remove(j);
               System.out.println("crash");
               hit += 10;
               mylife -= 10;
               System.out.println("mylife: " + mylife);

               life.setSize(hit, 20);
               life2.setLocation(240 + hit, 50); // 노란색 바의 위치를 뒤로 옮기면서
               life2.setSize(140 - hit, 20);// 가로길이를 줄인다
            } /////// 요거!!!!!!!!!!!!!!!!!!!!
         }

         // 비행기가 약먹을때
         for (int i = 0; i < can.pluslife.size(); i++) {

            Ball med = can.pluslife.get(i);
            // Rectangle airplaneR = new Rectangle(a.xpos, a.ypos, a.width, a.height); //
            // 비행기를 상자에 담는다.
            Rectangle medR = new Rectangle(med.xpos, med.ypos, med.width, med.height);// 약상자를 상자에 담는다

            if (airplaneR.intersects(medR)) {
               loadAudio("audio/꿀꺽2.wav");
               clip.start();

               Ball pluslife10 = new Ball(can.pluslife.get(i).xpos, can.pluslife.get(i).ypos - 20, 30, 30);

               can.plus10.add(pluslife10);

               can.pluslife.remove(i);

               if (hit > 0) {

                  hit -= 10;

                  life.setSize(hit, 20);
                  life2.setLocation(240 + hit, 50); // 노란색 바의 위치를 뒤로 옮기면서
                  life2.setSize(140 - hit, 20);// 가로길이를 줄인다
                  can.repaint();
               }

               if (mylife < 100) {
                  mylife += 10;
               }

               try {
                  sleep(100);
               } catch (InterruptedException e) {

               }
               can.plus10.remove(pluslife10);
            }
         }

         life.setSize(hit, 20);
         life2.setLocation(240 + hit, 50); // 노란색 바의 위치를 뒤로 옮기면서
         life2.setSize(140 - hit, 20);
         can.repaint();

         // 총알과 몬스터 충돌
         for (int i = 0; i < can.star.size(); i++) {
            Ball s = can.star.get(i);

            s.ypos -= s.speedy;// 별을 위로 쏘기

            if (s.ypos <= 0) {
               can.star.remove(i);// 배열 수 줄이기
            }

            Rectangle starR = new Rectangle(s.xpos, s.ypos, s.width, s.height);// 별을 상자에 담기

            for (int j = 0; j < can.mon.size(); j++) {

               Ball m = can.mon.get(j);
               Rectangle monR = new Rectangle(m.xpos, m.ypos, m.width, m.height);// 몬스터를 상자에 담는다

               if (starR.intersects(monR)) {
                  Ball bomba = new Ball(can.mon.get(j).xpos, can.mon.get(j).ypos, 30, 30);

                  can.bomb.add(bomba);

                  loadAudio("audio/버튼(벙).wav");
                  clip.start();

                  can.star.remove(i);
                  can.mon.remove(j);

                  // bar.crash();
                  killnum++; // 몬스터 죽인 수
                  System.out.println(killnum);// 확인

                  keykill++;

                  killscore = killnum * 10; // 몬스터 한마리당 10점
                  System.out.println(killscore);// 확인

                  score.setText("SCORE : " + killscore);
                  // sum.setText("Monster Kill: " + killnum);

                  try {
                     sleep(50);
                  } catch (InterruptedException e) {

                  }
                  can.bomb.remove(bomba);

               }

            }

         }

         // 방향키를 눌렀을때 비행기 객체를 이동시키는 쓰레드
         if (left)
            if (can.airplane.xpos > 10) {
               can.airplane.xpos -= can.airplane.speedx;
            }

         if (right) {
            if (can.airplane.xpos < 330)
               can.airplane.xpos += can.airplane.speedx;
         }
         if (up) {
            if (can.airplane.ypos > 50)
               can.airplane.ypos -= can.airplane.speedy;
         }
         if (down) {
            if (can.airplane.ypos < 500)
               can.airplane.ypos += can.airplane.speedy;
         }
///////////////////////////////////////////////////////////반전모드
         keykill = 0;

         if (pass % 6 == 5) {
            pass = 0;

            loadAudio("audio/경고.wav");
            clip.start();
            while (keykill != 5) {
               Ball b = can.airplane;
               Rectangle airplaneR2 = new Rectangle(a.xpos, a.ypos, a.width, a.height); // 비행기를 상자에 담는다.
               if (killscore >= 200 && count <= 30) {
                  Ball boss = can.boss;
                  Rectangle bossR = new Rectangle(boss.xpos, boss.ypos, boss.width, boss.height);// 보스몬스터를 상자에 담는다

                  // 총알과 보스와의 충돌
                  for (int k = 0; k < can.star.size(); k++) {

                     Ball s = can.star.get(k);
                     s.ypos -= s.speedy;// 별을 위로 쏘기
                     if (s.ypos <= 0) {
                        can.star.remove(k);// 배열 수 줄이기
                     }

                     Rectangle starR = new Rectangle(s.xpos, s.ypos, s.width, s.height);// 별을 상자에 담기

                     if (starR.intersects(bossR)) {
                        can.star.remove(k);
                        loadAudio("audio/으악.wav");
                        clip.start();
                        bhit += 10;
                        blife -= 10;
                        System.out.println("blife: " + blife);
                        count++;
                        System.out.println(count);

                        try {
                           sleep(40);
                        } catch (InterruptedException e) {

                        }

                     }
                  }

                  // 보스총알 움직임
                  for (int i = 0; i < can.bossA.size(); i++) {

                     if (i % 2 == 0) {
                        can.bossA.get(i).xpos += 2;
                        can.bossA.get(i).ypos += 2;
                     } else if (i % 2 != 0) {
                        can.bossA.get(i).xpos -= 2;
                        can.bossA.get(i).ypos += 2;
                     }
 
                     if (can.bossA.get(i).ypos >= 600) {
                        can.bossA.remove(i);
                     }
                     
 
                     if (count >= 30) {
                        can.bossA.remove(i);
                     }
                  }

                  // 비행기와 보스총알 충돌
                  for (int j = 0; j < can.bossA.size(); j++) {

                     Ball bA = can.bossA.get(j);

                     Rectangle bossAR = new Rectangle(bA.xpos, bA.ypos, bA.width, bA.height);// 몬스터를 상자에 담는다

                     if (airplaneR.intersects(bossAR)) {

                        can.bossA.remove(j);
                        hit += 10;
                        mylife -= 10;
                        System.out.println("mylife: " + mylife);

                        life.setSize(hit, 20);
                        life2.setLocation(240 + hit, 50); // 노란색 바의 위치를 뒤로 옮기면서
                        life2.setSize(140 - hit, 20);// 가로길이를 줄인다

                     } /////// 요거!!!!!!!!!!!!!!!!!!!!
                  }
               }

               // 비행기와 몬스터 충돌
               for (int j = 0; j < can.mon.size(); j++) {

                  Ball m = can.mon.get(j);

                  Rectangle monR = new Rectangle(m.xpos, m.ypos, m.width, m.height);// 몬스터를 상자에 담는다

                  if (airplaneR2.intersects(monR)) {
                     loadAudio("audio/MonDie.wav");
                     clip.start();
                     can.mon.remove(j);
                     System.out.println("crash");
                     hit += 10;
                     mylife -= 10;
                     System.out.println("mylife: " + mylife);

                     life.setSize(hit, 20);
                     life2.setLocation(240 + hit, 50); // 노란색 바의 위치를 뒤로 옮기면서
                     life2.setSize(140 - hit, 20);// 가로길이를 줄인다
                  } /////// 요거!!!!!!!!!!!!!!!!!!!!
               }

               // 비행기가 약먹었을때
               for (int i = 0; i < can.pluslife.size(); i++) {

                  Ball med = can.pluslife.get(i);
                  // Rectangle airplaneR = new Rectangle(a.xpos, a.ypos, a.width, a.height); //
                  // 비행기를 상자에 담는다.
                  Rectangle medR = new Rectangle(med.xpos, med.ypos, med.width, med.height);// 약상자를 상자에 담는다

                  if (airplaneR2.intersects(medR)) {

                     loadAudio("audio/꿀꺽2.wav");
                     clip.start();

                     Ball pluslife10 = new Ball(can.pluslife.get(i).xpos, can.pluslife.get(i).ypos - 15, 30, 30);

                     can.plus10.add(pluslife10);

                     can.pluslife.remove(i);

                     if (hit > 0) {

                        hit -= 10;

                        life.setSize(hit, 20);
                        life2.setLocation(240 + hit, 50); // 노란색 바의 위치를 뒤로 옮기면서
                        life2.setSize(140 - hit, 20);// 가로길이를 줄인다

                     }

                     if (mylife < 100) {
                        mylife += 10;
                     }

                     try {
                        sleep(100);
                     } catch (InterruptedException e) {

                     }
                     can.plus10.remove(pluslife10);
                  }
               }

               life.setSize(hit, 20);
               life2.setLocation(240 + hit, 50); // 노란색 바의 위치를 뒤로 옮기면서
               life2.setSize(140 - hit, 20);
               can.repaint();

               // 총알과 몬스터 충돌
               for (int i = 0; i < can.star.size(); i++) {
                  Ball s = can.star.get(i);
                  s.ypos -= s.speedy;// 별을 위로 쏘기

                  if (s.ypos <= 0) {
                     can.star.remove(i);// 배열 수 줄이기
                  }

                  Rectangle starR = new Rectangle(s.xpos, s.ypos, s.width, s.height);// 별을 상자에 담기

                  for (int j = 0; j < can.mon.size(); j++) {

                     Ball m = can.mon.get(j);
                     Rectangle monR = new Rectangle(m.xpos, m.ypos, m.width, m.height);// 몬스터를 상자에 담는다

                     if (starR.intersects(monR)) {

                        Ball bomba = new Ball(can.mon.get(j).xpos, can.mon.get(j).ypos, 30, 30);

                        can.bomb.add(bomba);

                        loadAudio("audio/버튼(벙).wav");
                        clip.start();

                        can.star.remove(i);
                        can.mon.remove(j);

                        // bar.crash();
                        killnum++; // 몬스터 죽인 수
                        System.out.println(killnum);// 확인

                        keykill++;

                        killscore = killnum * 10; // 몬스터 한마리당 10점
                        System.out.println(killscore);// 확인

                        score.setText("SCORE : " + killscore);
                        // sum.setText("Monster Kill: " + killnum);

                        try {
                           sleep(50);
                        } catch (InterruptedException e) {

                        }

                        can.bomb.remove(bomba);

                     }

                  }

               }

               if (left)
                  if (can.airplane.xpos < 330) {
                     can.airplane.xpos += can.airplane.speedx;
                  }

               if (right) {
                  if (can.airplane.xpos > 10)
                     can.airplane.xpos -= can.airplane.speedx;
               }
               if (up) {
                  if (can.airplane.ypos < 500)
                     can.airplane.ypos += can.airplane.speedy;
               }
               if (down) {
                  if (can.airplane.ypos > 50)
                     can.airplane.ypos -= can.airplane.speedy;
               }

               // 몬스터 객체를 이동시키는 쓰레드
               for (int i = 0; i < can.mon.size(); i++) {
                  can.mon.get(i).ypos += 1;

                  if (can.mon.get(i).ypos >= 600) {
                     can.mon.remove(i);

                     pass++; // 몬스터가 아래에 닿으면 지나간 몬스터의 수가 올라간다

                     monsterpass.setText("WARNING!!");
                     monsterpass.setForeground(Color.RED);
                  }
               }
               // 약 객체 이동
               for (int i = 0; i < can.pluslife.size(); i++) {
                  can.pluslife.get(i).ypos += 1;

                  if (can.pluslife.get(i).ypos >= 600) {
                     can.pluslife.remove(i);

                  }
               }

               can.repaint();

               try {
                  sleep(10);

               } catch (InterruptedException e) {

               }
               
               if(count>=30) { 
                   try {
                      out.clip.stop();

                      out.dispose();
//                      st.dispose();
//                      sf.dispose();
//                      
                      out.clip.stop(); 

                      // myscore.setText("SCORE : " + killscore);
                      new GameWin();
 
                      while (!Thread.currentThread().isInterrupted()) {

                         Thread.sleep(10000);

                      }

                   } catch (InterruptedException e) {

                   }
                }

               if (140 - hit <= 0) {
                  try {
                     out.dispose();
//                     st.dispose();
//                     sf.dispose();
//                     
                     clip.stop();
                     // myscore.setText("SCORE : " + killscore);
                     new GameOver();

                     while (!Thread.currentThread().isInterrupted()) {

                        Thread.sleep(10000);

                     }

                  } catch (InterruptedException e) {

                  }
               }
            }
            pass = 0;
         }

         monsterpass.setText("지나간 몬스터 수 : " + pass);
         monsterpass.setForeground(Color.black);
////////////////////////////////////////////////////////////////////

         // 몬스터 객체를 이동시키는 쓰레드

         for (int i = 0; i < can.mon.size(); i++) {
            can.mon.get(i).ypos += 1;

            if (can.mon.get(i).ypos >= 600) {
               can.mon.remove(i);

               pass++; // 몬스터가 아래에 닿으면 지나간 몬스터의 수가 올라간다

               monsterpass.setText("지나간 몬스터 수 : " + pass);
            }
         }

         for (int i = 0; i < can.pluslife.size(); i++) {
            can.pluslife.get(i).ypos += 1;

            if (can.pluslife.get(i).ypos >= 600) {
               can.pluslife.remove(i);

            }
         }

         can.repaint();

         try {
            sleep(10);

         } catch (InterruptedException e) {

         }
         if(count>=30) { 
            try {
               out.clip.stop();

               out.dispose();
//               st.dispose();
//               sf.dispose();
//               
               out.clip.stop();

               // myscore.setText("SCORE : " + killscore);
               new GameWin();

               while (!Thread.currentThread().isInterrupted()) {

                  Thread.sleep(10000);

               }

            } catch (InterruptedException e) {

            }
         }

         if (140 - hit <= 0) {
            try {
               out.clip.stop();

               out.dispose();
//               st.dispose();
//               sf.dispose();
//               
               out.clip.stop();

               // myscore.setText("SCORE : " + killscore);
               new GameOver();

               while (!Thread.currentThread().isInterrupted()) {

                  Thread.sleep(10000);

               }

            } catch (InterruptedException e) {

            }
         }

      }

   }
}