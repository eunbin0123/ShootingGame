
import java.util.Random;

public class MyMonster extends Thread{
   MyCanvas can;
   
   void setCanvas(MyCanvas can) {
      this.can = can;
   }//�����
   
   void addMon() {
      //���� x,y�� ��´�
      Random r = new Random();
      int x = r.nextInt(350);
      int y = r.nextInt(300);
      
      Ball monster = new Ball(x,y,30,30);
      if(MyThread.killscore<=200 || MyThread.count>=30 )//*����
    	  can.mon.add(monster);
      
  
   }
   
  
   public void run() {
      while(true) {
         addMon();
         
         
         try {
            sleep(500);
           
         } catch (InterruptedException e) {
         
         }
      
         can.repaint(); //��ü�� �����ɶ����� �ٽ� �׸��� �׸��� repaint()ȣ��
      }
   }
   

}