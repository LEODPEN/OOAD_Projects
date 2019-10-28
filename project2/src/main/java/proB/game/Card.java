package proB.game;

public class Card {

    private Integer value;

    private CardCategoryEnum category;

    public Integer getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public CardCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CardCategoryEnum category) {
        this.category = category;
    }

    public Card(Integer value, CardCategoryEnum category) {
        this.value = value;
        this.category = category;
    }

    //用于匹配对应卡片的image路径
    //类别（0-3）+值（16进制 1-13）
    @Override
    public String toString(){
        return Integer.toString(category.getCategoryValue())+Integer.toHexString(value);
    }

}
