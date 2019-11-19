package proC.models;

import proC.type.BoardObjectTypeEnum;
import proC.physicsWorld.Vect;

public interface AllObects {

    BoardObjectTypeEnum getType();

    String getName();

    double getX();

    double getY();

    Vect getCenter();

}
