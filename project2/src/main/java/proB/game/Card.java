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
}
