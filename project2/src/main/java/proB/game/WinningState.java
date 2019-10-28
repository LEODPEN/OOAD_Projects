package proB.game;

/**
 * @author onion
 * @date 2019/10/21 -2:28 下午
 */
public enum WinningState {
    PLAYER_WIN(),
    DEALER_WIN(),
    DRAW(),
    PLAYER_JACK(),
    DEALER_JACK(),
    NOT_DECIDE(),
    ;

    WinningState(){
    }
}
