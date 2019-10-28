package proB.viewController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import proB.Main;
import proB.game.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class GameController implements Initializable {

    public Main main;

    private PlayBoard playBoard;

    @FXML
    private AnchorPane root;

    @FXML
    private Circle hitButton;

    @FXML
    private Circle standButton;

    @FXML
    private Circle doubleButton;

    //player 当前点数
    @FXML
    private Label playerValue;

    //dealer 当前点数
    @FXML
    private Label dealerValue;

    //剩余牌组,数量与下注页面共享
    @FXML
    private Label cardNumber;

    @FXML
    private Label resultState;

    @FXML
    private Label bankMoney;

    @FXML
    private Label betMoney;

    private static final Double CARD_HEIGHT=120d;

    private static final Double CARD_WIDTH=90d;

    private static final Double CARD_LAYOUT_X=348d;

    private static final Double CARD_DISTANCE_X=23d;

    private static final Double DEALER_CARD_LAYOUT_Y=90d;

    private static final Double PLAYER_CARD_LAYOUT_Y=278d;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void setMain(Main main) {

        this.main = main;

        playBoard=main.getPlayBoard();

        //开始一轮新游戏
        playBoard.initialDraw();

        Player player=playBoard.getPlayer();
        Dealer dealer=playBoard.getDealer();
        CardHeap cardHeap=playBoard.getCardHeap();

        //关闭结果显示
        resultState.setVisible(false);
        //开启抽牌操作button
        openBetButton();

        //显示卡牌
        showCardsWithUnknow(dealer.getCurrentCard());
        showCards(player.getCurrentCard(),PLAYER_CARD_LAYOUT_Y);

        //显示player点数
        playerValue.setText(Integer.toString(player.getCurrentValue()));

        //显示牌组数量
        cardNumber.setText(Integer.toString(cardHeap.getSize()));

        //显示bankMoney和betMoney
        bankMoney.setText(Integer.toString(player.getMoney()));
        betMoney.setText(Integer.toString(playBoard.getBetMoney()/2));

        //判断blackJack
        judgeResult(playBoard.judgeBlackJack());
    }

    private void showCards(ArrayList<Card> cards,Double layoutY){

        for (int i = 0; i <cards.size() ; i++) {
            String imagePath="/img/"+cards.get(i).toString()+".JPG";
            ImageView cardView=new ImageView(new Image(Main.class.getResource(imagePath).toExternalForm()));
            cardView.setFitHeight(CARD_HEIGHT);
            cardView.setFitWidth(CARD_WIDTH);
            cardView.setLayoutX(CARD_LAYOUT_X+i*(CARD_DISTANCE_X));
            cardView.setLayoutY(layoutY);
            root.getChildren().add(cardView);
        }

    }

    //Dealer开场的两张牌中有一张为unknow
    private void showCardsWithUnknow(ArrayList<Card> cards){

        ImageView unknowCard=new ImageView(new Image(Main.class.getResource("/img/unknow.JPG").toExternalForm()));
        unknowCard.setFitHeight(CARD_HEIGHT);
        unknowCard.setFitWidth(CARD_WIDTH);
        unknowCard.setLayoutX(CARD_LAYOUT_X);
        unknowCard.setLayoutY(DEALER_CARD_LAYOUT_Y);
        root.getChildren().add(unknowCard);

        String imagePath="/img/"+cards.get(1).toString()+".JPG";
        ImageView cardView=new ImageView(new Image(Main.class.getResource(imagePath).toExternalForm()));
        cardView.setFitHeight(CARD_HEIGHT);
        cardView.setFitWidth(CARD_WIDTH);
        cardView.setLayoutX(CARD_LAYOUT_X+CARD_DISTANCE_X);
        cardView.setLayoutY(DEALER_CARD_LAYOUT_Y);
        root.getChildren().add(cardView);

        //显示dealer牌组的点数,一张明牌
        dealerValue.setText(Integer.toString(cards.get(1).getValue()));
    }



    @FXML
    public void hitButtonHandler(){
//        System.out.println("hit click");

        Player player=playBoard.getPlayer();
        Dealer dealer=playBoard.getDealer();
        CardHeap cardHeap=playBoard.getCardHeap();

        //抽牌
        playBoard.playerDrawCard();

        //显示牌组
        showCards(player.getCurrentCard(),PLAYER_CARD_LAYOUT_Y);

        //显示点数
        cardNumber.setText(Integer.toString(cardHeap.getSize()));
        playerValue.setText(Integer.toString(player.getCurrentValue()));


        //判断结果
        //玩家等于21点，自动停牌
        if(player.getCurrentValue()==21){
            standButtonHandler();
        }

        //玩家大于21点，庄家胜利
        if(player.getCurrentValue()>21){

            //显示dealer的暗牌
            showCards(dealer.getCurrentCard(),DEALER_CARD_LAYOUT_Y);
            dealerValue.setText(Integer.toString(dealer.getCurrentValue()));

            judgeResult(WinningState.DEALER_WIN);
        }

    }


    //double后玩家自动抽一张牌并停牌
    @FXML
    public void doubleButtonHandler(){

//        System.out.println("double click");
        playBoard.doubleBet();

        hitButtonHandler();

        Player player=playBoard.getPlayer();

        //显示bankMoney和betMoney
        bankMoney.setText(Integer.toString(player.getMoney()));
        betMoney.setText(Integer.toString(playBoard.getBetMoney()/2));

        //double后玩家抽一次牌没爆，自动stand
        if(player.getCurrentValue()<21){
            standButtonHandler();
        }

    }

    //庄家小于17点继续抽牌
    @FXML
    public void standButtonHandler(){
//        System.out.println("stand click");

        playBoard.dealerDrawCard();
        Dealer dealer=playBoard.getDealer();
        CardHeap cardHeap=playBoard.getCardHeap();

        showCards(dealer.getCurrentCard(),DEALER_CARD_LAYOUT_Y);

        cardNumber.setText(Integer.toString(cardHeap.getSize()));
        dealerValue.setText(Integer.toString(dealer.getCurrentValue()));

        //庄家抽牌自爆
        if(dealer.getCurrentValue()>21){
            judgeResult(WinningState.PLAYER_WIN);
        }else{
            judgeResult(playBoard.decideWinner());
        }


    }

    //处理结果
    private void judgeResult(WinningState winningState){

        switch (winningState){

            case PLAYER_WIN:
                resultState.setText("Player Win");
                break;
            case DEALER_WIN:
                resultState.setText("Dealer Win");
                break;
            case DRAW:
                resultState.setText("Draw");
                break;
            case PLAYER_JACK:
                //打补丁，修bug
                playerValue.setText("21");
                resultState.setText("Player BlackJack");
                break;
            case DEALER_JACK:
                //打补丁，修bug
                dealerValue.setText("21");
                resultState.setText("Dealer BlackJack");
                break;
            case NOT_DECIDE:
                resultState.setVisible(false);
                return;
            default:
                System.out.println("something wrong");
                break;
        }

        resultState.setVisible(true);
        closeBetButton();

//        DropShadow dropshadow = new DropShadow();// 阴影向外
//        dropshadow.setRadius(10);// 颜色蔓延的距离
//        dropshadow.setOffsetX(0);// 水平方向，0则向左右两侧，正则向右，负则向左
//        dropshadow.setOffsetY(0);// 垂直方向，0则向上下两侧，正则向下，负则向上
//        dropshadow.setSpread(0.1);// 颜色变淡的程度
//        dropshadow.setColor(Color.BLACK);// 设置颜色
//        root.setEffect(dropshadow);// 绑定指定窗口控件
//        HBox treeRoot = new HBox();// 创建最底层的面板
//        treeRoot.setBackground(javafx.scene.layout.Background.EMPTY);// 设置背景色为透明
//        treeRoot.setAlignment(Pos.CENTER);// 设置对齐方式为居中
//        treeRoot.setPadding(new Insets(dropshadow.getRadius()));// 设置要显示的阴影宽度为根控件与底层容器的四边距离
//        treeRoot.getChildren().add(root);// 添加根控件到底层容器中
//        root.setPrefSize(270, 350);
//        Scene scene = new Scene(treeRoot, 270, 350, Color.TRANSPARENT);

    }

    //开启所有抽牌操作button
    private void openBetButton(){
        hitButton.setVisible(true);
        doubleButton.setVisible(true);
        standButton.setVisible(true);
    }
    //关闭所有抽牌操作button
    private void closeBetButton(){
        hitButton.setVisible(false);
        doubleButton.setVisible(false);
        standButton.setVisible(false);
    }
    //点击resultState返回room页面
    @FXML
    private void returnToRoomPage(){
        main.showRoomView();
    }

}
