package proB.viewController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import proB.Main;
import proB.game.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

// 这是真正的游戏界面
public class RoomController implements Initializable {

    public Main main;

    //赌注列表
    private ArrayList<BetEnum> betEnums;

    private PlayBoard playBoard;

    @FXML
    private Button deal;

    //玩家总金钱
    @FXML
    private Label bankMoney;

    //本次游戏赌注
    @FXML
    private Label betMoney;

    @FXML
    private Label cardNumber;

    @FXML
    private Circle tenBet;

    @FXML
    private Circle twentyBet;

    @FXML
    private Circle fiftyBet;

    @FXML
    private Circle HundredBet;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setMain(Main main){

        this.main=main;

        playBoard=main.getPlayBoard();
//        playBoard=new PlayBoard(1000,1);
        Player player=playBoard.getPlayer();
        CardHeap cardHeap=playBoard.getCardHeap();

        //初始化赌注列表
        betEnums=new ArrayList<BetEnum>();

        //牌组小于20张，自动重开一副牌
        if(cardHeap.getSize()<=20)
            cardHeap.shuffle();

        //显示牌组
        cardNumber.setText(Integer.toString(cardHeap.getSize()));

        //显示player的money和bet
        bankMoney.setText(Integer.toString(player.getMoney()));
        betMoney.setText("0");

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

    @FXML
    public void removeBetMoneyClickHandler(){

        Integer bankMoneyValue=Integer.valueOf(bankMoney.getText());
        Integer betMoneyValue=Integer.valueOf(betMoney.getText());

        if(betEnums.size()>0){
            BetEnum betEnum=betEnums.remove(betEnums.size()-1);
            bankMoney.setText(Integer.toString(bankMoneyValue + betEnum.getBet()));
            betMoney.setText(Integer.toString(betMoneyValue - betEnum.getBet()));
        }
    }

    public void betMoneyClickHandler(BetEnum betEnum){

        Integer bankMoneyValue=Integer.valueOf(bankMoney.getText());
        Integer betMoneyValue=Integer.valueOf(betMoney.getText());

        Integer difference=bankMoneyValue-betEnum.getBet();

        //如果还能加注
        if(difference>=0){
            //赌注列表
            betEnums.add(betEnum);
            //显示金钱和赌注
            bankMoney.setText(Integer.toString(difference));
            betMoney.setText(Integer.toString(betMoneyValue+betEnum.getBet()));
        }

    }

    @FXML
    public void goDealPage(ActionEvent actionEvent) {

        //处理下注
        if(betEnums.size()>0){
            for (BetEnum betEnum : betEnums) {
                playBoard.bet(betEnum);
            }

            main.showGameView();
        }

    }

}
