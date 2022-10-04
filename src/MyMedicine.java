import java.util.Random;

public class MyMedicine extends Thread {
	MyCanvas can2;

	void setCanvas(MyCanvas can2) {
		this.can2 = can2;
	}// 저장법

	void addMedicine() {
		Random r2 = new Random();
		int x = r2.nextInt(350);
		int y = r2.nextInt(300);

		Ball medicine = new Ball(x, y, 30, 30);

		can2.pluslife.add(medicine);

	}
	
	public void run() {
	      while(true) {
	         
	         addMedicine();
	         
	         try {
	            sleep(15000); //15초마다 약상자가 떨어진다
	           
	         } catch (InterruptedException e) {
	         
	         }
	      
	         can2.repaint(); //객체가 생성될때마다 다시 그림으 그리는 repaint()호출
	      }
	   }
	   

}
