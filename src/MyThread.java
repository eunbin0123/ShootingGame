
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

   static int killscore = 0; // ����
   int killnum = 0;// ���� ���� ��
   int pass = 0; // ������ ���� ���� ��
   int keykill = 0;
   int hit = 0;
   int mylife = 100;
   int hitcount = 10;

   // *����
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

   // �ڹٿ����� �浹�� �����ϴ� Ŭ����
   // Rectangle : double���� width height�� ����
   // getArea() : �簢�����̸� �����Ѵ�.
   // intersects �������� Ȯ��

   public void run() {
      while (true) {
         Ball a = can.airplane;
         Rectangle airplaneR = new Rectangle(a.xpos, a.ypos, a.width, a.height); // ����⸦ ���ڿ� ��´�.

         // ����*
         if (killscore >= 200 && count <= 30) {
            Ball boss = can.boss;
            Rectangle bossR = new Rectangle(boss.xpos, boss.ypos, boss.width, boss.height);// �������͸� ���ڿ� ��´�


            // �Ѿ˰� �������� �浹
            for (int k = 0; k < can.star.size(); k++) {
               
               Ball s = can.star.get(k);
               s.ypos -= s.speedy;// ���� ���� ���
               if (s.ypos <= 0) {
                  can.star.remove(k);// �迭 �� ���̱�
               }

               Rectangle starR = new Rectangle(s.xpos, s.ypos, s.width, s.height);// ���� ���ڿ� ���
               

               
              
               if (starR.intersects(bossR)) {
                 //can.minus10.add(minus);
                  Ball minus = new Ball(can.star.get(k).xpos, can.star.get(k).ypos, 30, 30);
                 
                 can.minus10.add(minus);
                  can.star.remove(k);
                  loadAudio("audio/����.wav");
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

            // �����Ѿ� ������
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

            // ������ �����Ѿ� �浹
            for (int j = 0; j < can.bossA.size(); j++) {

               Ball bA = can.bossA.get(j);

               Rectangle bossAR = new Rectangle(bA.xpos, bA.ypos, bA.width, bA.height);// ���͸� ���ڿ� ��´�

               if (airplaneR.intersects(bossAR)) {

                  can.bossA.remove(j);
                  hit += 10;
                  mylife -= 10;
                  System.out.println("mylife: " + mylife);

                  life.setSize(hit, 20);
                  life2.setLocation(240 + hit, 50); // ����� ���� ��ġ�� �ڷ� �ű�鼭
                  life2.setSize(140 - hit, 20);// ���α��̸� ���δ�

               } /////// ���!!!!!!!!!!!!!!!!!!!!
            }
         }

         // ����������*

         // ������ ���� �浹
         for (int j = 0; j < can.mon.size(); j++) {

            Ball m = can.mon.get(j);

            Rectangle monR = new Rectangle(m.xpos, m.ypos, m.width, m.height);// ���͸� ���ڿ� ��´�

            if (airplaneR.intersects(monR)) {
               loadAudio("audio/MonDie.wav");
               clip.start();
               can.mon.remove(j);
               System.out.println("crash");
               hit += 10;
               mylife -= 10;
               System.out.println("mylife: " + mylife);

               life.setSize(hit, 20);
               life2.setLocation(240 + hit, 50); // ����� ���� ��ġ�� �ڷ� �ű�鼭
               life2.setSize(140 - hit, 20);// ���α��̸� ���δ�
            } /////// ���!!!!!!!!!!!!!!!!!!!!
         }

         // ����Ⱑ �������
         for (int i = 0; i < can.pluslife.size(); i++) {

            Ball med = can.pluslife.get(i);
            // Rectangle airplaneR = new Rectangle(a.xpos, a.ypos, a.width, a.height); //
            // ����⸦ ���ڿ� ��´�.
            Rectangle medR = new Rectangle(med.xpos, med.ypos, med.width, med.height);// ����ڸ� ���ڿ� ��´�

            if (airplaneR.intersects(medR)) {
               loadAudio("audio/�ܲ�2.wav");
               clip.start();

               Ball pluslife10 = new Ball(can.pluslife.get(i).xpos, can.pluslife.get(i).ypos - 20, 30, 30);

               can.plus10.add(pluslife10);

               can.pluslife.remove(i);

               if (hit > 0) {

                  hit -= 10;

                  life.setSize(hit, 20);
                  life2.setLocation(240 + hit, 50); // ����� ���� ��ġ�� �ڷ� �ű�鼭
                  life2.setSize(140 - hit, 20);// ���α��̸� ���δ�
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
         life2.setLocation(240 + hit, 50); // ����� ���� ��ġ�� �ڷ� �ű�鼭
         life2.setSize(140 - hit, 20);
         can.repaint();

         // �Ѿ˰� ���� �浹
         for (int i = 0; i < can.star.size(); i++) {
            Ball s = can.star.get(i);

            s.ypos -= s.speedy;// ���� ���� ���

            if (s.ypos <= 0) {
               can.star.remove(i);// �迭 �� ���̱�
            }

            Rectangle starR = new Rectangle(s.xpos, s.ypos, s.width, s.height);// ���� ���ڿ� ���

            for (int j = 0; j < can.mon.size(); j++) {

               Ball m = can.mon.get(j);
               Rectangle monR = new Rectangle(m.xpos, m.ypos, m.width, m.height);// ���͸� ���ڿ� ��´�

               if (starR.intersects(monR)) {
                  Ball bomba = new Ball(can.mon.get(j).xpos, can.mon.get(j).ypos, 30, 30);

                  can.bomb.add(bomba);

                  loadAudio("audio/��ư(��).wav");
                  clip.start();

                  can.star.remove(i);
                  can.mon.remove(j);

                  // bar.crash();
                  killnum++; // ���� ���� ��
                  System.out.println(killnum);// Ȯ��

                  keykill++;

                  killscore = killnum * 10; // ���� �Ѹ����� 10��
                  System.out.println(killscore);// Ȯ��

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

         // ����Ű�� �������� ����� ��ü�� �̵���Ű�� ������
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
///////////////////////////////////////////////////////////�������
         keykill = 0;

         if (pass % 6 == 5) {
            pass = 0;

            loadAudio("audio/���.wav");
            clip.start();
            while (keykill != 5) {
               Ball b = can.airplane;
               Rectangle airplaneR2 = new Rectangle(a.xpos, a.ypos, a.width, a.height); // ����⸦ ���ڿ� ��´�.
               if (killscore >= 200 && count <= 30) {
                  Ball boss = can.boss;
                  Rectangle bossR = new Rectangle(boss.xpos, boss.ypos, boss.width, boss.height);// �������͸� ���ڿ� ��´�

                  // �Ѿ˰� �������� �浹
                  for (int k = 0; k < can.star.size(); k++) {

                     Ball s = can.star.get(k);
                     s.ypos -= s.speedy;// ���� ���� ���
                     if (s.ypos <= 0) {
                        can.star.remove(k);// �迭 �� ���̱�
                     }

                     Rectangle starR = new Rectangle(s.xpos, s.ypos, s.width, s.height);// ���� ���ڿ� ���

                     if (starR.intersects(bossR)) {
                        can.star.remove(k);
                        loadAudio("audio/����.wav");
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

                  // �����Ѿ� ������
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

                  // ������ �����Ѿ� �浹
                  for (int j = 0; j < can.bossA.size(); j++) {

                     Ball bA = can.bossA.get(j);

                     Rectangle bossAR = new Rectangle(bA.xpos, bA.ypos, bA.width, bA.height);// ���͸� ���ڿ� ��´�

                     if (airplaneR.intersects(bossAR)) {

                        can.bossA.remove(j);
                        hit += 10;
                        mylife -= 10;
                        System.out.println("mylife: " + mylife);

                        life.setSize(hit, 20);
                        life2.setLocation(240 + hit, 50); // ����� ���� ��ġ�� �ڷ� �ű�鼭
                        life2.setSize(140 - hit, 20);// ���α��̸� ���δ�

                     } /////// ���!!!!!!!!!!!!!!!!!!!!
                  }
               }

               // ������ ���� �浹
               for (int j = 0; j < can.mon.size(); j++) {

                  Ball m = can.mon.get(j);

                  Rectangle monR = new Rectangle(m.xpos, m.ypos, m.width, m.height);// ���͸� ���ڿ� ��´�

                  if (airplaneR2.intersects(monR)) {
                     loadAudio("audio/MonDie.wav");
                     clip.start();
                     can.mon.remove(j);
                     System.out.println("crash");
                     hit += 10;
                     mylife -= 10;
                     System.out.println("mylife: " + mylife);

                     life.setSize(hit, 20);
                     life2.setLocation(240 + hit, 50); // ����� ���� ��ġ�� �ڷ� �ű�鼭
                     life2.setSize(140 - hit, 20);// ���α��̸� ���δ�
                  } /////// ���!!!!!!!!!!!!!!!!!!!!
               }

               // ����Ⱑ ��Ծ�����
               for (int i = 0; i < can.pluslife.size(); i++) {

                  Ball med = can.pluslife.get(i);
                  // Rectangle airplaneR = new Rectangle(a.xpos, a.ypos, a.width, a.height); //
                  // ����⸦ ���ڿ� ��´�.
                  Rectangle medR = new Rectangle(med.xpos, med.ypos, med.width, med.height);// ����ڸ� ���ڿ� ��´�

                  if (airplaneR2.intersects(medR)) {

                     loadAudio("audio/�ܲ�2.wav");
                     clip.start();

                     Ball pluslife10 = new Ball(can.pluslife.get(i).xpos, can.pluslife.get(i).ypos - 15, 30, 30);

                     can.plus10.add(pluslife10);

                     can.pluslife.remove(i);

                     if (hit > 0) {

                        hit -= 10;

                        life.setSize(hit, 20);
                        life2.setLocation(240 + hit, 50); // ����� ���� ��ġ�� �ڷ� �ű�鼭
                        life2.setSize(140 - hit, 20);// ���α��̸� ���δ�

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
               life2.setLocation(240 + hit, 50); // ����� ���� ��ġ�� �ڷ� �ű�鼭
               life2.setSize(140 - hit, 20);
               can.repaint();

               // �Ѿ˰� ���� �浹
               for (int i = 0; i < can.star.size(); i++) {
                  Ball s = can.star.get(i);
                  s.ypos -= s.speedy;// ���� ���� ���

                  if (s.ypos <= 0) {
                     can.star.remove(i);// �迭 �� ���̱�
                  }

                  Rectangle starR = new Rectangle(s.xpos, s.ypos, s.width, s.height);// ���� ���ڿ� ���

                  for (int j = 0; j < can.mon.size(); j++) {

                     Ball m = can.mon.get(j);
                     Rectangle monR = new Rectangle(m.xpos, m.ypos, m.width, m.height);// ���͸� ���ڿ� ��´�

                     if (starR.intersects(monR)) {

                        Ball bomba = new Ball(can.mon.get(j).xpos, can.mon.get(j).ypos, 30, 30);

                        can.bomb.add(bomba);

                        loadAudio("audio/��ư(��).wav");
                        clip.start();

                        can.star.remove(i);
                        can.mon.remove(j);

                        // bar.crash();
                        killnum++; // ���� ���� ��
                        System.out.println(killnum);// Ȯ��

                        keykill++;

                        killscore = killnum * 10; // ���� �Ѹ����� 10��
                        System.out.println(killscore);// Ȯ��

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

               // ���� ��ü�� �̵���Ű�� ������
               for (int i = 0; i < can.mon.size(); i++) {
                  can.mon.get(i).ypos += 1;

                  if (can.mon.get(i).ypos >= 600) {
                     can.mon.remove(i);

                     pass++; // ���Ͱ� �Ʒ��� ������ ������ ������ ���� �ö󰣴�

                     monsterpass.setText("WARNING!!");
                     monsterpass.setForeground(Color.RED);
                  }
               }
               // �� ��ü �̵�
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

         monsterpass.setText("������ ���� �� : " + pass);
         monsterpass.setForeground(Color.black);
////////////////////////////////////////////////////////////////////

         // ���� ��ü�� �̵���Ű�� ������

         for (int i = 0; i < can.mon.size(); i++) {
            can.mon.get(i).ypos += 1;

            if (can.mon.get(i).ypos >= 600) {
               can.mon.remove(i);

               pass++; // ���Ͱ� �Ʒ��� ������ ������ ������ ���� �ö󰣴�

               monsterpass.setText("������ ���� �� : " + pass);
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