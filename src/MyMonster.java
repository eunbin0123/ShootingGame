
import java.util.Random;

public class MyMonster extends Thread{
   MyCanvas can;
   
   void setCanvas(MyCanvas can) {
      this.can = can;
   }//저장법
   
   void addMon() {
      //랜덤 x,y를 잡는다
      Random r = new Random();
      int x = r.nextInt(350);
      int y = r.nextInt(300);
      
      Ball monster = new Ball(x,y,30,30);
      if(MyThread.killscore<=200 || MyThread.count>=30 )//*수정
    	  can.mon.add(monster);
      
  
   }
   
  
   public void run() {
      while(true) {
         addMon();
         
         
         try {
            sleep(500);
           
         } catch (InterruptedException e) {
         
         }
      
         can.repaint(); //객체가 생성될때마다 다시 그림으 그리는 repaint()호출
      }
   }
   

}