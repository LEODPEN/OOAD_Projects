package proC.view;

public class Model {
  private int x=0,y=0,speed = 3;//坐标
  private boolean movingLeft = false;
  private boolean movingRight = false;
  private boolean movingUp = false;
  private boolean movingDown = false;

  public void update(){
    if(movingDown) y+=speed;
    if(movingUp) y-=speed;
    if(movingLeft) x-=speed;
    if(movingRight) x+=speed;
  }

  public void setMovingLeft(boolean movingLeft) { this.movingLeft = movingLeft; }
  public void setMovingRight(boolean movingRight) { this.movingRight = movingRight; }
  public void setMovingUp(boolean movingUp) { this.movingUp = movingUp; }
  public void setMovingDown(boolean movingDown) { this.movingDown = movingDown; }

  public int getX() { return x; }
  public int getY() { return y; }
}
