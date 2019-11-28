package proC.models.ObjectsInBoard;

import proC.physicsWorld.LineSegment;
import proC.utils.Observable;

import java.util.ArrayList;
import java.util.List;


public interface Gizmo extends AllObjects, Observable {
    // 旋转
    // 只有triangle 和 curve 需要
    void rotate();

    double getRCoefficient();

    // 当前角度
    double getAngle();

    // 变大
    // 我们的变大是左下角坐标不变,rail 和 curve 无法变大
    void expand();

    // 设置坐标
    void setCoordinates(double x, double y);

    void activateAction();

    default List<LineSegment> getDeleteGravityLines(){
        return new ArrayList<>();
    };
}
