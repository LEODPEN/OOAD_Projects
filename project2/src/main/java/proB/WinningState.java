package proB.game;

/**
 * @author onion
 * @date 2019/10/21 -2:28 下午
 */
public enum WinningState {
    PLAYER_WIN(1),
    DEALER_WIN(2),
    DRAW(3),
    PLAYER_JACK(4),
    DEALER_JACK(5),
    ;
    private int state;
    public int getState(){
        return state;
    }
    WinningState(int state){
        this.state = state;
    }
}
