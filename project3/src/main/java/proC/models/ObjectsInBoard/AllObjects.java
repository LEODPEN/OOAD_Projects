package proC.models.ObjectsInBoard;

import proC.physicsWorld.Circle;
import proC.physicsWorld.LineSegment;
import proC.type.BoardObjectTypeEnum;
import proC.physicsWorld.Vect;

import java.util.List;

public interface AllObjects {

    BoardObjectTypeEnum getType();

    String getName();

    double getX();

    double getY();

    Vect getCenter();

    void expand();

    void shrink();

    void rotate();

    List<LineSegment> getLines();

    List<Circle> getCircles();

}
