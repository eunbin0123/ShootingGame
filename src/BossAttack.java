import java.util.Random;

public class BossAttack extends Thread{
	MyCanvas can3 = new MyCanvas();
	void setCanvas(MyCanvas can3) {
		this.can3 = can3;
	} // ĵ���� �����
	
	void addAttack() {
	
		Random r3 = new Random();
		int x = r3.nextInt(350);
		int y = r3.nextInt(300);
		if(MyThread.killscore>=200 &&  MyThread.count<=30) {
			
			Ball attack = new Ball(x, y, 30, 30);
			can3.bossA.add(attack);
		}
	}
	
	public void run() {
	      while(true) {
	         
	         addAttack();
	         
	         try {
	            sleep(300); 
	           
	         } catch (InterruptedException e) {
	         
	         }
	      
	         can3.repaint(); //��ü�� �����ɶ����� �ٽ� �׸��� �׸��� repaint()ȣ��
	      }
	   }

}