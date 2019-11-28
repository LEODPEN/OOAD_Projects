package proC.utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public interface DrawImage {

    /**
     * @param gc 通过getGraphicsContext2D()获取。
     * @param image 图片
     * @param angle 旋转的角度。
     * @param tlx 旋转之前图片左上角x坐标。
     * @param tly 旋转之前图片左上角y坐标。
     * @param px 旋转中心点x坐标
     * @param py 旋转中心点y坐标
     */
    static void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlx, double tly, double px, double py, double width, double height) {

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
