package proB.game;

public class Card {

    private Integer value;

    private CardCategoryEnum category;

    public Integer getValue() {
        return value;
    }

    public Card(Integer value, CardCategoryEnum category) {
        this.value = value;
        this.category = category;
    }

    @Override
    public String toString(){
        return category.getCategoryValue() +Integer.toHexString(value);
    }

}
