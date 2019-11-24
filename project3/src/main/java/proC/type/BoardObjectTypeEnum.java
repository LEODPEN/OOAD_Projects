package proC.type;

public enum BoardObjectTypeEnum {
    CIRCLE(0,"circle"),
    SQUARE(1,"Square"),
    TRIANGLE(2,"Triangle"),
    ABSORBER(3,"Absorber"),
    WALLS(4,"Walls"),
    RAIL(5,"Rail"),
    LEFT_PADDLE(6,"Left_Paddle"),
    RIGHT_PADDLE(7,"Right_Paddle"),
    BALL(8,"Ball"),
    CURVE(9, "CURVE"),
    CLICK(10,"CLICK")//仅用于选择组件
    ;

    private Integer num; // 编号

    private String name;


    BoardObjectTypeEnum(Integer num,String name) {
        this.name = name;
        this.num = num;
    }
    @Override
    public String toString() {
        return "BoardObjectTypeEnum{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}
