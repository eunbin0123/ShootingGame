
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
import java.awt.event.*;

class Score {
	int score = 0;

	Score(int score) {
		this.score = score;
		System.out.println(score);
	}
}

public class MyFrame extends JFrame {
	int sum;
	JLabel monnum[];
	MyCanvas can = new MyCanvas();
	
	
	BossAttack attack = new BossAttack();//*
	MyMonster mon = new MyMonster();
	MyMedicine med = new MyMedicine();
	static public String pass;

	
	Score score = new Score(0);
	Start st;
//	SecondFrame sf = new SecondFrame();
	//MyFrame gameover;

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
	
	public MyFrame() {
		
      setTitle("���ð���");
      setBounds(500, 500, 400, 600);
  
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension screenSize = tk.getScreenSize();// ȭ�������

      setLocation(screenSize.width / 2 - 300, screenSize.height / 2 - 300);// ȭ�� �߾ӿ� â ����
      
      Container c = getContentPane();
      c.setLayout(new BorderLayout());
     
      loadAudio("audio/1.wav");
      clip.start();
      
      JPanel p2;
      JPanel p = new JPanel();
      
      JLabel sum; //���� ���� �� ��
      JLabel score; //���� ��
      JLabel monsterpass;//������ ����
      JLabel lifebar;//�����
      JLabel lifebar2;//���� ���� ǥ��
      
      p.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 10));
      p.setBackground(new Color(120, 200, 100));

      sum = new JLabel(st.name);
      score = new JLabel("score : 0");
      monsterpass = new JLabel("������ ���� ��: ");
      pass = monsterpass.getText();
      lifebar = new JLabel();
      lifebar2 = new JLabel();
      
     JLabel myscore = new JLabel();
      MyThread th = new MyThread(sum, score, monsterpass, lifebar2, lifebar, this, myscore);
      p.add(sum);

      p.add(new JLabel("shooting game"));
      p.add(score);
      add(p, "North");

      p2 = new JPanel();
      p2.setBackground(new Color(95, 158, 160));
      p2.add(monsterpass);
      add(p2, "South");

      
      // �����

      Container c2 = getContentPane();
      
      
      lifebar.setBackground(Color.orange);
      lifebar.setOpaque(true);
      lifebar.setLocation(240, 50);
      lifebar.setSize(140, 20);
      
      lifebar2.setBackground(Color.red);
      lifebar2.setOpaque(true);
      lifebar2.setLocation(240, 50);
      lifebar2.setSize(0, 20);
      
      c2.add(lifebar);
      c2.add(lifebar2);
      
      
      add(can);
      
      // ���Ͱ�ü(������)�� �����ϴ� start()
      mon.setCanvas(can);
      med.setCanvas(can);
      attack.setCanvas(can);//*
      attack.start();//*
      mon.start();
      med.start();
      
      

      // ������ ������ �� �ֵ��� start()
      th.setCanvas(can);
      th.start();
      

      can.addKeyListener(new KeyAdapter() {

         // ����Ű�� �̿��ؼ� ������� ����⸦ �����δ�.
         // �����̽�Ű�� ������ ���� ��ü�� �����ϰ� ���� �ö󰡵��� ����

         public void keyPressed(KeyEvent e) {
				long StartTime = System.nanoTime();
				long EndTime = System.nanoTime();

            switch (e.getKeyCode()) {
            case 32:
               th.func();
               break;
            case 37:
               th.setLeft(true);
               break;
            case 39:
               th.setRight(true);
               break;
            case 38:
               th.setUp(true);
               break;
            case 40:
               th.setDown(true);
               break;
            }
         }

         // ����Ű�� ����
         public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
            case 37:
               th.setLeft(false);
               break;
            case 39:
               th.setRight(false);
               break;
            case 38 :
               th.setUp(false);
               break;
            case 40:
               th.setDown(false);
               break;
            }
         }      

      });
     
     
      
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
	
	

}