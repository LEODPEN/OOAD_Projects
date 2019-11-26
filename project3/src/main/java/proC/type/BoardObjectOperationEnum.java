package proC.type;

public enum  BoardObjectOperationEnum {

    EXPEND(0,"放大"),
    SHRINK(1,"缩小"),
    ROTATE(2,"旋转"),
    REMOVE(3,"移除");

    private Integer num; // 编号

    private String name;


    BoardObjectOperationEnum(Integer num, String name) {
        this.name = name;
        this.num = num;
    }
    @Override
    public String toString() {
        return "BoardObjectOperationEnum{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}
