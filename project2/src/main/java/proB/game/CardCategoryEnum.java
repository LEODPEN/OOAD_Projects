package proB.game;

// 只保留主牌
public enum CardCategoryEnum {
    SPADE(0,"♠️"),
    HEART(1,"♥️"),
    CLUB(2,"♣️"),
    DIAMOND(3,"♦️"),
    ;

    public Integer categoryValue;

    public String categoryName;

    public Integer getCategoryValue() {
        return categoryValue;
    }

    public String getCategoryName() {
        return categoryName;
    }

    CardCategoryEnum(Integer categoryValue, String categoryName) {
        this.categoryValue = categoryValue;
        this.categoryName = categoryName;
    }
}
