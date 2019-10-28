package proB.viewController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import proB.Main;
import proB.game.BetEnum;

import java.net.URL;
import java.util.ResourceBundle;

// 这是真正的游戏界面
public class RoomController implements Initializable {

    public Main main;

    @FXML
    public Button deal;

    //玩家总金钱
    @FXML
    Label bankMoney;

    //本次游戏赌注
    @FXML
    Label betMoney;

    @FXML
    Circle tenBet;

    @FXML
    Circle twentyBet;

    @FXML
    Circle fiftyBet;

    @FXML
    Circle HundredBet;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setMain(Main main){
        this.main=main;
    }

    @FXML
    public void tenBetClickHandler(){
        betMoneyClickHandler(BetEnum.TEN);
    }

    @FXML
    public void twentyBetClickHandler(){
        betMoneyClickHandler(BetEnum.TWENTY);
    }

    @FXML
    public void fiftyBetClickHandler(){
        betMoneyClickHandler(BetEnum.FIFTY);
    }
    @FXML
    public void hundredBetClickHandler(){
        betMoneyClickHandler(BetEnum.HUNDRED);
    }

    public void betMoneyClickHandler(BetEnum betEnum){

        Integer bankMoneyValue=Integer.valueOf(bankMoney.getText());
        Integer betMoneyValue=Integer.valueOf(betMoney.getText());

        Integer difference=bankMoneyValue-betEnum.getBet();

        if(difference>=0){
            bankMoney.setText(Integer.toString(difference));
            betMoney.setText(Integer.toString(betMoneyValue+betEnum.getBet()));
        }

    }

    @FXML
    public void goDealPage(ActionEvent actionEvent) {
        main.showGameView();
    }
}
