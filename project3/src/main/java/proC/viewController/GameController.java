package proC.viewController;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class GameController implements EventHandler<KeyEvent> {
  private Model model;
  public GameController(Model model) {this.model = model;}
  @Override
  public void handle(KeyEvent keyEvent) {
    switch (keyEvent.getCode()){
      case UP:
      case W:
        if(keyEvent.getEventType()== KeyEvent.KEY_PRESSED)
          model.setMovingUp(true);
        else if(keyEvent.getEventType()== KeyEvent.KEY_RELEASED)
          model.setMovingUp(false);
        break;
      case DOWN:
      case S:
        if(keyEvent.getEventType()== KeyEvent.KEY_PRESSED)
          model.setMovingDown(true);
        else if(keyEvent.getEventType()== KeyEvent.KEY_RELEASED)
          model.setMovingDown(false);
        break;
      case LEFT:
      case A:
        if(keyEvent.getEventType()== KeyEvent.KEY_PRESSED)
          model.setMovingLeft(true);
        else if(keyEvent.getEventType()== KeyEvent.KEY_RELEASED)
          model.setMovingLeft(false);
        break;
      case RIGHT:
      case D:
        if(keyEvent.getEventType()== KeyEvent.KEY_PRESSED)
          model.setMovingRight(true);
        else if(keyEvent.getEventType()== KeyEvent.KEY_RELEASED)
          model.setMovingRight(false);
        break;
      default:
        break;
    }
  }
}
