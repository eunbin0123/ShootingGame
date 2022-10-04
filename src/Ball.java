
//비행기, 몬스터, 별의 *이동*객체 생성하는 클래스
//x,y,넓이,높이,스피드x,y
public class Ball {
   int xpos, ypos, width, height, speedx, speedy;
   
   public Ball(int xpos, int ypos, int width, int height) {
      this.xpos = xpos;
      this.ypos = ypos;
      this.width = width;
      this.height = height;
   }
   
   
   public Ball(int xpos, int ypos, int width, int height, int speedx, int speedy) {
      this.xpos = xpos;
      this.ypos = ypos;
      this.width = width;
      this.height = height;
      this.speedx = speedx;
      this.speedy = speedy;
   }

}