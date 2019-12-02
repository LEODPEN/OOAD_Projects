package OOADLab.Lab1;

public class GlobalObject {

    private int value;

    private GlobalObject(){
        value = 0;
    }

    private static class GlobalObjectHolder{
        private static GlobalObject instance = new GlobalObject();
    }

    public static GlobalObject getInstance(){
        return GlobalObjectHolder.instance;
    }

    public void setValue(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
