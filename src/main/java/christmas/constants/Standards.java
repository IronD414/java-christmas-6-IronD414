package christmas.constants;

public enum Standards {
    EVENT_MONTH(12),
    MONTHLY_FIRST_DATE(1),
    MONTHLY_LAST_DATE(31),
    MAX_ORDER_QUANTITY(20),
    MIN_GIVEAWAY_PRICE(120000),
    MIN_DISCOUNT_DDAY(1000),
    DISCOUNT_WEEKDAY(2023),
    DISCOUNT_WEEKEND(2023),
    DISCOUNT_STAR(1000),
    EVENT_BADGE_STAR(5000),
    EVENT_BADGE_TREE(10000),
    EVENT_BADGE_SANTA(20000);

    private final int value;
    Standards(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
