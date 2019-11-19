package proC.viewController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class View extends Canvas {
  private Model model;
  public View(Model model) { this.model = model; }
  private Image image;
  public void setImage(Image image) { this.image = image; }
  public void update(){
    var gc = getGraphicsContext2D();
    gc.clearRect(0,0,getWidth(),getHeight());//清空画布
    gc.drawImage(image, model.getX(),model.getY(), 100,100);
  }
  //绘制水平翻转后的图像
  public void flipDraw(GraphicsContext gc, Image img, double x, double y){
    gc.save();
    gc.scale(-1,1);//-1,1表示水平翻转，1,-1表示垂直翻转
    gc.drawImage(img,x,y);
    gc.restore(); }
  /**
   * @param gc 通过getGraphicsContext2D()获取。
   * @param angle 旋转的角度。
   * @param tlx 旋转之前图片左上角x坐标。
   * @param tly 旋转之前图片左上角y坐标。
   * @param px 旋转中心点x坐标
   * @param py 旋转中心点y坐标
   */
  public void drawRotatedImage(GraphicsContext gc, Image image,
                               double angle, double tlx, double tly, double px, double py, double width, double height) {
    var rotate = new Rotate();
    gc.save(); //记录当前gc参数
    rotate.setAngle(angle);
    rotate.setPivotX(px);
    rotate.setPivotY(py);
    gc.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(),
      rotate.getMyy(), rotate.getTx(), rotate.getTy());
    gc.drawImage(image, tlx, tly, width, height);
    gc.restore(); //恢复gc参数
  }
}
