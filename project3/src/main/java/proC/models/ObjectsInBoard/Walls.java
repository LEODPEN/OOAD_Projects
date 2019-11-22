package proC.models.ObjectsInBoard;

import proC.models.ObjectsInBoard.AllObjects;
import proC.physicsWorld.Circle;
import proC.physicsWorld.LineSegment;
import proC.type.BoardObjectTypeEnum;
import proC.physicsWorld.Vect;
import proC.utils.Constants;

import java.util.ArrayList;
import java.util.List;

// 四周的墙壁
public class Walls implements AllObjects {

    // 边
    private final List<LineSegment> sides;
    // 角
    private final List<Circle> corners;
    private final double width;


    Walls() {
        width = Constants.BOARD_WIDTH;
        sides = new ArrayList<>();
        corners = new ArrayList<>();
        // 顺时针
        sides.add(new LineSegment(0,0,width, 0));
        sides.add(new LineSegment(width,0,width,width));
        sides.add(new LineSegment(width,width,0,width));
        sides.add(new LineSegment(0,width,0,0));

        corners.add(new Circle(0,0,0));
        corners.add(new Circle(width,0,0));
        corners.add(new Circle(width,width,0));
        corners.add(new Circle(0,width,0));

    }

    public double getRCoefficient() {
        return 1.0;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public List<LineSegment> getLines() {
        return sides;
    }

    @Override
    public List<Circle> getCircles() {
        return corners;
    }

    @Override
    public Vect getCenter() {
        return null;
    }

    @Override
    public BoardObjectTypeEnum getType() {
        return null;
    }

    @Override
    public String getName() {
        return "4 walls";
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }
}
