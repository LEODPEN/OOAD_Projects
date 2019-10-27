package proB.game;

// 只保留主牌
public enum CardCategoryEnum {
    HEI_TAO(0,"♠️"),
    HONG_TAO(1,"♥️"),
    MEI_HUA(2,"♣️"),
    FANG_KUAI(3,"♦️"),
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
